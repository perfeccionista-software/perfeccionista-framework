package io.perfeccionista.framework.pagefactory.factory.proxy;

import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElement;
import io.perfeccionista.framework.pagefactory.factory.proxy.frame.MobileElementFrame;
import net.sf.cglib.proxy.CallbackFilter;

import java.lang.reflect.Method;

public class MobileParentElementFrameCallbackFilter implements CallbackFilter {

    private final Class<? extends MobileElementFrame> mobileParentElementFrameClass;

    public MobileParentElementFrameCallbackFilter(Class<? extends MobileElementFrame> mobileParentElementFrameClass) {
        this.mobileParentElementFrameClass = mobileParentElementFrameClass;
    }

    @Override
    public int accept(Method method) {
        if (method.isDefault() || method.getDeclaringClass().isAssignableFrom(this.mobileParentElementFrameClass)) {
            return 0;
        }
        return MobileChildElement.class.isAssignableFrom(method.getReturnType()) ? 1 : 2;
    }

}

