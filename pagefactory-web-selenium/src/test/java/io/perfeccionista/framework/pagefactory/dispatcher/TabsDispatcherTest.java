package io.perfeccionista.framework.pagefactory.dispatcher;

import io.perfeccionista.framework.AbstractWebSeleniumParallelTest;
import io.perfeccionista.framework.pagefactory.dispatcher.context.WebPageContext;
import io.perfeccionista.framework.pagefactory.pageobjects.ElementsPage;
import io.perfeccionista.framework.pagefactory.pageobjects.HomePage;
import io.perfeccionista.framework.pagefactory.pageobjects.ListElementsPage;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.perfeccionista.framework.Web.*;
import static io.perfeccionista.framework.value.Values.intEquals;
import static io.perfeccionista.framework.value.Values.stringEquals;
import static io.perfeccionista.framework.value.Values.stringProcess;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("Browser") @Tag("Tabs")
class TabsDispatcherTest extends AbstractWebSeleniumParallelTest {

    @Test
    void tabsDispatcherGettersTest() {
        WebBrowserDispatcher browser = openDefaultBrowser();

        browser.tabs()
                .should(haveTabCount(intEquals(1)));

        assertAll(
                () -> assertEquals(1, browser.tabs().getTabCount()),
                () -> assertEquals("Perfeccionista Portal: Home", browser.tabs().getActiveTabTitle()),
                () -> assertEquals(stringProcess("${[config] start_url}"), browser.tabs().getActiveTabUrl()),
                () -> assertEquals(List.of("Perfeccionista Portal: Home"), browser.tabs().getAllTabTitles()),
                () -> assertEquals(List.of(stringProcess("${[config] start_url}")), browser.tabs().getAllTabUrls())
        );

        browser.tabs()
                .newTab(stringProcess("${[config] start_url}"))
                .should(haveTabCount(intEquals(2)));

        WebPageContext context = browser.getWebPageContext();

        HomePage homePage = context.getPage(HomePage.class);
        homePage.contentTitle()
                .should(beDisplayed());
        homePage.leftMenu()
                .select(stringEquals("Elements"));

        ElementsPage elementsPage = context.getPage(ElementsPage.class);
        // Поскольку ниже используются простые ассерты, нужно быть увереным, что страница открылась
        elementsPage.simpleButton()
                .should(beDisplayed());

        assertAll(
                () -> assertEquals(2, browser.tabs().getTabCount()),
                () -> assertEquals("Perfeccionista Portal: Elements", browser.tabs().getActiveTabTitle()),
                () -> assertEquals(stringProcess("${[config] start_url}elements"), browser.tabs().getActiveTabUrl()),
                () -> {
                    List<String> expectedTitles = List.of("Perfeccionista Portal: Home", "Perfeccionista Portal: Elements");
                    assertEquals(expectedTitles, browser.tabs().getAllTabTitles());
                },
                () -> {
                    List<String> expectedUrls = List.of(
                            stringProcess("${[config] start_url}"),
                            stringProcess("${[config] start_url}elements")
                    );
                    assertEquals(expectedUrls, browser.tabs().getAllTabUrls());
                }
        );
    }

    @Test
    void newEmptyTabTest() {
        WebBrowserDispatcher browser = openDefaultBrowser();

        browser.tabs()
                .should(haveTabCount(intEquals(1)))
                .newTab()
                .should(haveTabCount(intEquals(2)))
                .newTab()
                .should(haveTabCount(intEquals(3)));
    }

    @Test
    void newTabTest() {
        WebBrowserDispatcher browser = openDefaultBrowser();

        browser.tabs()
                .should(haveTabCount(intEquals(1)))
                .newTab(stringProcess("${[config] start_url}"))
                .should(haveTabCount(intEquals(2)))
                .newTab(stringProcess("${[config] start_url}"))
                .should(haveTabCount(intEquals(3)));
    }

    @Test
    void refreshTabTest() {
        WebBrowserDispatcher browser = openDefaultBrowser();

        HomePage homePage = browser.getWebPageContext()
                .getPage(HomePage.class);

        homePage.contentTitle()
                .should(beDisplayed());
        browser.tabs()
                .refresh();
        homePage.contentTitle()
                .should(beDisplayed());
    }

