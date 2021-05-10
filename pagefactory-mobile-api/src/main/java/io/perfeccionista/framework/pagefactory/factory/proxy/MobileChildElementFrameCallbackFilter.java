package io.perfeccionista.framework.pagefactory.factory.proxy;

import io.perfeccionista.framework.pagefactory.factory.proxy.frame.MobileElementFrame;
import net.sf.cglib.proxy.CallbackFilter;

import java.lang.reflect.Method;

public class MobileChildElementFrameCallbackFilter implements CallbackFilter {

    private final Class<? extends MobileElementFrame> mobileChildElementFrameClass;

    public MobileChildElementFrameCallbackFilter(Class<? extends MobileElementFrame> mobileChildElementFrameClass) {
        this.mobileChildElementFrameClass = mobileChildElementFrameClass;
    }

    @Override
    public int accept(Method method) {
        if (method.isDefault() || method.getDeclaringClass().isAssignableFrom(this.mobileChildElementFrameClass)) {
            return 0;
        }
        return 1;
    }

}

