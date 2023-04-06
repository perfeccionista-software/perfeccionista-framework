package io.perfeccionista.framework.pagefactory.pageobjects;

import io.perfeccionista.framework.name.Name;
import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.elements.WebPage;
import io.perfeccionista.framework.pagefactory.elements.WebText;
import io.perfeccionista.framework.pagefactory.elements.selectors.WebItemSelector;
import io.perfeccionista.framework.pagefactory.elements.selectors.WebSelector;
import io.perfeccionista.framework.pagefactory.operation.handler.JsCheckIsDisplayed;
import io.perfeccionista.framework.pagefactory.pageobjects.elements.LeftMenu;
import io.perfeccionista.framework.pagefactory.pageobjects.elements.LeftMenu.LeftMenuItemBlock;

public interface AbstractWebPage extends WebPage<AbstractWebPage> {

    @Name("Logo")
    @Name("Логотип")
    @WebSelector(text = "Perfeccionista")
    WebText logo();

    @Name("Left menu")
    @Name("Левое меню")
    @WebSelector(id = "main-menu", invokeOnCall = JsCheckIsDisplayed.class)
    LeftMenu leftMenu();

    // Эти элементы проверяют корректность инициализации страницы для разных вариантов конфигурации

    @WebSelector(id = "main-menu", invokeOnCall = JsCheckIsDisplayed.class)
    @WebItemSelector(css = ".list-group-item")
    WebList<LeftMenuItemBlock> testLeftMenu1();

    @WebSelector(id = "main-menu", invokeOnCall = JsCheckIsDisplayed.class)
    LeftMenu2 testLeftMenu2();

    @WebSelector(id = "main-menu", invokeOnCall = JsCheckIsDisplayed.class)
    LeftMenu3 testLeftMenu3();

    @WebItemSelector(css = ".list-group-item")
    interface LeftMenu2 extends LeftMenu {}

    @WebItemSelector(css = ".list-group-item")
    interface LeftMenu3 extends WebList {}

}
