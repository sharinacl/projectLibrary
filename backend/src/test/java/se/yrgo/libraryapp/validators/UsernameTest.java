package se.yrgo.libraryapp.validators;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

public class UsernameTest {
    // Valid usernames - ASCII characters, -, _, .,
    @ParameterizedTest
    @ValueSource(strings = {
            "test", // 4 characters
            "user123", // letters + numbers
            "valid_username", // with _
            "sh4rinn4", // mixed case + numbers
            "test@user", // with @
            "valid-username", // with -
            "test123@user.name" // combination
    })
    void validateUsername(String username){
        assertThat(Username.validate(username)).isTrue();
    }

   // Invalid username: too short (less than 4 characters)
    @ParameterizedTest
    @ValueSource(strings = {
            "a",
            "ab",
            "abc"
    })
    void tooShortUsername(String username){
        assertThat(Username.validate(username)).isFalse();
    }

    // Invalid: Empty string
    @ParameterizedTest
    @EmptySource
    void emptyUsername(String username) {
        assertThat(Username.validate(username)).isFalse();
    }

    // Invalid: Contains whitespace
    @ParameterizedTest
    @ValueSource(strings = {
            "invalid username",  // space in middle
            "  test  ",          // multiple spaces
            " test",            // leading space
            "test "          // trailing space
    })
    void usernamesWithWhitespace(String username) {
        assertThat(Username.validate(username)).isFalse();
    }

    // Invalid: Contains non-ASCII characters
    @ParameterizedTest
    @ValueSource(strings = {
            "shärina",           // has ä
            "José",              // has é
            "åsa",               // has å
            "münchen"            // has ü
    })
    void usernamesWithNonAsciiCharacters(String username) {
        assertThat(Username.validate(username)).isFalse();
    }

    // Invalid: Contains disallowed special characters
    @ParameterizedTest
    @ValueSource(strings = {
            "user!name",         // has !
            "test#user",         // has #
            "user$name",         // has $
            "test%user",         // has %
            "user&name",         // has &
            "test*user"          // has *
    })
    void usernamesWithInvalidSpecialCharacters(String username) {
        assertThat(Username.validate(username)).isFalse();
    }
}
