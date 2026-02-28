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

    @Key("testdata.path")
    @DefaultValue("src/test/resources/testdata/")
    String testDataPath();

    @Key("saucedemo.base.url")
    String saucedemoBaseUrl();

    @Key("saucedemo.username")
    String saucedemoUsername();

    @Key("saucedemo.password")
    String saucedemoPassword();

    @Key("saucedemo.locked.username")
    String saucedemoLockedUsername();

    @Key("parabank.base.url")
    String parabankBaseUrl();

    @Key("parabank.duplicate.username")
    String parabankDuplicateUsername();

    @Key("theinternet.base.url")
    String theinternetBaseUrl();

    @Key("theinternet.upload.url")
    String theinternetUploadUrl();

    @Key("theinternet.floating.url")
    String theinternetFloatingUrl();

    @Key("demoblaze.base.url")
    String demoblazeBaseUrl();

    @Key("uitap.base.url")
    String uitapBaseUrl();

    @Key("uitap.long.wait")
    int uitapLongWait();

    @Key("uitap.ajax.path")
    String uitapAjaxPath();

    @Key("uitap.shadow.path")
    String uitapShadowPath();

    @Key("uitap.overlapped.path")
    String uitapOverlappedPath();

    @Key("uitap.dynamicid.path")
    String uitapDynamicIdPath();

    @Key("uitap.clientdelay.path")
    String uitapClientDelayPath();

    static ConfigManager getInstance() {
        return ConfigFactory.create(ConfigManager.class);
    }
}
