package io.perfeccionista.framework.pagefactory.elements.mapping;

import io.perfeccionista.framework.exceptions.MappedBlockNotFound;
import io.perfeccionista.framework.exceptions.attachments.MobileElementAttachmentEntry;
import io.perfeccionista.framework.pagefactory.elements.MobileBlock;
import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.MAPPED_BLOCK_IMPLEMENTATION_NOT_FOUND;

public class MobileListFrame<T extends MobileBlock> {

    private final MobileChildElement element;
    private final T mappedBlockFrame;

    public MobileListFrame(@NotNull MobileChildElement element,
                           @Nullable T mappedBlockFrame) {
        this.element = element;
        this.mappedBlockFrame = mappedBlockFrame;
    }

    public @NotNull T getMappedBlockFrame() {
        if (Objects.isNull(mappedBlockFrame)) {
            throw MappedBlockNotFound.exception(MAPPED_BLOCK_IMPLEMENTATION_NOT_FOUND.getMessage(element.getClass().getCanonicalName()))
                    .addLastAttachmentEntry(MobileElementAttachmentEntry.of(element));
        }
        return mappedBlockFrame;
    }

}
