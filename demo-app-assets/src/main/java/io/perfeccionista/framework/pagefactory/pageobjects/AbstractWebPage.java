package io.perfeccionista.framework.pagefactory.pageobjects;

import io.perfeccionista.framework.name.Name;
import io.perfeccionista.framework.pagefactory.elements.WebPage;
import io.perfeccionista.framework.pagefactory.elements.WebText;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocator;
import io.perfeccionista.framework.pagefactory.operation.handler.JsCheckIsDisplayed;
import io.perfeccionista.framework.pagefactory.pageobjects.elements.LeftMenu;

public interface AbstractWebPage extends WebPage {

    @Name("Logo")
    @Name("Логотип")
    @WebLocator(text = "Perfeccionista")
    WebText logo();

    @Name("Left menu")
    @Name("Левое меню")
    @WebLocator(id = "main-menu", invokeOnCall = JsCheckIsDisplayed.class)
    LeftMenu leftMenu();

}
