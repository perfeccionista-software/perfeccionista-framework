package io.perfeccionista.framework.exceptions.base;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * TODO: Implement logic
 * TODO: JavaDoc
 */
public class PerfeccionistaExceptionSequence extends PerfeccionistaRuntimeException {

    private static final long serialVersionUID = 1L;

    protected final Deque<PerfeccionistaException> exceptions;

    public PerfeccionistaExceptionSequence(@NotNull Deque<PerfeccionistaException> exceptions, @NotNull String exceptionMessage) {
        super(exceptionMessage);
        this.exceptions = new ArrayDeque<>(exceptions);
    }

    /**
     * TODO: JavaDoc
     * @return
     */
    @NotNull
    public Deque<PerfeccionistaException> getCollectedExceptions() {
        return new ArrayDeque<>(exceptions);
    }

}
