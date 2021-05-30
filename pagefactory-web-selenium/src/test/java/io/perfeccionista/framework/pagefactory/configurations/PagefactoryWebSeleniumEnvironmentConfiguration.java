package io.perfeccionista.framework.pagefactory.configurations;

import io.perfeccionista.framework.DefaultEnvironmentConfiguration;
import io.perfeccionista.framework.invocation.InvocationService;
import io.perfeccionista.framework.pagefactory.WebPageService;
import io.perfeccionista.framework.pagefactory.dispatcher.configuration.DefaultSeleniumWebBrowserServiceConfiguration;
import io.perfeccionista.framework.pagefactory.dispatcher.WebBrowserService;
import io.perfeccionista.framework.service.ConfiguredServiceHolder;
import io.perfeccionista.framework.service.Service;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class PagefactoryWebSeleniumEnvironmentConfiguration extends DefaultEnvironmentConfiguration {

    @Override
    public @NotNull Map<Class<? extends Service>, ConfiguredServiceHolder> getServices() {
        ConfiguredServiceHolder webBrowserServiceHolder =
                ConfiguredServiceHolder.of(WebBrowserService.class, DefaultSeleniumWebBrowserServiceConfiguration.class);
        ConfiguredServiceHolder webPageServiceHolder =
                ConfiguredServiceHolder.of(WebPageService.class, TestSeleniumWebPageServiceConfiguration.class);
        ConfiguredServiceHolder invocationServiceHolder =
                ConfiguredServiceHolder.of(InvocationService.class, AllureInvocationServiceConfiguration.class);

        Map<Class<? extends Service>, ConfiguredServiceHolder> services = super.getServices();
        services.put(WebBrowserService.class, webBrowserServiceHolder);
        services.put(WebPageService.class, webPageServiceHolder);
        services.put(InvocationService.class, invocationServiceHolder);
        return services;
    }

}
