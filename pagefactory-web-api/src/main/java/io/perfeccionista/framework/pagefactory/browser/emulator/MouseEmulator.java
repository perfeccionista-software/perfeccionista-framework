package io.perfeccionista.framework.pagefactory.browser.emulator;

import io.perfeccionista.framework.pagefactory.elements.methods.Location;

public interface MouseEmulator {

    MouseEmulator click(Location location);

    MouseEmulator rightClick(Location location);

    MouseEmulator doubleClick(Location location);

    MouseEmulator mouseDown(Location location);

    MouseEmulator mouseUp(Location location);

    MouseEmulator mouseMove(Location location);

    MouseEmulator dragAndDrop(Location sourceLocation, Location targetLocation);

}
