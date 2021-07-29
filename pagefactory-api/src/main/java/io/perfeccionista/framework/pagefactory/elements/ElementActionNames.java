package io.perfeccionista.framework.pagefactory.elements;

// TODO: Переименовать _METHOD в _ACTION
public class ElementActionNames {

    private ElementActionNames() {
    }

    public static final String EXECUTE_ACTION = "ExecuteAction";                                    // elementName, endpointHandlerName, argsAsString)
    public static final String EXECUTE_CUSTOM_OPERATION = "ExecuteCustomOperation";                 // endpointHandlerName

    public static final String IS_PRESENT_METHOD = "IsPresent";                                     // elementName
    public static final String SHOULD_BE_PRESENT_METHOD = "ShouldBePresent";                        // elementName
    public static final String SHOULD_NOT_BE_PRESENT_METHOD = "ShouldNotBePresent";                 // elementName

    public static final String IS_DISPLAYED_METHOD = "IsDisplayed";                                 // elementName
    public static final String SHOULD_BE_DISPLAYED_METHOD = "ShouldBeDisplayed";                    // elementName
    public static final String SHOULD_NOT_BE_DISPLAYED_METHOD = "ShouldNotBeDisplayed";             // elementName
    public static final String CHECK_IS_DISPLAYED_METHOD = "CheckIsDisplayed";

    public static final String IS_IN_FOCUS_METHOD = "IsInFocus";                                    // elementName
    public static final String SHOULD_BE_IN_FOCUS_METHOD = "ShouldBeInFocus";                       // elementName
    public static final String SHOULD_NOT_BE_IN_FOCUS_METHOD = "ShouldNotBeInFocus";                // elementName

    public static final String IS_ON_THE_SCREEN_METHOD = "IsOnTheScreen";                           // elementName
    public static final String SHOULD_BE_ON_THE_SCREEN_METHOD = "ShouldBeOnTheScreen";              // elementName
    public static final String SHOULD_NOT_BE_ON_THE_SCREEN_METHOD = "ShouldNotBeOnTheScreen";       // elementName

    public static final String GET_ELEMENT_BOUNDS_METHOD = "GetElementBounds";                      // elementName, componentName
    public static final String SHOULD_HAVE_DIMENSIONS_METHOD = "ShouldHaveDimensions";              // elementName, componentName, expectedDimensions.toString()
    public static final String SHOULD_NOT_HAVE_DIMENSIONS_METHOD = "ShouldNotHaveDimensions";       // elementName, componentName, expectedDimensions.toString()
    public static final String SHOULD_HAVE_ABSOLUTE_LOCATION_METHOD = "ShouldHaveAbsoluteLocation";        // elementName, componentName, expectedLocation.toString()
    public static final String SHOULD_NOT_HAVE_ABSOLUTE_LOCATION_METHOD = "ShouldNotHaveAbsoluteLocation"; // elementName, componentName, expectedLocation.toString()
    public static final String SHOULD_HAVE_SCREEN_LOCATION_METHOD = "ShouldHaveScreenLocation";            // elementName, componentName, expectedLocation.toString()
    public static final String SHOULD_NOT_HAVE_SCREEN_LOCATION_METHOD = "ShouldNotHaveScreenLocation";     // elementName, componentName, expectedLocation.toString()
    public static final String SHOULD_HAVE_CENTER_LOCATION_METHOD = "ShouldHaveCenterLocation";        // elementName, componentName, expectedLocation.toString()
    public static final String SHOULD_NOT_HAVE_CENTER_LOCATION_METHOD = "ShouldNotHaveCenterLocation"; // elementName, componentName, expectedLocation.toString()

    public static final String GET_SCREENSHOT_METHOD = "GetScreenshot";                             // elementName, componentName
    public static final String SHOULD_LOOKS_LIKE_METHOD = "ShouldLooksLike";                        // elementName, componentName, String.valueOf(expectedScreenshot.getSize())
    public static final String SHOULD_NOT_LOOKS_LIKE_METHOD = "ShouldNotLooksLike";                 // elementName, componentName, String.valueOf(expectedScreenshot.getSize())

