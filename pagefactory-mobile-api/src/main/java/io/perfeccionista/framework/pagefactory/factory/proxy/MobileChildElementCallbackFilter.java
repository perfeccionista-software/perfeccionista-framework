package io.perfeccionista.framework.pagefactory.factory.proxy;

import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElement;
import net.sf.cglib.proxy.CallbackFilter;

import java.lang.reflect.Method;

public class MobileChildElementCallbackFilter implements CallbackFilter {

    private final Class<? extends MobileChildElement> mobileChildElementImplementationClass;

    public MobileChildElementCallbackFilter(Class<? extends MobileChildElement> mobileChildElementImplementationClass) {
        this.mobileChildElementImplementationClass = mobileChildElementImplementationClass;
    }

    @Override
    public int accept(Method method) {
        return method.isDefault() || method.getDeclaringClass().isAssignableFrom(this.mobileChildElementImplementationClass)
                ? 0
                : 1;
    }

}
