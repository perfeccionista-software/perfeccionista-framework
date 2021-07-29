package io.perfeccionista.framework.invocation.wrapper;

import io.perfeccionista.framework.AbstractWebParallelTest;
import io.perfeccionista.framework.invocation.runner.InvocationInfo;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static io.perfeccionista.framework.invocation.wrapper.CheckInvocationWrapper.runCheck;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * TODO: Implement checks
 */
class CheckActionWrapperTest extends AbstractWebParallelTest {

    @Test
    void test() {
        runCheck(InvocationInfo.assertInvocation("Simple check"), () -> {

        });
        Boolean result = runCheck(InvocationInfo.assertInvocation("Simple check with return statement"), () -> true);
        runCheck(InvocationInfo.assertInvocation("Single check with custom timeout"), () -> {

        }, Duration.ofSeconds(30));
        assertTrue(result);
    }

}
