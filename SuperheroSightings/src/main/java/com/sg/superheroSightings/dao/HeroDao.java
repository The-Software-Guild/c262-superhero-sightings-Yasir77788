package com.sg.superheroSightings.dao;

import com.sg.superheroSightings.dto.Hero;
import com.sg.superheroSightings.dto.Location;
import com.sg.superheroSightings.dto.Organization;


import java.util.List;

public interface HeroDao {

      List<Hero> getAllHeroes();

      Hero addHero(Hero hero);

      Hero getHeroById(int heroId);

      void updateHero(Hero hero);

      void deleteHeroById(int heroId);

      List<Hero> getHeroSightedForLocation(Location location);

      List<Hero> getOrganizationMembers(Organization org);
}
