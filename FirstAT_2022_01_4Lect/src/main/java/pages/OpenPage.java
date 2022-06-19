package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OpenPage {

    private String selectorExchangeRates = "//*[@class='main-page-exchange main-page-info__card']";
    private String selectorTableHeaders = ".//tbody/tr[contains(@class,'header')]/td";
    private String selectorTableRows = ".//tbody/tr[contains(@class,'row')]";

    private WebDriver driver;

    private WebElement exchangeRates;
    private List<Map<String, String>> collectExchangeRates = new ArrayList<>();

    public WebDriver getDriver() {
        return driver;
    }

    public WebElement getExchangeRates() {
        return exchangeRates;
    }

    public OpenPage(WebDriver driver) {
        this.driver = driver;
        exchangeRates = driver.findElement(By.xpath(selectorExchangeRates));
    }

    public List<Map<String, String>> getCollectExchangeRates() {
        List<WebElement> tableHeaders = exchangeRates.findElements(By.xpath(selectorTableHeaders));
        for (int i = 0; i< tableHeaders.size();i++){
            System.out.println( "tableHeaders.get(i).getText(): "+tableHeaders.get(i).getText());//пока не могу вывести русские слова
        }
        System.out.println(" tableHeaders.size(): "+ tableHeaders.size());
        List<WebElement> tableRows = exchangeRates.findElements(By.xpath(selectorTableRows));
        System.out.println(" tableRows.size(): "+ tableRows.size());
        for (int i = 0; i < tableRows.size(); ++i) {
            Map<String, String> collectRow = new HashMap<>();
            for (int j = 0; j < tableHeaders.size(); ++j) {
                collectRow.put(
                        tableHeaders.get(j).getText(),
                        tableRows.get(i).findElement(By.xpath("./td[" + (j + 1) + "]")).getText()
                );
                System.out.println( "td = "+ (j+1));
                System.out.println("tableHeaders.get(j).getText():                                                "+tableHeaders.get(j).getText() );
                System.out.println("tableRows.get(i).findElement(By.xpath(\"./td[\" + (j + 1) + \"]\")).getText():"+tableRows.get(i).findElement(By.xpath("./td[" + (j + 1) + "]")).getText());
            }
            collectExchangeRates.add(collectRow);
        }
        return collectExchangeRates;
    }
}
