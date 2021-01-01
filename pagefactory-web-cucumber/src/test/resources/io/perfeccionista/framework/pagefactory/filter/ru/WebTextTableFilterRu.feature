# language:ru

Функционал: Функционал проверки фильтров WebTextTable

  @WebElement @WebTextTable @Filter
  Сценарий: Положительный сценарий для WebTextTableRowEmptyCondition
    * пользователь запускает браузер "${[config] browser}"
    * пользователь вводит URL "${[config] start_url}" в браузер и нажимает 'Enter'

    * открывается страница "Домашняя страница"
    * пользователь выбирает в списке "Левое меню" блоки, где
      | "ссылка Пункт меню" содержит "Text Table Element" |
    * пользователь нажимает на "ссылка Пункт меню"
    * пользователь продолжает работать со страницей

    * открывается страница "Страница с простой таблицей стран"
    * элемент "Простая таблица стран" отображается
    * текстовая таблица "Простая таблица стран" содержит "195" строк
    * текстовая таблица "Простая таблица стран" содержит "195" строк, где
      | нет фильтра |

  @WebElement @WebTextTable @Filter
  Сценарий: Положительный сценарий для WebTextTableRowIndexCondition
    * пользователь запускает браузер "${[config] browser}"
    * пользователь вводит URL "${[config] start_url}" в браузер и нажимает 'Enter'

    * открывается страница "Домашняя страница"
    * пользователь выбирает в списке "Левое меню" блоки, где
      | "ссылка Пункт меню" содержит "Text Table Element" |
    * пользователь нажимает на "ссылка Пункт меню"
    * пользователь продолжает работать со страницей

    * открывается страница "Страница с простой таблицей стран"
    * элемент "Простая таблица стран" отображается
    * текстовая таблица "Простая таблица стран" содержит "95" строк, где
      | индекс "[больше или равен]100" |
    * текстовая таблица "Простая таблица стран" содержит "100" строк, где
      | индекс не "[больше или равен]100" |

  @WebElement @WebTextTable @Filter
  Сценарий: Положительный сценарий для WebTextTableRowTextCondition
    * пользователь запускает браузер "${[config] browser}"
    * пользователь вводит URL "${[config] start_url}" в браузер и нажимает 'Enter'

    * открывается страница "Домашняя страница"
    * пользователь выбирает в списке "Левое меню" блоки, где
      | "ссылка Пункт меню" содержит "Text Table Element" |
    * пользователь нажимает на "ссылка Пункт меню"
    * пользователь продолжает работать со страницей

    * открывается страница "Страница с простой таблицей стран"
    * элемент "Простая таблица стран" отображается
    * текстовая таблица "Простая таблица стран" содержит "1" строку, где
      | в столбце "Short country name" содержится "Финляндия" |
    * текстовая таблица "Простая таблица стран" содержит "17" строк, где
      | в столбце "Short country name" содержится "[начинается с]М" |
    * текстовая таблица "Простая таблица стран" содержит "194" строки, где
      | в столбце "Short country name" не содержится "Финляндия" |
    * текстовая таблица "Простая таблица стран" содержит "178" строк, где
      | в столбце "Short country name" не содержится "[начинается с]М" |

    * текстовая таблица "Простая таблица стран" содержит "1" строку, где
      | в столбце "Number" содержится число "77" |
    * текстовая таблица "Простая таблица стран" содержит "72" строки, где
      | в столбце "Number" содержится число "[больше или равно]124" |
    * текстовая таблица "Простая таблица стран" содержит "194" строки, где
      | в столбце "Number" не содержится число "77" |
    * текстовая таблица "Простая таблица стран" содержит "123" строки, где
      | в столбце "Number" не содержится число "[больше или равно]124" |

  #############################
  #   Multiple conditions
  #############################

  @WebElement @WebTextTable @Filter
  Сценарий: Положительный сценарий для WebTextTableFilterMultipleConditions_AND
    * пользователь запускает браузер "${[config] browser}"
    * пользователь вводит URL "${[config] start_url}" в браузер и нажимает 'Enter'

    * открывается страница "Домашняя страница"
    * пользователь выбирает в списке "Левое меню" блоки, где
      | "ссылка Пункт меню" содержит "Text Table Element" |
    * пользователь нажимает на "ссылка Пункт меню"
    * пользователь продолжает работать со страницей

    * открывается страница "Страница с простой таблицей стран"
    * элемент "Простая таблица стран" отображается
    * текстовая таблица "Простая таблица стран" содержит "14" строк, где
      | индекс "[больше или равен]100" | + |
      | индекс "[меньше]127"           | и |
      | индекс "[больше]112"           | и |

  @WebElement @WebTextTable @Filter
  Сценарий: Положительный сценарий для WebTextTableFilterMultipleConditions_OR
    * пользователь запускает браузер "${[config] browser}"
    * пользователь вводит URL "${[config] start_url}" в браузер и нажимает 'Enter'

    * открывается страница "Домашняя страница"
    * пользователь выбирает в списке "Левое меню" блоки, где
      | "ссылка Пункт меню" содержит "Text Table Element" |
    * пользователь нажимает на "ссылка Пункт меню"
    * пользователь продолжает работать со страницей

    * открывается страница "Страница с простой таблицей стран"
    * элемент "Простая таблица стран" отображается
    * текстовая таблица "Простая таблица стран" содержит "123" строки, где
      | индекс "[больше или равен]100" |  +  |
      | индекс "[меньше]27"            | или |
      | индекс "65"                    | или |

  @WebElement @WebTextTable @Filter
  Сценарий: Положительный сценарий для WebTextTableFilterMultipleConditions_ADD
    * пользователь запускает браузер "${[config] browser}"
    * пользователь вводит URL "${[config] start_url}" в браузер и нажимает 'Enter'

    * открывается страница "Домашняя страница"
    * пользователь выбирает в списке "Левое меню" блоки, где
      | "ссылка Пункт меню" содержит "Text Table Element" |
    * пользователь нажимает на "ссылка Пункт меню"
    * пользователь продолжает работать со страницей

    * открывается страница "Страница с простой таблицей стран"
    * элемент "Простая таблица стран" отображается
    * текстовая таблица "Простая таблица стран" содержит "123" строки, где
      | индекс "[больше или равен]100" | + |
      | индекс "[меньше]27"            | + |
      | индекс "65"                    | + |

  @WebElement @WebTextTable @Filter
  Сценарий: Положительный сценарий для WebTextTableFilterMultipleConditions_SUBTRACT
    * пользователь запускает браузер "${[config] browser}"
    * пользователь вводит URL "${[config] start_url}" в браузер и нажимает 'Enter'

    * открывается страница "Домашняя страница"
    * пользователь выбирает в списке "Левое меню" блоки, где
      | "ссылка Пункт меню" содержит "Text Table Element" |
    * пользователь нажимает на "ссылка Пункт меню"
    * пользователь продолжает работать со страницей

    * открывается страница "Страница с простой таблицей стран"
    * элемент "Простая таблица стран" отображается
    * текстовая таблица "Простая таблица стран" содержит "72" строки, где
      | индекс "[больше или равен]100" | - |
      | индекс "[меньше]27"            | - |
      | индекс "65"                    | - |

  @WebElement @WebTextTable @Filter
  Сценарий: Положительный сценарий для WebTextTableFilterMultipleConditions_WITH
    * пользователь запускает браузер "${[config] browser}"
    * пользователь вводит URL "${[config] start_url}" в браузер и нажимает 'Enter'

    * открывается страница "Домашняя страница"
    * пользователь выбирает в списке "Левое меню" блоки, где
      | "ссылка Пункт меню" содержит "Text Table Element" |
    * пользователь нажимает на "ссылка Пункт меню"
    * пользователь продолжает работать со страницей

    * открывается страница "Страница с простой таблицей стран"
    * элемент "Простая таблица стран" отображается
    * текстовая таблица "Простая таблица стран" содержит "63" строки, где
      | индекс "[больше или равен]100" |     |
      | индекс "[меньше]27"            | или |
      | индекс "[больше]150"           |  -  |
      | индекс "[меньше]15"            | или |

  @WebElement @WebTextTable @Filter
  Сценарий: Положительный сценарий для WebTextTableFilterMultipleConditions_WITHOUT
    * пользователь запускает браузер "${[config] browser}"
    * пользователь вводит URL "${[config] start_url}" в браузер и нажимает 'Enter'

    * открывается страница "Домашняя страница"
    * пользователь выбирает в списке "Левое меню" блоки, где
      | "ссылка Пункт меню" содержит "Text Table Element" |
    * пользователь нажимает на "ссылка Пункт меню"
    * пользователь продолжает работать со страницей

    * открывается страница "Страница с простой таблицей стран"
    * элемент "Простая таблица стран" отображается
    * текстовая таблица "Простая таблица стран" содержит "91" строку, где
      | индекс "[меньше]150"           |  -  |
      | индекс "[больше]15"            |  и  |
      | индекс не "123"                |  и  |
      | индекс "[больше]20"            |  +  |
      | индекс "[меньше]50"            |  и  |
