package io.perfeccionista.framework.pagefactory.elements.selectors;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.perfeccionista.framework.json.JsonSerializable;
import io.perfeccionista.framework.pagefactory.operation.handler.EndpointHandler;
import io.perfeccionista.framework.utils.JsonUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.ROOT;

public class WebSelectorHolder implements JsonSerializable {

    protected final String selectorId;
    protected String selectorComponent;
    protected final WebSelectorStrategy selectorStrategy;
    protected final String selectorValue;
    protected Set<Integer> indexes;
    protected int index;
    protected boolean single;
    protected boolean strictSearch;
    protected boolean fromParent;
    protected boolean onlyWithinParent;
    protected boolean calculateHash;
    protected String expectedHash;
    protected Deque<EndpointHandler<Void>> invokeOnCallHandlers;

    protected WebSelectorHolder(String selectorComponent, WebSelectorStrategy selectorStrategy, String selectorValue) {
        // TODO: Нужно формировать из pageObjectId(пейдж + блоки + филд) + ComponentName -> md5 (чтобы он был одинаковый для перерасчета)
//        this.id = id;
        this.selectorId = UUID.randomUUID().toString();
        this.selectorComponent = selectorComponent;
        this.selectorStrategy = selectorStrategy;
        this.selectorValue = selectorValue;
        this.indexes = null;
        this.index = -1;
        this.single = true;
        this.strictSearch = true;
        this.fromParent = true;
        this.onlyWithinParent = false;
        this.calculateHash = false;
        this.expectedHash = null;
        this.invokeOnCallHandlers = new ArrayDeque<>();
    }

    public static WebSelectorHolder of(String componentName, WebSelectorStrategy selectorStrategy, String selectorValue) {
        return new WebSelectorHolder(componentName, selectorStrategy, selectorValue);
    }

    public static WebSelectorHolder selfNode(String componentName) {
        return new WebSelectorHolder(componentName, WebSelectorStrategy.SELF_NODE, "");
    }

    public WebSelectorHolder setSelectorComponent(@NotNull String selectorComponent) {
        this.selectorComponent = selectorComponent;
        return this;
    }

    public WebSelectorHolder setIndex(int index) {
        this.index = index;
        return this;
    }

    public WebSelectorHolder setIndexes(@Nullable Collection<Integer> indexes) {
        if (Objects.isNull(indexes)) {
            this.indexes = null;
        } else {
            this.indexes = new HashSet<>(indexes);
        }
        this.setSingle(false);
        return this;
    }

    public WebSelectorHolder setSingle(boolean single) {
        this.single = single;
        return this;
    }

    public WebSelectorHolder setStrictSearch(boolean strictSearch) {
        this.strictSearch = strictSearch;
        return this;
    }

    public WebSelectorHolder setFromParent(boolean fromParent) {
        this.fromParent = fromParent;
        return this;
    }

    public WebSelectorHolder setOnlyWithinParent(boolean onlyWithinParent) {
        this.onlyWithinParent = onlyWithinParent;
        return this;
    }

    public WebSelectorHolder setCalculateHash(boolean calculateHash) {
        this.calculateHash = calculateHash;
        return this;
    }

    public WebSelectorHolder setExpectedHash(@Nullable String expectedHash) {
        this.expectedHash = expectedHash;
        return this;
    }

    // TODO: Проверять на наличие дубликатов, что такой функции тут нет, иначе игнорировать добавление
    public WebSelectorHolder addInvokedOnCallFunction(EndpointHandler<Void> invokeOnCallHandler) {
        this.invokeOnCallHandlers.add(invokeOnCallHandler);
        return this;
    }

    public WebSelectorHolder setInvokedOnCallFunctions(Deque<EndpointHandler<Void>> invokeOnCallHandlers) {
        this.invokeOnCallHandlers = invokeOnCallHandlers;
        return this;
    }

    public String getSelectorId() {
        return selectorId;
    }

    public String getSelectorComponent() {
        return selectorComponent;
    }

    public WebSelectorStrategy getSelectorStrategy() {
        return selectorStrategy;
    }

    public String getSelectorValue() {
        return selectorValue;
    }

    public @Nullable Set<Integer> getIndexes() {
        return Objects.isNull(indexes) ? null : new HashSet<>(indexes);
    }

