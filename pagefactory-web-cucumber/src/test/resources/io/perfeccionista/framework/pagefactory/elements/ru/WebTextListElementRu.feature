# language:ru

@UseEnvironmentConfiguration(io.perfeccionista.framework.pagefactory.configurations.TestEnvironmentConfiguration)
Функционал: Функционал проверки WebTextList

  @Disabled
  @WebElement @WebTextList
  Сценарий: Положительный сценарий WebTextList
    * пользователь запускает браузер "${[props] browser}"
    * пользователь вводит URL "${[props] start_url}" в браузер и нажимает 'Enter'

    * открывается страница "Домашняя страница"
    * пользователь выбирает в списке "Левое меню" блоки, где
      | "ссылка Пункт меню" содержит "Text List Elements" |
    * пользователь нажимает на "ссылка Пункт меню"
    * пользователь продолжает работать со страницей

    * открывается страница "Страница с простым списком стран"
    * элемент "Простой список стран" присутствует
    * элемент "Простой список стран" отображается
    * элемент "Простой список стран" находится не в фокусе
    * пользователь прокручивает страницу до "Простой список стран"
    * пользователь наводит курсор на "Простой список стран"

    * элемент "Простой список стран" имеет размеры "795.0 x 9508.0"
    * элемент "Простой список стран" имеет размеры "795.0 x 9508.0 (0.2)"
    * элемент "Простой список стран" не имеет размеры "1795.0 x 9508.0"
    * элемент "Простой список стран" не имеет размеры "1795.0 x 9508.0 (0.2)"

    * элемент "Простой список стран" находится в "(757.5, 505.0)"
    * элемент "Простой список стран" находится в "(757.5, 505.0) (0.2)"
    * элемент "Простой список стран" не находится в "(1757.5, 505.0)"
    * элемент "Простой список стран" не находится в "(1757.5, 505.0) (0.2)"

    * элемент "Простой список стран" имеет цвет "rgb(33, 37, 41)" css-свойства "border-color"
    * элемент "Простой список стран" имеет цвет "rgba(33, 37, 41, 1.0)" css-свойства "border-color"
    * элемент "Простой список стран" не имеет цвет "rgb(133, 37, 41)" css-свойства "border-color"
    * элемент "Простой список стран" не имеет цвет "rgba(133, 37, 41, 1.0)" css-свойства "border-color"

    * текстовый список "Простой список стран" содержит "195" значений
    * текстовый список "Простой список стран" содержит "[не]196" значений
