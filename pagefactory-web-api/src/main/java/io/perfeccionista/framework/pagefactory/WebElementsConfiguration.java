package io.perfeccionista.framework.pagefactory;

import io.perfeccionista.framework.pagefactory.js.JsFunction;

import java.util.Map;

public interface WebElementsConfiguration extends ElementsConfiguration {

    Map<Class<? extends JsFunction<?, ?>>, String> jsFunctionScripts();

}
