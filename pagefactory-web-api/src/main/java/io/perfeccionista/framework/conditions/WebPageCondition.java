package io.perfeccionista.framework.conditions;

import io.perfeccionista.framework.invocation.runner.InvocationInfo;
import io.perfeccionista.framework.pagefactory.elements.WebPage;
import org.jetbrains.annotations.NotNull;

public interface WebPageCondition {

    /**
     * С помощью этого метода выполняется непосредственная проверка условия
     * @param page
     * @return
     */
    void check(@NotNull WebPage page, @NotNull InvocationInfo invocationInfo);

    /**
     * Метод возвращает информацию о выполняемом условии
     * @return
     */
    InvocationInfo createInvocationInfoForElement(@NotNull WebPage page);

    /**
     * С помощью этого метода мы проверяем применимость матчера к тестируемому элементу,
     * например, наличие требуемого интерфейса: WebClickAvailable
     */
    WebElementCondition validate(@NotNull WebPage page);

    WebElementCondition forComponent(@NotNull String componentName);

    WebElementCondition inverse();

}
