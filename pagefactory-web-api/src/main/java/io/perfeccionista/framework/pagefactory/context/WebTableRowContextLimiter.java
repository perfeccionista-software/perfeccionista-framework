package io.perfeccionista.framework.pagefactory.context;

import io.perfeccionista.framework.exceptions.SearchContext;
import io.perfeccionista.framework.exceptions.attachments.ValueAttachmentEntry;
import io.perfeccionista.framework.pagefactory.context.base.WebContextLimiter;
import io.perfeccionista.framework.pagefactory.elements.WebTableRow;
import io.perfeccionista.framework.pagefactory.elements.base.WebParentElement;
import io.perfeccionista.framework.pagefactory.elements.WebTable;
import io.perfeccionista.framework.pagefactory.filter.table.WebTableFilterBuilder;
import io.perfeccionista.framework.value.number.NumberValue;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Stream;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.CONTEXT_LIMITER_RESULT_SIZE_NOT_MATCH;
import static io.perfeccionista.framework.pagefactory.extractor.WebExtractors.row;

public class WebTableRowContextLimiter implements WebContextLimiter<WebTableRow> {

    private final String elementPath;
    private final WebTableFilterBuilder filterBuilder;
    private final NumberValue<Integer> expectedSize;

    private WebTableRowContextLimiter(String elementPath,
                                      WebTableFilterBuilder filterBuilder,
                                      NumberValue<Integer> expectedSize) {
        this.elementPath = elementPath;
        this.filterBuilder = filterBuilder;
        this.expectedSize = expectedSize;
    }

    public static WebTableRowContextLimiter of(@NotNull String elementPath,
                                               @NotNull WebTableFilterBuilder filterBuilder,
                                               @NotNull NumberValue<Integer> expectedSize) {
        return new WebTableRowContextLimiter(elementPath, filterBuilder, expectedSize);
    }

    @Override
    public @NotNull Stream<WebTableRow> getContexts(@NotNull Stream<WebParentElement> parentElements) {
        Collection<WebTableRow> webBlocks = parentElements
                .map(parentElement -> parentElement
                        .getElementRegistry()
                        .getRequiredElementByPath(elementPath, WebTable.class)
                        .filter(filterBuilder)
                        .extractRows(row())
                        .getValues().values())
                .reduce(new ArrayList<>(), (webBlocks1, webBlocks2) -> {
                    webBlocks1.addAll(webBlocks2);
                    return webBlocks1;
                });
        if (!expectedSize.check(webBlocks.size())) {
            throw SearchContext.exception(CONTEXT_LIMITER_RESULT_SIZE_NOT_MATCH.getMessage())
                    .addLastAttachmentEntry(ValueAttachmentEntry.of(expectedSize));
        }
        return webBlocks.stream();
    }

}