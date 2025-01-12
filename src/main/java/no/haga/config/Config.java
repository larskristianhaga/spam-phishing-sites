package no.haga.config;

import com.microsoft.playwright.BrowserType;
import java.util.List;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Config {

    public BrowserType.LaunchOptions getLaunchOptions() {
        return new BrowserType.LaunchOptions()
                .setHeadless(true)
                .setTimeout(30)
                .setArgs(List.of("--start-maximized", "--window-position=0,0"));
    }
}
