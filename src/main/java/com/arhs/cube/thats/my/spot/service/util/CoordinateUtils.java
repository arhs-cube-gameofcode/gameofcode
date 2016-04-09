package com.arhs.cube.thats.my.spot.service.util;

import org.geojson.*;

import java.util.Iterator;
import java.util.List;

/**
 * Created by colinma on 09/04/2016.
 */
public class CoordinateUtils {
    public static final double LATITUDE_OFFSET = 77388.90;
    public static final double LONGITUDE_OFFSET = 98030.36;

    public static double computeLatitudeToRealOne(double luxLatitude) {
        return luxLatitude + LATITUDE_OFFSET;
    }
    public static double computeLongitudeToRealOne(double luxLongitude) {
        return luxLongitude + LONGITUDE_OFFSET;
    }

    public static BussWrapper computeBusWrapperCoordinateToRealOne(BussWrapper wrapper) {
        for ( Bus bus : wrapper.getData() ) {
            for (Feature ftr : ((FeatureCollection) bus.getFeatures())) {
                if (ftr.getGeometry() instanceof Polygon) {
                    Polygon tst = (Polygon) ftr.getGeometry();
                    Iterator<List<LngLatAlt>> listOfCoordinateIterator = tst.getCoordinates().iterator();
                    while (listOfCoordinateIterator.hasNext()) {
                        Iterator<LngLatAlt> coordinateIterator = listOfCoordinateIterator.next().iterator();
                        LngLatAlt coordinate = coordinateIterator.next();

                        computeCoordinateToRealOne(coordinate);
                    }
                }
            }
        }
        return wrapper;
    }

    public static void computeCoordinateToRealOne(LngLatAlt coordinate) {
        coordinate.setLatitude(computeLatitudeToRealOne(coordinate.getLatitude()));
        coordinate.setLongitude(computeLongitudeToRealOne(coordinate.getLongitude()));
    }
}
