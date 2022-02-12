package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OpenPage {

    private String selectorExchangeRates = "//*[@class='main-page-exchange main-page-info__card']";
    private String selectorTableHeaders=".//tbody/tr[contains(@class,'header')]/td";//это де-факто лист заголовков таблицы с валютами
    private String selectorTableRows = ".//tbody/tr[contains(@class,'row')]";//лист строк

    private WebDriver driver;

    private WebElement exchangeRates;
    private List<Map<String,String>> collectExchangeRates = new ArrayList<>();

    public WebDriver getDriver() {
        return driver;
    }//Это вроде для аллюра

    public WebElement getExchangeRates() {
        return exchangeRates;
    }//Это вроде для аллюра

    public OpenPage(WebDriver driver){
        this.driver=driver;
        exchangeRates= driver.findElement(By.xpath(selectorExchangeRates));
    }

    public List<Map<String, String>> getCollectExchangeRates() {//Метод возвращает коллекцию с курсом валют
        List<WebElement> tableHeaders = exchangeRates.findElements(By.xpath(selectorTableHeaders));
        List<WebElement> tableRows = exchangeRates.findElements(By.xpath(selectorTableRows));
        for(int i= 0; i<tableRows.size();++i){
            Map<String,String> collectRow=new HashMap<>();//Создал коллекцию типа Map.
            for (int j=0;j<tableHeaders.size();++j){
                collectRow.put(//Добавляю в коллекцию ключ: название столбца, значение: ячейку в столбце (текущего ряда конечно)
                        tableHeaders.get(j).getText(),//Т.о. в итоге весь ряд сохранил в мапе, с подписью каждой ячейки по названию её столбца.
                        tableRows.get(i).findElement(By.xpath("./td["+(j+1)+"]")).getText()//td - ячейка
                );
            }
            collectExchangeRates.add(collectRow);//Добавил в коллекцию ArrayList очередную коллекцию Map<String, String>, где первая строка - название столбца
        }//вторая - текст ячейки.
        return collectExchangeRates;
    }
}
