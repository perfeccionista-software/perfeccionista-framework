package io.perfeccionista.framework.pagefactory.dispatcher;

import com.google.common.collect.ImmutableMap;
import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.exceptions.SeleniumWebDriverInstantiation;
import io.perfeccionista.framework.pagefactory.dispatcher.type.RemoteType;
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

    protected URL remoteUrl;

    public RemoteWebBrowserSeleniumDispatcher(Environment environment, String remoteUrl) {
        super(environment, new RemoteType());
        try {
            this.remoteUrl = new URL(remoteUrl);
        } catch (MalformedURLException e) {
            throw SeleniumWebDriverInstantiation.exception(INCORRECT_REMOTE_WEB_DRIVER_INSTANCE_URL.getMessage(remoteUrl), e);
        }
    }

    public RemoteWebBrowserSeleniumDispatcher(Environment environment, URL remoteUrl) {
        super(environment, new RemoteType());
        this.remoteUrl = remoteUrl;
    }

    @Override
    public WebBrowserDispatcher launch() {
        long connectionTimeout = timeouts.getSessionTimeout();
        long socketTimeout = timeouts.getSessionTimeout() + 30_000;
        HttpClient.Factory factory = new RemoteWebDriverConnectionFactory()
                .connectionTimeout(Duration.ofSeconds(connectionTimeout))
                .socketTimeout(Duration.ofSeconds(socketTimeout));
        HttpCommandExecutor httpCommandExecutor = new HttpCommandExecutor(ImmutableMap.of(), remoteUrl, factory);
        this.instance = new RemoteWebDriver(httpCommandExecutor, this.options);
        this.instance.setFileDetector(new LocalFileDetector());
        setTimeouts();
        return this;
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
