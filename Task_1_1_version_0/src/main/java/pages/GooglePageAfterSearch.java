package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class GooglePageAfterSearch extends GooglePageBeforeSearch {//can extend PageBeforeSearch if it's applicable(pages may not be the same)

    private List<WebElement> results;

    WebDriverWait wait = new WebDriverWait(chromedriver, 120);

    public GooglePageAfterSearch(WebDriver chromedriver) {
        super(chromedriver);
    }

    public List<WebElement> getResults(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//cite[@role=\"text\"]")));//wait no more than 120s, until the element with the selector becomes visible
        results=chromedriver.findElements(By.xpath("//cite[@role=\"text\"]"));
        return results;
    }
}
