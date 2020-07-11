package io.perfeccionista.framework.pagefactory.elements.actions;

import io.perfeccionista.framework.attachment.JsonAttachmentEntry;
import io.perfeccionista.framework.attachment.StringAttachmentEntry;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.jsfunction.GetWebElement;
import io.perfeccionista.framework.pagefactory.operation.JsOperation;
import io.perfeccionista.framework.pagefactory.operation.JsOperationResult;
import org.junit.platform.commons.util.ReflectionUtils;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.List;

import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.INPUT;
import static io.perfeccionista.framework.utils.ThreadUtils.sleep;

public class SeleniumSendKeys implements WebElementActionImplementation<Void> {
    private static final Duration SEND_KEYS_DELAY_IN_MILLISECONDS = Duration.ofMillis(100);

    @Override
    public Void execute(WebChildElement element, Object... args) {
        List<CharSequence> keysToSend = (List<CharSequence>) args[0];
        GetWebElement getWebElementFunction = ReflectionUtils.newInstance(GetWebElement.class);
        JsOperation<WebElement> operation = JsOperation.of(element.getLocatorChainTo(INPUT), getWebElementFunction);
        JsOperationResult<WebElement> operationResult = element.getWebBrowserDispatcher().executor().executeOperation(operation);
        operationResult.ifException(exception -> {
            throw exception.addAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()));
        });
        WebElement webElement = operationResult.singleResult().get();
        keysToSend.forEach(charSequence -> {
            element.getWebBrowserDispatcher().getExceptionMapper()
                    .map(() -> webElement.sendKeys(charSequence), element.getElementIdentifier().getLastUsedName())
                    .ifException(exception -> {
                        throw exception.addAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()))
                                .addAttachmentEntry(StringAttachmentEntry.of("Keys To Send", charSequence.toString()));
                    });
            sleep(SEND_KEYS_DELAY_IN_MILLISECONDS);
        });
        return null;
    }

}
