package io.perfeccionista.framework.invocation.wrapper;

import io.perfeccionista.framework.invocation.InvocationService;
import io.perfeccionista.framework.invocation.runner.InvocationInfo;
import io.perfeccionista.framework.invocation.timeouts.TimeoutsService;
import org.jetbrains.annotations.NotNull;
import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.invocation.runner.InvocationRunner;
import io.perfeccionista.framework.invocation.timeouts.type.CheckTimeout;

import java.time.Duration;
import java.util.function.Supplier;

import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.newInstance;

public final class CheckInvocationWrapper implements InvocationWrapper {

    private CheckInvocationWrapper() {
    }

    // поставить аннотацию @Shadow над методом и аргументами, которые нужно выделять/затемнять
    public static <T> T runCheck(@NotNull final InvocationInfo name,
                                 @NotNull final Supplier<T> supplier,
                                 @NotNull final Duration timeout) {
        InvocationRunner actionRunner = getCheckActionRunner(Environment.getCurrent());
        return actionRunner.run(Environment.getCurrent(), name, supplier, timeout);
    }

    public static <T> T runCheck(@NotNull final InvocationInfo name,
                                 @NotNull final Supplier<T> supplier) {
        InvocationRunner actionRunner = getCheckActionRunner(Environment.getCurrent());
        return actionRunner.run(Environment.getCurrent(), name, supplier, getCheckTimeout(Environment.getCurrent()));
    }

    public static void runCheck(@NotNull final InvocationInfo name,
                                @NotNull final Runnable runnable,
                                @NotNull final Duration timeout) {
        InvocationRunner actionRunner = getCheckActionRunner(Environment.getCurrent());
        actionRunner.run(Environment.getCurrent(), name, (Supplier<Void>) () -> {
            runnable.run();
            return null;
        }, timeout);
    }

    public static void runCheck(@NotNull final InvocationInfo name,
                                @NotNull final Runnable runnable) {
        InvocationRunner actionRunner = getCheckActionRunner(Environment.getCurrent());
        actionRunner.run(Environment.getCurrent(), name, (Supplier<Void>) () -> {
            runnable.run();
            return null;
        }, getCheckTimeout(Environment.getCurrent()));
    }

    public static <T> T runCheck(@NotNull final Supplier<T> supplier,
                                 @NotNull final Duration timeout) {
        InvocationRunner actionRunner = getCheckActionRunner(Environment.getCurrent());
        return actionRunner.run(Environment.getCurrent(), InvocationInfo.empty(), supplier, timeout);
    }

    public static <T> T runCheck(@NotNull final Supplier<T> supplier) {
        InvocationRunner actionRunner = getCheckActionRunner(Environment.getCurrent());
        return actionRunner.run(Environment.getCurrent(), InvocationInfo.empty(), supplier, getCheckTimeout(Environment.getCurrent()));
    }

    public static void runCheck(@NotNull final Runnable runnable,
                                @NotNull final Duration timeout) {
        InvocationRunner actionRunner = getCheckActionRunner(Environment.getCurrent());
        actionRunner.run(Environment.getCurrent(), InvocationInfo.empty(), (Supplier<Void>) () -> {
            runnable.run();
            return null;
        }, timeout);
    }

    public static void runCheck(@NotNull final Runnable runnable) {
        InvocationRunner actionRunner = getCheckActionRunner(Environment.getCurrent());
        actionRunner.run(Environment.getCurrent(), InvocationInfo.empty(), (Supplier<Void>) () -> {
            runnable.run();
            return null;
        }, getCheckTimeout(Environment.getCurrent()));
    }

    // TODO: Не создавать каждый раз заново
    private static InvocationRunner getCheckActionRunner(Environment environment) {
        Class<? extends InvocationRunner> checkActionImplementation = environment.getService(InvocationService.class)
                .getInvocationRunnerImplementation(CheckInvocationWrapper.class);
        return newInstance(checkActionImplementation);
    }

    private static Duration getCheckTimeout(Environment environment) {
        return environment.getService(TimeoutsService.class).getTimeout(CheckTimeout.class);
    }

}
