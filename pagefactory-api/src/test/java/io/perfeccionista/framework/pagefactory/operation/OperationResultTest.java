package io.perfeccionista.framework.pagefactory.operation;

import io.perfeccionista.framework.SimpleParallelTest;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaException;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

final class OperationResultTest extends SimpleParallelTest {

    private Object result = new Object();
    private PerfeccionistaException exception = new PerfeccionistaException("exception");

    @Test
    void createSuccessOperationResultTest() {
        OperationResult successWithoutException = OperationResult.success(result);
        assertTrue(successWithoutException.isSuccess());
        assertEquals(Optional.of(result), successWithoutException.getResult());
        assertEquals(Optional.empty(), successWithoutException.getException());

        OperationResult successWithException = OperationResult.success(result, exception);
        assertTrue(successWithException.isSuccess());
        assertEquals(Optional.of(result), successWithException.getResult());
        assertEquals(Optional.of(exception), successWithException.getException());
    }

    @Test
    void createFailureOperationResultTest() {
        OperationResult failureWithoutResult = OperationResult.failure(exception);
        assertFalse(failureWithoutResult.isSuccess());
        assertEquals(Optional.empty(), failureWithoutResult.getResult());
        assertEquals(Optional.of(exception), failureWithoutResult.getException());

        OperationResult failureWithResult = OperationResult.failure(result, exception);
        assertFalse(failureWithResult.isSuccess());
        assertEquals(Optional.of(result), failureWithResult.getResult());
        assertEquals(Optional.of(exception), failureWithResult.getException());
    }

}