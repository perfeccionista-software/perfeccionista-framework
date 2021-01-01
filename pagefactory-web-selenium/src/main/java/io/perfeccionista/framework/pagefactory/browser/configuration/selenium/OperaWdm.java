package io.perfeccionista.framework.pagefactory.browser.configuration.selenium;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.pagefactory.browser.WebBrowserDispatcher;
import io.perfeccionista.framework.pagefactory.browser.configuration.WebBrowserConfiguration;
import io.perfeccionista.framework.pagefactory.browser.dispatcher.WdmWebBrowserSeleniumDispatcher;
import io.perfeccionista.framework.pagefactory.browser.type.OperaType;

public class OperaWdm implements WebBrowserConfiguration {

    @Override
    public WebBrowserDispatcher get() {
        return new WdmWebBrowserSeleniumDispatcher<>(Environment.getCurrent(), new OperaType());
    }

}

