package io.perfeccionista.framework.pagefactory.web.pageobjects;

import io.perfeccionista.framework.name.Name;
import io.perfeccionista.framework.pagefactory.elements.WebTextBlock;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocator;
import io.perfeccionista.framework.pagefactory.js.checks.IsDisplayedCheck;

public interface HomePage extends AbstractWebPage {

    @Name("текст Заголовок")
    @WebLocator(css = ".title", elementChecks = {IsDisplayedCheck.class})
    WebTextBlock contentTitle();

    @Name("текст Описание")
    @WebLocator(css = ".content", elementChecks = {IsDisplayedCheck.class})
    WebTextBlock content();

}
