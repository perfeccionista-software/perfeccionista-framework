package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.pagefactory.elements.actions.WebMappedElementAction;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElementBase;
import io.perfeccionista.framework.plugin.ActionMethodType;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;

import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.IS_IMAGE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SAVE_IMAGE_TO_FILE_METHOD;

public interface WebImageElement<T extends WebImageElement> extends WebChildElementBase {

    @WebMappedElementAction(IS_IMAGE_METHOD)
    boolean isImage();

    @ActionMethodType
    @WebMappedElementAction(SAVE_IMAGE_TO_FILE_METHOD)
    T saveImage(@NotNull Path filePath);

}
