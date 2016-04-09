package com.arhs.cube.thats.my.spot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by borellda on 4/9/2016.
 */
@Service
@Transactional
public class PublicTransportationStation {
    /* The Logger */
    private final Logger log = LoggerFactory.getLogger(BusService.class);

    private final static String BUSLINE$STATIONURL = new String("");


    public void getBusLine4Station(String queryString){

    }

}
