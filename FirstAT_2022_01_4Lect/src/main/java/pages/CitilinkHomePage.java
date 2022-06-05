package pages;

import driver.Manager;
import driver.Waits;
import helpers.Actions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CitilinkHomePage {
    private WebDriver driver;
    protected String itemsCatalogButtonLocator = "//button[@data-label = 'Каталог товаров']";

    public CitilinkHomePage() {
        this.driver = Manager.getCurrentDriver();
    }

    /**
     *
     * @param category - Имя категории, на которую наводим для того, чтобы появилось всплывающее окошко
     * @param chapterSubCategory - имя подкатегории товаров, выделенное жирным шрифтом
     * @param subCategory - имя типа товаров, на поиск которых мы хотим перейти
     */
    public void goMenu(String category, String chapterSubCategory, String subCategory){
        String categorySelector = "//div[contains(@class,'CatalogMenu__left')]//a[contains(@class,'CatalogMenu') and contains(.,'"+category+"')]";
        String subCatalogSelector = "//div[contains(@class,'CatalogMenu__right')]//div[contains(@class,'items') and ./div[@data-title='"+chapterSubCategory+"']]//div[contains(@class,'item') and contains(.,'"+subCategory+"')]";
        String checkGoMenu = "//*[contains(@class,'Heading Heading_level_1 Subcategory') and contains(text(),'"+subCategory+"')]";
        Manager.getCurrentDriver().findElement(By.xpath(itemsCatalogButtonLocator)).click();
        Actions.hover.accept(By.xpath(categorySelector));
        Actions.hover.accept(By.xpath(subCatalogSelector));
        Waits.waitElementPresents(subCatalogSelector);
        Manager.getCurrentDriver().findElement(By.xpath(subCatalogSelector)).click();
        Waits.waitElementPresents(checkGoMenu);
    }
}