    public boolean isSingle() {
        return single;
    }

    public boolean isStrictSearch() {
        return strictSearch;
    }

    public boolean isFromParent() {
        return fromParent;
    }

    public boolean isOnlyWithinParent() {
        return onlyWithinParent;
    }

    public boolean isCalculateHash() {
        return calculateHash;
    }

    public Optional<String> getExpectedHash() {
        return Optional.ofNullable(expectedHash);
    }

    public Deque<EndpointHandler<Void>> getInvokeOnCallHandlers() {
        return new ArrayDeque<>(invokeOnCallHandlers);
    }

    @Override
    public WebSelectorHolder clone() {
        return new WebSelectorHolder(this.selectorComponent, this.selectorStrategy, this.selectorValue)
                .setIndexes(this.getIndexes())
                .setSingle(this.single)
                .setStrictSearch(this.strictSearch)
                .setFromParent(this.fromParent)
                .setOnlyWithinParent(this.onlyWithinParent)
                .setCalculateHash(this.calculateHash)
                .setExpectedHash(this.expectedHash)
                .setInvokedOnCallFunctions(this.getInvokeOnCallHandlers());
    }

    @Override
    public @NotNull ObjectNode toJson() {
        ObjectNode locatorNode = JsonUtils.createObjectNode()
                .put("selectorId", this.selectorId)
                .put("selectorComponent", this.selectorComponent)
                .put("selectorStrategy", this.selectorStrategy.getStrategyName())
                .put("selectorValue", this.selectorValue)
                .put("single", this.single)
                .put("strictSearch", this.strictSearch)
                .put("fromParent", this.fromParent)
                .put("onlyWithinParent", this.onlyWithinParent)
                .put("calculateHash", this.calculateHash);
        if (getExpectedHash().isPresent()) {
            locatorNode.put("expectedHash", this.expectedHash);
        }
        if (single) {
            if (index != -1) {
                locatorNode.put("index", index);
            }
        } else {
            if (Objects.nonNull(indexes)) {
                ArrayNode indexesNode = locatorNode.putArray("indexes");
                indexes.stream().sorted().forEachOrdered(indexesNode::add);
            }
        }
        if (!invokeOnCallHandlers.isEmpty()) {
            ArrayNode invokeOnCallNode = locatorNode.putArray("invokeOnCallFunctions");
            invokeOnCallHandlers.forEach(endpointHandler -> invokeOnCallNode.add(endpointHandler.toJson()));
        }
        return locatorNode;
    }

    /**
     * Выводить в строку локатор
     * @return
     */
    public String toString() {
        return toJson().toString();
    }



    public static WebSelectorHolder byId(@NotNull String id) {
        return WebSelectorHolder.of(ROOT, WebSelectorStrategy.ID, id);
    }

    public static WebSelectorHolder byCss(@NotNull String cssSelector) {
        return WebSelectorHolder.of(ROOT, WebSelectorStrategy.CSS, cssSelector);
    }

    public static WebSelectorHolder byXpath(@NotNull String xpathSelector) {
        return WebSelectorHolder.of(ROOT, WebSelectorStrategy.CSS, xpathSelector);
    }

    public static WebSelectorHolder byClassName(@NotNull String className) {
        return WebSelectorHolder.of(ROOT, WebSelectorStrategy.CLASS_NAME, className);
    }

    public static WebSelectorHolder byTagName(@NotNull String tagName) {
        return WebSelectorHolder.of(ROOT, WebSelectorStrategy.TAG_NAME, tagName);
    }

    public static WebSelectorHolder byName(@NotNull String nameAttribute) {
        return WebSelectorHolder.of(ROOT, WebSelectorStrategy.NAME, nameAttribute);
    }

    public static WebSelectorHolder byDti(@NotNull String dataTestId) {
        return WebSelectorHolder.of(ROOT, WebSelectorStrategy.DTI, dataTestId);
    }

    public static WebSelectorHolder byFullText(@NotNull String fullText) {
        return WebSelectorHolder.of(ROOT, WebSelectorStrategy.EQUALS_TEXT, fullText);
    }

    public static WebSelectorHolder byPartialText(@NotNull String partialText) {
        return WebSelectorHolder.of(ROOT, WebSelectorStrategy.CONTAINS_TEXT, partialText);
    }

}
