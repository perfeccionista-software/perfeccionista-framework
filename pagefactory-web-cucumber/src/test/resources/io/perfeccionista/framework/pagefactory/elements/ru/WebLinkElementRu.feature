# language:ru

Функционал: Функционал проверки WebLink

  @WebElement @WebLink
  Сценарий: Положительный сценарий WebLink
    * пользователь запускает браузер "${[config] browser}"
    * пользователь устанавливает размер окна браузера "${[config] browser_width}" на "${[config] browser_height}"
    * пользователь вводит URL "${[config] start_url}" в браузер и нажимает 'Enter'

    * открывается страница "Домашняя страница"
    * пользователь выбирает в списке "Левое меню" блоки, где
      | "ссылка Пункт меню" содержит "Elements" |
    * пользователь нажимает на "ссылка Пункт меню"
    * пользователь продолжает работать со страницей

    * открывается страница "Страница элементов"
    * элемент "Простая ссылка" присутствует
    * элемент "Простая ссылка" отображается
    * элемент "Простая ссылка" находится не в фокусе
    * пользователь прокручивает страницу до "Простая ссылка"

    * элемент "Простая ссылка" имеет размеры "81.78 x 22.0"
    * элемент "Простая ссылка" имеет размеры "81.78 x 22.0 (0.2)"
    * элемент "Простая ссылка" не имеет размеры "181.78 x 22.0"
    * элемент "Простая ссылка" не имеет размеры "181.78 x 22.0 (0.2)"

    * элемент "Простая ссылка" находится в "(495.23, 491.37)"
    * элемент "Простая ссылка" находится в "(495.23, 491.37) (0.2)"
    * элемент "Простая ссылка" не находится в "(595.23, 491.37)"
    * элемент "Простая ссылка" не находится в "(595.23, 491.37) (0.2)"

    * элемент "Простая ссылка" имеет цвет "rgb(0, 123, 255)" css-свойства "color"
    * элемент "Простая ссылка" имеет цвет "rgba(0, 123, 255, 1.0)" css-свойства "color"
    * элемент "Простая ссылка" не имеет цвет "rgb(50, 123, 255)" css-свойства "color"
    * элемент "Простая ссылка" не имеет цвет "rgba(50, 123, 255, 1.0)" css-свойства "color"

    * пользователь наводит курсор на "Простая ссылка"
    * элемент "Простая ссылка" имеет цвет "rgba(0, 86, 179, 1.0)" css-свойства "color"
    * элемент "Простая ссылка" содержит "Simple Link"
    * элемент "Простая ссылка" содержит "[подстрока]Link"
    * элемент "Простая ссылка" не содержит "[подстрока]button"

    * элемент "текстовый блок Простой ссылки" присутствует
    * элемент "текстовый блок Простой ссылки" не отображается

    * пользователь нажимает на "Простая ссылка"
    * элемент "Простая ссылка" находится в фокусе

    * элемент "текстовый блок Простой ссылки" присутствует
    * элемент "текстовый блок Простой ссылки" отображается
    * элемент "текстовый блок Простой ссылки" находится не в фокусе
    * элемент "текстовый блок Простой ссылки" содержит "Simple Link clicked"
    * элемент "текстовый блок Простой ссылки" содержит "[текст без учета регистра]siMple lInK CLIcked"
    * элемент "текстовый блок Простой ссылки" содержит "[подстроку]ink cli"
    * элемент "текстовый блок Простой ссылки" содержит "[подстроку без учета регистра]Mple lINk CLIck"
    * элемент "текстовый блок Простой ссылки" содержит "[начинается с]Simple"
    * элемент "текстовый блок Простой ссылки" содержит "[заканчивается на]clicked"
    * элемент "текстовый блок Простой ссылки" содержит "[RegExp]^Simple\s?\w+\s{1}\w{4}ked$"

    * пользователь закрывает браузер
