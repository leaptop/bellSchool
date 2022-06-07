package custom.properties;

import org.aeonbits.owner.ConfigFactory;

public class TestData {
    public static PropsUrl propsUrl = ConfigFactory.create(PropsUrl.class);
    public static PropsDriver propsDriver = ConfigFactory.create(PropsDriver.class);
}
