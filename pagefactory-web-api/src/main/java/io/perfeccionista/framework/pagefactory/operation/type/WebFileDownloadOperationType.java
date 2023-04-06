package io.perfeccionista.framework.pagefactory.operation.type;

import io.perfeccionista.framework.invocation.runner.InvocationInfo;
import io.perfeccionista.framework.pagefactory.elements.methods.WebFileDownloadAvailable;
import io.perfeccionista.framework.pagefactory.elements.options.DownloadOptions;
import io.perfeccionista.framework.pagefactory.operation.handler.EndpointHandler;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.lang.reflect.Constructor;

import static io.perfeccionista.framework.invocation.runner.InvocationInfo.getterInvocation;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.DOWNLOAD_FILE_METHOD;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.getDeclaredConstructor;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.newInstance;

public class WebFileDownloadOperationType implements WebElementOperationType<File> {

    private final WebFileDownloadAvailable element;
    private final DownloadOptions options;

    private final InvocationInfo invocationInfo;

    private WebFileDownloadOperationType(WebFileDownloadAvailable element, DownloadOptions options) {
        this.element = element;
        this.options = options;
        String elementName = element.getElementIdentifier().getLastUsedName();
        this.invocationInfo = getterInvocation(DOWNLOAD_FILE_METHOD, elementName, options.toString());
    }

    public static WebFileDownloadOperationType of(@NotNull WebFileDownloadAvailable element, @NotNull DownloadOptions options) {
        return new WebFileDownloadOperationType(element, options);
    }

    @Override
    public @NotNull InvocationInfo getInvocationName() {
        return this.invocationInfo;
    }

    @Override
    public @NotNull EndpointHandler<File> getEndpointHandler() {
        Class<? extends EndpointHandler<File>> endpointHandlerClass = element.getEndpointHandler(DOWNLOAD_FILE_METHOD, File.class);
        Constructor<? extends EndpointHandler<File>> constructor = getDeclaredConstructor(endpointHandlerClass);
        return newInstance(constructor, options);
    }

}
