package io.perfeccionista.framework.pagefactory.dispatcher;

public class WebBrowserActionNames {

    private WebBrowserActionNames() {
    }

    public static final String BROWSER_GET_TAB_COUNT_METHOD = "BrowserGetTabCount";                                 //
    public static final String BROWSER_GET_ACTIVE_TAB_TITLE_METHOD = "BrowserGetActiveTabTitle";                    //
    public static final String BROWSER_GET_ACTIVE_TAB_URL_METHOD = "BrowserGetActiveTabUrl";                        //
    public static final String BROWSER_GET_ALL_TAB_TITLES_METHOD = "BrowserGetAllTabTitles";                        //
    public static final String BROWSER_GET_ALL_TAB_URLS_METHOD = "BrowserGetAllTabUrls";                            //

    public static final String BROWSER_GET_COOKIE_BY_NAME_METHOD = "BrowserGetCookieByName";                        // name
    public static final String BROWSER_GET_COOKIES_METHOD = "BrowserGetCookies";                                    //
    public static final String BROWSER_ADD_COOKIE_METHOD = "BrowserAddCookie";                                      //
    public static final String BROWSER_CLEAN_COOKIES_METHOD = "BrowserCleanCookies";                                //
    public static final String BROWSER_DELETE_COOKIE_METHOD = "BrowserDeleteCookie";                                //
    public static final String BROWSER_DELETE_COOKIE_BY_NAME_METHOD = "BrowserDeleteCookieByName";                  // name

    public static final String BROWSER_GET_PAGE_SCREENSHOT = "BrowserGetPageScreenshot";                            //
    public static final String BROWSER_GET_INNER_WINDOW_SIZE_METHOD = "BrowserGetInnerWindowSize";                  //
    public static final String BROWSER_GET_OUTER_WINDOW_SIZE_METHOD = "BrowserGetOuterWindowSize";                  //
    public static final String BROWSER_SET_OUTER_WINDOW_SIZE_METHOD = "BrowserSetOuterWindowSize";                  // windowSize.toString()
    public static final String BROWSER_GET_ABSOLUTE_WINDOW_LOCATION_METHOD = "BrowserGetAbsoluteWindowLocation";    //
    public static final String BROWSER_SET_ABSOLUTE_WINDOW_LOCATION_METHOD = "BrowserSetAbsoluteWindowLocation";    // location.toString()
    public static final String BROWSER_SET_WINDOW_FULLSCREEN_METHOD = "BrowserSetWindowFullscreen";                 //
    public static final String BROWSER_SET_WINDOW_MAXIMIZED_METHOD = "BrowserSetWindowMaximized";                   //

    public static final String BROWSER_GET_ACTIVE_TAB_PAGE_SOURCE_METHOD = "BrowserGetActiveTabPageSource";
    public static final String BROWSER_GET_ACTIVE_TAB_SCREENSHOT_METHOD = "BrowserGetActiveTabScreenshot";

    public static final String OPEN_EMPTY_TAB_METHOD = "OpenEmptyTab";                                              //
    public static final String OPEN_TAB_WITH_URL_METHOD = "OpenTabWithUrl";                                         // absoluteUrl
    public static final String BROWSER_REFRESH_METHOD = "BrowserRefresh";                                           //
    public static final String BROWSER_BACK_METHOD = "BrowserBack";                                                 //
    public static final String BROWSER_OPEN_URL_METHOD = "BrowserOpenUrl";                                          // relativeOrAbsoluteUrl
    public static final String BROWSER_CLOSE_ACTIVE_TAB_METHOD = "BrowserCloseActiveTab";                           //
    public static final String BROWSER_CLOSE_TAB_WITH_TITLE_METHOD = "BrowserCloseTabWithTitle";                    // tabTitle.getShortDescription()
    public static final String BROWSER_CLOSE_TAB_WITH_URL_METHOD = "BrowserCloseTabWithUrl";                        // tabUrl.getShortDescription()
    public static final String BROWSER_SWITCH_TO_TAB_WITH_TITLE_METHOD = "BrowserSwitchToTabWithTitleMethod";       // tabTitle.getShortDescription()
    public static final String BROWSER_SWITCH_TO_TAB_WITH_URL_METHOD = "BrowserSwitchToTabWithUrlMethod";           // tabUrl.getShortDescription()

