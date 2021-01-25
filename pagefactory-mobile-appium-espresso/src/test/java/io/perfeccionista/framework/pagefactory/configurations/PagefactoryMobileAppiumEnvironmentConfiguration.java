package io.perfeccionista.framework.pagefactory.configurations;

import io.perfeccionista.framework.DefaultEnvironmentConfiguration;
import io.perfeccionista.framework.pagefactory.MobilePageService;
import io.perfeccionista.framework.service.ConfiguredServiceHolder;
import io.perfeccionista.framework.service.Service;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class PagefactoryMobileAppiumEnvironmentConfiguration extends DefaultEnvironmentConfiguration {

    @Override
    public @NotNull Map<Class<? extends Service>, ConfiguredServiceHolder> getServices() {
//        ConfiguredServiceHolder webBrowserServiceHolder =
//                ConfiguredServiceHolder.of(MobileDeviceService.class, DefaultAppiumMobileDeviceServiceConfiguration.class);
        ConfiguredServiceHolder webPageServiceHolder =
                ConfiguredServiceHolder.of(MobilePageService.class, TestAppiumMobilePageServiceConfiguration.class);

        Map<Class<? extends Service>, ConfiguredServiceHolder> services = super.getServices();
//        services.put(MobileDeviceService.class, webBrowserServiceHolder);
        services.put(MobilePageService.class, webPageServiceHolder);
        return services;
    }

}
