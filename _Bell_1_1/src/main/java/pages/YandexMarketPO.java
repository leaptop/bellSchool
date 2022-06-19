package pages;

import org.openqa.selenium.WebDriver;

public class YandexMarketPO {
    public WebDriver chromedriver;
    public YandexMarketPO(WebDriver wd){
        chromedriver = wd;
    }
    public String yandexMarket_locator = "//a[contains(@href,'market.yandex')and @data-id='market']";
    public String catalogueButton = "//button[@aria-label=\"Каталог\"]";
    public String cataloguePopUpButton = "//li[@data-zone-name=\"category-link\"]";
    public String categorySelectorComputers ="//a[contains(@href,'/catalog--kompiuternaia-tekhnika')]";
    public String categorySelectorNotebooks = "//a[contains(@href,'catalog--noutbuki/')]";

}
