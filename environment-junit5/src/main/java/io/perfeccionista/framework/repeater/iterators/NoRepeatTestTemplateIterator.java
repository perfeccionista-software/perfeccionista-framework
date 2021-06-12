package io.perfeccionista.framework.repeater.iterators;

import org.junit.jupiter.api.extension.TestTemplateInvocationContext;
import io.perfeccionista.framework.repeater.RepeaterInvocationContext;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class NoRepeatTestTemplateIterator implements Iterator<TestTemplateInvocationContext> {

    private final String displayName;

    int currentIndex = 0;

    public NoRepeatTestTemplateIterator(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public boolean hasNext() {
        return currentIndex < 1;
    }

    @Override
    public TestTemplateInvocationContext next() {
        if (hasNext()) {
            return new RepeaterInvocationContext(displayName, ++currentIndex);
        }
        throw new NoSuchElementException();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

}
