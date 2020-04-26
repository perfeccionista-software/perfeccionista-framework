package io.perfeccionista.framework.bdd.parameters;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.pagefactory.browser.WebBrowserService;
import io.perfeccionista.framework.value.ValueService;
import io.perfeccionista.framework.value.string.StringValue;

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
                .createDispatcher(getProcessedValue())
                .launch();
    }

    @Override
    public void launch(String webBrowserDispatcherName) {
        environment.getService(WebBrowserService.class)
                .createDispatcher(getProcessedValue(), webBrowserDispatcherName)
                .launch();
    }

    @Override
    public String getProcessedValue() {
        return environment.getService(ValueService.class).stringProcess(rawInput);
    }

    @Override
    public StringValue getValue() {
        return environment.getService(ValueService.class).stringEquals(rawInput);
    }

    @Override
    public String getRaw() {
        return rawInput;
    }

}
