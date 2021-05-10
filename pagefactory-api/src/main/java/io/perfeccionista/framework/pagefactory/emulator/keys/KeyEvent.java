package io.perfeccionista.framework.pagefactory.emulator.keys;

import com.fasterxml.jackson.databind.JsonNode;
import io.perfeccionista.framework.json.JsonSerializable;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;
import java.util.Objects;

import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;

public class KeyEvent implements JsonSerializable {

    private final KeyEventType keyEventType;
    private final Key key;
    private Duration delay;

    private KeyEvent(@NotNull KeyEventType keyEventType, @NotNull Key key) {
        this.keyEventType = keyEventType;
        this.key = key;
        this.delay = null;
    }

    public static KeyEvent keyUp(@NotNull Key key) {
        return new KeyEvent(KeyEventType.KEY_UP, key);
    }

    public static KeyEvent keyDown(@NotNull Key key) {
        return new KeyEvent(KeyEventType.KEY_DOWN, key);
    }

    public static KeyEvent keyPress(@NotNull Key key) {
        return new KeyEvent(KeyEventType.KEY_PRESS, key);
    }

    public KeyEvent setDelay(@NotNull Duration delay) {
        this.delay = delay;
        return this;
    }

    public @NotNull KeyEventType getType() {
        return keyEventType;
    }

    public @NotNull Key getKey() {
        return key;
    }

    public @NotNull Duration getDelay() {
        return Objects.nonNull(delay) ? delay : Duration.ZERO;
    }

    @Override
    public JsonNode toJson() {
        return createObjectNode()
                .put("keyEventType", keyEventType.getEventName())
                .put("keyCode", key.getKeyCode())
                .put("delay", delay.toMillis());
    }

}
