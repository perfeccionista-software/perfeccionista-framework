package io.perfeccionista.framework.matcher.result;

import io.perfeccionista.framework.exceptions.WebResultSetNotSorted;
import io.perfeccionista.framework.exceptions.attachments.StringAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.WebElementAttachmentEntry;
import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.result.WebMultipleIndexedResult;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicBoolean;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.FILTERED_ELEMENT_IS_NOT_SORTED;
import static io.perfeccionista.framework.invocation.runner.InvocationName.assertInvocation;
import static io.perfeccionista.framework.invocation.wrappers.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.SHOULD_BE_SORTED_METHOD;

public class SortMatcher<T> implements WebMultipleIndexedResultMatcher<T> {

    private final Comparator<T> comparator;

    public SortMatcher(@NotNull Comparator<T> comparator) {
        this.comparator = comparator;
    }

    @Override
    public void check(@NotNull WebMultipleIndexedResult<T, ? extends WebChildElement> result) {
        InvocationName invocationName = assertInvocation(SHOULD_BE_SORTED_METHOD, this);

        WebChildElement element = result.getElement();

        runCheck(element.getEnvironment(), invocationName, () -> {
            AtomicBoolean comparisonResult = new AtomicBoolean(true);
            Map<String, Integer> detailComparisonResult = new HashMap<>();
            result.getValues().entrySet().stream()
                    .sorted(Entry.comparingByKey())
                    .reduce((entry1, entry2) -> {
                        int pairComparisonResult = comparator.compare(entry1.getValue(), entry2.getValue());
                        if (pairComparisonResult > 0) {
                            comparisonResult.set(false);
                        }
                        String indexPair = String.format("Compare indexes %10d and %10d", entry1.getKey(), entry2.getKey());
                        detailComparisonResult.put(indexPair, pairComparisonResult);
                        return entry2;
                    });
            if (!comparisonResult.get()) {
                throw WebResultSetNotSorted.assertionError(FILTERED_ELEMENT_IS_NOT_SORTED.getMessage())
                        .setProcessed(true)
                        .addLastAttachmentEntry(WebElementAttachmentEntry.of(element))
                        .addLastAttachmentEntry(StringAttachmentEntry.of("Comparison result", comparisonResultsToString(detailComparisonResult)));
            }
        });
    }

    private @NotNull String comparisonResultsToString(@NotNull Map<String, Integer> detailComparisonResults) {
        StringBuilder sb = new StringBuilder("Comparison result :\n");
        detailComparisonResults.forEach((indexPair, indexPairComparisonResult) -> {
            sb.append(indexPair).append(" -> ").append(indexPairComparisonResult).append("\n");
        });
        return sb.toString();
    }

}

