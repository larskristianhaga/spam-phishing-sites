package no.haga;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Playwright;
import lombok.extern.java.Log;
import no.haga.config.Config;
import no.haga.helpers.Helpers;

import static no.haga.CopyPaste.copyPasteCode;

@Log
public class Main implements Runnable {

    private static final int NUMBER_OF_THREADS = 3;

    public static void main(String[] args) {
        log.info("Starting program...");

        for (int i = 0; i < NUMBER_OF_THREADS; i++) {
            log.info("Starting new thread");
            Main obj = new Main();
            Thread thread = new Thread(obj);
            thread.start();

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                log.error("Thread sleep interrupted", e);
            }
        }
    }

    @Override
    @SuppressWarnings("InfiniteLoopStatement")
    public void run() {
        log.info("Running in a new thread");
        while (true) {
            generateBrowserAndExecute();
        }
    }

    private void generateBrowserAndExecute() {
        var user = Helpers.generateFakeUser();
        try (Playwright playwright = Playwright.create()) {
            log.info("user: " + user);

            try (Browser browser = Helpers.getRandomBrowser(playwright).launch(Config.getLaunchOptions())) {
                BrowserContext context = browser.newContext(new Browser.NewContextOptions()
                        .setUserAgent(Helpers.getRandomMobileUserAgent())
                        .setViewportSize(390, 664));

                var page = context.newPage();

                copyPasteCode(page, user);
            }
        }
    }
}