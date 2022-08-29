package com.sg.superheroSightings.dao;

import com.sg.superheroSightings.dto.Super;
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
public class OrganizationDaoDBImpl implements OrganizationDao{

    @Autowired
    JdbcTemplate jdbc;

    @Autowired
    LocationDao locationDao;

    @Override
    public List<Organization> getAllOrganizations() {
        final String GET_ALL_ORGS = "SELECT * FROM Organization";
        return jdbc.query(GET_ALL_ORGS, new OrganizationMapper());
    }

    @Override
    public Organization getOrganizationById(int orgId) {
        try {
            final String SELECT_ORG_BY_ID = "SELECT * FROM Organization WHERE orgId = ?";
            Organization org = jdbc.queryForObject(SELECT_ORG_BY_ID, new OrganizationMapper(), orgId);
            org.setMembers(getMembersForAnOrg(orgId));
            return org;
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    @Transactional
    public Organization addOrganization(Organization org) {
        final String INSERT_ORG = "INSERT INTO Organization(orgName, orgDescription, orgPhone, "
                + "orgEmail, isHeroOrganization, locationId) VALUES(?,?,?,?,?,?)";
        jdbc.update(INSERT_ORG,
                org.getOrgName(),
                org.getOrgDescription(),
                org.getOrgPhone(),
                org.getOrgEmail(),
                org.isHeroOrganization(),
                org.getLocation().getLocationId());

        int newID = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        org.setOrgId(newID);
        insertHeroOrg (org);
        return org;
    }
    // helper method => for join operation
//    private List<Hero> getMembersForOrg(int orgId) {
//        final String SELECT_MEM_FOR_ORG = "SELECT her.* FROM Hero her JOIN hero_org_bridge"
//                + " hob ON hob.heroId = her.heroId WHERE hob.orgId=?";
//        return jdbc.query(SELECT_MEM_FOR_ORG, new HeroDaoDBImpl.HeroMapper(), orgId);
//    }


    // helper method - for join operation
    private List<Super> getMembersForAnOrg(int orgId) {
        final String SELECT_MEM_FOR_ORG = "SELECT her.* FROM Hero her JOIN hero_org_bridge"
                + " hob ON hob.heroId = her.heroID WHERE hob.orgId=?";
        return jdbc.query(SELECT_MEM_FOR_ORG, new SuperDaoDBImpl.SuperMapper(), orgId);
    }


    @Override
    @Transactional
    public void updateOrganization(Organization org) {
        final String UPDATE_ORG = "UPDATE Organization SET orgName=?, orgDescription=?, orgPhone=?,"
                + "orgEmail=?, isHeroOrg=?, locationId=? WHERE orgId=?";
        jdbc.update(UPDATE_ORG,
                org.getOrgName(),
                org.getOrgDescription(),
                org.getOrgPhone(),
                org.getOrgEmail(),
                org.isHeroOrganization(),
                org.getLocation().getLocationId(),
                org.getOrgId());
        final String DELETE_HERO_ORG_BRIDGE = "DELETE FROM Hero_Org_Bridge WHERE orgId=?";
        jdbc.update(DELETE_HERO_ORG_BRIDGE, org.getOrgId());
        insertHeroOrg (org);
    }

    // helper method
    private void insertHeroOrg(Organization org) {
        final String INSERT_HERO_ORG_BRIDGE = "INSERT INTO "
                + "hero_org_bridge(heroId, orgId) VALUES(?,?)";
        for(Super hero: org.getMembers()) {
            jdbc.update(INSERT_HERO_ORG_BRIDGE,
                    hero.getSuperId(),
                    org.getOrgId());
        }
    }

    @Override
    public void deleteOrganizationById(int orgId) {
        final String DELETE_ORG_FROM_HEOR_ORG_BRIDGE = "DELETE FROM Hero_Org_Bridge WHERE orgId=?";
        jdbc.update(DELETE_ORG_FROM_HEOR_ORG_BRIDGE, orgId);

        final String DELETE_ORG = "DELETE FROM Organization WHERE orgId = ?";
        jdbc.update(DELETE_ORG, orgId);
    }


    public final class OrganizationMapper implements RowMapper<Organization> {
        @Override
        public Organization mapRow(ResultSet rs, int index) throws SQLException {
            Organization org = new Organization();

            org.setOrgId(rs.getInt("orgID"));
            org.setOrgName(rs.getString("orgName"));
            org.setOrgDescription(rs.getString("orgDescription"));
            org.setOrgPhone(rs.getString("orgPhone"));
            org.setOrgEmail(rs.getString("orgEmail"));
            org.setHeroOrganization(rs.getBoolean("isHeroOrg"));
            org.setLocation(locationDao.getLocationById(rs.getInt("locationId")));
            org.setMembers(getMembersForAnOrg(org.getOrgId()));

            return org;
        }
    }


}
