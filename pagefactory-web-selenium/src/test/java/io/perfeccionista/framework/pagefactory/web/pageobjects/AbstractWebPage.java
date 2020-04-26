package io.perfeccionista.framework.pagefactory.web.pageobjects;

import io.perfeccionista.framework.pagefactory.elements.Required;
import io.perfeccionista.framework.pagefactory.elements.WebPage;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocator;
import io.perfeccionista.framework.pagefactory.web.pageobjects.elements.LeftMenu;

public interface AbstractWebPage extends WebPage {

    @Required
    @WebLocator(id = "left-menu")
    LeftMenu leftMenu();

}
