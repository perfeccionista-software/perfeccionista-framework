package io.perfeccionista.framework.exceptions.attachments;

import io.perfeccionista.framework.value.number.NumberValue;
import io.perfeccionista.framework.value.object.ObjectValue;
import io.perfeccionista.framework.value.string.StringValue;
import org.jetbrains.annotations.NotNull;

public class ValueAttachmentEntry extends StringAttachmentEntry {

    protected ValueAttachmentEntry(String content) {
        super("Value Comparison Result", content);
    }

    public static ValueAttachmentEntry of(@NotNull String expectedString, @NotNull String actualString) {
        return new ValueAttachmentEntry(String.format("             Expected = '%s'\n"
                + "               Actual = '%s'\n", expectedString, actualString));
    }

    public static ValueAttachmentEntry of(@NotNull ObjectValue expectedObjectValue) {
        return new ValueAttachmentEntry(expectedObjectValue.toString());
    }

    public static ValueAttachmentEntry of(@NotNull StringValue expectedStringValue) {
        return new ValueAttachmentEntry(expectedStringValue.toString());
    }

    public static ValueAttachmentEntry of(@NotNull StringValue expectedStringValue, @NotNull String allActualValues) {
        return new ValueAttachmentEntry(expectedStringValue.toString() + '\n'
                + String.format("               All actual values = '%s'\n", allActualValues));
    }

    public static ValueAttachmentEntry of(int expectedNumber, int actualNumber) {
        return new ValueAttachmentEntry(String.format("             Expected = '%s'\n"
                + "               Actual = '%s'\n", expectedNumber, actualNumber));
    }

    public static <T extends Number> ValueAttachmentEntry of(@NotNull NumberValue<T> expectedNumberValue) {
        return new ValueAttachmentEntry(expectedNumberValue.toString());
    }

    public static <T extends Number> ValueAttachmentEntry of(@NotNull NumberValue<T> expectedNumberValue, @NotNull String allActualValues) {
        return new ValueAttachmentEntry(expectedNumberValue.toString() + '\n'
                + String.format("               All actual values = '%s'\n", allActualValues));
    }

    @Override
    public String getDescription() {
        return this.getContent().orElse("empty");
    }

}
