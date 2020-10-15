package io.perfeccionista.framework.pagefactory.factory.proxy;

import io.perfeccionista.framework.pagefactory.factory.proxy.frame.WebElementFrame;
import net.sf.cglib.proxy.CallbackFilter;

import java.lang.reflect.Method;

public class WebChildElementFrameCallbackFilter implements CallbackFilter {

    private final Class<? extends WebElementFrame> webChildElementFrameClass;

    public WebChildElementFrameCallbackFilter(Class<? extends WebElementFrame> webChildElementFrameClass) {
        this.webChildElementFrameClass = webChildElementFrameClass;
    }

    @Override
    public int accept(Method method) {
        if (method.isDefault() || method.getDeclaringClass().isAssignableFrom(this.webChildElementFrameClass)) {
            return 0;
        }
        return 1;
    }

}
