package no.haga.config;

import com.microsoft.playwright.BrowserType;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Config {

    public BrowserType.LaunchOptions getLaunchOptions() {
        return new BrowserType.LaunchOptions()
                .setHeadless(false)
                .setSlowMo(500)
                .setTimeout(1500);
    }
}
