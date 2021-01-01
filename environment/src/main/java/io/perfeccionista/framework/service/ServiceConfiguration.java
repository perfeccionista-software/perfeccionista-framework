package io.perfeccionista.framework.service;

import java.util.Optional;

/**
 * TODO: JavaDoc
 *
 * Как задавать дефолтный инстанс??? Через класс-имплементацию провайдера?
 * Пользователь сам реализовывает логику выбора дефолтного инстанса (нужно добавить в описание
 * документации как это правильно делать, например, через аннотацию @Default).
 */
public interface ServiceConfiguration {

    /**
     * TODO: JavaDoc
     * @return
     */
    default Optional<Class<? extends Service>> getImplementation() {
        return Optional.empty();
    }

    /**
     * TODO: JavaDoc
     * @return
     */
    default int getPriority() {
        return 0;
    }

}
