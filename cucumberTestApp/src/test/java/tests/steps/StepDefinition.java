package tests.steps;

import com.example.Steps;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;

import java.util.List;
import java.util.Map;

public class StepDefinition extends Steps {

    @Given("открыть браузер")
    public void openBrowser() {
        openBrowserStep();
    }

    @When("выполнен переход к следующей странице {string}")
    public void goToSelectedPage(String url) {
        goToSelectedPageStep(url);
    }

    @Then("заголовок страницы равен следующему тексту {string}")
    public void checkPageTitle(String title) {
        checkIsPageTitleEqualsStep(title);
    }

    @And("закрыть браузер")
    public void closeBrowser() {
        closeBrowserStep();
    }

    @When("распечатать list:")
    public void printList(List<String> list) {
        list.forEach(System.out::println);
    }

    @When("распечатать map:")
    public void printMap(Map<String, String> map) {
        map.forEach((key, value) -> {
            System.out.println("ключ " + key);
            System.out.println("значение " + value);
        });
    }

    @When("распечатать dataTable list:")
    public void printDataTableList(DataTable dataTable) {
        dataTable.asList().forEach(System.out::println);
    }

    @When("распечатать dataTable map:")
    public void printDataTableMap(DataTable dataTable) {
        dataTable.asMap(String.class, String.class).forEach((key, value) -> {
            System.out.println("ключ " + key);
            System.out.println("значение " + value);
        });
    }

    @When("распечатать таблицу:")
    public void printTable(DataTable dataTable) {
        List<Map<String, String>> table = dataTable.asMaps(String.class, String.class);
        table.forEach(map -> {
            System.out.println("firstCollum: " + map.get("firstCollum"));
            System.out.println("secondCollum: " + map.get("secondCollum"));
            System.out.println("thirdCollum: " + map.get("thirdCollum"));
            System.out.println("fourCollum: " + map.get("fourCollum"));
        });
    }

}
