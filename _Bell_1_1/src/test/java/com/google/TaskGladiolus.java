package com.google;

import driver.Waits;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.GoogleBeforeSearchPageObject;
import pages.GooglePageAfterSearchPageObject;

import java.util.List;

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
        Waits.waitElementPresents(gbspo.inputForSearch_Locator);
        gbspo.inputForSearchWebElement = chromedriver.findElement(
                By.xpath(gbspo.inputForSearch_Locator));
        gbspo.insertTextIntoSearchFieldAndPressEnter("Гладиолус");
        GooglePageAfterSearchPageObject gpas = new GooglePageAfterSearchPageObject(chromedriver);
        List<WebElement> lt = gpas.getResults();
        //System.out.println("lt.size = " + lt.size()); for (int i = 0; i < lt.size(); i++) {System.out.println("lt.get(i).getAttribute(\"href\") = " + lt.get(i).getAttribute("href"));}
        Assertions.assertTrue((
                        (lt.stream().anyMatch(x -> x.getAttribute("href").contains(result)))
                ),
                "На первой странице результатов поиска '" + keywords + "' не найдено '" + result + "'\n");
    }
}
