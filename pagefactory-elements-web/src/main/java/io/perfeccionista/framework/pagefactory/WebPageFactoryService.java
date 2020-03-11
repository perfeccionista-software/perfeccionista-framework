package io.perfeccionista.framework.pagefactory;

import io.perfeccionista.framework.exceptions.IncorrectServiceConfigurationException;
import io.perfeccionista.framework.pagefactory.registry.WebPageRegistry;
import io.perfeccionista.framework.service.Service;
import io.perfeccionista.framework.service.ServiceConfiguration;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.exceptions.messages.EnvironmentMessages.CHECK_CONFIGURATION_NOT_VALID;

/**
 * Сущность является пейджфактори, которая
 */
public class WebPageFactoryService implements Service {

    private WebPageFactoryServiceConfiguration configuration;
    private WebPageRegistry pageRegistry;

    @Override
    public void init(@NotNull ServiceConfiguration configuration) {
        this.configuration = validate(configuration);
        pageRegistry = new WebPageRegistry(this.configuration.getElementsConfiguration());
        pageRegistry.init(this.configuration.getPagePackages());
    }

    public WebPageRegistry getPageRegistry() {
        return pageRegistry;
    }







    @Override
    public void afterTest() {
        if (configuration.isCloseBrowsersAfterTest()) {
            // закрываем все экземпляры браузеров
        }
    }

    protected WebPageFactoryServiceConfiguration validate(ServiceConfiguration configuration) {
        if (configuration instanceof WebPageFactoryServiceConfiguration) {
            return (WebPageFactoryServiceConfiguration) configuration;
        }
        throw new IncorrectServiceConfigurationException(
                CHECK_CONFIGURATION_NOT_VALID.getMessage(configuration.getClass().getCanonicalName(), this.getClass().getCanonicalName()));
    }

}
