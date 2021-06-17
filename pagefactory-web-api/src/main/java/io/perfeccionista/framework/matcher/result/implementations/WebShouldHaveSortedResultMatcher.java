package io.perfeccionista.framework.matcher.result.implementations;

import io.perfeccionista.framework.exceptions.WebResultSetNotSorted;
import io.perfeccionista.framework.exceptions.attachments.TextAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.WebElementAttachmentEntry;
import io.perfeccionista.framework.invocation.runner.InvocationInfo;
import io.perfeccionista.framework.matcher.result.WebMultipleIndexedResultMatcher;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.result.WebMultipleIndexedResult;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicBoolean;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.FILTERED_ELEMENT_IS_NOT_SORTED;
import static io.perfeccionista.framework.invocation.runner.InvocationInfo.assertInvocation;
import static io.perfeccionista.framework.invocation.wrapper.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SHOULD_BE_SORTED_METHOD;

public class WebShouldHaveSortedResultMatcher<T> implements WebMultipleIndexedResultMatcher<T> {

    private final Comparator<T> comparator;

    public WebShouldHaveSortedResultMatcher(@NotNull Comparator<T> comparator) {
        this.comparator = comparator;
    }

    @Override
    public void check(@NotNull WebMultipleIndexedResult<T, ? extends WebChildElement> result) {
        InvocationInfo invocationName = assertInvocation(SHOULD_BE_SORTED_METHOD, this);

        WebChildElement element = result.getElement();

        runCheck(invocationName, () -> {
            AtomicBoolean comparisonResult = new AtomicBoolean(true);
            Map<String, Integer> detailComparisonResult = new HashMap<>();
            result.getResults().entrySet().stream()
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
                        .addLastAttachmentEntry(TextAttachmentEntry.of("Comparison result", comparisonResultsToString(detailComparisonResult)));
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

