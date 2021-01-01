package io.perfeccionista.framework.utils;

import io.perfeccionista.framework.DefaultEnvironmentConfiguration;
import io.perfeccionista.framework.EnvironmentConfiguration;

import java.util.Properties;

import static io.perfeccionista.framework.utils.ReflectionUtils.loadClass;
import static org.junit.platform.commons.util.ReflectionUtils.newInstance;

public class EnvironmentConfigurationResolver {

    private EnvironmentConfigurationResolver() {
    }

    private static final String PERFECCIONISTA_PROPERTIES_FILE = "perfeccionista.properties";
    private static final String ENVIRONMENT_CONFIGURATION_PROPERTY_NAME = "perfeccionista.environment";

    public static EnvironmentConfiguration resolveEnvironmentConfiguration() {
        Properties perfeccionistaProperties = FileUtils.readOptionalPropertyFile(PERFECCIONISTA_PROPERTIES_FILE)
                .orElse(new Properties());
        Properties systemProperties = System.getProperties();
        Class<? extends EnvironmentConfiguration> environmentConfigurationClass;
        if (systemProperties.containsKey(ENVIRONMENT_CONFIGURATION_PROPERTY_NAME)) {
            String environmentConfigurationClassProperty = systemProperties.getProperty(ENVIRONMENT_CONFIGURATION_PROPERTY_NAME);
            environmentConfigurationClass = loadClass(environmentConfigurationClassProperty, EnvironmentConfiguration.class);
        } else if (perfeccionistaProperties.containsKey(ENVIRONMENT_CONFIGURATION_PROPERTY_NAME)) {
            String environmentConfigurationClassProperty = perfeccionistaProperties.getProperty(ENVIRONMENT_CONFIGURATION_PROPERTY_NAME);
            environmentConfigurationClass = loadClass(environmentConfigurationClassProperty, EnvironmentConfiguration.class);
        } else {
            environmentConfigurationClass = DefaultEnvironmentConfiguration.class;
        }
        return newInstance(environmentConfigurationClass)
                .init(systemProperties, perfeccionistaProperties);
    }

    public static EnvironmentConfiguration resolveEnvironmentConfiguration(Class<? extends EnvironmentConfiguration> environmentConfigurationClass) {
        Properties perfeccionistaProperties = FileUtils.readOptionalPropertyFile(PERFECCIONISTA_PROPERTIES_FILE)
                .orElse(new Properties());
        Properties systemProperties = System.getProperties();
        return newInstance(environmentConfigurationClass)
                .init(systemProperties, perfeccionistaProperties);
    }

}
