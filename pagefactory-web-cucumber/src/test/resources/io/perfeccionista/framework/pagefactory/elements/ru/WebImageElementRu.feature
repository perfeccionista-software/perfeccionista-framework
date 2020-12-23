# language:ru

@UseEnvironmentConfiguration(io.perfeccionista.framework.pagefactory.configurations.TestEnvironmentConfiguration)
Функционал: Функционал проверки WebImage

  @Disabled
  @WebElement @WebImage
  Сценарий: Положительный сценарий WebImage
    * пользователь запускает браузер "${[props] browser}"
    * пользователь вводит URL "${[props] start_url}" в браузер и нажимает 'Enter'

    * открывается страница "Домашняя страница"
    * пользователь выбирает в списке "Левое меню" блоки, где
      | "ссылка Пункт меню" содержит "Elements" |
    * пользователь нажимает на "ссылка Пункт меню"
    * пользователь продолжает работать со страницей

    * открывается страница "Страница элементов"
    * элемент "Карта мира" присутствует
    * элемент "Карта мира" отображается
    * элемент "Карта мира" находится не в фокусе
    * пользователь прокручивает страницу до "Карта мира"
    * пользователь наводит курсор на "Карта мира"
    * пользователь нажимает на "Карта мира"

    * компонент "Image border" элемента "Карта мира" присутствует
    * компонент "Image border" элемента "Карта мира" отображается

    * элемент "Карта мира" имеет размеры "176.3 x 125.4"
    * элемент "Карта мира" имеет размеры "176.3 x 125.4 (0.2)"
    * элемент "Карта мира" не имеет размеры "276.3 x 125.4"
    * элемент "Карта мира" не имеет размеры "276.3 x 125.4 (0.2)"

    * элемент "Карта мира" находится в "(448.0, 235.5)"
    * элемент "Карта мира" находится в "(448.0, 235.5) (0.2)"
    * элемент "Карта мира" не находится в "(548.0, 235.5)"
    * элемент "Карта мира" не находится в "(548.0, 235.5) (0.2)"

    * элемент "Карта мира" имеет цвет "rgb(222, 226, 230)" css-свойства "border-color"
    * элемент "Карта мира" имеет цвет "rgba(222, 226, 230, 1.0)" css-свойства "border-color"
    * элемент "Карта мира" не имеет цвет "rgb(122, 226, 230)" css-свойства "border-color"
    * элемент "Карта мира" не имеет цвет "rgba(122, 226, 230, 1.0)" css-свойства "border-color"

    * свойство "prompt" элемента "Карта мира" содержит "World map picture"
    * свойство "source" элемента "Карта мира" содержит "[подстрока]src/static/world_map.jpeg"
    * свойство "prompt" элемента "Карта мира" не содержит "[подстрока]image"
    * свойство "source" элемента "Карта мира" не содержит "[пусто]"

    * пользователь закрывает браузер
