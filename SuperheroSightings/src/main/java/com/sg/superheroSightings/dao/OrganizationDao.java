package com.sg.superheroSightings.dao;

import com.sg.superheroSightings.dto.Organization;
import com.sg.superheroSightings.dto.Hero;

import java.util.List;

public interface OrganizationDao {

      List<Organization> getAllOrganizations();

      Organization getOrganizationById(int orgId);

      Organization addOrganization(Organization org);

      void updateOrganization(Organization organization);

      void deleteOrganizationById(int orgId);

//    List<Organization> getOrganizationsForHero(Hero superObj);


}
