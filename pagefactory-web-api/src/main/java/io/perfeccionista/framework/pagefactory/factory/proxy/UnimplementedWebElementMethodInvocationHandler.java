package io.perfeccionista.framework.pagefactory.factory.proxy;

import io.perfeccionista.framework.exceptions.ElementMethodNotImplemented;
import net.sf.cglib.proxy.InvocationHandler;

import java.lang.reflect.Method;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.ELEMENT_METHOD_NOT_IMPLEMENTED;

public class UnimplementedWebElementMethodInvocationHandler implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        throw ElementMethodNotImplemented.exception(ELEMENT_METHOD_NOT_IMPLEMENTED.getMessage(method.getName()));
    }

}
