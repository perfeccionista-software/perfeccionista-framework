package io.perfeccionista.framework.pagefactory.operations;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Optional;

public class JsOperationResult<T> extends OperationResult<T> {

    protected final Map<String, String> nodesHash;

    protected JsOperationResult(boolean success, T result, PerfeccionistaException exception, Map<String, String> nodesHash) {
        super(success, result, exception);
        this.nodesHash = nodesHash;
    }

    public Optional<String> getNodeHash(String locatorId) {
        return Optional.ofNullable(nodesHash.get(locatorId));
    }

    public static <T> JsOperationResult<T> success(@Nullable T result,
                                                   @NotNull Map<String, String> nodesHash) {
        return new JsOperationResult<>(true, result, null, nodesHash);
    }

    public static <T> JsOperationResult<T> success(@Nullable T result,
                                                   @Nullable PerfeccionistaException exception,
                                                   @NotNull Map<String, String> nodesHash) {
        return new JsOperationResult<>(true, result, exception, nodesHash);
    }

    public static <T> JsOperationResult<T> failure(@Nullable PerfeccionistaException exception,
                                                   @NotNull Map<String, String> nodesHash) {
        return new JsOperationResult<>(false, null, exception, nodesHash);
    }

    public static <T> JsOperationResult<T> failure(@Nullable T result,
                                                   @Nullable PerfeccionistaException exception,
                                                   @NotNull Map<String, String> nodesHash) {
        return new JsOperationResult<>(false, result, exception, nodesHash);
    }

}
