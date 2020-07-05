package io.perfeccionista.framework.pagefactory.pageobjects;

import io.perfeccionista.framework.name.Name;
import io.perfeccionista.framework.pagefactory.elements.WebImage;
import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.elements.WebMappedBlock;
import io.perfeccionista.framework.pagefactory.elements.WebTextBlock;
import io.perfeccionista.framework.pagefactory.elements.actions.WebElementAction;
import io.perfeccionista.framework.pagefactory.elements.components.WebComponents;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocator;
import io.perfeccionista.framework.pagefactory.elements.mapping.UseWebMappedBlock;
import io.perfeccionista.framework.pagefactory.elements.properties.WebElementProperty;
import io.perfeccionista.framework.pagefactory.jsfunction.CheckIsDisplayed;
import io.perfeccionista.framework.pagefactory.pageobjects.extractors.AltAttributeExtractor;

import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_HAVE_TEXT_METHOD;

@Name("Main page")
public interface HomePage extends AbstractWebPage {

    @Name("Title")
    @WebLocator(css = ".page-title", invokeOnCall = {CheckIsDisplayed.class})
    WebTextBlock contentTitle();

    @Name("first Text block")
    @WebLocator(containsText = "На данный момент в состав Организации Объединённых Наций")
    WebTextBlock firstTextBlock();







    @Name("первый Блок с флагами")
    @WebLocator(xpath = ".//*[@test-id='flags-first-group']")
    @WebLocator(component = WebComponents.LI, tagName = "img")
    @UseWebMappedBlock(FlagBlock.class)
    WebList flagsFirstGroup();

    @Name("второй Текстовый блок")
    @WebLocator(containsText = "Около десятка государственных образований")
    WebTextBlock secondTextBlock();

    @Name("второй Блок с флагами")
    @WebLocator(xpath = ".//*[@test-id='flags-second-group']")
    @WebLocator(component = WebComponents.LI, tagName = "img")
    @UseWebMappedBlock(FlagBlock.class)
    WebList flagsSecondGroup();

    @Name("третий Текстовый блок")
    @WebLocator(containsText = "Во многих частях света существуют регионы")
    WebTextBlock thirdTextBlock();

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

    interface FlagBlock extends WebMappedBlock {

        @Name("флаг")
        @WebLocator(css = ".img-thumbnail")
        @WebElementProperty(name = "подсказка", extractor = AltAttributeExtractor.class)
        WebImage russianFlag();

    }

    @Override
    default void validatePageOpen() {
        System.out.println("Hi");
    }
}
