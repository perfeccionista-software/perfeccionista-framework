package io.perfeccionista.framework.utils;

import java.lang.reflect.AccessibleObject;

public class ReflectionUtils {

    private ReflectionUtils() {
    }

    protected static <T extends AccessibleObject> T makeAccessible(T object) {
        object.setAccessible(true);
        return object;
    }

}
