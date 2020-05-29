package io.perfeccionista.framework.bdd.web;

import io.cucumber.java.en.Given;
import io.perfeccionista.framework.bdd.EnvironmentAvailable;
import io.perfeccionista.framework.bdd.parameters.ValueIntegerParameter;
import io.perfeccionista.framework.bdd.parameters.ValueStringParameter;
import io.perfeccionista.framework.bdd.parameters.WebBrowserParameter;
import io.perfeccionista.framework.pagefactory.browser.WebBrowserService;

// TODO: Wrap runLogic()
// TODO: Add step categories
public class BrowserSteps implements EnvironmentAvailable {

    /**
     *
     * @param webBrowser -
     */
    @Given("user launch browser {webBrowser}")
    @Given("пользователь запускает браузер {webBrowser}")
    public void userLaunchWebBrowser(WebBrowserParameter webBrowser) {
        webBrowser.launch();
    }

    /**
     *
     * @param webBrowser -
     * @param webBrowserName -
     */
    @Given("user launch browser {webBrowser} with name {stringValue}")
    @Given("пользователь запускает браузер {webBrowser} с именем {stringValue")
    public void userLaunchWebBrowserWithName(WebBrowserParameter webBrowser,
                                             ValueStringParameter webBrowserName) {
        webBrowser.launch(webBrowserName.getProcessedValue());
    }

    /**
     *
     */
    @Given("user close the browser")
    @Given("пользователь закрывает браузер")
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
    @Given("пользователь закрывает браузер с именем {stringValue")
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
    @Given("пользователь переключается на браузер с именем {stringValue")
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
    @Given("пользователь устанавливает размер окна браузера {integerValue} на {integerValue}")
    public void userChangeWebBrowserWindowSize(ValueIntegerParameter width,
                                               ValueIntegerParameter height) {
        getEnvironment().getService(WebBrowserService.class)
                .getActiveDispatcher()
                .window()
                .setWindowSize(width.getProcessedValue(), height.getProcessedValue());
    }

}
