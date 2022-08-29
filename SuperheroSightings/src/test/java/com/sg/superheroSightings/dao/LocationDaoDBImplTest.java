package com.sg.superheroSightings.dao;

import com.sg.superheroSightings.dto.Hero;
import com.sg.superheroSightings.dto.Location;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.testng.Assert.assertEquals;

@SpringBootTest
public class LocationDaoDBImplTest {

    @Autowired
    private LocationDao locationDao;

    public LocationDaoDBImplTest() {
    }

    @BeforeAll
    public static void setUpClass() {

    }

    @AfterAll
    public static void tearDownClass() {
    }

    // clear out the test-DB before each test-method run
    @BeforeEach
    public void setUp() {
        List<Location> locationList = locationDao.getAllLocations();
        for(Location loc : locationList) {
            locationDao.deleteLocationById(loc.getLocationId());
        }
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testAddAndGetLocation() {
        Location testLoc = new Location();

        testLoc.setLocationName("Hope City Football club");
        testLoc.setLocationDescription("South side of the city");
        testLoc.setLocationStreet("2nd St");
        testLoc.setLocationCity("Hope City");
        testLoc.setLocationState("CR");
        testLoc.setLocationZipCode("22222");
        testLoc.setLocationLong(" -79.653 ");
        testLoc.setLocationLat("55.2294");

        testLoc = locationDao.addLocation(testLoc);

        Location locFromDao = locationDao.getLocationById(testLoc.getLocationId());

        assertEquals(testLoc, locFromDao);
    }

}
