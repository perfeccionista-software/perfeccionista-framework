package io.perfeccionista.framework.pagefactory.dispatcher.configuration.selenium;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.pagefactory.dispatcher.WebBrowserDispatcher;
import io.perfeccionista.framework.pagefactory.dispatcher.configuration.WebBrowserConfiguration;
import io.perfeccionista.framework.pagefactory.dispatcher.WdmWebBrowserSeleniumDispatcher;
import io.perfeccionista.framework.pagefactory.dispatcher.type.EdgeType;

// Для корректной работы необходимо вручную скопировать файл библиотеки libc++.dylib в папку с драйвером
public class EdgeWdm implements WebBrowserConfiguration {

    @Override
    public WebBrowserDispatcher get() {
        return new WdmWebBrowserSeleniumDispatcher<>(Environment.getCurrent(), new EdgeType());
    }

}

