package io.perfeccionista.framework.pagefactory.elements.locators;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;
import java.util.function.Consumer;

import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;

public class WebLocatorChain {

    private Deque<WebLocatorHolder> locatorSequence = new ArrayDeque<>();

    public static WebLocatorChain empty() {
        return new WebLocatorChain();
    }

    public static WebLocatorChain of(WebLocatorHolder locator) {
        return new WebLocatorChain().addLastLocator(locator);
    }

    public WebLocatorChain validate() {
        // TODO: Implement Проверить что множественный локатор только 0 или 1 на цепочку
        //  заполнены либо single = true и index, либо false и indexes
        return this;
    }

    public WebLocatorChain addFirstLocator(@NotNull WebLocatorHolder locator) {
        locatorSequence.addFirst(locator);
        return this;
    }

    public WebLocatorChain addLastLocator(@NotNull WebLocatorHolder locator) {
        locatorSequence.addLast(locator);
        return this;
    }

    public WebLocatorChain addFirstLocators(@NotNull WebLocatorChain locatorChain) {
        addFirstLocators(locatorChain.getAllLocators());
        return this;
    }

    public WebLocatorChain addFirstLocators(@NotNull Collection<WebLocatorHolder> locators) {
        Deque<WebLocatorHolder> updatedLocatorSequence = new ArrayDeque<>();
        updatedLocatorSequence.addAll(locators);
        updatedLocatorSequence.addAll(locatorSequence);
        locatorSequence = updatedLocatorSequence;
        return this;
    }

    public WebLocatorChain addLastLocators(@NotNull WebLocatorChain locatorChain) {
        locatorSequence.addAll(locatorChain.getAllLocators());
        return this;
    }

    public WebLocatorChain addLastLocators(@NotNull Collection<WebLocatorHolder> locators) {
        locatorSequence.addAll(locators);
        return this;
    }

    public WebLocatorChain updateLastLocator(Consumer<WebLocatorHolder> lastLocatorHolderConsumer) {
        lastLocatorHolderConsumer.accept(locatorSequence.getLast());
        return this;
    }

    public WebLocatorHolder getFirstLocator() {
        return locatorSequence.getFirst();
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
