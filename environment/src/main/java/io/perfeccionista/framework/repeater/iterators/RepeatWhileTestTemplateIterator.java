package io.perfeccionista.framework.repeater.iterators;

import org.junit.jupiter.api.extension.TestTemplateInvocationContext;
import io.perfeccionista.framework.extension.PerfeccionistaExtension;
import io.perfeccionista.framework.repeater.policy.RepeatPolicy;
import io.perfeccionista.framework.repeater.RepeaterInvocationContext;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RepeatWhileTestTemplateIterator implements Iterator<TestTemplateInvocationContext> {

    private final PerfeccionistaExtension extension;
    private final RepeatPolicy repeatPolicy;
    private final String displayName;
    private final Method method;

    private int currentIndex = 0;

    public RepeatWhileTestTemplateIterator(PerfeccionistaExtension extension, RepeatPolicy repeatPolicy, String displayName, Method method) {
        this.extension = extension;
        this.repeatPolicy = repeatPolicy;
        this.displayName = displayName;
        this.method = method;
    }

    @Override
    public boolean hasNext() {
        if (currentIndex < repeatPolicy.minAttempt()) {
            return true;
        }
        return currentIndex < repeatPolicy.maxAttempt()
                && !repeatPolicy.getRepeatCondition().testCondition(extension.getThreadLocalTestResults(method));
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
