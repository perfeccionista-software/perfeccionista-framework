package io.perfeccionista.framework.value.checker.number.resolver;

import io.perfeccionista.framework.cucumber.resolver.CucumberResolver;
import io.perfeccionista.framework.exceptions.RequiredArgumentNotFound;
import io.perfeccionista.framework.exceptions.attachments.StringAttachmentEntry;
import io.perfeccionista.framework.value.number.NumberValue;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

import static io.perfeccionista.framework.exceptions.messages.UtilsMessages.REQUIRED_ARGUMENT_NOT_FOUND;
import static io.perfeccionista.framework.utils.StringUtils.objectsToString;

public interface IntegerValueCheckerCucumberResolver extends CucumberResolver<NumberValue<Integer>> {

    default int extractFirstNotNullIntegerArgument(@Nullable Object... args) {
        if (Objects.isNull(args) || args.length == 0 || args[0] == null) {
            throw RequiredArgumentNotFound.exception(REQUIRED_ARGUMENT_NOT_FOUND.getMessage())
                    .addLastAttachmentEntry(StringAttachmentEntry.of("Arguments", objectsToString(args)));
        }
        return (Integer) args[0];
    }

}
