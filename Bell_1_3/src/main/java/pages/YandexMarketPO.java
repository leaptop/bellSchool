package pages;

import org.openqa.selenium.By;
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

    public void sendPriceToSpan(String str){
        Actions builder = new Actions(chromedriver);
        Action seriesOfActions = builder
                .moveToElement(priceSpanFrom)
                .click()
                // .keyDown(txtUsername, Keys.SHIFT)
                .sendKeys(priceSpanFrom, str)
                // .keyUp(txtUsername, Keys.SHIFT)
                //  .doubleClick(txtUsername)
                // .contextClick()
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
