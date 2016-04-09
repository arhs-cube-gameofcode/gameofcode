package com.arhs.cube.thats.my.spot.service.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by borellda on 4/9/2016.
 */
public class Station2LineWrapper {
    /* The Logger */
    private final Logger log = LoggerFactory.getLogger(CsvParser.class);

    private String queryParameter;

    private double x_coord;

    private double y_coord;

    public Station2LineWrapper(String queryParameter, double x_coord, double y_coord){
        this.queryParameter = queryParameter;
        this.x_coord = x_coord;
        this.y_coord = y_coord;
    }


    public String getQueryParameter() {
        return queryParameter;
    }

    public double getX_coord() {
        return x_coord;
    }

    public double getY_coord() {
        return y_coord;
    }
}
