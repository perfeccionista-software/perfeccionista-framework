package io.perfeccionista.framework.bdd.parameters;

public interface WebBrowserParameter extends ValueStringParameter {

    void launch();

    void launch(String webBrowserDispatcherName);

}
