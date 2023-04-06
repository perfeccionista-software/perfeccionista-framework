package io.perfeccionista.framework.pagefactory.elements.selectors;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;
import java.util.function.Consumer;

import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;

public class WebSelectorChain {

    private Deque<WebSelectorHolder> selectorSequence = new ArrayDeque<>();

    public static WebSelectorChain empty() {
        return new WebSelectorChain();
    }

    public static WebSelectorChain of(WebSelectorHolder selector) {
        return new WebSelectorChain().addLastSelector(selector);
    }

    public WebSelectorChain validate() {
        // TODO: Implement Проверить что множественный локатор только 0 или 1 на цепочку
        //  заполнены либо single = true и index, либо false и indexes
        return this;
    }

    public boolean isEmpty() {
        return selectorSequence.isEmpty();
    }

    public WebSelectorChain addFirstSelector(@NotNull WebSelectorHolder selector) {
        selectorSequence.addFirst(selector);
        return this;
    }

    public WebSelectorChain addLastSelector(@NotNull WebSelectorHolder selector) {
        selectorSequence.addLast(selector);
        return this;
    }

    public WebSelectorChain addFirstSelectors(@NotNull WebSelectorChain selectorChain) {
        addFirstSelectors(selectorChain.getAllSelectors());
        return this;
    }

    public WebSelectorChain addFirstSelectors(@NotNull Collection<WebSelectorHolder> selectors) {
        Deque<WebSelectorHolder> updatedSelectorSequence = new ArrayDeque<>();
        updatedSelectorSequence.addAll(selectors);
        updatedSelectorSequence.addAll(selectorSequence);
        selectorSequence = updatedSelectorSequence;
        return this;
    }

    public WebSelectorChain addLastSelectors(@NotNull WebSelectorChain selectorChain) {
        selectorSequence.addAll(selectorChain.getAllSelectors());
        return this;
    }

    public WebSelectorChain addLastSelectors(@NotNull Collection<WebSelectorHolder> selectors) {
        selectorSequence.addAll(selectors);
        return this;
    }

    public WebSelectorChain updateLastSelector(Consumer<WebSelectorHolder> lastSelectorHolderConsumer) {
        lastSelectorHolderConsumer.accept(selectorSequence.getLast());
        return this;
    }

    public WebSelectorHolder getFirstSelector() {
        return selectorSequence.getFirst();
    }

    public WebSelectorHolder getLastSelector() {
        return selectorSequence.getLast();
    }

    public WebSelectorHolder removeLastSelector() {
        return selectorSequence.removeLast();
    }

    public Deque<WebSelectorHolder> getAllSelectors() {
        return new ArrayDeque<>(selectorSequence);
    }

    public ObjectNode toJson() {
        ObjectNode rootNode = createObjectNode();
        ArrayNode selectorChainNode = rootNode.putArray("selectors");
        selectorSequence.forEach(webSelectorHolder -> selectorChainNode.add(webSelectorHolder.toJson()));
        return rootNode;
    }

}
