package io.perfeccionista.framework.pagefactory.limiter;

import io.perfeccionista.framework.exceptions.SearchContextSizeMismatch;
import io.perfeccionista.framework.exceptions.attachments.ValueAttachmentEntry;
import io.perfeccionista.framework.pagefactory.elements.MobileBlock;
import io.perfeccionista.framework.pagefactory.elements.base.MobileParentElement;
import io.perfeccionista.framework.pagefactory.elements.MobileTable;
import io.perfeccionista.framework.pagefactory.filter.table.MobileTableFilterBuilder;
import io.perfeccionista.framework.value.number.NumberValue;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Stream;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.SEARCH_CONTEXT_EXPECTED_SIZE_DOES_NOT_MATCH;
import static io.perfeccionista.framework.pagefactory.extractor.MobileExtractors.cell;

public class MobileTableCellContextLimiter<T extends MobileBlock> implements MobileContextLimiter<T> {

    private final String columnName;
    private final String elementPath;
    private final MobileTable elementFrame;
    private final MobileTableFilterBuilder filterBuilder;
    private final NumberValue<Integer> expectedSize;

    private MobileTableCellContextLimiter(String elementPath,
                                          MobileTable elementFrame,
                                          String columnName,
                                          MobileTableFilterBuilder filterBuilder,
                                          NumberValue<Integer> expectedSize) {
        this.elementPath = elementPath;
        this.elementFrame = elementFrame;
        this.columnName = columnName;
        this.filterBuilder = filterBuilder;
        this.expectedSize = expectedSize;
    }

    public static <T extends MobileBlock> MobileTableCellContextLimiter<T> of(@NotNull MobileTable elementFrame,
                                                                              @NotNull String columnName,
                                                                              @NotNull MobileTableFilterBuilder filterBuilder,
                                                                              @NotNull NumberValue<Integer> expectedSize) {
        return new MobileTableCellContextLimiter<>(null, elementFrame, columnName, filterBuilder, expectedSize);
    }

    public static <T extends MobileBlock> MobileTableCellContextLimiter<T> of(@NotNull String elementPath,
                                                                              @NotNull String columnName,
                                                                              @NotNull MobileTableFilterBuilder filterBuilder,
                                                                              @NotNull NumberValue<Integer> expectedSize) {
        return new MobileTableCellContextLimiter<>(elementPath, null, columnName, filterBuilder, expectedSize);
    }

    @Override
    public @NotNull Stream<T> getContexts(@NotNull Stream<MobileParentElement> parentElements) {
        Collection<T> mobileBlocks = parentElements
                .map(parentElement -> {
                    MobileTable mobileTable;
                    if (Objects.nonNull(elementFrame)) {
                        mobileTable = parentElement
                                .getElementRegistry()
                                .getRequiredElementByMethod(elementFrame.getElementIdentifier().getElementMethod(), MobileTable.class);
                    } else {
                        mobileTable = parentElement
                                .getElementRegistry()
                                .getRequiredElementByPath(elementPath, MobileTable.class);
                    }
                    return (Collection<T>) mobileTable.filter(filterBuilder)
                            .extractRows(cell(columnName))
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
