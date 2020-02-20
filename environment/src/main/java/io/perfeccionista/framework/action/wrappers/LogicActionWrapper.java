package io.perfeccionista.framework.action.wrappers;

import org.jetbrains.annotations.NotNull;
import org.junit.platform.commons.util.ReflectionUtils;
import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.action.runner.ActionRunner;
import io.perfeccionista.framework.action.timeouts.LogicTimeout;
import io.perfeccionista.framework.action.timeouts.Timeouts;

import java.time.Duration;
import java.util.function.Supplier;

/**
 * TODO: JavaDoc
 * Пользователи могут самостоятельно создавать свои врапперы чтобы привязать логику выполения к необходимым им таймаутам
 * и реализациям повторяющихся действий
 */
public final class LogicActionWrapper implements ActionWrapper {

    private LogicActionWrapper() {
    }

    public static <T> T runLogic(@NotNull Environment environment,
                                 @NotNull String name,
                                 @NotNull final Supplier<T> supplier,
                                 @NotNull Duration timeout) {
        ActionRunner actionRunner = getLogicActionRunner(environment);
        return actionRunner.run(environment, name, supplier, timeout);
    }

    public static <T> T runLogic(@NotNull Environment environment,
                                 @NotNull String name,
                                 @NotNull final Supplier<T> supplier) {
        Duration timeout = getEnvironmentTimeouts(environment).getTimeout(LogicTimeout.class);
        ActionRunner actionRunner = getLogicActionRunner(environment);
        return actionRunner.run(environment, name, supplier, timeout);
    }

    public static void runLogic(@NotNull Environment environment,
                                @NotNull String name,
                                @NotNull final Runnable runnable,
                                @NotNull Duration timeout) {
        ActionRunner actionRunner = getLogicActionRunner(environment);
        actionRunner.run(environment, name, (Supplier<Void>) () -> {
            runnable.run();
            return null;
        }, timeout);
    }

    public static void runLogic(@NotNull Environment environment,
                                @NotNull String name,
                                @NotNull final Runnable runnable) {
        Duration timeout = getEnvironmentTimeouts(environment).getTimeout(LogicTimeout.class);
        ActionRunner actionRunner = getLogicActionRunner(environment);
        actionRunner.run(environment, name, (Supplier<Void>) () -> {
            runnable.run();
            return null;
        }, timeout);
    }

    public static <T> T runLogic(@NotNull Environment environment,
                                 @NotNull final Supplier<T> supplier,
                                 @NotNull Duration timeout) {
        ActionRunner actionRunner = getLogicActionRunner(environment);
        return actionRunner.run(environment, null, supplier, timeout);
    }

    public static <T> T runLogic(@NotNull Environment environment,
                                 @NotNull final Supplier<T> supplier) {
        Duration timeout = getEnvironmentTimeouts(environment).getTimeout(LogicTimeout.class);
        ActionRunner actionRunner = getLogicActionRunner(environment);
        return actionRunner.run(environment, null, supplier, timeout);
    }

    public static void runLogic(@NotNull Environment environment,
                                @NotNull final Runnable runnable,
                                @NotNull Duration timeout) {
        ActionRunner actionRunner = getLogicActionRunner(environment);
        actionRunner.run(environment, null, (Supplier<Void>) () -> {
            runnable.run();
            return null;
        }, timeout);
    }

    public static void runLogic(@NotNull Environment environment,
                                @NotNull final Runnable runnable) {
        Duration timeout = getEnvironmentTimeouts(environment).getTimeout(LogicTimeout.class);
        ActionRunner actionRunner = getLogicActionRunner(environment);
        actionRunner.run(environment, null, (Supplier<Void>) () -> {
            runnable.run();
            return null;
        }, timeout);
    }

    // TODO: Не создавать каждый раз заново
    private static ActionRunner getLogicActionRunner(Environment environment) {
        Class<? extends ActionRunner> actionImplementation = environment.getEnvironmentConfiguration()
                .getActionRunnerConfiguration()
                .getActionRunnerImplementation(LogicActionWrapper.class);
        return ReflectionUtils.newInstance(actionImplementation);
    }

    private static Timeouts getEnvironmentTimeouts(Environment environment) {
        return environment.getEnvironmentConfiguration().getTimeouts();
    }

}
