package com.sg.superheroSightings.dao;

import com.sg.superheroSightings.dto.Hero;
import com.sg.superheroSightings.dto.Location;
import com.sg.superheroSightings.dto.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class HeroDaoDBImpl implements HeroDao{

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public List<Hero> getAllHeroes() {
        final String GET_ALL_HEROES = "SELECT * FROM Hero";
        return jdbc.query(GET_ALL_HEROES, new HeroMapper());
    }

    @Override
    public Hero getHeroById(int heroId) {
        try {
            final String GET_HERO_BY_ID = "SELECT * FROM Hero WHERE heroId = ?";
            return jdbc.queryForObject(GET_HERO_BY_ID, new HeroMapper(), heroId);
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    @Transactional
    public Hero addHero(Hero hero) {
        final String INSERT_HERO = "INSERT INTO Hero(heroName, heroDescription, "
                + "superPower, isHero) VALUES(?,?,?,?)";
        jdbc.update(INSERT_HERO,
                hero.getHeroName(),
                hero.getHeroDescription(),
                hero.getSuperPower(),
                hero.isHero());

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        hero.setHeroId(newId);
        return hero;
    }

    @Override
    public void updateHero(Hero hero) {
        final String UPDATE_HERO = "UPDATE Hero SET heroName=?, heroDescription=?, "
                + "superPower=?, isHero=? WHERE heroId=?";
        jdbc.update(UPDATE_HERO,
                hero.getHeroName(),
                hero.getHeroDescription(),
                hero.getSuperPower(),
                hero.isHero(),
                hero.getHeroId());
    }

    @Override
    @Transactional
    public void deleteHeroById(int heroId) {

        final String DELETE_HERO_FROM_HERO_ORG = "DELETE FROM hero_org_bridge WHERE heroId = ?";
        jdbc.update(DELETE_HERO_FROM_HERO_ORG, heroId);

        final String DELETE_HERO_FROM_SIGHTING = "DELETE FROM sighting WHERE heroId = ?";
        jdbc.update(DELETE_HERO_FROM_SIGHTING, heroId);

        final String DELETE_HERO = "DELETE FROM Hero WHERE heroId = ?";
        jdbc.update(DELETE_HERO, heroId);
    }

    @Override
    public List<Hero> getHeroSightedForLocation(Location location) {
        return null;
    }

    @Override
    public List<Hero> getOrganizationMembers(Organization org) {
        return null;
    }


    public static final class HeroMapper implements RowMapper<Hero> {

        @Override
        public Hero mapRow(ResultSet rs, int index) throws SQLException {
            Hero hero = new Hero();
            hero.setHeroId(rs.getInt("heroId"));
            hero.setHeroName(rs.getString("heroName"));
            hero.setHeroDescription(rs.getString("heroDescription"));
            hero.setSuperPower(rs.getString("superPower"));
            hero.setHero(rs.getBoolean("isHero"));

            return hero;
        }
    }
}
