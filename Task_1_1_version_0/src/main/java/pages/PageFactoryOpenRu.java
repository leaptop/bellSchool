package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;      //ЛЮБОЙ ПАТТЕРН ДОЛЖЕН БЫТЬ ИСПОЛЬЗОВАН СТРОГО, ЕСЛИ НУЖНО РЕШИТЬ СООТВТЕТСВУЮЩУЮ ЗАДАЧУ
//ПАТТЕРН - УСЛОЖНЕНИЕ КОДА! ПОЭТОМУ ПРОСТО ТАК ИХ ИСПОЛЬЗОВАТЬ НЕЛЬЗЯ.

//НИКАКИХ ДРАЙВЕРОВ НЕ ДОЛЖНО БЫТЬ. ПАПКА ALLURE-RESULTS ПОЧИЩЕНА. 2 ПРОЕКТА В АРХИВАХ RAR БЕЗ SOUT.

//A pattern PageFactory is used for situations where you want to avoid multiple pages invokes. The idea is:
//1 We don't create separate classes for each page, but one class for multiple pages
//2 Selectors are written right away
//3 Search for elements at the same time with a need of calling them
//You shouldn't confuse functionality of selenium. ... .PageFactory with the pattern of the same name.
public class PageFactoryOpenRu {

    private WebDriver chromeDriver;

    @FindBy(how = How.XPATH, xpath = "//input[@role='combobox' and @name='q']")//assigned searchfield via annotation
    WebElement searchField;

    @FindBy(how = How.XPATH, xpath = "//*[@type='submit' and @aria-label='Поиск в Google']")
    WebElement searchButton;

    @FindBy(how = How.XPATH, xpath = "//cite[@role=\"text\"]")
    List<WebElement> results;

    @FindBy(how = How.XPATH, xpath = "//table[@class='main-page-exchange__table main-page-exchange__table--online']/tbody/tr[2]/td[2]//span[@class='main-page-exchange__rate']")
    WebElement bankBuysUSD;

    @FindBy(how = How.XPATH, xpath = "//table[@class='main-page-exchange__table main-page-exchange__table--online']/tbody/tr[2]/td[4]//span[@class='main-page-exchange__rate']")
    WebElement bankSellsUSD;

    @FindBy(how = How.XPATH, xpath = "//table[@class='main-page-exchange__table main-page-exchange__table--online']/tbody/tr[3]/td[2]//span[@class='main-page-exchange__rate']")
    WebElement bankBuysEUR;

    @FindBy(how = How.XPATH, xpath = "//table[@class='main-page-exchange__table main-page-exchange__table--online']/tbody/tr[3]/td[4]//span[@class='main-page-exchange__rate']")
    WebElement bankSellsEUR;

    public PageFactoryOpenRu(WebDriver chromeDriver){
        this.chromeDriver=chromeDriver;
    }

    public WebElement getBankBuysUSD() {
        return bankBuysUSD;
    }

    public WebElement getBankSellsUSD() {
        return bankSellsUSD;
    }

    public WebElement getBankBuysEUR() {
        return bankBuysEUR;
    }

    public WebElement getBankSellsEUR() {
        return bankSellsEUR;
    }

    public List<WebElement> getResults() {
        return results;
    }
    public void find(String keysFind){
        searchField.click();
        searchField.sendKeys(keysFind);
        searchButton.click();
    }
}
