package io.perfeccionista.framework.value.processor;

import io.perfeccionista.framework.AbstractParallelTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestInstance(Lifecycle.PER_METHOD)
@Execution(ExecutionMode.CONCURRENT)
class TokenTest extends AbstractParallelTest {

    @Test
    void initializationSuccessTest() {
        assertAll(
                () -> {
                    Token token1 = Token.of(TokenType.VALUE, 0);
                    assertAll(
                            () -> assertEquals(TokenType.VALUE, token1.getType()),
                            () -> assertEquals("", token1.getContent())
                    );
                },
                () -> {
                    Token token2 = Token.of(TokenType.VALUE, "[datasource]", 0);
                    assertAll(
                            () -> assertEquals(TokenType.VALUE, token2.getType()),
                            () -> assertEquals("[datasource]", token2.getContent())
                    );
                }
        );
    }

    @Test
    void notNullArgumentsTest() {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> Token.of(null, 0)),
                () -> assertThrows(IllegalArgumentException.class, () -> Token.of(null, "DataSource", 0)),
                () -> assertThrows(IllegalArgumentException.class, () -> Token.of(TokenType.VALUE, null, 0)),
                () -> assertThrows(IllegalArgumentException.class, () -> Token.of(null, null, 0))
        );
    }

}
