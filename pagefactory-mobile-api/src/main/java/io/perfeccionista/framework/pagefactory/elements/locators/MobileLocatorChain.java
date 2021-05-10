package io.perfeccionista.framework.pagefactory.elements.locators;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;
import java.util.function.Consumer;

import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;

public class MobileLocatorChain {

    private Deque<MobileLocatorHolder> locatorSequence = new ArrayDeque<>();

    public static MobileLocatorChain empty() {
        return new MobileLocatorChain();
    }

    public static MobileLocatorChain of(MobileLocatorHolder locator) {
        return new MobileLocatorChain().addLastLocator(locator);
    }

    public MobileLocatorChain validate() {
        // TODO: Implement Проверить что множественный локатор только 0 или 1 на цепочку
        //  заполнены либо single = true и index, либо false и indexes
        return this;
    }

    public boolean isEmpty() {
        return locatorSequence.isEmpty();
    }

    public MobileLocatorChain addFirstLocator(@NotNull MobileLocatorHolder locator) {
        locatorSequence.addFirst(locator);
        return this;
    }

    public MobileLocatorChain addLastLocator(@NotNull MobileLocatorHolder locator) {
        locatorSequence.addLast(locator);
        return this;
    }

    public MobileLocatorChain addFirstLocators(@NotNull MobileLocatorChain locatorChain) {
        addFirstLocators(locatorChain.getAllLocators());
        return this;
    }

    public MobileLocatorChain addFirstLocators(@NotNull Collection<MobileLocatorHolder> locators) {
        Deque<MobileLocatorHolder> updatedLocatorSequence = new ArrayDeque<>();
        updatedLocatorSequence.addAll(locators);
        updatedLocatorSequence.addAll(locatorSequence);
        locatorSequence = updatedLocatorSequence;
        return this;
    }

    public MobileLocatorChain addLastLocators(@NotNull MobileLocatorChain locatorChain) {
        locatorSequence.addAll(locatorChain.getAllLocators());
        return this;
    }

    public MobileLocatorChain addLastLocators(@NotNull Collection<MobileLocatorHolder> locators) {
        locatorSequence.addAll(locators);
        return this;
    }

    public MobileLocatorChain updateLastLocator(Consumer<MobileLocatorHolder> lastLocatorHolderConsumer) {
        lastLocatorHolderConsumer.accept(locatorSequence.getLast());
        return this;
    }

    public MobileLocatorHolder getFirstLocator() {
        return locatorSequence.getFirst();
    }

    public MobileLocatorHolder getLastLocator() {
        return locatorSequence.getLast();
    }

    public MobileLocatorHolder removeLastLocator() {
        return locatorSequence.removeLast();
    }

    public Deque<MobileLocatorHolder> getAllLocators() {
        return new ArrayDeque<>(locatorSequence);
    }

    public ObjectNode toJson() {
        ObjectNode rootNode = createObjectNode();
        ArrayNode locatorChainNode = rootNode.putArray("locators");
        locatorSequence.forEach(webLocatorHolder -> locatorChainNode.add(webLocatorHolder.toJson()));
        return rootNode;
    }

}
