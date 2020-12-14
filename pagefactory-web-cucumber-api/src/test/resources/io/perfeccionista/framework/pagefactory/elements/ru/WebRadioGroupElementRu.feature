# language:ru

@UseEnvironmentConfiguration(io.perfeccionista.framework.pagefactory.configurations.TestEnvironmentConfiguration)
Функционал: Функционал проверки WebRadioGroup

  @Disabled
  @WebElement @WebRadioGroup
  Сценарий: Положительный сценарий WebRadioGroup
    * пользователь запускает браузер "${[props] browser}"
    * пользователь вводит URL "${[props] start_url}" в браузер и нажимает 'Enter'

    * открывается страница "Домашняя страница"
    * пользователь выбирает в "Левое меню" для работы блоки, в которых
      | "ссылка Пункт меню" содержит "Elements" |

    * пользователь нажимает на "ссылка Пункт меню"
    * пользователь продолжает работать со страницей

    * открывается страница "Страница элементов"
    * "Радио группа" присутствует
    * "Радио группа" отображается
    * "Радио группа" находится не в фокусе
    * пользователь прокручивает страницу до "Радио группа"
    * пользователь наводит курсор на "Радио группа"

    * "Радио группа" имеет размеры "825 x 24.0"
    * "Радио группа" имеет размеры "825 x 24.0 (0.2)"
    * "Радио группа" не имеет размеры "1825 x 24.0"
    * "Радио группа" не имеет размеры "1825 x 24.0 (0.2)"

    * "Радио группа" находится в "(757.5, 1328.375)"
    * "Радио группа" находится в "(757.5, 1328.375) (0.2)"
    * "Радио группа" не находится в "(1757.5, 1328.375)"
    * "Радио группа" не находится в "(1757.5, 1328.375) (0.2)"

    * "Радио группа" имеет цвет "rgb(33, 37, 41)" css-свойства "border-color"
    * "Радио группа" имеет цвет "rgba(33, 37, 41, 1.0)" css-свойства "border-color"
    * "Радио группа" не имеет цвет "rgb(133, 37, 41)" css-свойства "border-color"
    * "Радио группа" не имеет цвет "rgba(133, 37, 41, 1.0)" css-свойства "border-color"

    * в "Радио группа" содержится "3" кнопки
    * в "Радио группа" содержится "[не]2" кнопки
    * в "Радио группа" кнопка с лейблом "Label 3" присутствует
    * в "Радио группа" кнопка с индексом "1" присутствует
    * в "Радио группа" кнопка с лейблом "Label 4" отсутствует
    * в "Радио группа" кнопка с индексом "[больше]3" отсутствует
    * в "Радио группа" кнопка с лейблом "Label 2" выделена
    * в "Радио группа" кнопка с индексом "1" выделена
    * в "Радио группа" кнопка с лейблом "Label 1" не выделена
    * в "Радио группа" кнопка с индексом "2" не выделена
    * в "Радио группа" кнопка с лейблом "Label 1" доступна
    * в "Радио группа" кнопка с индексом "0" доступна
    * в "Радио группа" кнопка с лейблом "Label 3" недоступна
    * в "Радио группа" кнопка с индексом "2" недоступна
    * в "Радио группа" кнопка с лейблом "Label 2" имеет индекс "1"
    * в "Радио группа" кнопка с индексом "0" имеет лейбл "Label 1"