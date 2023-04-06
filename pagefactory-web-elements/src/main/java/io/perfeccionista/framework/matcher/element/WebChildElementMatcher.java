package io.perfeccionista.framework.matcher.element;

import io.perfeccionista.framework.matcher.PerfeccionistaMatcher;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import org.jetbrains.annotations.NotNull;

public interface WebChildElementMatcher extends PerfeccionistaMatcher<WebChildElement> {

    @Override
    void check(@NotNull WebChildElement element);

    /**
     * С помощью этого метода мы проверяем применимость матчера к тестируемому элементу,
     * например, наличие требуемого интерфейса: WebClickAvailable
     */
    void validate(@NotNull WebChildElement element);

}
