package io.perfeccionista.framework.invocation.runner;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.Optional;

import static io.perfeccionista.framework.invocation.runner.InvocationResult.InvocationStatus.EXCEPTION;
import static io.perfeccionista.framework.invocation.runner.InvocationResult.InvocationStatus.NEW;
import static io.perfeccionista.framework.invocation.runner.InvocationResult.InvocationStatus.SUCCESS;

public class InvocationResult {

    private LocalDateTime started;
    private LocalDateTime finished;
    private InvocationStatus status;
    private Throwable throwable;

    private InvocationResult(LocalDateTime started) {
        this.started = started;
        this.status = NEW;
    }

    public static InvocationResult start() {
        return new InvocationResult(LocalDateTime.now());
    }

    public InvocationResult success() {
        this.finished = LocalDateTime.now();
        this.status = SUCCESS;
        return this;
    }

    public InvocationResult exception(@NotNull Throwable throwable) {
        this.finished = LocalDateTime.now();
        this.status = EXCEPTION;
        this.throwable = throwable;
        return this;
    }

    public LocalDateTime getStarted() {
        return started;
    }

    public LocalDateTime getFinished() {
        return finished;
    }

    public long getDuration(@NotNull ChronoField chronoField) {
        return this.finished.getLong(chronoField) - this.started.getLong(chronoField);
    }

    public InvocationStatus getStatus() {
        return status;
    }

    public Optional<Throwable> getThrowable() {
        return Optional.ofNullable(this.throwable);
    }

    public enum InvocationStatus {

        NEW,
        SUCCESS,
        EXCEPTION

    }

}
