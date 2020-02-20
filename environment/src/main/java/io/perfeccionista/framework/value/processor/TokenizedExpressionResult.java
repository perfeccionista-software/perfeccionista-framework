package io.perfeccionista.framework.value.processor;

import org.jetbrains.annotations.NotNull;

import java.util.Deque;

public class TokenizedExpressionResult {

    private final TokenType tokenContext;
    private final Deque<Token> rawTokens;
    private final Object result;

    private TokenizedExpressionResult(TokenType tokenContext, Deque<Token> rawTokens, Object result) {
        this.tokenContext = tokenContext;
        this.rawTokens = rawTokens;
        this.result = result;
    }

    public static TokenizedExpressionResult of(@NotNull TokenType tokenContext, Deque<Token> rawTokens, @NotNull Object result) {
        return new TokenizedExpressionResult(tokenContext, rawTokens, result);
    }

    public static TokenizedExpressionResult of(@NotNull TokenType tokenContext, Deque<Token> rawTokens) {
        return new TokenizedExpressionResult(tokenContext, rawTokens, null);
    }

    /**
     * Контекст обрабатываемого выражения
     */
    public @NotNull TokenType getTokenContext() {
        return tokenContext;
    }

    /**
     * Набор необработанных токенов
     */
    public Deque<Token> getRawTokens() {
        return rawTokens;
    }

    /**
     * Результат выполнения токенного выражения
     */
    public @NotNull Object getResult() {
        return result;
    }

}
