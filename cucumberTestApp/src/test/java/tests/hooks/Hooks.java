package tests.hooks;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks {

    @Before(value = "@Run", order = 1)
    public void before(Scenario scenario) {
        System.out.println("before");
    }

    @Before(value = "@Run", order = 2)
    public void before2(Scenario scenario) {
        System.out.println("before");
    }

    @Before(value = "@Run", order = 3)
    public void before3(Scenario scenario) {
        System.out.println("before");
    }

    @After(value = "@Run", order = 1)
    public void after(Scenario scenario) {
        System.out.println("after");
    }

    @After(value = "@Run", order = 2)
    public void after2(Scenario scenario) {
        System.out.println("after");
    }

    @After(value = "@Run", order = 3)
    public void after3(Scenario scenario) {
        System.out.println("after");
    }

    @After
    public void getScenarioInfo(Scenario scenario) {
        System.out.println(scenario.getId());
        System.out.println(scenario.getName());
        System.out.println(scenario.getStatus());
        System.out.println(scenario.isFailed());
        System.out.println(scenario.getSourceTagNames());
    }

}
