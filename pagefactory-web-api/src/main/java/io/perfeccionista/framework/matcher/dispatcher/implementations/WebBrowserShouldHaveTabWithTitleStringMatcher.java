package io.perfeccionista.framework.matcher.dispatcher.implementations;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.exceptions.WebBrowserTabTitle;
import io.perfeccionista.framework.exceptions.attachments.TextAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.ValueAttachmentEntry;
import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.matcher.dispatcher.WebBrowserTabsDispatcherMatcher;
import io.perfeccionista.framework.pagefactory.dispatcher.tabs.WebBrowserTabsDispatcher;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.WEB_BROWSER_CONTAINS_TAB_WITH_EXPECTED_TITLE;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.WEB_BROWSER_DOES_NOT_CONTAIN_TAB_WITH_EXPECTED_TITLE;
import static io.perfeccionista.framework.invocation.runner.InvocationName.assertInvocation;
import static io.perfeccionista.framework.invocation.wrapper.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.dispatcher.WebBrowserActionNames.BROWSER_SHOULD_HAVE_TAB_WITH_TITLE_METHOD;
import static io.perfeccionista.framework.pagefactory.dispatcher.WebBrowserActionNames.BROWSER_SHOULD_NOT_HAVE_TAB_WITH_TITLE_METHOD;

public class WebBrowserShouldHaveTabWithTitleStringMatcher implements WebBrowserTabsDispatcherMatcher {

    protected final String expectedText;
    protected final boolean positive;

    public WebBrowserShouldHaveTabWithTitleStringMatcher(String expectedText, boolean positive) {
        this.expectedText = expectedText;
        this.positive = positive;
    }

    @Override
    public void check(@NotNull WebBrowserTabsDispatcher tabsDispatcher) {
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

    protected void shouldHaveTabWithTitle(WebBrowserTabsDispatcher tabsDispatcher, List<String> actualTabTitles) {
        if (!actualTabTitles.contains(expectedText)) {
            throw WebBrowserTabTitle.assertionError(WEB_BROWSER_DOES_NOT_CONTAIN_TAB_WITH_EXPECTED_TITLE.getMessage())
                    .setProcessed(true)
                    .addLastAttachmentEntry(TextAttachmentEntry.of("Tabs", tabsDispatcher.getDescription()))
                    .addLastAttachmentEntry(ValueAttachmentEntry.of(expectedText, String.join("', '", actualTabTitles)));
        }
    }

    protected void shouldNotHaveTabWithTitle(WebBrowserTabsDispatcher tabsDispatcher, List<String> actualTabTitles) {
        if (actualTabTitles.contains(expectedText)) {
            throw WebBrowserTabTitle.assertionError(WEB_BROWSER_CONTAINS_TAB_WITH_EXPECTED_TITLE.getMessage())
                    .setProcessed(true)
                    .addLastAttachmentEntry(TextAttachmentEntry.of("Tabs", tabsDispatcher.getDescription()))
                    .addLastAttachmentEntry(ValueAttachmentEntry.of(expectedText, String.join("', '", actualTabTitles)));
        }
    }

}
