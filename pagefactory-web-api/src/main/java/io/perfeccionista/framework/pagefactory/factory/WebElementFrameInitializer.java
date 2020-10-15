package io.perfeccionista.framework.pagefactory.factory;

import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.factory.proxy.UnimplementedMethodInvocationHandler;
import io.perfeccionista.framework.pagefactory.factory.proxy.WebParentElementInvocationHandler;
import io.perfeccionista.framework.pagefactory.factory.proxy.frame.WebBlockElementFrame;
import io.perfeccionista.framework.pagefactory.factory.proxy.frame.WebChildElementFrame;
import io.perfeccionista.framework.pagefactory.factory.proxy.WebChildElementFrameCallbackFilter;
import io.perfeccionista.framework.pagefactory.factory.proxy.WebParentElementFrameCallbackFilter;
import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;
import org.jetbrains.annotations.NotNull;

public class WebElementFrameInitializer {

    private WebElementFrameInitializer() {
    }

    public static @NotNull<T extends WebBlock> T initWebBlockFrame(@NotNull Class<T> webMappedBlockClass) {
        Enhancer enhancer = new Enhancer();
        enhancer.setInterfaces(new Class[] {webMappedBlockClass});
        enhancer.setSuperclass(WebBlockElementFrame.class);
        enhancer.setCallbacks(new Callback[] {NoOp.INSTANCE, new WebParentElementInvocationHandler(), new UnimplementedMethodInvocationHandler()});
        enhancer.setCallbackFilter(new WebParentElementFrameCallbackFilter(WebBlockElementFrame.class));
        return (T) enhancer.create();
    }

    public static @NotNull WebChildElement initWebChildElementFrame(@NotNull Class<? extends WebChildElement> webChildElementClass) {
        Enhancer enhancer = new Enhancer();
        enhancer.setInterfaces(new Class[] {webChildElementClass});
        enhancer.setSuperclass(WebChildElementFrame.class);
        enhancer.setCallbacks(new Callback[] {NoOp.INSTANCE, new UnimplementedMethodInvocationHandler()});
        enhancer.setCallbackFilter(new WebChildElementFrameCallbackFilter(WebChildElementFrame.class));
        return (WebChildElement) enhancer.create();
    }

}