package io.perfeccionista.framework.assertions;

import io.perfeccionista.framework.value.number.NumberValue;
import io.perfeccionista.framework.value.string.StringValue;

@Deprecated
public class WebVisualAsserts {

    // Сравниваем со скриншотом по его имени с дефолтным разрешением экрана
    public static void assertImage(StringValue imagePath) {

    }

    // Сравниваем со скриншотом по его имени и разрешению браузера
    public static void assertImage(StringValue imagePath, NumberValue<Integer> screenWidth, NumberValue<Integer> height) {

    }

}
