package io.perfeccionista.framework.utils;

import io.perfeccionista.framework.DefaultEnvironmentConfiguration;
import io.perfeccionista.framework.EnvironmentConfiguration;

import java.util.Properties;

import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.loadClass;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.newInstance;

public class EnvironmentConfigurationResolver {

    private EnvironmentConfigurationResolver() {
    }

    private static final String PERFECCIONISTA_PROPERTIES_FILE = "perfeccionista.properties";
    private static final String ENVIRONMENT_CONFIGURATION_PROPERTY_NAME = "perfeccionista.environment";

    public static EnvironmentConfiguration resolveEnvironmentConfiguration() {
        Properties perfeccionistaProperties = FileUtils.readOptionalPropertyFileFromClasspath(PERFECCIONISTA_PROPERTIES_FILE)
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
        return newInstance(environmentConfigurationClass);
    }

}
