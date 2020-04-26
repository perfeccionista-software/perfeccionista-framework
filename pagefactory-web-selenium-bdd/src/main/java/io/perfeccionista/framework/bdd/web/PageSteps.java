package io.perfeccionista.framework.bdd.web;

import io.cucumber.java.en.Given;
import io.perfeccionista.framework.bdd.EnvironmentAvailable;
import io.perfeccionista.framework.bdd.parameters.ValueStringParameter;
import io.perfeccionista.framework.bdd.parameters.WebPageParameter;
import io.perfeccionista.framework.pagefactory.browser.WebBrowserService;

// TODO: Wrap runLogic()
// TODO: Add step categories
// TODO: Как сделать снятие скриншотов конфигурируемым из проекта (для каждого метода)
public class PageSteps implements EnvironmentAvailable {

    // TODO: Пользователь нажимает комбинацию клавиш (Ctrl + R, ...)

    /**
     *
     * @param webPage -
     */
    @Given("page {webPage} opens")
    @Given("открывается страница {webPage}")
    public void webPageOpens(WebPageParameter webPage) {
        webPage.usePage();
    }

    /**
     *
     */
    @Given("user clicks 'Refresh' button")
    @Given("пользователь нажимает кнопку 'Обновить'")
    public void userClicksRefreshButton() {
        getEnvironment().getService(WebBrowserService.class)
                .getActiveDispatcher()
                .refresh();
    }

    /**
     *
     */
    @Given("user clicks 'Back' button")
    @Given("пользователь нажимает кнопку 'Назад'")
    public void userClicksBackButton() {
        getEnvironment().getService(WebBrowserService.class)
                .getActiveDispatcher()
                .back();
    }

    /**
     *
     * @param tabUrl -
     */
    @Given("user enters a URL {stringValue} in the browser and clicks 'Enter'")
    @Given("пользователь вводит URL {stringValue} в браузер и нажимает 'Enter'")
    public void userOpenUrl(ValueStringParameter tabUrl) {
        getEnvironment().getService(WebBrowserService.class)
                .getActiveDispatcher()
                .openUrl(tabUrl.getProcessedValue());
    }

    /**
     *
     */
    @Given("user opens new tab")
    @Given("пользователь открывает новую вкладку")
    public void userOpensNewTab() {
        getEnvironment().getService(WebBrowserService.class)
                .getActiveDispatcher()
                .newTab();
    }

    /**
     *
     * @param tabUrl -
     */
    @Given("user opens new tab with URL {stringValue}")
    @Given("пользователь открывает новую вкладку с URL {stringValue}")
    public void userOpensNewTabWithUrl(ValueStringParameter tabUrl) {
        getEnvironment().getService(WebBrowserService.class)
                .getActiveDispatcher()
                .newTab(tabUrl.getProcessedValue());
    }

    /**
     *
     * @param tabTitle -
     */
    @Given("active tab contain title {stringValue}")
    @Given("активная вкладка содержит заголовок {stringValue}")
    public void activeTabContainTitle(ValueStringParameter tabTitle) {
        getEnvironment().getService(WebBrowserService.class)
                .getActiveDispatcher()
                .activeTabShouldHaveTitle(tabTitle.getValue());
    }

    /**
     *
     * @param tabTitle -
     */
    @Given("active tab doesn't contain title {stringValue}")
    @Given("активная вкладка не содержит заголовок {stringValue}")
    public void activeTabDoesNotContainTitle(ValueStringParameter tabTitle) {
        getEnvironment().getService(WebBrowserService.class)
                .getActiveDispatcher()
                .activeTabShouldNotHaveTitle(tabTitle.getValue());
    }

    /**
     *
     * @param tabUrl -
     */
    @Given("active tab contain URL {stringValue}")
    @Given("активная вкладка содержит URL {stringValue}")
    public void activeTabContainUrl(ValueStringParameter tabUrl) {
        getEnvironment().getService(WebBrowserService.class)
                .getActiveDispatcher()
                .activeTabShouldHaveUrl(tabUrl.getValue());
    }

