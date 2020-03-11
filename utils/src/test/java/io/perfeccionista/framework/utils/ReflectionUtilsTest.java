package io.perfeccionista.framework.utils;

import io.perfeccionista.framework.utils.ReflectionUtils.Order;
import org.junit.jupiter.api.Test;
import io.perfeccionista.framework.SimpleParallelTest;

import java.util.Deque;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

final class ReflectionUtilsTest extends SimpleParallelTest {

    @Test
    void getClassesWithInheritanceNotNullArgumentsTest() {
        assertThrows(IllegalArgumentException.class, () -> ReflectionUtils.getClassInheritors(null, Object.class, null));
        assertThrows(IllegalArgumentException.class, () -> ReflectionUtils.getClassInheritors(Object.class, null, null));
        assertThrows(IllegalArgumentException.class, () -> ReflectionUtils.getClassInheritors(null, Object.class, Order.DESC));
        assertThrows(IllegalArgumentException.class, () -> ReflectionUtils.getClassInheritors(Object.class, null, Order.DESC));
    }

    @Test
    void getClassesWithInheritanceTest() {
        Deque<Class<B>> classesFromBToC = ReflectionUtils.getClassInheritors(B.class, C.class, Order.ASC);
        assertEquals(2, classesFromBToC.size());
        assertEquals(B.class, classesFromBToC.getFirst());
        assertEquals(C.class, classesFromBToC.getLast());
    }

    @Test
    void getClassesWithInheritanceIgnoreInterfacesTest() {
        Deque<Class<A>> classesFromAToC = ReflectionUtils.getClassInheritors(A.class, C.class, Order.ASC);
        assertEquals(2, classesFromAToC.size());
        assertEquals(B.class, classesFromAToC.getFirst());
        assertEquals(C.class, classesFromAToC.getLast());
    }

    private interface A {}

    private static class B implements A {}

    private static class C extends B {}

}
