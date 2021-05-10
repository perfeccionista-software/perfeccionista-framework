package io.perfeccionista.framework.pagefactory.factory.proxy;

import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import net.sf.cglib.proxy.CallbackFilter;

import java.lang.reflect.Method;

public class WebChildElementCallbackFilter implements CallbackFilter {

    private final Class<? extends WebChildElement> webChildElementImplementationClass;

    public WebChildElementCallbackFilter(Class<? extends WebChildElement> webChildElementImplementationClass) {
        this.webChildElementImplementationClass = webChildElementImplementationClass;
    }

    @Override
    public int accept(Method method) {
        return method.isDefault() || method.getDeclaringClass().isAssignableFrom(this.webChildElementImplementationClass)
                ? 0
                : 1;
    }

}
