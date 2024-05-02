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
    @DisplayName("Test getRandomNumberWithLength with length 1")
    void getRandomNumberWithLengthOne() {
        // Arrange
        var length = 1;

        // Act
        var result = Helpers.getRandomNumberWithLength(length);

        // Assert
        assertNotNull(result);
        assertEquals(length, result.length());
    }

    @Test
    @DisplayName("Test getRandomNumberWithLength with leading zero")
    void getRandomNumberWithLengthLeadingZero() {
        // Arrange
        var length = 2;

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

        // Assert
        assertNotNull(result);
        var number = Integer.parseInt(result);

        assertEquals(1, result.length());
        assertTrue(number >= min && number <= max);
    }

    @Test
    @DisplayName("Test getRandomNumberBetweenRange with close min and max")
    void getRandomNumberBetweenRangeCloseValue() {
        // Arrange
        var min = 1;
        var max = 2;

        // Act
        var result = Helpers.getRandomNumberBetweenRange(min, max);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.length());
        assertTrue(result.equals(String.valueOf(min)) || result.equals(String.valueOf(max)));
    }

    @Test
    @DisplayName("Test getRandomNumberBetweenRange with same min and max")
    void getRandomNumberBetweenRangeSameValue() {
        // Arrange
        var min = 1;
        var max = 1;

        // Act
        var result = Helpers.getRandomNumberBetweenRange(min, max);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.length());
        assertEquals(String.valueOf(min), result);
    }

    @Test
    @DisplayName("Test getMockData")
    void getMockData() {
        // Act
        var result = Helpers.getMockData();

        // Assert
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

}