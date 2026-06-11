package io.perfeccionista.framework.datasource;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDateTime;

public class StashAction {

    private final LocalDateTime actionTime;
    private final StashActionType actionType;
    private final String key;
    private final Object value;
    private final Object oldValue;

    private StashAction(LocalDateTime actionTime, StashActionType actionType, String key, Object value, Object oldValue) {
        this.actionTime = actionTime;
        this.actionType = actionType;
        this.key = key;
        this.value = value;
        this.oldValue = oldValue;
    }

    public static StashAction getAction(@NotNull String key, @Nullable Object value) {
        return new StashAction(LocalDateTime.now(), StashActionType.GET, key, value, null);
    }

    public static StashAction putAction(@NotNull String key, @Nullable Object value) {
        return new StashAction(LocalDateTime.now(), StashActionType.PUT, key, value, null);
    }

    public static StashAction replaceAction(@NotNull String key, @Nullable Object oldValue, @Nullable Object newValue) {
        return new StashAction(LocalDateTime.now(), StashActionType.PUT, key, oldValue, newValue);
    }

    public LocalDateTime getActionTime() {
        return actionTime;
    }

    public StashActionType getActionType() {
        return actionType;
    }

    public String getKey() {
        return key;
    }

    public Object getValue() {
        return value;
    }

    public Object getOldValue() {
        return oldValue;
    }

    @Override
    public String toString() {
        return actionType == StashActionType.REPLACE
                ? actionTime.toString() + " | " + StashActionType.REPLACE + "\n"
                    + "Before: " + key + "=" + String.valueOf(oldValue) + "\n"
                    + " After: " + key + "=" + String.valueOf(value)
                : actionTime.toString() + " | " + actionType + "\n"
                    + key + "=" + String.valueOf(value);
    }

    enum StashActionType {
        GET,
        PUT,
        REPLACE
    }

}
