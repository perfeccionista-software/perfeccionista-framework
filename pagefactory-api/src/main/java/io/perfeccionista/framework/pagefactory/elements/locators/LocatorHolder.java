package io.perfeccionista.framework.pagefactory.elements.locators;

import java.util.Collection;
import java.util.UUID;

public class LocatorHolder {

    private final String id;

    public LocatorHolder() {
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
        return new LocatorHolder();
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
