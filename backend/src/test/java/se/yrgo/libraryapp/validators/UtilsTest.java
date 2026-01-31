package se.yrgo.libraryapp.validators;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class UtilsTest {

    @ParameterizedTest
    @ValueSource(strings = {
            "hello", "world", "java "
    })
    void onlyLettersAndWhitespaceTest(String word)
    {
        String result = Utils.onlyLettersAndWhitespace(word);

        assertThat(result).isEqualTo(word);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "abc143",
            "hellothere!",
            "a_z",
            "java@maybe"
    })
    void removesNonLettersAndWhitespace(String input) {
        String result = Utils.onlyLettersAndWhitespace(input);

        assertThat(result).doesNotContain("1")
                .doesNotContain("2")
                .doesNotContain("3")
                .doesNotContain("!")
                .doesNotContain("_")
                .doesNotContain("@");

    }

    @ParameterizedTest
    @ValueSource(strings = {""})
    void emptyStringReturnsEmpty(String input) {
        assertThat(Utils.onlyLettersAndWhitespace(input)).isEqualTo("");
    }

    @Test
    void nullThrowsNullPointerException() {
        assertThatThrownBy(() -> Utils.onlyLettersAndWhitespace(null))
                .isInstanceOf(NullPointerException.class);
    }
}
