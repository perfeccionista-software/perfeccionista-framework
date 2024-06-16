package io.perfeccionista.framework.utils;

import io.perfeccionista.framework.exceptions.impl.PreconditionViolationException;
import io.perfeccionista.framework.measurements.Order;
import org.junit.jupiter.api.Test;
import io.perfeccionista.framework.SimpleParallelTest;

import java.util.Deque;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

final class ReflectionUtilsTest extends SimpleParallelTest {

    @Test
    void getInheritedClassesNotNullArgumentsTest() {
        assertThrows(PreconditionViolationException.class, () -> ReflectionUtilsForClasses.findInheritedClasses(null, Object.class, null));
        assertThrows(PreconditionViolationException.class, () -> ReflectionUtilsForClasses.findInheritedClasses(Object.class, null, null));
        assertThrows(PreconditionViolationException.class, () -> ReflectionUtilsForClasses.findInheritedClasses(null, Object.class, Order.DESC));
        assertThrows(PreconditionViolationException.class, () -> ReflectionUtilsForClasses.findInheritedClasses(Object.class, null, Order.DESC));
    }

    @Test
    void getInheritedClassesDescTest() {
        Deque<Class<? extends B>> classesFromBToC = ReflectionUtilsForClasses.findInheritedClasses(B.class, C.class, Order.DESC);
        assertEquals(2, classesFromBToC.size());
        assertEquals(C.class, classesFromBToC.getFirst());
        assertEquals(B.class, classesFromBToC.getLast());
    }

    @Test
    void getInheritedClassesAscTest() {
        Deque<Class<? extends B>> classesFromBToC = ReflectionUtilsForClasses.findInheritedClasses(B.class, C.class, Order.ASC);
        assertEquals(2, classesFromBToC.size());
        assertEquals(B.class, classesFromBToC.getFirst());
        assertEquals(C.class, classesFromBToC.getLast());
    }

    @Test
    void getInheritedClassesAscIgnoreInterfacesTest() {
        Deque<Class<? extends A>> classesFromAToC = ReflectionUtilsForClasses.findInheritedClasses(A.class, C.class, Order.ASC);
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

    private static class H2Impl extends HImpl {}

    @Test
    void getInheritedInterfacesNotNullArgumentsTest() {
        assertThrows(PreconditionViolationException.class, () -> ReflectionUtilsForClasses.findInheritedInterfaces(null, Object.class, null));
        assertThrows(PreconditionViolationException.class, () -> ReflectionUtilsForClasses.findInheritedInterfaces(Object.class, null, null));
        assertThrows(PreconditionViolationException.class, () -> ReflectionUtilsForClasses.findInheritedInterfaces(null, Object.class, Order.DESC));
        assertThrows(PreconditionViolationException.class, () -> ReflectionUtilsForClasses.findInheritedInterfaces(Object.class, null, Order.DESC));
    }

    @Test
    void getInheritedInterfacesDescTest() {
        Deque<Class<? extends A>> interfacesFromHToA = ReflectionUtilsForClasses.findInheritedInterfaces(A.class, HImpl.class, Order.DESC);
        assertEquals(5, interfacesFromHToA.size());
        assertEquals(H.class, interfacesFromHToA.getFirst());
        assertEquals(A.class, interfacesFromHToA.getLast());
    }

    @Test
    void getInheritedInterfacesAscTest() {
        Deque<Class<? extends A>> interfacesFromHToA = ReflectionUtilsForClasses.findInheritedInterfaces(A.class, HImpl.class, Order.ASC);
        assertEquals(5, interfacesFromHToA.size());
        assertEquals(A.class, interfacesFromHToA.getFirst());
        assertEquals(H.class, interfacesFromHToA.getLast());
    }

    @Test
    void getInheritedInterfacesFromExtendedClassAscTest() {
        Deque<Class<? extends A>> interfacesFromHToA = ReflectionUtilsForClasses.findInheritedInterfaces(A.class, H2Impl.class, Order.ASC);
        assertEquals(5, interfacesFromHToA.size());
        assertEquals(A.class, interfacesFromHToA.getFirst());
        assertEquals(H.class, interfacesFromHToA.getLast());
    }

}
