# language:ru

@UseEnvironmentConfiguration(io.perfeccionista.framework.pagefactory.configurations.TestEnvironmentConfiguration)
Функционал: Функционал проверки WebList

  @Disabled
  @WebElement @WebList
  Сценарий: Положительный сценарий WebList
    * пользователь запускает браузер "${[props] browser}"
    * пользователь вводит URL "${[props] start_url}" в браузер и нажимает 'Enter'

    * открывается страница "Домашняя страница"
    * пользователь выбирает в "Левое меню" для работы блоки, в которых
      | "ссылка Пункт меню" содержит "List Elements" |

    * пользователь нажимает на "ссылка Пункт меню"
    * пользователь продолжает работать со страницей

    * открывается страница "Страница со списком стран"
    * "Список стран" присутствует
    * "Список стран" отображается
    * "Список стран" находится не в фокусе
    * пользователь прокручивает страницу до "Список стран"
    * пользователь наводит курсор на "Список стран"

    * "Список стран" имеет размеры "795.0 x 10295.0"
    * "Список стран" имеет размеры "795.0 x 10295.0 (0.2)"
    * "Список стран" не имеет размеры "1795.0 x 10295.0"
    * "Список стран" не имеет размеры "1795.0 x 10295.0 (0.2)"

    * "Список стран" находится в "(757.5, 505.5)"
    * "Список стран" находится в "(757.5, 505.5) (0.2)"
    * "Список стран" не находится в "(1757.5, 505.5)"
    * "Список стран" не находится в "(1757.5, 505.5) (0.2)"

    * "Список стран" имеет цвет "rgb(33, 37, 41)" css-свойства "border-color"
    * "Список стран" имеет цвет "rgba(33, 37, 41, 1.0)" css-свойства "border-color"
    * "Список стран" не имеет цвет "rgb(133, 37, 41)" css-свойства "border-color"
    * "Список стран" не имеет цвет "rgba(133, 37, 41, 1.0)" css-свойства "border-color"

    * список "Список стран" содержит "195" блоков
    * список "Список стран" содержит "[не]196" блоков