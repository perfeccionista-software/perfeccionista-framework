package io.perfeccionista.framework.pagefactory.pageobjects;

import io.perfeccionista.framework.name.Name;
import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.WebImage;
import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.elements.WebText;
import io.perfeccionista.framework.pagefactory.elements.selectors.WebItemSelector;
import io.perfeccionista.framework.pagefactory.elements.selectors.WebSelector;
import io.perfeccionista.framework.pagefactory.operation.handler.JsCheckIsDisplayed;

import static io.perfeccionista.framework.Web.beDisplayed;

@Name("Home page")
@Name("Домашняя страница")
public interface HomePage extends AbstractWebPage {

    @Name("Title")
    @Name("Заголовок")
    @WebSelector(css = ".page-title", invokeOnCall = JsCheckIsDisplayed.class)
    WebText contentTitle();

    @Name("First text block")
    @Name("Первый текстовый блок")
    @WebSelector(containsText = "На данный момент в состав Организации Объединённых Наций")
    WebText firstTextBlock();

    @Name("First flag block")
    @Name("Первый блок с флагами")
    @WebSelector(xpath = ".//*[@test-id='flags-first-group']")
    @WebItemSelector(tagName = "img")
    WebList<FlagBlock> flagsFirstGroup();

    @Name("Second text block")
    @Name("Второй текстовый блок")
    @WebSelector(containsText = "Около десятка государственных образований")
    WebText secondTextBlock();

    @Name("Second flag block")
    @Name("Второй блок с флагами")
    @WebSelector(xpath = ".//*[@test-id='flags-second-group']")
    @WebItemSelector(tagName = "img")
    WebList<FlagBlock> flagsSecondGroup();

    @Name("Third text block")
    @Name("Третий текстовый блок")
    @WebSelector(containsText = "Во многих частях света существуют регионы")
    WebText thirdTextBlock();


    String vvv();

    default boolean validate() {
        return true;
    }

    default String val() {
        return vvv();
    }

    static String getStatic() {
        return "Hello static";
    }

    interface FlagBlock extends WebBlock<FlagBlock> {

        @Name("Flag image")
        @Name("картинка Флага")
        @WebSelector(css = ".img-thumbnail")
        WebImage russianFlag();

    }

    @Override
    default HomePage shouldBeOpen() {
        contentTitle().should(beDisplayed());
        return this;
    }

}
