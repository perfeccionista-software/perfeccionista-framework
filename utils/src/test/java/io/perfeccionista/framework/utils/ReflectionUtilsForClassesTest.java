package io.perfeccionista.framework.utils;

import io.perfeccionista.framework.SimpleParallelTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.newInstance;

public class ReflectionUtilsForClassesTest extends SimpleParallelTest {

    @Test
    void newInstanceWithoutArgumentsTest() {
        ClassWithConstructorWithoutArguments instance = newInstance(ClassWithConstructorWithoutArguments.class);
        Assertions.assertNotNull(instance);
    }

    @Test
    void newInstanceWithOneArgumentTest() {
        ClassWithConstructorWithOneArgument instance = newInstance(ClassWithConstructorWithOneArgument.class, "String argument");
        Assertions.assertNotNull(instance);
    }

    @Test
    void newInstanceWithTwoArgumentsTest() {
        ClassWithConstructorWithTwoArgument instance = newInstance(ClassWithConstructorWithTwoArgument.class, 7, "String argument");
        Assertions.assertNotNull(instance);
    }

    @Test
    void newInstanceForClassWithConstructorWithPrimitiveTypeUsingParametrizedTypesTest() {
        Class<?>[] parametrizedTypes = new Class<?>[] {int.class, String.class};
        ClassWithConstructorWithPrimitiveType instance = newInstance(ClassWithConstructorWithPrimitiveType.class, parametrizedTypes, 7, "String argument");
        Assertions.assertNotNull(instance);
    }

    static class ClassWithConstructorWithoutArguments {

    }

    static class ClassWithConstructorWithOneArgument {

        private final String stringValue;

        public ClassWithConstructorWithOneArgument(String stringValue) {
            this.stringValue = stringValue;
        }

    }

    static class ClassWithConstructorWithTwoArgument {

        private final int intValue;
        private final String stringValue;

        public ClassWithConstructorWithTwoArgument(Integer intValue, String stringValue) {
            this.intValue = intValue;
            this.stringValue = stringValue;
        }

    }

    static class ClassWithConstructorWithPrimitiveType {

        private final int intValue;
        private final String stringValue;

        public ClassWithConstructorWithPrimitiveType(int intValue, String stringValue) {
            this.intValue = intValue;
            this.stringValue = stringValue;
        }

    }

}
