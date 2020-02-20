package io.perfeccionista.framework.value;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public final class ValueDeclaration {

    private final String valueExpression;
    private final String valueCondition;
    private final String valueConditionDeclaration;

    private ValueDeclaration(@NotNull String valueExpression, @Nullable String valueCondition, @Nullable String valueConditionDeclaration) {
        this.valueExpression = valueExpression;
        this.valueCondition = valueCondition;
        this.valueConditionDeclaration = valueConditionDeclaration;
    }

    public static ValueDeclaration of(@NotNull String valueExpression) {
        return new ValueDeclaration(valueExpression, null, null);
    }

    public static ValueDeclaration of(@NotNull String valueExpression, @NotNull String valueCondition, @NotNull String valueConditionDeclaration) {
        return new ValueDeclaration(valueExpression, valueCondition, valueConditionDeclaration);
    }

    public @NotNull String getValueExpression() {
        return valueExpression;
    }

    public Optional<String> getValueCondition() {
        return Optional.ofNullable(valueCondition);
    }

    public Optional<String> getValueConditionDeclaration() {
        return Optional.ofNullable(valueConditionDeclaration);
    }

}
