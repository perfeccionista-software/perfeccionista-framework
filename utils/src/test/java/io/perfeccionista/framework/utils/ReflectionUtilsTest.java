package io.perfeccionista.framework.utils;

import io.perfeccionista.framework.utils.ReflectionUtils.Order;
import org.junit.jupiter.api.Test;
import io.perfeccionista.framework.SimpleParallelTest;

import java.util.Deque;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

final class ReflectionUtilsTest extends SimpleParallelTest {

    @Test
    void getInheritedClassesNotNullArgumentsTest() {
        assertThrows(IllegalArgumentException.class, () -> ReflectionUtils.getInheritedClasses(null, Object.class, null));
        assertThrows(IllegalArgumentException.class, () -> ReflectionUtils.getInheritedClasses(Object.class, null, null));
        assertThrows(IllegalArgumentException.class, () -> ReflectionUtils.getInheritedClasses(null, Object.class, Order.DESC));
        assertThrows(IllegalArgumentException.class, () -> ReflectionUtils.getInheritedClasses(Object.class, null, Order.DESC));
    }

    @Test
    void getInheritedClassesDescTest() {
        Deque<Class<? extends B>> classesFromBToC = ReflectionUtils.getInheritedClasses(B.class, C.class, Order.DESC);
        assertEquals(2, classesFromBToC.size());
        assertEquals(C.class, classesFromBToC.getFirst());
        assertEquals(B.class, classesFromBToC.getLast());
    }

    @Test
    void getInheritedClassesAscTest() {
        Deque<Class<? extends B>> classesFromBToC = ReflectionUtils.getInheritedClasses(B.class, C.class, Order.ASC);
        assertEquals(2, classesFromBToC.size());
        assertEquals(B.class, classesFromBToC.getFirst());
        assertEquals(C.class, classesFromBToC.getLast());
    }

    @Test
    void getInheritedClassesAscIgnoreInterfacesTest() {
        Deque<Class<? extends A>> classesFromAToC = ReflectionUtils.getInheritedClasses(A.class, C.class, Order.ASC);
        assertEquals(2, classesFromAToC.size());
        assertEquals(B.class, classesFromAToC.getFirst());
        assertEquals(C.class, classesFromAToC.getLast());
    }

    private interface A {}

    private static class B implements A {}

    private static class C extends B {}

    private interface D extends A {}

    private interface E extends D {}

    private interface F {}

    private interface G extends E, F, D {}

    private interface H extends G {}

    private static class HImpl implements H {}

    @Test
    void getInheritedInterfacesNotNullArgumentsTest() {
        assertThrows(IllegalArgumentException.class, () -> ReflectionUtils.getInheritedInterfaces(null, Object.class, null));
        assertThrows(IllegalArgumentException.class, () -> ReflectionUtils.getInheritedInterfaces(Object.class, null, null));
        assertThrows(IllegalArgumentException.class, () -> ReflectionUtils.getInheritedInterfaces(null, Object.class, Order.DESC));
        assertThrows(IllegalArgumentException.class, () -> ReflectionUtils.getInheritedInterfaces(Object.class, null, Order.DESC));
    }

    @Test
    void getInheritedInterfacesDescTest() {
        Deque<Class<? extends A>> interfacesFromHToA = ReflectionUtils.getInheritedInterfaces(A.class, HImpl.class, Order.DESC);
        assertEquals(5, interfacesFromHToA.size());
        assertEquals(H.class, interfacesFromHToA.getFirst());
        assertEquals(A.class, interfacesFromHToA.getLast());
    }

    @Test
    void getInheritedInterfacesAscTest() {
        Deque<Class<? extends A>> interfacesFromHToA = ReflectionUtils.getInheritedInterfaces(A.class, HImpl.class, Order.ASC);
        assertEquals(5, interfacesFromHToA.size());
        assertEquals(A.class, interfacesFromHToA.getFirst());
        assertEquals(H.class, interfacesFromHToA.getLast());
    }

}
