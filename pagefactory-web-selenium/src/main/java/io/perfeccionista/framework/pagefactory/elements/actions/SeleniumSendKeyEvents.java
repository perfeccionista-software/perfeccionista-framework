package io.perfeccionista.framework.pagefactory.elements.actions;

import io.perfeccionista.framework.exceptions.attachments.JsonAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.WebElementAttachmentEntry;
import io.perfeccionista.framework.pagefactory.jsfunction.GetWebElement;
import io.perfeccionista.framework.pagefactory.elements.actions.base.WebElementActionImplementation;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElementBase;
import io.perfeccionista.framework.pagefactory.keys.Key;
import io.perfeccionista.framework.pagefactory.keys.KeyEventType;
import io.perfeccionista.framework.pagefactory.keys.KeysEventChain;
import io.perfeccionista.framework.pagefactory.operation.JsOperation;
import io.perfeccionista.framework.pagefactory.operation.JsOperationResult;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.time.Duration;

import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.INPUT;
import static io.perfeccionista.framework.utils.ThreadUtils.sleep;

public class SeleniumSendKeyEvents implements WebElementActionImplementation<Void> {

    @Override
    public Void execute(@NotNull WebChildElementBase element, Object... args) {
        KeysEventChain keysEventChain = (KeysEventChain) args[0];
        JsOperation<WebElement> operation = JsOperation.of(element.getLocatorChainTo(INPUT), new GetWebElement());
        JsOperationResult<WebElement> operationResult = element.getWebBrowserDispatcher().executor()
                .executeOperation(operation)
                .ifException(exception -> {
                    throw exception.addLastAttachmentEntry(WebElementAttachmentEntry.of(element));
                });
        WebElement webElement = operationResult.getResult();
        RemoteWebDriver remoteWebDriver = element.getWebBrowserDispatcher().getInstance(RemoteWebDriver.class);

        keysEventChain.getKeyEvents().forEach(keyEvent -> {
            Key key = keyEvent.getKey();
            KeyEventType type = keyEvent.getType();
            CharSequence charSequenceToInput = Keys.getKeyFromUnicode(key.getKeyCode()) == null
                    ? String.valueOf(key.getKeyCode())
                    : Keys.getKeyFromUnicode(key.getKeyCode());
            element.getWebBrowserDispatcher().getExceptionMapper()
                    .map(() -> {
                        switch (type) {
                            case KEY_PRESS: {
                                webElement.sendKeys(charSequenceToInput);
                                break;
                            }
                            case KEY_DOWN: {
                                new Actions(remoteWebDriver)
                                        .keyDown(webElement, charSequenceToInput)
                                        .build()
                                        .perform();
                                break;
                            }
                            case KEY_UP: {
                                new Actions(remoteWebDriver)
                                        .keyUp(webElement, charSequenceToInput)
                                        .build()
                                        .perform();
                                break;
                            }
                        }
                        sleep(keyEvent.getDelay());
                    }, element.getElementIdentifier().getLastUsedName())
                    .ifException(exception -> {
                        throw exception.addLastAttachmentEntry(WebElementAttachmentEntry.of(element))
                                .addLastAttachmentEntry(JsonAttachmentEntry.of("Key Events", keysEventChain.toJson()));
                    });
        });
        return null;
    }

}
