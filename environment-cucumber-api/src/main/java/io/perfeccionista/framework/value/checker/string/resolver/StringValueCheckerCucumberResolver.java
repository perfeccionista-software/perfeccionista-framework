package io.perfeccionista.framework.value.checker.string.resolver;

import io.perfeccionista.framework.cucumber.resolver.CucumberResolver;
import io.perfeccionista.framework.exceptions.RequiredArgumentNotFound;
import io.perfeccionista.framework.exceptions.attachments.StringAttachmentEntry;
import io.perfeccionista.framework.value.string.StringValue;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

import static io.perfeccionista.framework.exceptions.messages.UtilsMessages.REQUIRED_ARGUMENT_NOT_FOUND;
import static io.perfeccionista.framework.utils.StringUtils.objectsToString;

public interface StringValueCheckerCucumberResolver extends CucumberResolver<StringValue> {

    default @NotNull String extractFirstNotNullStringArgument(@Nullable Object... args) {
        if (Objects.isNull(args) || args.length == 0 || args[0] == null) {
            throw RequiredArgumentNotFound.exception(REQUIRED_ARGUMENT_NOT_FOUND.getMessage())
                    .addLastAttachmentEntry(StringAttachmentEntry.of("Arguments", objectsToString(args)));
        }
        return (String) args[0];
    }

}
