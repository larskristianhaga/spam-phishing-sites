package no.haga;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import lombok.SneakyThrows;

import static no.haga.Helpers.*;

public class Main {
    public static void main(String[] args) {
        runPlaywright();
    }

    @SneakyThrows
    private static void runPlaywright() {
        var mockUsers = getMockData();

        var playwright = Playwright.create();

        mockUsers.forEach(user -> {
            System.out.println("Starting test for user: " + user.getFullName());

            var fullName = user.getFullName();
            var address = user.getAddress();
            var postcode = user.getPostcode();
            var email = user.getEmail();
            var password = user.getPassword();
            var phoneNumber = user.getMobileNumber();
            var creditCardNumber = user.getCreditCardNumber();

            var browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
            var context = browser.newContext();
            var page = context.newPage();

            var url = "https://wordpress-brown-whale-ivarwee12838327.codeanyapp.com/index/";

            page.navigate(url);
            page.getByLabel("Skriv inn din e-postadresse *").fill(email);
            page.getByLabel("Vennligst oppgi passordet").fill(password);
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Logg på")).click();

            page.locator("input[name=\"aa\"]").fill(fullName);
            page.getByPlaceholder("ddmmååxxxxx").fill(getRandomNumber(11));
            page.getByPlaceholder("xxxxxxxx").fill(phoneNumber);
            page.getByPlaceholder("ddmmåå", new Page.GetByPlaceholderOptions().setExact(true)).fill(getRandomNumber(6));
            page.getByLabel("Bekreft").click();

            page.frameLocator("iframe[title=\"Betal med kort\"]").locator("#cardNumber").fill(creditCardNumber);
            page.frameLocator("iframe[title=\"Betal med kort\"]").getByLabel("Gyldig til (MM/ÅÅ)").fill(String.format("%s/%s", getRandomNumberBetweenRange(1, 12), getRandomNumberBetweenRange(21, 30)));
            page.frameLocator("iframe[title=\"Betal med kort\"]").getByLabel("CVC (3-4 sifre)").fill(getRandomNumber(3));
            page.frameLocator("iframe[title=\"Betal med kort\"]").getByLabel("Bekreft").click();

            page.frameLocator("iframe[title=\"Betal med kort\"]").locator("button[name=\"submit\"]").click();
            page.frameLocator("iframe[title=\"Betal med kort\"]").getByPlaceholder("********").fill(password);
            page.frameLocator("iframe[title=\"Betal med kort\"]").locator("button[name=\"submit\"]").click();
        });
    }
}