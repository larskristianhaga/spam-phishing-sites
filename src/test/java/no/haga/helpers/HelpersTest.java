package no.haga.helpers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.microsoft.playwright.Playwright;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class HelpersTest {

    @Test
    @DisplayName("Should not be able to instantiate class")
    void TestCannotInstantiate() {
        assertThrows(InvocationTargetException.class, () -> {
            var constructor = Helpers.class.getDeclaredConstructor();
            assertTrue(Modifier.isPrivate(constructor.getModifiers()));
            constructor.setAccessible(true);
            constructor.newInstance();
        });
    }

    @Nested
    @DisplayName("Test getRandomNumberWithLength")
    class GetRandomNumberWithLength {
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
    }

    @Nested
    @DisplayName("Test getRandomNumberBetweenRange")
    class GetRandomNumberBetweenRange {
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
    }

    @Nested
    @DisplayName("Test generateFakeUser")
    class GenerateFakeUser {
        @Test
        @DisplayName("Test generateFakeUser")
        void generateFakeUser() {
            // Act
            var result = Helpers.generateFakeUser();

            // Assert
            assertNotNull(result);

            assertNotNull(result.getFullName());
            assertNotNull(result.getFirstName());
            assertNotNull(result.getLastName());
            assertNotNull(result.getAddress());
            assertNotNull(result.getPostCode());
            assertNotNull(result.getCity());
            assertNotNull(result.getEmail());
            assertNotNull(result.getPassword());
            assertNotNull(result.getCreditCardNumber());
        }
    }

    @Nested
    @DisplayName("Test getRandomBrowser")
    class GetRandomBrowser {
        @Test
        @DisplayName("Test getRandomBrowser with null")
        void getRandomBrowserNull() {
            // Act
            var result = Helpers.getRandomBrowser(Playwright.create());

            // Assert
            assertNotNull(result);
        }
    }

    @Nested
    @DisplayName("Test getRandomMobileUserAgent")
    class GetRandomMobileUserAgent {
        @Test
        @DisplayName("Test getRandomMobileUserAgent")
        void getRandomMobileUserAgent() {
            // Act
            var result = Helpers.getRandomMobileUserAgent();

            // Assert
            assertNotNull(result);
        }
    }

    @Nested
    @DisplayName("Test getRandomDesktopUserAgent")
    class GetRandomDesktopUserAgent {
        @Test
        @DisplayName("Test getRandomDesktopUserAgent")
        void getRandomDesktopUserAgent() {
            // Act
            var result = Helpers.getRandomDesktopUserAgent();

            // Assert
            assertNotNull(result);
        }
    }
}