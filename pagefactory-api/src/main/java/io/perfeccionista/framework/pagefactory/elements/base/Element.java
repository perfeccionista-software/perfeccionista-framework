package io.perfeccionista.framework.pagefactory.elements.base;

import java.util.Set;

/**
 * immovable and highlight через NodeCheck
 * Для последнего использованного элемента хранить имя или поле на странице и брать его оттуда
 */
public interface Element {

    Set<String> getNames();

}
