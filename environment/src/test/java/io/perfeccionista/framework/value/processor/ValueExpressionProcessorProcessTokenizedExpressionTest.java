package io.perfeccionista.framework.value.processor;

import io.perfeccionista.framework.SimpleParallelTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.UseEnvironmentConfiguration;
import io.perfeccionista.framework.exceptions.DataConverterNotFoundException;
import io.perfeccionista.framework.exceptions.DataSourceNotFoundException;
import io.perfeccionista.framework.exceptions.ServiceNotFoundException;
import io.perfeccionista.framework.exceptions.StringValueParseException;
import io.perfeccionista.framework.extension.PerfeccionistaExtension;
import io.perfeccionista.framework.value.configuration.TestValueEnvironmentConfiguration;
import io.perfeccionista.framework.value.configuration.TestValueEnvironmentConfigurationWithoutDataConverterService;
import io.perfeccionista.framework.value.configuration.TestValueEnvironmentConfigurationWithoutDataSourceService;
import io.perfeccionista.framework.value.implementations.entities.User;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(PerfeccionistaExtension.class)
@UseEnvironmentConfiguration(TestValueEnvironmentConfiguration.class)
public class ValueExpressionProcessorProcessTokenizedExpressionTest extends SimpleParallelTest {

    @Test
    void processSimpleStringExpression(Environment environment) {
        ValueExpressionProcessor expressionProcessor = new ValueExpressionProcessor(environment);

        Object result1 = expressionProcessor.processExpression("simple expression");
        assertNotNull(result1);
        assertEquals(String.class, result1.getClass());
        assertEquals("simple expression", result1.toString());

        Object result2 = expressionProcessor.processExpression("{simple expression}");
        assertNotNull(result2);
        assertEquals(String.class, result2.getClass());
        assertEquals("{simple expression}", result2.toString());

        Object result3 = expressionProcessor.processExpression("\\$\\{escaped DataSource expression\\}");
        assertNotNull(result3);
        assertEquals(String.class, result3.getClass());
        assertEquals("${escaped DataSource expression}", result3.toString());

        Object result4 = expressionProcessor.processExpression("\\@\\{escaped DataConverter expression\\}");
        assertNotNull(result4);
        assertEquals(String.class, result4.getClass());
        assertEquals("@{escaped DataConverter expression}", result4.toString());
    }

    @Test
    void processValidStringDataSourceExpression(Environment environment) {
        ValueExpressionProcessor expressionProcessor = new ValueExpressionProcessor(environment);

        Object result1 = expressionProcessor.processExpression("${John}");
        assertNotNull(result1);
        assertEquals(String.class, result1.getClass());
        assertEquals("John Smith", result1.toString());

        Object result2 = expressionProcessor.processExpression("${[user name]John}");
        assertNotNull(result2);
        assertEquals(String.class, result2.getClass());
        assertEquals("John Smith", result2.toString());

        Object result3 = expressionProcessor.processExpression("${[user name] John}");
        assertNotNull(result3);
        assertEquals(String.class, result3.getClass());
        assertEquals("John Smith", result3.toString());

        Object result4 = expressionProcessor.processExpression("User ${[user name] John} activated");
        assertNotNull(result4);
        assertEquals(String.class, result4.getClass());
        assertEquals("User John Smith activated", result4.toString());
    }

    @Test
    void processValidUserDataSourceExpression(Environment environment) {
        ValueExpressionProcessor expressionProcessor = new ValueExpressionProcessor(environment);

        Object result1 = expressionProcessor.processExpression("${[user] John}");
        assertNotNull(result1);
        assertEquals(User.class, result1.getClass());
        assertEquals(new User("John", "Smith"), result1);

        Object result2 = expressionProcessor.processExpression("${[user] John} activated");
        assertNotNull(result2);
        assertEquals(String.class, result2.getClass());
        assertEquals("John Smith activated", result2);

        Object result3 = expressionProcessor.processExpression("User ${[user] John} activated");
        assertNotNull(result3);
        assertEquals(String.class, result3.getClass());
        assertEquals("User John Smith activated", result3);
    }

    @Test
    void processValidStringToUserDataConverterExpression(Environment environment) {
        ValueExpressionProcessor expressionProcessor = new ValueExpressionProcessor(environment);

        Object result1 = expressionProcessor.processExpression("@{[user name to user]John Smith}");
        assertNotNull(result1);
        assertEquals(User.class, result1.getClass());
        assertEquals(new User("John", "Smith"), result1);

        Object result2 = expressionProcessor.processExpression("@{[user name to user] John Smith}");
        assertNotNull(result2);
        assertEquals(User.class, result2.getClass());
        assertEquals(new User("John", "Smith"), result2);

        Object result3 = expressionProcessor.processExpression("@{[user name to user] John Smith} activated");
        assertNotNull(result3);
        assertEquals(String.class, result3.getClass());
        assertEquals("John Smith activated", result3);

        Object result4 = expressionProcessor.processExpression("User @{[user name to user] John Smith} activated");
        assertNotNull(result4);
        assertEquals(String.class, result4.getClass());
        assertEquals("User John Smith activated", result4);
    }

