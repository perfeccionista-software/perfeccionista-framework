package io.perfeccionista.framework.pagefactory.keys;

public class CustomKey implements Key {

    private final char keyCode;

    public CustomKey(char keyCode) {
        this.keyCode = keyCode;
    }

    @Override
    public char getKeyCode() {
        return keyCode;
    }

    @Override
    public String toString() {
        return String.valueOf(keyCode);
    }

}