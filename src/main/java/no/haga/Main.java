package no.haga;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;
import lombok.extern.java.Log;
import no.haga.models.User;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Log
public class Main implements Runnable {

    private static final int NUMBER_OF_THREADS = 2;
    private final User user;

    public Main(User user) {
        this.user = user;
    }

    public static void main(String[] args) throws IOException {
        List<User> mockUsers = Helpers.getMockData();

        log.info("Starting spam...");

        try (ExecutorService executor = Executors.newFixedThreadPool(NUMBER_OF_THREADS)) {
            for (User user : mockUsers) {
                executor.submit(new Main(user));
            }
            executor.shutdown();
        }
    }

    @Override
    public void run() {
        try (Playwright playwright = Playwright.create()) {
            log.info("Starting test for user: " + user.getFullName());

            var fullName = user.getFullName();
            var address = user.getAddress();
            var email = user.getEmail();
            var password = user.getPassword();
            var phoneNumber = user.getMobileNumber();
            var creditCardNumber = user.getCreditCardNumber();

            var launchOptions = new BrowserType.LaunchOptions()
                    .setHeadless(false)
                    .setTimeout(30)
                    .setArgs(List.of("--start-maximized", "--window-position=0,0"));

            try (Browser browser = playwright.webkit().launch(launchOptions)) {
                BrowserContext context = browser.newContext(new Browser.NewContextOptions().setDeviceScaleFactor(3).setHasTouch(true).setIsMobile(true).setUserAgent("Mozilla/5.0 (iPhone; CPU iPhone OS 15_0 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/17.4 Mobile/15E148 Safari/604.1").setViewportSize(390, 664));

                var page = context.newPage();
            }
        }
    }
}