    @Test
    void processValidUserToStringDataConverterExpression(Environment environment) {
        ValueExpressionProcessor expressionProcessor = new ValueExpressionProcessor(environment);

        Object result1 = expressionProcessor.processExpression("@{[user to user name]${[user] John}}");
        assertNotNull(result1);
        assertEquals(String.class, result1.getClass());
        assertEquals("John Smith", result1);

        Object result2 = expressionProcessor.processExpression("@{[user to user name] ${[user] John}}");
        assertNotNull(result2);
        assertEquals(String.class, result2.getClass());
        assertEquals("John Smith", result2);

        Object result3 = expressionProcessor.processExpression("@{[user to user name] ${[user] John}} activated");
        assertNotNull(result3);
        assertEquals(String.class, result3.getClass());
        assertEquals("John Smith activated", result3);

        Object result4 = expressionProcessor.processExpression("User @{[user to user name] ${[user] John}} activated");
        assertNotNull(result4);
        assertEquals(String.class, result4.getClass());
        assertEquals("User John Smith activated", result4);
    }

    @Test
    void processValidUserToStringDataConverterWithFormatExpression(Environment environment) {
        ValueExpressionProcessor expressionProcessor = new ValueExpressionProcessor(environment);

        Object result1 = expressionProcessor.processExpression("@{[user to user name -> Mr.]${[user] John}}");
        assertNotNull(result1);
        assertEquals(String.class, result1.getClass());
        assertEquals("Mr. John Smith", result1);

        Object result2 = expressionProcessor.processExpression("@{[user to user name -> Mr.] ${[user] John}}");
        assertNotNull(result2);
        assertEquals(String.class, result2.getClass());
        assertEquals("Mr. John Smith", result2);

        Object result3 = expressionProcessor.processExpression("@{[user to user name -> Mr.] ${[user] John}} activated");
        assertNotNull(result3);
        assertEquals(String.class, result3.getClass());
        assertEquals("Mr. John Smith activated", result3);

        Object result4 = expressionProcessor.processExpression("User @{[user to user name -> Mr.] ${[user] John}} activated");
        assertNotNull(result4);
        assertEquals(String.class, result4.getClass());
        assertEquals("User Mr. John Smith activated", result4);
    }

    @Test
    @UseEnvironmentConfiguration(TestValueEnvironmentConfigurationWithoutDataSourceService.class)
    void processDataSourceExpressionWithoutDataSourceService(Environment environment) {
        ValueExpressionProcessor expressionProcessor = new ValueExpressionProcessor(environment);

        assertThrows(ServiceNotFoundException.class, () -> expressionProcessor.processExpression("${[user] John}"));
    }

    @Test
    @UseEnvironmentConfiguration(TestValueEnvironmentConfigurationWithoutDataConverterService.class)
    void processDataConverterExpressionWithoutDataConverterService(Environment environment) {
        ValueExpressionProcessor expressionProcessor = new ValueExpressionProcessor(environment);

        assertThrows(ServiceNotFoundException.class, () -> expressionProcessor.processExpression("@{[user to user name] John}"));
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

        assertThrows(StringValueParseException.class, () -> expressionProcessor.processExpression("${[user name] ${[user] John}}"));
    }

    @Test
    void processDataConverterExpressionWithIncorrectKeyType(Environment environment) {
        ValueExpressionProcessor expressionProcessor = new ValueExpressionProcessor(environment);

        assertThrows(StringValueParseException.class, () -> expressionProcessor.processExpression("@{[user name to user] ${[user] John}}"));
    }

    @Test
    void processDataConverterExpressionWithoutName(Environment environment) {
        ValueExpressionProcessor expressionProcessor = new ValueExpressionProcessor(environment);

        assertThrows(StringValueParseException.class, () -> expressionProcessor.processExpression("@{${[user] John}}"));
    }

    @Test
    void processExpressionWithCompoundKey(Environment environment) {
        ValueExpressionProcessor expressionProcessor = new ValueExpressionProcessor(environment);

        assertThrows(StringValueParseException.class, () -> expressionProcessor.processExpression("${[user name] User ${[user] John}}"));
        assertThrows(StringValueParseException.class, () -> expressionProcessor.processExpression("@{[user name to user] User ${[user] John}}"));
    }

}
