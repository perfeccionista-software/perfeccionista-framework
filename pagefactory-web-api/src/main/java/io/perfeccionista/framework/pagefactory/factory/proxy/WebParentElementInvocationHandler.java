package io.perfeccionista.framework.pagefactory.factory.proxy;

import io.perfeccionista.framework.exceptions.ElementNotDeclaredException;
import io.perfeccionista.framework.exceptions.UnimplementedMethodCallException;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.base.WebParentElement;
import net.sf.cglib.proxy.InvocationHandler;

import java.lang.reflect.Method;
import java.util.Optional;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryMessages.ELEMENT_NOT_DECLARED;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.CALLED_METHOD_IS_NOT_IMPLEMENTED;

public class WebParentElementInvocationHandler implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (isElementMethod(method)) {
            Optional<WebChildElement> optionalElement = ((WebParentElement) proxy).getElementRegistry().getElementByMethod(method);
            return optionalElement
                    .orElseThrow(() -> new ElementNotDeclaredException(ELEMENT_NOT_DECLARED.getMessage(method.getName())));
        }
        throw new UnimplementedMethodCallException(CALLED_METHOD_IS_NOT_IMPLEMENTED.getMessage());
    }

    /**
     * Проверяем, что метод возвращает элемент
     * @param method
     * @return
     */
    private boolean isElementMethod(Method method) {
        return WebChildElement.class.isAssignableFrom(method.getReturnType());
    }

}
