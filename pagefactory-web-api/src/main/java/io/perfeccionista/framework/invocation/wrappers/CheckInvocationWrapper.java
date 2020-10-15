package io.perfeccionista.framework.invocation.wrappers;

import io.perfeccionista.framework.invocation.runner.InvocationName;
import org.jetbrains.annotations.NotNull;
import org.junit.platform.commons.util.ReflectionUtils;
import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.invocation.runner.InvocationRunner;
import io.perfeccionista.framework.invocation.timeouts.CheckTimeout;
import io.perfeccionista.framework.invocation.timeouts.Timeouts;

import java.time.Duration;
import java.util.function.Supplier;

public final class CheckInvocationWrapper implements InvocationWrapper {

    private CheckInvocationWrapper() {
    }

    // поставить аннотацию @Shadow над методом и аргументами, которые нужно выделять/затемнять
    public static <T> T runCheck(@NotNull Environment environment,
                                 @NotNull InvocationName name,
                                 @NotNull final Supplier<T> supplier,
                                 @NotNull Duration timeout) {
        InvocationRunner actionRunner = getCheckActionRunner(environment);
        return actionRunner.run(environment, name, supplier, timeout);
    }

    public static <T> T runCheck(@NotNull Environment environment,
                                 @NotNull InvocationName name,
                                 @NotNull final Supplier<T> supplier) {
        Duration timeout = getEnvironmentTimeouts(environment).getTimeout(CheckTimeout.class);
        InvocationRunner actionRunner = getCheckActionRunner(environment);
        return actionRunner.run(environment, name, supplier, timeout);
    }

    public static void runCheck(@NotNull Environment environment,
                                @NotNull InvocationName name,
                                @NotNull final Runnable runnable,
                                @NotNull Duration timeout) {
        InvocationRunner actionRunner = getCheckActionRunner(environment);
        actionRunner.run(environment, name, (Supplier<Void>) () -> {
            runnable.run();
            return null;
        }, timeout);
    }

    public static void runCheck(@NotNull Environment environment,
                                @NotNull InvocationName name,
                                @NotNull final Runnable runnable) {
        Duration timeout = getEnvironmentTimeouts(environment).getTimeout(CheckTimeout.class);
        InvocationRunner actionRunner = getCheckActionRunner(environment);
        actionRunner.run(environment, name, (Supplier<Void>) () -> {
            runnable.run();
            return null;
        }, timeout);
    }

    public static <T> T runCheck(@NotNull Environment environment,
                                 @NotNull final Supplier<T> supplier,
                                 @NotNull Duration timeout) {
        InvocationRunner actionRunner = getCheckActionRunner(environment);
        return actionRunner.run(environment, InvocationName.empty(), supplier, timeout);
    }

    public static <T> T runCheck(@NotNull Environment environment,
                                 @NotNull final Supplier<T> supplier) {
        Duration timeout = getEnvironmentTimeouts(environment).getTimeout(CheckTimeout.class);
        InvocationRunner actionRunner = getCheckActionRunner(environment);
        return actionRunner.run(environment, InvocationName.empty(), supplier, timeout);
    }

    public static void runCheck(@NotNull Environment environment,
                                @NotNull final Runnable runnable,
                                @NotNull Duration timeout) {
        InvocationRunner actionRunner = getCheckActionRunner(environment);
        actionRunner.run(environment, InvocationName.empty(), (Supplier<Void>) () -> {
            runnable.run();
            return null;
        }, timeout);
    }

    public static void runCheck(@NotNull Environment environment,
                                @NotNull final Runnable runnable) {
        Duration timeout = getEnvironmentTimeouts(environment).getTimeout(CheckTimeout.class);
        InvocationRunner actionRunner = getCheckActionRunner(environment);
        actionRunner.run(environment, InvocationName.empty(), (Supplier<Void>) () -> {
            runnable.run();
            return null;
        }, timeout);
    }

    // TODO: Не создавать каждый раз заново
    private static InvocationRunner getCheckActionRunner(Environment environment) {
        Class<? extends InvocationRunner> checkActionImplementation = environment.getEnvironmentConfiguration()
                .getInvocationRunnerConfiguration()
                .getInvocationRunnerImplementation(CheckInvocationWrapper.class);
        return ReflectionUtils.newInstance(checkActionImplementation);
    }

    private static Timeouts getEnvironmentTimeouts(Environment environment) {
        return environment.getEnvironmentConfiguration().getTimeouts();
    }

}
