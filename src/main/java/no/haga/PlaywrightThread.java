package no.haga;

import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.AriaRole;
import no.haga.models.User;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static no.haga.Helpers.getRandomNumberWithLength;

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

            var browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
            var context = browser.newContext();
            var page = context.newPage();

            var url = "https://lolo577.wpenginepowered.com/Parkin/";

            // Navigate to the URL
            page.navigate(url);


        }
    }
}