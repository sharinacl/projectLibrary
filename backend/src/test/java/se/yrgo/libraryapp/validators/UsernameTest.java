package se.yrgo.libraryapp.validators;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

public class UsernameTest {
    @Test
    void correctUsername() {
//        assertTrue(Username.validate("bosse"));
        boolean resultTrue = Username.validate("bosse");
        assertThat(resultTrue).isTrue();
    }

    @Test
    void incorrectUsername() {
//        assertFalse(Username.validate("name with space"));
        boolean resultFalse = Username.validate("name with space");
        assertThat(resultFalse).isFalse();
    }

}
