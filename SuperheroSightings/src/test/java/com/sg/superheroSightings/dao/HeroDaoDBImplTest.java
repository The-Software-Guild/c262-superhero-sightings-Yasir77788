package com.sg.superheroSightings.dao;

import com.sg.superheroSightings.dto.Hero;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.testng.Assert.*;

@SpringBootTest
public class HeroDaoDBImplTest {

    @Autowired
    private HeroDao heroDao;


    public HeroDaoDBImplTest() {
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
        List<Hero> superList = heroDao.getAllHeroes();
        for(Hero hero : superList) {
            heroDao.deleteHeroById(hero.getHeroId());
        }
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testGetAllHeroes() {
        // ARRANGE
        Hero tesHero = new Hero();
        tesHero.setHeroName("Superhuman");
        tesHero.setHeroDescription("A person with good super skills");
        tesHero.setSuperPower("x-ray vision");
        tesHero.setHero(true);
        tesHero = heroDao.addHero(tesHero);

        Hero testVillain = new Hero();
        testVillain.setHeroName("The Joker");
        testVillain.setHeroDescription("A person with evil intentions");
        testVillain.setSuperPower("Lethal concoctions");
        testVillain.setHero(false);
        testVillain = heroDao.addHero(testVillain);

        // ACT - call the code under test
        List<Hero> superList = heroDao.getAllHeroes();

        // ASSERT - verify the test result
        assertEquals(2, superList.size());
        assertTrue(superList.contains(tesHero));
        assertTrue(superList.contains(testVillain));
    }

    @Test
    public void testAddAndGetHero() {
        Hero testHero = new Hero();
        testHero.setHeroName("Batman");
        testHero.setHeroDescription("A man that can climb high buildings");
        testHero.setSuperPower("super intellect");
        testHero.setHero(true);
        testHero = heroDao.addHero(testHero);

        Hero heroFromDao = heroDao.getHeroById(testHero.getHeroId());

        assertEquals(testHero, heroFromDao);
    }

    @Test
    public void testUpdateHero() {
        Hero testHero = new Hero();
        testHero.setHeroName("Superman 1.0");
        testHero.setHeroDescription("A man with super skills");
        testHero.setSuperPower("super-speed");
        testHero.setHero(true);
        testHero = heroDao.addHero(testHero);

        Hero heroFromDao = heroDao.getHeroById(testHero.getHeroId());
        assertEquals(testHero, heroFromDao);

        testHero.setHeroName("Superman 2.0: updated");
        testHero.setHeroDescription("A man with super skills updated");
        testHero.setSuperPower("super-speed: updated");
        testHero.setHero(true);
        heroDao.updateHero(testHero);

        assertNotEquals(testHero, heroFromDao);

        // get the updated hero from Dao
        heroFromDao = heroDao.getHeroById(testHero.getHeroId());
        // compare the returned updated-heroFromDao with the updated testHero
        assertEquals(testHero, heroFromDao);
    }

    @Test
    public void testDeleteHeroById() {
        Hero hero = new Hero();
        hero.setHeroName("Test Superman");
        hero.setHeroDescription("A man who helps the vulnerable");
        hero.setSuperPower("superhuman strength");
        hero.setHero(true);
        hero = heroDao.addHero(hero);

        Hero heroFromDao = heroDao.getHeroById(hero.getHeroId());
        assertEquals(hero, heroFromDao);

        heroDao.deleteHeroById(hero.getHeroId());

        heroFromDao = heroDao.getHeroById(hero.getHeroId());
        assertNull(heroFromDao);
    }

}
