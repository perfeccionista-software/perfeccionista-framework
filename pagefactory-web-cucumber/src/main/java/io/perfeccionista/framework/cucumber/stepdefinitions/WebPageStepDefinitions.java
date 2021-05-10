package io.perfeccionista.framework.cucumber.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.ru.Дано;
import io.perfeccionista.framework.cucumber.parameters.ValueStringParameter;
import io.perfeccionista.framework.cucumber.parameters.WebPageParameter;

import static io.perfeccionista.framework.Web.*;

// TODO: Wrap runLogic()
// TODO: Add step categories
// TODO: Как сделать снятие скриншотов конфигурируемым из проекта (для каждого метода)
public class WebPageStepDefinitions implements WebStepDefinitions {

    // TODO: Пользователь нажимает комбинацию клавиш (Ctrl + R, ...)

    /**
     *
     * @param webPage -
     */
    @Given("page {webPage} opens")
    @Дано("открывается страница {webPage}")
    public void webPageOpens(WebPageParameter webPage) {
        webPage.usePage();
    }

    /**
     *
     */
    @Given("user clicks 'Refresh' button")
    @Дано("пользователь нажимает кнопку 'Обновить'")
    public void userClicksRefreshButton() {
        getWebBrowserDispatcher().tabs()
                .refresh();
    }

    /**
     *
     */
    @Given("user clicks 'Back' button")
    @Дано("пользователь нажимает кнопку 'Назад'")
    public void userClicksBackButton() {
        getWebBrowserDispatcher().tabs()
                .back();
    }

    /**
     *
     * @param tabUrl -
     */
    @Given("user enters the URL {stringValue} in the browser and clicks 'Enter'")
    @Дано("пользователь вводит URL {stringValue} в браузер и нажимает 'Enter'")
    public void userOpenUrl(ValueStringParameter tabUrl) {
        getWebBrowserDispatcher().tabs()
                .openUrl(tabUrl.getProcessedValue());
    }

    /**
     *
     */
    @Given("user opens new tab")
    @Дано("пользователь открывает новую вкладку")
    public void userOpensNewTab() {
        getWebBrowserDispatcher().tabs()
                .newTab();
    }

    /**
     *
     * @param tabUrl -
     */
    @Given("user opens new tab with URL {stringValue}")
    @Дано("пользователь открывает новую вкладку с URL {stringValue}")
    public void userOpensNewTabWithUrl(ValueStringParameter tabUrl) {
        getWebBrowserDispatcher().tabs()
                .newTab(tabUrl.getProcessedValue());
    }

    /**
     *
     * @param tabTitle -
     */
    @Given("active tab contain title {stringValue}")
    @Дано("активная вкладка содержит заголовок {stringValue}")
    public void activeTabContainTitle(ValueStringParameter tabTitle) {
        getWebBrowserDispatcher().tabs()
                .should(activeTabHaveTitle(tabTitle.getValue()));
    }

    /**
     *
     * @param tabTitle -
     */
    @Given("active tab doesn't contain title {stringValue}")
    @Дано("активная вкладка не содержит заголовок {stringValue}")
    public void activeTabDoesNotContainTitle(ValueStringParameter tabTitle) {
        getWebBrowserDispatcher().tabs()
                .should(activeTabNotHaveTitle(tabTitle.getValue()));
    }

    /**
     *
     * @param tabUrl -
     */
    @Given("active tab contain URL {stringValue}")
    @Дано("активная вкладка содержит URL {stringValue}")
    public void activeTabContainUrl(ValueStringParameter tabUrl) {
        getWebBrowserDispatcher().tabs()
                .should(activeTabHaveUrl(tabUrl.getValue()));
    }

    /**
     *
     * @param tabUrl -
     */
    @Given("active tab doesn't contain URL {stringValue}")
    @Дано("активная вкладка не содержит URL {stringValue}")
    public void activeTabDoesNotContainUrl(ValueStringParameter tabUrl) {
        getWebBrowserDispatcher().tabs()
                .should(activeTabNotHaveUrl(tabUrl.getValue()));
    }

    /**
     *
     * @param tabTitle -
     */
    @Given("browser has tab with title {stringValue}")
    @Дано("в браузере присутствует вкладка с заголовком {stringValue}")
    public void tabWithTitleExists(ValueStringParameter tabTitle) {
        getWebBrowserDispatcher().tabs()
                .should(haveTabWithTitle(tabTitle.getValue()));
    }

    /**
     *
     * @param tabTitle -
     */
    @Given("browser doesn't have tab with title {stringValue}")
    @Дано("в браузере отсутствует вкладка с заголовком {stringValue}")
    public void tabWithTitleAbsent(ValueStringParameter tabTitle) {
        getWebBrowserDispatcher().tabs()
                .should(notHaveTabWithTitle(tabTitle.getValue()));
    }

    /**
     *
     * @param tabUrl -
     */
    @Given("browser has tab with URL {stringValue}")
    @Дано("в браузере присутствует вкладка с URL {stringValue}")
    public void tabWithUrlExists(ValueStringParameter tabUrl) {
        getWebBrowserDispatcher().tabs()
                .should(haveTabWithUrl(tabUrl.getValue()));
    }

    /**
     *
     * @param tabUrl -
     */
    @Given("browser doesn't have tab with URL {stringValue}")
    @Дано("в браузере отсутствует вкладка с URL {stringValue}")
    public void tabWithUrlAbsent(ValueStringParameter tabUrl) {
        getWebBrowserDispatcher().tabs()
                .should(notHaveTabWithUrl(tabUrl.getValue()));
    }

    /**
     *
     * @param tabTitle -
     */
    @Given("user switches to tab with title {stringValue}")
    @Дано("пользователь переключается на вкладку с заголовком {stringValue}")
    public void userSetTabByTitle(ValueStringParameter tabTitle) {
        getWebBrowserDispatcher().tabs()
                .switchToTabWithTitle(tabTitle.getValue());
    }

    /**
     *
     * @param tabUrl -
     */
    @Given("user switches to tab with URL {stringValue}")
    @Дано("пользователь переключается на вкладку с URL {stringValue}")
    public void userSetTabByUrl(ValueStringParameter tabUrl) {
        getWebBrowserDispatcher().tabs()
                .switchToTabWithUrl(tabUrl.getValue());
    }

    /**
     *
     */
    @Given("user closes active tab")
    @Дано("пользователь закрывает активную вкладку")
    public void userCloseActiveTab() {
        getWebBrowserDispatcher().tabs()
                .closeActiveTab();
    }

    /**
     *
     * @param tabTitle -
     */
    @Given("user closes tab with title {stringValue}")
    @Дано("пользователь закрывает вкладку с заголовком {stringValue}")
    public void userCloseTabWithTitle(ValueStringParameter tabTitle) {
        getWebBrowserDispatcher().tabs()
                .closeTabWithTitle(tabTitle.getValue());
    }

    /**
     *
     * @param tabUrl -
     */
    @Given("user closes tab with URL {stringValue}")
    @Дано("пользователь закрывает вкладку с URL {stringValue}")
    public void userCloseTabWithUrl(ValueStringParameter tabUrl) {
        getWebBrowserDispatcher().tabs()
                .closeTabWithUrl(tabUrl.getValue());
    }

}
