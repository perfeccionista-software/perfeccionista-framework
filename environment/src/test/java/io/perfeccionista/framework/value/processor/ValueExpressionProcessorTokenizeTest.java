package io.perfeccionista.framework.value.processor;

import io.perfeccionista.framework.AbstractParallelTestWithEnvironment;
import io.perfeccionista.framework.exceptions.impl.StringValueParseException;
import org.junit.jupiter.api.Test;
import io.perfeccionista.framework.Environment;

import java.util.Deque;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

class ValueExpressionProcessorTokenizeTest extends AbstractParallelTestWithEnvironment {

    @Test
    void tokenizeSimpleExpression() {
        ValueExpressionProcessor expressionProcessor = new ValueExpressionProcessor(mock(Environment.class));
        assertAll(
                () -> {
                    Deque<Token> tokenizedExpression1 = expressionProcessor.tokenize("simple expression");
                    assertAll(
                            () -> assertNotNull(tokenizedExpression1),
                            () -> assertEquals(1, tokenizedExpression1.size()),
                            () -> assertEquals(Token.of(TokenType.VALUE, "simple expression", 0), tokenizedExpression1.getFirst())
                    );
                },
                () -> {
                    Deque<Token> tokenizedExpression2 = expressionProcessor.tokenize("{simple expression}");
                    assertAll(
                            () -> assertNotNull(tokenizedExpression2),
                            () -> assertEquals(1, tokenizedExpression2.size()),
                            () -> assertEquals(Token.of(TokenType.VALUE, "{simple expression}", 0), tokenizedExpression2.getFirst())
                    );
                },
                () -> {
                    Deque<Token> tokenizedExpression3 = expressionProcessor.tokenize("   ");
                    assertAll(
                            () -> assertNotNull(tokenizedExpression3),
                            () -> assertEquals(1, tokenizedExpression3.size()),
                            () -> assertEquals(Token.of(TokenType.VALUE, "   ", 0), tokenizedExpression3.getFirst())
                    );
                }
        );
    }

