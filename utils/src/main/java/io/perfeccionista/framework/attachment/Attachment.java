package io.perfeccionista.framework.attachment;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * TODO: JavaDoc
 * TODO: Implementation
 *  - отладочная информация о сущностях
 *  - скриншоты
 *  - вложения
 * Переделать тип эксепшена на класс аттачментЭнтри.
 */
public class Attachment {

    private final Set<AttachmentEntry<?>> entries;

    private Attachment(Set<AttachmentEntry<?>> entries) {
        this.entries = entries;
    }

    public static Attachment of(@NotNull AttachmentEntry<?>... entries) {
        return new Attachment(Arrays.stream(entries).collect(Collectors.toSet()));
    }

    public Attachment addAttachmentEntry(@NotNull AttachmentEntry<?> entry) {
        entries.add(entry);
        return this;
    }

    public Stream<AttachmentEntry<?>> getAttachmentEntries() {
        return entries.stream();
    }

    public Stream<AttachmentEntry<?>> getAttachmentEntriesByType(@NotNull Class<? extends AttachmentEntry> entryType) {
        return entries.stream().filter(entry -> entry.getClass().equals(entryType));
    }

    public Optional<AttachmentEntry<?>> getAttachmentEntry(@NotNull Class<? extends AttachmentEntry> entryType, @NotNull String entryName) {
        return entries.stream()
                .filter(entry -> entry.getClass().equals(entryType))
                .filter(entry -> entry.getName().equals(entryName))
                .findFirst();
    }

}
