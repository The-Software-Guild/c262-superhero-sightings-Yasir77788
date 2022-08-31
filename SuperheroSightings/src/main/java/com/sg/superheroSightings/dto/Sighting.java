package com.sg.superheroSightings.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

public class Sighting {
    private int sightingId;

    @NotBlank(message = "Sighting date must not be empty.")
    @Size(max = 50, message="Sighting date must be less than 50 characters.")
    private LocalDate sightingDate;

    @NotBlank(message = "Sighting location must not be empty.")
    @Size(max = 50, message="Sighting date must be less than 50 characters.")
    private Location location;

    @NotBlank(message = "Sighting super must not be empty.")
    @Size(max = 50, message="Sighting super must be less than 50 characters.")
    private Super superObj;

    public Sighting() {
    }

    public Sighting(LocalDate sightingDate, Location location, Super superObj) {
        this.sightingDate = sightingDate;
        this.location = location;
        this.superObj = superObj;
    }

    public int getSightingId() {
        return sightingId;
    }

    public void setSightingId(int sightingId) {
        this.sightingId = sightingId;
    }

    public LocalDate getSightingDate() {
        return sightingDate;
    }

    public void setSightingDate(LocalDate sightingDate) {
        this.sightingDate = sightingDate;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Super getSuperObj() {
        return superObj;
    }

    public void setSuperObj(Super superObj) {
        this.superObj = superObj;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sighting sighting = (Sighting) o;
        return sightingId == sighting.sightingId && Objects.equals(sightingDate, sighting.sightingDate) && Objects.equals(location, sighting.location) && Objects.equals(superObj, sighting.superObj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sightingId, sightingDate, location, superObj);
    }

    @Override
    public String toString() {
        return "Sighting{" +
                "sightingId=" + sightingId +
                ", sightingDate=" + sightingDate +
                ", location=" + location +
                ", superObj=" + superObj +
                '}';
    }
}
