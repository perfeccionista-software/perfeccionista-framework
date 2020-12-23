# language:ru

@UseEnvironmentConfiguration(io.perfeccionista.framework.pagefactory.configurations.TestEnvironmentConfiguration)
Функционал: Функционал проверки WebTextInput

  @Disabled
  @WebElement @WebTextInput
  Сценарий: Положительный сценарий WebTextInput
    * пользователь запускает браузер "${[props] browser}"
    * пользователь вводит URL "${[props] start_url}" в браузер и нажимает 'Enter'

    * открывается страница "Домашняя страница"
    * пользователь выбирает в списке "Левое меню" блоки, где
      | "ссылка Пункт меню" содержит "Elements" |
    * пользователь нажимает на "ссылка Пункт меню"
    * пользователь продолжает работать со страницей

    * открывается страница "Страница элементов"
    * элемент "кнопка для Простого поля ввода" присутствует
    * элемент "кнопка для Простого поля ввода" отображается
    * компонент "Red" элемента "кнопка для Простого поля ввода" отображается
    * элемент "кнопка для Простого поля ввода" содержит "Disable Input"

    * элемент "текстовый блок Простого поля ввода" присутствует
    * элемент "текстовый блок Простого поля ввода" отображается
    * элемент "текстовый блок Простого поля ввода" содержит ""

    * элемент "Простое поле ввода" присутствует
    * элемент "Простое поле ввода" отображается
    * элемент "Простое поле ввода" находится не в фокусе
    * элемент "Простое поле ввода" доступен
    * пользователь прокручивает страницу до "Простое поле ввода"

    * элемент "Простое поле ввода" имеет размеры "382.5 x 38.0"
    * элемент "Простое поле ввода" имеет размеры "382.5 x 38.0 (0.2)"
    * элемент "Простое поле ввода" не имеет размеры "1382.5 x 38.0"
    * элемент "Простое поле ввода" не имеет размеры "1382.5 x 38.0 (0.2)"

    * элемент "Простое поле ввода" находится в "(757.75, 536.4)"
    * элемент "Простое поле ввода" находится в "(757.75, 536.4) (0.2)"
    * элемент "Простое поле ввода" не находится в "(1757.75, 536.4)"
    * элемент "Простое поле ввода" не находится в "(1757.75, 536.4) (0.2)"

    * элемент "Простое поле ввода" имеет цвет "rgb(206, 212, 218)" css-свойства "border-color"
    * элемент "Простое поле ввода" имеет цвет "rgba(206, 212, 218, 1.0)" css-свойства "border-color"
    * элемент "Простое поле ввода" не имеет цвет "rgb(106, 212, 218)" css-свойства "border-color"
    * элемент "Простое поле ввода" не имеет цвет "rgba(106, 212, 218, 1.0)" css-свойства "border-color"

    * пользователь наводит курсор на "Простое поле ввода"
    * пользователь нажимает на "Простое поле ввода"
    * элемент "Простое поле ввода" имеет цвет "rgb(128, 189, 255)" css-свойства "border-color"
    * элемент "Простое поле ввода" содержит ""
    * элемент "Простое поле ввода" не содержит "input"
    * свойство "placeholder" элемента "Простое поле ввода" содержит "Enter text"
    * свойство "placeholder" элемента "Простое поле ввода" не содержит "[подстрока]Name"
    * пользователь вводит в "Простое поле ввода" "Let's test text input"
    * элемент "Простое поле ввода" содержит "Let's test text input"

    * элемент "текстовый блок Простого поля ввода" отображается
    * элемент "текстовый блок Простого поля ввода" содержит "Let's test text input"
    * пользователь очищает текст в "Простое поле ввода"
    * элемент "Простое поле ввода" содержит ""
    * элемент "текстовый блок Простого поля ввода" содержит ""

    * пользователь нажимает на "кнопка для Простого поля ввода"
    * элемент "кнопка для Простого поля ввода" находится в фокусе
    * компонент "Green" элемента "кнопка для Простого поля ввода" отображается
    * элемент "кнопка для Простого поля ввода" содержит "Enable Input"
    * элемент "Простое поле ввода" недоступен
    * элемент "Простое поле ввода" содержит ""
