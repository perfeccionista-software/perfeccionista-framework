package io.perfeccionista.framework.pagefactory.emulator.keys;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.perfeccionista.framework.json.JsonSerializable;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;
import java.util.ArrayDeque;
import java.util.Deque;

import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;

// TODO: Добавить возможность добавлять куски текста
public class KeyEventsChain implements JsonSerializable {

    private final Deque<KeyEvent> keyEvents;
    private int pressedKeysCounter = 0;

    private KeyEventsChain() {
        this.keyEvents = new ArrayDeque<>();
    }

    public static KeyEventsChain builder() {
        return new KeyEventsChain();
    }

    /**
     * Нажатие клавиши
     * @param key
     * @return
     */
    public KeyEventsChain keyDown(@NotNull Key key) {
        keyEvents.addLast(KeyEvent.keyDown(key));
        pressedKeysCounter++;
        return this;
    }

    /**
     * Отпускание клавиши
     * @param key
     * @return
     */
    public KeyEventsChain keyUp(@NotNull Key key) {
        keyEvents.addLast(KeyEvent.keyUp(key));
        pressedKeysCounter--;
        return this;
    }

    /**
     * Нажатие и отпускание клавиши
     * @param key
     * @return
     */
    public KeyEventsChain keyPress(@NotNull Key key) {
        keyEvents.addLast(KeyEvent.keyPress(key));
        return this;
    }

    /**
     * Нажатие и отпускание клавиши
     * @param text
     * @return
     */
    public KeyEventsChain addText(@NotNull String text) {
        for (char key : text.toCharArray()) {
            keyEvents.addLast(KeyEvent.keyPress(Keys.of(key)));
        }
        return this;
    }

    /**
     * Устанавливает задержку к последнему событию, если оно есть.
     * @param delay
     * @return
     */
    public KeyEventsChain setDelay(@NotNull Duration delay) {
        KeyEvent lastKeyEvent = keyEvents.peekLast();
        if (lastKeyEvent != null) {
            lastKeyEvent.setDelay(delay);
        }
        return this;
    }

    /**
     * Проверяем, что все клавиши по завершению отжаты
     * @return
     */
    protected KeyEventsChain validate() {
        if (pressedKeysCounter != 0) {
            // TODO: Exception
        }
        return this;
    }

    public Deque<KeyEvent> getKeyEvents() {
        return new ArrayDeque<>(keyEvents);
    }

    @Override
    public @NotNull JsonNode toJson() {
        ObjectNode rootNode = createObjectNode();
        ArrayNode keyEventsNode = rootNode.putArray("keyEvents");
        keyEvents.forEach(keyEvent -> keyEventsNode
                .add(createObjectNode().put(keyEvent.getType().getEventName(), keyEvent.getKey().getKeyCode())));
        return rootNode;
    }

    @Override
    public String toString() {
        return toJson().toString();
    }

}
