package com.yandex;

import org.junit.jupiter.api.Test;
import com.codeborne.selenide.*;

import static com.codeborne.selenide.Selenide.open;

/**
 * Задание 4.1
 * 1. Открыть браузер и развернуть на весь экран.
 * 2. Зайти на yandex.ru.
 * 3. Перейти в яндекс маркет
 * 4. Выбрать раздел Электроника
 * 5. Выбрать раздел Смартфоны
 * 6. Задать параметр «Производитель» Apple.
 * 8. Дождаться результатов поиска.
 * 9. Установить количество показываемых элементов на страницу 12 (Элемент находиться в самом низу страницы)
 * 10. Убедится что в выборку попали только iPhone. Если страниц несколько – проверить все.
 * Тест должен работать для любого производителя из списка:
 * <p>
 * Java, Junit Jupiter, Selenide, PageObject по стилю Selenide
 * <p>
 * Изучить самостоятельно:
 * - Методы Selenide, ожидания Selenide
 * - Попрактиковаться в xpath
 * - Полностью разобрать проект, который делали. Чтобы было понятно каждое слово
 */
public class YandexMarketApple extends BaseTests {
    @Test
    public void testIphone() {
    open("https://yandex.ru");
    }
}
