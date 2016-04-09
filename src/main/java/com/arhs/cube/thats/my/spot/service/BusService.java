package com.arhs.cube.thats.my.spot.service;

import com.arhs.cube.thats.my.spot.Bus;
import com.arhs.cube.thats.my.spot.BussWrapper;
import org.geojson.GeoJsonObject;
import org.geojson.LngLatAlt;
import org.hibernate.internal.util.collections.ConcurrentReferenceHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

/**
 * Created by borellda on 4/9/2016.
 */
@Service
@Transactional
public class BusService {
    /* The Logger */
    private final Logger log = LoggerFactory.getLogger(BusService.class);

    private static final String BUSLINESURL = new String("http://opendata.vdl.lu/odaweb/index.jsp?describe=1");

    private static final String BUSSTOPS = new String("http://opendata.vdl.lu/odaweb/?cat=%s");


    public BussWrapper callBusLines(){
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", "Mozilla/5.0");

        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        ResponseEntity<BussWrapper> response = restTemplate.exchange(BUSLINESURL, HttpMethod.GET, entity, BussWrapper.class);

        //response.getBody().getData().stream().forEach( bus -> callBusTrackGeoJson(bus));

        return  response.getBody();

    }

    public Bus callBusTrackGeoJson( Bus bus){

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", "Mozilla/5.0");

        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        ResponseEntity<GeoJsonObject> response = restTemplate.exchange(String.format(BUSSTOPS,bus.getId()), HttpMethod.GET, entity, GeoJsonObject.class);
        bus.setFeatures(response.getBody());

        return bus;
    }



}
