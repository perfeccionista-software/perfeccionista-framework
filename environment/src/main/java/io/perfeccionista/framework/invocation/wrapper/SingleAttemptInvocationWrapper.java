package io.perfeccionista.framework.invocation.wrapper;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.invocation.InvocationService;
import io.perfeccionista.framework.invocation.runner.InvocationInfo;
import io.perfeccionista.framework.invocation.runner.InvocationRunner;
import io.perfeccionista.framework.invocation.timeouts.TimeoutsService;
import io.perfeccionista.framework.invocation.timeouts.type.RunInvocationTimeout;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;
import java.util.function.Supplier;

import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.newInstance;

public final class SingleAttemptInvocationWrapper implements InvocationWrapper {

    private SingleAttemptInvocationWrapper() {
    }

    // поставить аннотацию @Shadow над методом и аргументами, которые нужно выделять/затемнять
    public static <T> T runInvocation(@NotNull final InvocationInfo name,
                                      @NotNull final Supplier<T> supplier,
                                      @NotNull final Duration timeout) {
        InvocationRunner actionRunner = getSingleActionRunner(Environment.getForCurrentThread());
        return actionRunner.run(Environment.getForCurrentThread(), name, supplier, timeout);
    }

    public static <T> T runInvocation(@NotNull final InvocationInfo name,
                                      @NotNull final Supplier<T> supplier) {
        InvocationRunner actionRunner = getSingleActionRunner(Environment.getForCurrentThread());
        return actionRunner.run(Environment.getForCurrentThread(), name, supplier, getCheckTimeout(Environment.getForCurrentThread()));
    }

    public static void runInvocation(@NotNull final InvocationInfo name,
                                     @NotNull final Runnable runnable,
                                     @NotNull final Duration timeout) {
        InvocationRunner actionRunner = getSingleActionRunner(Environment.getForCurrentThread());
        actionRunner.run(Environment.getForCurrentThread(), name, (Supplier<Void>) () -> {
            runnable.run();
            return null;
        }, timeout);
    }

    public static void runInvocation(@NotNull final InvocationInfo name,
                                     @NotNull final Runnable runnable) {
        InvocationRunner actionRunner = getSingleActionRunner(Environment.getForCurrentThread());
        actionRunner.run(Environment.getForCurrentThread(), name, (Supplier<Void>) () -> {
            runnable.run();
            return null;
        }, getCheckTimeout(Environment.getForCurrentThread()));
    }

    public static <T> T runInvocation(@NotNull final Supplier<T> supplier,
                                      @NotNull final Duration timeout) {
        InvocationRunner actionRunner = getSingleActionRunner(Environment.getForCurrentThread());
        return actionRunner.run(Environment.getForCurrentThread(), InvocationInfo.empty(), supplier, timeout);
    }

    public static <T> T runInvocation(@NotNull final Supplier<T> supplier) {
        InvocationRunner actionRunner = getSingleActionRunner(Environment.getForCurrentThread());
        return actionRunner.run(Environment.getForCurrentThread(), InvocationInfo.empty(), supplier, getCheckTimeout(Environment.getForCurrentThread()));
    }

    public static void runInvocation(@NotNull final Runnable runnable,
                                     @NotNull final Duration timeout) {
        InvocationRunner actionRunner = getSingleActionRunner(Environment.getForCurrentThread());
        actionRunner.run(Environment.getForCurrentThread(), InvocationInfo.empty(), (Supplier<Void>) () -> {
            runnable.run();
            return null;
        }, timeout);
    }

    public static void runInvocation(@NotNull final Runnable runnable) {
        InvocationRunner actionRunner = getSingleActionRunner(Environment.getForCurrentThread());
        actionRunner.run(Environment.getForCurrentThread(), InvocationInfo.empty(), (Supplier<Void>) () -> {
            runnable.run();
            return null;
        }, getCheckTimeout(Environment.getForCurrentThread()));
    }

    // TODO: Не создавать каждый раз заново
    private static InvocationRunner getSingleActionRunner(Environment environment) {
        Class<? extends InvocationRunner> singleActionImplementation = environment.getService(InvocationService.class)
                .getInvocationRunnerImplementation(SingleAttemptInvocationWrapper.class);
        return newInstance(singleActionImplementation);
    }

    private static Duration getCheckTimeout(Environment environment) {
        // Для операций, которые могут зависнутьт используется таймаут такой же как и для проверки
        return environment.getService(TimeoutsService.class).getTimeout(RunInvocationTimeout.class);
    }

}

