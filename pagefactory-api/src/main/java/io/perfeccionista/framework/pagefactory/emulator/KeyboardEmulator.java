package io.perfeccionista.framework.pagefactory.emulator;

public interface KeyboardEmulator {

    KeyboardEmulator pressKey(CharSequence key);

    KeyboardEmulator releaseKey(CharSequence key);

    KeyboardEmulator sendKeys(CharSequence... key);

}
