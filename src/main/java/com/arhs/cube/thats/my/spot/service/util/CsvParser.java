package com.arhs.cube.thats.my.spot.service.util;

import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by borellda on 4/9/2016.
 */
public class CsvParser {
    /* The Logger */
    private final Logger log = LoggerFactory.getLogger(CsvParser.class);

    public String[] lineSplitter(final String line, final String delimiter){
        String[] splitline = line.split(delimiter);

        return splitline;

    }

}
