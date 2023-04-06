package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.pagefactory.elements.actions.WebMappedElementAction;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElementBase;
import io.perfeccionista.framework.pagefactory.elements.options.DownloadOptions;
import io.perfeccionista.framework.plugin.ActionMethodType;
import org.jetbrains.annotations.NotNull;

import java.io.File;

import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.DOWNLOAD_FILE_METHOD;

public interface WebFileDownloadAvailable extends WebChildElementBase {

    @ActionMethodType
    @WebMappedElementAction(DOWNLOAD_FILE_METHOD)
    @NotNull File download();

    @ActionMethodType
    @WebMappedElementAction(DOWNLOAD_FILE_METHOD)
    @NotNull File download(@NotNull DownloadOptions options);

}
