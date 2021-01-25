package io.perfeccionista.framework.pagefactory.limiter;

import io.perfeccionista.framework.exceptions.SearchContextSizeMismatch;
import io.perfeccionista.framework.exceptions.attachments.ValueAttachmentEntry;
import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.elements.base.WebParentElement;
import io.perfeccionista.framework.pagefactory.filter.list.WebListFilterBuilder;
import io.perfeccionista.framework.value.number.NumberValue;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Stream;

import static io.perfeccionista.framework.Web.block;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.SEARCH_CONTEXT_EXPECTED_SIZE_DOES_NOT_MATCH;

public class WebListBlockContextLimiter<T extends WebBlock> implements WebContextLimiter<T> {

    private final String elementPath;
    private final WebList elementFrame;
    private final WebListFilterBuilder filterBuilder;
    private final NumberValue<Integer> expectedSize;

    private WebListBlockContextLimiter(String elementPath,
                                       WebList elementFrame,
                                       WebListFilterBuilder filterBuilder,
                                       NumberValue<Integer> expectedSize) {
        this.elementPath = elementPath;
        this.elementFrame = elementFrame;
        this.filterBuilder = filterBuilder;
        this.expectedSize = expectedSize;
    }

    public static <T extends WebBlock> WebListBlockContextLimiter<T> of(@NotNull WebList elementFrame,
                                                                        @NotNull WebListFilterBuilder filterBuilder,
                                                                        @NotNull NumberValue<Integer> expectedSize) {
        return new WebListBlockContextLimiter<>(null, elementFrame, filterBuilder, expectedSize);
    }

    public static <T extends WebBlock> WebListBlockContextLimiter<T> of(@NotNull String elementPath,
                                                                        @NotNull WebListFilterBuilder filterBuilder,
                                                                        @NotNull NumberValue<Integer> expectedSize) {
        return new WebListBlockContextLimiter<>(elementPath, null, filterBuilder, expectedSize);
    }

    @Override
    public @NotNull Stream<T> getContexts(@NotNull Stream<WebParentElement> parentElements) {
        Collection<T> webBlocks = parentElements.map(parentElement -> {
            WebList webList;
            if (Objects.nonNull(elementFrame)) {
                webList = parentElement
                        .getElementRegistry()
                        .getRequiredElementByMethod(elementFrame.getElementIdentifier().getElementMethod(), WebList.class);
            } else {
                webList = parentElement
                        .getElementRegistry()
                        .getRequiredElementByPath(elementPath, WebList.class);
            }
            return (Collection<T>) webList.filter(filterBuilder)
                    .extractAll(block())
                    .getResults().values();
        }).reduce(new ArrayList<>(), (webBlocks1, webBlocks2) -> {
            webBlocks1.addAll(webBlocks2);
            return webBlocks1;
        });
        if (!expectedSize.check(webBlocks.size())) {
            throw SearchContextSizeMismatch.exception(SEARCH_CONTEXT_EXPECTED_SIZE_DOES_NOT_MATCH.getMessage())
                    .addLastAttachmentEntry(ValueAttachmentEntry.of(expectedSize));
        }
        return webBlocks.stream();
    }

}