    /**
     *
     * @param tabUrl -
     */
    @Given("active tab doesn't contain URL {stringValue}")
    @Given("активная вкладка не содержит URL {stringValue}")
    public void activeTabDoesNotContainUrl(ValueStringParameter tabUrl) {
        getEnvironment().getService(WebBrowserService.class)
                .getActiveDispatcher()
                .activeTabShouldNotHaveUrl(tabUrl.getValue());
    }

    /**
     *
     * @param tabTitle -
     */
    @Given("browser has tab with title {stringValue}")
    @Given("в браузере присутствует вкладка с заголовком {stringValue}")
    public void tabWithTitleExists(ValueStringParameter tabTitle) {
        getEnvironment().getService(WebBrowserService.class)
                .getActiveDispatcher()
                .shouldHaveTabWithTitle(tabTitle.getValue());
    }

    /**
     *
     * @param tabTitle -
     */
    @Given("browser doesn't have tab with title {stringValue}")
    @Given("в браузере отсутствует вкладка с заголовком {stringValue}")
    public void tabWithTitleAbsent(ValueStringParameter tabTitle) {
        getEnvironment().getService(WebBrowserService.class)
                .getActiveDispatcher()
                .shouldNotHaveTabWithTitle(tabTitle.getValue());
    }

    /**
     *
     * @param tabUrl -
     */
    @Given("browser has tab with URL {stringValue}")
    @Given("в браузере присутствует вкладка с URL {stringValue}")
    public void tabWithUrlExists(ValueStringParameter tabUrl) {
        getEnvironment().getService(WebBrowserService.class)
                .getActiveDispatcher()
                .shouldHaveTabWithUrl(tabUrl.getValue());
    }

    /**
     *
     * @param tabUrl -
     */
    @Given("browser doesn't have tab with URL {stringValue}")
    @Given("в браузере отсутствует вкладка с URL {stringValue}")
    public void tabWithUrlAbsent(ValueStringParameter tabUrl) {
        getEnvironment().getService(WebBrowserService.class)
                .getActiveDispatcher()
                .shouldNotHaveTabWithUrl(tabUrl.getValue());
    }

    /**
     *
     * @param tabTitle -
     */
    @Given("user switches to tab with title {stringValue}")
    @Given("пользователь переключается на вкладку с заголовком {stringValue}")
    public void userSetTabByTitle(ValueStringParameter tabTitle) {
        getEnvironment().getService(WebBrowserService.class)
                .getActiveDispatcher()
                .switchToTabWithTitle(tabTitle.getValue());
    }

    /**
     *
     * @param tabUrl -
     */
    @Given("user switches to tab with URL {stringValue}")
    @Given("пользователь переключается на вкладку с URL {stringValue}")
    public void userSetTabByUrl(ValueStringParameter tabUrl) {
        getEnvironment().getService(WebBrowserService.class)
                .getActiveDispatcher()
                .switchToTabWithUrl(tabUrl.getValue());
    }

    /**
     *
     */
    @Given("user closes active tab")
    @Given("пользователь закрывает активную вкладку")
    public void userCloseActiveTab() {
        getEnvironment().getService(WebBrowserService.class)
                .getActiveDispatcher()
                .closeActiveTab();
    }

    /**
     *
     * @param tabTitle -
     */
    @Given("user closes tab with title {stringValue}")
    @Given("пользователь закрывает вкладку с заголовком {stringValue}")
    public void userCloseTabWithTitle(ValueStringParameter tabTitle) {
        getEnvironment().getService(WebBrowserService.class)
                .getActiveDispatcher()
                .closeTabWithTitle(tabTitle.getValue());
    }

    /**
     *
     * @param tabUrl -
     */
    @Given("user closes tab with URL {stringValue}")
    @Given("пользователь закрывает вкладку с URL {stringValue}")
    public void userCloseTabWithUrl(ValueStringParameter tabUrl) {
        getEnvironment().getService(WebBrowserService.class)
                .getActiveDispatcher()
                .closeTabWithUrl(tabUrl.getValue());
    }

}
