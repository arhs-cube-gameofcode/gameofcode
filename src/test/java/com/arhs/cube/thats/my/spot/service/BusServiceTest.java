package com.arhs.cube.thats.my.spot.service;

import com.arhs.cube.thats.my.spot.Application;
import com.arhs.cube.thats.my.spot.service.util.BussWrapper;
import com.arhs.cube.thats.my.spot.service.util.Station2LineWrapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.inject.Inject;

import java.util.Set;

import static org.assertj.core.api.StrictAssertions.assertThat;

/**
 * Created by borellda on 4/9/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class BusServiceTest {

    private final Logger log = LoggerFactory.getLogger(BusServiceTest.class);

    @Inject
    private BusService busService;

    @Inject
    private PublicTransportationStation stationService;

    @Test
    public void busestest(){

        BussWrapper wrapper = busService.callBusLines();

        assertThat(wrapper).isNotNull();
        assertThat(wrapper.getData().isEmpty()).isFalse();


    }

    @Test
    public void busTrackTest(){

        BussWrapper wrapper = busService.callBusLines();

        wrapper.getData().stream().forEach( bus -> bus = busService.callBusTrackGeoJson(bus));

        assertThat(wrapper).isNotNull();
        assertThat(wrapper.getData().isEmpty()).isFalse();


    }
     @Test
     public void findNearestStation (){
        Station2LineWrapper station = busService.findNearestPublicTransportStation(6112550,49610700,500);
         assertThat(station).isNotNull();

         stationService.getBusLine4Station(station);
     }


}
