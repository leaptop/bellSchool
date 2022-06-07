package custom.properties;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "system:env",
        "file:url.properties"
})
public interface PropsUrl extends Config {

    @Key("base.url.citylink")
    String baseURLCitylink();

    @Key("base.url.google")
    String baseURLGoogle();

}
