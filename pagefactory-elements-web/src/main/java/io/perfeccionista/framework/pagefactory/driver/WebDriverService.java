package io.perfeccionista.framework.pagefactory.driver;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.exceptions.IncorrectServiceConfigurationException;
import io.perfeccionista.framework.pagefactory.driver.registry.WebDriverInstanceRegistry;
import io.perfeccionista.framework.service.Service;
import io.perfeccionista.framework.service.ServiceConfiguration;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.exceptions.messages.EnvironmentMessages.CHECK_CONFIGURATION_NOT_VALID;

public class WebDriverService implements Service {

    private WebDriverServiceConfiguration configuration;
    private WebDriverInstanceRegistry webDriverInstanceRegistry;

    @Override
    public void init(@NotNull Environment environment, @NotNull ServiceConfiguration configuration) {
        this.configuration = validate(configuration);
        webDriverInstanceRegistry = new WebDriverInstanceRegistry(this.configuration.getWebDriverInstanceConfigurations());
        webDriverInstanceRegistry.init();
    }

    @Override
    public void afterTest() {
        if (configuration.isCloseBrowsersAfterTest()) {
            closeAll();
        }
    }
    // ????
    public WebDriverInstanceRegistry getWebDriverInstanceRegistry() {
        return webDriverInstanceRegistry;
    }

    // берем из пула или запускаем и создаем новый контекст в браузере
    public WebDriverService launch(String configurationName) {

        return this;
    }

    // берем из пула или запускаем и создаем новый контекст в браузере
    public WebDriverService launch(String driverName, String configurationName) {

        return this;
    }

    // ThreadLocal. Делает дефолтным драйвер с указанным именем
    public WebDriverService switchTo(String driverName) {

        return this;
    }

    // ThreadLocal. Закрывает дефолтный драйвер для этого потока
    public WebDriverService close() {

        return this;
    }

    // ThreadLocal. Закрывает драйвер с указанным именем для этого потока
    public WebDriverService close(String driverName) {

        return this;
    }

    // ThreadLocal. Закрывает все драйверы для этого потока
    public WebDriverService closeAll() {

        return this;
    }

    // ThreadLocal. Возвращает дефолтный экземпляр браузера для этого потока
    public WebDriverInstance getDriverInstance() {
        return this.webDriverInstanceRegistry.getDefault();
    }

    // ThreadLocal&
    public WebDriverInstance getDriverInstance(String driverInstanceName) {
        return this.webDriverInstanceRegistry.getByName(driverInstanceName);
    }






    protected WebDriverServiceConfiguration validate(ServiceConfiguration configuration) {
        if (configuration instanceof WebDriverServiceConfiguration) {
            return (WebDriverServiceConfiguration) configuration;
        }
        throw new IncorrectServiceConfigurationException(
                CHECK_CONFIGURATION_NOT_VALID.getMessage(configuration.getClass().getCanonicalName(), this.getClass().getCanonicalName()));
    }

}