    @Test
    void tokenizeEscapedSimpleExpression() {
        ValueExpressionProcessor expressionProcessor = new ValueExpressionProcessor(mock(Environment.class));
        assertAll(
                () -> {
                    Deque<Token> tokenizedExpression1 = expressionProcessor.tokenize("\\$\\{escaped DataSource expression\\}");
                    assertAll(
                            () -> assertNotNull(tokenizedExpression1),
                            () -> assertEquals(1, tokenizedExpression1.size()),
                            () -> assertEquals(Token.of(TokenType.VALUE, "${escaped DataSource expression}", 0), tokenizedExpression1.getFirst())
                    );
                },
                () -> {
                    Deque<Token> tokenizedExpression2 = expressionProcessor.tokenize("\\@\\{escaped DataConverter expression\\}");
                    assertAll(
                            () -> assertNotNull(tokenizedExpression2),
                            () -> assertEquals(1, tokenizedExpression2.size()),
                            () -> assertEquals(Token.of(TokenType.VALUE, "@{escaped DataConverter expression}", 0), tokenizedExpression2.getFirst())
                    );
                },
                () -> {
                    Deque<Token> tokenizedExpression3 = expressionProcessor.tokenize("\\{escaped DataConverter expression\\}");
                    assertAll(
                            () -> assertNotNull(tokenizedExpression3),
                            () -> assertEquals(1, tokenizedExpression3.size()),
                            () -> assertEquals(Token.of(TokenType.VALUE, "{escaped DataConverter expression}", 0), tokenizedExpression3.getFirst())
                    );
                },
                () -> {
                    Deque<Token> tokenizedExpression4 = expressionProcessor.tokenize("escaped \\${DataSource expression}");
                    assertAll(
                            () -> assertNotNull(tokenizedExpression4),
                            () -> assertEquals(1, tokenizedExpression4.size()),
                            () -> assertEquals(Token.of(TokenType.VALUE, "escaped ${DataSource expression}", 0), tokenizedExpression4.getFirst())
                    );
                },
                () -> {
                    Deque<Token> tokenizedExpression5 = expressionProcessor.tokenize("escaped \\@{DataConverter expression}");
                    assertAll(
                            () -> assertNotNull(tokenizedExpression5),
                            () -> assertEquals(1, tokenizedExpression5.size()),
                            () -> assertEquals(Token.of(TokenType.VALUE, "escaped @{DataConverter expression}", 0), tokenizedExpression5.getFirst())
                    );
                },
                () -> {
                    Deque<Token> tokenizedExpression6 = expressionProcessor.tokenize("escaped \\@{DataConverter expression}$");
                    assertAll(
                            () -> assertNotNull(tokenizedExpression6),
                            () -> assertEquals(1, tokenizedExpression6.size()),
                            () -> assertEquals(Token.of(TokenType.VALUE, "escaped @{DataConverter expression}$", 0), tokenizedExpression6.getFirst())
                    );
                },
                () -> {
                    Deque<Token> tokenizedExpression7 = expressionProcessor.tokenize("escaped \\@{DataConverter expression}@");
                    assertAll(
                            () -> assertNotNull(tokenizedExpression7),
                            () -> assertEquals(1, tokenizedExpression7.size()),
                            () -> assertEquals(Token.of(TokenType.VALUE, "escaped @{DataConverter expression}@", 0), tokenizedExpression7.getFirst())
                    );
                },
                () -> {
                    Deque<Token> tokenizedExpression8 = expressionProcessor.tokenize("escaped \\@{DataConverter expression}\\");
                    assertAll(
                            () -> assertNotNull(tokenizedExpression8),
                            () -> assertEquals(1, tokenizedExpression8.size()),
                            () -> assertEquals(Token.of(TokenType.VALUE, "escaped @{DataConverter expression}\\", 0), tokenizedExpression8.getFirst())
                    );
                }
        );
    }

    @Test
    void tokenizeExpressionWithDataSource() {
        ValueExpressionProcessor expressionProcessor = new ValueExpressionProcessor(mock(Environment.class));
        assertAll(
                () -> {
                    Deque<Token> tokenizedExpression1 = expressionProcessor.tokenize("expression ${dataSource key}");
                    assertAll(
                            () -> assertNotNull(tokenizedExpression1),
                            () -> assertEquals(4, tokenizedExpression1.size()),
                            () -> assertEquals(Token.of(TokenType.VALUE, "expression ", 0), tokenizedExpression1.pollFirst()),
                            () -> assertEquals(Token.of(TokenType.DATA_SOURCE_OPEN, 11), tokenizedExpression1.pollFirst()),
                            () -> assertEquals(Token.of(TokenType.VALUE, "dataSource key", 13), tokenizedExpression1.pollFirst()),
                            () -> assertEquals(Token.of(TokenType.DATA_SOURCE_CLOSED, 27), tokenizedExpression1.pollFirst())
                    );
                },
                () -> {
                    Deque<Token> tokenizedExpression2 = expressionProcessor.tokenize("${dataSource key} expression");
                    assertAll(
                            () -> assertNotNull(tokenizedExpression2),
                            () -> assertEquals(4, tokenizedExpression2.size()),
                            () -> assertEquals(Token.of(TokenType.DATA_SOURCE_OPEN, 0), tokenizedExpression2.pollFirst()),
                            () -> assertEquals(Token.of(TokenType.VALUE, "dataSource key", 2), tokenizedExpression2.pollFirst()),
                            () -> assertEquals(Token.of(TokenType.DATA_SOURCE_CLOSED, 16), tokenizedExpression2.pollFirst()),
                            () -> assertEquals(Token.of(TokenType.VALUE, " expression", 17), tokenizedExpression2.pollFirst())
                    );
                },
                () -> {
                    Deque<Token> tokenizedExpression3 = expressionProcessor.tokenize("custom ${dataSource key} expression");
                    assertAll(
                            () -> assertNotNull(tokenizedExpression3),
                            () -> assertEquals(5, tokenizedExpression3.size()),
                            () -> assertEquals(Token.of(TokenType.VALUE, "custom ", 0), tokenizedExpression3.pollFirst()),
                            () -> assertEquals(Token.of(TokenType.DATA_SOURCE_OPEN, 7), tokenizedExpression3.pollFirst()),
                            () -> assertEquals(Token.of(TokenType.VALUE, "dataSource key", 9), tokenizedExpression3.pollFirst()),
                            () -> assertEquals(Token.of(TokenType.DATA_SOURCE_CLOSED, 23), tokenizedExpression3.pollFirst()),
                            () -> assertEquals(Token.of(TokenType.VALUE, " expression", 24), tokenizedExpression3.pollFirst())
                    );
                }
        );
    }

