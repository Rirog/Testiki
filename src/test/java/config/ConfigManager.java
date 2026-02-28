package config;

import org.aeonbits.owner.ConfigFactory;

public class ConfigManager {
    private final ValuesConfig factory = ConfigFactory.create(ValuesConfig.class);

    public String getApiKey() {
        return System.getProperty(factory.token());
    }

    public String getBaseUrl() {
        return factory.baseUrl();
    }

    public String getTokenAdmin() {
        return System.getProperty(factory.tokenAdmin());
    }

    public String getTokenPublic() {
        return System.getProperty(factory.tokenPublic());
    }
}
