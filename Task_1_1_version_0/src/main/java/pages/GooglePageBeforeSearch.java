package pages;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GooglePageBeforeSearch {
    protected WebDriver chromedriver;
    protected WebElement searchField;
    protected WebElement searchButton;

    public GooglePageBeforeSearch(WebDriver chromedriver){//constructor gets a browser fo work
        this.chromedriver = chromedriver;
        this.searchField = chromedriver.findElement(By.xpath("//input[@role='combobox' and @name='q']"));
        this.searchButton = chromedriver.findElement(By.xpath("//*[@type='submit' and @aria-label='Поиск в Google']"));//interesting behavior is: in before search page there is input tag, abd in after search page the input is button tag. So I had to use an asterisk
    }

    public void find(String keysFind){
        searchField.click();
        searchField.sendKeys(keysFind);
        searchButton.click();
    }


}
