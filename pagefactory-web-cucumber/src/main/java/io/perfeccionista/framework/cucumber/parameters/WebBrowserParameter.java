package io.perfeccionista.framework.cucumber.parameters;

import org.jetbrains.annotations.NotNull;

public interface WebBrowserParameter extends ValueStringParameter {

    void launch();

    void launch(@NotNull String webBrowserDispatcherName);

}
