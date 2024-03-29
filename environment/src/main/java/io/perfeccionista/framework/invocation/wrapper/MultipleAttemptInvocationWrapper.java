package io.perfeccionista.framework.invocation.wrapper;

import io.perfeccionista.framework.invocation.InvocationService;
import io.perfeccionista.framework.invocation.runner.InvocationInfo;
import io.perfeccionista.framework.invocation.timeouts.TimeoutsService;
import org.jetbrains.annotations.NotNull;
import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.invocation.runner.InvocationRunner;
import io.perfeccionista.framework.invocation.timeouts.type.RepeatInvocationTimeout;

import java.time.Duration;
import java.util.function.Supplier;

import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.newInstance;

public final class MultipleAttemptInvocationWrapper implements InvocationWrapper {

    private MultipleAttemptInvocationWrapper() {
    }

    // поставить аннотацию @Shadow над методом и аргументами, которые нужно выделять/затемнять
    public static <T> T repeatInvocation(@NotNull final InvocationInfo name,
                                         @NotNull final Supplier<T> supplier,
                                         @NotNull final Duration timeout) {
        InvocationRunner actionRunner = getCheckActionRunner(Environment.getForCurrentThread());
        return actionRunner.run(Environment.getForCurrentThread(), name, supplier, timeout);
    }

    public static <T> T repeatInvocation(@NotNull final InvocationInfo name,
                                         @NotNull final Supplier<T> supplier) {
        InvocationRunner actionRunner = getCheckActionRunner(Environment.getForCurrentThread());
        return actionRunner.run(Environment.getForCurrentThread(), name, supplier, getCheckTimeout(Environment.getForCurrentThread()));
    }

    public static void repeatInvocation(@NotNull final InvocationInfo name,
                                        @NotNull final Runnable runnable,
                                        @NotNull final Duration timeout) {
        InvocationRunner actionRunner = getCheckActionRunner(Environment.getForCurrentThread());
        actionRunner.run(Environment.getForCurrentThread(), name, (Supplier<Void>) () -> {
            runnable.run();
            return null;
        }, timeout);
    }

    public static void repeatInvocation(@NotNull final InvocationInfo name,
                                        @NotNull final Runnable runnable) {
        InvocationRunner actionRunner = getCheckActionRunner(Environment.getForCurrentThread());
        actionRunner.run(Environment.getForCurrentThread(), name, (Supplier<Void>) () -> {
            runnable.run();
            return null;
        }, getCheckTimeout(Environment.getForCurrentThread()));
    }

    public static <T> T repeatInvocation(@NotNull final Supplier<T> supplier,
                                         @NotNull final Duration timeout) {
        InvocationRunner actionRunner = getCheckActionRunner(Environment.getForCurrentThread());
        return actionRunner.run(Environment.getForCurrentThread(), InvocationInfo.empty(), supplier, timeout);
    }

    public static <T> T repeatInvocation(@NotNull final Supplier<T> supplier) {
        InvocationRunner actionRunner = getCheckActionRunner(Environment.getForCurrentThread());
        return actionRunner.run(Environment.getForCurrentThread(), InvocationInfo.empty(), supplier, getCheckTimeout(Environment.getForCurrentThread()));
    }

    public static void repeatInvocation(@NotNull final Runnable runnable,
                                        @NotNull final Duration timeout) {
        InvocationRunner actionRunner = getCheckActionRunner(Environment.getForCurrentThread());
        actionRunner.run(Environment.getForCurrentThread(), InvocationInfo.empty(), (Supplier<Void>) () -> {
            runnable.run();
            return null;
        }, timeout);
    }

    public static void repeatInvocation(@NotNull final Runnable runnable) {
        InvocationRunner actionRunner = getCheckActionRunner(Environment.getForCurrentThread());
        actionRunner.run(Environment.getForCurrentThread(), InvocationInfo.empty(), (Supplier<Void>) () -> {
            runnable.run();
            return null;
        }, getCheckTimeout(Environment.getForCurrentThread()));
    }

    // TODO: Не создавать каждый раз заново
    private static InvocationRunner getCheckActionRunner(Environment environment) {
        Class<? extends InvocationRunner> checkActionImplementation = environment.getService(InvocationService.class)
                .getInvocationRunnerImplementation(MultipleAttemptInvocationWrapper.class);
        return newInstance(checkActionImplementation);
    }

    private static Duration getCheckTimeout(Environment environment) {
        return environment.getService(TimeoutsService.class).getTimeout(RepeatInvocationTimeout.class);
    }

}
