package com.arhs.cube.thats.my.spot.service;

import com.arhs.cube.thats.my.spot.Application;
import com.arhs.cube.thats.my.spot.Bus;
import com.arhs.cube.thats.my.spot.BussWrapper;
import com.arhs.cube.thats.my.spot.service.util.Station2LineWrapper;
import org.geojson.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.inject.Inject;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.StrictAssertions.assertThat;

/**
 * Created by borellda on 4/9/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class GeoServiceTest {

    private final Logger log = LoggerFactory.getLogger(GeoServiceTest.class);

    @Inject
    private BusService busService;

    @Inject
    private GeoService geoService;

    @Test
    public void calculateDistanceTest(){

        BussWrapper wrapper = busService.callBusLines();

        wrapper.getData().stream().forEach( bus -> bus = busService.callBusTrackGeoJson(bus));

        for ( Bus bus : wrapper.getData() ) {
            bus.toString();
            for (Feature ftr : ((FeatureCollection) bus.getFeatures())) {
                GeoJsonObject obj = ftr.getGeometry();
                if (ftr.getGeometry() instanceof Polygon) {
                    Polygon tst = (Polygon) ftr.getGeometry();
                    Iterator<List<LngLatAlt>> iter = tst.getCoordinates().iterator();
                    while (iter.hasNext()) {
                        List<LngLatAlt> tmp = iter.next();
                        Iterator<LngLatAlt> iter2 = tmp.iterator();
                        boolean flag = false;
                        LngLatAlt frst;
                        LngLatAlt scnd = null;
                        while (iter2.hasNext()) {
                            if (flag) {
                                flag = false;
                                frst = iter2.next();
                                log.info(String.format("Distans is: %f", geoService.distFrom(frst, scnd)));
                            }else {
                                flag = true;
                                scnd = iter2.next();
                            }

                        }
                    }
                }
            }
        // wrapper.getData().stream().forEach( bus -> log.info(String.format("Distans is: %d", busService.distFrom(bus.getFeatures())))
        }
    }


    @Test
    public void getPublicTransportationStationsNearby(){
        Set<Station2LineWrapper> set = geoService.getPublicTransportationStationsnearby(6112550,49610700,500);

        assertThat(set).isNotNull();
        assertThat(set.isEmpty()).isFalse();
    }
}
