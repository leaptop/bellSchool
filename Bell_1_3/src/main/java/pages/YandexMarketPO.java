package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

public class YandexMarketPO {
    WebDriver chromedriver;

    public YandexMarketPO(WebDriver cd) {
        chromedriver = cd;
    }

    WebElement priceSpanFrom;
    WebElement priceSpanTo;
    WebElement showAllManufacturersButton;

    public WebElement getShowAllManufacturersButton(){
        return showAllManufacturersButton = chromedriver.findElement(By.xpath("//span[text()=\"Производитель\"]"));
    }

    public void dounbleClickOnManufacturer(){
        Actions builder = new Actions(chromedriver);

        builder.doubleClick(showAllManufacturersButton).perform();
    }
    public void sendPricesToSpans(int from, int to){
        Actions builder = new Actions(chromedriver);
        Action seriesOfActions = builder
                .moveToElement(priceSpanFrom)
                .sendKeys(priceSpanFrom, Integer.toString(from))
                .sendKeys(Keys.TAB)
                .sendKeys(Integer.toString(to))
              //  .sendKeys(Keys.ENTER)
                .build();

        seriesOfActions.perform() ;
    }

    public WebElement getPriceSpanFrom(){
        return priceSpanFrom = chromedriver.findElement(By.xpath("//span [@data-auto=\"filter-range-min\"]"));
    }
    public WebElement getLaptopsWebElement(){
        return chromedriver.findElement(By.linkText("Ноутбуки"));
    }
    public WebElement getComputersWebElement(){
        return chromedriver.findElement(By.xpath("//img[@alt=\"Компьютеры\"]"));
    }

    public WebElement getCatalogueButton() {
        // return chromedriver.findElement(By.linkText("Каталог"));//
        return chromedriver.findElement(By.xpath("//button [@id=\"catalogPopupButton\"]"));
        //return chromedriver.findElement(By.xpath("//a"));
    }
}
