package io.perfeccionista.framework.pagefactory.browser.configuration;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.pagefactory.browser.WebBrowserDispatcher;

/**
 * Для инициализации экземпляры этого класса должны иметь конструктор без параметров
 */
public interface WebBrowserConfiguration {

    WebBrowserDispatcher get(Environment environment);

}
