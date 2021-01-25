package io.perfeccionista.framework.pagefactory.factory.proxy;

import io.perfeccionista.framework.pagefactory.elements.base.MobileParentElement;
import net.sf.cglib.proxy.CallbackFilter;

import java.lang.reflect.Method;

public class MobileParentElementCallbackFilter implements CallbackFilter {

    private final Class<? extends MobileParentElement> webParentElementClass;

    public MobileParentElementCallbackFilter(Class<? extends MobileParentElement> webParentElementClass) {
        this.webParentElementClass = webParentElementClass;
    }

    @Override
    public int accept(Method method) {
        return method.isDefault() || method.getDeclaringClass().isAssignableFrom(this.webParentElementClass)
                ? 0
                : 1;
    }

}
