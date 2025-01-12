package no.haga.config;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ConfigTest {

    @Test
    @DisplayName("Should not be able to instantiate class")
    void TestCannotInstantiate() {
        assertThrows(InvocationTargetException.class, () -> {
            var constructor = Config.class.getDeclaredConstructor();
            assertTrue(Modifier.isPrivate(constructor.getModifiers()));
            constructor.setAccessible(true);
            constructor.newInstance();
        });
    }
}