    @Test
    void backButtonTabTest() {
        WebBrowserDispatcher browser = openDefaultBrowser();
        WebPageContext context = browser.getWebPageContext();

        HomePage homePage = context.getPage(HomePage.class);
        homePage.contentTitle()
                .should(beDisplayed());
        homePage.leftMenu()
                .select(stringEquals("Elements"));

        ElementsPage elementsPage = context.getPage(ElementsPage.class);
        elementsPage.simpleButton()
                .should(beDisplayed());
        browser.tabs()
                .back();

        context.usePage(HomePage.class);
        homePage.contentTitle()
                .should(beDisplayed());
    }

    @Test
    void closeActiveTabTest() {
        WebBrowserDispatcher browser = openDefaultBrowser();
        WebPageContext context = browser.getWebPageContext();

        browser.tabs()
                .should(haveTabCount(intEquals(1)))
                .should(activeTabHaveTitle(stringEquals("Perfeccionista Portal: Home")))
                .newTab(stringProcess("${[config] start_url}"))
                .should(haveTabCount(intEquals(2)));

        HomePage homePage = context.getPage(HomePage.class);
        homePage.contentTitle()
                .should(beDisplayed());
        homePage.leftMenu()
                .select(stringEquals("Elements"));

        context.getPage(ElementsPage.class).simpleButton()
                .should(beDisplayed());

        browser.tabs()
                .should(activeTabHaveTitle("Perfeccionista Portal: Elements"))
                .closeActiveTab()
                .should(haveTabCount(1))
                .should(activeTabHaveTitle("Perfeccionista Portal: Home"));
    }

    @Test
    void closeTabWithTitleTest() {
        WebBrowserDispatcher browser = openDefaultBrowser();
        WebPageContext context = browser.getWebPageContext();

        browser.tabs()
                .newTab(stringProcess("${[config] start_url}"))
                .should(haveTabCount(intEquals(2)));

        HomePage homePage = context.getPage(HomePage.class);
        homePage.contentTitle()
                .should(beDisplayed());
        homePage.leftMenu()
                .select(stringEquals("Elements"));

        context.getPage(ElementsPage.class).simpleButton()
                .should(beDisplayed());

        browser.tabs()
                .should(activeTabHaveTitle("Perfeccionista Portal: Elements"))
                .closeTabWithTitle(stringEquals("Perfeccionista Portal: Home"))
                .should(haveTabCount(1))
                .should(activeTabHaveTitle("Perfeccionista Portal: Elements"));
    }

    @Test
    void closeTabWithUrlTest() {
        WebBrowserDispatcher browser = openDefaultBrowser();
        WebPageContext context = browser.getWebPageContext();

        browser.tabs()
                .newTab(stringProcess("${[config] start_url}"))
                .should(haveTabCount(intEquals(2)));

        HomePage homePage = context.getPage(HomePage.class);
        homePage.contentTitle()
                .should(beDisplayed());
        homePage.leftMenu()
                .select(stringEquals("Elements"));

        context.getPage(ElementsPage.class).simpleButton()
                .should(beDisplayed());

        browser.tabs()
                .should(activeTabHaveTitle(stringEquals("Perfeccionista Portal: Elements")))
                .closeTabWithUrl(stringEquals("${[config] start_url}"))
                .should(haveTabCount(1))
                .should(activeTabHaveTitle("Perfeccionista Portal: Elements"));
    }

    @Test
    void switchToTabWithTitleTest() {
        WebBrowserDispatcher browser = openDefaultBrowser();
        WebPageContext context = browser.getWebPageContext();

        browser.tabs()
                .newTab(stringProcess("${[config] start_url}"))
                .should(haveTabCount(intEquals(2)));

        HomePage homePage = context.getPage(HomePage.class);
        homePage.contentTitle()
                .should(beDisplayed());
        homePage.leftMenu()
                .select(stringEquals("Elements"));

        context.getPage(ElementsPage.class).simpleButton()
                .should(beDisplayed());

        browser.tabs()
                .should(activeTabHaveTitle("Perfeccionista Portal: Elements"))
                .should(activeTabHaveUrl(stringEquals("${[config] start_url}elements")))
                .switchToTabWithTitle(stringEquals("Perfeccionista Portal: Home"))
                .should(activeTabHaveTitle("Perfeccionista Portal: Home"))
                .should(activeTabHaveUrl(stringEquals("${[config] start_url}")))
                .switchToTabWithTitle(stringEquals("Perfeccionista Portal: Elements"))
                .should(activeTabHaveTitle("Perfeccionista Portal: Elements"))
                .should(activeTabHaveUrl(stringEquals("${[config] start_url}elements")));
    }

