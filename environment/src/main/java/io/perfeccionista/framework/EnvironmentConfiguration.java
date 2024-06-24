package io.perfeccionista.framework;

import io.perfeccionista.framework.service.ServiceConfigurationManager;
import io.perfeccionista.framework.service.ConfiguredServiceHolder;
import io.perfeccionista.framework.utils.DefaultEnvironmentAttachmentProcessor;
import io.perfeccionista.framework.utils.EnvironmentAttachmentProcessor;
import org.jetbrains.annotations.NotNull;

/**
 * Базовый интерфейс для конфигурирования {@link Environment}.
 * Реализация этого интерфейса в общем случае выглядит следующим образом:
 * @see {@link Environment}
 */
public interface EnvironmentConfiguration {

    /**
     * @return реализацию {@link Environment}, которая будет использована.
     * @see Environment
     */
    default @NotNull Class<? extends Environment> getEnvironmentClass() {
        return Environment.class;
    }

    /**
     * Добавляет в очередь приоритизации очередной сконфигурированный сервис,
     * который должен переопределять найденные ранее менее приоритетные конфигурации.
     * @param configuredServiceHolder
     */
    void addOrOverrideServiceConfiguration(@NotNull ConfiguredServiceHolder configuredServiceHolder);

    /**
     * Набор сервисов и конфигураций для замены дефолных сервисов
     * @return
     */
    @NotNull ServiceConfigurationManager getServiceConfigurations();

    /**
     * @return Реализация обработчика аттачмента конфигурации Environment.
     * Базовый обработчик выводит конфигурацию в лог.
     * Реализация может, например, крепить ее к аллюр-отчету.
     */
    default @NotNull EnvironmentAttachmentProcessor getEnvironmentAttachmentProcessor() {
        return new DefaultEnvironmentAttachmentProcessor();
    }

}
