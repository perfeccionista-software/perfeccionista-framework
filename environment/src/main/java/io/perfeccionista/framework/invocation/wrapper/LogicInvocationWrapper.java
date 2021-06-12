package io.perfeccionista.framework.invocation.wrapper;

import io.perfeccionista.framework.invocation.InvocationService;
import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.invocation.timeouts.TimeoutsService;
import org.jetbrains.annotations.NotNull;
import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.invocation.runner.InvocationRunner;
import io.perfeccionista.framework.invocation.timeouts.type.LogicTimeout;

import java.time.Duration;
import java.util.function.Supplier;

import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.newInstance;

/**
 * TODO: JavaDoc
 * Пользователи могут самостоятельно создавать свои врапперы чтобы привязать логику выполения к необходимым им таймаутам
 * и реализациям повторяющихся действий
 */
public final class LogicInvocationWrapper implements InvocationWrapper {

    private LogicInvocationWrapper() {
    }

    public static <T> T runLogic(@NotNull final InvocationName name,
                                 @NotNull final Supplier<T> supplier,
                                 @NotNull final Duration timeout) {
        InvocationRunner actionRunner = getLogicActionRunner(Environment.getCurrent());
        return actionRunner.run(Environment.getCurrent(), name, supplier, timeout);
    }

    public static <T> T runLogic(@NotNull final InvocationName name,
                                 @NotNull final Supplier<T> supplier) {
        InvocationRunner actionRunner = getLogicActionRunner(Environment.getCurrent());
        return actionRunner.run(Environment.getCurrent(), name, supplier, getLogicTimeout(Environment.getCurrent()));
    }

    public static void runLogic(@NotNull final InvocationName name,
                                @NotNull final Runnable runnable,
                                @NotNull final Duration timeout) {
        InvocationRunner actionRunner = getLogicActionRunner(Environment.getCurrent());
        actionRunner.run(Environment.getCurrent(), name, (Supplier<Void>) () -> {
            runnable.run();
            return null;
        }, timeout);
    }

    public static void runLogic(@NotNull final InvocationName name,
                                @NotNull final Runnable runnable) {
        InvocationRunner actionRunner = getLogicActionRunner(Environment.getCurrent());
        actionRunner.run(Environment.getCurrent(), name, (Supplier<Void>) () -> {
            runnable.run();
            return null;
        }, getLogicTimeout(Environment.getCurrent()));
    }

    public static <T> T runLogic(@NotNull final Supplier<T> supplier,
                                 @NotNull final Duration timeout) {
        InvocationRunner actionRunner = getLogicActionRunner(Environment.getCurrent());
        return actionRunner.run(Environment.getCurrent(), InvocationName.empty(), supplier, timeout);
    }

    public static <T> T runLogic(@NotNull final Supplier<T> supplier) {
        InvocationRunner actionRunner = getLogicActionRunner(Environment.getCurrent());
        return actionRunner.run(Environment.getCurrent(), null, supplier, getLogicTimeout(Environment.getCurrent()));
    }

    public static void runLogic(@NotNull final Runnable runnable,
                                @NotNull final Duration timeout) {
        InvocationRunner actionRunner = getLogicActionRunner(Environment.getCurrent());
        actionRunner.run(Environment.getCurrent(), InvocationName.empty(), (Supplier<Void>) () -> {
            runnable.run();
            return null;
        }, timeout);
    }

    public static void runLogic(@NotNull final Runnable runnable) {
        InvocationRunner actionRunner = getLogicActionRunner(Environment.getCurrent());
        actionRunner.run(Environment.getCurrent(), InvocationName.empty(), (Supplier<Void>) () -> {
            runnable.run();
            return null;
        }, getLogicTimeout(Environment.getCurrent()));
    }

    // TODO: Не создавать каждый раз заново
    private static InvocationRunner getLogicActionRunner(final Environment environment) {
        Class<? extends InvocationRunner> actionImplementation = environment.getService(InvocationService.class)
                .getInvocationRunnerImplementation(LogicInvocationWrapper.class);
        return newInstance(actionImplementation);
    }

    private static Duration getLogicTimeout(Environment environment) {
        return environment.getService(TimeoutsService.class).getTimeout(LogicTimeout.class);
    }
}
