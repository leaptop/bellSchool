package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GooglePageWithSearch {
    private String selectorSearchItems="//div[@class='g' and not(@data-hveid)]";//как-то отсеиваю лишние результаты поиска... пока непонятно, как это происходит
    private String selectorURL = ".//a[@href]";
    private String selectorNamePage = ".//h3";
    private String selectorDiscriprion = ".//div[@class='IsZvec']";

    private WebDriver driver;

    private List<WebElement> searchItems = new ArrayList<>();
    private List<Map<String,Object>> collectResults = new ArrayList<>();//Object для возможности хранения разных элементов(WebElement например)

    public GooglePageWithSearch(WebDriver driver) {
        this.driver = driver;
        this.searchItems = driver.findElements(By.xpath(selectorSearchItems));
    }


    public GooglePageWithSearch(WebDriver driver, String search) {
        this.driver = driver;
        this.driver.get("https://www.google.ru/search?q="+search);
        searchItems = driver.findElements(By.xpath(selectorSearchItems));
    }

    public List<Map<String, Object>> getCollectResults() {//Заполняем нашу коллекцию. Это чтобы удобно было обращаться с результатами поиска.
        for(WebElement result : searchItems){
            collectResults.add(Map.of(//Каждый раз добавляется новая мапа с конечным числом пар(4 штуки)
                    "WEB_ELEMENT", result,
                    "URL", result.findElement(By.xpath(selectorURL)),
                    "NAME_PAGE", result.findElement(By.xpath(selectorNamePage)).getText(),
                    "DISCRIPTION", result.findElement(By.xpath(selectorDiscriprion)).getText()
                    )
            );
        }
        return collectResults;
    }

    public boolean goPage(String namePage){//Переход на новую страницу. Клик по ссылке
        WebElement pageLink = (WebElement) collectResults.stream()
                .filter(x->x.get("NAME_PAGE").toString().contains(namePage))
                .findFirst()
                .get().get("WEB_ELEMENT");
        pageLink.findElement(By.xpath(selectorURL)).click();
        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        for (String tab : tabs){
            driver.switchTo().window(tab);
            if(driver.getTitle().contains(namePage))
                return true;
        }
        Assertions.fail("Не удалось открыть вкладку, содержащую " + namePage);
        return false;
    }

}
