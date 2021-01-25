package io.perfeccionista.framework.pagefactory.limiter;

import io.perfeccionista.framework.exceptions.SearchContextSizeMismatch;
import io.perfeccionista.framework.exceptions.attachments.ValueAttachmentEntry;
import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.base.WebParentElement;
import io.perfeccionista.framework.pagefactory.elements.WebTable;
import io.perfeccionista.framework.pagefactory.filter.table.WebTableFilterBuilder;
import io.perfeccionista.framework.value.number.NumberValue;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Stream;

import static io.perfeccionista.framework.Web.cell;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.SEARCH_CONTEXT_EXPECTED_SIZE_DOES_NOT_MATCH;

public class WebTableCellContextLimiter<T extends WebBlock> implements WebContextLimiter<T> {

    private final String columnName;
    private final String elementPath;
    private final WebTable elementFrame;
    private final WebTableFilterBuilder filterBuilder;
    private final NumberValue<Integer> expectedSize;

    private WebTableCellContextLimiter(String elementPath,
                                       WebTable elementFrame,
                                       String columnName,
                                       WebTableFilterBuilder filterBuilder,
                                       NumberValue<Integer> expectedSize) {
        this.elementPath = elementPath;
        this.elementFrame = elementFrame;
        this.columnName = columnName;
        this.filterBuilder = filterBuilder;
        this.expectedSize = expectedSize;
    }

    public static <T extends WebBlock> WebTableCellContextLimiter<T> of(@NotNull WebTable elementFrame,
                                                                        @NotNull String columnName,
                                                                        @NotNull WebTableFilterBuilder filterBuilder,
                                                                        @NotNull NumberValue<Integer> expectedSize) {
        return new WebTableCellContextLimiter<>(null, elementFrame, columnName, filterBuilder, expectedSize);
    }

    public static <T extends WebBlock> WebTableCellContextLimiter<T> of(@NotNull String elementPath,
                                                                        @NotNull String columnName,
                                                                        @NotNull WebTableFilterBuilder filterBuilder,
                                                                        @NotNull NumberValue<Integer> expectedSize) {
        return new WebTableCellContextLimiter<>(elementPath, null, columnName, filterBuilder, expectedSize);
    }

    @Override
    public @NotNull Stream<T> getContexts(@NotNull Stream<WebParentElement> parentElements) {
        Collection<T> webBlocks = parentElements.map(parentElement -> {
            WebTable webTable;
            if (Objects.nonNull(elementFrame)) {
                webTable = parentElement
                        .getElementRegistry()
                        .getRequiredElementByMethod(elementFrame.getElementIdentifier().getElementMethod(), WebTable.class);
            } else {
                webTable = parentElement
                        .getElementRegistry()
                        .getRequiredElementByPath(elementPath, WebTable.class);
            }
            return (Collection<T>) webTable.filter(filterBuilder)
                    .extractRows(cell(columnName))
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
