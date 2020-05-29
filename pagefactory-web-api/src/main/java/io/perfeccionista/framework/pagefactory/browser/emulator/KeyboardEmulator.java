package io.perfeccionista.framework.pagefactory.browser.emulator;

public interface KeyboardEmulator {

    KeyboardEmulator pressKey(CharSequence key);

    KeyboardEmulator releaseKey(CharSequence key);

    KeyboardEmulator sendKeys(CharSequence... key);

}
