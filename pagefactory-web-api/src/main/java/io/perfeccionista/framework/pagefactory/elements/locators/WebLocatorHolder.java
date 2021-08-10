package io.perfeccionista.framework.pagefactory.elements.locators;

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

import static io.perfeccionista.framework.utils.JsonUtils.toPrettyJson;

public class WebLocatorHolder implements JsonSerializable {

    protected final String locatorId;
    protected String locatorComponent;
    protected final WebLocatorStrategy locatorStrategy;
    protected final String locatorValue;
    protected Set<Integer> indexes;
    protected int index;
    protected boolean single;
    protected boolean strictSearch;
    protected boolean fromParent;
    protected boolean onlyWithinParent;
    protected boolean calculateHash;
    protected String expectedHash;
    protected Deque<EndpointHandler<Void>> invokeOnCallHandlers;

    protected WebLocatorHolder(String locatorComponent, WebLocatorStrategy locatorStrategy, String locatorValue) {
        // TODO: Нужно формировать из pageObjectId(пейдж + блоки + филд) + ComponentName -> md5 (чтобы он был одинаковый для перерасчета)
//        this.id = id;
        this.locatorId = UUID.randomUUID().toString();
        this.locatorComponent = locatorComponent;
        this.locatorStrategy = locatorStrategy;
        this.locatorValue = locatorValue;
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

    public static WebLocatorHolder of(String locatorComponent, WebLocatorStrategy locatorStrategy, String locatorValue) {
        return new WebLocatorHolder(locatorComponent, locatorStrategy, locatorValue);
    }

    public static WebLocatorHolder selfNode(String locatorComponent) {
        return new WebLocatorHolder(locatorComponent, WebLocatorStrategy.SELF_NODE, "");
    }

    public WebLocatorHolder setLocatorComponent(@NotNull String locatorComponent) {
        this.locatorComponent = locatorComponent;
        return this;
    }

    public WebLocatorHolder setIndex(int index) {
        this.index = index;
        return this;
    }

    public WebLocatorHolder setIndexes(@Nullable Collection<Integer> indexes) {
        if (Objects.isNull(indexes)) {
            this.indexes = null;
        } else {
            this.indexes = new HashSet<>(indexes);
        }
        this.setSingle(false);
        return this;
    }

    public WebLocatorHolder setSingle(boolean single) {
        this.single = single;
        return this;
    }

    public WebLocatorHolder setStrictSearch(boolean strictSearch) {
        this.strictSearch = strictSearch;
        return this;
    }

    public WebLocatorHolder setFromParent(boolean fromParent) {
        this.fromParent = fromParent;
        return this;
    }

    public WebLocatorHolder setOnlyWithinParent(boolean onlyWithinParent) {
        this.onlyWithinParent = onlyWithinParent;
        return this;
    }

    public WebLocatorHolder setCalculateHash(boolean calculateHash) {
        this.calculateHash = calculateHash;
        return this;
    }

    public WebLocatorHolder setExpectedHash(@Nullable String expectedHash) {
        this.expectedHash = expectedHash;
        return this;
    }

    // TODO: Проверять на наличие дубликатов, что такой функции тут нет, иначе игнорировать добавление
    public WebLocatorHolder addInvokedOnCallFunction(EndpointHandler<Void> invokeOnCallHandler) {
        this.invokeOnCallHandlers.add(invokeOnCallHandler);
        return this;
    }

    public WebLocatorHolder setInvokedOnCallFunctions(Deque<EndpointHandler<Void>> invokeOnCallHandlers) {
        this.invokeOnCallHandlers = invokeOnCallHandlers;
        return this;
    }

    public String getLocatorId() {
        return locatorId;
    }

    public String getLocatorComponent() {
        return locatorComponent;
    }

    public WebLocatorStrategy getLocatorStrategy() {
        return locatorStrategy;
    }

    public String getLocatorValue() {
        return locatorValue;
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
    public WebLocatorHolder clone() {
        return new WebLocatorHolder(this.locatorComponent, this.locatorStrategy, this.locatorValue)
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
                .put("locatorId", this.locatorId)
                .put("locatorComponent", this.locatorComponent)
                .put("locatorStrategy", this.locatorStrategy.getStrategyName())
                .put("locatorValue", this.locatorValue)
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

}
