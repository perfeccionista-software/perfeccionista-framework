package io.perfeccionista.framework.invocation.wrapper;

import io.perfeccionista.framework.invocation.runner.InvocationName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.UseEnvironmentConfiguration;
import io.perfeccionista.framework.invocation.wrapper.configuration.TestClassLocalEnvironmentConfiguration;
import io.perfeccionista.framework.extension.PerfeccionistaExtension;

import java.time.Duration;

import static io.perfeccionista.framework.invocation.wrappers.CheckActionWrapper.runCheck;

@ExtendWith(PerfeccionistaExtension.class)
@UseEnvironmentConfiguration(TestClassLocalEnvironmentConfiguration.class)
public class CheckActionWrapperTest {

    @Test
    public void test(Environment environment) {
        runCheck(environment, InvocationName.of("Simple check"), () -> {

        });
        Boolean result = runCheck(environment, InvocationName.of("Simple check with return statement"), () -> true);
        runCheck(environment, InvocationName.of("Single check with custom timeout"), () -> {

        }, Duration.ofSeconds(30));
    }

}
