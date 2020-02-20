package io.perfeccionista.framework.utils;

import org.junit.jupiter.api.Test;
import io.perfeccionista.framework.SimpleParallelTest;

import java.util.Deque;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

final class ReflectionUtilsTest extends SimpleParallelTest {

    @Test
    void getClassesWithInheritanceNotNullArgumentsTest() {
        assertThrows(IllegalArgumentException.class, () -> ReflectionUtils.getClassesWithInheritance(null, Object.class));
        assertThrows(IllegalArgumentException.class, () -> ReflectionUtils.getClassesWithInheritance(Object.class, null));
    }

    @Test
    void getClassesWithInheritanceTest() {
        Deque<Class<? extends B>> classesFromBToC = ReflectionUtils.getClassesWithInheritance(C.class, B.class);
        assertEquals(2, classesFromBToC.size());
        assertEquals(B.class, classesFromBToC.getFirst());
        assertEquals(C.class, classesFromBToC.getLast());
    }

    @Test
    void getClassesWithInheritanceIgnoreInterfacesTest() {
        Deque<Class<? extends A>> classesFromAToC = ReflectionUtils.getClassesWithInheritance(C.class, A.class);
        assertEquals(2, classesFromAToC.size());
        assertEquals(B.class, classesFromAToC.getFirst());
        assertEquals(C.class, classesFromAToC.getLast());
    }

    private interface A {}

    private static class B implements A {}

    private static class C extends B {}

}
