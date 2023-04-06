package io.perfeccionista.framework.matcher.dispatcher.implementations;

import io.perfeccionista.framework.exceptions.WebBrowserTabTitle;
import io.perfeccionista.framework.exceptions.attachments.TextAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.ValueAttachmentEntry;
import io.perfeccionista.framework.invocation.runner.InvocationInfo;
import io.perfeccionista.framework.matcher.dispatcher.WebBrowserTabsDispatcherMatcher;
import io.perfeccionista.framework.pagefactory.dispatcher.tabs.WebBrowserTabsDispatcher;
import io.perfeccionista.framework.value.string.StringValue;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.WEB_BROWSER_CONTAINS_TAB_WITH_EXPECTED_TITLE;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.WEB_BROWSER_DOES_NOT_CONTAIN_TAB_WITH_EXPECTED_TITLE;
import static io.perfeccionista.framework.invocation.runner.InvocationInfo.assertInvocation;
import static io.perfeccionista.framework.invocation.wrapper.MultipleAttemptInvocationWrapper.repeatInvocation;
import static io.perfeccionista.framework.pagefactory.dispatcher.WebBrowserActionNames.BROWSER_SHOULD_HAVE_TAB_WITH_TITLE_VALUE_METHOD;
import static io.perfeccionista.framework.pagefactory.dispatcher.WebBrowserActionNames.BROWSER_SHOULD_NOT_HAVE_TAB_WITH_TITLE_VALUE_METHOD;

public class WebBrowserShouldHaveTabWithTitleStringValueMatcher implements WebBrowserTabsDispatcherMatcher {

    protected final StringValue expectedTextValue;
    protected final boolean positive;

    public WebBrowserShouldHaveTabWithTitleStringValueMatcher(StringValue expectedTextValue, boolean positive) {
        this.expectedTextValue = expectedTextValue;
        this.positive = positive;
    }

    @Override
    public void check(@NotNull WebBrowserTabsDispatcher tabsDispatcher) {
        InvocationInfo invocationName = positive
                ? assertInvocation(BROWSER_SHOULD_HAVE_TAB_WITH_TITLE_VALUE_METHOD, expectedTextValue.getShortDescription())
                : assertInvocation(BROWSER_SHOULD_NOT_HAVE_TAB_WITH_TITLE_VALUE_METHOD, expectedTextValue.getShortDescription());

        repeatInvocation(invocationName,
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
        boolean match = actualTabTitles.stream()
                .anyMatch(expectedTextValue::check);
        if (!match) {
            throw WebBrowserTabTitle.assertionError(WEB_BROWSER_DOES_NOT_CONTAIN_TAB_WITH_EXPECTED_TITLE.getMessage())
                    .setProcessed(true)
                    .addLastAttachmentEntry(TextAttachmentEntry.of("Tabs", tabsDispatcher.getDescription()))
                    .addLastAttachmentEntry(ValueAttachmentEntry.of(expectedTextValue, String.join("', '", actualTabTitles)));
        }
    }

    protected void shouldNotHaveTabWithTitle(WebBrowserTabsDispatcher tabsDispatcher, List<String> actualTabTitles) {
        boolean match = actualTabTitles.stream()
                .anyMatch(expectedTextValue::check);
        if (match) {
            throw WebBrowserTabTitle.assertionError(WEB_BROWSER_CONTAINS_TAB_WITH_EXPECTED_TITLE.getMessage())
                    .setProcessed(true)
                    .addLastAttachmentEntry(TextAttachmentEntry.of("Tabs", tabsDispatcher.getDescription()))
                    .addLastAttachmentEntry(ValueAttachmentEntry.of(expectedTextValue, String.join("', '", actualTabTitles)));
        }
    }

}
