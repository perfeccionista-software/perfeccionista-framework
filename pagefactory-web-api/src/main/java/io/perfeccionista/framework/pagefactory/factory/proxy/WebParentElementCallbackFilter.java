package io.perfeccionista.framework.pagefactory.factory.proxy;

import io.perfeccionista.framework.pagefactory.elements.base.WebParentElement;
import net.sf.cglib.proxy.CallbackFilter;

import java.lang.reflect.Method;

public class WebParentElementCallbackFilter implements CallbackFilter {

    private final Class<? extends WebParentElement> webParentElementClass;

    public WebParentElementCallbackFilter(Class<? extends WebParentElement> webParentElementClass) {
        this.webParentElementClass = webParentElementClass;
    }

    @Override
    public int accept(Method method) {
        return method.isDefault() || method.getDeclaringClass().isAssignableFrom(this.webParentElementClass)
                ? 0
                : 1;
    }

}

