package io.perfeccionista.framework;

import io.perfeccionista.framework.invocation.runner.InvocationRunnerConfiguration;
import io.perfeccionista.framework.invocation.runner.SeleniumInvocationRunnerConfiguration;
import io.perfeccionista.framework.invocation.timeouts.DefaultPageFactoryTimeouts;
import io.perfeccionista.framework.invocation.timeouts.Timeouts;
import io.perfeccionista.framework.pagefactory.browser.DefaultSeleniumWebBrowserServiceConfiguration;
import io.perfeccionista.framework.pagefactory.browser.WebBrowserService;
import io.perfeccionista.framework.service.UseService;
import org.jetbrains.annotations.NotNull;

@UseService(service = WebBrowserService.class, configuration = DefaultSeleniumWebBrowserServiceConfiguration.class)
public class DefaultSeleniumEnvironmentConfiguration extends DefaultEnvironmentConfiguration {

    @Override
    public @NotNull InvocationRunnerConfiguration getActionRunnerConfiguration() {
        return new SeleniumInvocationRunnerConfiguration();
    }

    @Override
    public @NotNull Timeouts getTimeouts() {
        return new DefaultPageFactoryTimeouts();
    }

}
