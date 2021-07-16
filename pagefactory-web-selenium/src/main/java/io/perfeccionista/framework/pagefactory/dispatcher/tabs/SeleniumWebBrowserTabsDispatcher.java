package io.perfeccionista.framework.pagefactory.dispatcher.tabs;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.exceptions.WebBrowserTabNotFound;
import io.perfeccionista.framework.exceptions.attachments.TextAttachmentEntry;
import io.perfeccionista.framework.exceptions.mapper.WebExceptionMapper;
import io.perfeccionista.framework.matcher.dispatcher.WebBrowserTabsDispatcherMatcher;
import io.perfeccionista.framework.value.Value;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.WEB_BROWSER_HAS_NO_TAB_WITH_TITLE;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.WEB_BROWSER_HAS_NO_TAB_WITH_URL;
import static io.perfeccionista.framework.invocation.runner.InvocationInfo.actionInvocation;
import static io.perfeccionista.framework.invocation.runner.InvocationInfo.getterInvocation;
import static io.perfeccionista.framework.invocation.wrapper.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.dispatcher.WebBrowserActionNames.BROWSER_BACK_METHOD;
import static io.perfeccionista.framework.pagefactory.dispatcher.WebBrowserActionNames.BROWSER_CLOSE_ACTIVE_TAB_METHOD;
import static io.perfeccionista.framework.pagefactory.dispatcher.WebBrowserActionNames.BROWSER_CLOSE_TAB_WITH_TITLE_METHOD;
import static io.perfeccionista.framework.pagefactory.dispatcher.WebBrowserActionNames.BROWSER_CLOSE_TAB_WITH_URL_METHOD;
import static io.perfeccionista.framework.pagefactory.dispatcher.WebBrowserActionNames.BROWSER_GET_ACTIVE_TAB_TITLE_METHOD;
import static io.perfeccionista.framework.pagefactory.dispatcher.WebBrowserActionNames.BROWSER_GET_ACTIVE_TAB_URL_METHOD;
import static io.perfeccionista.framework.pagefactory.dispatcher.WebBrowserActionNames.BROWSER_GET_ALL_TAB_TITLES_METHOD;
import static io.perfeccionista.framework.pagefactory.dispatcher.WebBrowserActionNames.BROWSER_GET_ALL_TAB_URLS_METHOD;
import static io.perfeccionista.framework.pagefactory.dispatcher.WebBrowserActionNames.BROWSER_GET_TAB_COUNT_METHOD;
import static io.perfeccionista.framework.pagefactory.dispatcher.WebBrowserActionNames.BROWSER_OPEN_URL_METHOD;
import static io.perfeccionista.framework.pagefactory.dispatcher.WebBrowserActionNames.BROWSER_REFRESH_METHOD;
import static io.perfeccionista.framework.pagefactory.dispatcher.WebBrowserActionNames.BROWSER_SWITCH_TO_TAB_WITH_TITLE_METHOD;
import static io.perfeccionista.framework.pagefactory.dispatcher.WebBrowserActionNames.BROWSER_SWITCH_TO_TAB_WITH_URL_METHOD;
import static io.perfeccionista.framework.pagefactory.dispatcher.WebBrowserActionNames.OPEN_EMPTY_TAB_METHOD;
import static io.perfeccionista.framework.pagefactory.dispatcher.WebBrowserActionNames.OPEN_TAB_WITH_URL_METHOD;
import static io.perfeccionista.framework.utils.UrlUtils.isAbsoluteUrl;
import static io.perfeccionista.framework.utils.UrlUtils.withoutFollowingSlash;
import static java.util.stream.Collectors.toList;

// TODO: Эти манипуляции скорее всего можно сделать через JS проще
public class SeleniumWebBrowserTabsDispatcher implements WebBrowserTabsDispatcher {

    protected final Environment environment;
    protected final RemoteWebDriver instance;
    protected final WebExceptionMapper exceptionMapper;

    public SeleniumWebBrowserTabsDispatcher(Environment environment, RemoteWebDriver instance, WebExceptionMapper exceptionMapper) {
        this.environment = environment;
        this.instance = instance;
        this.exceptionMapper = exceptionMapper;
    }

    @Override
    public int getTabCount() {
        return runCheck(getterInvocation(BROWSER_GET_TAB_COUNT_METHOD), () ->
                exceptionMapper.map(() -> instance.getWindowHandles().size()))
                .ifException(exception -> {
                    throw exception;
                })
                .getResult();
    }

    @Override
    public @NotNull String getActiveTabTitle() {
        return runCheck(getterInvocation(BROWSER_GET_ACTIVE_TAB_TITLE_METHOD), () ->
                exceptionMapper.map(instance::getTitle))
                .ifException(exception -> {
                    throw exception;
                })
                .getResult();
    }

    @Override
    public @NotNull String getActiveTabUrl() {
        return runCheck(getterInvocation(BROWSER_GET_ACTIVE_TAB_URL_METHOD), () ->
                exceptionMapper.map(instance::getCurrentUrl))
                .ifException(exception -> {
                    throw exception;
                })
                .getResult();
    }

