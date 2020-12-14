# language:en

@UseEnvironmentConfiguration(io.perfeccionista.framework.pagefactory.configurations.TestEnvironmentConfiguration)
Feature: WebCheckbox element feature

  @Disabled
  @WebCheckbox @WebElement
  Scenario: WebCheckbox positive scenario
    * user launch browser "${[props] browser}"
    * user enters the URL "${[props] start_url}" in the browser and clicks 'Enter'

    * page "Home page" opens
    * user chooses for work in the "Left menu" blocks with
      | "link Menu item" contain "Elements" |

    * user clicks on the "link Menu item"
    * user continues working with the page

    * page "Elements page" opens
    * "Checkbox text" is present
    * "Checkbox text" is displayed
    * "Checkbox text" contains "[empty]"

    * "Checkbox one" is present
    * "Checkbox one" is displayed
    * "Checkbox one" is enabled
    * "Checkbox one" is not selected
    * "Checkbox one" is not in focus
    * user scrolls the page to the "Checkbox one"

    * "Checkbox one" has dimensions "176.3 x 24.0"
    * "Checkbox one" has dimensions "176.3 x 24.0 (0.2)"
    * "Checkbox one" does not have dimensions "276.3 x 24.0"
    * "Checkbox one" does not have dimensions "276.3 x 24.0 (0.2)"

    * "Checkbox one" has location "(448.0, 685.375)"
    * "Checkbox one" has location "(448.0, 685.375) (0.2)"
    * "Checkbox one" does not have location "(548.0, 685.375)"
    * "Checkbox one" does not have location "(548.0, 685.375) (0.2)"

    * component "LABEL" of the "Checkbox one" has color "rgb(33, 37, 41)" of css-property "color"
    * component "LABEL" of the "Checkbox one" has color "rgb(33, 37, 41, 1.0)" of css-property "color"
    * component "LABEL" of the "Checkbox one" does not have color "rgb(133, 37, 41)" of css-property "color"
    * component "LABEL" of the "Checkbox one" does not have color "rgba(133, 37, 41, 1.0)" of css-property "color"

    * user hovers the cursor over the "Checkbox one"
    * label of the "Checkbox one" contains "Label 1"
    * label of the "Checkbox one" does not contain "[substring]Label 2"
    * property "name" of the "Checkbox one" contains "Checkbox 1"
    * user clicks on the "Checkbox one"
    * "Checkbox one" is in focus
    * "Checkbox one" is selected

    * "Checkbox text" contains "Label 1"

    * "Checkbox three" is present
    * "Checkbox three" is displayed
    * "Checkbox three" is disabled
    * "Checkbox three" is not selected
    * "Checkbox three" is not in focus

    * user clicks on the "Checkbox three"

    * "Checkbox text" contains "Label 1"

    * "Checkbox two" is present
    * "Checkbox two" is displayed
    * "Checkbox two" is not selected
    * "Checkbox two" is not in focus

    * user clicks on the "Checkbox two"
    * "Checkbox two" is selected
    * "Checkbox two" is in focus

    * "Checkbox text" contains "[substring]Label 1"
    * "Checkbox text" contains "[substring]Label 2"

    * user close the browser