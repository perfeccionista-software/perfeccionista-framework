package io.perfeccionista.framework.exceptions.attachments.processor;

import io.perfeccionista.framework.exceptions.attachments.Attachment;
import org.jetbrains.annotations.NotNull;

public interface AttachmentProcessor {

    /**
     * Интерфейс используется при обработке исключений для корректного вывода приложенных аттачментов.
     * @param attachment
     * @return Строка, которая содержит в себе текстовое представление аттачментов,
     * которые должны быть выведены перед непосредственно стектрейсом ошибки
     */
    String processAttachment(@NotNull Attachment attachment);

    default String getDelimiter(boolean withBreak) {
        if (withBreak) {
            return "-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n";
        } else {
            return "-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------";
        }
    }

}
