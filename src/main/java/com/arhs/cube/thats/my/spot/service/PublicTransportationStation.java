package com.arhs.cube.thats.my.spot.service;

import com.arhs.cube.thats.my.spot.service.util.Station2LineWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by borellda on 4/9/2016.
 */
@Service
@Transactional
public class PublicTransportationStation {
    /* The Logger */
    private final Logger log = LoggerFactory.getLogger(BusService.class);

    private final static String BUSLINE4STATIONURL = new String("http://travelplanner.mobiliteit.lu/restproxy/departureBoard?accessId=cdt&format=json&");


    public void getBusLine4Station(Station2LineWrapper queryWrapper){

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(String.format("%s%s",BUSLINE4STATIONURL,queryWrapper.getQueryParameter()));
        HttpEntity<?> entity = new HttpEntity<>(headers);

        HttpEntity<String> response = restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET, entity, String.class);

        HashMap<String,Object> result = new HashMap<>( );
        try {
            result =
                new ObjectMapper().readValue(response.getBody(), HashMap.class);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        log.info(String.format("Result :%s", response.getBody()));

    }

}
