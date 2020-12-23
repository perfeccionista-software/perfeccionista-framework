package io.perfeccionista.framework.pagefactory.pageobjects;

import io.perfeccionista.framework.name.Name;
import io.perfeccionista.framework.pagefactory.elements.WebPage;
import io.perfeccionista.framework.pagefactory.elements.WebTextBlock;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocator;
import io.perfeccionista.framework.pagefactory.jsfunction.CheckIsDisplayed;
import io.perfeccionista.framework.pagefactory.pageobjects.elements.LeftMenu;

public interface AbstractWebPage extends WebPage {

    @Name("Logo")
    @Name("Логотип")
    @WebLocator(text = "Perfeccionista")
    WebTextBlock logo();

    @Name("Left menu")
    @Name("Левое меню")
    @WebLocator(id = "main-menu", invokeOnCall = CheckIsDisplayed.class)
    LeftMenu leftMenu();

}
