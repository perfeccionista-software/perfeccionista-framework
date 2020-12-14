#language:ru
Функционал: Тесты элементов со списками

  @list
  Сценарий: Тест WebList
    * выполнить сценарий "пользователь открывает страницу Элементов" с параметром "page" равным "UL Elements"
    * "list of countries" содержит "195" блоков
    * "list of countries" содержит "[больше]150" блоков
    * "list of countries" содержит "[больше или равно]193" блока
    * "list of countries" содержит "[меньше]200" блоков
    * "list of countries" содержит "[меньше или равно]196" блоков
    * "list of countries" содержит "[не равно]192" блока

    * "list of countries" содержит "1" блок, в котором
      | индекс "153" |

    * в "list of countries" "текстовые значения" из "Short name" в формате "дата" отсортированы "по возрастанию"

    * "list of countries" содержит "42" блока, в которых
      | индекс "[больше]150" |
    * "list of countries" содержит "1" блок, в котором
      | индекс "122" |
    * "list of countries" содержит "193" блока, в которых
      | "Full name" отображается |
    * "list of countries" содержит "123" блока, в которых
      | "Short name" содержит "[подстрока]gia" |
    * "list of countries" содержит "77" блоков, в которых
      | "Number" содержит число "[меньше]77" |
    * "list of countries" содержит "186" блоков, в которых
      | "Checkbox" доступен |
    * "list of countries" содержит "5" блоков, в которых
      | "Checkbox" выделен |
    * "list of countries" содержит "1" блок, в котором
      | свойство "Wiki link" элемента "Short name" содержит "https://ru.wikipedia.org/wiki/Австралия" |
    * "list of countries" содержит "14" блоков, в которых
      | компонент "SNG" элемента "Full name" отображается |