package io.perfeccionista.framework.pagefactory.factory.proxy;

import io.perfeccionista.framework.exceptions.WebElementMethodNotImplemented;
import net.sf.cglib.proxy.InvocationHandler;

import java.lang.reflect.Method;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.WEB_ELEMENT_METHOD_NOT_IMPLEMENTED;

public class UnimplementedMethodInvocationHandler implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        throw WebElementMethodNotImplemented.exception(WEB_ELEMENT_METHOD_NOT_IMPLEMENTED.getMessage(method.getName()));
    }

}