    @Override
    public @NotNull List<String> getAllTabTitles() {
        return runCheck(getterInvocation(BROWSER_GET_ALL_TAB_TITLES_METHOD), () ->
                exceptionMapper.map(() -> instance.getWindowHandles().stream()
                        .map(windowHandle -> {
                            instance.switchTo().window(windowHandle);
                            return instance.getTitle();
                        }).collect(toList())))
                .ifException(exception -> {
                    throw exception;
                })
                .getResult();
    }

    @Override
    public @NotNull List<String> getAllTabUrls() {
        return runCheck(getterInvocation(BROWSER_GET_ALL_TAB_URLS_METHOD), () ->
                exceptionMapper.map(() -> instance.getWindowHandles().stream()
                        .map(windowHandle -> {
                            instance.switchTo().window(windowHandle);
                            return instance.getCurrentUrl();
                        }).collect(toList())))
                .ifException(exception -> {
                    throw exception;
                })
                .getResult();
    }

    @Override
    public SeleniumWebBrowserTabsDispatcher newTab() {
        runCheck(actionInvocation(OPEN_EMPTY_TAB_METHOD), () -> {
            exceptionMapper.map(() -> {
                instance.executeScript("window.open();");
                List<String> tabs = new ArrayList<>(instance.getWindowHandles());
                instance.switchTo().window(tabs.get(tabs.size() - 1));
            }).ifException(exception -> {
                throw exception;
            });
        });
        return this;
    }

    @Override
    public SeleniumWebBrowserTabsDispatcher newTab(@NotNull String absoluteUrl) {
        runCheck(actionInvocation(OPEN_TAB_WITH_URL_METHOD, absoluteUrl), () -> {
            exceptionMapper.map(() -> {
                instance.executeScript("window.open('" + absoluteUrl + "','_blank');");
                List<String> tabs = new ArrayList<>(instance.getWindowHandles());
                instance.switchTo().window(tabs.get(tabs.size() - 1));
            }).ifException(exception -> {
                throw exception;
            });
        });
        return this;
    }

    @Override
    public SeleniumWebBrowserTabsDispatcher refresh() {
        runCheck(actionInvocation(BROWSER_REFRESH_METHOD), () -> {
            exceptionMapper.map(() -> instance.navigate().refresh())
                    .ifException(exception -> {
                        throw exception;
                    });
        });
        return this;
    }

    @Override
    public SeleniumWebBrowserTabsDispatcher back() {
        runCheck(actionInvocation(BROWSER_BACK_METHOD), () -> {
            exceptionMapper.map(() -> instance.navigate().back())
                    .ifException(exception -> {
                        throw exception;
                    });
        });
        return this;
    }

    @Override
    public SeleniumWebBrowserTabsDispatcher openUrl(@NotNull String relativeOrAbsoluteUrl) {
        runCheck(actionInvocation(BROWSER_OPEN_URL_METHOD, relativeOrAbsoluteUrl), () -> {
            exceptionMapper.map(() -> {
                String actualUrl;
                if (isAbsoluteUrl(relativeOrAbsoluteUrl)) {
                    actualUrl = relativeOrAbsoluteUrl;
                } else {
                    actualUrl = withoutFollowingSlash(instance.getCurrentUrl()) + relativeOrAbsoluteUrl;
                }
                instance.get(actualUrl);
            }).ifException(exception -> {
                throw exception;
            });
        });
        return this;
    }

    @Override
    public SeleniumWebBrowserTabsDispatcher closeActiveTab() {
        runCheck(actionInvocation(BROWSER_CLOSE_ACTIVE_TAB_METHOD), () -> {
            exceptionMapper.map(() -> {
                Set<String> windowHandles = instance.getWindowHandles();
                String currentHandle = instance.getWindowHandle();
                instance.close();
                windowHandles.stream()
                        .filter(handle -> !currentHandle.equals(handle))
                        .findFirst()
                        .ifPresent(handle -> instance.switchTo().window(handle));
            }).ifException(exception -> {
                throw exception;
            });
        });
        return this;
    }

    @Override
    public SeleniumWebBrowserTabsDispatcher closeTabWithTitle(@NotNull Value<String> tabTitle) {
        runCheck(actionInvocation(BROWSER_CLOSE_TAB_WITH_TITLE_METHOD, tabTitle.getShortDescription()), () -> {
            exceptionMapper.map(() -> {
                String currentHandle = instance.getWindowHandle();
                Set<String> windowHandles = instance.getWindowHandles();
                Set<String> windowTitles = new HashSet<>();
                String targetHandle = windowHandles.stream()
                        .filter(windowHandle -> {
                            instance.switchTo().window(windowHandle);
                            String currentTabTitle = instance.getTitle();
                            windowTitles.add(currentTabTitle);
                            return tabTitle.check(currentTabTitle);
                        })
                        .findFirst()
                        .orElseThrow(() -> WebBrowserTabNotFound.exception(WEB_BROWSER_HAS_NO_TAB_WITH_TITLE.getMessage(tabTitle.get()))
                                .addLastAttachmentEntry(TextAttachmentEntry.of("Browser tab titles", windowTitles)));
                if (windowHandles.size() == 1) {
                    instance.close();
                    return;
                }
                instance.switchTo().window(targetHandle);
                instance.close();
                if (instance.getWindowHandles().contains(currentHandle)) {
                    instance.switchTo().window(currentHandle);
                }
            }).ifException(exception -> {
                throw exception;
            });
        });
        return this;
    }

