package io.perfeccionista.framework.matcher.browser;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.exceptions.WebBrowserTabTitle;
import io.perfeccionista.framework.exceptions.attachments.StringAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.ValueAttachmentEntry;
import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.pagefactory.browser.tabs.TabsDispatcher;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.WEB_BROWSER_CONTAINS_TAB_WITH_EXPECTED_TITLE;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.WEB_BROWSER_DOES_NOT_CONTAIN_TAB_WITH_EXPECTED_TITLE;
import static io.perfeccionista.framework.invocation.runner.InvocationName.assertInvocation;
import static io.perfeccionista.framework.invocation.wrapper.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.browser.WebBrowserActionNames.BROWSER_SHOULD_HAVE_TAB_WITH_TITLE_METHOD;
import static io.perfeccionista.framework.pagefactory.browser.WebBrowserActionNames.BROWSER_SHOULD_NOT_HAVE_TAB_WITH_TITLE_METHOD;

public class BrowserShouldHaveTabWithTitleStringMatcher implements TabsDispatcherMatcher {

    protected final String expectedText;
    protected final boolean positive;

    public BrowserShouldHaveTabWithTitleStringMatcher(String expectedText, boolean positive) {
        this.expectedText = expectedText;
        this.positive = positive;
    }

    @Override
    public void check(@NotNull TabsDispatcher tabsDispatcher) {
        InvocationName invocationName = positive
                ? assertInvocation(BROWSER_SHOULD_HAVE_TAB_WITH_TITLE_METHOD, this, expectedText)
                : assertInvocation(BROWSER_SHOULD_NOT_HAVE_TAB_WITH_TITLE_METHOD, this, expectedText);

        runCheck(Environment.getCurrent(), invocationName,
                () -> {
                    List<String> actualTabTitles = tabsDispatcher.getAllTabTitles();
                    if (positive) {
                        shouldHaveTabWithTitle(tabsDispatcher, actualTabTitles);
                    } else {
                        shouldNotHaveTabWithTitle(tabsDispatcher, actualTabTitles);
                    }
                });
    }

    protected void shouldHaveTabWithTitle(TabsDispatcher tabsDispatcher, List<String> actualTabTitles) {
        if (!actualTabTitles.contains(expectedText)) {
            throw WebBrowserTabTitle.assertionError(WEB_BROWSER_DOES_NOT_CONTAIN_TAB_WITH_EXPECTED_TITLE.getMessage())
                    .setProcessed(true)
                    .addLastAttachmentEntry(StringAttachmentEntry.of("Tabs", tabsDispatcher.getDescription()))
                    .addLastAttachmentEntry(ValueAttachmentEntry.of(expectedText, String.join("', '", actualTabTitles)));
        }
    }

    protected void shouldNotHaveTabWithTitle(TabsDispatcher tabsDispatcher, List<String> actualTabTitles) {
        if (actualTabTitles.contains(expectedText)) {
            throw WebBrowserTabTitle.assertionError(WEB_BROWSER_CONTAINS_TAB_WITH_EXPECTED_TITLE.getMessage())
                    .setProcessed(true)
                    .addLastAttachmentEntry(StringAttachmentEntry.of("Tabs", tabsDispatcher.getDescription()))
                    .addLastAttachmentEntry(ValueAttachmentEntry.of(expectedText, String.join("', '", actualTabTitles)));
        }
    }

}
