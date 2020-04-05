package io.perfeccionista.framework.value;

import org.junit.jupiter.api.Test;
import io.perfeccionista.framework.SimpleParallelTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValueDeclarationTest extends SimpleParallelTest {

    @Test
    void initializationWithoutConditionSuccessTest() {
        ValueDeclaration valueDeclarationWithoutConditions = ValueDeclaration.of("value");
        assertNotNull(valueDeclarationWithoutConditions.getValueExpression());
        assertEquals("value", valueDeclarationWithoutConditions.getValueExpression());
        assertEquals(Optional.empty(), valueDeclarationWithoutConditions.getValueCondition());
        assertEquals(Optional.empty(), valueDeclarationWithoutConditions.getValueConditionDeclaration());
    }

    @Test
    void initializationWithConditionSuccessTest() {
        ValueDeclaration valueDeclarationWithConditions = ValueDeclaration.of("value", "подстроку", "(подстроку)");
        assertNotNull(valueDeclarationWithConditions.getValueExpression());
        assertEquals("value", valueDeclarationWithConditions.getValueExpression());
        assertTrue(valueDeclarationWithConditions.getValueCondition().isPresent());
        assertEquals("подстроку", valueDeclarationWithConditions.getValueCondition().get());
        assertTrue(valueDeclarationWithConditions.getValueConditionDeclaration().isPresent());
        assertEquals("(подстроку)", valueDeclarationWithConditions.getValueConditionDeclaration().get());
    }


    @Test
    void notNullArgumentsTest() {
        assertThrows(IllegalArgumentException.class, () -> ValueDeclaration.of(null));
        assertThrows(IllegalArgumentException.class, () -> ValueDeclaration.of("value", "подстроку", null));
        assertThrows(IllegalArgumentException.class, () -> ValueDeclaration.of("value", null, "(подстроку)"));
        assertThrows(IllegalArgumentException.class, () -> ValueDeclaration.of("value", null, null));
    }

}
