package no.haga;

import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.AriaRole;
import lombok.SneakyThrows;

public class Main {
    public static void main(String[] args) {
        runPlaywright();
    }

    @SneakyThrows
    private static void runPlaywright() {
        var mockUsers = Helpers.getMockData();

        var playwright = Playwright.create();

        mockUsers.forEach(user -> {
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

            var url = "";

            // Navigate to the URL
            page.navigate(url);

            // Examples on how to interact with the page
            page.frameLocator("iframe[title=\"XXX\"]").locator("button[name=\"XXX\"]").fill("XXX");
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("XXX")).fill("XXX");
            page.getByPlaceholder("XXX").fill("XXX");
            page.getByLabel("XXX").fill("XXX");
        });
    }
}