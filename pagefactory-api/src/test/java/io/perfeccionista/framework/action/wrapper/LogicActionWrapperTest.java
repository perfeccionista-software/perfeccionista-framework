package io.perfeccionista.framework.action.wrapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.UseEnvironmentConfiguration;
import io.perfeccionista.framework.action.wrapper.configuration.TestClassLocalEnvironmentConfiguration;
import io.perfeccionista.framework.extension.PerfeccionistaExtension;

import java.time.Duration;

import static io.perfeccionista.framework.action.wrappers.CheckActionWrapper.runCheck;
import static io.perfeccionista.framework.action.wrappers.LogicActionWrapper.runLogic;

@ExtendWith(PerfeccionistaExtension.class)
@UseEnvironmentConfiguration(TestClassLocalEnvironmentConfiguration.class)
public class LogicActionWrapperTest {

    @Test
    public void test(Environment environment) {
        runLogic(environment, "First logic action in this test",  () -> {
            runCheck(environment, "First check in first logic action", () -> {
                // There is execution code
            });
            runCheck(environment, "Second check in first logic action with custom timeout", () -> {
                // There is execution code
            }, Duration.ofMillis(750));
        });
        // There is execution code without reporting
        runLogic(environment, "Second logic action with custom timeout in this test", () -> {
            runCheck(environment, "First check in second action", () -> {
                // There is execution code
            });
            runCheck(environment, "Second check in second action", () -> {
                // There is execution code
            });
        }, Duration.ofSeconds(30));
        runCheck(environment, "Single unwrapped check action", () -> {
            // There is execution code
        });
    }

}
