package io.perfeccionista.framework.action.wrapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.UseEnvironmentConfiguration;
import io.perfeccionista.framework.action.wrapper.configuration.TestClassLocalEnvironmentConfiguration;
import io.perfeccionista.framework.extension.PerfeccionistaExtension;

import java.time.Duration;

import static io.perfeccionista.framework.action.wrappers.CheckActionWrapper.runCheck;

@ExtendWith(PerfeccionistaExtension.class)
@UseEnvironmentConfiguration(TestClassLocalEnvironmentConfiguration.class)
public class CheckActionWrapperTest {

    @Test
    public void test(Environment environment) {
        runCheck(environment, "Simple check", () -> {

        });
        Boolean result = runCheck(environment, "Simple check with return statement", () -> true);
        runCheck(environment, "Single check with custom timeout", () -> {

        }, Duration.ofSeconds(30));
    }

}
