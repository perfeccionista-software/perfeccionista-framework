package io.perfeccionista.framework.pagefactory;

import io.perfeccionista.framework.pagefactory.elements.AbstractWebBlock;
import io.perfeccionista.framework.pagefactory.elements.AbstractWebChildElement;
import io.perfeccionista.framework.pagefactory.elements.AbstractWebMappedBlock;
import io.perfeccionista.framework.pagefactory.elements.AbstractWebPage;
import io.perfeccionista.framework.pagefactory.elements.WebImage;
import io.perfeccionista.framework.pagefactory.elements.WebImageSeleniumImpl;
import io.perfeccionista.framework.pagefactory.elements.WebLink;
import io.perfeccionista.framework.pagefactory.elements.WebLinkSeleniumImpl;
import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.elements.WebListSeleniumImpl;
import io.perfeccionista.framework.pagefactory.elements.WebTextBlock;
import io.perfeccionista.framework.pagefactory.elements.WebTextBlockSeleniumImpl;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;

import java.util.HashMap;
import java.util.Map;

public class DefaultSeleniumWebElementsConfiguration implements WebElementsConfiguration {

    @Override
    public Class<? extends AbstractWebPage> getWebPageImplementation() {
        return AbstractWebPage.class;
    }

    @Override
    public Class<? extends AbstractWebBlock> getWebBlockImplementation() {
        return AbstractWebBlock.class;
    }

    @Override
    public Class<? extends AbstractWebMappedBlock> getWebMappedBlockImplementation() {
        return AbstractWebMappedBlock.class;
    }

    @Override
    public Map<Class<? extends WebChildElement>, Class<? extends AbstractWebChildElement>> getElementImplementations() {
        Map<Class<? extends WebChildElement>, Class<? extends AbstractWebChildElement>> elementsConfiguration = new HashMap<>();

        elementsConfiguration.put(WebTextBlock.class, WebTextBlockSeleniumImpl.class);
        elementsConfiguration.put(WebList.class, WebListSeleniumImpl.class);
        elementsConfiguration.put(WebImage.class, WebImageSeleniumImpl.class);
        elementsConfiguration.put(WebLink.class, WebLinkSeleniumImpl.class);

        return elementsConfiguration;
    }
}
