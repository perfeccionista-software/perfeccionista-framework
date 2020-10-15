package io.perfeccionista.framework.exceptions.attachments;

import io.perfeccionista.framework.value.number.NumberValue;
import org.jetbrains.annotations.NotNull;

public class SizeAttachmentEntry extends StringAttachmentEntry {

    protected SizeAttachmentEntry(String content) {
        super("Size Comparison Result", content);
    }

    public static SizeAttachmentEntry of(@NotNull int expectedSize, @NotNull int actualSize) {
        return new SizeAttachmentEntry(String.format("             Expected = '%s'\n"
                                                   + "               Actual = '%s'\n", expectedSize, actualSize));
    }

    public static <T extends Number> SizeAttachmentEntry of(@NotNull NumberValue<T> expectedValue) {
        return new SizeAttachmentEntry(expectedValue.toString());
    }

    @Override
    public String getDescription() {
        return this.getContent().orElse("empty");
    }

}
