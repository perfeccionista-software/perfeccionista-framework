package io.perfeccionista.framework.exceptions.base;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * TODO: Implement logic
 * TODO: JavaDoc
 */
public class PerfeccionistaExceptionSequence extends PerfeccionistaRuntimeException {

    private static final long serialVersionUID = 1L;

    protected final List<PerfeccionistaException> exceptions;
    protected final String exceptionMessage;

    public PerfeccionistaExceptionSequence(@NotNull List<PerfeccionistaException> exceptions, @NotNull String exceptionMessage) {
        super(exceptionMessage);
        this.exceptions = new ArrayList<>(exceptions);
        this.exceptionMessage = exceptionMessage;
    }

    /**
     * TODO: JavaDoc
     * @return
     */
    @NotNull
    public Deque<PerfeccionistaException> getCollectedExceptions() {
        return new ArrayDeque<>(exceptions);
    }

    @Override
    public String toString() {
        return this.getClass().getCanonicalName() + ":\n" + exceptionMessage + this.getClass().getCanonicalName();
    }

}
