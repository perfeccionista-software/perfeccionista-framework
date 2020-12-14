package io.perfeccionista.framework.cucumber.parameters;

public interface WebBrowserParameter extends ValueStringParameter {

    void launch();

    void launch(String webBrowserDispatcherName);

}
