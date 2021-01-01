package io.perfeccionista.framework.invocation.wrapper;

import io.perfeccionista.framework.invocation.InvocationService;
import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.invocation.timeouts.TimeoutsService;
import org.jetbrains.annotations.NotNull;
import org.junit.platform.commons.util.ReflectionUtils;
import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.invocation.runner.InvocationRunner;
import io.perfeccionista.framework.invocation.timeouts.type.CheckTimeout;

import java.time.Duration;
import java.util.function.Supplier;

public final class CheckInvocationWrapper implements InvocationWrapper {

    private CheckInvocationWrapper() {
    }

    // поставить аннотацию @Shadow над методом и аргументами, которые нужно выделять/затемнять
    public static <T> T runCheck(@NotNull final Environment environment,
                                 @NotNull final InvocationName name,
                                 @NotNull final Supplier<T> supplier,
                                 @NotNull final Duration timeout) {
        InvocationRunner actionRunner = getCheckActionRunner(environment);
        return actionRunner.run(environment, name, supplier, timeout);
    }

    public static <T> T runCheck(@NotNull final Environment environment,
                                 @NotNull final InvocationName name,
                                 @NotNull final Supplier<T> supplier) {
        InvocationRunner actionRunner = getCheckActionRunner(environment);
        return actionRunner.run(environment, name, supplier, getCheckTimeout(environment));
    }

    public static void runCheck(@NotNull final Environment environment,
                                @NotNull final InvocationName name,
                                @NotNull final Runnable runnable,
                                @NotNull final Duration timeout) {
        InvocationRunner actionRunner = getCheckActionRunner(environment);
        actionRunner.run(environment, name, (Supplier<Void>) () -> {
            runnable.run();
            return null;
        }, timeout);
    }

    public static void runCheck(@NotNull final Environment environment,
                                @NotNull final InvocationName name,
                                @NotNull final Runnable runnable) {
        InvocationRunner actionRunner = getCheckActionRunner(environment);
        actionRunner.run(environment, name, (Supplier<Void>) () -> {
            runnable.run();
            return null;
        }, getCheckTimeout(environment));
    }

    public static <T> T runCheck(@NotNull final Environment environment,
                                 @NotNull final Supplier<T> supplier,
                                 @NotNull final Duration timeout) {
        InvocationRunner actionRunner = getCheckActionRunner(environment);
        return actionRunner.run(environment, InvocationName.empty(), supplier, timeout);
    }

    public static <T> T runCheck(@NotNull final Environment environment,
                                 @NotNull final Supplier<T> supplier) {
        InvocationRunner actionRunner = getCheckActionRunner(environment);
        return actionRunner.run(environment, InvocationName.empty(), supplier, getCheckTimeout(environment));
    }

    public static void runCheck(@NotNull final Environment environment,
                                @NotNull final Runnable runnable,
                                @NotNull final Duration timeout) {
        InvocationRunner actionRunner = getCheckActionRunner(environment);
        actionRunner.run(environment, InvocationName.empty(), (Supplier<Void>) () -> {
            runnable.run();
            return null;
        }, timeout);
    }

    public static void runCheck(@NotNull final Environment environment,
                                @NotNull final Runnable runnable) {
        InvocationRunner actionRunner = getCheckActionRunner(environment);
        actionRunner.run(environment, InvocationName.empty(), (Supplier<Void>) () -> {
            runnable.run();
            return null;
        }, getCheckTimeout(environment));
    }

    // TODO: Не создавать каждый раз заново
    private static InvocationRunner getCheckActionRunner(final Environment environment) {
        Class<? extends InvocationRunner> checkActionImplementation = environment.getService(InvocationService.class)
                .getInvocationRunnerImplementation(CheckInvocationWrapper.class);
        return ReflectionUtils.newInstance(checkActionImplementation);
    }

    private static Duration getCheckTimeout(Environment environment) {
        return environment.getService(TimeoutsService.class).getTimeout(CheckTimeout.class);
    }

}
