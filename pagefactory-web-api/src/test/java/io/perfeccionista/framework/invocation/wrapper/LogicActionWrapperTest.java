package io.perfeccionista.framework.invocation.wrapper;

import io.perfeccionista.framework.invocation.runner.InvocationName;
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
        runLogic(environment, InvocationName.of("First logic action in this test"),  () -> {
            runCheck(environment, InvocationName.of("First check in first logic action"), () -> {
                // There is execution code
            });
            runCheck(environment, InvocationName.of("Second check in first logic action with custom timeout"), () -> {
                // There is execution code
            }, Duration.ofMillis(750));
        });
        // There is execution code without reporting
        runLogic(environment, InvocationName.of("Second logic action with custom timeout in this test"), () -> {
            runCheck(environment, InvocationName.of("First check in second action"), () -> {
                // There is execution code
            });
            runCheck(environment, InvocationName.of("Second check in second action"), () -> {
                // There is execution code
            });
        }, Duration.ofSeconds(30));
        runCheck(environment, InvocationName.of("Single unwrapped check action"), () -> {
            // There is execution code
        });
    }

}
