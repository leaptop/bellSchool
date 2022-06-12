package pages;

import net.bytebuddy.description.ByteCodeElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class YandexMarketPO {
    WebDriver chromedriver;
    WebDriverWait wait;

    public YandexMarketPO(WebDriver cd, WebDriverWait ww) {
        chromedriver = cd;
        wait = ww;
    }

    WebElement priceSpanFrom;
    WebElement priceSpanTo;
    WebElement showAllManufacturersButton;
    protected String yandexMarketLocator = "//a [@class=\"home-link services-new__item services-new__item_search_yes\"]";
    protected String yandexMarketCatalogue = "//button [@id=\"catalogPopupButton\"]";
    protected String ymComputersLocator = "//img[@alt=\"Компьютеры\"]";
    protected String laptops = "Ноутбуки";
    protected String priceSpanFromXpathLocator = "//span [@data-auto=\"filter-range-min\"]";
    protected String priceSpanToXpathLocator = "//span [@data-auto=\"filter-range-max\"]";
    protected String allManufacturersLocator = "//div[contains(@data-zone-data,\"Производитель\")]//span[contains(text(),\"Показать всё\")]";
    protected String firstResultNameLocator = "//div[@data-index=\"0\"]//h3[@data-zone-name=\"title\"]/a/span";
    protected String InputForSearchLocator = "//input[@id=\"header-search\"]";
    protected String searchButtonLocator = "//button[@data-r=\"search-button\"]";
    WebElement input;
    WebElement searchButton;

    public boolean checkNameInSearch() throws InterruptedException {
        //Thread.sleep(10000);
        String result0 = chromedriver.findElement(By.xpath(firstResultNameLocator)).getText();
        //wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(InputForSearchLocator)));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(InputForSearchLocator)));
        input = chromedriver.findElement(By.xpath(InputForSearchLocator));
        input.sendKeys(result0);
        searchButton = chromedriver.findElement(By.xpath(searchButtonLocator));
        searchButton.click();
        waitForResultsOfTheSearch();

        return result0.equals(chromedriver.findElement(By.xpath(firstResultNameLocator)).getText());
    }

    public void waitForResultsOfTheSearch() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/")));//By.xpath("//article[@data-autotest-id=\"product-snippet\"]")));
        System.out.println("Все элементы article стали visible");
    }

    public void selectManufacturersHPAndLenovo() {

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(allManufacturersLocator)));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(allManufacturersLocator)));
        showAllManufacturersButton = chromedriver.findElement(By.xpath(allManufacturersLocator));
        showAllManufacturersButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//span [text()='" + "HP" + "'][last()]")));
        getManufacturerComboBox("HP").click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//span [text()='" + "Lenovo" + "'][last()]")));
        getManufacturerComboBox("Lenovo").click();

    }

    public void sendPricesToSpansWithWaiting(int from, int to) throws InterruptedException {
        //wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(priceSpanFromXpathLocator)));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(priceSpanFromXpathLocator)));
        priceSpanFrom = chromedriver.findElement(By.xpath(priceSpanFromXpathLocator));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(priceSpanFromXpathLocator)));
        do {
            Actions builder = new Actions(chromedriver);
            Action seriesOfActions = builder
                    .moveToElement(priceSpanFrom)
                    .sendKeys(priceSpanFrom, Integer.toString(from))
                    .build();
            seriesOfActions.perform();
            wait.until(ExpectedConditions.visibilityOf(priceSpanFrom));
            System.out.println("priceSpanFrom.getText() = " + priceSpanFrom.getText());
        } while (priceSpanFrom.getText().equals("10000"));
       // wait.until(ExpectedConditions.)
       // wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(priceSpanToXpathLocator)));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(priceSpanToXpathLocator)));
        priceSpanTo = chromedriver.findElement(By.xpath(priceSpanToXpathLocator));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(priceSpanToXpathLocator)));
        do {
            Actions builder2 = new Actions(chromedriver);
            Action seriesOfActions2 = builder2
                    .moveToElement(priceSpanTo)
                    .sendKeys(priceSpanTo, Integer.toString(to))
                    .build();
            seriesOfActions2.perform();
            wait.until(ExpectedConditions.visibilityOf(priceSpanTo));
            System.out.println("priceSpanTo.getText() = " + priceSpanTo.getText());
            // System.out.println( " = " + );
        } while (priceSpanTo.getText().equals("90000"));
        System.out.println("priceSpanFrom.getText().equals(\"10000\") = " + priceSpanFrom.getText().equals("10000"));
        System.out.println("priceSpanTo.getText().equals(\"90000\") = " + priceSpanTo.getText().equals("90000"));
    }

    public void sendPricesToSpans(int from, int to) {//REWRITE!
        Actions builder = new Actions(chromedriver);
        Action seriesOfActions = builder
                .moveToElement(priceSpanFrom)
                .sendKeys(priceSpanFrom, Integer.toString(from))
                .sendKeys(Keys.TAB)
                .sendKeys(Integer.toString(to))
                //  .sendKeys(Keys.ENTER)
                .build();
        seriesOfActions.perform();
    }

    public void goToLaptops() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Ноутбуки")));
        chromedriver.findElement(By.linkText(laptops)).click();
    }

    public void goToComputers() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(ymComputersLocator)));
        chromedriver.findElement((By.xpath(ymComputersLocator))).click();
    }

    public void goToYandexMarket() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(yandexMarketLocator)));
        chromedriver.findElement(By.xpath(yandexMarketLocator)).click();
    }

    public void goToCatalogue() {
        wait.until((ExpectedConditions.presenceOfElementLocated(By.xpath(yandexMarketCatalogue))));
        getCatalogueButton().click();
    }

    public WebElement getManufacturerComboBox(String manufacturer) {
        return chromedriver.findElement(By.xpath("//span [text()='" + manufacturer + "'][last()]"));
    }

    public WebElement getShowAllManufacturersButton() {
        return showAllManufacturersButton = chromedriver.findElement(By.xpath(
                "//div[contains(@data-zone-data,\"Производитель\")]//span[contains(text(),\"Показать всё\")]"));
    }

    public void dounbleClickOnManufacturer() {
        Actions builder = new Actions(chromedriver);

        builder.doubleClick(showAllManufacturersButton).perform();
    }


    public WebElement getPriceSpanFrom() {
        return priceSpanFrom = chromedriver.findElement(By.xpath("//span [@data-auto=\"filter-range-min\"]"));
    }

    public WebElement getLaptopsWebElement() {
        return chromedriver.findElement(By.linkText("Ноутбуки"));
    }

    public WebElement getComputersWebElement() {
        return chromedriver.findElement(By.xpath("//img[@alt=\"Компьютеры\"]"));
    }

    public WebElement getCatalogueButton() {
        return chromedriver.findElement(By.xpath("//button [@id=\"catalogPopupButton\"]"));
    }
}
