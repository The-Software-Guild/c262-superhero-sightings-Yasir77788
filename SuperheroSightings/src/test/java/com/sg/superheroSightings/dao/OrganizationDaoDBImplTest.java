package com.sg.superheroSightings.dao;

import com.sg.superheroSightings.dto.Location;
import com.sg.superheroSightings.dto.Organization;
import com.sg.superheroSightings.dto.Sighting;
import com.sg.superheroSightings.dto.Super;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.testng.Assert.*;

public class OrganizationDaoDBImplTest {
    @Autowired
    private SightingDao sightingDao;

    @Autowired
    private LocationDao locationDao;

    @Autowired
    private SuperDao superDao;

    @Autowired
    private OrganizationDao orgDao;

    public OrganizationDaoDBImplTest() {
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

        List<Organization> orgList = orgDao.getAllOrganizations();
        for (Organization org : orgList) {
            orgDao.deleteOrganizationById(org.getOrgId());
        }
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testAddAndGetOrg() {
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

        Organization org1 = new Organization();
        org1.setOrgName("Test ABC Org");
        org1.setOrgDescription("Non Profit");
        org1.setOrgPhone("456-123-8844");
        org1.setOrgEmail("org1@email.com");
        org1.setHeroOrganization(true);
        org1.setLocation(location);
        orgDao.addOrganization(org1);

        Organization fromDAO = orgDao.getOrganizationById(org1.getOrgId());

        assertEquals(org1, fromDAO);
    }

    @Test
    public void testGetAllOrgs() {
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

        Organization org1 = new Organization();
        org1.setOrgName("Test ABC Org");
        org1.setOrgDescription("Non Profit");
        org1.setOrgPhone("456-123-8844");
        org1.setOrgEmail("org1@email.com");
        org1.setHeroOrganization(true);
        org1.setLocation(location);
        orgDao.addOrganization(org1);


        Organization org2 = new Organization();
        org2.setOrgName("Test ABC Org 2");
        org2.setOrgDescription("Non Profit");
        org2.setOrgPhone("456-123-8844");
        org2.setOrgEmail("org1@email.com");
        org2.setHeroOrganization(true);
        org2.setLocation(location);
        orgDao.addOrganization(org2);


        List<Organization> orgs = orgDao.getAllOrganizations();

        assertEquals(2, orgs.size());
        assertTrue(orgs.contains(org1));
        assertTrue(orgs.contains(org2));
    }

    @Test
    public void testUpdateOrg() {
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

        Organization org1 = new Organization();
        org1.setOrgName("Test ABC Org");
        org1.setOrgDescription("Non Profit");
        org1.setOrgPhone("456-123-8844");
        org1.setOrgEmail("org1@email.com");
        org1.setHeroOrganization(true);
        org1.setLocation(location);
        orgDao.addOrganization(org1);

        Organization fromDAO = orgDao.getOrganizationById(org1.getOrgId());
        assertEquals(org1, fromDAO);

        org1.setOrgName("Org 2.0");
        orgDao.updateOrganization(org1);

        assertNotEquals(org1, fromDAO);

        fromDAO = orgDao.getOrganizationById(org1.getOrgId());
        assertEquals(org1, fromDAO);
    }

    @Test
    public void testDeleteOrgById() {
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

        Organization org1 = new Organization();
        org1.setOrgName("Test ABC Org");
        org1.setOrgDescription("Non Profit");
        org1.setOrgPhone("456-123-8844");
        org1.setOrgEmail("org1@email.com");
        org1.setHeroOrganization(true);
        org1.setLocation(location);
        orgDao.addOrganization(org1);

        Organization fromDAO = orgDao.getOrganizationById(org1.getOrgId());
        assertEquals(org1, fromDAO);

        orgDao.deleteOrganizationById(org1.getOrgId());
        fromDAO = orgDao.getOrganizationById(org1.getOrgId());
        assertNull(fromDAO);

    }
}
