package io.perfeccionista.framework.pagefactory.factory;

import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.WebMappedBlock;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.factory.proxy.NullInvocationHandler;
import io.perfeccionista.framework.pagefactory.factory.proxy.mock.WebBlockMock;
import io.perfeccionista.framework.pagefactory.factory.proxy.mock.WebChildElementMock;
import io.perfeccionista.framework.pagefactory.factory.proxy.mock.WebChildElementMockInvocationHandler;
import io.perfeccionista.framework.pagefactory.factory.proxy.mock.WebMappedBlockMock;
import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

public class WebElementMockInitializer {

    private WebElementMockInitializer() {
    }

    public static <T extends WebMappedBlock> T initWebMappedBlockMock(Class<T> webMappedBlockClass) {
        Enhancer enhancer = new Enhancer();
        enhancer.setInterfaces(new Class[] {webMappedBlockClass});
        enhancer.setSuperclass(WebMappedBlockMock.class);
        enhancer.setCallbacks(new Callback[] {NoOp.INSTANCE, new WebChildElementMockInvocationHandler(), new NullInvocationHandler()});
        enhancer.setCallbackFilter(method -> {
            if (method.isDefault() || WebMappedBlockMock.class.equals(method.getDeclaringClass())) {
                return 0;
            }
            return WebChildElement.class.isAssignableFrom(method.getReturnType()) ? 1 : 2;
        });
        return (T) enhancer.create();
    }

    public static WebBlock initWebBlockMock(Class<? extends WebBlock> webBlockClass) {
        Enhancer enhancer = new Enhancer();
        enhancer.setInterfaces(new Class[] {webBlockClass});
        enhancer.setSuperclass(WebBlockMock.class);
        enhancer.setCallbacks(new Callback[] {NoOp.INSTANCE, new WebChildElementMockInvocationHandler(), new NullInvocationHandler()});
        enhancer.setCallbackFilter(method -> {
            if (method.isDefault() || WebBlockMock.class.equals(method.getDeclaringClass())) {
                return 0;
            }
            return WebChildElement.class.isAssignableFrom(method.getReturnType()) ? 1 : 2;
        });
        return (WebBlock) enhancer.create();
    }

    public static WebChildElement initWebChildElementMock(Class<? extends WebChildElement> webChildElementClass) {
        Enhancer enhancer = new Enhancer();
        enhancer.setInterfaces(new Class[] {webChildElementClass});
        enhancer.setSuperclass(WebChildElementMock.class);
        enhancer.setCallbacks(new Callback[] {NoOp.INSTANCE, new WebChildElementMockInvocationHandler(), new NullInvocationHandler()});
        enhancer.setCallbackFilter(method -> {
            if (method.isDefault() || WebChildElementMock.class.equals(method.getDeclaringClass())) {
                return 0;
            }
            return WebChildElement.class.isAssignableFrom(method.getReturnType()) ? 1 : 2;
        });
        return (WebChildElement) enhancer.create();

    }

}
