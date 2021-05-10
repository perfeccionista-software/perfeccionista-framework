package io.perfeccionista.framework.pagefactory.limiter;

import io.perfeccionista.framework.exceptions.SearchContextSizeMismatch;
import io.perfeccionista.framework.exceptions.attachments.ValueAttachmentEntry;
import io.perfeccionista.framework.pagefactory.elements.MobileBlock;
import io.perfeccionista.framework.pagefactory.elements.MobileList;
import io.perfeccionista.framework.pagefactory.elements.base.MobileParentElement;
import io.perfeccionista.framework.pagefactory.filter.list.MobileListFilterBuilder;
import io.perfeccionista.framework.value.number.NumberValue;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Stream;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.SEARCH_CONTEXT_EXPECTED_SIZE_DOES_NOT_MATCH;
import static io.perfeccionista.framework.pagefactory.extractor.MobileExtractors.block;

public class MobileListBlockContextLimiter<T extends MobileBlock> implements MobileContextLimiter<T> {

    private final String elementPath;
    private final MobileList elementFrame;
    private final MobileListFilterBuilder filterBuilder;
    private final NumberValue<Integer> expectedSize;

    private MobileListBlockContextLimiter(String elementPath,
                                          MobileList elementFrame,
                                          MobileListFilterBuilder filterBuilder,
                                          NumberValue<Integer> expectedSize) {
        this.elementPath = elementPath;
        this.elementFrame = elementFrame;
        this.filterBuilder = filterBuilder;
        this.expectedSize = expectedSize;
    }

    public static <T extends MobileBlock> MobileListBlockContextLimiter<T> of(@NotNull MobileList elementFrame,
                                                                              @NotNull MobileListFilterBuilder filterBuilder,
                                                                              @NotNull NumberValue<Integer> expectedSize) {
        return new MobileListBlockContextLimiter<>(null, elementFrame, filterBuilder, expectedSize);
    }

    public static <T extends MobileBlock> MobileListBlockContextLimiter<T> of(@NotNull String elementPath,
                                                                              @NotNull MobileListFilterBuilder filterBuilder,
                                                                              @NotNull NumberValue<Integer> expectedSize) {
        return new MobileListBlockContextLimiter<>(elementPath, null, filterBuilder, expectedSize);
    }

    @Override
    public @NotNull Stream<T> getContexts(@NotNull Stream<MobileParentElement> parentElements) {
        Collection<T> mobileBlocks = parentElements
                .map(parentElement -> {
                    MobileList mobileList;
                    if (Objects.nonNull(elementFrame)) {
                        mobileList = parentElement
                                .getElementRegistry()
                                .getRequiredElementByMethod(elementFrame.getElementIdentifier().getElementMethod(), MobileList.class);
                    } else {
                        mobileList = parentElement
                                .getElementRegistry()
                                .getRequiredElementByPath(elementPath, MobileList.class);
                    }
                    return (Collection<T>) mobileList.filter(filterBuilder)
                            .extractAll(block())
                            .getResults().values();
                }).reduce(new ArrayList<>(), (mobileBlocks1, mobileBlocks2) -> {
                    mobileBlocks1.addAll(mobileBlocks2);
                    return mobileBlocks1;
                });
        if (!expectedSize.check(mobileBlocks.size())) {
            throw SearchContextSizeMismatch.exception(SEARCH_CONTEXT_EXPECTED_SIZE_DOES_NOT_MATCH.getMessage())
                    .addLastAttachmentEntry(ValueAttachmentEntry.of(expectedSize));
        }
        return mobileBlocks.stream();
    }

}
