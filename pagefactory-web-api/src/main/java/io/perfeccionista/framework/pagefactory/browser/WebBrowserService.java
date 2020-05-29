package io.perfeccionista.framework.pagefactory.browser;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.exceptions.IncorrectServiceConfigurationException;
import io.perfeccionista.framework.exceptions.WebBrowserServiceException;
import io.perfeccionista.framework.exceptions.mapper.ExceptionMapper;
import io.perfeccionista.framework.service.Service;
import io.perfeccionista.framework.service.ServiceConfiguration;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static io.perfeccionista.framework.exceptions.messages.EnvironmentMessages.CHECK_CONFIGURATION_NOT_VALID;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.NO_ACTIVE_WEB_BROWSER_DISPATCHER_FOUND;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.NO_EXCEPTION_MAPPER_BY_CLASS_DECLARED;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.NO_WEB_BROWSER_DISPATCHER_WITH_NAME_CREATED;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.WEB_BROWSER_CONFIGURATION_NOT_DECLARED;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.WEB_BROWSER_SERVICE_CONFIGURATION_NOT_DECLARED;

public class WebBrowserService implements Service {

    protected Environment environment;
    protected WebBrowserServiceConfiguration configuration;

    protected Set<WebBrowserDispatcher> webBrowserDispatchers = new HashSet<>();
    protected Map<String, WebBrowserDispatcher> webBrowserDispatchersByName = new HashMap<>();
    protected WebBrowserDispatcher activeWebBrowserDispatcher = null;

    @Override
    public void init(@NotNull Environment environment, @Nullable ServiceConfiguration configuration) {
        this.environment = environment;
        this.configuration = validate(configuration);
    }

    @Override
    public void afterTest() {
        if (configuration.isCloseWebBrowsersAfterTest()) {
            closeAll();
        }
    }

    public WebBrowserDispatcher createDispatcher(String webBrowserConfigurationName) {
        WebBrowserDispatcher webBrowserDispatcher = Optional.ofNullable(configuration.getWebBrowserConfigurations().get(webBrowserConfigurationName))
                .orElseThrow(() -> new WebBrowserServiceException(WEB_BROWSER_CONFIGURATION_NOT_DECLARED.getMessage(webBrowserConfigurationName)))
                .get(environment);
        webBrowserDispatchers.add(webBrowserDispatcher);
        this.activeWebBrowserDispatcher = webBrowserDispatcher;
        return webBrowserDispatcher;
    }

    public WebBrowserDispatcher createDispatcher(String webBrowserConfigurationName, String webBrowserDispatcherName) {
        WebBrowserDispatcher webBrowserDispatcher = Optional.ofNullable(configuration.getWebBrowserConfigurations().get(webBrowserConfigurationName))
                .orElseThrow(() -> new WebBrowserServiceException(WEB_BROWSER_CONFIGURATION_NOT_DECLARED.getMessage(webBrowserConfigurationName)))
                .get(environment);
        webBrowserDispatchersByName.put(webBrowserDispatcherName, webBrowserDispatcher);
        this.activeWebBrowserDispatcher = webBrowserDispatcher;
        return webBrowserDispatcher;
    }

    public WebBrowserDispatcher getActiveDispatcher() {
        return Optional.ofNullable(activeWebBrowserDispatcher)
                .orElseThrow(() -> new WebBrowserServiceException(NO_ACTIVE_WEB_BROWSER_DISPATCHER_FOUND.getMessage()));
    }

    public WebBrowserDispatcher setActiveDispatcher(String webBrowserDispatcherName) {
        activeWebBrowserDispatcher = getDispatcherByName(webBrowserDispatcherName);
        return this.activeWebBrowserDispatcher;
    }

    public WebBrowserDispatcher getDispatcherByName(String webBrowserDispatcherName) {
        return Optional.ofNullable(webBrowserDispatchersByName.get(webBrowserDispatcherName))
                .orElseThrow(() -> new WebBrowserServiceException(NO_WEB_BROWSER_DISPATCHER_WITH_NAME_CREATED.getMessage(webBrowserDispatcherName)));
    }

    public WebBrowserService closeAll() {
        webBrowserDispatchersByName.forEach((name, webBrowserDispatcher) -> {
            webBrowserDispatcher.close();
        });
        webBrowserDispatchers.forEach(WebBrowserDispatcher::close);
        return this;
    }

    public ExceptionMapper getExceptionMapper(Class<? extends ExceptionMapper> exceptionMapperClass) {
        return Optional.ofNullable(this.configuration.getExceptionMappers().get(exceptionMapperClass))
                .orElseThrow(() -> new WebBrowserServiceException(NO_EXCEPTION_MAPPER_BY_CLASS_DECLARED
                        .getMessage(exceptionMapperClass.getCanonicalName())));
    }

    protected WebBrowserServiceConfiguration validate(ServiceConfiguration configuration) {
        if (configuration == null) {
            throw new WebBrowserServiceException(WEB_BROWSER_SERVICE_CONFIGURATION_NOT_DECLARED.getMessage());
        }
        if (configuration instanceof WebBrowserServiceConfiguration) {
            return (WebBrowserServiceConfiguration) configuration;
        }
        throw new IncorrectServiceConfigurationException(
                CHECK_CONFIGURATION_NOT_VALID.getMessage(configuration.getClass().getCanonicalName(), this.getClass().getCanonicalName()));
    }

}

