package config;

import org.aeonbits.owner.ConfigFactory;

public class ConfigManager {
    private static final ValuesConfig factory = ConfigFactory.create(ValuesConfig.class);

    public static String getApiKey() {
        return System.getProperty(factory.token());
    }

    public static String getBaseUrl() {
        return factory.baseUrl();
    }

    public static String getTokenAdmin() {
        return System.getProperty(factory.tokenAdmin());
    }

    public static String getTokenPublic() {
        return System.getProperty(factory.tokenPublic());
    }
}
