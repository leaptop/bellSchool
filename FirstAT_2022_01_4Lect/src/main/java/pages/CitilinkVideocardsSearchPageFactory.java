package pages;

import driver.Manager;
import driver.Waits;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CitilinkVideocardsSearchPageFactory {
    private WebDriver driver;

    @FindBy(xpath = "//div[@class = 'ProductCardCategoryList__list']/section/div[1]//a[contains(@class, 'ProductCardHorizontal__title')]")
    protected WebElement firstVideocardName;

    @FindBy(xpath = "//div[@data-meta-name = 'FilterSeoLabelLayout' and @data-meta-value = 'RTX 3070']")
    protected WebElement rtx3070Search;

    @FindBy(xpath = "//button[@class = 'e4uhfkv0 css-1eedcpq e4mggex0']")
    protected WebElement showAll;

    public CitilinkVideocardsSearchPageFactory() {
        this.driver = Manager.getCurrentDriver();
        PageFactory.initElements(driver, this);
    }

    public void getName() {
        System.out.println(firstVideocardName.getText());
        showAll.click();
        rtx3070Search.click();
        Waits.waitUntilElementTextContains(firstVideocardName, "RTX 3070");
        System.out.println(firstVideocardName.getText());
    }
}
