package io.perfeccionista.framework.pagefactory.dispatcher;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.exceptions.IncorrectServiceConfiguration;
import io.perfeccionista.framework.exceptions.WebBrowserConfigurationNotFound;
import io.perfeccionista.framework.exceptions.WebBrowserDispatcherNotStarted;
import io.perfeccionista.framework.exceptions.ExceptionMapperNotFound;
import io.perfeccionista.framework.exceptions.mapper.WebExceptionMapper;
import io.perfeccionista.framework.service.Service;
import io.perfeccionista.framework.service.ServiceConfiguration;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import static io.perfeccionista.framework.exceptions.messages.EnvironmentMessages.SERVICE_CONFIGURATION_NOT_VALID;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.NO_ACTIVE_WEB_BROWSER_DISPATCHER_FOUND;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.NO_ACTIVE_WEB_BROWSER_DISPATCHER_WITH_NAME_FOUND;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.WEB_BROWSER_CONFIGURATION_NOT_FOUND;
import static io.perfeccionista.framework.exceptions.messages.UtilsMessages.EXCEPTION_MAPPER_FOR_CLASS_NOT_FOUND;

public class WebBrowserService implements Service {

    protected Environment environment;
    protected WebBrowserServiceConfiguration configuration;

    protected Set<WebBrowserDispatcher> webBrowserDispatchers = new HashSet<>();
    protected Map<String, WebBrowserDispatcher> webBrowserDispatchersByName = new HashMap<>();
    protected WebBrowserDispatcher activeWebBrowserDispatcher = null;

    @Override
    public void init(@NotNull Environment environment, @NotNull ServiceConfiguration configuration) {
        this.environment = environment;
        this.configuration = validate(configuration);
    }

    @Override
    public void afterTest() {
        if (configuration.isCloseWebBrowsersAfterTest()) {
            closeAll();
        }
    }

    public @NotNull WebBrowserDispatcher createDispatcher(@NotNull String webBrowserConfigurationName) {
        WebBrowserDispatcher webBrowserDispatcher = Optional.ofNullable(configuration.getWebBrowserConfigurations().get(webBrowserConfigurationName))
                .orElseThrow(() -> WebBrowserConfigurationNotFound.exception(WEB_BROWSER_CONFIGURATION_NOT_FOUND.getMessage(webBrowserConfigurationName)))
                .get();
        webBrowserDispatchers.add(webBrowserDispatcher);
        this.activeWebBrowserDispatcher = webBrowserDispatcher;
        return webBrowserDispatcher;
    }

    public @NotNull WebBrowserDispatcher createDispatcher(@NotNull String webBrowserConfigurationName, @NotNull String webBrowserDispatcherName) {
        WebBrowserDispatcher webBrowserDispatcher = Optional.ofNullable(configuration.getWebBrowserConfigurations().get(webBrowserConfigurationName))
                .orElseThrow(() -> WebBrowserConfigurationNotFound.exception(WEB_BROWSER_CONFIGURATION_NOT_FOUND.getMessage(webBrowserConfigurationName)))
                .get();
        webBrowserDispatchersByName.put(webBrowserDispatcherName, webBrowserDispatcher);
        this.activeWebBrowserDispatcher = webBrowserDispatcher;
        return webBrowserDispatcher;
    }

    public boolean isActiveDispatcherRunning() {
        return Objects.nonNull(activeWebBrowserDispatcher);
    }

    public @NotNull WebBrowserDispatcher getActiveDispatcher() {
        return Optional.ofNullable(activeWebBrowserDispatcher)
                .orElseThrow(() -> WebBrowserDispatcherNotStarted.exception(NO_ACTIVE_WEB_BROWSER_DISPATCHER_FOUND.getMessage()));
    }

    public @NotNull WebBrowserDispatcher setActiveDispatcher(@NotNull String webBrowserDispatcherName) {
        activeWebBrowserDispatcher = getDispatcherByName(webBrowserDispatcherName);
        return this.activeWebBrowserDispatcher;
    }

    public @NotNull WebBrowserDispatcher getDispatcherByName(@NotNull String webBrowserDispatcherName) {
        return Optional.ofNullable(webBrowserDispatchersByName.get(webBrowserDispatcherName))
                .orElseThrow(() -> WebBrowserDispatcherNotStarted.exception(NO_ACTIVE_WEB_BROWSER_DISPATCHER_WITH_NAME_FOUND.getMessage(webBrowserDispatcherName)));
    }

    public WebBrowserService closeAll() {
        webBrowserDispatchersByName.forEach((name, webBrowserDispatcher) -> webBrowserDispatcher.close());
        webBrowserDispatchers.forEach(WebBrowserDispatcher::close);
        this.activeWebBrowserDispatcher = null;
        return this;
    }

    public @NotNull WebExceptionMapper getExceptionMapper(@NotNull Class<? extends WebExceptionMapper> exceptionMapperClass) {
        return Optional.ofNullable(this.configuration.getExceptionMappers().get(exceptionMapperClass))
                .orElseThrow(() -> ExceptionMapperNotFound
                        .exception(EXCEPTION_MAPPER_FOR_CLASS_NOT_FOUND.getMessage(exceptionMapperClass.getCanonicalName())));
    }

    protected WebBrowserServiceConfiguration validate(ServiceConfiguration configuration) {
        if (configuration instanceof WebBrowserServiceConfiguration) {
            return (WebBrowserServiceConfiguration) configuration;
        }
        throw IncorrectServiceConfiguration
                .exception(SERVICE_CONFIGURATION_NOT_VALID.getMessage(configuration.getClass().getCanonicalName(), this.getClass().getCanonicalName()));
    }

}

