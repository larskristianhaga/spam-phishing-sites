package no.haga.helpers;

import com.github.javafaker.Faker;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;
import generator.RandomUserAgentGenerator;
import java.util.Locale;
import java.util.Random;
import lombok.experimental.UtilityClass;
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

        var name = localFaker.name();
        var address = localFaker.address();
        var internet = localFaker.internet();
        var phoneNumber = localFaker.phoneNumber();
        var business = localFaker.business();
        var idNumber = localFaker.idNumber();

        return User.builder()
                .fullName(name.fullName())
                .firstName(name.firstName())
                .lastName(name.lastName())
                .address(address.fullAddress())
                .postCode(address.zipCode())
                .city(address.city())
                .email(internet.emailAddress())
                .password(internet.password(true))
                .phoneNumber(phoneNumber.cellPhone())
                .idNumber(idNumber.valid())
                .creditCardNumber(business.creditCardNumber())
                .creditCardExpiry(business.creditCardExpiry())
                .creditCardCvc(getRandomNumberBetweenRange(100, 999))
                .creditCardType(business.creditCardType())
                .creditCardHolder(name.fullName())
                .build();
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
