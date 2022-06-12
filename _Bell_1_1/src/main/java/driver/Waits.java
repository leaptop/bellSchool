package driver;

import helpers.Constants;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Waits {
    public static WebDriverWait wait = new WebDriverWait(Manager.getCurrentDriver(), Constants.DEFAULT_TIMEOUT);

    private static void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void waitElementPresents(String xpath) {
        Waits.wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
    }

    public static void waitUntilElementBeVisible(WebElement element) {
        new WebDriverWait(Manager.getCurrentDriver(), Constants.DEFAULT_TIMEOUT)
                .until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitUntilElementBeClickable(WebElement element) {
        new WebDriverWait(Manager.getCurrentDriver(), Constants.DEFAULT_TIMEOUT)
                .until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void waitUntilElementTextContains(WebElement element, String text) {
        new WebDriverWait(Manager.getCurrentDriver(), Constants.DEFAULT_TIMEOUT)
                .until(ExpectedConditions.textToBePresentInElement(element, text));
    }

    public static void waitUntilElementTextContainsByLocator(By locator, String text) {
        new WebDriverWait(Manager.getCurrentDriver(), Constants.DEFAULT_TIMEOUT)
                .until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
    }


    public static void waitUntilElementsCountWillBe(String xpath, Integer number) {
        new WebDriverWait(Manager.getCurrentDriver(), Constants.DEFAULT_TIMEOUT)
                .until(ExpectedConditions.numberOfElementsToBe(By.xpath(xpath), number));
    }

    public static void waitUntilElementNotExistByXpath(String xpath) {
        int timer = 0;
        for (; timer < Constants.DEFAULT_TIMEOUT; timer++) {
            if (Manager.getCurrentDriver().findElements(By.xpath(xpath)).size() == 0)
                break;
            sleep(1);
        }
        Assertions.assertNotEquals(timer, Constants.DEFAULT_TIMEOUT,
                "Элемент с селектором " + xpath + " не исчез за " + Constants.DEFAULT_TIMEOUT + " секунд");
    }


    public static void waitUntilAttributeWillBe(WebElement element, String attribute, String value) {
        new WebDriverWait(Manager.getCurrentDriver(), Constants.DEFAULT_TIMEOUT)
                // В левой части лямбды - driver, в правой части логическое выражение
                .until((ExpectedCondition<Boolean>) driver -> element.getAttribute(attribute).contains(value));
    }
}
