package io.perfeccionista.framework.value;

import io.perfeccionista.framework.SimpleParallelTest;
import io.perfeccionista.framework.extension.PerfeccionistaExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(PerfeccionistaExtension.class)
public class StringValueTest extends SimpleParallelTest {

    @Test
    void stringValuePositiveTest(ValueService value) {
        assertTrue(value.stringEquals("Checked value")
                .check("Checked value"));
        assertTrue(value.stringEqualsIgnoreCase("CheCKed VaLuE")
                .check("Checked value"));
        assertTrue(value.stringContains("ecked va")
                .check("Checked value"));
        assertTrue(value.stringContainsIgnoreCase("eCKed vA")
                .check("Checked value"));
        assertTrue(value.stringContainsAll(Set.of("Checked", "value"))
                .check("Checked value"));
        assertTrue(value.stringContainsAny(Set.of("Unknown", "value"))
                .check("Checked value"));
        assertTrue(value.stringEmpty()
                .check(""));
        assertTrue(value.stringStartsWith("Check")
                .check("Checked value"));
        assertTrue(value.stringEndsWith(" value")
                .check("Checked value"));
        assertTrue(value.stringLength(13)
                .check("Checked value"));
        assertTrue(value.stringMatches("^\\w{7}\\s\\w{5}$")
                .check("Checked value"));
    }

    @Test
    void stringValueReversePositiveTest(ValueService value) {
        assertTrue(value.stringEquals("Checked Value").inverse()
                .check("Checked value"));
        assertTrue(value.stringEqualsIgnoreCase("CheCKed VaLuE!").inverse()
                .check("Checked value"));
        assertTrue(value.stringContains("ecked  va").inverse()
                .check("Checked value"));
        assertTrue(value.stringContainsIgnoreCase("eCKed  vA").inverse()
                .check("Checked value"));
        assertTrue(value.stringContainsAll(Set.of("Checked", "Value")).inverse()
                .check("Checked value"));
        assertTrue(value.stringContainsAny(Set.of("Unknown", "value!")).inverse()
                .check("Checked value"));
        assertTrue(value.stringEmpty().inverse()
                .check("0"));
        assertTrue(value.stringStartsWith("Fast Check").inverse()
                .check("Checked value"));
        assertTrue(value.stringEndsWith(" value!").inverse()
                .check("Checked value"));
        assertTrue(value.stringLength(12).inverse()
                .check("Checked value"));
        assertTrue(value.stringMatches("^\\w{5}\\s\\w{5}$").inverse()
                .check("Checked value"));
    }


    @Test
    void stringValueNegativeTest(ValueService value) {
        assertFalse(value.stringEquals("Checked Value")
                .check("Checked value"));
        assertFalse(value.stringEqualsIgnoreCase("CheCKed VaLuE!")
                .check("Checked value"));
        assertFalse(value.stringContains("ecked  va")
                .check("Checked value"));
        assertFalse(value.stringContainsIgnoreCase("eCKed  vA")
                .check("Checked value"));
        assertFalse(value.stringContainsAll(Set.of("Checked", "Value"))
                .check("Checked value"));
        assertFalse(value.stringContainsAny(Set.of("Unknown", "value!"))
                .check("Checked value"));
        assertFalse(value.stringEmpty()
                .check("0"));
        assertFalse(value.stringStartsWith("Fast Check")
                .check("Checked value"));
        assertFalse(value.stringEndsWith(" value!")
                .check("Checked value"));
        assertFalse(value.stringLength(12)
                .check("Checked value"));
        assertFalse(value.stringMatches("^\\w{5}\\s\\w{5}$")
                .check("Checked value"));
    }

    @Test
    void stringValueWithActualValueTransformerTest(ValueService value) {
        assertTrue(value.stringEquals("CHECKED VALUE").transformActual(String::toUpperCase)
                .check("Checked value"));
        assertTrue(value.stringEquals("Checked_value").transformActual(s -> s.replaceAll(" ", "_"))
                .check("Checked value"));
    }

    @Test
    void stringValueWithExpectedValueTransformerTest(ValueService value) {
        assertTrue(value.stringEquals("CHECKED VALUE").transformExpected(String::toLowerCase)
                .check("checked value"));
        assertTrue(value.stringEquals("Checked_value").transformExpected(s -> s.replaceAll("_", " "))
                .check("Checked value"));
    }

}
