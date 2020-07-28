package io.perfeccionista.framework.attachment;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.Optional;
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

    private final Deque<AttachmentEntry<?>> entries;

    private Attachment(Deque<AttachmentEntry<?>> entries) {
        this.entries = entries;
    }

    public static Attachment of(@NotNull AttachmentEntry<?>... entries) {
        return new Attachment(new ArrayDeque<>(Arrays.asList(entries)));
    }

    public Attachment addAttachmentEntry(@NotNull AttachmentEntry<?> entry) {
        entries.addLast(entry);
        return this;
    }

    public Attachment addAttachmentEntryToTop(@NotNull AttachmentEntry<?> entry) {
        entries.addFirst(entry);
        return this;
    }

    public Stream<AttachmentEntry<?>> getAttachmentEntries() {
        return entries.stream();
    }

    @SuppressWarnings("unchecked")
    public <T extends AttachmentEntry<?>> Stream<T> getAttachmentEntriesByType(@NotNull Class<T> entryType) {
        return entries.stream().filter(entry -> entry.getClass().equals(entryType)).map(entry -> (T) entry);
    }

    @SuppressWarnings("unchecked")
    public <T extends AttachmentEntry<?>> Optional<T> getAttachmentEntry(@NotNull Class<T> entryType, @NotNull String entryName) {
        return entries.stream()
                .filter(entry -> entry.getClass().equals(entryType))
                .filter(entry -> entry.getName().equals(entryName))
                .map(entry -> (T) entry)
                .findFirst();
    }

}
