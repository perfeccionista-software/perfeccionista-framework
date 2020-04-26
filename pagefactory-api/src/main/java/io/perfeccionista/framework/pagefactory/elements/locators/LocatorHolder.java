package io.perfeccionista.framework.pagefactory.elements.locators;

import java.util.Collection;
import java.util.UUID;

public class LocatorHolder {

    private final String id;

    private final LocatorType locatorType;
    private final String locatorStrategy;
    private final String locatorValue;

    public LocatorHolder(LocatorType locatorType, String locatorStrategy, String locatorValue) {
        this.locatorType = locatorType;
        this.locatorStrategy = locatorStrategy;
        this.locatorValue = locatorValue;
        this.id = UUID.randomUUID().toString();
    }

    public LocatorHolder calculateHash(boolean calculateHash) {
        return this;
    }

    public LocatorHolder checkHash(String hash) {
        return this;
    }

    public LocatorHolder setSingle(boolean single) {
        return this;
    }

    public LocatorHolder setIndexes(int... indexes) {
        return this;
    }

    public LocatorHolder setIndexes(Collection<Integer> indexes) {
        return this;
    }

    public LocatorHolder clone() {
        return new LocatorHolder(this.locatorType, this.locatorStrategy, this.locatorValue);
    }

    public String getId() {
        return id;
    }

    /**
     * Используется в описании цепочки локаторов до элемента
     * @return
     */
    public String getShortDescription() {
        return "";
    }

    /**
     * Выводить в строку локатор
     * @return
     */
    public String toString() {
        return "";
    }

}
