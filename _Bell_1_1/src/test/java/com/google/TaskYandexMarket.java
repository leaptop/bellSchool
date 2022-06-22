package com.google;

import driver.Waits;
import helpers.Actions;
import org.junit.jupiter.api.Assertions;
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
    @CsvSource("10000, 90000, HP, Lenovo")
    public void checkYM(String min, String max, String manufacturer1, String manufacturer2) throws InterruptedException {
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
        YandexMarketNotebooksPO ymn = new YandexMarketNotebooksPO(chromedriver);
        Waits.waitUntilElementBeClickable(chromedriver.findElement(By.xpath(ymn.textFieldPriceMin)));
        WebElement minWE = chromedriver.findElement(By.xpath(ymn.textFieldPriceMin));
        WebElement maxWE = chromedriver.findElement(By.xpath(ymn.textFieldPriceMax));

        minWE.sendKeys(min);
        maxWE.sendKeys(max);
        Waits.waitUntilAttributeWillBe(minWE, "value", min);
        Waits.waitUntilAttributeWillBe(maxWE, "value", max);
        chromedriver.findElement(By.xpath(ymn.showAllManufacturersButton)).click();
        Waits.waitUntilElementBeClickable(chromedriver.findElement
                (By.xpath(ymn.getComboBoxManufacturer_locator(manufacturer1))));
        WebElement manuWE1 = chromedriver.findElement
                (By.xpath(ymn.getComboBoxManufacturer_locator(manufacturer1)));
        Waits.waitUntilElementBeClickable(chromedriver.findElement
                (By.xpath(ymn.getComboBoxManufacturer_locator(manufacturer2))));
        WebElement manuWE2 = chromedriver.findElement
                (By.xpath(ymn.getComboBoxManufacturer_locator(manufacturer2)));
        // System.out.println("Время пошло");
        //Thread.sleep(10000);
        manuWE1.click();
        manuWE2.click();

        Waits.waitElementPresents(ymn.theSeventhSearchResult);
        Waits.waitUntilElementNotExistByXpath(ymn.upperRotatingProgressBar);
        String text1 = ymn.getTitleAttributeOfLinkOfResultNumber(0);
        chromedriver.findElement(By.xpath(ymn.searchField_locator)).sendKeys(text1);
        chromedriver.findElement(By.xpath(ymn.searchButton_locator)).click();
        Waits.waitElementPresents(ymn.getLinkTextOfSearchResult(0));
       // Thread.sleep(10000);
        String text2 = ymn.getTitleAttributeOfLinkOfResultNumber(0);
        System.out.println("text1: " + text1);
        System.out.println("text2: " + text2);
        Assertions.assertTrue(text1.equals(text2));

    }
}
