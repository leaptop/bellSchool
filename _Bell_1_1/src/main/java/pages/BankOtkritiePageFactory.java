package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BankOtkritiePageFactory {
    public BankOtkritiePageFactory(WebDriver chromeDriver) {
        this.chromeDriver = chromeDriver;
    }

    private WebDriver chromeDriver;

    public WebElement getLinkOfFoundSite() {
        return linkOfFoundSite;
    }

    @FindBy(how = How.XPATH, xpath = "//div[@class='main-page-exchange main-page-info__card']")
    WebElement rootExchange;

    @FindBy(how = How.PARTIAL_LINK_TEXT, partialLinkText = "www.open.ru")
    WebElement linkOfFoundSite;
    /**
     * Assigned searchfield via annotation.
     */

    @FindBy(how = How.XPATH, xpath = "//input[@title='Search' or @title='Поиск']")
    WebElement searchFieldSecondOption;
    @FindBy(how = How.XPATH, xpath = "//input[@role='combobox' and @name='q']")
    WebElement searchField;
    @FindBy(how = How.XPATH, xpath = "//div[not(@jsname)]/center/input[@name='btnK']")
    WebElement searchButton;
    //@FindBy(how = How.XPATH, xpath = "//*[@type='submit' and @aria-label='Поиск в Google']")
    //WebElement searchButton;
    @FindBy(how = How.XPATH, xpath = "//cite[@role=\"text\"]")
    List<WebElement> results;
    /**
     * String 0 : имя валюты; String 1 : продажа; String 2 : покупка
     */
    Map<String, Map<String, String>> currencyTable;
    public String tableLocator = "//*[@class=\"main-page-exchange__table main-page-exchange__table--online\"]";
    //public String headersLocator = "./class=\"main-page-exchange__table-header\"";
    /**
     * Ячейки строки с заголовками.
     * Их достаточно, чтобы вызвать getText() несмотря на то, что текст в следующем элементе (span).
     */
    public String cellsWithHeaders = "//tr[contains(@class, 'header')]/td/span";//[not(contains(@class, 'desktop'))]";// /span[contains(@class, 'desktop')]
    public String cellsWithData = "";
    public String cellWithCurrencyName = "//span[contains(@class,'currency-name')]";
    public String cellWithCurrencyValue = "//span[contains(@class, 'exchange__rate')]";
    private String selectorTableHeaders = ".//tbody/tr[contains(@class,'header')]/td";
    private String selectorTableRows = ".//tbody/tr[contains(@class,'row')]";
    public String columnsOfARow = "//span[contains(@class, 'main-page-exchange')]";//Это можно применть к Вебэлемету таблицы

    /**
     * Особенность этого метода в том, что нужно одновременно обращаться и к массивам джавы и к элементам DOM-дерева.
     * Первые начинаются с нулевого идекса, вторые с первого. Поэтому можно запутаться.
     *
     * @throws InterruptedException
     */
    public void fillCurrencyTable() throws InterruptedException {
        //WebElement we = chromeDriver.findElement(By.xpath(tableLocator));
        List<WebElement> rows = chromeDriver.findElements(By.xpath(selectorTableRows));
        System.out.println("rows.size: " + rows.size());
        for (int i = 0; i < rows.size(); i++) {
            System.out.println("rows.get(i): " + rows.get(i).getText());
        }
        List<WebElement> headers = chromeDriver.findElements(By.xpath(selectorTableHeaders));
        //Thread.sleep(1000000);
        System.out.println("headers.size() : " + headers.size());
        for (int i = 0; i < headers.size(); i++) {
            System.out.println("headers.get(i).getText(): " + headers.get(i).getText());
        }
        currencyTable = new HashMap<>();
        System.out.println("\nВо время укладки");
        for (int i = 0; i < rows.size(); ++i) {
            HashMap<String, String> hm = new HashMap<>();
            for (int j = 0; j < headers.size(); ++j) {
                hm.put(headers.get(j).getText(),
                        rows.get(i).findElement(By.xpath("./td[" + (j + 1) + "]")).getText());

                System.out.println("Стринга хедера:               " + headers.get(j).getText());
                System.out.println("Стринга значения под хедером: "
                        + rows.get(i).findElement(By.xpath("./td[" + (j + 1) + "]")).getText());
            }
            currencyTable.put(rows.get(i).findElement(By.xpath("./td[1]")).getText(), hm);
            System.out.println("Стринга валюты верхней мапы : " + rows.get(i).findElement(By.xpath("./td[1]")).getText() + "\n");
        }
        System.out.println("Конец укладки");
    }

    /**
     * Проверяем, что цена покупки валюты ниже цены продажи.
     * @return true если покупка меньше продажи.
     */
    public boolean checkIfBuyIsLessThanSell() {
        Double sell = 0.0, buy = 0.0;
        System.out.println();
        for (Map.Entry<String, Map<String, String>> entry : currencyTable.entrySet()) {
            for (Map.Entry<String, String> entryInner : entry.getValue().entrySet()) {
                if ((entryInner.getKey()==(null))) {
                    System.out.println("continued");
                    continue;
                }
                System.out.println( "entryInner.getKey(): "+entryInner.getKey());
                if (entryInner.getKey().equals("Банк продает")) {
                    String s = entryInner.getValue().replace(',','.');
                    sell = Double.valueOf(s);
                    System.out.println("sell was assigned " + sell);
                }
                if (entryInner.getKey().equals("Банк покупает")) {
                    String s = entryInner.getValue().replace(',','.');
                    buy = Double.valueOf(s);
                    System.out.println("buy was assigned " + buy);
                }
            }
            System.out.println("sell - buy = " + (sell - buy));
            if ((sell - buy) <= 0) return false;
        }
        return true;
    }
    public List<WebElement> getResults() {
        return results;
    }

    public void find(String keysFind) {
        searchField.click();
        searchField.sendKeys(keysFind);
        // Waits.waitUntilElementBeClickable(searchButton);
        // searchButton.click();
        searchField.sendKeys(Keys.ENTER);
    }
}
