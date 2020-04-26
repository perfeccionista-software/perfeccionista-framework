package io.perfeccionista.framework.pagefactory.browser;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.exceptions.IncorrectServiceConfigurationException;
import io.perfeccionista.framework.pagefactory.browser.registry.WebBrowserDispatcherRegistry;
import io.perfeccionista.framework.service.Service;
import io.perfeccionista.framework.service.ServiceConfiguration;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.exceptions.messages.EnvironmentMessages.CHECK_CONFIGURATION_NOT_VALID;

public class WebBrowserService implements Service {

    private WebBrowserServiceConfiguration configuration;
    private WebBrowserDispatcherRegistry webBrowserDispatcherRegistry;

    @Override
    public void init(@NotNull Environment environment, @NotNull ServiceConfiguration configuration) {
        this.configuration = validate(configuration);
        webBrowserDispatcherRegistry = new WebBrowserDispatcherRegistry(this.configuration.getWebBrowserConfigurations());
        webBrowserDispatcherRegistry.init();
    }

    @Override
    public void afterTest() {
        if (configuration.isCloseWebBrowsersAfterTest()) {
            closeAll();
        }
    }


    // ThreadLocal. Возвращает дефолтный экземпляр браузера для этого потока
    public WebBrowserDispatcher setActiveDispatcher(String webBrowserDispatcherName) {
        return null;
    }

    public WebBrowserDispatcher getActiveDispatcher() {
        return this.webBrowserDispatcherRegistry.getDefault();
    }

    // TODO: Не делает его активным
    public WebBrowserDispatcher getDispatcherByName(String webBrowserDispatcherName) {
        return this.webBrowserDispatcherRegistry.getByName(webBrowserDispatcherName);
    }

    // Сразу делаем его дефолтным
    public WebBrowserDispatcher createDispatcher(String webBrowserConfigurationName) {
        return null;
    }

    public WebBrowserDispatcher createDispatcher(String webBrowserConfigurationName, String webBrowserDispatcherName) {
        return null;
    }
    // ThreadLocal. Закрывает все драйверы для этого потока

    public WebBrowserService closeAll() {
        return this;
    }





    protected WebBrowserServiceConfiguration validate(ServiceConfiguration configuration) {
        if (configuration instanceof WebBrowserServiceConfiguration) {
            return (WebBrowserServiceConfiguration) configuration;
        }
        throw new IncorrectServiceConfigurationException(
                CHECK_CONFIGURATION_NOT_VALID.getMessage(configuration.getClass().getCanonicalName(), this.getClass().getCanonicalName()));
    }

}

