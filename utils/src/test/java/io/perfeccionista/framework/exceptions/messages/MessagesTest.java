package io.perfeccionista.framework.exceptions.messages;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import io.perfeccionista.framework.SimpleParallelTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

final class MessagesTest extends SimpleParallelTest {

    @Test
    void getMessageWithoutParametersTest() {
        assertEquals("Message without parameters",
                TestMessages.MESSAGE_WITHOUT_PARAMETERS.getMessage());
    }

    @Test
    void getMessageWithParametersTest() {
        Assertions.assertEquals("Message with parameters: param, 1234, MessagesTest",
                TestMessages.MESSAGE_WITH_PARAMETERS.getMessage("param", 1234, MessagesTest.class.getSimpleName()));
    }

    private enum TestMessages implements Messages {

        MESSAGE_WITHOUT_PARAMETERS("Message without parameters"),
        MESSAGE_WITH_PARAMETERS("Message with parameters: %s, %s, %s");

        private String key;

        TestMessages(String key) {
            this.key = key;
        }

        @Override
        public String getErrorMessage() {
            return key;
        }

    }

}