    public static final String ACTIVE_TAB_SHOULD_HAVE_TITLE_METHOD = "BrowserActiveTabShouldHaveTitle";                             // expectedText
    public static final String ACTIVE_TAB_SHOULD_NOT_HAVE_TITLE_METHOD = "BrowserActiveTabShouldNotHaveTitle";                      // expectedText
    public static final String ACTIVE_TAB_SHOULD_HAVE_TITLE_VALUE_METHOD = "BrowserActiveTabShouldHaveTitleValue";                  // expectedStringValue.getShortDescription()
    public static final String ACTIVE_TAB_SHOULD_NOT_HAVE_TITLE_VALUE_METHOD = "BrowserActiveTabShouldNotHaveTitleValue";           // expectedStringValue.getShortDescription()

    public static final String ACTIVE_TAB_SHOULD_HAVE_URL_METHOD = "BrowserActiveTabShouldHaveUrl";                                 // expectedText
    public static final String ACTIVE_TAB_SHOULD_NOT_HAVE_URL_METHOD = "BrowserActiveTabShouldNotHaveUrl";                          // expectedText
    public static final String ACTIVE_TAB_SHOULD_HAVE_URL_VALUE_METHOD = "BrowserActiveTabShouldHaveUrlValue";                      // expectedTextValue.getShortDescription()
    public static final String ACTIVE_TAB_SHOULD_NOT_HAVE_URL_VALUE_METHOD = "BrowserActiveTabShouldNotHaveUrlValue";               // expectedTextValue.getShortDescription()

    public static final String BROWSER_SHOULD_HAVE_TAB_WITH_TITLE_METHOD = "BrowserShouldHaveTabWithTitle";                         // expectedText
    public static final String BROWSER_SHOULD_NOT_HAVE_TAB_WITH_TITLE_METHOD = "BrowserShouldNotHaveTabWithTitle";                  // expectedText
    public static final String BROWSER_SHOULD_HAVE_TAB_WITH_TITLE_VALUE_METHOD = "BrowserShouldHaveTabWithTitleValue";              // expectedTextValue.getShortDescription()
    public static final String BROWSER_SHOULD_NOT_HAVE_TAB_WITH_TITLE_VALUE_METHOD = "BrowserShouldNotHaveTabWithTitleValue";       // expectedTextValue.getShortDescription()

    public static final String BROWSER_SHOULD_HAVE_TAB_WITH_URL_METHOD = "BrowserShouldHaveTabWithUrl";                             // expectedText
    public static final String BROWSER_SHOULD_NOT_HAVE_TAB_WITH_URL_METHOD = "BrowserShouldNotHaveTabWithUrl";                      // expectedText
    public static final String BROWSER_SHOULD_HAVE_TAB_WITH_URL_VALUE_METHOD = "BrowserShouldHaveTabWithUrlValue";                  // expectedTextValue.getShortDescription()
    public static final String BROWSER_SHOULD_NOT_HAVE_TAB_WITH_URL_VALUE_METHOD = "BrowserShouldNotHaveTabWithUrlValue";           // expectedTextValue.getShortDescription()

    public static final String BROWSER_SHOULD_HAVE_TABS_COUNT_NUMBER_METHOD = "BrowserShouldHaveTabsCountNumber";                   // String.valueOf(expectedNumber)
    public static final String BROWSER_SHOULD_NOT_HAVE_TABS_COUNT_NUMBER_METHOD = "BrowserShouldNotHaveTabsCountNumber";            // String.valueOf(expectedNumber)
    public static final String BROWSER_SHOULD_HAVE_TABS_COUNT_NUMBER_VALUE_METHOD = "BrowserShouldHaveTabsCountNumberValue";        // expectedNumberValue.getShortDescription()
    public static final String BROWSER_SHOULD_NOT_HAVE_TABS_COUNT_NUMBER_VALUE_METHOD = "BrowserShouldNotHaveTabsCountNumberValue"; // expectedNumberValue.getShortDescription()

}
