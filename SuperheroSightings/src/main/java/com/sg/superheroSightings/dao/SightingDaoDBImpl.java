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
import java.sql.Timestamp;
import java.util.List;

@Repository
public class SightingDaoDBImpl implements SightingDao{

    @Autowired
    JdbcTemplate jdbc;

    @Autowired
    LocationDao locationDao;

    @Autowired
    SuperDao superDao;

    @Override
    public List<Sighting> getAllSightings() {
        final String SELECT_ALL_SIGHTINGS = "SELECT * FROM Sighting";
        List<Sighting> sightings = jdbc.query(SELECT_ALL_SIGHTINGS, new SightingMapper());
        associateSuperAndLocations(sightings);
        return sightings;
    }

    private void associateSuperAndLocations(List<Sighting> sightings) {
        for (Sighting sighting : sightings) {
            sighting.setSuperObj(getSuperForSighting(sighting.getSightingId()));
            sighting.setLocation(getLocationForSighting(sighting.getSightingId()));
        }
    }

    private Super getSuperForSighting(int sightingId) {
        final String SELECT_SUPER_FOR_SIGHTING = "SELECT sp.* FROM Super sp "
                + "JOIN Sighting si ON si.superId = sp.superId WHERE si.sightingId = ?";
        return jdbc.queryForObject(SELECT_SUPER_FOR_SIGHTING, new SuperDaoDBImpl.SuperMapper(), sightingId);
    }

    private Location getLocationForSighting(int sightingId) {
        final String SELECT_LOC_FOR_SIGHTING = "SELECT l.* FROM Location l "
                + "JOIN Sighting s ON s.locationId = l.locationId WHERE s.sightingId = ?";
        return jdbc.queryForObject(SELECT_LOC_FOR_SIGHTING, new LocationDaoDBImpl.LocationMapper(), sightingId);
    }

    @Override
    @Transactional
    public Sighting addSighting(Sighting sighting) {
        final String INSERT_SIGHTING = "INSERT INTO Sighting(sightingDate, superId, locationId) "
                + "VALUES(?,?,?)";
        jdbc.update(INSERT_SIGHTING,
                sighting.getSightingDate(),
                sighting.getSuperObj().getSuperId(),
                sighting.getLocation().getLocationId());

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        sighting.setSightingId(newId);
        return sighting;
    }

    @Override
    public Sighting getSightingById(int sightingId) {
        try {
            final String SELECT_SIGHTING_BY_ID = "SELECT * FROM Sighting WHERE sightingId=?";
            Sighting sighting = jdbc.queryForObject(SELECT_SIGHTING_BY_ID, new SightingMapper(), sightingId);
            sighting.setSuperObj(getSuperForSighting(sightingId));
            sighting.setLocation(getLocationForSighting(sightingId));
            return sighting;
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    @Transactional
    public void updateSighting(Sighting sighting) {
        final String UPDATE_SIGHTING = "UPDATE Sighting SET sighingDate=?, sightingId=?, "
                + "locationId=? WHERE sightingId=?";
        jdbc.update(UPDATE_SIGHTING,
                sighting.getSightingDate(),
                sighting.getSuperObj().getSuperId(),
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
            int superId = rs.getInt("superId");

            sighting.setSightingId(rs.getInt("sightingId"));
            sighting.setSightingDate(rs.getDate("sightingDate"));
            sighting.setLocation(locationDao.getLocationById(locationId));
            sighting.setSuperObj(superDao.getSuperById(superId));

            return sighting;
        }
    }
}
