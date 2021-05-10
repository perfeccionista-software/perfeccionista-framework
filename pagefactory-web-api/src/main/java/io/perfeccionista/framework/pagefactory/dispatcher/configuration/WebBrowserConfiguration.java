package io.perfeccionista.framework.pagefactory.dispatcher.configuration;

import io.perfeccionista.framework.pagefactory.dispatcher.WebBrowserDispatcher;

/**
 * Для инициализации экземпляры этого класса должны иметь конструктор без параметров
 */
public interface WebBrowserConfiguration {

    WebBrowserDispatcher get();

}
