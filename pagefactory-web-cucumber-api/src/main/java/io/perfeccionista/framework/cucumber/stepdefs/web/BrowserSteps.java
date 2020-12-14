package io.perfeccionista.framework.cucumber.stepdefs.web;

import io.cucumber.java.en.Given;
import io.cucumber.java.ru.Дано;
import io.perfeccionista.framework.cucumber.parameters.ValueIntegerParameter;
import io.perfeccionista.framework.cucumber.parameters.ValueStringParameter;
import io.perfeccionista.framework.cucumber.parameters.WebBrowserParameter;
import io.perfeccionista.framework.pagefactory.browser.WebBrowserService;

// TODO: Wrap runLogic()
// TODO: Add step categories
public class BrowserSteps implements WebStepDefinitions {

    /**
     *
     * @param webBrowser -
     */
    @Given("user launch browser {webBrowser}")
    @Дано("пользователь запускает браузер {webBrowser}")
    public void userLaunchWebBrowser(WebBrowserParameter webBrowser) {
        webBrowser.launch();
    }

    /**
     *
     * @param webBrowser -
     * @param webBrowserName -
     */
    @Given("user launch browser {webBrowser} with name {stringValue}")
    @Дано("пользователь запускает браузер {webBrowser} с именем {stringValue}")
    public void userLaunchWebBrowserWithName(WebBrowserParameter webBrowser,
                                             ValueStringParameter webBrowserName) {
        webBrowser.launch(webBrowserName.getProcessedValue());
    }

    /**
     *
     */
    @Given("user close the browser")
    @Дано("пользователь закрывает браузер")
    public void userCloseWebBrowser() {
        getEnvironment().getService(WebBrowserService.class)
                .getActiveDispatcher()
                .close();
    }

    /**
     *
     * @param webBrowserName -
     */
    @Given("user close browser with name {stringValue}")
    @Дано("пользователь закрывает браузер с именем {stringValue}")
    public void userCloseWebBrowserWithName(ValueStringParameter webBrowserName) {
        getEnvironment().getService(WebBrowserService.class)
                .getDispatcherByName(webBrowserName.getProcessedValue())
                .close();
    }

    /**
     *
     * @param webBrowserName -
     */
    @Given("user switches to the browser with name {stringValue}")
    @Дано("пользователь переключается на браузер с именем {stringValue}")
    public void userSwitchesToTheWebBrowserWithName(ValueStringParameter webBrowserName) {
        getEnvironment().getService(WebBrowserService.class)
                .setActiveDispatcher(webBrowserName.getProcessedValue());
    }

    /**
     *
     * @param width -
     * @param height -
     */
    @Given("user set browser's window size {integerValue} x {integerValue}")
    @Дано("пользователь устанавливает размер окна браузера {integerValue} на {integerValue}")
    public void userChangeWebBrowserWindowSize(ValueIntegerParameter width,
                                               ValueIntegerParameter height) {
        getEnvironment().getService(WebBrowserService.class)
                .getActiveDispatcher()
                .window()
                .setWindowSize(width.getProcessedValue(), height.getProcessedValue());
    }

}
