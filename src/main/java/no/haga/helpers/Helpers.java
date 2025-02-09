package no.haga.helpers;

import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;
import generator.RandomUserAgentGenerator;

import java.util.Locale;
import java.util.Random;

import lombok.experimental.UtilityClass;
import net.datafaker.Faker;
import no.haga.models.Card;
import no.haga.models.User;

@UtilityClass
public class Helpers {

    private static final Random random = new Random();

    /**
     * Generates a random number with the specified length.
     *
     * @param length The length of the random number.
     * @return The random number as a string with the specified length.
     */
    public static String getRandomNumberWithLength(int length) {
        // Calculate the range
        var max = (int) Math.pow(10, length) - 1;

        // Generate the random number
        var value = random.nextInt(max + 1);

        // Return the value as a string, with leading zeros if necessary
        return String.format("%0" + length + "d", value);
    }

    /**
     * Generates a random number between the specified min and max values.
     *
     * @param min The minimum value.
     * @param max The maximum value.
     * @return The random number.
     */
    public static String getRandomNumberBetweenRange(int min, int max) {
        var value = random.nextInt((max - min) + 1) + min;

        return String.valueOf(value);
    }

    /**
     * Generates a new instance of the Faker class with the Norwegian locale.
     *
     * @return A new instance of the Faker class.
     */
    public User generateFakeUser() {
        var localFaker = new Faker(Locale.forLanguageTag("nb-NO"));

        var phoneNumber = localFaker.regexify("[49]\\d{7}");
        var idNumber = localFaker.regexify("\\d{6}\\d{5}");
        var card = generateVisaOrMastercard(localFaker);

        var firstName = localFaker.name().firstName();
        var lastName = localFaker.name().lastName();
        var fullName = firstName + " " + lastName;

        var firstInitial = firstName.charAt(0);

        String[] emailFormats = {firstName + "." + lastName + "@%s", firstName + lastName + "@%s", firstInitial + "." + lastName + "@%s", firstName + "_" + lastName.charAt(0) + "@%s", firstName + localFaker.number().digits(3) + "@%s", firstName + "." + lastName + localFaker.number().digits(2) + "@%s", firstInitial + lastName + "@%s"};

        // Choose a random email format
        String[] domains = {"gmail.com", "outlook.com", "yahoo.com", "protonmail.com", "online.no", "hotmail.com", "hotmail.no", "live.no"};
        var domain = domains[localFaker.random().nextInt(domains.length)];

        var emailFormat = emailFormats[localFaker.random().nextInt(emailFormats.length)];
        var email = String.format(emailFormat, domain);

        var address = localFaker.address();
        var internet = localFaker.internet();
        var business = localFaker.business();

        return User.builder()
                .fullName(fullName)
                .firstName(firstName)
                .lastName(lastName)
                .address(address.fullAddress())
                .postCode(address.zipCode())
                .city(address.city())
                .email(email)
                .password(internet.password(true))
                .phoneNumber(phoneNumber)
                .idNumber(idNumber)
                .creditCardNumber(card.getNumber())
                .creditCardExpiry(business.creditCardExpiry())
                .creditCardCvc(business.securityCode())
                .creditCardType(card.getType())
                .build();
    }

    public static Card generateVisaOrMastercard(Faker faker) {
        if (faker.random().nextBoolean()) {
            // Visa (Starts with 4, 16 digits)
            return new Card("Visa", faker.regexify("4\\d{15}"));
        } else {
            // Mastercard (51-55xxxx, 2221-2720xxxx, 16 digits)
            return new Card("Mastercard", faker.regexify("(5[1-5]\\d{14}|222[1-9]\\d{12}|22[3-9]\\d{13}|2[3-6]\\d{14}|27[01]\\d{13}|2720\\d{12})"));
        }
    }

    /**
     * Returns a random browser type.
     *
     * @param playwright The playwright instance.
     * @return A random browser type.
     */
    public BrowserType getRandomBrowser(Playwright playwright) {
        int random = (int) (Math.random() * 3);
        return switch (random) {
            case 0 -> playwright.webkit();
            case 1 -> playwright.firefox();
            default -> playwright.chromium();
        };
    }

    public String getRandomMobileUserAgent() {
        return RandomUserAgentGenerator.getNextMobile();
    }

    public String getRandomDesktopUserAgent() {
        return RandomUserAgentGenerator.getNextNonMobile();
    }
}
