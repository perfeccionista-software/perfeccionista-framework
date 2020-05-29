package io.perfeccionista.framework.pagefactory.web.pageobjects;

import io.perfeccionista.framework.name.Name;
import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.WebImage;
import io.perfeccionista.framework.pagefactory.elements.WebTextBlock;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocator;

@Name("Домашняя страница")
public interface HomePage extends AbstractWebPage {

    @Name("текст Заголовок")
//    @WebLocator(css = ".title", invokeOnCall = {IsDisplayedFunctionInvoke.class})
    WebTextBlock contentTitle();

    @Name("блок с Флагами")
    FlagsBlock flags();

    @Name("текст Описание")
//    @WebLocator(css = ".content", invokeOnCall = {IsDisplayedFunctionInvoke.class})
    WebTextBlock content();

//    @WebLocator(css = ".flagBlocks", invokeOnCall = {IsDisplayedFunctionInvoke.class})
    interface FlagsBlock extends WebBlock {

        @Name("русский флаг")
//        @WebLocator(css = ".russian", invokeOnCall = {IsDisplayedFunctionInvoke.class})
        WebImage russianFlag();

        @Name("итальянский флаг")
//        @WebLocator(css = ".italian", invokeOnCall = {IsDisplayedFunctionInvoke.class})
        WebImage italyFlag();

        @Name("испанский флаг")
//        @WebLocator(css = ".spain", invokeOnCall = {IsDisplayedFunctionInvoke.class})
        WebImage spainFlag();

    }

}
