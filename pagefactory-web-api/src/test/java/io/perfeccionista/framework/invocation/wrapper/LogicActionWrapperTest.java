package io.perfeccionista.framework.invocation.wrapper;

import io.perfeccionista.framework.AbstractWebParallelTest;
import io.perfeccionista.framework.invocation.runner.InvocationInfo;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static io.perfeccionista.framework.invocation.wrapper.MultipleAttemptInvocationWrapper.repeatInvocation;

/**
 * TODO: Implement checks
 */
class LogicActionWrapperTest extends AbstractWebParallelTest {

    @Test
    void test() {
        repeatInvocation(InvocationInfo.assertInvocation("First logic action in this test"),  () -> {
            repeatInvocation(InvocationInfo.assertInvocation("First check in first logic action"), () -> {
                // There is execution code
            });
            repeatInvocation(InvocationInfo.assertInvocation("Second check in first logic action with custom timeout"), () -> {
                // There is execution code
            }, Duration.ofMillis(750));
        }, Duration.ofSeconds(5));
        // There is execution code without reporting
        repeatInvocation(InvocationInfo.assertInvocation("Second logic action with custom timeout in this test"), () -> {
            repeatInvocation(InvocationInfo.assertInvocation("First check in second action"), () -> {
                // There is execution code
            });
            repeatInvocation(InvocationInfo.assertInvocation("Second check in second action"), () -> {
                // There is execution code
            });
        }, Duration.ofSeconds(30));
        repeatInvocation(InvocationInfo.assertInvocation("Single unwrapped check action"), () -> {
            // There is execution code
        });
    }

}
