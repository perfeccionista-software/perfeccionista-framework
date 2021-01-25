package io.perfeccionista.framework.pagefactory.emulator;

import io.perfeccionista.framework.measurements.Location2D;

public interface MouseEmulator {

    MouseEmulator click(Location2D location);

    MouseEmulator rightClick(Location2D location);

    MouseEmulator doubleClick(Location2D location);

    MouseEmulator mouseDown(Location2D location);

    MouseEmulator mouseUp(Location2D location);

    MouseEmulator mouseMove(Location2D location);

    MouseEmulator dragAndDrop(Location2D sourceLocation, Location2D targetLocation);

}
