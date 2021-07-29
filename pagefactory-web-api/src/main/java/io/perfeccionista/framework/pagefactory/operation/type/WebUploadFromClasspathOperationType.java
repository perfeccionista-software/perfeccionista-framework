package io.perfeccionista.framework.pagefactory.operation.type;

import io.perfeccionista.framework.invocation.runner.InvocationInfo;
import io.perfeccionista.framework.pagefactory.elements.WebFileInput;
import io.perfeccionista.framework.pagefactory.operation.handler.EndpointHandler;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;
import java.util.List;

import static io.perfeccionista.framework.invocation.runner.InvocationInfo.actionInvocation;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.UPLOAD_FROM_CLASSPATH_METHOD;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.getDeclaredConstructor;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.newInstance;

public class WebUploadFromClasspathOperationType implements WebElementOperationType<Void> {

    private final WebFileInput element;
    private final List<String> resourcesToUpload;

    private final InvocationInfo invocationInfo;

    private WebUploadFromClasspathOperationType(WebFileInput element, List<String> resourcesToUpload) {
        this.element = element;
        this.resourcesToUpload = resourcesToUpload;
        var elementName = element.getElementIdentifier().getLastUsedName();
        this.invocationInfo = actionInvocation(UPLOAD_FROM_CLASSPATH_METHOD, elementName, String.join(", ", resourcesToUpload));
    }

    public static WebUploadFromClasspathOperationType of(@NotNull WebFileInput element, @NotNull List<String> resourcesToUpload) {
        return new WebUploadFromClasspathOperationType(element, resourcesToUpload);
    }

    @Override
    public @NotNull InvocationInfo getInvocationName() {
        return this.invocationInfo;
    }

    @Override
    public @NotNull EndpointHandler<Void> getEndpointHandler() {
        Class<? extends EndpointHandler<Void>> endpointHandlerClass = element.getEndpointHandler(UPLOAD_FROM_CLASSPATH_METHOD, Void.class);
        Constructor<? extends EndpointHandler<Void>> constructor = getDeclaredConstructor(endpointHandlerClass);
        return newInstance(constructor, resourcesToUpload);
    }

}
