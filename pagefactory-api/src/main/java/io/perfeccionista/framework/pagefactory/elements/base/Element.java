package io.perfeccionista.framework.pagefactory.elements.base;

import io.perfeccionista.framework.pagefactory.elements.locators.LocatorHolder;
import io.perfeccionista.framework.pagefactory.elements.locators.LocatorChain;
import io.perfeccionista.framework.pagefactory.driver.DriverInstance;

import java.util.Set;

/**
 * immovable and highlight через NodeCheck
 * Для последнего использованного элемента хранить имя или поле на странице и брать его оттуда
 */
// TODO: Возможно есть смысл сделать элемент с джененриком типа его вебдрайвера
public interface Element {

    DriverInstance<?> getDriverInstance();

    LocatorChain getLocatorChainTo(String locatorName);

    LocatorChain getLocatorChain();

    LocatorHolder getLocator(String locatorName);

    Set<String> getNames();

}
