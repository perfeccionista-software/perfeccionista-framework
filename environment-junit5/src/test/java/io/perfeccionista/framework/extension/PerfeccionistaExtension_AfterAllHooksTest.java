package io.perfeccionista.framework.extension;

import io.perfeccionista.framework.AbstractParallelTestWithEnvironment;
import io.perfeccionista.framework.Environment;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

public class PerfeccionistaExtension_AfterAllHooksTest extends AbstractParallelTestWithEnvironment {

    @Test
    void afterAllHooksTest() {
        Environment.addAfterAllHook(() -> System.out.println("After All Hook output"));
    }

    @AfterAll
    static void tearDown() {
        System.out.println("After All Method execution");
    }

}
