package io.perfeccionista.framework.pagefactory.factory.proxy.mock;

import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.factory.proxy.NullInvocationHandler;
import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.InvocationHandler;
import net.sf.cglib.proxy.NoOp;

import java.lang.reflect.Method;

public class WebChildElementMockInvocationHandler implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Class<? extends WebChildElement> returnType = (Class<? extends WebChildElement>) method.getReturnType();
        Enhancer enhancer = new Enhancer();
        enhancer.setInterfaces(new Class[] {returnType});
        enhancer.setSuperclass(WebMappedBlockMock.class);
        enhancer.setCallbacks(new Callback[] {NoOp.INSTANCE, new WebChildElementMockInvocationHandler(), new NullInvocationHandler()});
        enhancer.setCallbackFilter(invokedMethod -> {
            if (invokedMethod.getDeclaringClass().equals(WebMappedBlockMock.class)) {
                return 0;
            }
            return WebChildElement.class.isAssignableFrom(invokedMethod.getReturnType()) ? 1 : 2;
        });
        WebMappedBlockMock webChildElementMock = (WebMappedBlockMock) enhancer.create();
        webChildElementMock.setParentMock((WebMappedBlockMock) proxy)
                .setParentMethod(method)
                .setItemClass(returnType);
        return webChildElementMock;
    }

}
