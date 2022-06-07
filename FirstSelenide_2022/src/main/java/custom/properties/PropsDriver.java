package custom.properties;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "system:env",
        "file:src.main.java.resources.driver.properties"
})
public interface PropsDriver extends Config {

    @Key("default.timeout")
    int defaultTimeout();

    @Key("address.chrome.driver")
    String addressChromeDriver();

}
