package io.perfeccionista.framework.utils;

import io.perfeccionista.framework.SimpleParallelTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class StringUtilsTest extends SimpleParallelTest {

    @Test
    void collectionToStringTest() {
        String actual = StringUtils.collectionToString(List.of("One", "Two", "Three", "Four"));
        Assertions.assertEquals("[One, Two, Three, Four]", actual);
    }

}
