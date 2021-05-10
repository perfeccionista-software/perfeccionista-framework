# language:ru

Функционал: Функционал проверки WebTextTable

  @WebElement @WebTextTable
  Сценарий: Положительный сценарий WebTextTable
    * пользователь запускает браузер "${[config] browser}"
    * пользователь устанавливает размер окна браузера "${[config] browser_width}" на "${[config] browser_height}"
    * пользователь вводит URL "${[config] start_url}" в браузер и нажимает 'Enter'

    * открывается страница "Домашняя страница"
    * пользователь выбирает в списке "Левое меню" блоки, где
      | "ссылка Пункт меню" содержит "Text Table Element" |
    * пользователь нажимает на "ссылка Пункт меню"
    * пользователь продолжает работать со страницей

    * открывается страница "Страница с простой таблицей стран"
    * элемент "Простая таблица стран" присутствует
    * элемент "Простая таблица стран" отображается
    * элемент "Простая таблица стран" находится не в фокусе
    * пользователь прокручивает страницу до "Простая таблица стран"
    * пользователь наводит курсор на "Простая таблица стран"

    * элемент "Простая таблица стран" имеет размеры "795.0 x 10014.0"
    * элемент "Простая таблица стран" имеет размеры "795.0 x 10014.0 (0.2)"
    * элемент "Простая таблица стран" не имеет размеры "1795.0 x 10014.0"
    * элемент "Простая таблица стран" не имеет размеры "1795.0 x 10014.0 (0.2)"

    * элемент "Простая таблица стран" находится в "(757.5, 439.0)"
    * элемент "Простая таблица стран" находится в "(757.5, 439.0) (0.2)"
    * элемент "Простая таблица стран" не находится в "(1757.5, 439.0)"
    * элемент "Простая таблица стран" не находится в "(1757.5, 439.0) (0.2)"

    * элемент "Простая таблица стран" имеет цвет "rgb(222, 226, 230)" css-свойства "border-color"
    * элемент "Простая таблица стран" имеет цвет "rgba(222, 226, 230, 1.0)" css-свойства "border-color"
    * элемент "Простая таблица стран" не имеет цвет "rgb(122, 226, 230)" css-свойства "border-color"
    * элемент "Простая таблица стран" не имеет цвет "rgba(122, 226, 230, 1.0)" css-свойства "border-color"

    * текстовая таблица "Простая таблица стран" содержит "195" строк
    * текстовая таблица "Простая таблица стран" содержит "[не]196" строк
