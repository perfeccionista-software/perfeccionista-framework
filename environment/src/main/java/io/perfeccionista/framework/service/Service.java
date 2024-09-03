package io.perfeccionista.framework.service;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.exceptions.IncorrectServiceConfiguration;
import io.perfeccionista.framework.utils.CastUtils;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.exceptions.messages.EnvironmentMessages.SERVICE_CONFIGURATION_NOT_VALID;

/**
 * TODO: Add description
 */
public interface Service {

    /**
     * TODO: JavaDoc
     * Environment instance недоступен из метода Environment.getForCurrentThread().
     */
    void init(@NotNull Environment environment);

    /**
     * TODO: JavaDoc
     * Environment instance недоступен из метода Environment.getForCurrentThread().
     */
    void init(@NotNull Environment environment, @NotNull ServiceConfiguration configuration);

    /**
     * TODO: JavaDoc
     * Возможно, сделать вызов этого метода прямо перед тестов (после метода beforeEach() в тестовом классе
     * Environment instance доступен из метода Environment.getForCurrentThread().
     */
    default void beforeTest() {
        // do nothing
    }

    /**
     * TODO: JavaDoc
     * Тут работает линтер, например
     * Environment instance доступен из метода Environment.getForCurrentThread().
     */
    default void afterTest() {
        // do nothing
    }

    default <T extends ServiceConfiguration> T validate(ServiceConfiguration configuration, Class<T> classToValidate) {
        if (CastUtils.isSubtypeOf(configuration, classToValidate)) {
            return CastUtils.castObject(configuration, classToValidate);
        }
        throw IncorrectServiceConfiguration.exception(
                SERVICE_CONFIGURATION_NOT_VALID.getMessage(configuration.getClass().getCanonicalName(), this.getClass().getCanonicalName(), classToValidate.getCanonicalName()));
    }

}
