package se.yrgo.libraryapp.validators;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class RealNameTest {
    @ParameterizedTest
    @ValueSource(strings = {
            "idiot",
            "dumma",
            "hora",
            "fult",
            "testord"
    })
    void wordsFromBadWordsFileAreRejected(String badWord) {
        assertThat(RealName.validate(badWord)).isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "Anna Andersson",
            "Bosse",
            "Sara Karlsson"
    })
    void validRealNamesAreAccepted(String name) {
        assertThat(RealName.validate(name)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {""})
    void emptyStringIsRejected(String name) {
        assertThat(RealName.validate(name)).isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {"   ", "\t"})
    void whitespaceOnlyIsRejected(String name) {
        assertThat(RealName.validate(name)).isFalse();
    }

    @Test
    void nullThrowsNullPointerException() {
        assertThatThrownBy(() -> RealName.validate(null))
                .isInstanceOf(NullPointerException.class);
    }

}
