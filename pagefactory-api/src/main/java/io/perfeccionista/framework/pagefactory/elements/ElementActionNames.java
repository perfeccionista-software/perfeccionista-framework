package io.perfeccionista.framework.pagefactory.elements;

// TODO: Переименовать _METHOD в _ACTION
public class ElementActionNames {

    private ElementActionNames() {
    }

    public static final String EXECUTE_ACTION = "ExecuteAction";
    public static final String EXECUTE_INTERACTION = "ExecuteInteraction";

    public static final String IS_PRESENT_METHOD = "IsPresent";
    public static final String SHOULD_BE_PRESENT_METHOD = "ShouldBePresent";
    public static final String SHOULD_NOT_BE_PRESENT_METHOD = "ShouldNotBePresent";

    public static final String IS_DISPLAYED_METHOD = "IsDisplayed";
    public static final String SHOULD_BE_DISPLAYED_METHOD = "ShouldBeDisplayed";
    public static final String SHOULD_NOT_BE_DISPLAYED_METHOD = "ShouldNotBeDisplayed";

    public static final String IS_IN_FOCUS_METHOD = "IsInFocus";
    public static final String SHOULD_BE_IN_FOCUS_METHOD = "ShouldBeInFocus";
    public static final String SHOULD_NOT_BE_IN_FOCUS_METHOD = "ShouldNotBeInFocus";

    public static final String IS_ON_THE_SCREEN_METHOD = "IsOnTheScreen";
    public static final String SHOULD_BE_ON_THE_SCREEN_METHOD = "ShouldBeOnTheScreen";
    public static final String SHOULD_NOT_BE_ON_THE_SCREEN_METHOD = "ShouldNotBeOnTheScreen";

    public static final String GET_ELEMENT_BOUNDS_METHOD = "GetElementBounds";

    public static final String GET_DIMENSIONS_METHOD = "GetDimensions";
    public static final String SHOULD_HAVE_DIMENSIONS_METHOD = "ShouldHaveDimensions";
    public static final String SHOULD_NOT_HAVE_DIMENSIONS_METHOD = "ShouldNotHaveDimensions";

    public static final String GET_LOCATION_METHOD = "GetLocation";
    public static final String SHOULD_HAVE_SCREEN_LOCATION_METHOD = "ShouldHaveScreenLocation";
    public static final String SHOULD_NOT_HAVE_SCREEN_LOCATION_METHOD = "ShouldNotHaveScreenLocation";
    public static final String SHOULD_HAVE_CENTER_LOCATION_METHOD = "ShouldHaveCenterLocation";
    public static final String SHOULD_NOT_HAVE_CENTER_LOCATION_METHOD = "ShouldNotHaveCenterLocation";

    public static final String GET_SCREENSHOT_METHOD = "GetScreenshot";
    public static final String SHOULD_LOOKS_LIKE_METHOD = "ShouldLooksLike";
    public static final String SHOULD_NOT_LOOKS_LIKE_METHOD = "ShouldNotLooksLike";

    public static final String IS_SELECTED_METHOD = "IsSelected";
    public static final String SHOULD_BE_SELECTED_METHOD = "ShouldBeSelected";
    public static final String SHOULD_NOT_BE_SELECTED_METHOD = "ShouldNotBeSelected";

    public static final String IS_ENABLED_METHOD = "IsEnabled";
    public static final String SHOULD_BE_ENABLED_METHOD = "ShouldBeEnabled";
    public static final String SHOULD_BE_DISABLED_METHOD = "ShouldBeDisabled";

    public static final String IS_COMPONENT_PRESENT_METHOD = "IsComponentPresent";
    public static final String COMPONENT_SHOULD_BE_PRESENT_METHOD = "ComponentShouldBePresentMethod";
    public static final String COMPONENT_SHOULD_NOT_BE_PRESENT_METHOD = "ComponentShouldNotBePresentMethod";
    public static final String IS_COMPONENT_DISPLAYED_METHOD = "IsComponentDisplayed";
    public static final String COMPONENT_SHOULD_BE_DISPLAYED_METHOD = "ComponentShouldBeDisplayedMethod";
    public static final String COMPONENT_SHOULD_NOT_BE_DISPLAYED_METHOD = "ComponentShouldNotBeDisplayedMethod";

    public static final String GET_PROPERTY_VALUE_METHOD = "GetPropertyValue";
    public static final String SHOULD_HAVE_PROPERTY_TEXT_METHOD = "ShouldHavePropertyText";
    public static final String SHOULD_HAVE_PROPERTY_VALUE_METHOD = "ShouldHavePropertyValue";
    public static final String SHOULD_HAVE_PROPERTY_NUMBER_METHOD = "ShouldHavePropertyNumber";
    public static final String SHOULD_NOT_HAVE_PROPERTY_TEXT_METHOD = "ShouldNotHavePropertyText";
    public static final String SHOULD_NOT_HAVE_PROPERTY_VALUE_METHOD = "ShouldNotHavePropertyValue";
    public static final String SHOULD_NOT_HAVE_PROPERTY_NUMBER_METHOD = "ShouldNotHavePropertyNumber";

    public static final String HAS_STATE_METHOD = "HasState";
    public static final String SHOULD_HAVE_STATE_METHOD = "ShouldHaveState";
    public static final String SHOULD_NOT_HAVE_STATE_METHOD = "ShouldNotHaveState";

    public static final String GET_COLOR_METHOD = "GetColor";
    public static final String SHOULD_HAVE_COLOR_METHOD = "ShouldHaveColor";
    public static final String SHOULD_NOT_HAVE_COLOR_METHOD = "ShouldNotHaveColor";

