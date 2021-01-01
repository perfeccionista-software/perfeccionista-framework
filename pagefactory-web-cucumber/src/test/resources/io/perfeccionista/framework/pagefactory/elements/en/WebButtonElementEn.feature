# language:en

Feature: WebButton element feature

  @WebElement @WebButton
  Scenario: WebButton positive scenario
    * user launch browser "${[config] browser}"
    * user enters the URL "${[config] start_url}" in the browser and clicks 'Enter'

    * page "Home page" opens
    * user chooses in the list "Left menu" blocks with
      | "link Menu item" contains "Elements" |

    * user clicks on the "link Menu item"
    * user continues working with the page

    * page "Elements page" opens
    * element "Simple button" is present
    * element "Simple button" is displayed
    * element "Simple button" is not in focus
    * user scrolls the page to the "Simple button"

    * element "Simple button" has dimensions "127.6 x 38.0"
    * element "Simple button" has dimensions "127.6 x 38.0 (0.2)"
    * element "Simple button" does not have dimensions "227.6 x 38.0"
    * element "Simple button" does not have dimensions "227.6 x 38.0 (0.2)"

    * element "Simple button" has location "(472.3, 333.4)"
    * element "Simple button" has location "(472.3, 333.4) (0.2)"
    * element "Simple button" does not have location "(572.3, 333.4)"
    * element "Simple button" does not have location "(572.3, 333.4) (0.2)"

    * element "Simple button" has color "rgb(0, 123, 255)" of css-property "background-color"
    * element "Simple button" has color "rgba(0, 123, 255, 1.0)" of css-property "background-color"
    * element "Simple button" does not have color "rgb(50, 123, 255)" of css-property "background-color"
    * element "Simple button" does not have color "rgba(50, 123, 255, 1.0)" of css-property "background-color"

    * user hovers the cursor over the "Simple button"
    * element "Simple button" has color "rgba(0, 105, 217, 1.0)" of css-property "background-color"
    * element "Simple button" contains "Simple Button"
    * element "Simple button" contains "[подстрока]Button"
    * element "Simple button" does not contain "[подстрока]link"

    * element "Simple button text" is not present
    * element "Simple button text" is not displayed

    * user clicks on the "Simple button"
    * element "Simple button" is in focus

    * element "Simple button text" is present
    * element "Simple button text" is displayed
    * element "Simple button text" is not in focus
    * element "Simple button text" contains "Simple Button clicked"
    * element "Simple button text" contains "[case-insensitive text]siMple butTon CLIcked"
    * element "Simple button text" contains "[substring]utton cli"
    * element "Simple button text" contains "[case-insensitive substring]Mple butTon CLIck"
    * element "Simple button text" contains "[starts with]Simple"
    * element "Simple button text" contains "[ends with]clicked"
    * element "Simple button text" contains "[RegExp]^Simple\s?\w+\s{1}\w{4}ked$"

    * user close the browser

  @WebElement @WebButton
  Scenario: WebButton with spinner positive scenario
    * user launch browser "${[config] browser}"
    * user enters the URL "${[config] start_url}" in the browser and clicks 'Enter'

    * page "Home page" opens
    * user chooses in the list "Left menu" blocks with
      | "link Menu item" contains "Elements" |

    * user clicks on the "link Menu item"
    * user continues working with the page

    * page "Elements page" opens
    * element "Button with spinner" is present
    * element "Button with spinner" is displayed
    * element "Spinner" is present
    * element "Spinner" is not displayed
    * element "Button with spinner text" is present
    * element "Button with spinner text" is not displayed
    * user clicks on the "Button with spinner"
    * element "Spinner" is displayed
    * element "Button with spinner text" is displayed
    * element "Button with spinner text" contains "Spinner Button clicked"