    public static final String IS_SELECTED_METHOD = "IsSelected";                                   // elementName
    public static final String SHOULD_BE_SELECTED_METHOD = "ShouldBeSelected";                      // elementName
    public static final String SHOULD_NOT_BE_SELECTED_METHOD = "ShouldNotBeSelected";               // elementName

    public static final String IS_ENABLED_METHOD = "IsEnabled";                                     // elementName
    public static final String SHOULD_BE_ENABLED_METHOD = "ShouldBeEnabled";                        // elementName
    public static final String SHOULD_BE_DISABLED_METHOD = "ShouldBeDisabled";                      // elementName

    public static final String IS_COMPONENT_PRESENT_METHOD = "IsComponentPresent";                                  // elementName, componentName
    public static final String COMPONENT_SHOULD_BE_PRESENT_METHOD = "ComponentShouldBePresentMethod";               // elementName, componentName
    public static final String COMPONENT_SHOULD_NOT_BE_PRESENT_METHOD = "ComponentShouldNotBePresentMethod";        // elementName, componentName
    public static final String IS_COMPONENT_DISPLAYED_METHOD = "IsComponentDisplayed";                              // elementName, componentName
    public static final String COMPONENT_SHOULD_BE_DISPLAYED_METHOD = "ComponentShouldBeDisplayedMethod";           // elementName, componentName
    public static final String COMPONENT_SHOULD_NOT_BE_DISPLAYED_METHOD = "ComponentShouldNotBeDisplayedMethod";    // elementName, componentName

    public static final String GET_PROPERTY_VALUE_METHOD = "GetPropertyValue";                          // elementName, propertyHolder.getName()
    public static final String SHOULD_HAVE_PROPERTY_TEXT_METHOD = "ShouldHavePropertyText";             // elementName, propertyName, expectedText
    public static final String SHOULD_HAVE_PROPERTY_VALUE_METHOD = "ShouldHavePropertyValue";           // elementName, propertyName, expectedStringValue.getShortDescription()
    public static final String SHOULD_HAVE_PROPERTY_NUMBER_METHOD = "ShouldHavePropertyNumber";         // elementName, propertyName, expectedNumberValue.getShortDescription()
    public static final String SHOULD_NOT_HAVE_PROPERTY_TEXT_METHOD = "ShouldNotHavePropertyText";      // elementName, propertyName, expectedText
    public static final String SHOULD_NOT_HAVE_PROPERTY_VALUE_METHOD = "ShouldNotHavePropertyValue";    // elementName, propertyName, expectedStringValue.getShortDescription()
    public static final String SHOULD_NOT_HAVE_PROPERTY_NUMBER_METHOD = "ShouldNotHavePropertyNumber";  // elementName, propertyName, expectedNumberValue.getShortDescription()

    public static final String HAS_STATE_METHOD = "HasState";                                       // elementName, stateHolder.getName()
    public static final String SHOULD_HAVE_STATE_METHOD = "ShouldHaveState";                        // elementName, stateName
    public static final String SHOULD_NOT_HAVE_STATE_METHOD = "ShouldNotHaveState";                 // elementName, stateName

    public static final String GET_COLOR_METHOD = "GetColor";                                       // elementName, componentName, cssProperty
    public static final String SHOULD_HAVE_COLOR_METHOD = "ShouldHaveColor";                        // elementName, componentName, cssProperty, expectedColor.toString()
    public static final String SHOULD_NOT_HAVE_COLOR_METHOD = "ShouldNotHaveColor";                 // elementName, componentName, cssProperty, expectedColor.toString()

