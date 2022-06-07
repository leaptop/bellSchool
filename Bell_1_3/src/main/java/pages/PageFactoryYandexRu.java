package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;
/**
 * PageFactory здесь, вероятно, не работает, т.к. все эти элементы создаются один раз при вызове
 * PageFactoryYandexRu pageFactoryYandexRu = PageFactory.initElements(chromeDriver, PageFactoryYandexRu.class);
 * Поэтому надо пробовать делать с Page Object.
 */
public class PageFactoryYandexRu {

  /*public boolean waitUntilElementBeVisible(WebElement we){
      if(isVisible(we))return;
  }
*/
    private WebDriver chromeDriver;

    public WebElement getLinkOfYandexMarket() {
        return linkOfYandexMarket;
    }

    @FindBy(how = How.PARTIAL_LINK_TEXT, linkText = "Маркет")// partialLinkText = "Маркет")
    WebElement linkOfYandexMarket;


    public WebElement getLinkOfCatalogue() {
        return linkOfCatalogue;
    }

   // @FindBy(how = How.XPATH, xpath = "//*[@id=\"catalogPopupButton\"]") ////*[@id="catalogPopupButton"]
  //  @FindBy(how = How.ID, id = "catalogPopupButton")
   //@FindBy(how = How.XPATH, xpath = "//*[@id=""+"catalogPopupButton"+""]")
   //@FindBy(how = How.XPATH, xpath = "//*[@data-apiary-widget-name=\"@MarketNode/HeaderCatalogEntrypoint\"]" )//data-apiary-widget-name
    //@FindBy(how = How.PARTIAL_LINK_TEXT, linkText = "Каталог")//button [@id="catalogPopupButton"]
    @FindBy(how = How.XPATH, xpath = "//button [@id=\"catalogPopupButton\"]")
    WebElement linkOfCatalogue;


    /**
     * Assigned searchfield via annotation.
     */
    @FindBy(how = How.XPATH, xpath = "//input[@role='combobox' and @name='q']")
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

    public PageFactoryYandexRu(WebDriver chromeDriver){
        this.chromeDriver=chromeDriver;
    }

    /**
     * @return a web element with the information about the price of buying US dollars for our bank (open.ru)
     */
  /*  public WebElement getBankBuysUSD() {
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

   */
    public void find(String keysFind){
        searchField.click();
        searchField.sendKeys(keysFind);
        searchButton.click();
    }
}
