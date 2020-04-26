package io.perfeccionista.framework.pagefactory.web.pageobjects;

import io.perfeccionista.framework.name.Name;
import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.WebImage;
import io.perfeccionista.framework.pagefactory.elements.WebTextBlock;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocator;
import io.perfeccionista.framework.pagefactory.js.checks.IsDisplayedAction;

@Name("Домашняя страница")
public interface HomePage extends AbstractWebPage {

    @Name("текст Заголовок")
    @WebLocator(css = ".title", executeOnCall = {IsDisplayedAction.class})
    WebTextBlock contentTitle();

    @Name("блок с Флагами")
    FlagsBlock flags();

    @Name("текст Описание")
    @WebLocator(css = ".content", executeOnCall = {IsDisplayedAction.class})
    WebTextBlock content();

    @WebLocator(css = ".flagBlocks", executeOnCall = {IsDisplayedAction.class})
    interface FlagsBlock extends WebBlock {

        @Name("русский флаг")
        @WebLocator(css = ".russian", executeOnCall = {IsDisplayedAction.class})
        WebImage russianFlag();

        @Name("итальянский флаг")
        @WebLocator(css = ".italian", executeOnCall = {IsDisplayedAction.class})
        WebImage italyFlag();

        @Name("испанский флаг")
        @WebLocator(css = ".spain", executeOnCall = {IsDisplayedAction.class})
        WebImage spainFlag();

    }

}
