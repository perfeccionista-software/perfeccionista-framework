package io.perfeccionista.framework.pagefactory.factory.proxy;

import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.factory.proxy.frame.WebElementFrame;
import net.sf.cglib.proxy.CallbackFilter;

import java.lang.reflect.Method;

public class WebParentElementFrameCallbackFilter implements CallbackFilter {

    private final Class<? extends WebElementFrame> webParentElementFrameClass;

    public WebParentElementFrameCallbackFilter(Class<? extends WebElementFrame> webParentElementFrameClass) {
        this.webParentElementFrameClass = webParentElementFrameClass;
    }

    @Override
    public int accept(Method method) {
        if (method.isDefault() || method.getDeclaringClass().isAssignableFrom(this.webParentElementFrameClass)) {
            return 0;
        }
        return WebChildElement.class.isAssignableFrom(method.getReturnType()) ? 1 : 2;
    }

}
