package io.perfeccionista.framework.pagefactory.pageobjects;

import io.perfeccionista.framework.name.Name;
import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.WebImage;
import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.elements.WebText;
import io.perfeccionista.framework.pagefactory.elements.locators.WebListBlockLocator;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocator;
import io.perfeccionista.framework.pagefactory.elements.properties.base.WebElementAttributeProperty;
import io.perfeccionista.framework.pagefactory.operation.handler.JsCheckIsDisplayed;

import static io.perfeccionista.framework.Web.beDisplayed;

@Name("Home page")
@Name("Домашняя страница")
public interface HomePage extends AbstractWebPage {

    @Name("Title")
    @Name("Заголовок")
    @WebLocator(css = ".page-title", invokeOnCall = JsCheckIsDisplayed.class)
    WebText contentTitle();

    @Name("First text block")
    @Name("Первый текстовый блок")
    @WebLocator(containsText = "На данный момент в состав Организации Объединённых Наций")
    WebText firstTextBlock();

    @Name("First flag block")
    @Name("Первый блок с флагами")
    @WebLocator(xpath = ".//*[@test-id='flags-first-group']")
    @WebListBlockLocator(tagName = "img")
    WebList<FlagBlock> flagsFirstGroup();

    @Name("Second text block")
    @Name("Второй текстовый блок")
    @WebLocator(containsText = "Около десятка государственных образований")
    WebText secondTextBlock();

    @Name("Second flag block")
    @Name("Второй блок с флагами")
    @WebLocator(xpath = ".//*[@test-id='flags-second-group']")
    @WebListBlockLocator(tagName = "img")
    WebList<FlagBlock> flagsSecondGroup();

    @Name("Third text block")
    @Name("Третий текстовый блок")
    @WebLocator(containsText = "Во многих частях света существуют регионы")
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

    interface FlagBlock extends WebBlock {

        @Name("Flag image")
        @Name("картинка Флага")
        @WebLocator(css = ".img-thumbnail")
        @WebElementAttributeProperty(name = "подсказка", attribute = "alt")
        WebImage russianFlag();

    }

    @Override
    default void validatePageOpen() {
        contentTitle().should(beDisplayed());
    }

}
