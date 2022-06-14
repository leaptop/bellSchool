package pages;

import driver.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GoogleBeforeSearchPageObject {
    public GoogleBeforeSearchPageObject(WebDriver cr) {
        chromedriver = cr;
    }

    public WebDriver chromedriver;
    public WebElement inputForSearchWebElement;
    public String inputForSearchLocator = "//input[@title='Поиск']";

    public void initializePage() {
        // Waits/// нет. Похоже это не нужно, т.к. элементы могут перезагрузиться...
    }

    public void insertTextIntoSearchFieldAndPressEnter(String textForSearch) {
        Waits.waitElementPresents(inputForSearchLocator);
        inputForSearchWebElement = chromedriver.findElement(By.xpath(inputForSearchLocator));

        inputForSearchWebElement.sendKeys(textForSearch);
       // Waits.waitUntilElementTextContains(inputForSearchWebElement, textForSearch);
        inputForSearchWebElement.sendKeys(Keys.ENTER);
    }

}
