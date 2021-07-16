package io.perfeccionista.framework.value.string;

import io.perfeccionista.framework.exceptions.StringValueNotMatch;
import io.perfeccionista.framework.exceptions.attachments.ValueAttachmentEntry;
import io.perfeccionista.framework.value.checker.StringChecker;
import io.perfeccionista.framework.value.transformer.string.ActualStringValueTransformer;
import io.perfeccionista.framework.value.transformer.string.ExpectedStringValueTransformer;
import io.perfeccionista.framework.value.transformer.string.StringValueTransformer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.UnaryOperator;

import static io.perfeccionista.framework.exceptions.messages.EnvironmentMessages.STRING_VALUE_NOT_MATCH;

public class DefaultStringValue implements StringValue {

    protected final StringChecker stringChecker;

    protected boolean inverse = false;

    public DefaultStringValue(StringChecker stringChecker) {
        this.stringChecker = stringChecker;
    }

    @Override
    public StringValue transformExpected(UnaryOperator<String> transformFunction) {
        stringChecker.addTransformer((ExpectedStringValueTransformer) transformFunction::apply);
        return this;
    }

    @Override
    public StringValue transformActual(UnaryOperator<String> transformFunction) {
        stringChecker.addTransformer((ActualStringValueTransformer) transformFunction::apply);
        return this;
    }

    @Override
    public StringValue addTransformer(@NotNull StringValueTransformer transformer) {
        stringChecker.addTransformer(transformer);
        return this;
    }

    @Override
    public StringValue withoutProcessing() {
        stringChecker.setProcessExpectedStatement(false);
        return this;
    }

    @Override
    public StringValue inverse() {
        inverse = true;
        return this;
    }

    @Override
    public @NotNull String get() {
        return stringChecker.getProcessedExpected();
    }

    @Override
    public boolean check(@Nullable String actual) {
        if (actual == null) {
            return false;
        }
        stringChecker.setActual(actual);
        if (inverse) {
            return !stringChecker.check();
        }
        return stringChecker.check();
    }

    @Override
    public StringValue shouldMatch(String actual) {
        if (check(actual)) {
            return this;
        }
        throw StringValueNotMatch.assertionError(STRING_VALUE_NOT_MATCH.getMessage())
                .addLastAttachmentEntry(ValueAttachmentEntry.of(this));
    }

    @Override
    public @NotNull String getShortDescription() {
        return stringChecker.getShortComparisonDescription();
    }

    @Override
    public String toString() {
        return stringChecker.getComparisonDescription();
    }

}
