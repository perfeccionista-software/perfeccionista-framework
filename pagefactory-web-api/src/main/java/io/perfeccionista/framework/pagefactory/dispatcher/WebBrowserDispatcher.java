package io.perfeccionista.framework.pagefactory.dispatcher;

import io.perfeccionista.framework.exceptions.mapper.WebExceptionMapper;
import io.perfeccionista.framework.pagefactory.dispatcher.context.WebPageContext;
import io.perfeccionista.framework.pagefactory.dispatcher.cookies.WebBrowserCookiesDispatcher;
import io.perfeccionista.framework.pagefactory.dispatcher.executor.WebBrowserOperationExecutor;
import io.perfeccionista.framework.pagefactory.dispatcher.logs.WebBrowserLogsDispatcher;
import io.perfeccionista.framework.pagefactory.dispatcher.tabs.WebBrowserTabsDispatcher;
import io.perfeccionista.framework.pagefactory.dispatcher.window.WebBrowserWindowDispatcher;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;

//TODO: Сюда нужно будет передавать экземпляр сервиса WebPageService
public interface WebBrowserDispatcher {

    @API(status = Status.STABLE)
    WebBrowserDispatcher launch();

    @API(status = Status.STABLE)
    WebBrowserDispatcher close();

    @API(status = Status.EXPERIMENTAL)
    WebExceptionMapper getExceptionMapper();

    @API(status = Status.STABLE)
    WebPageContext getWebPageContext();

    @API(status = Status.STABLE)
    WebBrowserOperationExecutor executor();

    @API(status = Status.STABLE)
    WebBrowserCookiesDispatcher cookies();

    @API(status = Status.STABLE)
    WebBrowserWindowDispatcher window();

    @API(status = Status.STABLE)
    WebBrowserTabsDispatcher tabs();

    @API(status = Status.STABLE)
    WebBrowserLogsDispatcher logs();

    @API(status = Status.INTERNAL)
    <T> T getInstance(Class<T> browserInstanceClass);

}
