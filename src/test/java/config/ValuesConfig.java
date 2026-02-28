package config;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:values.properties"})
public interface ValuesConfig extends Config {
    @Key("base.url")
    String baseUrl();

    @Key("api.token")
    String token();

    @Key("api.token.admin")
    String tokenAdmin();

    @Key("api.token.public")
    String tokenPublic();
}
