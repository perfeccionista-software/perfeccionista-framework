package io.perfeccionista.framework.value.configurations;

import io.perfeccionista.framework.DefaultEnvironmentConfiguration;
import io.perfeccionista.framework.datasource.DataConverterService;
import io.perfeccionista.framework.exceptions.attachments.TextAttachmentEntry;
import io.perfeccionista.framework.service.ConfiguredServiceHolder;
import io.perfeccionista.framework.utils.EnvironmentAttachmentProcessor;
import io.qameta.allure.Allure;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class TestValueEnvironmentConfigurationWithoutDataConverterService extends DefaultEnvironmentConfiguration {

    @Override
    public @NotNull Set<ConfiguredServiceHolder> getServiceConfigurations() {
        return super.getServiceConfigurations().stream()
                .filter(configuredServiceHolder -> !Objects.equals(configuredServiceHolder.getServiceClass(), DataConverterService.class))
                .collect(toSet());
    }

    @Override
    public @NotNull EnvironmentAttachmentProcessor getEnvironmentAttachmentProcessor() {
        return new AllureEnvironmentAttachmentProcessor();
    }

    static class AllureEnvironmentAttachmentProcessor implements EnvironmentAttachmentProcessor {

        @Override
        public void process(@NotNull TextAttachmentEntry environmentTextAttachment) {
            environmentTextAttachment.getContent()
                    .ifPresent(content -> Allure.addAttachment("EnvironmentConfiguration", "text/plain", content));
        }

    }

}
