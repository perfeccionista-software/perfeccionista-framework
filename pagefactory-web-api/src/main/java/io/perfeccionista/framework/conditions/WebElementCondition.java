package io.perfeccionista.framework.conditions;

import io.perfeccionista.framework.invocation.runner.InvocationInfo;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import org.jetbrains.annotations.NotNull;


public interface WebElementCondition {

    /**
     * С помощью этого метода выполняется непосредственная проверка условия
     * @param element
     * @return
     */
    void check(@NotNull WebChildElement element, @NotNull InvocationInfo invocationInfo);

    /**
     * Метод возвращает информацию о выполняемом условии
     * @return
     */
    InvocationInfo createInvocationInfoForElement(@NotNull WebChildElement element);

    /**
     * С помощью этого метода мы проверяем применимость матчера к тестируемому элементу,
     * например, наличие требуемого интерфейса: WebClickAvailable
     */
    WebElementCondition validate(@NotNull WebChildElement element);

    WebElementCondition forComponent(@NotNull String componentName);

    WebElementCondition inverse();

}
