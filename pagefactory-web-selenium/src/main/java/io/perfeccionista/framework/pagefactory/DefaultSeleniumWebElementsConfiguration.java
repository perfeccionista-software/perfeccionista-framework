package io.perfeccionista.framework.pagefactory;

import io.perfeccionista.framework.pagefactory.elements.AbstractWebBlock;
import io.perfeccionista.framework.pagefactory.elements.AbstractWebChildElement;
import io.perfeccionista.framework.pagefactory.elements.AbstractWebMappedBlock;
import io.perfeccionista.framework.pagefactory.elements.AbstractWebPage;
import io.perfeccionista.framework.pagefactory.elements.WebButton;
import io.perfeccionista.framework.pagefactory.elements.WebButtonSeleniumImpl;
import io.perfeccionista.framework.pagefactory.elements.WebCheckbox;
import io.perfeccionista.framework.pagefactory.elements.WebCheckboxSeleniumImpl;
import io.perfeccionista.framework.pagefactory.elements.WebFileInput;
import io.perfeccionista.framework.pagefactory.elements.WebFileInputSeleniumImpl;
import io.perfeccionista.framework.pagefactory.elements.WebImage;
import io.perfeccionista.framework.pagefactory.elements.WebImageSeleniumImpl;
import io.perfeccionista.framework.pagefactory.elements.WebLink;
import io.perfeccionista.framework.pagefactory.elements.WebLinkSeleniumImpl;
import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.elements.WebListSeleniumImpl;
import io.perfeccionista.framework.pagefactory.elements.WebRadioButton;
import io.perfeccionista.framework.pagefactory.elements.WebRadioButtonSeleniumImpl;
import io.perfeccionista.framework.pagefactory.elements.WebRadioGroup;
import io.perfeccionista.framework.pagefactory.elements.WebRadioGroupSeleniumImpl;
import io.perfeccionista.framework.pagefactory.elements.WebTable;
import io.perfeccionista.framework.pagefactory.elements.WebTableSeleniumImpl;
import io.perfeccionista.framework.pagefactory.elements.WebTextBlock;
import io.perfeccionista.framework.pagefactory.elements.WebTextBlockSeleniumImpl;
import io.perfeccionista.framework.pagefactory.elements.WebTextInput;
import io.perfeccionista.framework.pagefactory.elements.WebTextInputSeleniumImpl;
import io.perfeccionista.framework.pagefactory.elements.WebTextList;
import io.perfeccionista.framework.pagefactory.elements.WebTextListSeleniumImpl;
import io.perfeccionista.framework.pagefactory.elements.WebTextTable;
import io.perfeccionista.framework.pagefactory.elements.WebTextTableSeleniumImpl;
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

        elementsConfiguration.put(WebButton.class, WebButtonSeleniumImpl.class);
        elementsConfiguration.put(WebCheckbox.class, WebCheckboxSeleniumImpl.class);
        elementsConfiguration.put(WebImage.class, WebImageSeleniumImpl.class);
        elementsConfiguration.put(WebFileInput.class, WebFileInputSeleniumImpl.class);

        elementsConfiguration.put(WebLink.class, WebLinkSeleniumImpl.class);
        elementsConfiguration.put(WebList.class, WebListSeleniumImpl.class);
        elementsConfiguration.put(WebRadioButton.class, WebRadioButtonSeleniumImpl.class);
        elementsConfiguration.put(WebRadioGroup.class, WebRadioGroupSeleniumImpl.class);
        elementsConfiguration.put(WebTable.class, WebTableSeleniumImpl.class);
        elementsConfiguration.put(WebTextBlock.class, WebTextBlockSeleniumImpl.class);
        elementsConfiguration.put(WebTextInput.class, WebTextInputSeleniumImpl.class);
        elementsConfiguration.put(WebTextList.class, WebTextListSeleniumImpl.class);
        elementsConfiguration.put(WebTextTable.class, WebTextTableSeleniumImpl.class);

        return elementsConfiguration;
    }
}
