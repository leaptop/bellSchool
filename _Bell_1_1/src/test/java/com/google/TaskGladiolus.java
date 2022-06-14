package com.google;

import driver.Waits;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import pages.GoogleBeforeSearchPageObject;
import pages.GooglePageAfterSearch;
//import org.junit.jupiter.engine.

public class TaskGladiolus extends BaseTest {

    /**
     * Задание 1.1
     * Задача:
     * 1.	запустить Chrome
     * 2.	Открыть https://www.google.com/
     * 3.	Ввести «Гладиолус». Нажать поиск
     * 4.	Убедится что в полученной выборке на первой странице есть ссылка на википедию
     * Автотест необходимо написать, используя данный стек:
     * Java, Junit Jupiter, Selenium, PageObject
     *
     * @param keywords
     * @param result
     * @throws InterruptedException
     */
    @ParameterizedTest(name = "{displayName} {arguments}")
    @CsvSource("Гладиолус, wikipedia")
    public void checkIfGladiolusWikiExists(String keywords, String result) throws InterruptedException {
        chromedriver.get("https://google.com/");
        GoogleBeforeSearchPageObject gbspo = new GoogleBeforeSearchPageObject(chromedriver);
        Waits.waitElementPresents(gbspo.inputForSearchLocator);
        System.out.println("3");
        Thread.sleep(2500000);
        gbspo.inputForSearchWebElement = chromedriver.findElement(
                By.xpath(gbspo.inputForSearchLocator));
        gbspo.insertTextIntoSearchFieldAndPressEnter("Гладиолус");
        GooglePageAfterSearch gpas = new GooglePageAfterSearch(chromedriver);
        Assertions.assertTrue((
                        (gpas.getResults().stream().anyMatch(x -> x.getText().contains(result)))
                                || (gpas.getAdditionalResults().stream().anyMatch(x -> x.getAttribute("href").contains(result)))
                ),
                "На первой странице результатов поиска '" + keywords + "' не найдено '" + result + "'\n");
    }
}