    @Test
    void tokenizeExpressionWithDataConverter() {
        ValueExpressionProcessor expressionProcessor = new ValueExpressionProcessor(mock(Environment.class));
        assertAll(
                () -> {
                    Deque<Token> tokenizedExpression1 = expressionProcessor.tokenize("expression @{dataConverter key}");
                    assertAll(
                            () -> assertNotNull(tokenizedExpression1),
                            () -> assertEquals(4, tokenizedExpression1.size()),
                            () -> assertEquals(Token.of(TokenType.VALUE, "expression ", 0), tokenizedExpression1.pollFirst()),
                            () -> assertEquals(Token.of(TokenType.DATA_CONVERTER_OPEN, 11), tokenizedExpression1.pollFirst()),
                            () -> assertEquals(Token.of(TokenType.VALUE, "dataConverter key", 13), tokenizedExpression1.pollFirst()),
                            () -> assertEquals(Token.of(TokenType.DATA_CONVERTER_CLOSED, 30), tokenizedExpression1.pollFirst())
                    );
                },
                () -> {
                    Deque<Token> tokenizedExpression2 = expressionProcessor.tokenize("@{dataConverter key} expression");
                    assertAll(
                            () -> assertNotNull(tokenizedExpression2),
                            () -> assertEquals(4, tokenizedExpression2.size()),
                            () -> assertEquals(Token.of(TokenType.DATA_CONVERTER_OPEN, 0), tokenizedExpression2.pollFirst()),
                            () -> assertEquals(Token.of(TokenType.VALUE, "dataConverter key", 2), tokenizedExpression2.pollFirst()),
                            () -> assertEquals(Token.of(TokenType.DATA_CONVERTER_CLOSED, 19), tokenizedExpression2.pollFirst()),
                            () -> assertEquals(Token.of(TokenType.VALUE, " expression", 20), tokenizedExpression2.pollFirst())
                    );
                },
                () -> {
                    Deque<Token> tokenizedExpression3 = expressionProcessor.tokenize("custom @{dataConverter key} expression");
                    assertAll(
                            () -> assertNotNull(tokenizedExpression3),
                            () -> assertEquals(5, tokenizedExpression3.size()),
                            () -> assertEquals(Token.of(TokenType.VALUE, "custom ", 0), tokenizedExpression3.pollFirst()),
                            () -> assertEquals(Token.of(TokenType.DATA_CONVERTER_OPEN, 7), tokenizedExpression3.pollFirst()),
                            () -> assertEquals(Token.of(TokenType.VALUE, "dataConverter key", 9), tokenizedExpression3.pollFirst()),
                            () -> assertEquals(Token.of(TokenType.DATA_CONVERTER_CLOSED, 26), tokenizedExpression3.pollFirst()),
                            () -> assertEquals(Token.of(TokenType.VALUE, " expression", 27), tokenizedExpression3.pollFirst())
                    );
                }
        );
    }

