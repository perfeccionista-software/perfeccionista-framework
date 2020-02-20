package io.perfeccionista.framework.value.processor;

import org.jetbrains.annotations.NotNull;

/**
 *
 */
public final class Token {

    private final TokenType type;
    private final String content;
    private final int position;

    private Token(TokenType type, String content, int position) {
        this.type = type;
        this.content = content;
        this.position = position;
    }

    public static Token of(@NotNull TokenType type, int position) {
        return new Token(type, "", position);
    }

    public static Token of(@NotNull TokenType type, @NotNull String content, int position) {
        return new Token(type, content, position);
    }

    public @NotNull TokenType getType() {
        return type;
    }

    public @NotNull String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "Token: {type = " + type + "; content = '" + content + "'; position = " + position + "}";
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null || getClass() != that.getClass()) {
            return false;
        }

        Token token = (Token) that;

        if (position != token.position) {
            return false;
        }
        if (type != token.type) {
            return false;
        }
        return content.equals(token.content);
    }

    @Override
    public int hashCode() {
        int result = type.hashCode();
        result = 31 * result + content.hashCode();
        result = 31 * result + position;
        return result;
    }

}
