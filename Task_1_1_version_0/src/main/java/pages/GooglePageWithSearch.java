package pages;
//Если гугл вас лочит, используйте любой другой поисковик
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GooglePageWithSearch {
    private String selectorSearchItems = "//div[@class='g' and not(@data-hveid)]";
    private String selectorURL = ".//a[@href]";
    private String selectorNammePage = ".//h3";
    private String selectorDescription = ".//div[@class='IsZvec']";

    private WebDriver driver;

    private List<WebElement> searchItems = new ArrayList<>();
    private List<Map<String, Object>> collectResults = new ArrayList<>();//Object для возможности хранения разных элементов(WebElement например)

    public GooglePageWithSearch(WebDriver driver){
        this.driver = driver;
        this.searchItems = driver.findElements(By.xpath(selectorSearchItems));
    }

}
