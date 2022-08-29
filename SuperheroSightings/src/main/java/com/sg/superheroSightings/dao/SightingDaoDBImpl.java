package com.sg.superheroSightings.dao;

import com.sg.superheroSightings.dto.Super;
import com.sg.superheroSightings.dto.Location;
import com.sg.superheroSightings.dto.Sighting;
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
public class SightingDaoDBImpl implements SightingDao{

    @Autowired
    JdbcTemplate jdbc;

    @Autowired
    LocationDao locationDao;

    @Autowired
    SuperDao heroDao;

    @Override
    public List<Sighting> getAllSightings() {
        final String SELECT_ALL_SIGHTINGS = "SELECT * FROM Sighting";
        List<Sighting> sightings = jdbc.query(SELECT_ALL_SIGHTINGS, new SightingMapper());
        associateHeroesAndLocations(sightings);
        return sightings;
    }

    private void associateHeroesAndLocations(List<Sighting> sightings) {
        for (Sighting sighting : sightings) {
            sighting.setHero(getHeroForSighting(sighting.getSightingId()));
            sighting.setLocation(getLocationForSighting(sighting.getSightingId()));
        }
    }

    private Super getHeroForSighting(int sightingId) {
        final String SELECT_HERO_FOR_SIGHTING = "SELECT h.* FROM Hero h "
                + "JOIN Sighting s ON s.heroId = h.heroId WHERE s.sightingId = ?";
        return jdbc.queryForObject(SELECT_HERO_FOR_SIGHTING, new SuperDaoDBImpl.SuperMapper(), sightingId);
    }

    private Location getLocationForSighting(int sightingId) {
        final String SELECT_LOC_FOR_SIGHTING = "SELECT l.* FROM Location l "
                + "JOIN Sighting s ON s.locationId = l.locationId WHERE s.sightingId = ?";
        return jdbc.queryForObject(SELECT_LOC_FOR_SIGHTING, new LocationDaoDBImpl.LocationMapper(), sightingId);
    }

    @Override
    @Transactional
    public Sighting addSighting(Sighting sighting) {
        final String INSERT_SIGHTING = "INSERT INTO Sighting(sightingDate, heroId, locationId) "
                + "VALUES(?,?,?)";
        jdbc.update(INSERT_SIGHTING,
                sighting.getSightingDate(),
                sighting.getHero().getSuperId(),
                sighting.getLocation().getLocationId());

        int newID = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        sighting.setSightingId(newID);
        return sighting;
    }

    @Override
    public Sighting getSightingById(int sightingId) {
        try {
            final String SELECT_SIGHTING_BY_ID = "SELECT * FROM Sighting WHERE sightingId=?";
            Sighting sighting = jdbc.queryForObject(SELECT_SIGHTING_BY_ID, new SightingMapper(), sightingId);
            sighting.setHero(getHeroForSighting(sightingId));
            sighting.setLocation(getLocationForSighting(sightingId));
            return sighting;
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    @Transactional
    public void updateSighting(Sighting sighting) {
        final String UPDATE_SIGHTING = "UPDATE Sighting SET sightingDate=?, heroId=?, "
                + "locationId=? WHERE sightingId=?";
        jdbc.update(UPDATE_SIGHTING,
                sighting.getSightingDate(),
                sighting.getHero().getSuperId(),
                sighting.getLocation().getLocationId(),
                sighting.getSightingId());
    }

    @Override
    @Transactional
    public void deleteSightingById(int sightingId) {
        final String DELETE_SIGHTING = "DELETE FROM Sighting WHERE sightingId=?";
        jdbc.update(DELETE_SIGHTING, sightingId);
    }


    public final class SightingMapper implements RowMapper<Sighting> {

        @Override
        public Sighting mapRow(ResultSet rs, int index) throws SQLException {
            Sighting sighting = new Sighting();
            int locationId = rs.getInt("locationId");
            int heroID = rs.getInt("heroId");

            sighting.setSightingId(rs.getInt("sightingID"));
            sighting.setSightingDate(rs.getDate("sightingDate"));
            sighting.setLocation(locationDao.getLocationById(locationId));
            sighting.setHero(heroDao.getSuperById(heroID));

            return sighting;
        }
    }


}
