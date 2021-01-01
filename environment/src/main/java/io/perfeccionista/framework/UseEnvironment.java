package io.perfeccionista.framework;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотация позволяет указывать конфигурацию для
 * экземпляра {@link Environment}.
 * Аннотация считывается {@link io.perfeccionista.framework.extension.PerfeccionistaExtension}
 * В момент инициализации {@link Environment}.
 * Приоритет конфигураций при инициализации выглядит так:
 * <UL>
 *     <li>Если есть аннотация над тестовым методом, то используется она и
 *     экземпляр {@link Environment} пересоздается</li>
 *     <li>Если над тестовым методом ее нет, то выполняется ее поиск
 *     над тестовым классом. Если аннотация есть над тестовым классом,
 *     то используется она, а экземпляр {@link Environment} создается
 *     один на весь класс.</li>
 *     <li>Если над тестовым классом нет конфигурации, то ее поиск выполняется
 *     в классах-предках тестового класса.</li>
 *     <li>Если в классах-предках тестового класса ее также нет,
 *     то используется дефолтная конфигурация</li>
 * </UL>
 * @see EnvironmentConfiguration
 * @see Environment
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface UseEnvironment {

    /**
     * @return Класс-реализация конфигурации {@link Environment}
     */
    Class<? extends EnvironmentConfiguration> value();

}

