package no.haga;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HelpersTest {

    @Test
    @DisplayName("Test getRandomNumberWithLength")
    void getRandomNumberWithLength() {
        // Arrange
        var length = 4;

        // Act
        var result = Helpers.getRandomNumberWithLength(length);

        // Assert
        assertNotNull(result);
        assertEquals(length, result.length());
    }

    @Test
    @DisplayName("Test getRandomNumberWithLength with no leading zero")
    void getRandomNumberWithLengthNoLeadingZero() {
        // Arrange
        var length = 1;

        // Act
        var result = Helpers.getRandomNumberWithLength(length);

        // Assert
        assertNotNull(result);
        assertEquals(length, result.length());
    }

    @Test
    @DisplayName("Test getRandomNumberBetweenRange")
    void getRandomNumberBetweenRange() {
        // Arrange
        var min = 1;
        var max = 9;

        // Act
        var result = Helpers.getRandomNumberBetweenRange(min, max);

        System.out.println(result);
        // Assert
        assertNotNull(result);
        var number = Integer.parseInt(result);

        assertEquals(1, result.length());
        assertTrue(number >= min && number <= max);
    }
}