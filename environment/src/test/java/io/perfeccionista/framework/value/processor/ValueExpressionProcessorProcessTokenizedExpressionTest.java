package io.perfeccionista.framework.value.processor;

import io.perfeccionista.framework.AbstractParallelTestWithEnvironment;
import io.perfeccionista.framework.UseEnvironment;
import io.perfeccionista.framework.datasource.entities.User;
import io.perfeccionista.framework.exceptions.DataConverterNotFound.DataConverterNotFoundException;
import io.perfeccionista.framework.exceptions.DataSourceNotFound.DataSourceNotFoundException;
import io.perfeccionista.framework.exceptions.ServiceNotFound.ServiceNotFoundException;
import io.perfeccionista.framework.exceptions.StringValueParse.StringValueParseException;
import io.perfeccionista.framework.value.configurations.TestValueEnvironmentConfigurationWithoutDataConverterService;
import io.perfeccionista.framework.value.configurations.TestValueEnvironmentConfigurationWithoutDataSourceService;
import org.junit.jupiter.api.Test;
import io.perfeccionista.framework.Environment;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ValueExpressionProcessorProcessTokenizedExpressionTest extends AbstractParallelTestWithEnvironment {

    @Test
    void processSimpleStringExpression(Environment environment) {
        ValueExpressionProcessor expressionProcessor = new ValueExpressionProcessor(environment);
        assertAll(
                () -> {
                    Object result1 = expressionProcessor.processExpression("simple expression");
                    assertAll(
                            () -> assertNotNull(result1),
                            () -> assertEquals(String.class, result1.getClass()),
                            () -> assertEquals("simple expression", result1.toString())
                    );
                },
                () -> {
                    Object result2 = expressionProcessor.processExpression("{simple expression}");
                    assertAll(
                            () -> assertNotNull(result2),
                            () -> assertEquals(String.class, result2.getClass()),
                            () -> assertEquals("{simple expression}", result2.toString())
                    );
                },
                () -> {
                    Object result3 = expressionProcessor.processExpression("\\$\\{escaped DataSource expression\\}");
                    assertAll(
                            () -> assertNotNull(result3),
                            () -> assertEquals(String.class, result3.getClass()),
                            () -> assertEquals("${escaped DataSource expression}", result3.toString())
                    );

                },
                () -> {
                    Object result4 = expressionProcessor.processExpression("\\@\\{escaped DataConverter expression\\}");
                    assertAll(
                            () -> assertNotNull(result4),
                            () -> assertEquals(String.class, result4.getClass()),
                            () -> assertEquals("@{escaped DataConverter expression}", result4.toString())
                    );
                }
        );
    }

    @Test
    void processValidStringDataSourceExpression(Environment environment) {
        ValueExpressionProcessor expressionProcessor = new ValueExpressionProcessor(environment);
        assertAll(
                () -> {
                    Object result1 = expressionProcessor.processExpression("${[userName]John}");
                    assertAll(
                            () -> assertNotNull(result1),
                            () -> assertEquals(String.class, result1.getClass()),
                            () -> assertEquals("John Smith", result1.toString())
                    );
                },
                () -> {
                    Object result2 = expressionProcessor.processExpression("${[userName] John}");
                    assertAll(
                            () -> assertNotNull(result2),
                            () -> assertEquals(String.class, result2.getClass()),
                            () -> assertEquals("John Smith", result2.toString())
                    );
                },
                () -> {
                    Object result3 = expressionProcessor.processExpression("${[userName] John }");
                    assertAll(
                            () -> assertNotNull(result3),
                            () -> assertEquals(String.class, result3.getClass()),
                            () -> assertEquals("John Smith", result3.toString())
                    );
                },
                () -> {
                    Object result4 = expressionProcessor.processExpression("User ${[userName] John} activated");
                    assertAll(
                            () -> assertNotNull(result4),
                            () -> assertEquals(String.class, result4.getClass()),
                            () -> assertEquals("User John Smith activated", result4.toString())
                    );
                }
        );
    }

    @Test
    void processValidUserDataSourceExpression(Environment environment) {
        ValueExpressionProcessor expressionProcessor = new ValueExpressionProcessor(environment);
        assertAll(
                () -> {
                    Object result1 = expressionProcessor.processExpression("${[user] John}");
                    assertAll(
                            () -> assertNotNull(result1),
                            () -> assertEquals(User.class, result1.getClass()),
                            () -> assertEquals(new User("John", "Smith"), result1)
                    );
                },
                () -> {
                    Object result2 = expressionProcessor.processExpression("${[user] John} activated");
                    assertAll(
                            () -> assertNotNull(result2),
                            () -> assertEquals(String.class, result2.getClass()),
                            () -> assertEquals("John Smith activated", result2)
                    );
                },
                () -> {
                    Object result3 = expressionProcessor.processExpression("User ${[user] John} activated");
                    assertAll(
                            () -> assertNotNull(result3),
                            () -> assertEquals(String.class, result3.getClass()),
                            () -> assertEquals("User John Smith activated", result3)
                    );
                }
        );
    }

    @Test
    void processValidStringToUserDataConverterExpression(Environment environment) {
        ValueExpressionProcessor expressionProcessor = new ValueExpressionProcessor(environment);
        assertAll(
                () -> {
                    Object result1 = expressionProcessor.processExpression("@{[userName to user]John Smith}");
                    assertAll(
                            () -> assertNotNull(result1),
                            () -> assertEquals(User.class, result1.getClass()),
                            () -> assertEquals(new User("John", "Smith"), result1)
                    );
                },
                () -> {
                    Object result2 = expressionProcessor.processExpression("@{[userName to user] John Smith}");
                    assertAll(
                            () -> assertNotNull(result2),
                            () -> assertEquals(User.class, result2.getClass()),
                            () -> assertEquals(new User("John", "Smith"), result2)
                    );
                },
                () -> {
                    Object result3 = expressionProcessor.processExpression("@{[userName to user] John Smith} activated");
                    assertAll(
                            () -> assertNotNull(result3),
                            () -> assertEquals(String.class, result3.getClass()),
                            () -> assertEquals("John Smith activated", result3)
                    );
                },
                () -> {
                    Object result4 = expressionProcessor.processExpression("User @{[userName to user] John Smith} activated");
                    assertAll(
                            () -> assertNotNull(result4),
                            () -> assertEquals(String.class, result4.getClass()),
                            () -> assertEquals("User John Smith activated", result4)
                    );
                }
        );
    }

    @Test
    void processValidUserToStringDataConverterExpression(Environment environment) {
        ValueExpressionProcessor expressionProcessor = new ValueExpressionProcessor(environment);
        assertAll(
                () -> {
                    Object result1 = expressionProcessor.processExpression("@{[user to userName]${[user] John}}");
                    assertAll(
                            () -> assertNotNull(result1),
                            () -> assertEquals(String.class, result1.getClass()),
                            () -> assertEquals("John Smith", result1)
                    );
                },
                () -> {
                    Object result2 = expressionProcessor.processExpression("@{[user to userName] ${[user] John}}");
                    assertAll(
                            () -> assertNotNull(result2),
                            () -> assertEquals(String.class, result2.getClass()),
                            () -> assertEquals("John Smith", result2)
                    );
                },
                () -> {
                    Object result3 = expressionProcessor.processExpression("@{[user to userName] ${[user] John}} activated");
                    assertAll(
                            () -> assertNotNull(result3),
                            () -> assertEquals(String.class, result3.getClass()),
                            () -> assertEquals("John Smith activated", result3)
                    );
                },
                () -> {
                    Object result4 = expressionProcessor.processExpression("User @{[user to userName] ${[user] John}} activated");
                    assertAll(
                            () -> assertNotNull(result4),
                            () -> assertEquals(String.class, result4.getClass()),
                            () -> assertEquals("User John Smith activated", result4)
                    );
                }
        );
    }

    @Test
    void processValidUserToStringDataConverterWithFormatExpression(Environment environment) {
        ValueExpressionProcessor expressionProcessor = new ValueExpressionProcessor(environment);
        assertAll(
                () -> {
                    Object result1 = expressionProcessor.processExpression("@{[user to userName -> Mr.]${[user] John}}");
                    assertAll(
                            () -> assertNotNull(result1),
                            () -> assertEquals(String.class, result1.getClass()),
                            () -> assertEquals("Mr. John Smith", result1)
                    );
                },
                () -> {
                    Object result2 = expressionProcessor.processExpression("@{[user to userName -> Mr.] ${[user] John}}");
                    assertAll(
                            () -> assertNotNull(result2),
                            () -> assertEquals(String.class, result2.getClass()),
                            () -> assertEquals("Mr. John Smith", result2)
                    );
                },
                () -> {
                    Object result3 = expressionProcessor.processExpression("@{[user to userName -> Mr.] ${[user] John}} activated");
                    assertAll(
                            () -> assertNotNull(result3),
                            () -> assertEquals(String.class, result3.getClass()),
                            () -> assertEquals("Mr. John Smith activated", result3)
                    );
                },
                () -> {
                    Object result4 = expressionProcessor.processExpression("User @{[user to userName -> Mr.] ${[user] John}} activated");
                    assertAll(
                            () -> assertNotNull(result4),
                            () -> assertEquals(String.class, result4.getClass()),
                            () -> assertEquals("User Mr. John Smith activated", result4)
                    );
                }
        );
    }

    @Test
    @UseEnvironment(TestValueEnvironmentConfigurationWithoutDataSourceService.class)
    void processDataSourceExpressionWithoutDataSourceService(Environment environment) {
        ValueExpressionProcessor expressionProcessor = new ValueExpressionProcessor(environment);
        assertThrows(ServiceNotFoundException.class, () -> expressionProcessor.processExpression("${[user] John}"));
    }

    @Test
    @UseEnvironment(TestValueEnvironmentConfigurationWithoutDataConverterService.class)
    void processDataConverterExpressionWithoutDataConverterService(Environment environment) {
        ValueExpressionProcessor expressionProcessor = new ValueExpressionProcessor(environment);
        assertThrows(ServiceNotFoundException.class, () -> expressionProcessor.processExpression("@{[user to userName] John}"));
    }

    @Test
    void processDataSourceExpressionWithUnknownDataSource(Environment environment) {
        ValueExpressionProcessor expressionProcessor = new ValueExpressionProcessor(environment);
        assertThrows(DataSourceNotFoundException.class, () -> expressionProcessor.processExpression("${[unknown] John}"));
    }

    @Test
    void processDataConverterExpressionWithUnknownDataConverter(Environment environment) {
        ValueExpressionProcessor expressionProcessor = new ValueExpressionProcessor(environment);
        assertThrows(DataConverterNotFoundException.class, () -> expressionProcessor.processExpression("@{[unknown] John}"));
    }

    @Test
    void processDataSourceExpressionWithIncorrectKeyType(Environment environment) {
        ValueExpressionProcessor expressionProcessor = new ValueExpressionProcessor(environment);
        assertThrows(StringValueParseException.class, () -> expressionProcessor.processExpression("${[userName] ${[user] John}}"));
    }

    @Test
    void processDataConverterExpressionWithIncorrectKeyType(Environment environment) {
        ValueExpressionProcessor expressionProcessor = new ValueExpressionProcessor(environment);
        assertThrows(StringValueParseException.class, () -> expressionProcessor.processExpression("@{[userName to user] ${[user] John}}"));
    }

    @Test
    void processDataConverterExpressionWithoutName(Environment environment) {
        ValueExpressionProcessor expressionProcessor = new ValueExpressionProcessor(environment);
        assertThrows(StringValueParseException.class, () -> expressionProcessor.processExpression("@{${[user] John}}"));
    }

    @Test
    void processExpressionWithCompoundKey(Environment environment) {
        ValueExpressionProcessor expressionProcessor = new ValueExpressionProcessor(environment);
        assertAll(
                () -> assertThrows(StringValueParseException.class, () -> expressionProcessor.processExpression("${[userName] User ${[user] John}}")),
                () -> assertThrows(StringValueParseException.class, () -> expressionProcessor.processExpression("@{[userName to user] User ${[user] John}}"))
        );
    }

}
