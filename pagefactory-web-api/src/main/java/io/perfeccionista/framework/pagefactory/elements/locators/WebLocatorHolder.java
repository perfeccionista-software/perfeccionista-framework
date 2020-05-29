package io.perfeccionista.framework.pagefactory.elements.locators;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.perfeccionista.framework.pagefactory.jsfunction.JsFunction;
import io.perfeccionista.framework.utils.JsonUtils;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static io.perfeccionista.framework.utils.JsonUtils.toPrettyJson;

public class WebLocatorHolder {

    protected final String locatorId;
    protected final String locatorComponent;
    protected final String locatorStrategy;
    protected final String locatorValue;
    protected Set<Integer> indexes;
    protected int index;
    protected boolean single;
    protected boolean strictSearch;
    protected boolean onlyWithinParent;
    protected boolean calculateHash;
    protected String expectedHash;
    protected Deque<JsFunction<Void>> invokeOnCallFunctions;

    protected WebLocatorHolder(String locatorComponent, String locatorStrategy, String locatorValue) {
        // TODO: Нужно формировать из pageObjectId(пейдж + блоки + филд) + ComponentName -> md5 (чтобы он был одинаковый для перерасчета)
//        this.id = id;
        this.locatorId = UUID.randomUUID().toString();
        this.locatorComponent = locatorComponent;
        this.locatorStrategy = locatorStrategy;
        this.locatorValue = locatorValue;
        this.indexes = new HashSet<>();
        this.index = -1;
        this.single = true;
        this.strictSearch = true;
        this.onlyWithinParent = true;
        this.calculateHash = false;
        this.expectedHash = null;
        this.invokeOnCallFunctions = new ArrayDeque<>();
    }

    public static WebLocatorHolder of(String locatorComponent, String locatorStrategy, String locatorValue) {
        return new WebLocatorHolder(locatorComponent, locatorStrategy, locatorValue);
    }

    public WebLocatorHolder setIndex(int index) {
        this.index = index;
        return this;
    }

    public WebLocatorHolder setIndexes(Collection<Integer> indexes) {
        this.indexes = new HashSet<>(indexes);
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

    public WebLocatorHolder setOnlyWithinParent(boolean onlyWithinParent) {
        this.onlyWithinParent = onlyWithinParent;
        return this;
    }

    public WebLocatorHolder setCalculateHash(boolean calculateHash) {
        this.calculateHash = calculateHash;
        return this;
    }

    public WebLocatorHolder setExpectedHash(String expectedHash) {
        this.expectedHash = expectedHash;
        return this;
    }

    public WebLocatorHolder addInvokedOnCallFunctions(JsFunction<Void> invokeOnCallFunction) {
        this.invokeOnCallFunctions.add(invokeOnCallFunction);
        return this;
    }

    public WebLocatorHolder setInvokedOnCallFunctions(Deque<JsFunction<Void>> invokeOnCallFunctions) {
        this.invokeOnCallFunctions = invokeOnCallFunctions;
        return this;
    }

    public String getLocatorId() {
        return locatorId;
    }

    public String getLocatorComponent() {
        return locatorComponent;
    }

    public String getLocatorStrategy() {
        return locatorStrategy;
    }

    public String getLocatorValue() {
        return locatorValue;
    }

    public Set<Integer> getIndexes() {
        return Set.copyOf(indexes);
    }

    public boolean isSingle() {
        return single;
    }

    public boolean isStrictSearch() {
        return strictSearch;
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

    public Deque<JsFunction<Void>> getInvokeOnCallFunctions() {
        return new ArrayDeque<>(invokeOnCallFunctions);
    }

    @Override
    public WebLocatorHolder clone() {
        return new WebLocatorHolder(this.locatorComponent, this.locatorStrategy, this.locatorValue)
                .setIndexes(this.getIndexes())
                .setSingle(this.single)
                .setStrictSearch(this.strictSearch)
                .setOnlyWithinParent(this.onlyWithinParent)
                .setCalculateHash(this.calculateHash)
                .setExpectedHash(this.expectedHash)
                .setInvokedOnCallFunctions(this.getInvokeOnCallFunctions());
    }

    public ObjectNode toJson() {
        ObjectNode locatorNode = JsonUtils.createObjectNode()
                .put("locatorId", this.locatorId)
                .put("locatorComponent", this.locatorComponent)
                .put("locatorStrategy", this.locatorStrategy)
                .put("locatorValue", this.locatorValue)
                .put("single", this.single)
                .put("strictSearch", this.strictSearch)
                .put("onlyWithinParent", this.onlyWithinParent)
                .put("calculateHash", this.calculateHash);
        if (getExpectedHash().isPresent()) {
            locatorNode.put("expectedHash", this.expectedHash);
        }
        if (single) {
            if (index != -1) {
                locatorNode.put("index", index);
            }
        } else  {
            ArrayNode indexesNode = locatorNode.putArray("indexes");
            indexes.stream().sorted().forEachOrdered(indexesNode::add);
        }
        if (!invokeOnCallFunctions.isEmpty()) {
            ArrayNode invokeOnCallNode = locatorNode.putArray("invokeOnCallFunctions");
            invokeOnCallFunctions.forEach(jsFunction -> invokeOnCallNode.add(jsFunction.getJsFunctionInvocation()));
        }
        return locatorNode;
    }

    /**
     * Выводить в строку локатор
     * @return
     */
    public String toString() {
        return toPrettyJson(toJson());
    }

    /**
     * Используется в описании цепочки локаторов до элемента
     * @return
     */
    public String getShortDescription() {
        // TODO: Implement
        return "";
    }

}
