package io.perfeccionista.framework.pagefactory.elements.base;

import com.fasterxml.jackson.databind.JsonNode;
import io.perfeccionista.framework.Environment;

public interface ElementBase {

    Environment getEnvironment();

    JsonNode toJson();

}
