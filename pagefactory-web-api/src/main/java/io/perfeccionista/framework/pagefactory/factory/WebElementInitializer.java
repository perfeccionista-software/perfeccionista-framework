package io.perfeccionista.framework.pagefactory.factory;

import io.perfeccionista.framework.exceptions.WebElementImplementationNotFound;
import io.perfeccionista.framework.pagefactory.elements.WebPageImpl;
import io.perfeccionista.framework.pagefactory.elements.preferences.WebPageFactoryPreferences;
import io.perfeccionista.framework.pagefactory.elements.WebBlockImpl;
import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.WebPage;
import io.perfeccionista.framework.pagefactory.factory.proxy.UnimplementedMethodInvocationHandler;
import io.perfeccionista.framework.pagefactory.factory.proxy.WebChildElementCallbackFilter;
import io.perfeccionista.framework.pagefactory.factory.proxy.WebParentElementCallbackFilter;
import io.perfeccionista.framework.pagefactory.factory.proxy.WebParentElementInvocationHandler;
import io.perfeccionista.framework.utils.ReflectionUtils.Order;
import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;
import org.jetbrains.annotations.NotNull;

import java.util.Deque;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.ABSTRACT_WEB_CHILD_ELEMENT_IMPLEMENTATION_NOT_ALLOWED;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.WEB_CHILD_ELEMENT_IMPLEMENTATION_NOT_FOUND;
import static io.perfeccionista.framework.utils.ReflectionUtils.getInheritedInterfaces;
import static java.lang.reflect.Modifier.isAbstract;
import static java.lang.reflect.Modifier.isInterface;
import static org.junit.platform.commons.util.ReflectionUtils.newInstance;


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

    public @NotNull WebBlock initWebTableRow() {
        Class<? extends WebBlockImpl> webBlockImplementation = configuration.getWebBlockImplementation();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(webBlockImplementation);
        enhancer.setCallbacks(new Callback[] {NoOp.INSTANCE, new WebParentElementInvocationHandler()});
        enhancer.setCallbackFilter(new WebParentElementCallbackFilter(webBlockImplementation));
        return (WebBlock) enhancer.create();
    }

    public @NotNull WebChildElement initWebChildElement(@NotNull Class<? extends WebChildElement> webChildElementClass) {
        // Если возвращаемый тип - не интерфейс, а имплементация
        if (!isInterface(webChildElementClass.getModifiers())) {
            if (isAbstract(webChildElementClass.getModifiers())) {
                throw WebElementImplementationNotFound.exception(ABSTRACT_WEB_CHILD_ELEMENT_IMPLEMENTATION_NOT_ALLOWED
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
        enhancer.setCallbacks(new Callback[] {NoOp.INSTANCE, new UnimplementedMethodInvocationHandler()});
        enhancer.setCallbackFilter(new WebChildElementCallbackFilter(interfaceImplementationClass));
        return (WebChildElement) enhancer.create();
    }

    protected @NotNull Class<? extends WebChildElement> findAncestorInterfaceImplementation(@NotNull Class<? extends WebChildElement> webChildElementClass) {
        Deque<Class<? extends WebChildElement>> inheritedInterfaces = getInheritedInterfaces(WebChildElement.class, webChildElementClass, Order.DESC);
        for (Class<? extends WebChildElement> inheritedInterface : inheritedInterfaces) {
            Class<? extends WebChildElement> elementImplementation = configuration.getWebElementImplementation(inheritedInterface);
            if (null != elementImplementation) {
                return elementImplementation;
            }
        }
        throw WebElementImplementationNotFound.exception(WEB_CHILD_ELEMENT_IMPLEMENTATION_NOT_FOUND
                .getMessage(webChildElementClass.getCanonicalName()));
    }

}