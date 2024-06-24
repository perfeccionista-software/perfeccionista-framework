package io.perfeccionista.framework.exceptions.attachments;

import io.perfeccionista.framework.preconditions.Preconditions;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Optional;
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

    private final Deque<AttachmentEntry<?>> entries;

    private Attachment(Deque<AttachmentEntry<?>> entries) {
        this.entries = entries;
    }

    public static Attachment empty() {
        return new Attachment(new ArrayDeque<>());
    }

    public static Attachment with(@NotNull AttachmentEntry<?>... entries) {
        return new Attachment(new ArrayDeque<>(Arrays.asList(entries)));
    }

    public boolean isEmpty() {
        return entries.isEmpty();
    }

    public Attachment addFirstAttachmentEntry(@NotNull AttachmentEntry<?> entry) {
        Preconditions.notNull(entry, "Attachment entry must not be null");
        entries.addFirst(entry);
        return this;
    }

    public Attachment addLastAttachmentEntry(@NotNull AttachmentEntry<?> entry) {
        Preconditions.notNull(entry, "Attachment entry must not be null");
        entries.addLast(entry);
        return this;
    }

    public Stream<AttachmentEntry<?>> getAttachmentEntries() {
        return entries.stream();
    }

    @SuppressWarnings("unchecked")
    public <T extends AttachmentEntry<?>> Stream<T> getAttachmentEntriesByType(@NotNull Class<T> entryType) {
        Preconditions.notNull(entryType, "Attachment entry type must not be null");
        return entries.stream().filter(entry -> entry.getClass().equals(entryType)).map(entry -> (T) entry);
    }

    @SuppressWarnings("unchecked")
    public <T extends AttachmentEntry<?>> Optional<T> getAttachmentEntry(@NotNull Class<T> entryType, @NotNull String entryName) {
        Preconditions.notNull(entryType, "Attachment entry type must not be null");
        Preconditions.notNull(entryName, "Attachment entry name must not be null");
        return entries.stream()
                .filter(entry -> entry.getClass().equals(entryType))
                .filter(entry -> entry.getName().equals(entryName))
                .map(entry -> (T) entry)
                .findFirst();
    }

}
