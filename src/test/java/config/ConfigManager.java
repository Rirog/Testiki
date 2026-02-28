package config;

import org.aeonbits.owner.ConfigFactory;

public class ConfigManager {
    private static final ValuesConfig factory = ConfigFactory.create(ValuesConfig.class);

    static {
        try {
            System.out.println("Trying to load config...");
            System.out.println("Classpath: " + System.getProperty("java.class.path"));
            System.out.println("Resource URL: " + ConfigManager.class.getResource("/values.properties"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
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
