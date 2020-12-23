# language:en

@UseEnvironmentConfiguration(io.perfeccionista.framework.pagefactory.configurations.TestEnvironmentConfiguration)
Feature: WebCheckbox element feature

  @Disabled
  @WebCheckbox @WebElement
  Scenario: WebCheckbox positive scenario
    * user launch browser "${[props] browser}"
    * user enters the URL "${[props] start_url}" in the browser and clicks 'Enter'

    * page "Home page" opens
    * user chooses in the list "Left menu" blocks with
      | "link Menu item" contains "Elements" |

    * user clicks on the "link Menu item"
    * user continues working with the page

    * page "Elements page" opens
    * element "Checkbox text" is present
    * element "Checkbox text" is displayed
    * element "Checkbox text" contains "[empty]"

    * element "Checkbox one" is present
    * element "Checkbox one" is displayed
    * element "Checkbox one" is enabled
    * element "Checkbox one" is not selected
    * element "Checkbox one" is not in focus
    * user scrolls the page to the "Checkbox one"

    * element "Checkbox one" has dimensions "176.3 x 24.0"
    * element "Checkbox one" has dimensions "176.3 x 24.0 (0.2)"
    * element "Checkbox one" does not have dimensions "276.3 x 24.0"
    * element "Checkbox one" does not have dimensions "276.3 x 24.0 (0.2)"

    * element "Checkbox one" has location "(448.0, 685.375)"
    * element "Checkbox one" has location "(448.0, 685.375) (0.2)"
    * element "Checkbox one" does not have location "(548.0, 685.375)"
    * element "Checkbox one" does not have location "(548.0, 685.375) (0.2)"

    * component "LABEL" of the element "Checkbox one" has color "rgb(33, 37, 41)" of css-property "color"
    * component "LABEL" of the element "Checkbox one" has color "rgb(33, 37, 41, 1.0)" of css-property "color"
    * component "LABEL" of the element "Checkbox one" does not have color "rgb(133, 37, 41)" of css-property "color"
    * component "LABEL" of the element "Checkbox one" does not have color "rgba(133, 37, 41, 1.0)" of css-property "color"

    * user hovers the cursor over the "Checkbox one"
    * label of the element "Checkbox one" contains "Label 1"
    * label of the element "Checkbox one" does not contain "[substring]Label 2"
    * property "name" of the element "Checkbox one" contains "Checkbox 1"
    * user clicks on the "Checkbox one"
    * element "Checkbox one" is in focus
    * element "Checkbox one" is selected

    * element "Checkbox text" contains "Label 1"

    * element "Checkbox three" is present
    * element "Checkbox three" is displayed
    * element "Checkbox three" is disabled
    * element "Checkbox three" is not selected
    * element "Checkbox three" is not in focus

    * user clicks on the "Checkbox three"

    * element "Checkbox text" contains "Label 1"

    * element "Checkbox two" is present
    * element "Checkbox two" is displayed
    * element "Checkbox two" is not selected
    * element "Checkbox two" is not in focus

    * user clicks on the "Checkbox two"
    * element "Checkbox two" is selected
    * element "Checkbox two" is in focus

    * element "Checkbox text" contains "[substring]Label 1"
    * element "Checkbox text" contains "[substring]Label 2"

    * user close the browser
