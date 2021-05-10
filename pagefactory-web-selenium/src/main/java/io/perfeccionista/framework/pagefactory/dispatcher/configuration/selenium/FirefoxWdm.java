package io.perfeccionista.framework.pagefactory.dispatcher.configuration.selenium;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.pagefactory.dispatcher.WebBrowserDispatcher;
import io.perfeccionista.framework.pagefactory.dispatcher.configuration.WebBrowserConfiguration;
import io.perfeccionista.framework.pagefactory.dispatcher.WdmWebBrowserSeleniumDispatcher;
import io.perfeccionista.framework.pagefactory.dispatcher.type.FirefoxType;

public class FirefoxWdm implements WebBrowserConfiguration {

    @Override
    public WebBrowserDispatcher get() {
        return new WdmWebBrowserSeleniumDispatcher<>(Environment.getCurrent(), new FirefoxType());
    }

}

