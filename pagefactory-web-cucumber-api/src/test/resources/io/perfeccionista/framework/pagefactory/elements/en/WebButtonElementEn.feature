# language:en

@UseEnvironmentConfiguration(io.perfeccionista.framework.pagefactory.configurations.TestEnvironmentConfiguration)
Feature: WebButton element feature

  @Disabled
  @WebButton @WebElement
  Scenario: WebButton positive scenario
    * user launch browser "${[props] browser}"
    * user enters the URL "${[props] start_url}" in the browser and clicks 'Enter'

    * page "Home page" opens
    * user chooses for work in the "Left menu" blocks with
      | "link Menu item" contain "Elements" |

    * user clicks on the "link Menu item"
    * user continues working with the page

    * page "Elements page" opens
    * "Simple button" is present
    * "Simple button" is displayed
    * "Simple button" is not in focus
    * user scrolls the page to the "Simple button"

    * "Simple button" has dimensions "127.6 x 38.0"
    * "Simple button" has dimensions "127.6 x 38.0 (0.2)"
    * "Simple button" does not have dimensions "227.6 x 38.0"
    * "Simple button" does not have dimensions "227.6 x 38.0 (0.2)"

    * "Simple button" has location "(472.3, 333.4)"
    * "Simple button" has location "(472.3, 333.4) (0.2)"
    * "Simple button" does not have location "(572.3, 333.4)"
    * "Simple button" does not have location "(572.3, 333.4) (0.2)"

    * "Simple button" has color "rgb(0, 123, 255)" of css-property "background-color"
    * "Simple button" has color "rgba(0, 123, 255, 1.0)" of css-property "background-color"
    * "Simple button" does not have color "rgb(50, 123, 255)" of css-property "background-color"
    * "Simple button" does not have color "rgba(50, 123, 255, 1.0)" of css-property "background-color"

    * user hovers the cursor over the "Simple button"
    * "Simple button" has color "rgba(0, 105, 217, 1.0)" of css-property "background-color"
    * "Simple button" contains "Simple Button"
    * "Simple button" contains "[подстрока]Button"
    * "Simple button" does not contain "[подстрока]link"

    * "Simple button text" is not present
    * "Simple button text" is not displayed

    * user clicks on the "Simple button"
    * "Simple button" is in focus

    * "Simple button text" is present
    * "Simple button text" is displayed
    * "Simple button text" is not in focus
    * "Simple button text" contains "Simple Button clicked"
    * "Simple button text" contains "[case-insensitive text]siMple butTon CLIcked"
    * "Simple button text" contains "[substring]utton cli"
    * "Simple button text" contains "[case-insensitive substring]Mple butTon CLIck"
    * "Simple button text" contains "[starts with]Simple"
    * "Simple button text" contains "[ends with]clicked"
    * "Simple button text" contains "[RegExp]^Simple\s?\w+\s{1}\w{4}ked$"

    * user close the browser

  @Disabled
  @WebButton @WebElement
  Scenario: WebButton with spinner positive scenario
    * user launch browser "${[props] browser}"
    * user enters the URL "${[props] start_url}" in the browser and clicks 'Enter'

    * page "Home page" opens
    * user chooses for work in the "Left menu" blocks with
      | "link Menu item" contain "Elements" |

    * user clicks on the "link Menu item"
    * user continues working with the page

    * page "Elements page" opens
    * "Button with spinner" is present
    * "Button with spinner" is displayed
    * "Spinner" is present
    * "Spinner" is not displayed
    * "Button with spinner text" is present
    * "Button with spinner text" is not displayed
    * user clicks on the "Button with spinner"
    * "Spinner" is displayed
    * "Button with spinner text" is displayed
    * "Button with spinner text" contains "Spinner Button clicked"