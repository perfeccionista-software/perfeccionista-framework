package io.perfeccionista.framework.pagefactory.pageobjects;

import io.perfeccionista.framework.name.Name;
import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.elements.WebPage;
import io.perfeccionista.framework.pagefactory.elements.WebText;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocator;
import io.perfeccionista.framework.pagefactory.operation.handler.JsCheckIsDisplayed;
import io.perfeccionista.framework.pagefactory.pageobjects.elements.LeftMenu;
import io.perfeccionista.framework.pagefactory.pageobjects.elements.LeftMenu.LeftMenuItemBlock;

public interface AbstractWebPage extends WebPage {

    @Name("Logo")
    @Name("Логотип")
    @WebLocator(text = "Perfeccionista")
    WebText logo();

    @Name("Left menu")
    @Name("Левое меню")
    @WebLocator(id = "main-menu", invokeOnCall = JsCheckIsDisplayed.class)
    LeftMenu leftMenu();

    // Эти элементы проверяют корректность инициализации страницы для разных вариантов конфигурации

    @WebLocator(id = "main-menu", invokeOnCall = JsCheckIsDisplayed.class)
    @WebLocator(component = "LI", css = ".list-group-item", single = false)
    WebList<LeftMenuItemBlock> testLeftMenu1();

    @WebLocator(id = "main-menu", invokeOnCall = JsCheckIsDisplayed.class)
    LeftMenu2 testLeftMenu2();

    @WebLocator(id = "main-menu", invokeOnCall = JsCheckIsDisplayed.class)
    LeftMenu3 testLeftMenu3();

    @WebLocator(component = "LI", css = ".list-group-item", single = false)
    interface LeftMenu2 extends LeftMenu {}

    @WebLocator(component = "LI", css = ".list-group-item", single = false)
    interface LeftMenu3 extends WebList {}

}
