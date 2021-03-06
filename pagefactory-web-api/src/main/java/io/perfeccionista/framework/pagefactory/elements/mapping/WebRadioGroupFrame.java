package io.perfeccionista.framework.pagefactory.elements.mapping;

import io.perfeccionista.framework.exceptions.MappedBlockNotFound;
import io.perfeccionista.framework.exceptions.attachments.WebElementAttachmentEntry;
import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.WebRadioGroup;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.MAPPED_BLOCK_IMPLEMENTATION_NOT_FOUND;

public class WebRadioGroupFrame<T extends WebBlock> {

    private final WebRadioGroup element;
    private final T mappedBlockFrame;

    public WebRadioGroupFrame(@NotNull WebRadioGroup element,
                              @Nullable T mappedBlockFrame) {
        this.element = element;
        this.mappedBlockFrame = mappedBlockFrame;
    }

    public @NotNull T getMappedBlockFrame() {
        if (Objects.isNull(mappedBlockFrame)) {
            throw MappedBlockNotFound.exception(MAPPED_BLOCK_IMPLEMENTATION_NOT_FOUND.getMessage(element.getClass().getCanonicalName()))
                    .addLastAttachmentEntry(WebElementAttachmentEntry.of(element));
        }
        return mappedBlockFrame;
    }

}
