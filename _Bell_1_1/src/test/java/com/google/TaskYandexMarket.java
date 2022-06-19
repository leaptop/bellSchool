package com.google;

import driver.Waits;
import helpers.Actions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.YandexMarketNotebooksPO;
import pages.YandexMarketPO;

import java.util.ArrayList;

public class TaskYandexMarket extends BaseTest {
    /**
     * Если гугл вас лочит, используйте любой другой поисковик
     * Задание 1.3
     * Задача:
     * 1. Открыть браузер и развернуть на весь экран.
     * 2. Зайти на yandex.ru.
     * 3. Перейти в яндекс маркет
     * 4. Выбрать раздел Компьютеры
     * 5. Выбрать раздел Ноутбуки
     * 6. Задать параметр «Цена, Р» от  10000 до 90000 рублей.
     * 7. Выбрать производителя HP и Lenovo
     * 8. Дождаться результатов поиска.
     * 9. Установить количество показываемых элементов на страницу 12 (Элемент находиться в самом низу страницы)
     * 10. Дождаться обновления результатов.
     * 11. Проверить, что на странице отображалось 12 элементов.
     * 12. Запомнить наименование первого значения в списке.
     * 13. В поисковую строку ввести запомненное значение.
     * 14. Нажать кнопку «Найти»
     * 15. Проверить, что наименование товара соответствует сохраненному значению.
     * Автотест необходимо написать, используя данный стек:
     * Java, Junit Jupiter, Selenium, PageObject или PageFactory
     * По итогу:
     * -	Делаем ровно то что написано
     * -	Недопустимо использования Thread.sleep. За исключением, создания собственных ожиданий (к примеру каждый 5 милисекунд проверяем что что-то случилось, и так не более 10 секунд.). Лучше обойтись явными\неявными ожиданиями
     * -	Обязательно писать JavaDoc
     * -	Помните про универсальные методы. Старайтесь писать код, полезный в других тестах, полезный коллегам. Не будьте эгоистами!
     */
    @ParameterizedTest(name = "{displayName} {arguments}")
    @CsvSource("10000, 90000")
    public void checkYM(String min, String max) {
        chromedriver.get("https://yandex.ru/");
        YandexMarketPO ympo = new YandexMarketPO(chromedriver);
        chromedriver.findElement(By.xpath(ympo.yandexMarket_locator)).click();
        ArrayList<String> newTab = new ArrayList<>(chromedriver.getWindowHandles());
        chromedriver.switchTo().window(newTab.get(1));
        chromedriver.findElement(By.xpath(ympo.catalogueButton)).click();
        Waits.waitUntilElementBeVisible(chromedriver.findElement(By.xpath(ympo.cataloguePopUpButton)));
        Actions.hover.accept(By.xpath(ympo.categorySelectorComputers));
        Actions.hover.accept(By.xpath(ympo.categorySelectorNotebooks));
        Waits.waitElementPresents(ympo.categorySelectorNotebooks);
        chromedriver.findElement(By.xpath(ympo.categorySelectorNotebooks)).click();
        YandexMarketNotebooksPO ymn = new YandexMarketNotebooksPO();
        Waits.waitUntilElementBeClickable(chromedriver.findElement(By.xpath(ymn.textFieldPriceMin)));
        WebElement minWE = chromedriver.findElement(By.xpath(ymn.textFieldPriceMin));
        WebElement maxWE = chromedriver.findElement(By.xpath(ymn.textFieldPriceMax));
        minWE.sendKeys(min);
        maxWE.sendKeys(max);
        Waits.waitUntilElementTextContainsByLocator(By.xpath(ymn.textFieldPriceMin), min);
        Waits.waitUntilElementTextContainsByLocator(By.xpath(ymn.textFieldPriceMax), max);



    }
}
