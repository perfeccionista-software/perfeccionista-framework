package io.perfeccionista.framework.pagefactory.factory.proxy;

import net.sf.cglib.proxy.InvocationHandler;

import java.lang.reflect.Method;

public class NullInvocationHandler implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return null;
    }

}
