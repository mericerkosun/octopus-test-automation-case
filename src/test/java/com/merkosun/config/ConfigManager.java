package com.merkosun.config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.ConfigFactory;

@Config.Sources("classpath:config.properties")
public interface ConfigManager extends Config {

    @Key("browser")
    String browser();

    @Key("implicit.wait")
    int implicitWait();

    @Key("page.load.timeout")
    int pageLoadTimeout();

    @Key("explicit.wait")
    int explicitWait();

    @Key("saucedemo.base.url")
    String saucedemoBaseUrl();

    @Key("parabank.base.url")
    String parabankBaseUrl();

    @Key("theinternet.base.url")
    String theinternetBaseUrl();

    @Key("theinternet.upload.url")
    String theinternetUploadUrl();

    @Key("theinternet.floating.url")
    String theinternetFloatingUrl();

    @Key("demoblaze.base.url")
    String demoblazeBaseUrl();

    static ConfigManager getInstance() {
        return ConfigFactory.create(ConfigManager.class);
    }
}
