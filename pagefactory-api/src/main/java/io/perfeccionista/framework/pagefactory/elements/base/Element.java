package io.perfeccionista.framework.pagefactory.elements.base;

import com.fasterxml.jackson.databind.JsonNode;
import io.perfeccionista.framework.Environment;

/**
 * immovable and highlight через NodeCheck
 * Для последнего использованного элемента хранить имя или поле на странице и брать его оттуда
 */
@Deprecated
public interface Element {



    Environment getEnvironment();

    JsonNode toJson();

}
