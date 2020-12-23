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

@ExtendWith(PerfeccionistaExtension.class)
@UseEnvironmentConfiguration(TestClassLocalEnvironmentConfiguration.class)
public class CheckActionWrapperTest {

    @Test
    public void test(Environment environment) {
        runCheck(environment, InvocationName.assertInvocation("Simple check"), () -> {

        });
        Boolean result = runCheck(environment, InvocationName.assertInvocation("Simple check with return statement"), () -> true);
        runCheck(environment, InvocationName.assertInvocation("Single check with custom timeout"), () -> {

        }, Duration.ofSeconds(30));
    }

}
