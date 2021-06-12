package io.perfeccionista.framework.matcher.result.implementations;

import io.perfeccionista.framework.exceptions.ResultVerification;
import io.perfeccionista.framework.exceptions.attachments.ValueAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.WebElementAttachmentEntry;
import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.matcher.result.WebMultipleIndexedResultMatcher;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.result.WebMultipleIndexedResult;
import io.perfeccionista.framework.value.string.StringValue;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.FILTERED_ELEMENT_DOES_NOT_CONTAIN_EXPECTED_RESULT;
import static io.perfeccionista.framework.invocation.runner.InvocationName.assertInvocation;
import static io.perfeccionista.framework.invocation.wrapper.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SHOULD_HAVE_EXPECTED_RESULT_METHOD;

public class WebShouldHaveStringValueResultMatcher implements WebMultipleIndexedResultMatcher<String> {

    private final StringValue expectedResult;

    public WebShouldHaveStringValueResultMatcher(@NotNull StringValue expectedResult) {
        this.expectedResult = expectedResult;
    }

    @Override
    public void check(@NotNull WebMultipleIndexedResult<String, ? extends WebChildElement> result) {
        InvocationName invocationName = assertInvocation(SHOULD_HAVE_EXPECTED_RESULT_METHOD, this);

        WebChildElement element = result.getElement();

        runCheck(invocationName, () -> {
            AtomicBoolean match = new AtomicBoolean(true);
            Map<Integer, String> results = result.getResults();
            results.forEach((index, value) -> {
                if (!expectedResult.check(value)) {
                    match.set(false);
                }
            });
            if (!match.get()) {
                throw ResultVerification.assertionError(FILTERED_ELEMENT_DOES_NOT_CONTAIN_EXPECTED_RESULT.getMessage())
                        .setProcessed(true)
                        .addLastAttachmentEntry(WebElementAttachmentEntry.of(element))
                        // TODO: Добавить в велью все значения, которые сравнивались за цикл
                        .addLastAttachmentEntry(ValueAttachmentEntry.of(expectedResult))
                        // TODO: Добавить конструктор для коллекции
                        .addLastAttachmentEntry(ValueAttachmentEntry.of("Results", new HashSet<>(results.values())));
            }
        });
    }

}

