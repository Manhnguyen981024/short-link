package com.demo.shortlink.service;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.model.CityResponse;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;

@Service
public class GeoIpService {

    private final DatabaseReader databaseReader;

    public GeoIpService() throws IOException {
        File dbFile = new ClassPathResource("GeoLite2-City.mmdb").getFile();
        this.databaseReader = new DatabaseReader.Builder(dbFile).build();
    }

    public String getLocationByIP(String ip) {
        try {
            InetAddress ipAddress = InetAddress.getByName(ip);
            CityResponse response = databaseReader.city(ipAddress);
            return response.getCity().getName() + ", " +
                    response.getCountry().getName();
        } catch (Exception e) {
            return "Unknown";
        }
    }
}
