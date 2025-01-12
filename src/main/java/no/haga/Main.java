package no.haga;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Playwright;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import lombok.extern.java.Log;
import no.haga.config.Config;
import no.haga.helpers.Helpers;

@Log
public class Main implements Runnable {

    private static final int NUMBER_OF_THREADS = 3;

    public static void main(String[] args) {
        log.info("Starting spam...");

        try (ExecutorService executor = Executors.newFixedThreadPool(NUMBER_OF_THREADS)) {
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                executor.submit(new Main());
            }
        }
    }

    @Override
    public void run() {
        var user = Helpers.generateFakeUser();
        try (Playwright playwright = Playwright.create()) {
            log.info("Starting test for user: " + user.getFullName());

            try (Browser browser = Helpers.getRandomBrowser(playwright).launch(Config.getLaunchOptions())) {
                BrowserContext context = browser.newContext(
                        new Browser.NewContextOptions().setDeviceScaleFactor(3)
                                .setHasTouch(true)
                                .setIsMobile(true)
                                .setUserAgent(Helpers.getRandomMobileUserAgent())
                                .setViewportSize(390, 664));

                var page = context.newPage();
            }
        }
    }
}