# language:ru

Функционал: Функционал проверки WebRadioButton

  @WebElement @WebRadioButton
  Сценарий: Положительный сценарий WebRadioButton
    * пользователь запускает браузер "${[config] browser}"
    * пользователь устанавливает размер окна браузера "${[config] browser_width}" на "${[config] browser_height}"
    * пользователь вводит URL "${[config] start_url}" в браузер и нажимает 'Enter'

    * открывается страница "Домашняя страница"
    * пользователь выбирает в списке "Левое меню" блоки, где
      | "ссылка Пункт меню" содержит "Elements" |
    * пользователь нажимает на "ссылка Пункт меню"
    * пользователь продолжает работать со страницей

    * открывается страница "Страница элементов"
    * элемент "текстовый блок Радио группы" присутствует
    * элемент "текстовый блок Радио группы" отображается
    * элемент "текстовый блок Радио группы" содержит "Label 2"

    * элемент "Первая радио-кнопка" присутствует
    * элемент "Первая радио-кнопка" отображается
    * элемент "Первая радио-кнопка" доступен
    * элемент "Первая радио-кнопка" не выделен
    * элемент "Первая радио-кнопка" находится не в фокусе
    * пользователь прокручивает страницу до "Первая радио-кнопка"

    * элемент "Первая радио-кнопка" имеет размеры "176.3 x 24.0"
    * элемент "Первая радио-кнопка" имеет размеры "176.3 x 24.0 (0.2)"
    * элемент "Первая радио-кнопка" не имеет размеры "276.3 x 24.0"
    * элемент "Первая радио-кнопка" не имеет размеры "276.3 x 24.0 (0.2)"

    * элемент "Первая радио-кнопка" находится в "(448.0, 725.375)"
    * элемент "Первая радио-кнопка" находится в "(448.0, 725.375) (0.2)"
    * элемент "Первая радио-кнопка" не находится в "(1448.0, 725.375)"
    * элемент "Первая радио-кнопка" не находится в "(1448.0, 725.375) (0.2)"

    * компонент "LABEL" элемента "Первая радио-кнопка" имеет цвет "rgb(33, 37, 41)" css-свойства "color"
    * компонент "LABEL" элемента "Первая радио-кнопка" имеет цвет "rgba(33, 37, 41, 1.0)" css-свойства "color"
    * компонент "LABEL" элемента "Первая радио-кнопка" не имеет цвет "rgb(133, 37, 41)" css-свойства "color"
    * компонент "LABEL" элемента "Первая радио-кнопка" не имеет цвет "rgba(133, 37, 41, 1.0)" css-свойства "color"

    * пользователь наводит курсор на "Первая радио-кнопка"
    * лейбл элемента "Первая радио-кнопка" содержит "Label 1"
    * лейбл элемента "Первая радио-кнопка" не содержит "[подстрока]Label 2"
    * свойство "name" элемента "Первая радио-кнопка" содержит "RadioButton 1"

    * пользователь нажимает на "Первая радио-кнопка"
    * элемент "Первая радио-кнопка" находится в фокусе
    * элемент "Первая радио-кнопка" выделен
    * элемент "текстовый блок Радио группы" содержит "Label 1"

    * пользователь закрывает браузер
