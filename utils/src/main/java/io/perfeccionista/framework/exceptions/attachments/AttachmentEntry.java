package io.perfeccionista.framework.exceptions.attachments;

import io.perfeccionista.framework.preconditions.Preconditions;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

/**
 * TODO: JavaDoc
 * Этот класс можно расширять создавая свои типы записей отчета, которые потом с помощью ресолвера
 * отправлять в аллюр отчет или другую систему.
 * TODO: Тесты на заполнение отчетов
 * TODO: Для всех типов аттачментов переписать toString() и toJson().
 */
public abstract class AttachmentEntry<T> {

    private final LocalDateTime created;
    private final String name;
    private final T content;

    protected AttachmentEntry(@NotNull String name, @Nullable T content) {
        Preconditions.notNull(name, "Attachment entry name must not be null");
        this.created = LocalDateTime.now();
        this.name = name;
        this.content = content;
    }

    @NotNull
    public LocalDateTime getCreated() {
        return created;
    }

    @NotNull
    public String getName() {
        return name;
    }

    public Optional<T> getContent() {
        return Optional.ofNullable(content);
    }

    /**
     * Текстовое описание приложенного аттачмента
     */
    public abstract String getDescription();

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        AttachmentEntry<?> that = (AttachmentEntry<?>) object;
        return this.name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClass(), name);
    }

}
