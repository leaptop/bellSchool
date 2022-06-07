package ru.yandex;

import io.qameta.allure.Feature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import pages.YandexMarketPO;
import pages.YandexRuPO;

import java.util.ArrayList;

public class Tests extends BaseTest {
    /**
     *
     */
    @Feature("blank")
    @Test
    public void test_1_3_withPageFactory() throws InterruptedException {
        chromeDriver.get("https://yandex.ru/");

        YandexRuPO yandexRuPO = new YandexRuPO(chromeDriver);
        yandexRuPO.getYandexMarketButton().click();
        YandexMarketPO yandexMarketPO = new YandexMarketPO(chromeDriver);
       // Thread.sleep(5000);
        ArrayList<String> newTab = new ArrayList<>(chromeDriver.getWindowHandles());
        chromeDriver.switchTo().window(newTab.get(1));
        yandexMarketPO.getCatalogueButton().click();
        yandexMarketPO.getComputersWebElement().click();
        yandexMarketPO.getLaptopsWebElement().click();
        System.out.println("yandexMarketPO.getPriceLabelFrom().getText() : "
                +yandexMarketPO.getPriceSpanFrom().getText() );
       // yandexMarketPO.getPriceSpanFrom().click();
      //  String sringForLowerPrice = Keys.chord("10000");

        yandexMarketPO.sendPriceToSpan("10000");



        //yandexMarketPO.getPriceSpanFrom().sendKeys("10000");
        //yandexMarketPO.getPriceSpanFrom().sendKeys("10000");

        //chromeDriver.get("https://yandex.ru/");
      //  PageFactoryYandexRu pageFactoryYandexRu = PageFactory.initElements(chromeDriver, PageFactoryYandexRu.class);

       // String n = Keys.chord(Keys.ENTER);
      //  pageFactoryYandexRu.getLinkOfYandexMarket().sendKeys(n);
        //System.out.println("жду 5000 миллисекунд");
       // Thread.sleep(5000);
       // System.out.println("    5000 миллисекунд прошло");

       // pageFactoryYandexRu.getLinkOfCatalogue().sendKeys(n);
     //   pageFactoryYandexRu.getLinkOfYandexMarket().click();
      //  Thread.sleep(5000);
       // pageFactoryYandexRu = PageFactory.initElements(chromeDriver, PageFactoryYandexRu.class);
       // Thread.sleep(5000);
      //  pageFactoryYandexRu.getLinkOfCatalogue().click();

       // ArrayList<String> newTab = new ArrayList<>(chromeDriver.getWindowHandles());
       // chromeDriver.switchTo().window(newTab.get(1));

        Assertions.assertTrue(false);
    }
}
