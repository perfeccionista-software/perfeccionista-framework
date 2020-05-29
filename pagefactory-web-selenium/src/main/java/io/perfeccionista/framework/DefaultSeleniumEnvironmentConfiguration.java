package io.perfeccionista.framework;

import io.perfeccionista.framework.action.runner.ActionRunnerConfiguration;
import io.perfeccionista.framework.action.runner.SeleniumActionRunnerConfiguration;
import io.perfeccionista.framework.action.timeouts.DefaultPageFactoryTimeouts;
import io.perfeccionista.framework.action.timeouts.Timeouts;
import io.perfeccionista.framework.pagefactory.browser.DefaultSeleniumWebBrowserServiceConfiguration;
import io.perfeccionista.framework.pagefactory.browser.WebBrowserService;
import io.perfeccionista.framework.service.UseService;
import org.jetbrains.annotations.NotNull;

@UseService(service = WebBrowserService.class, configuration = DefaultSeleniumWebBrowserServiceConfiguration.class)
public class DefaultSeleniumEnvironmentConfiguration extends DefaultEnvironmentConfiguration {

    @Override
    public @NotNull ActionRunnerConfiguration getActionRunnerConfiguration() {
        return new SeleniumActionRunnerConfiguration();
    }

    @Override
    public @NotNull Timeouts getTimeouts() {
        return new DefaultPageFactoryTimeouts();
    }

}
