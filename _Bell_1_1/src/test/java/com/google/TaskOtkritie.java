package com.google;

import io.qameta.allure.Feature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import org.openqa.selenium.support.PageFactory;

import pages.BankOtkritiePageFactory;

import java.util.ArrayList;

public class TaskOtkritie extends BaseTest {

    /**
     * Задание 1.2
     * Задача:
     * 5.	запустить Chrome
     * 6.	открыть https://www.google.com/
     * 7.	написать в строке поиска «Открытие»
     * 8.	нажать Поиск
     * 9.	проверить, что результатах поиска есть https://www.open.ru
     * 10.	перейти на сайт https://www.open.ru
     * 11.	проверить в блоке «Курс обмена в интернет-банке», что курс продажи больше курса покупки, для USD и для EUR.
     * Автотест необходимо написать, используя данный стек:
     * Java, JUnit Jupiter, Selenium, PageFactory
     */
    @Feature("Проверка результатов поиска")
    @Test
    public void checkIfOpenRuCanBeFoundInGoogle() throws InterruptedException {
        chromedriver.get("https://www.google.com/search");
        //Thread.sleep(100000000);
        //Thread.sleep(10000);
//        GoogleBeforeSearchPageObject gbspo = new GoogleBeforeSearchPageObject(chromedriver);
//        gbspo.insertTextIntoSearchFieldAndPressEnter("Открытие");
//        GooglePageAfterSearchPageObject gpaspo = new GooglePageAfterSearchPageObject(chromedriver);
        //       List<WebElement> list = gpaspo.getResults();
        //       System.out.println("list.size = " + list.size()); for (int i = 0; i < list.size(); i++) {System.out.println("list.get(i).getAttribute(\"href\") = " + list.get(i).getAttribute("href"));}
//        Assertions.assertTrue(list.stream().anyMatch(x -> x.getText().contains("https://www.open.ru")));
        BankOtkritiePageFactory bopf = PageFactory.initElements(chromedriver, BankOtkritiePageFactory.class);//important: invoke selenium's PageFactory
        bopf.find("Открытие");
        Assertions.assertTrue(bopf.getResults().stream().anyMatch(x -> x.getText().contains("https://www.open.ru")));
        String n = Keys.chord(Keys.CONTROL, Keys.ENTER);
        bopf.getLinkOfFoundSite().sendKeys(n);
        ArrayList<String> newTab = new ArrayList<>(chromedriver.getWindowHandles());
        chromedriver.switchTo().window(newTab.get(1));
        bopf.fillCurrencyTable();
        Assertions.assertTrue(bopf.checkIfBuyIsLessThanSell());
    }
}

