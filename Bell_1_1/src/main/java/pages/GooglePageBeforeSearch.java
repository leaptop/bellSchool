package pages;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GooglePageBeforeSearch {
    protected WebDriver chromedriver;
    protected WebElement searchField;
    protected WebElement searchButton;

    /** Constructor gets a browser for work
     *
     * @param chromedriver getting a driver to use a web browser and search for web elements on it.
     * Interesting behavior is: in before search page there is input tag, and in after search
     * page the input is button tag. So I had to use an asterisk
     */
    public GooglePageBeforeSearch(WebDriver chromedriver){
        this.chromedriver = chromedriver;
        this.searchField = chromedriver.findElement(By.xpath("//input[@role='combobox' and @name='q']"));
        this.searchButton = chromedriver.findElement(By.xpath("//*[@type='submit' and @aria-label='Поиск в Google']"));    }

    /**
     * Uses found on a page searchField and searchButton to insert the keys to find
     * @param keysFind a string to find, using our search web elements
     */
    public void find(String keysFind){
        searchField.click();
        searchField.sendKeys(keysFind);
        searchButton.click();
    }


}
