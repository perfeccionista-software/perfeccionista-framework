package io.perfeccionista.framework.pagefactory.operation.type;

import io.perfeccionista.framework.invocation.runner.InvocationInfo;
import io.perfeccionista.framework.pagefactory.elements.methods.WebImageElement;
import io.perfeccionista.framework.pagefactory.operation.handler.EndpointHandler;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;
import java.nio.file.Path;

import static io.perfeccionista.framework.invocation.runner.InvocationInfo.actionInvocation;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SAVE_IMAGE_TO_FILE_METHOD;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.getDeclaredConstructor;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.newInstance;

public class WebSaveImageToFileOperationType implements WebElementOperationType<Void> {

    private final WebImageElement element;
    private final Path file;

    private final InvocationInfo invocationInfo;

    private WebSaveImageToFileOperationType(WebImageElement element, Path file) {
        this.element = element;
        this.file = file;
        String elementName = element.getElementIdentifier().getLastUsedName();
        this.invocationInfo = actionInvocation(SAVE_IMAGE_TO_FILE_METHOD, elementName, file.toString());
    }

    public static WebSaveImageToFileOperationType of(@NotNull WebImageElement element, @NotNull Path filePath) {
        return new WebSaveImageToFileOperationType(element, filePath);
    }

    @Override
    public @NotNull InvocationInfo getInvocationName() {
        return this.invocationInfo;
    }

    @Override
    public @NotNull EndpointHandler<Void> getEndpointHandler() {
        Class<? extends EndpointHandler<Void>> endpointHandlerClass = element.getEndpointHandler(SAVE_IMAGE_TO_FILE_METHOD, Void.class);
        Constructor<? extends EndpointHandler<Void>> constructor = getDeclaredConstructor(endpointHandlerClass);
        return newInstance(constructor, element, file);
    }

}
