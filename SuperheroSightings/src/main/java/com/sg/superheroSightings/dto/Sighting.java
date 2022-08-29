package com.sg.superheroSightings.dto;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Sighting {
    private int sightingId;
    private Date sightingDate;
    private Location location;
    private Hero hero;

    public Sighting() {
    }

    public Sighting(Date sightingDate, Location location, Hero hero) {
        this.sightingDate = sightingDate;
        this.location = location;
        this.hero = hero;
    }

    public int getSightingId() {
        return sightingId;
    }

    public void setSightingId(int sightingId) {
        this.sightingId = sightingId;
    }

    public Date getSightingDate() {
        return sightingDate;
    }

    public void setSightingDate(Date sightingDate) {
        this.sightingDate = sightingDate;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sighting sighting = (Sighting) o;
        return sightingId == sighting.sightingId && Objects.equals(sightingDate, sighting.sightingDate) && Objects.equals(location, sighting.location) && Objects.equals(hero, sighting.hero);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sightingId, sightingDate, location, hero);
    }

    @Override
    public String toString() {
        return "Sighting{" +
                "sightingId=" + sightingId +
                ", sightingDate=" + sightingDate +
                ", location=" + location +
                ", hero=" + hero +
                '}';
    }
}
