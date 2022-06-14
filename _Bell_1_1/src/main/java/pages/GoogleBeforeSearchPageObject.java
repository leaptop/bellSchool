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
    /**
     * Локатор для поиска элемента для ввода текста.
     */
    public String inputForSearch_Locator = "//input[@title='Search' or @title='Поиск']";

    /**
     * Вводит текст и жмёт Enter. Не могу понять, нужно здесь ожидание ли нет.
     * @param textForSearch
     */
    public void insertTextIntoSearchFieldAndPressEnter(String textForSearch) {
        Waits.waitElementPresents(inputForSearch_Locator);
        inputForSearchWebElement = chromedriver.findElement(By.xpath(inputForSearch_Locator));
        inputForSearchWebElement.sendKeys(textForSearch + Keys.ENTER);
    }

}
