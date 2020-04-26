package io.perfeccionista.framework.utils;

import io.perfeccionista.framework.exceptions.ExtractingClipboardValueException;

import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import static io.perfeccionista.framework.exceptions.messages.UtilsMessages.CLIPBOARD_VALUE_NOT_AVAILABLE;

public class ToolkitUtils {

    private ToolkitUtils() {
    }

    public static void copyToClipboard(String textToCopy) {
        Toolkit.getDefaultToolkit()
                .getSystemClipboard()
                .setContents(new StringSelection(textToCopy), null);
    }

    public static String getFromClipboard() {
        try {
            return (String) Toolkit.getDefaultToolkit()
                    .getSystemClipboard()
                    .getData(DataFlavor.stringFlavor);
        } catch (IOException | UnsupportedFlavorException e) {
            throw new ExtractingClipboardValueException(CLIPBOARD_VALUE_NOT_AVAILABLE.getMessage(), e);
        }
    }

}
