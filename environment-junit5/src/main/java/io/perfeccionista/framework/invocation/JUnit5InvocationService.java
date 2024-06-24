package io.perfeccionista.framework.invocation;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.exceptions.attachments.processor.AttachmentProcessor;
import io.perfeccionista.framework.invocation.runner.EmptyInvocationRunner;
import io.perfeccionista.framework.invocation.runner.InvocationRunner;
import io.perfeccionista.framework.service.DefaultServiceConfiguration;
import io.perfeccionista.framework.service.ServiceConfiguration;
import io.perfeccionista.framework.utils.CastUtils;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static io.perfeccionista.framework.extension.PerfeccionistaExtension.resolveAttachmentProcessors;

@DefaultServiceConfiguration(DefaultInvocationServiceConfiguration.class)
public class JUnit5InvocationService extends InvocationService {

    private Set<AttachmentProcessor> testAttachmentProcessors = new HashSet<>();

    @Override
    public void init(@NotNull Environment environment) {
        super.init(environment);
        resolveTestAttachmentProcessors(environment);
    }

    @Override
    public void init(@NotNull Environment environment, @NotNull ServiceConfiguration configuration) {
        super.init(environment, configuration);
        resolveTestAttachmentProcessors(environment);
    }

    public @NotNull Class<? extends InvocationRunner> getInvocationRunnerImplementation(@NotNull Class<?> invocationWrapper) {
        return configuration == null
                ? EmptyInvocationRunner.class
                : configuration.getInvocationRunnerImplementation(invocationWrapper);
    }

    public @NotNull Set<AttachmentProcessor> getAttachmentProcessors() {
        Set<AttachmentProcessor> attachmentProcessors = configuration.getAttachmentProcessors();
        testAttachmentProcessors.forEach(attachmentProcessors::add);
        return attachmentProcessors;
    }

    protected void resolveTestAttachmentProcessors(Environment environment) {
        Optional.ofNullable(environment.getRelatedObjects().get("Context"))
                .ifPresent(context -> {
                    if (CastUtils.isSubtypeOf(context, ExtensionContext.class)) {
                        ExtensionContext extensionContext = (ExtensionContext) context;
                        Optional<Method> requiredTestMethod = extensionContext.getTestMethod();
                        Optional<Class<?>> requiredTestClass = extensionContext.getTestClass();
                        if (requiredTestMethod.isPresent() && requiredTestClass.isPresent()) {
                            testAttachmentProcessors.addAll(resolveAttachmentProcessors(requiredTestMethod.get(), requiredTestClass.get()));
                        }
                    }
                });
    }

}
