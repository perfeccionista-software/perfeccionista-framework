package io.perfeccionista.framework.pagefactory.operation.type;

import io.perfeccionista.framework.invocation.runner.InvocationInfo;
import io.perfeccionista.framework.pagefactory.elements.methods.WebFileUploadAvailable;
import io.perfeccionista.framework.pagefactory.elements.options.UploadOptions;
import io.perfeccionista.framework.pagefactory.operation.handler.EndpointHandler;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.perfeccionista.framework.invocation.runner.InvocationInfo.getterInvocation;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.UPLOAD_FILE_METHOD;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.getDeclaredConstructor;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.newInstance;

public class WebFileUploadOperationType implements WebElementOperationType<Void> {

    private final WebFileUploadAvailable element;
    private final UploadOptions options;
    private final List<String> resourceNames;
    private final List<Path> files;

    private final InvocationInfo invocationInfo;

    private WebFileUploadOperationType(WebFileUploadAvailable element, UploadOptions options,
                                       List<String> resourceNames, List<Path> files) {
        this.element = element;
        this.options = options;
        this.resourceNames = resourceNames;
        this.files = files;
        String elementName = element.getElementIdentifier().getLastUsedName();
        // TODO: Добавить в сообщение об ошибке списки ресурсов и путей
        this.invocationInfo = getterInvocation(UPLOAD_FILE_METHOD, elementName, options.toString());
    }

    public static WebFileUploadOperationType of(@NotNull WebFileUploadAvailable element, @NotNull UploadOptions options, String... resourceNames) {
        return new WebFileUploadOperationType(element, options, Arrays.asList(resourceNames), new ArrayList<>());
    }

    public static WebFileUploadOperationType of(@NotNull WebFileUploadAvailable element, @NotNull UploadOptions options, Path... files) {
        return new WebFileUploadOperationType(element, options, new ArrayList<>(), Arrays.asList(files));
    }

    @Override
    public @NotNull InvocationInfo getInvocationName() {
        return this.invocationInfo;
    }

    @Override
    public @NotNull EndpointHandler<Void> getEndpointHandler() {
        Class<? extends EndpointHandler<Void>> endpointHandlerClass = element.getEndpointHandler(UPLOAD_FILE_METHOD, Void.class);
        Constructor<? extends EndpointHandler<Void>> constructor = getDeclaredConstructor(endpointHandlerClass);
        return newInstance(constructor, options, resourceNames, files);
    }

}