    @Test
    void tokenizeExpressionWithTwoDataSources() {
        ValueExpressionProcessor expressionProcessor = new ValueExpressionProcessor(mock(Environment.class));
        Deque<Token> tokenizedExpression1 = expressionProcessor.tokenize("${dataSource key}${dataSource key}");
        assertAll(
                () -> assertNotNull(tokenizedExpression1),
                () -> assertEquals(6, tokenizedExpression1.size()),
                () -> assertEquals(Token.of(TokenType.DATA_SOURCE_OPEN, 0), tokenizedExpression1.pollFirst()),
                () -> assertEquals(Token.of(TokenType.VALUE, "dataSource key", 2), tokenizedExpression1.pollFirst()),
                () -> assertEquals(Token.of(TokenType.DATA_SOURCE_CLOSED, 16), tokenizedExpression1.pollFirst()),
                () -> assertEquals(Token.of(TokenType.DATA_SOURCE_OPEN, 17), tokenizedExpression1.pollFirst()),
                () -> assertEquals(Token.of(TokenType.VALUE, "dataSource key", 19), tokenizedExpression1.pollFirst()),
                () -> assertEquals(Token.of(TokenType.DATA_SOURCE_CLOSED, 33), tokenizedExpression1.pollFirst())
        );
    }

    @Test
    void tokenizeExpressionWithTwoNamedDataSources() {
        ValueExpressionProcessor expressionProcessor = new ValueExpressionProcessor(mock(Environment.class));
        Deque<Token> tokenizedExpression1 = expressionProcessor.tokenize("${[dataSource] dataSource key}${[dataSource]dataSource key}");
        assertAll(
                () -> assertNotNull(tokenizedExpression1),
                () -> assertEquals(6, tokenizedExpression1.size()),
                () -> assertEquals(Token.of(TokenType.DATA_SOURCE_OPEN, 0), tokenizedExpression1.pollFirst()),
                () -> assertEquals(Token.of(TokenType.VALUE, "[dataSource] dataSource key", 2), tokenizedExpression1.pollFirst()),
                () -> assertEquals(Token.of(TokenType.DATA_SOURCE_CLOSED, 29), tokenizedExpression1.pollFirst()),
                () -> assertEquals(Token.of(TokenType.DATA_SOURCE_OPEN, 30), tokenizedExpression1.pollFirst()),
                () -> assertEquals(Token.of(TokenType.VALUE, "[dataSource]dataSource key", 32), tokenizedExpression1.pollFirst()),
                () -> assertEquals(Token.of(TokenType.DATA_SOURCE_CLOSED, 58), tokenizedExpression1.pollFirst())
        );
    }

    @Test
    void tokenizeExpressionWithDataSourceAndDataConverter() {
        ValueExpressionProcessor expressionProcessor = new ValueExpressionProcessor(mock(Environment.class));
        Deque<Token> tokenizedExpression1 = expressionProcessor.tokenize("expression with ${dataSource key} and @{dataConverter key}.");
        assertAll(
                () -> assertNotNull(tokenizedExpression1),
                () -> assertEquals(9, tokenizedExpression1.size()),
                () -> assertEquals(Token.of(TokenType.VALUE, "expression with ", 0), tokenizedExpression1.pollFirst()),
                () -> assertEquals(Token.of(TokenType.DATA_SOURCE_OPEN, 16), tokenizedExpression1.pollFirst()),
                () -> assertEquals(Token.of(TokenType.VALUE, "dataSource key", 18), tokenizedExpression1.pollFirst()),
                () -> assertEquals(Token.of(TokenType.DATA_SOURCE_CLOSED, 32), tokenizedExpression1.pollFirst()),
                () -> assertEquals(Token.of(TokenType.VALUE, " and ", 33), tokenizedExpression1.pollFirst()),
                () -> assertEquals(Token.of(TokenType.DATA_CONVERTER_OPEN, 38), tokenizedExpression1.pollFirst()),
                () -> assertEquals(Token.of(TokenType.VALUE, "dataConverter key", 40), tokenizedExpression1.pollFirst()),
                () -> assertEquals(Token.of(TokenType.DATA_CONVERTER_CLOSED, 57), tokenizedExpression1.pollFirst()),
                () -> assertEquals(Token.of(TokenType.VALUE, ".", 58), tokenizedExpression1.pollFirst())
        );
    }

