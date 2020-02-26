package io.perfeccionista.framework.pagefactory;

import io.perfeccionista.framework.pagefactory.elements.AbstractChildElement;
import io.perfeccionista.framework.pagefactory.elements.Button;
import io.perfeccionista.framework.pagefactory.elements.Checkbox;
import io.perfeccionista.framework.pagefactory.elements.base.Element;
import io.perfeccionista.framework.pagefactory.elements.web.WebButton;
import io.perfeccionista.framework.pagefactory.elements.web.WebCheckbox;
import io.perfeccionista.framework.pagefactory.elements.web.impl.WebButtonImpl;
import io.perfeccionista.framework.pagefactory.elements.web.impl.WebCheckboxImpl;
import io.perfeccionista.framework.pagefactory.js.JsFunction;

import java.util.AbstractMap.SimpleEntry;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface ElementsConfiguration {

    default <T extends Element, I extends AbstractChildElement> Map<Class<T>, Class<I>> elementImplementations() {
        return null;
//        return (Map<Class<T>, Class<I>>) Stream.of(
//                new SimpleEntry<>(WebButton.class, WebButtonImpl.class),
//                new SimpleEntry<>(WebCheckbox.class, WebCheckboxImpl.class)
//        ).collect(Collectors.toMap(SimpleEntry::getKey, SimpleEntry::getValue));
    }

    // TODO: Подумать как можно конфигурить не только элементы используемые по умолчанию, но и связанные с jsFunction скрипты
    //  Исполняемую функцию получаем в JsExecutor.
    default Map<Class<? extends JsFunction<?, ?>>, String> jsFunctionScripts() {
        return null;
    }

}
