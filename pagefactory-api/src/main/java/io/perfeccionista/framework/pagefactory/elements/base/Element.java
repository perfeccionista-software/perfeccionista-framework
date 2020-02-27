package io.perfeccionista.framework.pagefactory.elements.base;

import io.perfeccionista.framework.pagefactory.elements.locators.LocatorHolder;

import java.util.Set;

/**
 * immovable and highlight через NodeCheck
 * Для последнего использованного элемента хранить имя или поле на странице и брать его оттуда
 */
public interface Element {

    LocatorHolder getLocator(String locatorName);

    Set<String> getNames();

}
