package ru.netology.i18n;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.netology.entity.Country;

import java.util.Set;
import java.util.prefs.Preferences;

import static org.junit.jupiter.api.Assertions.*;

class LocalizationServiceImplTest {

    LocalizationServiceImpl localizationService = new LocalizationServiceImpl();

    @Test
    void locale() {
        Assertions.assertEquals("Добро пожаловать", localizationService.locale(Country.RUSSIA));
        Assertions.assertEquals("Welcome", localizationService.locale(Country.USA));
        Assertions.assertEquals("Welcome", localizationService.locale(Country.BRAZIL));
        Assertions.assertEquals("Welcome", localizationService.locale(Country.GERMANY));
    }
}