package io.perfeccionista.framework.pagefactory.factory.proxy.mock;

import io.perfeccionista.framework.exceptions.ElementNotDeclaredException;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import net.sf.cglib.proxy.InvocationHandler;

import java.lang.reflect.Method;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryMessages.ELEMENT_NOT_DECLARED;

public class WebChildElementMockInvocationHandler implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // TODO: Если это метод элемента, то возвращаем из реджистри
        Class<? extends WebChildElement> returnType = (Class<? extends WebChildElement>) method.getReturnType();
        if (WebChildElement.class.isAssignableFrom(returnType)) {
            return ((WebMappedBlockMock) proxy).getElementRegistry().getElementByMethod(method)
                    .orElseThrow(() -> new ElementNotDeclaredException(ELEMENT_NOT_DECLARED.getMessage(method.getName())));
        }
        // TODO: Доработать механизм
        return null;
    }

}
