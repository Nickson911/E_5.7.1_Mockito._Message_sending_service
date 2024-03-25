package ru.netology.sender;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MessageSenderImplTest {


    GeoService geoService;
    LocalizationService localizationService;

    String testIPMosFull = "172.123.12.19";
    String testIPMosStartsWith = "172.";
    String testIPNYFull = "96.44.183.149";
    String testIPNYStartsWith = "96.";

    @org.junit.jupiter.api.Test
    @DisplayName("Проверяем полный Московский IP")
    void massageTestIPMosFull() {
        geoService = mock(GeoService.class);
        when(geoService.byIp(testIPMosFull)).thenReturn(
                new Location("Moskow", Country.RUSSIA, "Lenina", 15));
        localizationService = mock(LocalizationService.class);
        when(localizationService.locale(Country.RUSSIA)).thenReturn(
                "Добро пожаловать");
        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> expected = new HashMap<>();
        expected.put(MessageSenderImpl.IP_ADDRESS_HEADER, testIPMosFull);
        Assertions.assertEquals("Добро пожаловать", messageSender.send(expected));
    }

    @Test
    @DisplayName("Проверяем первые цифры в Московком IP")
    void massageTestIPMosStartsWith () {
        geoService = mock(GeoService.class);
        when(geoService.byIp(testIPMosStartsWith)).thenReturn(
                new Location("Moscow", Country.RUSSIA, null, 0));
        localizationService = mock(LocalizationService.class);
        when(localizationService.locale(Country.RUSSIA)).thenReturn(
                "Добро пожаловать");
        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> expected = new HashMap<>();
        expected.put(MessageSenderImpl.IP_ADDRESS_HEADER, testIPMosStartsWith);
        Assertions.assertEquals("Добро пожаловать", messageSender.send(expected));
    }

    @Test
    @DisplayName("Проверяем полный New York IP")
    void massageTestIPNYFull () {
        geoService = mock(GeoService.class);
        when(geoService.byIp(testIPNYFull)).thenReturn(
                new Location("New York", Country.USA, " 10th Avenue", 32));
        localizationService = mock(LocalizationService.class);
        when(localizationService.locale(Country.USA)).thenReturn(
                "Welcome");
        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> expected = new HashMap<>();
        expected.put(MessageSenderImpl.IP_ADDRESS_HEADER, testIPNYFull);
        Assertions.assertEquals("Welcome", messageSender.send(expected));
    }

    @Test
    @DisplayName("Проверяем первые цифры в New York IP")
    void massageTestIPNYStartsWith () {
        geoService = mock(GeoService.class);
        when(geoService.byIp(testIPNYStartsWith)).thenReturn(
                new Location("New York", Country.USA, null,  0));
        localizationService = mock(LocalizationService.class);
        when(localizationService.locale(Country.USA)).thenReturn(
                "Welcome");
        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> expected = new HashMap<>();
        expected.put(MessageSenderImpl.IP_ADDRESS_HEADER, testIPNYStartsWith);
        Assertions.assertEquals("Welcome", messageSender.send(expected));
    }

}