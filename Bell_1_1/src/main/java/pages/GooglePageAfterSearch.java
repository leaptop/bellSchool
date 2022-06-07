package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**  Can extend PageBeforeSearch if it's applicable (pages may not be the same)
 */
public class GooglePageAfterSearch extends GooglePageBeforeSearch {
    private List<WebElement> results;
    /**
     * A WebDriverWait to use for waiting))
     */
    WebDriverWait wait = new WebDriverWait(chromedriver, 120);

    public GooglePageAfterSearch(WebDriver chromedriver) {
        super(chromedriver);
    }

    /**
     * Wait no more than 120s, until the element with the selector becomes visible
     * @return
     */
    public List<WebElement> getResults(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//cite[@role=\"text\"]")));
        results=chromedriver.findElements(By.xpath("//cite[@role=\"text\"]"));
        return results;
    }
}
