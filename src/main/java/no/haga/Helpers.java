package no.haga;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import no.haga.models.User;

import java.util.List;
import java.util.Random;

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
        return String.format(STR."%0\{length}d", value);
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
     * Reads the mock file.
     *
     * @return The content of the mock file as a string.
     */
    @SneakyThrows
    public List<User> getMockData() {
        var mockUsersFile = Helpers.class.getResourceAsStream("/mockdata.json");

        return new ObjectMapper().readValue(mockUsersFile, new TypeReference<>() {
        });
    }
}
