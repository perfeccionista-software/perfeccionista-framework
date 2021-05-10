package io.perfeccionista.framework.pagefactory.extractor.list;

import io.perfeccionista.framework.pagefactory.MobilePageService;
import io.perfeccionista.framework.pagefactory.elements.MobileBlock;
import io.perfeccionista.framework.pagefactory.elements.MobileList;
import io.perfeccionista.framework.pagefactory.factory.MobilePageFactory;
import io.perfeccionista.framework.pagefactory.filter.FilterResult;
import io.perfeccionista.framework.pagefactory.filter.list.MobileListFilter;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class MobileListBlockExtractor<T extends MobileBlock> implements MobileListBlockValueExtractor<T> {

    private final Class<T> blockClass;

    public MobileListBlockExtractor(@NotNull Class<T> blockClass) {
        this.blockClass = blockClass;
    }

    @Override
    public Map<Integer, T> extractValues(@NotNull MobileListFilter filter) {
        FilterResult filterResult = filter.getFilterResult();
        MobileList element = filter.getElement();

        MobilePageFactory mobilePageFactory = element.getEnvironment()
                .getService(MobilePageService.class)
                .getMobilePageFactory();

        return mobilePageFactory.createMobileListBlocks(element, filterResult)
                .entrySet().stream()
                .collect(Collectors.toMap(Entry::getKey, entry -> (T) entry.getValue()));
    }

}

