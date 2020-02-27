package io.perfeccionista.framework.pagefactory.elements.registry;

import io.perfeccionista.framework.exceptions.EmptyElementPathException;
import org.junit.platform.commons.util.StringUtils;

import java.util.Arrays;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryMessages.EMPTY_ELEMENT_PATH;

public interface ElementRegistry {

    default String[] parseElementPath(String elementPath) {
        String trimmedElementPath = elementPath.trim();
        if (StringUtils.isBlank(trimmedElementPath)) {
            throw new EmptyElementPathException(EMPTY_ELEMENT_PATH.getMessage());
        }
        return Arrays.stream(trimmedElementPath.split("\\s->\\s")).map(String::trim).toArray(String[]::new);
    }

}
