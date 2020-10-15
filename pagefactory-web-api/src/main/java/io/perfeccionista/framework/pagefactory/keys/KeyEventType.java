package io.perfeccionista.framework.pagefactory.keys;

public enum KeyEventType {

    KEY_PRESS("keypress"),
    KEY_DOWN("keydown"),
    KEY_UP("keyup");

    private final String eventName;

    KeyEventType(String eventName) {
        this.eventName = eventName;
    }

    public String getEventName() {
        return eventName;
    }

}
