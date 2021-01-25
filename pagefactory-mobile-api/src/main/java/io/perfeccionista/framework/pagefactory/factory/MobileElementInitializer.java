package io.perfeccionista.framework.pagefactory.factory;

import io.perfeccionista.framework.exceptions.ElementImplementationNotFound;
import io.perfeccionista.framework.pagefactory.elements.MobileBlock;
import io.perfeccionista.framework.pagefactory.elements.MobileBlockImpl;
import io.perfeccionista.framework.pagefactory.elements.MobilePage;
import io.perfeccionista.framework.pagefactory.elements.MobilePageImpl;
import io.perfeccionista.framework.pagefactory.elements.MobileTableRow;
import io.perfeccionista.framework.pagefactory.elements.MobileTableRowImpl;
import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElement;
import io.perfeccionista.framework.pagefactory.elements.preferences.MobilePageFactoryPreferences;
import io.perfeccionista.framework.pagefactory.factory.proxy.MobileChildElementCallbackFilter;
import io.perfeccionista.framework.pagefactory.factory.proxy.MobileParentElementCallbackFilter;
import io.perfeccionista.framework.pagefactory.factory.proxy.MobileParentElementInvocationHandler;
import io.perfeccionista.framework.pagefactory.factory.proxy.UnimplementedElementMethodInvocationHandler;
import io.perfeccionista.framework.utils.ReflectionUtils.Order;
import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;
import org.jetbrains.annotations.NotNull;

import java.util.Deque;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.ELEMENT_IMPLEMENTATION_CANT_BE_ABSTRACT;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.ELEMENT_IMPLEMENTATION_NOT_FOUND;
import static io.perfeccionista.framework.utils.ReflectionUtils.getInheritedInterfaces;
import static java.lang.reflect.Modifier.isAbstract;
import static java.lang.reflect.Modifier.isInterface;
import static org.junit.platform.commons.util.ReflectionUtils.newInstance;

public class MobileElementInitializer {

    private final MobilePageFactoryPreferences configuration;

    public MobileElementInitializer(@NotNull MobilePageFactoryPreferences configuration) {
        this.configuration = configuration;
    }

    public @NotNull MobilePage initMobilePageInstance(@NotNull Class<? extends MobilePage> webPageClass) {
        Class<? extends MobilePageImpl> webPageImplementation = configuration.getMobilePageImplementation();
        Enhancer enhancer = new Enhancer();
        enhancer.setInterfaces(new Class[] {webPageClass});
        enhancer.setSuperclass(webPageImplementation);
        enhancer.setCallbacks(new Callback[] {NoOp.INSTANCE, new MobileParentElementInvocationHandler()});
        enhancer.setCallbackFilter(new MobileParentElementCallbackFilter(webPageImplementation));
        return (MobilePage) enhancer.create();
    }

    public @NotNull <T extends MobileBlock> T initMobileBlock(@NotNull Class<T> webBlockClass) {
        Class<? extends MobileBlockImpl> webBlockImplementation = configuration.getMobileBlockImplementation();
        Enhancer enhancer = new Enhancer();
        enhancer.setInterfaces(new Class[] {webBlockClass});
        enhancer.setSuperclass(webBlockImplementation);
        enhancer.setCallbacks(new Callback[] {NoOp.INSTANCE, new MobileParentElementInvocationHandler()});
        enhancer.setCallbackFilter(new MobileParentElementCallbackFilter(webBlockImplementation));
        return (T) enhancer.create();
    }

    public @NotNull <T extends MobileBlock> T initMappedMobileBlock(@NotNull Class<T> webBlockClass) {
        Class<? extends MobileBlockImpl> webBlockImplementation = configuration.getMappedMobileBlockImplementation();
        Enhancer enhancer = new Enhancer();
        enhancer.setInterfaces(new Class[] {webBlockClass});
        enhancer.setSuperclass(webBlockImplementation);
        enhancer.setCallbacks(new Callback[] {NoOp.INSTANCE, new MobileParentElementInvocationHandler()});
        enhancer.setCallbackFilter(new MobileParentElementCallbackFilter(webBlockImplementation));
        return (T) enhancer.create();
    }

    public @NotNull MobileTableRow initMobileTableRow() {
        Class<? extends MobileTableRowImpl> webTableRowImplementation = configuration.getMobileTableRowImplementation();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(webTableRowImplementation);
        enhancer.setCallbacks(new Callback[] {NoOp.INSTANCE, new MobileParentElementInvocationHandler()});
        enhancer.setCallbackFilter(new MobileParentElementCallbackFilter(webTableRowImplementation));
        return (MobileTableRow) enhancer.create();
    }

    public @NotNull MobileChildElement initMobileChildElement(@NotNull Class<? extends MobileChildElement> webChildElementClass) {
        // Если возвращаемый тип - не интерфейс, а имплементация
        if (!isInterface(webChildElementClass.getModifiers())) {
            if (isAbstract(webChildElementClass.getModifiers()) || webChildElementClass.isEnum()) {
                throw ElementImplementationNotFound.exception(ELEMENT_IMPLEMENTATION_CANT_BE_ABSTRACT
                        .getMessage(webChildElementClass.getCanonicalName()));
            }
            return newInstance(webChildElementClass);
        }
        // Если возвращаемый тип - интерфейс для которого задана имплементация
        Class<? extends MobileChildElement> webChildElementImplementation = configuration.getMobileElementImplementation(webChildElementClass);
        if (webChildElementImplementation != null) {
            return newInstance(webChildElementImplementation);
        }
        // Если возвращаемый тип - интерфейс для которого не задана имплементация (например, MyMobileButton extends MyButton)
        Class<? extends MobileChildElement> interfaceImplementationClass = findAncestorInterfaceImplementation(webChildElementClass);
        // Создаем элемент через Enhancer
        Enhancer enhancer = new Enhancer();
        enhancer.setInterfaces(new Class[] {webChildElementClass});
        enhancer.setSuperclass(interfaceImplementationClass);
        enhancer.setCallbacks(new Callback[] {NoOp.INSTANCE, new UnimplementedElementMethodInvocationHandler()});
        enhancer.setCallbackFilter(new MobileChildElementCallbackFilter(interfaceImplementationClass));
        return (MobileChildElement) enhancer.create();
    }

    protected @NotNull Class<? extends MobileChildElement> findAncestorInterfaceImplementation(@NotNull Class<? extends MobileChildElement> webChildElementClass) {
        Deque<Class<? extends MobileChildElement>> inheritedInterfaces = getInheritedInterfaces(MobileChildElement.class, webChildElementClass, Order.DESC);
        for (Class<? extends MobileChildElement> inheritedInterface : inheritedInterfaces) {
            Class<? extends MobileChildElement> elementImplementation = configuration.getMobileElementImplementation(inheritedInterface);
            if (null != elementImplementation) {
                return elementImplementation;
            }
        }
        throw ElementImplementationNotFound.exception(ELEMENT_IMPLEMENTATION_NOT_FOUND
                .getMessage(webChildElementClass.getCanonicalName()));
    }

}
