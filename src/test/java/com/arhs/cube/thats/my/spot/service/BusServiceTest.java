package com.arhs.cube.thats.my.spot.service;

import com.arhs.cube.thats.my.spot.Application;
import com.arhs.cube.thats.my.spot.Bus;
import com.arhs.cube.thats.my.spot.BussWrapper;
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



}
