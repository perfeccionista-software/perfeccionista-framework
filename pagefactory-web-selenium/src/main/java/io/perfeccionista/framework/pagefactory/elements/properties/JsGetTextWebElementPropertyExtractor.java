package io.perfeccionista.framework.pagefactory.elements.properties;

import io.perfeccionista.framework.pagefactory.elements.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorChain;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorHolder;
import io.perfeccionista.framework.pagefactory.jsfunction.GetText;
import io.perfeccionista.framework.pagefactory.operation.JsOperation;
import org.junit.platform.commons.util.ReflectionUtils;

public class JsGetTextWebElementPropertyExtractor implements WebElementPropertyExtractor<WebChildElement> {

    @Override
    public String extract(WebChildElement element, WebLocatorHolder locatorHolder) {
        WebLocatorChain locatorChain = element.getLocatorChain().addLocator(locatorHolder);
        GetText getTextFunction = ReflectionUtils.newInstance(GetText.class);
        JsOperation<String> operation = JsOperation.of(locatorChain, getTextFunction);
        return element.getWebBrowserDispatcher().executor().executeOperation(operation)
                .singleResult()
                .get();
    }

}
