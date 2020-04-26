package io.perfeccionista.framework.pagefactory.elements.properties;

import io.perfeccionista.framework.pagefactory.elements.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.locators.LocatorChain;
import io.perfeccionista.framework.pagefactory.elements.locators.LocatorHolder;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;
import io.perfeccionista.framework.pagefactory.js.GetText;
import io.perfeccionista.framework.pagefactory.operations.JsOperation;
import org.junit.platform.commons.util.ReflectionUtils;

public class JsGetTextElementPropertyExtractor implements ElementPropertyExtractor<WebChildElement> {

    @Override
    public String extract(WebChildElement element, LocatorHolder locatorHolder) {
        LocatorChain locatorChain = element.getLocatorChain().addLocator(locatorHolder);
        GetText getTextFunction = ReflectionUtils.newInstance(GetText.class);
        JsOperation<SingleResult<String>> operation = JsOperation.single(locatorChain, getTextFunction);
        return element.getWebBrowserDispatcher().getDriverOperationExecutor().executeOperation(operation).get();
    }

}
