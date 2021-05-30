package io.perfeccionista.framework.pagefactory.emulator.keys;

/**
 * Возможно, к чару нужно добавить еще и name для именования в событии клавиши шифт/контрол и тп
 */
public enum Keys implements Key {

    NULL         ('\uE000'),
    CANCEL       ('\uE001'), // ^break
    HELP         ('\uE002'),
    BACK_SPACE   ('\uE003'),
    TAB          ('\uE004'),
    CLEAR        ('\uE005'),
    RETURN       ('\uE006'),
    ENTER        ('\uE007'),
    SHIFT        ('\uE008'),
    LEFT_SHIFT   ('\uE008'),
    CONTROL      ('\uE009'),
    LEFT_CONTROL ('\uE009'),
    ALT          ('\uE00A'),
    LEFT_ALT     ('\uE00A'),
    PAUSE        ('\uE00B'),
    ESCAPE       ('\uE00C'),
    SPACE        ('\uE00D'),
    PAGE_UP      ('\uE00E'),
    PAGE_DOWN    ('\uE00F'),
    END          ('\uE010'),
    HOME         ('\uE011'),
    LEFT         ('\uE012'),
    ARROW_LEFT   ('\uE012'),
    UP           ('\uE013'),
    ARROW_UP     ('\uE013'),
    RIGHT        ('\uE014'),
    ARROW_RIGHT  ('\uE014'),
    DOWN         ('\uE015'),
    ARROW_DOWN   ('\uE015'),
    INSERT       ('\uE016'),
    DELETE       ('\uE017'),
    SEMICOLON    ('\uE018'),
    EQUALS       ('\uE019'),

    // Number pad keys
    NUMPAD0      ('\uE01A'),
    NUMPAD1      ('\uE01B'),
    NUMPAD2      ('\uE01C'),
    NUMPAD3      ('\uE01D'),
    NUMPAD4      ('\uE01E'),
    NUMPAD5      ('\uE01F'),
    NUMPAD6      ('\uE020'),
    NUMPAD7      ('\uE021'),
    NUMPAD8      ('\uE022'),
    NUMPAD9      ('\uE023'),
    MULTIPLY     ('\uE024'),
    ADD          ('\uE025'),
    SEPARATOR    ('\uE026'),
    SUBTRACT     ('\uE027'),
    DECIMAL      ('\uE028'),
    DIVIDE       ('\uE029'),

    // Function keys
    F1           ('\uE031'),
    F2           ('\uE032'),
    F3           ('\uE033'),
    F4           ('\uE034'),
    F5           ('\uE035'),
    F6           ('\uE036'),
    F7           ('\uE037'),
    F8           ('\uE038'),
    F9           ('\uE039'),
    F10          ('\uE03A'),
    F11          ('\uE03B'),
    F12          ('\uE03C'),

    META         ('\uE03D'),
    COMMAND      ('\uE03D'),

    ZENKAKU_HANKAKU ('\uE040');

    private final char keyCode;

    Keys(char keyCode) {
        this.keyCode = keyCode;
    }

    public static Key of(char keyCode) {
        return new CustomKey(keyCode);
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