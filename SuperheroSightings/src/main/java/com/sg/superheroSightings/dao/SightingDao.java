package com.sg.superheroSightings.dao;

import com.sg.superheroSightings.dto.Sighting;

import java.util.List;

public interface SightingDao {

      Sighting getSightingById(int sightingId);

      List<Sighting> getAllSightings();

      Sighting addSighting(Sighting sighting);

      void updateSighting(Sighting sighting);

     void deleteSightingById(int sightingId);

//    List<Sighting> getSightingForHero(Hero hero);
//
//    List<Sighting> getSightingForDate(Date date);
//
//    List<Sighting> getSightingsForLocationAndDate(Location location, Date date);
}
