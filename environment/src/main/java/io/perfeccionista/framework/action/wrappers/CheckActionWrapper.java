package io.perfeccionista.framework.action.wrappers;

import org.jetbrains.annotations.NotNull;
import org.junit.platform.commons.util.ReflectionUtils;
import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.action.runner.ActionRunner;
import io.perfeccionista.framework.action.timeouts.CheckTimeout;
import io.perfeccionista.framework.action.timeouts.Timeouts;

import java.time.Duration;
import java.util.function.Supplier;

public final class CheckActionWrapper implements ActionWrapper {

    private CheckActionWrapper() {
    }

    // поставить аннотацию @Shadow над методом и агрументами, которые нужно выделять/затемнять
    public static <T> T runCheck(@NotNull Environment environment,
                                 @NotNull String name,
                                 @NotNull final Supplier<T> supplier,
                                 @NotNull Duration timeout) {
        ActionRunner actionRunner = getCheckActionRunner(environment);
        return actionRunner.run(environment, name, supplier, timeout);
    }

    public static <T> T runCheck(@NotNull Environment environment,
                                 @NotNull String name,
                                 @NotNull final Supplier<T> supplier) {
        Duration timeout = getEnvironmentTimeouts(environment).getTimeout(CheckTimeout.class);
        ActionRunner actionRunner = getCheckActionRunner(environment);
        return actionRunner.run(environment, name, supplier, timeout);
    }

    public static void runCheck(@NotNull Environment environment,
                                @NotNull String name,
                                @NotNull final Runnable runnable,
                                @NotNull Duration timeout) {
        ActionRunner actionRunner = getCheckActionRunner(environment);
        actionRunner.run(environment, name, (Supplier<Void>) () -> {
            runnable.run();
            return null;
        }, timeout);
    }

    public static void runCheck(@NotNull Environment environment,
                                @NotNull String name,
                                @NotNull final Runnable runnable) {
        Duration timeout = getEnvironmentTimeouts(environment).getTimeout(CheckTimeout.class);
        ActionRunner actionRunner = getCheckActionRunner(environment);
        actionRunner.run(environment, name, (Supplier<Void>) () -> {
            runnable.run();
            return null;
        }, timeout);
    }

    public static <T> T runCheck(@NotNull Environment environment,
                                 @NotNull final Supplier<T> supplier,
                                 @NotNull Duration timeout) {
        ActionRunner actionRunner = getCheckActionRunner(environment);
        return actionRunner.run(environment, null, supplier, timeout);
    }

    public static <T> T runCheck(@NotNull Environment environment,
                                 @NotNull final Supplier<T> supplier) {
        Duration timeout = getEnvironmentTimeouts(environment).getTimeout(CheckTimeout.class);
        ActionRunner actionRunner = getCheckActionRunner(environment);
        return actionRunner.run(environment, null, supplier, timeout);
    }

    public static void runCheck(@NotNull Environment environment,
                                @NotNull final Runnable runnable,
                                @NotNull Duration timeout) {
        ActionRunner actionRunner = getCheckActionRunner(environment);
        actionRunner.run(environment, null, (Supplier<Void>) () -> {
            runnable.run();
            return null;
        }, timeout);
    }

    public static void runCheck(@NotNull Environment environment,
                                @NotNull final Runnable runnable) {
        Duration timeout = getEnvironmentTimeouts(environment).getTimeout(CheckTimeout.class);
        ActionRunner actionRunner = getCheckActionRunner(environment);
        actionRunner.run(environment, null, (Supplier<Void>) () -> {
            runnable.run();
            return null;
        }, timeout);
    }

    // TODO: Не создавать каждый раз заново
    private static ActionRunner getCheckActionRunner(Environment environment) {
        Class<? extends ActionRunner> checkActionImplementation = environment.getEnvironmentConfiguration()
                .getActionRunnerConfiguration()
                .getActionRunnerImplementation(CheckActionWrapper.class);
        return ReflectionUtils.newInstance(checkActionImplementation);
    }

    private static Timeouts getEnvironmentTimeouts(Environment environment) {
        return environment.getEnvironmentConfiguration().getTimeouts();
    }

}
