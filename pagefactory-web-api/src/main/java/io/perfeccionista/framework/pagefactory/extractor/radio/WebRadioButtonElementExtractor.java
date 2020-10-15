package io.perfeccionista.framework.pagefactory.extractor.radio;

import io.perfeccionista.framework.pagefactory.WebPageService;
import io.perfeccionista.framework.pagefactory.elements.WebRadioButton;
import io.perfeccionista.framework.pagefactory.elements.WebRadioGroup;
import io.perfeccionista.framework.pagefactory.factory.WebPageFactory;
import io.perfeccionista.framework.pagefactory.filter.WebFilterResult;
import io.perfeccionista.framework.pagefactory.filter.radio.WebRadioGroupFilter;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class WebRadioButtonElementExtractor implements WebRadioButtonValueExtractor<WebRadioButton> {

    @Override
    public Map<Integer, WebRadioButton> extractValues(@NotNull WebRadioGroupFilter filter) {
        WebFilterResult filterResult = filter.getFilterResult();
        WebRadioGroup element = filter.getElement();

        WebPageFactory webPageFactory = element.getEnvironment()
                .getService(WebPageService.class)
                .getWebPageFactory();

        return webPageFactory.createWebRadioButtons(element, filterResult);
    }

}