    public static final String GET_TEXT_METHOD = "GetText";                                         // elementName
    public static final String SHOULD_HAVE_TEXT_METHOD = "ShouldHaveText";                          // elementName, expectedText
    public static final String SHOULD_HAVE_VALUE_METHOD = "ShouldHaveValue";                        // elementName, expectedStringValue.getShortDescription()
    public static final String SHOULD_HAVE_NUMBER_METHOD = "ShouldHaveNumber";                      // elementName, expectedNumberValue.getShortDescription()
    public static final String SHOULD_NOT_HAVE_TEXT_METHOD = "ShouldNotHaveText";                   // elementName, expectedText
    public static final String SHOULD_NOT_HAVE_VALUE_METHOD = "ShouldNotHaveValue";                 // elementName, expectedStringValue.getShortDescription()
    public static final String SHOULD_NOT_HAVE_NUMBER_METHOD = "ShouldNotHaveNumber";               // elementName, expectedNumberValue.getShortDescription()

    public static final String GET_LABEL_METHOD = "GetLabel";                                       // elementName
    public static final String SHOULD_HAVE_TEXT_LABEL_METHOD = "ShouldHaveTextLabel";               // elementName, expectedText
    public static final String SHOULD_HAVE_VALUE_LABEL_METHOD = "ShouldHaveValueLabel";             // elementName, expectedStringValue.getShortDescription()
    public static final String SHOULD_HAVE_NUMBER_LABEL_METHOD = "ShouldHaveNumberLabel";           // elementName, expectedNumberValue.getShortDescription()
    public static final String SHOULD_NOT_HAVE_TEXT_LABEL_METHOD = "ShouldNotHaveTextLabel";        // elementName, expectedText
    public static final String SHOULD_NOT_HAVE_VALUE_LABEL_METHOD = "ShouldNotHaveValueLabel";      // elementName, expectedStringValue.getShortDescription()
    public static final String SHOULD_NOT_HAVE_NUMBER_LABEL_METHOD = "ShouldNotHaveNumberLabel";    // elementName, expectedNumberValue.getShortDescription()

    public static final String GET_SIZE_ELEMENTS_METHOD = "GetSize";                                // elementName
    public static final String GET_INDEX_METHOD = "GetIndex";                                       // elementName
    public static final String GET_EXTRACTED_VALUE_METHOD = "GetValue";                             // elementName
    public static final String GET_EXTRACTED_VALUES_METHOD = "GetValues";                           // elementName
    public static final String SHOULD_HAVE_SIZE_METHOD = "ShouldHaveSize";                          // elementName, String.valueOf(expectedSize)
    public static final String SHOULD_NOT_HAVE_SIZE_METHOD = "ShouldNotHaveSize";                   // elementName, String.valueOf(expectedSize)
    public static final String SHOULD_HAVE_SIZE_VALUE_METHOD = "ShouldHaveSizeValue";               // elementName, expectedValue.getShortDescription()
    public static final String SHOULD_NOT_HAVE_SIZE_VALUE_METHOD = "ShouldNotHaveSizeValue";        // elementName, expectedValue.getShortDescription()
    public static final String SHOULD_HAVE_EXPECTED_RESULT_METHOD = "ShouldHaveExpectedResult";     // elementName, String.valueOf(expectedSize)
    public static final String SHOULD_HAVE_NOT_NULL_RESULT_METHOD = "ShouldHaveNotNullResults";     // elementName
    public static final String SHOULD_HAVE_NULL_RESULT_METHOD = "ShouldHaveNullResults";            // elementName
    public static final String SHOULD_HAVE_INDEX_METHOD = "ShouldHaveIndex";                        // elementName, String.valueOf(expectedIndex)
    public static final String SHOULD_NOT_HAVE_INDEX_METHOD = "ShouldNotHaveIndex";                 // elementName, String.valueOf(expectedIndex)
    public static final String SHOULD_HAVE_INDEX_VALUE_METHOD = "ShouldHaveIndexValue";             // elementName, expectedValue.getShortDescription()
    public static final String SHOULD_NOT_HAVE_INDEX_VALUE_METHOD = "ShouldNotHaveIndexValue";      // elementName, expectedValue.getShortDescription()

    public static final String CLEAR_METHOD = "Clear";                                              // elementName

    public static final String CLICK_METHOD = "Click";                                              // elementName
    public static final String SINGLE_TAP_METHOD = "SingleTap";                                     // elementName
    public static final String LONG_TAP_METHOD = "LongTap";                                         // elementName
    public static final String DOUBLE_TAP_METHOD = "DoubleTap";                                     // elementName

