package io.perfeccionista.framework.pagefactory.factory;

import io.perfeccionista.framework.exceptions.ElementImplementationNotFound;
import io.perfeccionista.framework.pagefactory.elements.WebPageImpl;
import io.perfeccionista.framework.pagefactory.elements.preferences.WebPageFactoryPreferences;
import io.perfeccionista.framework.pagefactory.elements.WebBlockImpl;
import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.WebPage;
import io.perfeccionista.framework.pagefactory.factory.proxy.UnimplementedWebElementMethodInvocationHandler;
import io.perfeccionista.framework.pagefactory.factory.proxy.WebChildElementCallbackFilter;
import io.perfeccionista.framework.pagefactory.factory.proxy.WebParentElementCallbackFilter;
import io.perfeccionista.framework.pagefactory.factory.proxy.WebParentElementInvocationHandler;
import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;
import org.jetbrains.annotations.NotNull;

import java.util.Deque;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.ELEMENT_IMPLEMENTATION_CANT_BE_ABSTRACT;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.ELEMENT_IMPLEMENTATION_NOT_FOUND;
import static io.perfeccionista.framework.measurements.Order.DESC;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.findInheritedInterfaces;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.newInstance;
import static java.lang.reflect.Modifier.isAbstract;
import static java.lang.reflect.Modifier.isInterface;

public class WebElementInitializer {

    private final WebPageFactoryPreferences configuration;

    public WebElementInitializer(@NotNull WebPageFactoryPreferences configuration) {
        this.configuration = configuration;
    }

    public @NotNull WebPage initWebPageInstance(@NotNull Class<? extends WebPage> webPageClass) {
        Class<? extends WebPageImpl> webPageImplementation = configuration.getWebPageImplementation();
        Enhancer enhancer = new Enhancer();
        enhancer.setInterfaces(new Class[] {webPageClass});
        enhancer.setSuperclass(webPageImplementation);
        enhancer.setCallbacks(new Callback[] {NoOp.INSTANCE, new WebParentElementInvocationHandler()});
        enhancer.setCallbackFilter(new WebParentElementCallbackFilter(webPageImplementation));
        return (WebPage) enhancer.create();
    }

    public @NotNull <T extends WebBlock> T initWebBlock(@NotNull Class<T> webBlockClass) {
        Class<? extends WebBlockImpl> webBlockImplementation = configuration.getWebBlockImplementation();
        Enhancer enhancer = new Enhancer();
        enhancer.setInterfaces(new Class[] {webBlockClass});
        enhancer.setSuperclass(webBlockImplementation);
        enhancer.setCallbacks(new Callback[] {NoOp.INSTANCE, new WebParentElementInvocationHandler()});
        enhancer.setCallbackFilter(new WebParentElementCallbackFilter(webBlockImplementation));
        return (T) enhancer.create();
    }

    public @NotNull <T extends WebBlock> T initMappedWebBlock(@NotNull Class<T> webBlockClass) {
        Class<? extends WebBlockImpl> webBlockImplementation = configuration.getMappedWebBlockImplementation();
        Enhancer enhancer = new Enhancer();
        enhancer.setInterfaces(new Class[] {webBlockClass});
        enhancer.setSuperclass(webBlockImplementation);
        enhancer.setCallbacks(new Callback[] {NoOp.INSTANCE, new WebParentElementInvocationHandler()});
        enhancer.setCallbackFilter(new WebParentElementCallbackFilter(webBlockImplementation));
        return (T) enhancer.create();
    }

    public @NotNull WebChildElement initWebChildElement(@NotNull Class<? extends WebChildElement> webChildElementClass) {
        // Если возвращаемый тип - не интерфейс, а имплементация
        if (!isInterface(webChildElementClass.getModifiers())) {
            if (isAbstract(webChildElementClass.getModifiers()) || webChildElementClass.isEnum()) {
                throw ElementImplementationNotFound.exception(ELEMENT_IMPLEMENTATION_CANT_BE_ABSTRACT
                        .getMessage(webChildElementClass.getCanonicalName()));
            }
            return newInstance(webChildElementClass);
        }
        // Если возвращаемый тип - интерфейс для которого задана имплементация
        Class<? extends WebChildElement> webChildElementImplementation = configuration.getWebElementImplementation(webChildElementClass);
        if (webChildElementImplementation != null) {
            return newInstance(webChildElementImplementation);
        }
        // Если возвращаемый тип - интерфейс для которого не задана имплементация (например, MyWebButton extends MyButton)
        Class<? extends WebChildElement> interfaceImplementationClass = findAncestorInterfaceImplementation(webChildElementClass);
        // Создаем элемент через Enhancer
        Enhancer enhancer = new Enhancer();
        enhancer.setInterfaces(new Class[] {webChildElementClass});
        enhancer.setSuperclass(interfaceImplementationClass);
        enhancer.setCallbacks(new Callback[] {NoOp.INSTANCE, new UnimplementedWebElementMethodInvocationHandler()});
        enhancer.setCallbackFilter(new WebChildElementCallbackFilter(interfaceImplementationClass));
        return (WebChildElement) enhancer.create();
    }

    protected @NotNull Class<? extends WebChildElement> findAncestorInterfaceImplementation(@NotNull Class<? extends WebChildElement> webChildElementClass) {
        Deque<Class<? extends WebChildElement>> inheritedInterfaces = findInheritedInterfaces(WebChildElement.class, webChildElementClass, DESC);
        for (Class<? extends WebChildElement> inheritedInterface : inheritedInterfaces) {
            Class<? extends WebChildElement> elementImplementation = configuration.getWebElementImplementation(inheritedInterface);
            if (null != elementImplementation) {
                return elementImplementation;
            }
        }
        throw ElementImplementationNotFound.exception(ELEMENT_IMPLEMENTATION_NOT_FOUND
                .getMessage(webChildElementClass.getCanonicalName()));
    }

}
