package io.perfeccionista.framework.pagefactory.elements.locators;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.perfeccionista.framework.utils.JsonUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static io.perfeccionista.framework.utils.JsonUtils.toPrettyJson;

public abstract class MobileLocatorHolder {

    protected final String locatorId;
    protected String locatorComponent;
    protected final LocatorStrategy locatorStrategy;
    protected final String locatorValue;
    protected Set<Integer> indexes;
    protected int index;
    protected boolean single;
    protected boolean strictSearch;
    protected boolean onlyWithinParent;
    protected boolean calculateHash;
    protected String expectedHash;

    protected MobileLocatorHolder(String locatorComponent,
                                  LocatorStrategy locatorStrategy,
                                  String locatorValue) {
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
    }

    public MobileLocatorHolder setLocatorComponent(@NotNull String locatorComponent) {
        this.locatorComponent = locatorComponent;
        return this;
    }

    public MobileLocatorHolder setIndex(int index) {
        this.index = index;
        return this;
    }

    public MobileLocatorHolder setIndexes(Collection<Integer> indexes) {
        this.indexes = new HashSet<>(indexes);
        this.setSingle(false);
        return this;
    }

    public MobileLocatorHolder setSingle(boolean single) {
        this.single = single;
        return this;
    }

    public MobileLocatorHolder setStrictSearch(boolean strictSearch) {
        this.strictSearch = strictSearch;
        return this;
    }

    public MobileLocatorHolder setOnlyWithinParent(boolean onlyWithinParent) {
        this.onlyWithinParent = onlyWithinParent;
        return this;
    }

    public MobileLocatorHolder setCalculateHash(boolean calculateHash) {
        this.calculateHash = calculateHash;
        return this;
    }

    public MobileLocatorHolder setExpectedHash(@Nullable String expectedHash) {
        this.expectedHash = expectedHash;
        return this;
    }

    public String getLocatorId() {
        return locatorId;
    }

    public String getLocatorComponent() {
        return locatorComponent;
    }

    public LocatorStrategy getLocatorStrategy() {
        return locatorStrategy;
    }

    public String getLocatorValue() {
        return locatorValue;
    }

    public Integer getIndex() {
        return index;
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

    @Override
    public abstract MobileLocatorHolder clone();

    public ObjectNode toJson() {
        ObjectNode locatorNode = JsonUtils.createObjectNode()
                .put("locatorId", this.locatorId)
                .put("locatorComponent", this.locatorComponent)
                .put("locatorStrategy", this.locatorStrategy.getStrategyName())
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
