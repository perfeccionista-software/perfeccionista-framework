package io.perfeccionista.framework.pagefactory.configurations;

import io.perfeccionista.framework.DefaultSeleniumEnvironmentConfiguration;
import io.perfeccionista.framework.datasource.NamedDataSourceService;
import io.perfeccionista.framework.pagefactory.WebPageService;
import io.perfeccionista.framework.pagefactory.browser.WebBrowserService;
import io.perfeccionista.framework.pagefactory.configurations.datasource.TestDataSourceServiceConfiguration;
import io.perfeccionista.framework.service.UseService;

@UseService(service = NamedDataSourceService.class, configuration = TestDataSourceServiceConfiguration.class)
@UseService(service = WebBrowserService.class, configuration = TestWebBrowserServiceConfiguration.class)
@UseService(service = WebPageService.class, configuration = TestWebPageServiceConfiguration.class)
public class TestEnvironmentConfiguration extends DefaultSeleniumEnvironmentConfiguration {
}
