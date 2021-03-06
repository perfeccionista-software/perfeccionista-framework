package io.perfeccionista.framework.invocation.wrapper;

import io.perfeccionista.framework.AbstractWebParallelTest;
import io.perfeccionista.framework.invocation.runner.InvocationInfo;
import org.junit.jupiter.api.Test;
import io.perfeccionista.framework.Environment;

import java.time.Duration;

import static io.perfeccionista.framework.invocation.wrapper.CheckInvocationWrapper.runCheck;

/**
 * TODO: Implement checks
 */
class CheckActionWrapperTest extends AbstractWebParallelTest {

    @Test
    void test(Environment environment) {
        runCheck(environment, InvocationInfo.assertInvocation("Simple check"), () -> {

        });
        Boolean result = runCheck(environment, InvocationInfo.assertInvocation("Simple check with return statement"), () -> true);
        runCheck(environment, InvocationInfo.assertInvocation("Single check with custom timeout"), () -> {

        }, Duration.ofSeconds(30));
    }

}
