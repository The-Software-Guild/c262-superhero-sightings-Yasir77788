package com.sg.superheroSightings.dao;

import com.sg.superheroSightings.dto.Location;
import com.sg.superheroSightings.dto.Sighting;
import com.sg.superheroSightings.dto.Super;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.testng.Assert.*;

@SpringBootTest
public class SightingDaoDBImplTest {

    @Autowired
    private SightingDao sightingDao;

    @Autowired
    private LocationDao locationDao;

    @Autowired
    private SuperDao superDao;

    public SightingDaoDBImplTest() {
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

        List<Super> superList = superDao.getAllSupers();
        for(Super s : superList) {
            superDao.deleteSuperById(s.getSuperId());
        }

        List<Location> locationList = locationDao.getAllLocations();
        for(Location loc : locationList) {
            locationDao.deleteLocationById(loc.getLocationId());
        }

        List<Sighting> sightings = sightingDao.getAllSightings();
        for (Sighting sighting : sightings) {
            sightingDao.deleteSightingById(sighting.getSightingId());
        }
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testAddAndGetSighting() throws ParseException {
        Location location = new Location();
        location.setLocationName("Music Corner");
        location.setLocationDescription("The location on the corner");
        location.setStreet("1234 First Street");
        location.setCity("Music city");
        location.setState("MC");
        location.setZipCode("89098");
        location.setLocationLat("123-ac-487");
        location.setLocationLong("342-da-873");
        locationDao.addLocation(location);

        Super superObj = new Super();
        superObj.setSuperName("Test Spiderman");
        superObj.setSuperDescription("A man that is a spider");
        superObj.setSuperPower("Can climb");
        superObj.setHero(true);
        superDao.addSuper(superObj);

        Sighting sighting = new Sighting();
        sighting.setSightingDate(new Date());
        sighting.setSuperObj(superObj);
        sighting.setLocation(location);
        sightingDao.addSighting(sighting);

        Sighting fromDAO = sightingDao.getSightingById(sighting.getSightingId());
        System.out.println("Date from expected: " + sighting.getSightingDate());
        System.out.println("Date from actual: " + fromDAO.getSightingDate());

        assertEquals(sighting, fromDAO);
    }

    @Test
    public void testGetAllSightings() {
        Location loc = new Location();
        loc.setLocationName("XYZ");
        loc.setLocationDescription("Good Place");
        loc.setStreet("1234 st");
        loc.setCity("Fair city");
        loc.setState("ST");
        loc.setZipCode("12345");
        loc.setLocationLat("123--1f-23");
        loc.setLocationLong("321-dx-211");
        locationDao.addLocation(loc);

        Super superObj = new Super();
        superObj.setSuperName("Test Spiderman");
        superObj.setSuperDescription("A man that is a spider");
        superObj.setSuperPower("Can climb high buildings");
        superObj.setHero(true);
        superDao.addSuper(superObj);

        Sighting sighting = new Sighting();
        sighting.setSightingDate(new Date());
        sighting.setSuperObj(superObj);
        sighting.setLocation(loc);
        sightingDao.addSighting(sighting);

        Location loc2 = new Location();
        loc2.setLocationName("City Hospital");
        loc2.setLocationDescription("The Hospital on the stree");
        loc2.setStreet("1234 Best Street");
        loc2.setCity("Good city");
        loc2.setState("SC");
        loc2.setZipCode("12345");
        loc2.setLocationLat("123-cx-123");
        loc2.setLocationLong("341-mc-291");
        locationDao.addLocation(loc2);

        Super super2 = new Super();
        super2.setSuperName("Test Spiderman2");
        super2.setSuperDescription("A man that is a spider");
        super2.setSuperPower("Can climb");
        super2.setHero(false);
        superDao.addSuper(super2);

        Sighting sighting2 = new Sighting();
        sighting2.setSightingDate(new Date());
        sighting2.setSuperObj(superObj);
        sighting2.setLocation(loc);
        sightingDao.addSighting(sighting2);

        List<Sighting> sightings = sightingDao.getAllSightings();

        assertEquals(2, sightings.size());
//        assertTrue(sightings.contains(sighting));
//        assertTrue(sightings.contains(sighting2));
    }


    @Test
    public void testUpdateSighting() {
        Location location = new Location();
        location.setLocationName("Music Corner");
        location.setLocationDescription("The location on the corner");
        location.setStreet("1234 First Street");
        location.setCity("Music city");
        location.setState("MC");
        location.setZipCode("89098");
        location.setLocationLat("123-ac-487");
        location.setLocationLong("342-da-873");
        locationDao.addLocation(location);



        Super super1 = new Super();
        super1.setSuperName("Test Spiderman");
        super1.setSuperDescription("A man that is a spider");
        super1.setSuperPower("Can climb high buildings");
        super1.setHero(true);
        superDao.addSuper(super1);


        Super super2 = new Super();
        super2.setSuperName("Test Spiderman2");
        super2.setSuperDescription("A man that is a spider");
        super2.setSuperPower("Can climb");
        super2.setHero(false);
        superDao.addSuper(super2);


        Sighting sighting = new Sighting();
        sighting.setSightingDate(new Date());
        sighting.setSuperObj(super1);
        sighting.setLocation(location);
        sightingDao.addSighting(sighting);

        Sighting fromDAO = sightingDao.getSightingById(sighting.getSightingId());
        assertEquals(sighting, fromDAO);

        sighting.setSuperObj(super2);
        sightingDao.updateSighting(sighting);

        assertNotEquals(sighting, fromDAO);

        fromDAO = sightingDao.getSightingById(sighting.getSightingId());
        assertEquals(sighting, fromDAO);
    }

    @Test
    public void testDeleteSightingById() {
        Location location = new Location();
        location.setLocationName("Music Corner");
        location.setLocationDescription("The location on the corner");
        location.setStreet("1234 First Street");
        location.setCity("Music city");
        location.setState("MC");
        location.setZipCode("89098");
        location.setLocationLat("123-ac-487");
        location.setLocationLong("342-da-873");
        locationDao.addLocation(location);


        Super super1 = new Super();
        super1.setSuperName("Test Spiderman");
        super1.setSuperDescription("A man that is a spider");
        super1.setSuperPower("Can climb high buildings");
        super1.setHero(true);
        superDao.addSuper(super1);

        Sighting sighting = new Sighting();
        sighting.setSightingDate(new Date());
        sighting.setSuperObj(super1);
        sighting.setLocation(location);
        sightingDao.addSighting(sighting);

        Sighting fromDAO = sightingDao.getSightingById(sighting.getSightingId());
        assertEquals(sighting, fromDAO);

        sightingDao.deleteSightingById(sighting.getSightingId());

        fromDAO = sightingDao.getSightingById(sighting.getSightingId());
        assertNull(fromDAO);
    }



}
