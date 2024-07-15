package io.perfeccionista.framework.exceptions.attachments;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;

public class TextAttachmentEntry extends AttachmentEntry<String> {

    protected TextAttachmentEntry(String name, String content) {
        super(name, content);
    }

    public static TextAttachmentEntry of(@NotNull String name, int content) {
        return new TextAttachmentEntry(name, Integer.toString(content));
    }

    public static TextAttachmentEntry of(@NotNull String name, long content) {
        return new TextAttachmentEntry(name, Long.toString(content));
    }

    public static TextAttachmentEntry of(@NotNull String name, double content) {
        return new TextAttachmentEntry(name, Double.toString(content));
    }

    public static TextAttachmentEntry of(@NotNull String name, @Nullable BigDecimal content) {
        return new TextAttachmentEntry(name, Objects.nonNull(content) ? content.toString() : "null");
    }

    public static TextAttachmentEntry of(@NotNull String name, char content) {
        return new TextAttachmentEntry(name, Character.toString(content));
    }

    public static TextAttachmentEntry of(@NotNull String name, @Nullable String content) {
        return new TextAttachmentEntry(name, content);
    }

    public static TextAttachmentEntry of(@NotNull String name, @NotNull Collection<String> content) {
        return new TextAttachmentEntry(name, String.join("\n", content));
    }

    @Override
    public String getDescription() {
        return this.getContent().orElse("empty");
    }

}
