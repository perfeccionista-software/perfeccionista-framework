package io.perfeccionista.framework.pagefactory.factory.proxy;

import io.perfeccionista.framework.exceptions.WebElementMethodNotImplemented;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.base.WebParentElement;
import net.sf.cglib.proxy.InvocationHandler;

import java.lang.reflect.Method;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.WEB_ELEMENT_METHOD_NOT_IMPLEMENTED;

public class WebParentElementInvocationHandler implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (isElementMethod(method)) {
            return ((WebParentElement) proxy).getElementRegistry()
                    .getRequiredElementByMethod(method);
        }
        // TODO: Добавить в сообщение об ошибке имя класса в котором вызывается метод
        throw WebElementMethodNotImplemented.exception(WEB_ELEMENT_METHOD_NOT_IMPLEMENTED.getMessage(method.getName()));
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
