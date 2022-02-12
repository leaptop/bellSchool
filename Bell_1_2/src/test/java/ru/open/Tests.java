package ru.open;

import io.qameta.allure.Feature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.PageFactory;
import pages.PageFactoryOpenRu;

import java.util.ArrayList;

public class Tests extends BaseTest {
    @Feature("Проверка результатов поиска")
    @Test
    public void test_1_2_a_withPageFactory(){//checks if google gives "https://www.open.ru" after searching "Открытие"
        chromeDriver.get("https://www.google.com/search");
        PageFactoryOpenRu pageFactoryOpenRu = PageFactory.initElements(chromeDriver, PageFactoryOpenRu.class);//important: invoke selenium's PageFactory
        pageFactoryOpenRu.find("Открытие");
        Assertions.assertTrue(pageFactoryOpenRu.getResults().stream().anyMatch(x -> x.getText().contains("https://www.open.ru")));

        String n = Keys.chord(Keys.CONTROL, Keys.ENTER);//Ctrl + click opens a link in a new tab
        pageFactoryOpenRu.getLinkOfFoundSite().sendKeys(n);

        ArrayList<String> newTab = new ArrayList<>(chromeDriver.getWindowHandles());
        chromeDriver.switchTo().window(newTab.get(1));

        double bbu = Double.parseDouble(pageFactoryOpenRu.getBankBuysUSD().getText().replace(',','.'));
        double bsu = Double.parseDouble(pageFactoryOpenRu.getBankSellsUSD().getText().replace(',','.') );
        double bbe = Double.parseDouble(pageFactoryOpenRu.getBankBuysEUR().getText().replace(',','.') );
        double bse = Double.parseDouble(pageFactoryOpenRu.getBankSellsEUR().getText().replace(',','.') );
        Assertions.assertTrue(bbu<bsu);//checks if prices to buy are less, than prices to sell
        Assertions.assertTrue(bbe<bse);
    }
}
