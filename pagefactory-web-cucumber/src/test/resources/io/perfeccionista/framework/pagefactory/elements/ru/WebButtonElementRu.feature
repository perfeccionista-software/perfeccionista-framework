# language:ru

Функционал: Функционал проверки WebButton

  @WebElement @WebButton
  Сценарий: Положительный сценарий WebButton

    * шаг "Открытие браузера"
    * пользователь запускает браузер "${[config] browser}"
    * пользователь устанавливает размер окна браузера "${[config] browser_width}" на "${[config] browser_height}"
    * пользователь вводит URL "${[config] start_url}" в браузер и нажимает 'Enter'

    * шаг "Переход на страницу проверок"
    * открывается страница "Домашняя страница"
    * пользователь выбирает в списке "Левое меню" блоки, где
      | "ссылка Пункт меню" содержит "Elements" |
    * пользователь нажимает на "ссылка Пункт меню"
    * пользователь продолжает работать со страницей

    * шаг "Выполнение проверок"
    * открывается страница "Страница элементов"
    * элемент "Простая кнопка" присутствует
    * элемент "Простая кнопка" отображается
    * элемент "Простая кнопка" находится не в фокусе
    * пользователь прокручивает страницу до "Простая кнопка"

    * элемент "Простая кнопка" имеет размеры "127.6 x 38.0"
    * элемент "Простая кнопка" имеет размеры "127.6 x 38.0 (0.2)"
    * элемент "Простая кнопка" не имеет размеры "227.6 x 38.0"
    * элемент "Простая кнопка" не имеет размеры "227.6 x 38.0 (0.2)"

    * элемент "Простая кнопка" находится в "(369.3, 314.4)"
    * элемент "Простая кнопка" находится в "(369.3, 314.4) (0.2)"
    * элемент "Простая кнопка" не находится в "(572.3, 333.4)"
    * элемент "Простая кнопка" не находится в "(572.3, 333.4) (0.2)"

    * элемент "Простая кнопка" имеет цвет "rgb(0, 123, 255)" css-свойства "background-color"
    * элемент "Простая кнопка" имеет цвет "rgba(0, 123, 255, 1.0)" css-свойства "background-color"
    * элемент "Простая кнопка" не имеет цвет "rgb(50, 123, 255)" css-свойства "background-color"
    * элемент "Простая кнопка" не имеет цвет "rgba(50, 123, 255, 1.0)" css-свойства "background-color"

    * пользователь наводит курсор на "Простая кнопка"
    * элемент "Простая кнопка" имеет цвет "rgba(0, 105, 217, 1.0)" css-свойства "background-color"
    * элемент "Простая кнопка" содержит "Simple Button"
    * элемент "Простая кнопка" содержит "[подстрока]Button"
    * элемент "Простая кнопка" не содержит "[подстрока]link"

    * элемент "текстовый блок Простой кнопки" отсутствует
    * элемент "текстовый блок Простой кнопки" не отображается

    * пользователь нажимает на "Простая кнопка"
    * элемент "Простая кнопка" находится в фокусе

    * элемент "текстовый блок Простой кнопки" присутствует
    * элемент "текстовый блок Простой кнопки" отображается
    * элемент "текстовый блок Простой кнопки" находится не в фокусе
    * элемент "текстовый блок Простой кнопки" содержит "Simple Button clicked"
    * элемент "текстовый блок Простой кнопки" содержит "[текст без учета регистра]siMple butTon CLIcked"
    * элемент "текстовый блок Простой кнопки" содержит "[подстроку]utton cli"
    * элемент "текстовый блок Простой кнопки" содержит "[подстроку без учета регистра]Mple butTon CLIck"
    * элемент "текстовый блок Простой кнопки" содержит "[начинается с]Simple"
    * элемент "текстовый блок Простой кнопки" содержит "[заканчивается на]clicked"
    * элемент "текстовый блок Простой кнопки" содержит "[RegExp]^Simple\s?\w+\s{1}\w{4}ked$"

    * пользователь закрывает браузер

  @WebElement @WebButton
  Сценарий: Положительный сценарий WebButton со спиннером

    * шаг "Открытие браузера"
    * пользователь запускает браузер "${[config] browser}"
    * пользователь устанавливает размер окна браузера "${[config] browser_width}" на "${[config] browser_height}"
    * пользователь вводит URL "${[config] start_url}" в браузер и нажимает 'Enter'

    * шаг "Переход на страницу проверок"
    * открывается страница "Домашняя страница"
    * пользователь выбирает в списке "Левое меню" блоки, где
      | "ссылка Пункт меню" содержит "Elements" |
    * пользователь нажимает на "ссылка Пункт меню"
    * пользователь продолжает работать со страницей

    * шаг "Выполнение проверок"
    * открывается страница "Страница элементов"
    * элемент "Кнопка со спиннером" присутствует
    * элемент "Кнопка со спиннером" отображается
    * элемент "Спиннер" присутствует
    * элемент "Спиннер" не отображается
    * элемент "текстовый блок Кнопки со спиннером" присутствует
    * элемент "текстовый блок Кнопки со спиннером" не отображается
    * пользователь нажимает на "Кнопка со спиннером"
    * элемент "Спиннер" отображается
    * элемент "текстовый блок Кнопки со спиннером" отображается
    * элемент "текстовый блок Кнопки со спиннером" содержит "Spinner Button clicked"
