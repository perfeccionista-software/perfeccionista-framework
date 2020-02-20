package io.perfeccionista.framework.value.processor;

import org.jetbrains.annotations.NotNull;

import java.util.Deque;

public class TokenizedExpression {

    private final TokenType tokenContext;
    private final Deque<Token> rawTokens;

    private TokenizedExpression(TokenType tokenContext, Deque<Token> rawTokens) {
        this.tokenContext = tokenContext;
        this.rawTokens = rawTokens;
    }

    public static TokenizedExpression of(@NotNull TokenType tokenContext, Deque<Token> rawTokens) {
        return new TokenizedExpression(tokenContext, rawTokens);
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

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("TokenizedExpression: TokenContext = ")
                .append(tokenContext)
                .append("; Tokens = {\n");
        rawTokens.forEach(token -> stringBuilder.append("'")
                .append(token.getContent())
                .append("'\n"));
        return stringBuilder.append("}").toString();
    }

}
