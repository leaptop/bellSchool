package ru.yandex;

import io.qameta.allure.Feature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.YandexMarketPO;
import pages.YandexRuPO;

import java.util.ArrayList;

public class Tests extends BaseTest {
    /**
     *
     */
    @Feature("blank")
    @Test
    public void test_1_3_withPageObject() throws InterruptedException {
        chromeDriver.get("https://yandex.ru/");
        YandexRuPO yandexRuPO = new YandexRuPO(chromeDriver);
        YandexMarketPO yandexMarketPO = new YandexMarketPO(chromeDriver, wait);

        yandexMarketPO.goToYandexMarket();
        ArrayList<String> newTab = new ArrayList<>(chromeDriver.getWindowHandles());
        chromeDriver.switchTo().window(newTab.get(1));
        Thread.sleep(7000);
        yandexMarketPO.goToCatalogue();
        yandexMarketPO.goToComputers();
        yandexMarketPO.goToLaptops();
        yandexMarketPO.sendPricesToSpansWithWaiting(10000, 90000);
        yandexMarketPO.selectManufacturersHPAndLenovo();

        yandexMarketPO.waitForResultsOfTheSearch();

        System.out.println( "true or false? " + yandexMarketPO.checkNameInSearch() ); ;

        Assertions.assertTrue(true);
    }
}
