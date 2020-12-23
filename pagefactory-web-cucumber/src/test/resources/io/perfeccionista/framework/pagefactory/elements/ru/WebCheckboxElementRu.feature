# language:ru

@UseEnvironmentConfiguration(io.perfeccionista.framework.pagefactory.configurations.TestEnvironmentConfiguration)
Функционал: Функционал проверки WebCheckbox

  @Disabled
  @WebElement @WebCheckbox
  Сценарий: Положительный сценарий WebCheckbox
    * пользователь запускает браузер "${[props] browser}"
    * пользователь вводит URL "${[props] start_url}" в браузер и нажимает 'Enter'

    * открывается страница "Домашняя страница"
    * пользователь выбирает в списке "Левое меню" блоки, где
      | "ссылка Пункт меню" содержит "Elements" |
    * пользователь нажимает на "ссылка Пункт меню"
    * пользователь продолжает работать со страницей

    * открывается страница "Страница элементов"
    * элемент "текстовый блок Чекбокса" присутствует
    * элемент "текстовый блок Чекбокса" отображается
    * элемент "текстовый блок Чекбокса" содержит "[пусто]"

    * элемент "Первый чекбокс" присутствует
    * элемент "Первый чекбокс" отображается
    * элемент "Первый чекбокс" доступен
    * элемент "Первый чекбокс" не выделен
    * элемент "Первый чекбокс" находится не в фокусе
    * пользователь прокручивает страницу до "Первый чекбокс"

    * элемент "Первый чекбокс" имеет размеры "176.3 x 24.0"
    * элемент "Первый чекбокс" имеет размеры "176.3 x 24.0 (0.2)"
    * элемент "Первый чекбокс" не имеет размеры "276.3 x 24.0"
    * элемент "Первый чекбокс" не имеет размеры "276.3 x 24.0 (0.2)"

    * элемент "Первый чекбокс" находится в "(448.0, 685.375)"
    * элемент "Первый чекбокс" находится в "(448.0, 685.375) (0.2)"
    * элемент "Первый чекбокс" не находится в "(548.0, 685.375)"
    * элемент "Первый чекбокс" не находится в "(548.0, 685.375) (0.2)"

    * компонент "LABEL" элемента "Первый чекбокс" имеет цвет "rgb(33, 37, 41)" css-свойства "color"
    * компонент "LABEL" элемента "Первый чекбокс" имеет цвет "rgba(33, 37, 41, 1.0)" css-свойства "color"
    * компонент "LABEL" элемента "Первый чекбокс" не имеет цвет "rgb(133, 37, 41)" css-свойства "color"
    * компонент "LABEL" элемента "Первый чекбокс" не имеет цвет "rgba(133, 37, 41, 1.0)" css-свойства "color"

    * пользователь наводит курсор на "Первый чекбокс"
    * лейбл элемента "Первый чекбокс" содержит "Label 1"
    * лейбл элемента "Первый чекбокс" не содержит "[подстрока]Label 2"
    * свойство "name" элемента "Первый чекбокс" содержит "Checkbox 1"
    * пользователь нажимает на "Первый чекбокс"
    * элемент "Первый чекбокс" находится в фокусе
    * элемент "Первый чекбокс" выделен

    * элемент "текстовый блок Чекбокса" содержит "Label 1"

    * элемент "Третий чекбокс" присутствует
    * элемент "Третий чекбокс" отображается
    * элемент "Третий чекбокс" недоступен
    * элемент "Третий чекбокс" не выделен
    * элемент "Третий чекбокс" находится не в фокусе

    * пользователь нажимает на "Третий чекбокс"

    * элемент "текстовый блок Чекбокса" содержит "Label 1"

    * элемент "Второй чекбокс" присутствует
    * элемент "Второй чекбокс" отображается
    * элемент "Второй чекбокс" не выделен
    * элемент "Второй чекбокс" находится не в фокусе

    * пользователь нажимает на "Второй чекбокс"
    * элемент "Второй чекбокс" выделен
    * элемент "Второй чекбокс" находится в фокусе

    * элемент "текстовый блок Чекбокса" содержит "[подстрока]Label 1"
    * элемент "текстовый блок Чекбокса" содержит "[подстрока]Label 2"

    * пользователь закрывает браузер

