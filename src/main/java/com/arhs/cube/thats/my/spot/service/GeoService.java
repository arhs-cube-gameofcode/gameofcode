package com.arhs.cube.thats.my.spot.service;

import com.arhs.cube.thats.my.spot.BussWrapper;
import org.geojson.LngLatAlt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

/**
 * Created by borellda on 4/9/2016.
 */
@Service
@Transactional
public class GeoService {
    /* The Logger */
    private final Logger log = LoggerFactory.getLogger(GeoService.class);

    public double distFrom(LngLatAlt firstCoord, LngLatAlt secondCoordinate){
        return distFrom(firstCoord.getLatitude(),firstCoord.getLongitude(),secondCoordinate.getLatitude(),secondCoordinate.getLongitude());
    }

    public double distFrom(double lat1, double lng1, double lat2, double lng2) {
        double earthRadius = 6371000; //meters
        double dLat = Math.toRadians(lat2-lat1);
        double dLng = Math.toRadians(lng2-lng1);
        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
            Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                Math.sin(dLng/2) * Math.sin(dLng/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double dist =  (earthRadius * c);

        return dist;
    }

    public void getPublicTransportationStationsnearby(double latitude, double longtitude, double maxDistance){

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", "Mozilla/5.0");

        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        ResponseEntity<BussWrapper> response = restTemplate.exchange("", HttpMethod.GET, entity, BussWrapper.class);


    }

}