    @Override
    public SeleniumWebBrowserTabsDispatcher closeTabWithUrl(@NotNull Value<String> tabUrl) {
        runCheck(actionInvocation(BROWSER_CLOSE_TAB_WITH_URL_METHOD, tabUrl.getShortDescription()), () -> {
            exceptionMapper.map(() -> {
                String currentHandle = instance.getWindowHandle();
                Set<String> windowHandles = instance.getWindowHandles();
                Set<String> windowUrls = new HashSet<>();
                String targetHandle = windowHandles.stream()
                        .filter(windowHandle -> {
                            instance.switchTo().window(windowHandle);
                            String currentTabUrl = instance.getCurrentUrl();
                            windowUrls.add(currentTabUrl);
                            return tabUrl.check(currentTabUrl);
                        })
                        .findFirst()
                        .orElseThrow(() -> WebBrowserTabNotFound.exception(WEB_BROWSER_HAS_NO_TAB_WITH_URL.getMessage(tabUrl.get()))
                                .addLastAttachmentEntry(TextAttachmentEntry.of("Browser tab urls", windowUrls)));
                if (windowHandles.size() == 1) {
                    instance.close();
                    return;
                }
                instance.switchTo().window(targetHandle);
                instance.close();
                if (instance.getWindowHandles().contains(currentHandle)) {
                    instance.switchTo().window(currentHandle);
                }
            }).ifException(exception -> {
                throw exception;
            });
        });
        return this;
    }

    @Override
    public SeleniumWebBrowserTabsDispatcher switchToTabWithTitle(@NotNull Value<String> tabTitle) {
        runCheck(actionInvocation(BROWSER_SWITCH_TO_TAB_WITH_TITLE_METHOD, tabTitle.getShortDescription()), () -> {
            exceptionMapper.map(() -> {
                Set<String> windowHandles = instance.getWindowHandles();
                Set<String> windowTitles = new HashSet<>();
                String targetHandle = windowHandles.stream()
                        .filter(windowHandle -> {
                            instance.switchTo().window(windowHandle);
                            String currentTabTitle = instance.getTitle();
                            windowTitles.add(currentTabTitle);
                            return tabTitle.check(currentTabTitle);
                        })
                        .findFirst()
                        .orElseThrow(() -> WebBrowserTabNotFound.exception(WEB_BROWSER_HAS_NO_TAB_WITH_TITLE.getMessage(tabTitle.get()))
                                .addLastAttachmentEntry(TextAttachmentEntry.of("Browser tab titles", windowTitles)));
                instance.switchTo().window(targetHandle);
            }).ifException(exception -> {
                throw exception;
            });
        });
        return this;
    }

    @Override
    public SeleniumWebBrowserTabsDispatcher switchToTabWithUrl(@NotNull Value<String> tabUrl) {
        runCheck(actionInvocation(BROWSER_SWITCH_TO_TAB_WITH_URL_METHOD, tabUrl.getShortDescription()), () -> {
            exceptionMapper.map(() -> {
                Set<String> windowHandles = instance.getWindowHandles();
                Set<String> windowUrls = new HashSet<>();
                String targetHandle = windowHandles.stream()
                        .filter(windowHandle -> {
                            instance.switchTo().window(windowHandle);
                            String currentTabUrl = instance.getCurrentUrl();
                            windowUrls.add(currentTabUrl);
                            return tabUrl.check(currentTabUrl);
                        })
                        .findFirst()
                        .orElseThrow(() -> WebBrowserTabNotFound.exception(WEB_BROWSER_HAS_NO_TAB_WITH_URL.getMessage(tabUrl.get()))
                                .addLastAttachmentEntry(TextAttachmentEntry.of("Browser tab urls", windowUrls)));
                instance.switchTo().window(targetHandle);
            }).ifException(exception -> {
                throw exception;
            });
        });
        return this;
    }

    @Override
    public SeleniumWebBrowserTabsDispatcher should(@NotNull WebBrowserTabsDispatcherMatcher matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public @NotNull String getDescription() {
        return exceptionMapper.map(() -> {
            StringBuilder sb = new StringBuilder();
            instance.getWindowHandles().forEach(windowHandle -> {
                        instance.switchTo().window(windowHandle);
                        String currentTabTitle = instance.getTitle();
                        String currentTabUrl = instance.getCurrentUrl();
                        sb.append(currentTabTitle)
                                .append("\n")
                                .append(currentTabUrl)
                                .append("\n");
                    });
            return sb.toString();
        }).setResultIfException("unavailable")
                .getResult();
    }

}
