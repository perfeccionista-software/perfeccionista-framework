package io.perfeccionista.framework.matcher.result.implementations;

import io.perfeccionista.framework.exceptions.WebElementIndex;
import io.perfeccionista.framework.exceptions.attachments.ValueAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.WebElementAttachmentEntry;
import io.perfeccionista.framework.invocation.runner.InvocationInfo;
import io.perfeccionista.framework.matcher.result.WebIndexesMatcher;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.result.WebMultipleIndexedResult;
import io.perfeccionista.framework.value.number.NumberValue;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.FILTERED_ELEMENT_CONTAINS_INDEX;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.FILTERED_ELEMENT_DOES_NOT_CONTAIN_INDEX;
import static io.perfeccionista.framework.invocation.runner.InvocationInfo.assertInvocation;
import static io.perfeccionista.framework.invocation.wrapper.MultipleAttemptInvocationWrapper.repeatInvocation;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SHOULD_HAVE_INDEX_VALUE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SHOULD_NOT_HAVE_INDEX_VALUE_METHOD;
import static java.util.stream.Collectors.joining;

public class WebShouldHaveIndexNumberValueMatcher implements WebIndexesMatcher {

    private final NumberValue<Integer> expectedValue;
    private final boolean positive;

    public WebShouldHaveIndexNumberValueMatcher(@NotNull NumberValue<Integer> expectedValue, boolean positive) {
        this.expectedValue = expectedValue;
        this.positive = positive;
    }

    @Override
    public void check(@NotNull WebMultipleIndexedResult<Integer, ? extends WebChildElement> result) {
        WebChildElement element = result.getElement();
        String elementName = element.getElementIdentifier().getLastUsedName();

        InvocationInfo invocationName = positive
                ? assertInvocation(SHOULD_HAVE_INDEX_VALUE_METHOD, elementName, expectedValue.getShortDescription())
                : assertInvocation(SHOULD_NOT_HAVE_INDEX_VALUE_METHOD, elementName, expectedValue.getShortDescription());

        repeatInvocation(invocationName, () -> {
            Collection<Integer> indexes = result.getResults().values();
            boolean actualValue = indexes.stream()
                    .anyMatch(expectedValue::check);
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
                    .addLastAttachmentEntry(WebElementAttachmentEntry.of(element))
                    .addLastAttachmentEntry(ValueAttachmentEntry.of(expectedValue, indexesToString(indexes)));
        }
    }

    protected void shouldNotHaveIndex(WebChildElement element, boolean actualValue, Collection<Integer> indexes) {
        if (actualValue) {
            throw WebElementIndex.assertionError(FILTERED_ELEMENT_CONTAINS_INDEX.getMessage())
                    .setProcessed(true)
                    .addLastAttachmentEntry(WebElementAttachmentEntry.of(element))
                    .addLastAttachmentEntry(ValueAttachmentEntry.of(expectedValue, indexesToString(indexes)));
        }
    }

    private String indexesToString(Collection<Integer> indexes) {
        return indexes.stream()
                .map(Object::toString)
                .collect(joining(", "));
    }

}
