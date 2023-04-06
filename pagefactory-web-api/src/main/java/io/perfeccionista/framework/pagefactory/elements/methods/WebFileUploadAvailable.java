package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.pagefactory.elements.actions.WebMappedElementAction;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElementBase;
import io.perfeccionista.framework.pagefactory.elements.options.UploadOptions;
import io.perfeccionista.framework.plugin.ActionMethodType;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;

import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.UPLOAD_FILE_METHOD;

public interface WebFileUploadAvailable<T extends WebFileUploadAvailable> extends WebChildElementBase {

    @ActionMethodType
    @WebMappedElementAction(UPLOAD_FILE_METHOD)
    T uploadFromClasspath(@NotNull String... resourceName);

    @ActionMethodType
    @WebMappedElementAction(UPLOAD_FILE_METHOD)
    T uploadFromClasspath(@NotNull UploadOptions options, @NotNull String... resourceName);

    @ActionMethodType
    @WebMappedElementAction(UPLOAD_FILE_METHOD)
    T uploadFromFile(@NotNull Path... file);

    @ActionMethodType
    @WebMappedElementAction(UPLOAD_FILE_METHOD)
    T uploadFromFile(@NotNull UploadOptions options, @NotNull Path... file);

}
