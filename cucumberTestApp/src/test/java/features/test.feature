
Feature: тесты для поисковиков

  @Run
  Scenario Outline: тестирование гугла
    Given открыть браузер
    When выполнен переход к следующей странице '<сайт>'
    Then заголовок страницы равен следующему тексту '<title>'
    And закрыть браузер

    Examples:
    |сайт|title|
    |https://www.google.com/|Google|
    |https://www.yandex.ru/|Яндекс|

  Scenario: коллекции
    When распечатать list:
      |list 1|
      |list 2|
      |list 3|
      |list 4|

    And распечатать map:
      |map key 1|map value 1|
      |map key 2|map value 2|
      |map key 3|map value 3|
      |map key 4|map value 4|

  Scenario: dataTable коллекции
    When распечатать dataTable list:
      |list 1|
      |list 2|
      |list 3|
      |list 4|
    And распечатать dataTable map:
      |map key 1|map value 1|
      |map key 2|map value 2|
      |map key 3|map value 3|
      |map key 4|map value 4|

  Scenario: dataTable несколько полей
    When распечатать таблицу:
      |firstCollum  |secondCollum  |thirdCollum  |fourCollum  |
      |firstCollum 1|secondCollum 1|thirdCollum 1|fourCollum 1|
      |firstCollum 2|secondCollum 2|thirdCollum 2|fourCollum 2|
      |firstCollum 3|secondCollum 3|thirdCollum 3|fourCollum 3|
      |firstCollum 4|secondCollum 4|thirdCollum 4|fourCollum 4|



