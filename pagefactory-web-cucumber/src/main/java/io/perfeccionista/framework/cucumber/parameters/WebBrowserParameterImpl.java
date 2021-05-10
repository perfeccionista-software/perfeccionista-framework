package io.perfeccionista.framework.cucumber.parameters;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.exceptions.IncorrectStringValueResult;
import io.perfeccionista.framework.pagefactory.dispatcher.WebBrowserService;
import io.perfeccionista.framework.value.ValueService;
import io.perfeccionista.framework.value.string.StringValue;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

import static io.perfeccionista.framework.exceptions.messages.EnvironmentCucumberApiMessages.STRING_VALUE_RESULT_IS_NULL;

public class WebBrowserParameterImpl implements WebBrowserParameter {

    private final Environment environment;
    private final String rawInput;

    public WebBrowserParameterImpl(Environment environment, String rawInput) {
        this.environment = environment;
        this.rawInput = rawInput;
    }

    @Override
    public void launch() {
        environment.getService(WebBrowserService.class)
                .createDispatcher(getNotNullProcessedValue())
                .launch();
    }

    @Override
    public void launch(@NotNull String webBrowserDispatcherName) {
        environment.getService(WebBrowserService.class)
                .createDispatcher(getNotNullProcessedValue(), webBrowserDispatcherName)
                .launch();
    }

    @Override
    public @NotNull String getNotNullProcessedValue() {
        return Optional.ofNullable(getProcessedValue())
                .orElseThrow(() -> IncorrectStringValueResult.exception(STRING_VALUE_RESULT_IS_NULL.getMessage()));
    }

    @Override
    public String getProcessedValue() {
        return environment.getService(ValueService.class).stringProcess(rawInput);
    }

    @Override
    public @NotNull StringValue getValue() {
        return environment.getService(ValueService.class).stringEquals(rawInput);
    }

    @Override
    public @NotNull String getRaw() {
        return rawInput;
    }

}
