package io.perfeccionista.framework.pagefactory.pageobjects.extractors;

import io.perfeccionista.framework.pagefactory.elements.base.WebChildElementBase;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorChain;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorHolder;
import io.perfeccionista.framework.pagefactory.elements.properties.base.WebElementPropertyExtractor;
import io.perfeccionista.framework.pagefactory.jsfunction.GetAttribute;
import io.perfeccionista.framework.pagefactory.operation.JsOperation;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class AltAttributeExtractor implements WebElementPropertyExtractor {

    @Override
    public JsOperation<String> getJsOperation(@NotNull WebChildElementBase element, Optional<WebLocatorHolder> locatorHolder) {
        WebLocatorChain locatorChain = element.getLocatorChain();
        locatorHolder.ifPresent(locatorChain::addLastLocator);
        return JsOperation.of(locatorChain, new GetAttribute("alt"));
    }

}
