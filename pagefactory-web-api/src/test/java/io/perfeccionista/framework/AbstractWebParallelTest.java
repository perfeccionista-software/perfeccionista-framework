package io.perfeccionista.framework;

import io.perfeccionista.framework.extension.PerfeccionistaExtension;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

@TestInstance(Lifecycle.PER_METHOD)
@Execution(ExecutionMode.CONCURRENT)
@ExtendWith(PerfeccionistaExtension.class)
public abstract class AbstractWebParallelTest {
}
