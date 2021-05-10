package io.perfeccionista.framework.pagefactory.limiter;

import io.perfeccionista.framework.exceptions.SearchContextSizeMismatch;
import io.perfeccionista.framework.exceptions.attachments.ValueAttachmentEntry;
import io.perfeccionista.framework.pagefactory.elements.WebTableRow;
import io.perfeccionista.framework.pagefactory.elements.base.WebParentElement;
import io.perfeccionista.framework.pagefactory.elements.WebTable;
import io.perfeccionista.framework.pagefactory.filter.table.WebTableFilterBuilder;
import io.perfeccionista.framework.value.number.NumberValue;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Stream;

import static io.perfeccionista.framework.Web.row;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.SEARCH_CONTEXT_EXPECTED_SIZE_DOES_NOT_MATCH;

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
                        .getResults().values())
                .reduce(new ArrayList<>(), (webBlocks1, webBlocks2) -> {
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
