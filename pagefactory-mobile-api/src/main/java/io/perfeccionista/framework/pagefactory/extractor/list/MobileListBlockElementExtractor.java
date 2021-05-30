package io.perfeccionista.framework.pagefactory.extractor.list;

import io.perfeccionista.framework.pagefactory.MobilePageService;
import io.perfeccionista.framework.pagefactory.elements.MobileList;
import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElement;
import io.perfeccionista.framework.pagefactory.factory.MobilePageFactory;
import io.perfeccionista.framework.pagefactory.filter.FilterResult;
import io.perfeccionista.framework.pagefactory.filter.list.MobileListFilter;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class MobileListBlockElementExtractor<T extends MobileChildElement> implements MobileListBlockValueExtractor<T> {

    private final String elementPath;
    private final T elementFrame;
    private final Class<T> returnType;

    public MobileListBlockElementExtractor(@NotNull String elementPath, @NotNull Class<T> returnType) {
        this.elementPath = elementPath;
        this.elementFrame = null;
        this.returnType = returnType;
    }

    public MobileListBlockElementExtractor(@NotNull T elementFrame) {
        this.elementPath = null;
        this.elementFrame = elementFrame;
        this.returnType = null;
    }

    @Override
    public Map<Integer, T> extractValues(@NotNull MobileListFilter filter) {
        FilterResult filterResult = filter.getFilterResult();
        MobileList element = filter.getElement();

        Map<Integer, T> extractedElements = new HashMap<>();

        MobilePageFactory webPageFactory = element.getEnvironment()
                .getService(MobilePageService.class)
                .getMobilePageFactory();

        webPageFactory.createMobileListBlocks(element, filterResult)
                .forEach((index, webMappedBlock) -> {
                    T elementToExtract;
                    if (elementPath != null) {
                        elementToExtract = webMappedBlock.getElementRegistry()
                                .getRequiredElementByPath(elementPath, returnType);
                    } else {
                        //noinspection unchecked
                        elementToExtract = (T) webMappedBlock.getElementRegistry()
                                .getRequiredElementByMethod(elementFrame.getElementIdentifier().getElementMethod());
                    }
                    extractedElements.put(index, elementToExtract);
                });
        return extractedElements;
    }

}