    @Test
    void tokenizeExpressionWithInnerDataSource() {
        ValueExpressionProcessor expressionProcessor = new ValueExpressionProcessor(mock(Environment.class));
        Deque<Token> tokenizedExpression1 = expressionProcessor.tokenize("expression with ${[dataSource] with inner ${dataSource key}}.");
        assertAll(
                () -> assertNotNull(tokenizedExpression1),
                () -> assertEquals(8, tokenizedExpression1.size()),
                () -> assertEquals(Token.of(TokenType.VALUE, "expression with ", 0), tokenizedExpression1.pollFirst()),
                () -> assertEquals(Token.of(TokenType.DATA_SOURCE_OPEN, 16), tokenizedExpression1.pollFirst()),
                () -> assertEquals(Token.of(TokenType.VALUE, "[dataSource] with inner ", 18), tokenizedExpression1.pollFirst()),
                () -> assertEquals(Token.of(TokenType.DATA_SOURCE_OPEN, 42), tokenizedExpression1.pollFirst()),
                () -> assertEquals(Token.of(TokenType.VALUE, "dataSource key", 44), tokenizedExpression1.pollFirst()),
                () -> assertEquals(Token.of(TokenType.DATA_SOURCE_CLOSED, 58), tokenizedExpression1.pollFirst()),
                () -> assertEquals(Token.of(TokenType.DATA_SOURCE_CLOSED, 59), tokenizedExpression1.pollFirst()),
                () -> assertEquals(Token.of(TokenType.VALUE, ".", 60), tokenizedExpression1.pollFirst())
        );
    }

    @Test
    void tokenizeExpressionWithInnerDataConverter() {
        ValueExpressionProcessor expressionProcessor = new ValueExpressionProcessor(mock(Environment.class));
        Deque<Token> tokenizedExpression1 = expressionProcessor.tokenize("expression with @{[dataConverter] with inner @{dataConverter key}}.");
        assertAll(
                () -> assertNotNull(tokenizedExpression1),
                () -> assertEquals(8, tokenizedExpression1.size()),
                () -> assertEquals(Token.of(TokenType.VALUE, "expression with ", 0), tokenizedExpression1.pollFirst()),
                () -> assertEquals(Token.of(TokenType.DATA_CONVERTER_OPEN, 16), tokenizedExpression1.pollFirst()),
                () -> assertEquals(Token.of(TokenType.VALUE, "[dataConverter] with inner ", 18), tokenizedExpression1.pollFirst()),
                () -> assertEquals(Token.of(TokenType.DATA_CONVERTER_OPEN, 45), tokenizedExpression1.pollFirst()),
                () -> assertEquals(Token.of(TokenType.VALUE, "dataConverter key", 47), tokenizedExpression1.pollFirst()),
                () -> assertEquals(Token.of(TokenType.DATA_CONVERTER_CLOSED, 64), tokenizedExpression1.pollFirst()),
                () -> assertEquals(Token.of(TokenType.DATA_CONVERTER_CLOSED, 65), tokenizedExpression1.pollFirst()),
                () -> assertEquals(Token.of(TokenType.VALUE, ".", 66), tokenizedExpression1.pollFirst())
        );
    }

