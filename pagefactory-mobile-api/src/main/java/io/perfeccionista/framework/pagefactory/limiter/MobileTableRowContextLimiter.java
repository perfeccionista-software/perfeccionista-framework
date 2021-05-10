package io.perfeccionista.framework.pagefactory.limiter;

import io.perfeccionista.framework.exceptions.SearchContextSizeMismatch;
import io.perfeccionista.framework.exceptions.attachments.ValueAttachmentEntry;
import io.perfeccionista.framework.pagefactory.elements.MobileTableRow;
import io.perfeccionista.framework.pagefactory.elements.base.MobileParentElement;
import io.perfeccionista.framework.pagefactory.elements.MobileTable;
import io.perfeccionista.framework.pagefactory.filter.table.MobileTableFilterBuilder;
import io.perfeccionista.framework.value.number.NumberValue;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Stream;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.SEARCH_CONTEXT_EXPECTED_SIZE_DOES_NOT_MATCH;
import static io.perfeccionista.framework.pagefactory.extractor.MobileExtractors.row;

public class MobileTableRowContextLimiter implements MobileContextLimiter<MobileTableRow> {

    private final String elementPath;
    private final MobileTableFilterBuilder filterBuilder;
    private final NumberValue<Integer> expectedSize;

    private MobileTableRowContextLimiter(String elementPath,
                                         MobileTableFilterBuilder filterBuilder,
                                         NumberValue<Integer> expectedSize) {
        this.elementPath = elementPath;
        this.filterBuilder = filterBuilder;
        this.expectedSize = expectedSize;
    }

    public static MobileTableRowContextLimiter of(@NotNull String elementPath,
                                                  @NotNull MobileTableFilterBuilder filterBuilder,
                                                  @NotNull NumberValue<Integer> expectedSize) {
        return new MobileTableRowContextLimiter(elementPath, filterBuilder, expectedSize);
    }

    @Override
    public @NotNull Stream<MobileTableRow> getContexts(@NotNull Stream<MobileParentElement> parentElements) {
        Collection<MobileTableRow> webBlocks = parentElements
                .map(parentElement -> parentElement
                        .getElementRegistry()
                        .getRequiredElementByPath(elementPath, MobileTable.class)
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
