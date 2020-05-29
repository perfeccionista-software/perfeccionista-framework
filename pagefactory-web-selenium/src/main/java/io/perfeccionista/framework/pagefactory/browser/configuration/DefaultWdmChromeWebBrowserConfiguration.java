package io.perfeccionista.framework.pagefactory.browser.configuration;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.pagefactory.browser.WebBrowserDispatcher;
import io.perfeccionista.framework.pagefactory.browser.dispatcher.WdmWebBrowserSeleniumDispatcher;
import io.perfeccionista.framework.pagefactory.browser.type.ChromeType;

public class DefaultWdmChromeWebBrowserConfiguration implements WebBrowserConfiguration {

    @Override
    public WebBrowserDispatcher get(Environment environment) {
        return new WdmWebBrowserSeleniumDispatcher<>(environment, new ChromeType());
    }

}