    @Test
    void tokenizeExpressionWithInnerDataSourceAndDataConverter() {
        ValueExpressionProcessor expressionProcessor = new ValueExpressionProcessor(mock(Environment.class));
        Deque<Token> tokenizedExpression1 = expressionProcessor.tokenize("expression with @{[dataConverter] with inner ${dataSource key}}.");
        assertAll(
                () -> assertNotNull(tokenizedExpression1),
                () -> assertEquals(8, tokenizedExpression1.size()),
                () -> assertEquals(Token.of(TokenType.VALUE, "expression with ", 0), tokenizedExpression1.pollFirst()),
                () -> assertEquals(Token.of(TokenType.DATA_CONVERTER_OPEN, 16), tokenizedExpression1.pollFirst()),
                () -> assertEquals(Token.of(TokenType.VALUE, "[dataConverter] with inner ", 18), tokenizedExpression1.pollFirst()),
                () -> assertEquals(Token.of(TokenType.DATA_SOURCE_OPEN, 45), tokenizedExpression1.pollFirst()),
                () -> assertEquals(Token.of(TokenType.VALUE, "dataSource key", 47), tokenizedExpression1.pollFirst()),
                () -> assertEquals(Token.of(TokenType.DATA_SOURCE_CLOSED, 61), tokenizedExpression1.pollFirst()),
                () -> assertEquals(Token.of(TokenType.DATA_CONVERTER_CLOSED, 62), tokenizedExpression1.pollFirst()),
                () -> assertEquals(Token.of(TokenType.VALUE, ".", 63), tokenizedExpression1.pollFirst())
        );
    }

    @Test
    void tokenizeExpressionWithEmptyKeys() {
        ValueExpressionProcessor expressionProcessor = new ValueExpressionProcessor(mock(Environment.class));
        assertAll(
                () -> {
                    Deque<Token> tokenizedExpression1 = expressionProcessor.tokenize("expression ${}");
                    assertAll(
                            () -> assertNotNull(tokenizedExpression1),
                            () -> assertEquals(3, tokenizedExpression1.size()),
                            () -> assertEquals(Token.of(TokenType.VALUE, "expression ", 0), tokenizedExpression1.pollFirst()),
                            () -> assertEquals(Token.of(TokenType.DATA_SOURCE_OPEN, 11), tokenizedExpression1.pollFirst()),
                            () -> assertEquals(Token.of(TokenType.DATA_SOURCE_CLOSED, 13), tokenizedExpression1.pollFirst())
                    );
                },
                () -> {
                    Deque<Token> tokenizedExpression2 = expressionProcessor.tokenize("expression @{}");
                    assertAll(
                            () -> assertNotNull(tokenizedExpression2),
                            () -> assertEquals(3, tokenizedExpression2.size()),
                            () -> assertEquals(Token.of(TokenType.VALUE, "expression ", 0), tokenizedExpression2.pollFirst()),
                            () -> assertEquals(Token.of(TokenType.DATA_CONVERTER_OPEN, 11), tokenizedExpression2.pollFirst()),
                            () -> assertEquals(Token.of(TokenType.DATA_CONVERTER_CLOSED, 13), tokenizedExpression2.pollFirst())
                    );
                }
        );
    }

    @Test
    void tokenizeExpressionWithOpenContext() {
        ValueExpressionProcessor expressionProcessor = new ValueExpressionProcessor(mock(Environment.class));
        assertAll(
                () -> assertThrows(StringValueParseException.class,
                        () -> expressionProcessor.tokenize("expression ${dataSource key")),
                () -> assertThrows(StringValueParseException.class,
                        () -> expressionProcessor.tokenize("expression @{dataConverter key")),
                () -> assertThrows(StringValueParseException.class,
                        () -> expressionProcessor.tokenize("expression ${dataSource key} @{dataConverter key")),
                () -> assertThrows(StringValueParseException.class,
                        () -> expressionProcessor.tokenize("expression @{dataConverter key} ${dataSource key")),
                () -> assertThrows(StringValueParseException.class,
                        () -> expressionProcessor.tokenize("expression ${dataSource key @{dataConverter key}")),
                () -> assertThrows(StringValueParseException.class,
                        () -> expressionProcessor.tokenize("expression @{dataConverter key ${dataSource key}"))
        );
    }

}
