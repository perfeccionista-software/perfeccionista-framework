package io.perfeccionista.framework.exceptions.base;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import io.perfeccionista.framework.SimpleParallelTest;

import java.util.ArrayList;
import java.util.List;

final class PerfeccionistaExceptionSequenceTest extends SimpleParallelTest {

    @Test
    void initializationFailedTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new PerfeccionistaExceptionSequence(null, "message"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new PerfeccionistaExceptionSequence(new ArrayList<>(), null));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new PerfeccionistaExceptionSequence(null, null));
    }

    @Test
    void initializationSuccessTest() {
        List<PerfeccionistaException> exceptions = new ArrayList<>();
        exceptions.add(new PerfeccionistaRuntimeException("message 1"));
        exceptions.add(new PerfeccionistaRuntimeException("message 2"));
        PerfeccionistaExceptionSequence exceptionSequence = new PerfeccionistaExceptionSequence(exceptions, "Exception sequence message");
        Assertions.assertArrayEquals(exceptions.toArray(), exceptionSequence.getCollectedExceptions().toArray());
    }

}
