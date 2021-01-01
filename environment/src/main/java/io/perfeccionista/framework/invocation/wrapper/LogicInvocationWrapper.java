package io.perfeccionista.framework.invocation.wrapper;

import io.perfeccionista.framework.invocation.InvocationService;
import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.invocation.timeouts.TimeoutsService;
import org.jetbrains.annotations.NotNull;
import org.junit.platform.commons.util.ReflectionUtils;
import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.invocation.runner.InvocationRunner;
import io.perfeccionista.framework.invocation.timeouts.type.LogicTimeout;

import java.time.Duration;
import java.util.function.Supplier;

/**
 * TODO: JavaDoc
 * Пользователи могут самостоятельно создавать свои врапперы чтобы привязать логику выполения к необходимым им таймаутам
 * и реализациям повторяющихся действий
 */
public final class LogicInvocationWrapper implements InvocationWrapper {

    private LogicInvocationWrapper() {
    }

    public static <T> T runLogic(@NotNull final Environment environment,
                                 @NotNull final InvocationName name,
                                 @NotNull final Supplier<T> supplier,
                                 @NotNull final Duration timeout) {
        InvocationRunner actionRunner = getLogicActionRunner(environment);
        return actionRunner.run(environment, name, supplier, timeout);
    }

    public static <T> T runLogic(@NotNull final Environment environment,
                                 @NotNull final InvocationName name,
                                 @NotNull final Supplier<T> supplier) {
        InvocationRunner actionRunner = getLogicActionRunner(environment);
        return actionRunner.run(environment, name, supplier, getLogicTimeout(environment));
    }

    public static void runLogic(@NotNull final Environment environment,
                                @NotNull final InvocationName name,
                                @NotNull final Runnable runnable,
                                @NotNull final Duration timeout) {
        InvocationRunner actionRunner = getLogicActionRunner(environment);
        actionRunner.run(environment, name, (Supplier<Void>) () -> {
            runnable.run();
            return null;
        }, timeout);
    }

    public static void runLogic(@NotNull final Environment environment,
                                @NotNull final InvocationName name,
                                @NotNull final Runnable runnable) {
        InvocationRunner actionRunner = getLogicActionRunner(environment);
        actionRunner.run(environment, name, (Supplier<Void>) () -> {
            runnable.run();
            return null;
        }, getLogicTimeout(environment));
    }

    public static <T> T runLogic(@NotNull final Environment environment,
                                 @NotNull final Supplier<T> supplier,
                                 @NotNull final Duration timeout) {
        InvocationRunner actionRunner = getLogicActionRunner(environment);
        return actionRunner.run(environment, InvocationName.empty(), supplier, timeout);
    }

    public static <T> T runLogic(@NotNull final Environment environment,
                                 @NotNull final Supplier<T> supplier) {
        InvocationRunner actionRunner = getLogicActionRunner(environment);
        return actionRunner.run(environment, null, supplier, getLogicTimeout(environment));
    }

    public static void runLogic(@NotNull final Environment environment,
                                @NotNull final Runnable runnable,
                                @NotNull final Duration timeout) {
        InvocationRunner actionRunner = getLogicActionRunner(environment);
        actionRunner.run(environment, InvocationName.empty(), (Supplier<Void>) () -> {
            runnable.run();
            return null;
        }, timeout);
    }

    public static void runLogic(@NotNull final Environment environment,
                                @NotNull final Runnable runnable) {
        InvocationRunner actionRunner = getLogicActionRunner(environment);
        actionRunner.run(environment, InvocationName.empty(), (Supplier<Void>) () -> {
            runnable.run();
            return null;
        }, getLogicTimeout(environment));
    }

    // TODO: Не создавать каждый раз заново
    private static InvocationRunner getLogicActionRunner(final Environment environment) {
        Class<? extends InvocationRunner> actionImplementation = environment.getService(InvocationService.class)
                .getInvocationRunnerImplementation(LogicInvocationWrapper.class);
        return ReflectionUtils.newInstance(actionImplementation);
    }

    private static Duration getLogicTimeout(Environment environment) {
        return environment.getService(TimeoutsService.class).getTimeout(LogicTimeout.class);
    }
}