package io.perfeccionista.framework.value.processor;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TokenTest {

    @Test
    void initializationSuccessTest() {
        Token token1 = Token.of(TokenType.VALUE, 0);
        assertEquals(TokenType.VALUE, token1.getType());
        assertEquals("", token1.getContent());
        Token token2 = Token.of(TokenType.VALUE, "[datasource]", 0);
        assertEquals(TokenType.VALUE, token2.getType());
        assertEquals("[datasource]", token2.getContent());
    }

    @Test
    void notNullArgumentsTest() {
        assertThrows(IllegalArgumentException.class, () -> Token.of(null, 0));
        assertThrows(IllegalArgumentException.class, () -> Token.of(null, "DataSource", 0));
        assertThrows(IllegalArgumentException.class, () -> Token.of(TokenType.VALUE, null, 0));
        assertThrows(IllegalArgumentException.class, () -> Token.of(null, null, 0));
    }

}
