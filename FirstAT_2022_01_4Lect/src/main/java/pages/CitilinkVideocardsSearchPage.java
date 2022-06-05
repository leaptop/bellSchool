package pages;

import driver.Manager;
import driver.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CitilinkVideocardsSearchPage {
    private WebDriver driver;
    protected String firstVideocardNameLocator =
            "//div[@class = 'ProductCardCategoryList__list']/section/div[@data-index = '1']//a[contains(@class, 'ProductCardHorizontal__title')]";
    protected String rtx3070SearchLocator = "//div[@data-meta-name = 'FilterSeoLabelLayout' and @data-meta-value = 'RTX 3070']";
    protected String showAllLocator = "//div[@data-meta-name = 'FilterSeoLayout']//button[span[contains(., 'Показать все')]]";


    public CitilinkVideocardsSearchPage() {
        this.driver = Manager.getCurrentDriver();
    }

    public void getName() {
        WebElement firstVideocardName = driver.findElement(By.xpath(firstVideocardNameLocator));
        System.out.println(firstVideocardName.getText());
        driver.findElement(By.xpath(showAllLocator)).click();
        driver.findElement(By.xpath(rtx3070SearchLocator)).click();
        Waits.waitUntilElementNotExistByXpath(firstVideocardNameLocator);
        Waits.waitUntilElementTextContainsByLocator(By.xpath(firstVideocardNameLocator), "RTX 3070");
        firstVideocardName = driver.findElement(By.xpath(firstVideocardNameLocator));
        System.out.println(firstVideocardName.getText());
    }
}
