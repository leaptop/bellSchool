package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Selenium Page Object для открытия сайта яндекса и нахождения ссылки на яндекс маркет на нём.
 */
public class YandexRuPO {
    protected WebDriver chromedriver;

  public  YandexRuPO(WebDriver cd) {
        chromedriver = cd;
    }

    WebElement yandexMarketButton;

   public WebElement getYandexMarketButton() {
       // return yandexMarketButton = chromedriver.findElement(By.linkText("Маркет"));
       return yandexMarketButton = chromedriver.findElement((By.xpath
               ("//a [@class=\"home-link services-new__item services-new__item_search_yes\"]")));
    }
}
