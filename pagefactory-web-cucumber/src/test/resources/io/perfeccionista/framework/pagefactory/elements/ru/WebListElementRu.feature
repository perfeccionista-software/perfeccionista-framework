# language:ru

Функционал: Функционал проверки WebList

  @WebElement @WebList
  Сценарий: Положительный сценарий WebList
    * пользователь запускает браузер "${[config] browser}"
    * пользователь вводит URL "${[config] start_url}" в браузер и нажимает 'Enter'

    * открывается страница "Домашняя страница"
    * пользователь выбирает в списке "Левое меню" блоки, где
      | "ссылка Пункт меню" содержит "List Elements" |
    * пользователь нажимает на "ссылка Пункт меню"
    * пользователь продолжает работать со страницей

    * открывается страница "Страница со списком стран"
    * элемент "Список стран" присутствует
    * элемент "Список стран" отображается
    * элемент "Список стран" находится не в фокусе
    * пользователь прокручивает страницу до "Список стран"
    * пользователь наводит курсор на "Список стран"

    * элемент "Список стран" имеет размеры "795.0 x 10295.0"
    * элемент "Список стран" имеет размеры "795.0 x 10295.0 (0.2)"
    * элемент "Список стран" не имеет размеры "1795.0 x 10295.0"
    * элемент "Список стран" не имеет размеры "1795.0 x 10295.0 (0.2)"

    * элемент "Список стран" находится в "(757.5, 505.5)"
    * элемент "Список стран" находится в "(757.5, 505.5) (0.2)"
    * элемент "Список стран" не находится в "(1757.5, 505.5)"
    * элемент "Список стран" не находится в "(1757.5, 505.5) (0.2)"

    * элемент "Список стран" имеет цвет "rgb(33, 37, 41)" css-свойства "border-color"
    * элемент "Список стран" имеет цвет "rgba(33, 37, 41, 1.0)" css-свойства "border-color"
    * элемент "Список стран" не имеет цвет "rgb(133, 37, 41)" css-свойства "border-color"
    * элемент "Список стран" не имеет цвет "rgba(133, 37, 41, 1.0)" css-свойства "border-color"

    * список "Список стран" содержит "195" блоков
    * список "Список стран" содержит "[не]196" блоков