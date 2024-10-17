package io.perfeccionista.framework.repeater.iterators;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestTemplateInvocationContext;
import io.perfeccionista.framework.extension.PerfeccionistaExtension;
import io.perfeccionista.framework.repeater.policy.RepeatPolicy;
import io.perfeccionista.framework.repeater.RepeaterInvocationContext;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * TODO: JavaDoc
 */
public class RepeatIfTestTemplateIterator implements Iterator<TestTemplateInvocationContext> {

    private final PerfeccionistaExtension extension;
    private final RepeatPolicy repeatPolicy;
    private final ExtensionContext context;

    private int currentIndex = 0;

    public RepeatIfTestTemplateIterator(PerfeccionistaExtension extension, RepeatPolicy repeatPolicy, ExtensionContext context) {
        this.extension = extension;
        this.repeatPolicy = repeatPolicy;
        this.context = context;
    }

    @Override
    public boolean hasNext() {
        if (currentIndex < repeatPolicy.minAttempt()) {
            return true;
        }
        return currentIndex < repeatPolicy.maxAttempt()
                && repeatPolicy.getRepeatCondition().testCondition(extension.getTestResults(context));
    }

    @Override
    public TestTemplateInvocationContext next() {
        if (hasNext()) {
            return new RepeaterInvocationContext(context.getDisplayName(), ++currentIndex);
        }
        throw new NoSuchElementException();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

}
