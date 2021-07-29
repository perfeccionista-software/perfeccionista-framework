package io.perfeccionista.framework.pagefactory.operation.type;

import io.perfeccionista.framework.invocation.runner.InvocationInfo;
import io.perfeccionista.framework.pagefactory.elements.WebFileInput;
import io.perfeccionista.framework.pagefactory.operation.handler.EndpointHandler;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

import static io.perfeccionista.framework.invocation.runner.InvocationInfo.actionInvocation;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.UPLOAD_FROM_FILE_METHOD;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.getDeclaredConstructor;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.newInstance;

public class WebUploadFromFileOperationType implements WebElementOperationType<Void> {

    private final WebFileInput element;
    private final List<Path> filesToUpload;

    private final InvocationInfo invocationInfo;

    private WebUploadFromFileOperationType(WebFileInput element, List<Path> filesToUpload) {
        this.element = element;
        this.filesToUpload = filesToUpload;
        var elementName = element.getElementIdentifier().getLastUsedName();
        this.invocationInfo = actionInvocation(UPLOAD_FROM_FILE_METHOD, elementName, filesToUpload.stream()
                .map(Path::toString)
                .collect(Collectors.joining(", ")));
    }

    public static WebUploadFromFileOperationType of(@NotNull WebFileInput element, @NotNull List<Path> filesToUpload) {
        return new WebUploadFromFileOperationType(element, filesToUpload);
    }

    @Override
    public @NotNull InvocationInfo getInvocationName() {
        return this.invocationInfo;
    }

    @Override
    public @NotNull EndpointHandler<Void> getEndpointHandler() {
        Class<? extends EndpointHandler<Void>> endpointHandlerClass = element.getEndpointHandler(UPLOAD_FROM_FILE_METHOD, Void.class);
        Constructor<? extends EndpointHandler<Void>> constructor = getDeclaredConstructor(endpointHandlerClass);
        return newInstance(constructor, filesToUpload);
    }

}
