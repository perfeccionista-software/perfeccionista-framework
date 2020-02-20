package io.perfeccionista.framework.pagefactory.elements.locators;

import java.util.ArrayDeque;
import java.util.Deque;

public class LocatorChain {
    private Deque<LocatorHolder> locatorSequence = new ArrayDeque<>();


    public static LocatorChain of() {
        return new LocatorChain();
    }

    public static LocatorChain of(LocatorHolder locator) {
        return new LocatorChain().addLocator(locator);
    }

    public LocatorChain addLocator(LocatorHolder locator) {

        return this;
    }



    public Deque<LocatorHolder> getLocatorChain() {
        return new ArrayDeque<>();
    }

}
