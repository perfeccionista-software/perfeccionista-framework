package io.perfeccionista.framework.invocation.wrapper;

import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.invocation.wrapper.configuration.TestClassLocalEnvironmentConfiguration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.UseEnvironmentConfiguration;
import io.perfeccionista.framework.extension.PerfeccionistaExtension;

import java.time.Duration;

import static io.perfeccionista.framework.invocation.wrappers.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.invocation.wrappers.LogicInvocationWrapper.runLogic;

@ExtendWith(PerfeccionistaExtension.class)
@UseEnvironmentConfiguration(TestClassLocalEnvironmentConfiguration.class)
public class LogicActionWrapperTest {

    @Test
    public void test(Environment environment) {
        runLogic(environment, InvocationName.assertInvocation("First logic action in this test"),  () -> {
            runCheck(environment, InvocationName.assertInvocation("First check in first logic action"), () -> {
                // There is execution code
            });
            runCheck(environment, InvocationName.assertInvocation("Second check in first logic action with custom timeout"), () -> {
                // There is execution code
            }, Duration.ofMillis(750));
        });
        // There is execution code without reporting
        runLogic(environment, InvocationName.assertInvocation("Second logic action with custom timeout in this test"), () -> {
            runCheck(environment, InvocationName.assertInvocation("First check in second action"), () -> {
                // There is execution code
            });
            runCheck(environment, InvocationName.assertInvocation("Second check in second action"), () -> {
                // There is execution code
            });
        }, Duration.ofSeconds(30));
        runCheck(environment, InvocationName.assertInvocation("Single unwrapped check action"), () -> {
            // There is execution code
        });
    }

}
