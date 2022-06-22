package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class YandexMarketNotebooksPO {
    public YandexMarketNotebooksPO(WebDriver we){
        chromedriver = we;
    }
    public WebDriver chromedriver;
    public String searchButton_locator = "//button/span[contains(text(),'Найти')]/..";
    public String searchField_locator = "//input[@id='header-search']";
    public String upperRotatingProgressBar = "//div[@data-grabber=\"SearchSerp\"]//span[@aria-label=\"Загрузка...\"and @role=\"progressbar\"]";
    public String textFieldPriceMin = "//div[@data-auto=\"filter-range-glprice\"]//span[@data-auto=\"filter-range-min\"]//input";
    public String textFieldPriceMax = "//div[@data-auto=\"filter-range-glprice\"]//span[@data-auto=\"filter-range-max\"]//input";
    public String showAllManufacturersButton = "//div[@data-filter-type=\"enum\"]//*[contains(text(),'Показать всё')]";
    public String  comboboxManufacturerExample = "//div[@data-filter-type=\"enum\"]//span[contains(text(),'Xiaomi')]";
    public String getComboBoxManufacturer_locator(String manufacturer){
        return "//div[@data-filter-type=\"enum\"]//span[contains(text(),'"+manufacturer+"')]";
    }
    public String theSeventhSearchResult = "//main[@aria-label=\"Результаты поиска\"]//div[@data-index=\"7\"]";
    public String getCertainSearchResult_locator(int num){
        return "//main[@aria-label=\"Результаты поиска\"]//div[@data-index=\""+num+"\"]";
    }
    public String some_locator = "//div[@data-grabber=\"SearchSerp\"]";
    public String linkOfTheFirstSearchResult_locator = "//main[@aria-label=\"Результаты поиска\"]//div[@data-index=\"0\"]//a/span/..";
    public String getLinkOfSearchResult(int resultNumber){
        return "//main[@aria-label=\"Результаты поиска\"]//div[@data-index=\""+resultNumber+"\"]//a/span/..";
    }
    public String getLinkTextOfSearchResult(int resultNumber){
        return "//main[@aria-label=\"Результаты поиска\"]//div[@data-index=\""+resultNumber+"\"]//a/span";
    }
    public String getTitleAttributeOfLinkOfResultNumber(int resultNumber){
        return chromedriver.findElement(By.xpath(getLinkOfSearchResult(resultNumber))).getAttribute("title");
    }
}
