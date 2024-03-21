package no.haga;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import lombok.SneakyThrows;

import static no.haga.Helpers.*;

public class Main {
    public static void main(String[] args) {
        runPlaywright();
    }

    @SneakyThrows
    private static void runPlaywright() {
        var mockUsers = getMockData();

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch();

        mockUsers.forEach(user -> {
            var fullName = user.getFullName();
            var address = user.getAddress();
            var postcode = user.getPostcode();
            var email = user.getEmail();
            var password = user.getPassword();
            var mobileNumber = user.getMobileNumber();
            var creditCardNumber = user.getCreditCardNumber();

            var url = "/";

            Page page = browser.newPage();
            page.navigate(url);
            System.out.println(page.title());
        });
    }
}