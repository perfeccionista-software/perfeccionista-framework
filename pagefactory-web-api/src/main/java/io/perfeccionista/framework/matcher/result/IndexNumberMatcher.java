package io.perfeccionista.framework.matcher.result;

import io.perfeccionista.framework.exceptions.WebElementIndex;
import io.perfeccionista.framework.exceptions.attachments.ValueAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.WebElementAttachmentEntry;
import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.result.WebMultipleIndexedResult;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.FILTERED_ELEMENT_CONTAINS_INDEX;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.FILTERED_ELEMENT_DOES_NOT_CONTAIN_INDEX;
import static io.perfeccionista.framework.invocation.wrappers.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.SHOULD_HAVE_INDEX_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.SHOULD_NOT_HAVE_INDEX_METHOD;
import static java.util.stream.Collectors.joining;

public class IndexNumberMatcher implements WebIndexesMatcher {

    private final int expectedIndex;
    private final boolean positive;

    public IndexNumberMatcher(int expectedIndex, boolean positive) {
        this.expectedIndex = expectedIndex;
        this.positive = positive;
    }

    @Override
    public void check(@NotNull WebMultipleIndexedResult<Integer, ? extends WebChildElement> result) {
        InvocationName invocationName = positive
                ? InvocationName.of(SHOULD_HAVE_INDEX_METHOD, this, expectedIndex)
                : InvocationName.of(SHOULD_NOT_HAVE_INDEX_METHOD, this, expectedIndex);

        WebChildElement element = result.getElement();

        runCheck(element.getEnvironment(), invocationName, () -> {
            Collection<Integer> indexes = result.getValues().values();
            boolean actualValue = indexes.stream()
                    .anyMatch(index -> expectedIndex == index);
            if (positive) {
                shouldHaveIndex(element, actualValue, indexes);
            } else {
                shouldNotHaveIndex(element, actualValue, indexes);
            }
        });
    }

    protected void shouldHaveIndex(WebChildElement element, boolean actualValue, Collection<Integer> indexes) {
        if (!actualValue) {
            throw WebElementIndex.assertionError(FILTERED_ELEMENT_DOES_NOT_CONTAIN_INDEX.getMessage())
                    .setProcessed(true)
                    .addLastAttachmentEntry(ValueAttachmentEntry.of(String.valueOf(expectedIndex), indexesToString(indexes)))
                    .addLastAttachmentEntry(WebElementAttachmentEntry.of(element));
        }
    }

    protected void shouldNotHaveIndex(WebChildElement element, boolean actualValue, Collection<Integer> indexes) {
        if (actualValue) {
            throw WebElementIndex.assertionError(FILTERED_ELEMENT_CONTAINS_INDEX.getMessage())
                    .setProcessed(true)
                    .addLastAttachmentEntry(ValueAttachmentEntry.of(String.valueOf(expectedIndex), indexesToString(indexes)))
                    .addLastAttachmentEntry(WebElementAttachmentEntry.of(element));
        }
    }

    private String indexesToString(Collection<Integer> indexes) {
        return indexes.stream()
                .map(Object::toString)
                .collect(joining(", "));
    }

}