    public static final String GET_TEXT_METHOD = "GetText";
    public static final String SHOULD_HAVE_TEXT_METHOD = "ShouldHaveText";
    public static final String SHOULD_HAVE_VALUE_METHOD = "ShouldHaveValue";
    public static final String SHOULD_HAVE_NUMBER_METHOD = "ShouldHaveNumber";
    public static final String SHOULD_NOT_HAVE_TEXT_METHOD = "ShouldNotHaveText";
    public static final String SHOULD_NOT_HAVE_VALUE_METHOD = "ShouldNotHaveValue";
    public static final String SHOULD_NOT_HAVE_NUMBER_METHOD = "ShouldNotHaveNumber";

    public static final String GET_LABEL_METHOD = "GetLabel";
    public static final String SHOULD_HAVE_TEXT_LABEL_METHOD = "ShouldHaveTextLabel";
    public static final String SHOULD_HAVE_VALUE_LABEL_METHOD = "ShouldHaveValueLabel";
    public static final String SHOULD_HAVE_NUMBER_LABEL_METHOD = "ShouldHaveNumberLabel";
    public static final String SHOULD_NOT_HAVE_TEXT_LABEL_METHOD = "ShouldNotHaveTextLabel";
    public static final String SHOULD_NOT_HAVE_VALUE_LABEL_METHOD = "ShouldNotHaveValueLabel";
    public static final String SHOULD_NOT_HAVE_NUMBER_LABEL_METHOD = "ShouldNotHaveNumberLabel";

    public static final String GET_SIZE_ELEMENTS_METHOD = "GetSize";
    public static final String GET_INDEX_METHOD = "GetIndex";
    public static final String GET_EXTRACTED_VALUE_METHOD = "GetValue";
    public static final String GET_EXTRACTED_VALUES_METHOD = "GetValues";
    public static final String SHOULD_HAVE_SIZE_METHOD = "ShouldHaveSize";
    public static final String SHOULD_HAVE_EXPECTED_RESULT_METHOD = "ShouldHaveExpectedResult";
    public static final String SHOULD_HAVE_NOT_NULL_RESULT_METHOD = "ShouldHaveNotNullResults";
    public static final String SHOULD_HAVE_NULL_RESULT_METHOD = "ShouldHaveNullResults";
    public static final String SHOULD_NOT_HAVE_SIZE_METHOD = "ShouldNotHaveSize";
    public static final String SHOULD_HAVE_SIZE_VALUE_METHOD = "ShouldHaveSizeValue";
    public static final String SHOULD_NOT_HAVE_SIZE_VALUE_METHOD = "ShouldNotHaveSizeValue";
    public static final String SHOULD_HAVE_INDEX_METHOD = "ShouldHaveIndex";
    public static final String SHOULD_NOT_HAVE_INDEX_METHOD = "ShouldNotHaveIndex";
    public static final String SHOULD_HAVE_INDEX_VALUE_METHOD = "ShouldHaveIndexValue";
    public static final String SHOULD_NOT_HAVE_INDEX_VALUE_METHOD = "ShouldNotHaveIndexValue";

    public static final String CLEAR_METHOD = "Clear";

    public static final String CLICK_METHOD = "Click";
    public static final String SINGLE_TAP_METHOD = "SingleTap";
    public static final String LONG_TAP_METHOD = "LongTap";
    public static final String DOUBLE_TAP_METHOD = "DoubleTap";

    public static final String IS_IMAGE_METHOD = "IsImage";
    public static final String SAVE_IMAGE_TO_FILE_METHOD = "SaveImageToFile";

    public static final String HOVER_TO_METHOD = "HoverTo";


    public static final String TYPE_TEXT_METHOD = "TypeText";
    public static final String REPLACE_TEXT_METHOD = "ReplaceText";
    public static final String SEND_KEY_EVENTS_METHOD = "SendKeyEvents";

    public static final String SET_FILENAME_METHOD = "SetFileName";
    public static final String SHOULD_FILE_EXIST_METHOD = "ShouldFileExist";

    public static final String GET_STRING_ATTRIBUTE_VALUE_METHOD = "GetStringAttributeValueMethod";

    public static final String SCROLL_TO_METHOD = "ScrollTo";
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

    public static final String CLOSE_METHOD = "Close";

    public static final String OPEN_METHOD = "Open";

    public static final String IS_OPEN_METHOD = "IsOpen";
    public static final String SHOULD_BE_CLOSED_METHOD = "ShouldBeClosed";
    public static final String SHOULD_BE_OPEN_METHOD = "ShouldBeOpen";

    public static final String SHOULD_BE_SORTED_METHOD = "ShouldBeSorted";

    public static final String GET_SELECTED_METHOD = "GetSelected";
    public static final String GET_BY_LABEL_METHOD = "GetByLabel";
    public static final String GET_BY_INDEX_METHOD = "GetByIndex";

    public static final String ADD_LOG_ENTRY_METHOD = "AddLogEntry";

    public static final String DRAG_AND_DROP_METHOD = "Drag and Drop";

    public static final String CHECK_BOOLEAN_ATTRIBUTE_VALUE_METHOD = "CheckBooleanAttributeValue";
    public static final String CHECK_STRING_ATTRIBUTE_VALUE_METHOD = "CheckStringAttributeValue";
    public static final String CHECK_IS_DISPLAYED_METHOD = "CheckIsDisplayed";



    public static final String COLUMN_SHOULD_BE_DISPLAYED_METHOD = "ColumnShouldBeDisplayed";
    public static final String COLUMN_SHOULD_NOT_BE_DISPLAYED_METHOD = "ColumnShouldNotBeDisplayed";



}
