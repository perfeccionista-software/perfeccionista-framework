package io.perfeccionista.framework.pagefactory.browser;

import io.perfeccionista.framework.exceptions.mapper.ExceptionMapper;
import io.perfeccionista.framework.pagefactory.context.base.WebPageContext;
import io.perfeccionista.framework.pagefactory.browser.cookies.CookiesDispatcher;
import io.perfeccionista.framework.pagefactory.browser.executor.OperationExecutor;
import io.perfeccionista.framework.pagefactory.browser.logs.LogsDispatcher;
import io.perfeccionista.framework.pagefactory.browser.tabs.TabsDispatcher;
import io.perfeccionista.framework.pagefactory.browser.window.WindowDispatcher;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;

//TODO: Сюда нужно будет передавать экземпляр сервиса WebPageService
public interface WebBrowserDispatcher {

    @API(status = Status.STABLE)
    WebBrowserDispatcher launch();

    @API(status = Status.STABLE)
    WebBrowserDispatcher close();

    @API(status = Status.EXPERIMENTAL)
    ExceptionMapper getExceptionMapper();

    @API(status = Status.STABLE)
    WebPageContext getWebPageContext();

    @API(status = Status.STABLE)
    OperationExecutor executor();

    @API(status = Status.STABLE)
    CookiesDispatcher cookies();

    @API(status = Status.STABLE)
    WindowDispatcher window();

    @API(status = Status.STABLE)
    TabsDispatcher tabs();

    @API(status = Status.STABLE)
    LogsDispatcher logs();

    @API(status = Status.INTERNAL)
    <T> T getInstance(Class<T> browserInstanceClass);

}
