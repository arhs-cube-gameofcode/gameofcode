package com.arhs.cube.thats.my.spot.web.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.arhs.cube.thats.my.spot.data.Busdata;
import com.arhs.cube.thats.my.spot.domain.BussLine;
import com.codahale.metrics.annotation.Timed;

/**
 * REST controller for managing Route.
 */
@RestController
@RequestMapping("/api")
public class RouteResource {

    /**
     * GET  /routes/:id -> get the "id" route.
     */
    @RequestMapping(value = "/routes",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<BussLine>> getRoute(@RequestParam(required=false) String lat, @RequestParam(required=false) String lng, @RequestParam(required=false) String distance) {	
        //TODO filter the open data here and do the distance calculations.
    	
    	return new ResponseEntity<List<BussLine>>(Busdata.getData(), HttpStatus.OK);
    }
}
