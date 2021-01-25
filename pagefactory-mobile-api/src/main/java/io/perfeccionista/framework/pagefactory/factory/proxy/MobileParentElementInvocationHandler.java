package io.perfeccionista.framework.pagefactory.factory.proxy;

import io.perfeccionista.framework.exceptions.ElementMethodNotImplemented;
import io.perfeccionista.framework.exceptions.attachments.MobileElementAttachmentEntry;
import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElement;
import io.perfeccionista.framework.pagefactory.elements.base.MobileParentElement;
import net.sf.cglib.proxy.InvocationHandler;

import java.lang.reflect.Method;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.ELEMENT_METHOD_NOT_IMPLEMENTED;

public class MobileParentElementInvocationHandler implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        MobileParentElement element = ((MobileParentElement) proxy);
        if (isElementMethod(method)) {
            return element.getElementRegistry()
                    .getRequiredElementByMethod(method);
        }
        // TODO: Добавить в сообщение об ошибке имя класса в котором вызывается метод
        throw ElementMethodNotImplemented.exception(ELEMENT_METHOD_NOT_IMPLEMENTED.getMessage(method.getName()))
                .addLastAttachmentEntry(MobileElementAttachmentEntry.of(element));
    }

    /**
     * Проверяем, что метод возвращает элемент
     * @param method
     * @return
     */
    private boolean isElementMethod(Method method) {
        return MobileChildElement.class.isAssignableFrom(method.getReturnType());
    }

}