    @Test
    void switchToTabWithUrlTest() {
        WebBrowserDispatcher browser = openDefaultBrowser();
        WebPageContext context = browser.getWebPageContext();

        browser.tabs()
                .newTab(stringProcess("${[config] start_url}"))
                .should(haveTabCount(intEquals(2)));

        HomePage homePage = context.getPage(HomePage.class);
        homePage.contentTitle()
                .should(beDisplayed());
        homePage.leftMenu()
                .select(stringEquals("Elements"));

        context.getPage(ElementsPage.class).simpleButton()
                .should(beDisplayed());

        browser.tabs()
                .should(activeTabHaveTitle("Perfeccionista Portal: Elements"))
                .should(activeTabHaveUrl(stringEquals("${[config] start_url}elements")))
                .switchToTabWithUrl(stringEquals("${[config] start_url}"))
                .should(activeTabHaveTitle("Perfeccionista Portal: Home"))
                .should(activeTabHaveUrl(stringEquals("${[config] start_url}")))
                .switchToTabWithUrl(stringEquals("${[config] start_url}elements"))
                .should(activeTabHaveTitle("Perfeccionista Portal: Elements"))
                .should(activeTabHaveUrl(stringEquals("${[config] start_url}elements")));
    }

    @Test
    void shouldHaveTabWithTitleTest() {
        WebBrowserDispatcher browser = openDefaultBrowser();
        WebPageContext context = browser.getWebPageContext();

        browser.tabs()
                .newTab(stringProcess("${[config] start_url}"))
                .should(haveTabCount(intEquals(2)));

        HomePage homePage = context.getPage(HomePage.class);
        homePage.contentTitle()
                .should(beDisplayed());
        homePage.leftMenu()
                .select(stringEquals("Elements"));

        context.getPage(ElementsPage.class).simpleButton()
                .should(beDisplayed());

        browser.tabs()
                .newTab(stringProcess("${[config] start_url}"))
                .should(haveTabCount(intEquals(3)));

        context.usePage(HomePage.class);
        homePage.contentTitle()
                .should(beDisplayed());
        homePage.leftMenu()
                .select(stringEquals("List Elements"));

        context.getPage(ListElementsPage.class).webList()
                .should(beDisplayed());

        browser.tabs()
                .should(haveTabWithTitle("Perfeccionista Portal: Home"))
                .should(haveTabWithTitle("Perfeccionista Portal: Elements"))
                .should(haveTabWithTitle("Perfeccionista Portal: List Elements"))
                .should(notHaveTabWithTitle("Perfeccionista Portal: Table Elements"));
    }

    @Test
    void shouldHaveTabWithUrlTest() {
        WebBrowserDispatcher browser = openDefaultBrowser();
        WebPageContext context = browser.getWebPageContext();

        browser.tabs()
                .newTab(stringProcess("${[config] start_url}"))
                .should(haveTabCount(intEquals(2)));

        HomePage homePage = context.getPage(HomePage.class);
        homePage.contentTitle()
                .should(beDisplayed());
        homePage.leftMenu()
                .select(stringEquals("Elements"));

        context.getPage(ElementsPage.class).simpleButton()
                .should(beDisplayed());

        browser.tabs()
                .newTab(stringProcess("${[config] start_url}"))
                .should(haveTabCount(intEquals(3)));

        context.usePage(HomePage.class);
        homePage.contentTitle()
                .should(beDisplayed());
        homePage.leftMenu()
                .select(stringEquals("List Elements"));

        context.getPage(ListElementsPage.class).webList()
                .should(beDisplayed());

        browser.tabs()
                .should(haveTabWithUrl(stringEquals("${[config] start_url}")))
                .should(haveTabWithUrl(stringEquals("${[config] start_url}elements")))
                .should(haveTabWithUrl(stringEquals("${[config] start_url}list-elements")))
                .should(notHaveTabWithUrl(stringEquals("${[config] start_url}table-elements")));
    }

}
