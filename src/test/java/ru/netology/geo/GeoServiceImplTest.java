package ru.netology.geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;

import static org.junit.jupiter.api.Assertions.*;

class GeoServiceImplTest {

    GeoServiceImpl geoService = new GeoServiceImpl();

    @Test
    @DisplayName("LOCALHOST")
    void LocalIP() {
       String ip = "127.0.0.1";
        assertNull(geoService.byIp(ip).getCountry());
        assertNull(geoService.byIp(ip).getCity());
        assertNull(geoService.byIp(ip).getStreet());
        Assertions.assertEquals(0, geoService.byIp(ip).getBuiling());
    }

    @Test
    @DisplayName("MOSCOW_IP")
    void MoscowIP() {
        String ip = "172.0.32.11";
        assertEquals("Moscow", geoService.byIp(ip).getCity());
        assertEquals(Country.RUSSIA, geoService.byIp(ip).getCountry());
        assertEquals("Lenina", geoService.byIp(ip).getStreet());
        assertEquals(15, geoService.byIp(ip).getBuiling());
    }

    @Test
    @DisplayName("NewYork_IP")
    void NewYorkIP() {
        String ip = "96.44.183.149";
        assertEquals("New York", geoService.byIp(ip).getCity());
        assertEquals(Country.USA, geoService.byIp(ip).getCountry());
        assertEquals(" 10th Avenue", geoService.byIp(ip).getStreet());
        assertEquals(32, geoService.byIp(ip).getBuiling());
    }
}