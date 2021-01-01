package io.perfeccionista.framework.pagefactory.browser.dispatcher;

import com.google.common.collect.ImmutableMap;
import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.exceptions.SeleniumWebDriverInstantiation;
import io.perfeccionista.framework.pagefactory.browser.WebBrowserDispatcher;
import io.perfeccionista.framework.pagefactory.browser.type.RemoteType;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.HttpCommandExecutor;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.http.HttpClient;
import org.openqa.selenium.remote.internal.OkHttpClient;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebSeleniumMessages.INCORRECT_REMOTE_WEB_DRIVER_INSTANCE_URL;

// TODO: Добавить в жавадоки описание этих капабилитис
public class RemoteWebBrowserSeleniumDispatcher extends AbstractWebBrowserSeleniumDispatcher<RemoteWebDriver, MutableCapabilities> {
    protected static final String CONNECTION_TIMEOUT = "perfeccionista.selenium.connectionTimeout.milliseconds";
    protected static final String SOCKET_TIMEOUT = "perfeccionista.selenium.socketTimeout.milliseconds";

    protected String remoteUrl;

    public RemoteWebBrowserSeleniumDispatcher(Environment environment, String remoteUrl) {
        super(environment, new RemoteType());
        this.remoteUrl = remoteUrl;
    }

    @Override
    public WebBrowserDispatcher launch() {
        try {
            HttpClient.Factory factory = new RemoteWebDriverConnectionFactory()
                    .connectionTimeout(Duration.ofSeconds(getIntCapability(CONNECTION_TIMEOUT, 120_000)))
                    .socketTimeout(Duration.ofSeconds(getIntCapability(SOCKET_TIMEOUT, 180_000)));
            HttpCommandExecutor httpCommandExecutor = new HttpCommandExecutor(ImmutableMap.of(), new URL(remoteUrl), factory);
            this.instance = new RemoteWebDriver(httpCommandExecutor, this.options);
            this.instance.setFileDetector(new LocalFileDetector());
        } catch (MalformedURLException e) {
            throw SeleniumWebDriverInstantiation.exception(INCORRECT_REMOTE_WEB_DRIVER_INSTANCE_URL.getMessage(remoteUrl), e);
        }
        setTimeouts();
        return this;
    }

    private int getIntCapability(String capabilityName, int defaultValue) {
        Object capability = this.options.getCapability(capabilityName);
        if (capability instanceof String) {
            try{
                return Integer.parseInt(String.valueOf(capability));
            } catch (Exception e) {
                // Incorrect capability value. Use default value
            }
        }
        return defaultValue;
    }

    static class RemoteWebDriverConnectionFactory extends OkHttpClient.Factory {

        private Duration connectionTimeout;
        private Duration socketTimeout;

        RemoteWebDriverConnectionFactory connectionTimeout(Duration connectionTimeout) {
            this.connectionTimeout = connectionTimeout;
            return this;
        }

        RemoteWebDriverConnectionFactory socketTimeout(Duration socketTimeout) {
            this.socketTimeout = socketTimeout;
            return this;
        }

        @Override
        public HttpClient.Builder builder() {
            return super.builder()
                    .connectionTimeout(connectionTimeout)
                    .readTimeout(socketTimeout);
        }

    }

}

