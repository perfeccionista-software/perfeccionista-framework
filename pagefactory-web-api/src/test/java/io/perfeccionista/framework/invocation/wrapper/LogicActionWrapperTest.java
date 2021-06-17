package io.perfeccionista.framework.invocation.wrapper;

import io.perfeccionista.framework.AbstractWebParallelTest;
import io.perfeccionista.framework.invocation.runner.InvocationInfo;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static io.perfeccionista.framework.invocation.wrapper.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.invocation.wrapper.LogicInvocationWrapper.runLogic;

/**
 * TODO: Implement checks
 */
class LogicActionWrapperTest extends AbstractWebParallelTest {

    @Test
    void test() {
        runLogic(InvocationInfo.assertInvocation("First logic action in this test"),  () -> {
            runCheck(InvocationInfo.assertInvocation("First check in first logic action"), () -> {
                // There is execution code
            });
            runCheck(InvocationInfo.assertInvocation("Second check in first logic action with custom timeout"), () -> {
                // There is execution code
            }, Duration.ofMillis(750));
        });
        // There is execution code without reporting
        runLogic(InvocationInfo.assertInvocation("Second logic action with custom timeout in this test"), () -> {
            runCheck(InvocationInfo.assertInvocation("First check in second action"), () -> {
                // There is execution code
            });
            runCheck(InvocationInfo.assertInvocation("Second check in second action"), () -> {
                // There is execution code
            });
        }, Duration.ofSeconds(30));
        runCheck(InvocationInfo.assertInvocation("Single unwrapped check action"), () -> {
            // There is execution code
        });
    }

}
