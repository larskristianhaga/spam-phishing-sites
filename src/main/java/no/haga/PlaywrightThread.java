package no.haga;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;
import no.haga.models.User;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PlaywrightThread implements Runnable {

    private final User user;

    public PlaywrightThread(User user) {
        this.user = user;
    }

    public static void main(String[] args) {
        List<User> mockUsers = Helpers.getMockData();
        ExecutorService executor = Executors.newFixedThreadPool(5);
        for (User user : mockUsers) {
            executor.submit(new PlaywrightThread(user));
        }
        executor.shutdown();
    }

    @Override
    public void run() {
        try (Playwright playwright = Playwright.create()) {
            System.out.println(STR."Starting test for user: \{user.getFullName()}");

            var fullName = user.getFullName();
            var address = user.getAddress();
            var email = user.getEmail();
            var password = user.getPassword();
            var phoneNumber = user.getMobileNumber();
            var creditCardNumber = user.getCreditCardNumber();

            Browser browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));
            BrowserContext context = browser.newContext(new Browser.NewContextOptions()
                    .setDeviceScaleFactor(3)
                    .setHasTouch(true)
                    .setIsMobile(true)
                    .setUserAgent("Mozilla/5.0 (iPhone; CPU iPhone OS 15_0 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/17.4 Mobile/15E148 Safari/604.1")
                    .setViewportSize(390, 664));

        }
    }
}