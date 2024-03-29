# language:ru

Функционал: Функционал проверки WebRadioGroup

  @WebElement @WebRadioGroup
  Сценарий: Положительный сценарий WebRadioGroup
    * пользователь запускает браузер "${[config] browser}"
    * пользователь устанавливает размер окна браузера "${[config] browser_width}" на "${[config] browser_height}"
    * пользователь вводит URL "${[config] start_url}" в браузер и нажимает 'Enter'

    * открывается страница "Домашняя страница"
    * пользователь выбирает в списке "Левое меню" блоки, где
      | "ссылка Пункт меню" содержит "Elements" |
    * пользователь нажимает на "ссылка Пункт меню"
    * пользователь продолжает работать со страницей

    * открывается страница "Страница элементов"
    * элемент "Радио группа" присутствует
    * элемент "Радио группа" отображается
    * элемент "Радио группа" находится не в фокусе
    * пользователь прокручивает страницу до "Радио группа"
    * пользователь наводит курсор на "Радио группа"

    * элемент "Радио группа" имеет размеры "825 x 24.0"
    * элемент "Радио группа" имеет размеры "825 x 24.0 (0.2)"
    * элемент "Радио группа" не имеет размеры "1825 x 24.0"
    * элемент "Радио группа" не имеет размеры "1825 x 24.0 (0.2)"

    * элемент "Радио группа" находится в "(330.0, 713.375)"
    * элемент "Радио группа" находится в "(330.0, 713.375) (0.2)"
    * элемент "Радио группа" не находится в "(1757.5, 1328.375)"
    * элемент "Радио группа" не находится в "(1757.5, 1328.375) (0.2)"

    * элемент "Радио группа" имеет цвет "rgb(33, 37, 41)" css-свойства "border-color"
    * элемент "Радио группа" имеет цвет "rgba(33, 37, 41, 1.0)" css-свойства "border-color"
    * элемент "Радио группа" не имеет цвет "rgb(133, 37, 41)" css-свойства "border-color"
    * элемент "Радио группа" не имеет цвет "rgba(133, 37, 41, 1.0)" css-свойства "border-color"

    * в радио-группе "Радио группа" содержится "3" кнопки
    * в радио-группе "Радио группа" содержится "[не]2" кнопки
    * в радио-группе "Радио группа" кнопка с лейблом "Label 3" присутствует
    * в радио-группе "Радио группа" кнопка с индексом "1" присутствует
    * в радио-группе "Радио группа" кнопка с лейблом "Label 4" отсутствует
    * в радио-группе "Радио группа" кнопка с индексом "[больше]3" отсутствует
    * в радио-группе "Радио группа" кнопка с лейблом "Label 2" выделена
    * в радио-группе "Радио группа" кнопка с индексом "1" выделена
    * в радио-группе "Радио группа" кнопка с лейблом "Label 1" не выделена
    * в радио-группе "Радио группа" кнопка с индексом "2" не выделена
    * в радио-группе "Радио группа" кнопка с лейблом "Label 1" доступна
    * в радио-группе "Радио группа" кнопка с индексом "0" доступна
    * в радио-группе "Радио группа" кнопка с лейблом "Label 3" недоступна
    * в радио-группе "Радио группа" кнопка с индексом "2" недоступна
    * в радио-группе "Радио группа" кнопка с лейблом "Label 2" имеет индекс "1"
    * в радио-группе "Радио группа" кнопка с индексом "0" имеет лейбл "Label 1"
