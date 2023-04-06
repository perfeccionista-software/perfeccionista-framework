package io.perfeccionista.framework.pagefactory.configurations;

import io.perfeccionista.framework.invocation.InvocationServiceConfiguration;
import io.perfeccionista.framework.invocation.runner.AllureInvocationNameFormatter;
import io.perfeccionista.framework.invocation.runner.DefaultInvocationInfoStatisticsFormatter;
import io.perfeccionista.framework.invocation.runner.InvocationInfoNameFormatter;
import io.perfeccionista.framework.invocation.runner.InvocationInfoStatisticsFormatter;
import io.perfeccionista.framework.invocation.runner.EmptyInvocationRunner;
import io.perfeccionista.framework.invocation.runner.InvocationRunner;
import io.perfeccionista.framework.invocation.wrapper.MultipleAttemptInvocationWrapper;
import io.perfeccionista.framework.invocation.wrapper.SingleAttemptInvocationWrapper;
import io.perfeccionista.framework.utils.FileUtils;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class AllureInvocationServiceConfiguration implements InvocationServiceConfiguration {
    private static final InvocationInfoStatisticsFormatter ALLURE_STATISTICS_FORMATTER = new DefaultInvocationInfoStatisticsFormatter();

    protected static final String ALLURE_STEP_NAMES_PROPERTIES_FILE = "allureStepNames.properties";

    protected final Map<String, String> allureStepNamesProperties = new HashMap<>();

    public AllureInvocationServiceConfiguration() {
        readProperties();
    }

    @Override
    public @NotNull Class<? extends InvocationRunner> getInvocationRunnerImplementation(@NotNull Class<?> invocationWrapper) {
        if (MultipleAttemptInvocationWrapper.class.equals(invocationWrapper)) {
            // TODO: Переименовать под новые имена
//            return WebAllureCheckInvocationRunner.class;
        }
        if (SingleAttemptInvocationWrapper.class.equals(invocationWrapper)) {
            // TODO: Переименовать под новые имена
//            return WebAllureLogicInvocationRunner.class;
        }
        return EmptyInvocationRunner.class;
    }

    protected void readProperties() {
        Properties properties = FileUtils.readOptionalPropertyFileFromClasspath(ALLURE_STEP_NAMES_PROPERTIES_FILE)
                .orElse(new Properties());
        for (String propertyName : properties.stringPropertyNames()) {
            allureStepNamesProperties.put(propertyName, properties.getProperty(propertyName));
        }
    }

    @Override
    public @NotNull InvocationInfoNameFormatter getInvocationInfoNameFormatter() {
        return new AllureInvocationNameFormatter(allureStepNamesProperties);
    }

    @Override
    public @NotNull InvocationInfoStatisticsFormatter getInvocationInfoStatisticsFormatter() {
        return ALLURE_STATISTICS_FORMATTER;
    }

}