    public static final String IS_IMAGE_METHOD = "IsImage";                                         // elementName
    public static final String SAVE_IMAGE_TO_FILE_METHOD = "SaveImageToFile";                       // elementName, filePath

    public static final String HOVER_TO_METHOD = "HoverTo";                                         // elementName

    public static final String TYPE_TEXT_METHOD = "TypeText";                                       // elementName, text
    public static final String REPLACE_TEXT_METHOD = "ReplaceText";                                 // elementName, text
    public static final String SEND_KEY_EVENTS_METHOD = "SendKeyEvents";                            // elementName, keyEvents.toString()

    public static final String SET_FILENAME_METHOD = "SetFileName";
    public static final String SHOULD_FILE_EXIST_METHOD = "ShouldFileExist";

    public static final String UPLOAD_FROM_CLASSPATH_METHOD = "UploadFromClasspath";
    public static final String UPLOAD_FROM_FILE_METHOD = "UploadFromFile";

    public static final String SCROLL_TO_METHOD = "ScrollTo";                                       // elementName
    public static final String SCROLL_UP_METHOD = "ScrollUp";
    public static final String SCROLL_RIGHT_METHOD = "ScrollRight";
    public static final String SCROLL_DOWN_METHOD = "ScrollDown";
    public static final String SCROLL_LEFT_METHOD = "ScrollLeft";

    public static final String SCROLL_TO_ELEMENT_METHOD = "ScrollToElement";
    public static final String LIST_SCROLL_TO_VERTICALLY_METHOD = "ListScrollToVerticallyElement";

    public static final String FILTER_METHOD = "Filter";
    public static final String GET_FILTER_RESULT_METHOD = "GetFilterResult";
    public static final String EXTRACT_ALL_METHOD = "ExtractAll";

    public static final String EXTRACT_HEADER_FILTER = "ExtractHeader";
    public static final String EXTRACT_ALL_ROWS_FILTER = "ExtractAllRows";
    public static final String EXTRACT_FOOTER_FILTER = "ExtractFooter";

    public static final String CLOSE_METHOD = "Close";                                              // elementName

    public static final String OPEN_METHOD = "Open";                                                // elementName

    public static final String IS_OPEN_METHOD = "IsOpen";                                           // elementName
    public static final String SHOULD_BE_CLOSED_METHOD = "ShouldBeClosed";                          // elementName
    public static final String SHOULD_BE_OPEN_METHOD = "ShouldBeOpen";                              // elementName

    public static final String SHOULD_BE_SORTED_METHOD = "ShouldBeSorted";                          // elementName, comparator.getClass().getCanonicalName()

    public static final String GET_SELECTED_METHOD = "GetSelected";
    public static final String GET_BY_LABEL_METHOD = "GetByLabel";
    public static final String GET_BY_INDEX_METHOD = "GetByIndex";

    public static final String ADD_LOG_ENTRY_METHOD = "AddLogEntry";                                // pageName, logLevel.getName(), message

    public static final String DRAG_AND_DROP_METHOD = "DragAndDrop";                                // elementName, target.toString()

    public static final String GET_STRING_ATTRIBUTE_VALUE_METHOD = "GetStringAttributeValueMethod"; // elementName, attributeName
    public static final String CHECK_BOOLEAN_ATTRIBUTE_VALUE_METHOD = "CheckBooleanAttributeValue"; // elementName, attributeName
    public static final String CHECK_STRING_ATTRIBUTE_VALUE_METHOD = "CheckStringAttributeValue";   // elementName, attributeName, expectedValue.getShortDescription()



    public static final String COLUMN_SHOULD_BE_DISPLAYED_METHOD = "ColumnShouldBeDisplayed";        // elementName, columnName
    public static final String COLUMN_SHOULD_NOT_BE_DISPLAYED_METHOD = "ColumnShouldNotBeDisplayed"; // elementName, columnName



}
