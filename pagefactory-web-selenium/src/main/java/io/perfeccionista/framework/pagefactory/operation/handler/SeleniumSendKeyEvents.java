package io.perfeccionista.framework.pagefactory.operation.handler;

import com.fasterxml.jackson.databind.JsonNode;
import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.pagefactory.dispatcher.WebBrowserService;
import io.perfeccionista.framework.pagefactory.emulator.keys.Key;
import io.perfeccionista.framework.pagefactory.emulator.keys.KeyEventType;
import io.perfeccionista.framework.pagefactory.emulator.keys.KeyEventsChain;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;

import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;
import static io.perfeccionista.framework.utils.ThreadUtils.sleep;

public class SeleniumSendKeyEvents implements EndpointHandler<Void> {

    private final KeyEventsChain keyEvents;

    public SeleniumSendKeyEvents(@NotNull KeyEventsChain keyEvents) {
        this.keyEvents = keyEvents;
    }

    @Override
    public Void handle(Object endpoint) {
        WebElement webElement = (WebElement) endpoint;

        WebBrowserService webBrowserService = Environment.getCurrent().getService(WebBrowserService.class);
        RemoteWebDriver remoteWebDriver = webBrowserService.getActiveDispatcher()
                .getInstance(RemoteWebDriver.class);

        keyEvents.getKeyEvents().forEach(keyEvent -> {
            Key key = keyEvent.getKey();
            KeyEventType type = keyEvent.getType();
            CharSequence charSequenceToInput = Keys.getKeyFromUnicode(key.getKeyCode()) == null
                    ? String.valueOf(key.getKeyCode())
                    : Keys.getKeyFromUnicode(key.getKeyCode());

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
        });

        return null;
    }

    @Override
    public @NotNull JsonNode toJson() {
        return createObjectNode()
                .put("name", "perfeccionista.js.selenium.GetWebElement")
                .put("script", "js/GetWebElement.js");
    }

}
