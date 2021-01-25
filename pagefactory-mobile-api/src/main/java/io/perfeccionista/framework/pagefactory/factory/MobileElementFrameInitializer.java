package io.perfeccionista.framework.pagefactory.factory;

import io.perfeccionista.framework.pagefactory.elements.MobileBlock;
import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElement;
import io.perfeccionista.framework.pagefactory.factory.proxy.MobileChildElementFrameCallbackFilter;
import io.perfeccionista.framework.pagefactory.factory.proxy.MobileParentElementFrameCallbackFilter;
import io.perfeccionista.framework.pagefactory.factory.proxy.MobileParentElementInvocationHandler;
import io.perfeccionista.framework.pagefactory.factory.proxy.UnimplementedElementMethodInvocationHandler;
import io.perfeccionista.framework.pagefactory.factory.proxy.frame.MobileBlockElementFrame;
import io.perfeccionista.framework.pagefactory.factory.proxy.frame.MobileChildElementFrame;
import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;
import org.jetbrains.annotations.NotNull;

public class MobileElementFrameInitializer {

    private MobileElementFrameInitializer() {
    }

    public static @NotNull <T extends MobileBlock> T initMobileBlockFrame(@NotNull Class<T> webMappedBlockClass) {
        Enhancer enhancer = new Enhancer();
        enhancer.setInterfaces(new Class[] {webMappedBlockClass});
        enhancer.setSuperclass(MobileBlockElementFrame.class);
        enhancer.setCallbacks(new Callback[] {NoOp.INSTANCE, new MobileParentElementInvocationHandler(), new UnimplementedElementMethodInvocationHandler()});
        enhancer.setCallbackFilter(new MobileParentElementFrameCallbackFilter(MobileBlockElementFrame.class));
        return (T) enhancer.create();
    }

    public static @NotNull MobileChildElement initMobileChildElementFrame(@NotNull Class<? extends MobileChildElement> webChildElementClass) {
        Enhancer enhancer = new Enhancer();
        enhancer.setInterfaces(new Class[] {webChildElementClass});
        enhancer.setSuperclass(MobileChildElementFrame.class);
        enhancer.setCallbacks(new Callback[] {NoOp.INSTANCE, new UnimplementedElementMethodInvocationHandler()});
        enhancer.setCallbackFilter(new MobileChildElementFrameCallbackFilter(MobileChildElementFrame.class));
        return (MobileChildElement) enhancer.create();
    }

}
