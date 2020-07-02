package io.perfeccionista.framework.pagefactory.elements.locators;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;

import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;

public class WebLocatorChain {

    private Deque<WebLocatorHolder> locatorSequence = new ArrayDeque<>();

    public static WebLocatorChain empty() {
        return new WebLocatorChain();
    }

    public static WebLocatorChain of(WebLocatorHolder locator) {
        return new WebLocatorChain().addLocator(locator);
    }

    public WebLocatorChain validate() {
        // TODO: Implement Проверить что множественный локатор только 0 или 1 на цепочку
        //  заполнены либо single = true и index, либо false и indexes
        return this;
    }

    public WebLocatorChain addLocator(@NotNull WebLocatorHolder locator) {
        locatorSequence.addLast(locator);
        return this;
    }

    public WebLocatorChain addLocators(@NotNull Collection<WebLocatorHolder> locators) {
        locatorSequence.addAll(locators);
        return this;
    }

    public WebLocatorChain addExpectedHashToLastLocator(String expectedHash) {
        locatorSequence.getLast().setExpectedHash(expectedHash);
        return this;
    }

    public WebLocatorHolder getLastLocator() {
        return locatorSequence.getLast();
    }

    public WebLocatorHolder removeLastLocator() {
        return locatorSequence.removeLast();
    }

    public Deque<WebLocatorHolder> getAllLocators() {
        return new ArrayDeque<>(locatorSequence);
    }

    public ObjectNode toJson() {
        ObjectNode rootNode = createObjectNode();
        ArrayNode locatorChainNode = rootNode.putArray("locators");
        locatorSequence.forEach(webLocatorHolder -> locatorChainNode.add(webLocatorHolder.toJson()));
        return rootNode;
    }

}
