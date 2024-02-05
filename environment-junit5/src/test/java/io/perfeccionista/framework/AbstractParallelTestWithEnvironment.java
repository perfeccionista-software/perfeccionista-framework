package io.perfeccionista.framework;

import io.perfeccionista.framework.extension.PerfeccionistaExtension;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(PerfeccionistaExtension.class)
public abstract class AbstractParallelTestWithEnvironment extends AbstractParallelTest {
}
