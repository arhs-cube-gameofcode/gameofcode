package com.arhs.cube.thats.my.spot.service;

import com.arhs.cube.thats.my.spot.service.util.CsvParser;
import com.arhs.cube.thats.my.spot.service.util.Station2LineWrapper;
import org.geojson.LngLatAlt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

/**
 * Created by borellda on 4/9/2016.
 */
@Service
@Transactional
public class GeoService {
    /* The Logger */
    private final Logger log = LoggerFactory.getLogger(GeoService.class);

    private final static String NEARBYSTATIONURL = new String("http://travelplanner.mobiliteit.lu/hafas/query.exe/dot");

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

    public Set<Station2LineWrapper> getPublicTransportationStationsnearby(int latitude, int longtitude, int maxDistance){

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(NEARBYSTATIONURL)
            .queryParam("look_maxdist", maxDistance)
            .queryParam("look_x", latitude)
            .queryParam("look_y", longtitude)
            .queryParam("performLocating", 2)
            .queryParam("tpl", "stop2csv")
            .queryParam("stationProxy", "yes");

        HttpEntity<?> entity = new HttpEntity<>(headers);

        HttpEntity<String> response = restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET, entity, String.class);

        log.info(String.format("Result :%S", response.getBody()));
        Set<Station2LineWrapper> set = new HashSet<>();
        CsvParser parser = new CsvParser();
        NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);
        for (String line: parser.lineSplitter(response.getBody(),"\n")) {
            double x_coord = 0.0;
            double y_coord = 0.0;
            try {
                for (String column : parser.lineSplitter(line, "@")) {
                    x_coord = column.contains("X=") ? format.parse(column.split("=")[1]).doubleValue(): 0.0;
                    y_coord = column.contains("Y=") ? format.parse(column.split("=")[1]).doubleValue(): 0.0;
                }
            } catch (ParseException e) {
                log.error(e.getMessage(),e);
            }
            Station2LineWrapper wrapper = new Station2LineWrapper(line,x_coord,y_coord);
            set.add(wrapper);
        }
        return set;
    }

}
