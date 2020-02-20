package io.perfeccionista.framework.pagefactory;

import io.perfeccionista.framework.exceptions.IncorrectServiceConfigurationException;
import io.perfeccionista.framework.service.Service;
import io.perfeccionista.framework.service.ServiceConfiguration;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.exceptions.messages.EnvironmentMessages.CHECK_CONFIGURATION_NOT_VALID;

/**
 * Сущность является пейджфактори, которая
 */
public class PageFactoryService implements Service {

    private PageFactoryServiceConfiguration configuration;

    @Override
    public void init(@NotNull ServiceConfiguration configuration) {
        this.configuration = validate(configuration);
    }









    @Override
    public void afterTest() {
        if (configuration.closeBrowsersAfterTest()) {
            // закрываем все экземпляры браузеров
        }
    }

    protected PageFactoryServiceConfiguration validate(ServiceConfiguration configuration) {
        if (configuration instanceof PageFactoryServiceConfiguration) {
            return (PageFactoryServiceConfiguration) configuration;
        }
        throw new IncorrectServiceConfigurationException(
                CHECK_CONFIGURATION_NOT_VALID.getMessage(configuration.getClass().getCanonicalName(), this.getClass().getCanonicalName()));
    }

}
