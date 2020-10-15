package io.perfeccionista.framework.pagefactory.elements.actions;

import io.perfeccionista.framework.pagefactory.elements.actions.base.WebElementJsOperationActionImplementation;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElementBase;
import io.perfeccionista.framework.pagefactory.operation.JsOperation;
import org.jetbrains.annotations.NotNull;

// TODO: Добавить в функции явный вызов scrollTo, чтобы не приходилось их постоянно дописывать в тесте
//  Сюда же можно добавить вызовы и других функций для проверки соответствующих условий (clickable и т.д.)
/**
 * Not implemented yet
 */
@Deprecated
public class JsMouseClickLeftButton implements WebElementJsOperationActionImplementation<Void> {

    @Override
    public @NotNull Void execute(WebChildElementBase element, Object... args) {
        // TODO: Implement
        throw new UnsupportedOperationException("Action is not implemented yet");
    }

    @Override
    public JsOperation<Void> getJsOperation(WebChildElementBase element, Object... args) {
        // TODO: Implement
        throw new UnsupportedOperationException("Action is not implemented yet");
    }

}