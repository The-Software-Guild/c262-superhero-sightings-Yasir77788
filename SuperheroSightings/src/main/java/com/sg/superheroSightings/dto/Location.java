package com.sg.superheroSightings.dto;

import java.util.Objects;

public class Location {
    //Locations have names, descriptions, address information, and latitude/longitude coordinates.
    private int locationId;
    private String locationName;
    private String locationDescription;
    private String locationStreet;
    private String locationCity;
    private String locationState;
    private String locationZipCode;
    private String locationLong;
    private String locationLat;

    public Location() {
    }

    public Location(String locationName, String locationDescription, String locationStreet, String locationCity, String locationState,
                    String locationZipCode, String locationLong, String locationLat) {
        this.locationName = locationName;
        this.locationDescription = locationDescription;
        this.locationStreet = locationStreet;
        this.locationCity = locationCity;
        this.locationState = locationState;
        this.locationZipCode = locationZipCode;
        this.locationLong = locationLong;
        this.locationLat = locationLat;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getLocationStreet() {
        return locationStreet;
    }

    public void setLocationStreet(String locationStreet) {
        this.locationStreet = locationStreet;
    }

    public String getLocationCity() {
        return locationCity;
    }

    public void setLocationCity(String locationCity) {
        this.locationCity = locationCity;
    }

    public String getLocationState() {
        return locationState;
    }

    public void setLocationState(String locationState) {
        this.locationState = locationState;
    }

    public String getLocationZipCode() {
        return locationZipCode;
    }

    public void setLocationZipCode(String locationZipCode) {
        this.locationZipCode = locationZipCode;
    }

    public String getLocationLong() {
        return locationLong;
    }

    public void setLocationLong(String locationLong) {
        this.locationLong = locationLong;
    }

    public String getLocationLat() {
        return locationLat;
    }

    public void setLocationLat(String locationLat) {
        this.locationLat = locationLat;
    }

    public String getLocationDescription() {
        return locationDescription;
    }

    public void setLocationDescription(String locationDescription) {
        this.locationDescription = locationDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return locationId == location.locationId && Objects.equals(locationName, location.locationName) && Objects.equals(locationDescription, location.locationDescription) && Objects.equals(locationStreet, location.locationStreet) && Objects.equals(locationCity, location.locationCity) && Objects.equals(locationState, location.locationState) && Objects.equals(locationZipCode, location.locationZipCode) && Objects.equals(locationLong, location.locationLong) && Objects.equals(locationLat, location.locationLat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(locationId, locationName, locationDescription, locationStreet, locationCity, locationState, locationZipCode, locationLong, locationLat);
    }

    @Override
    public String toString() {
        return "Location{" +
                "locationId=" + locationId +
                ", locationName='" + locationName + '\'' +
                ", locationDescription='" + locationDescription + '\'' +
                ", locationStreet='" + locationStreet + '\'' +
                ", locationCity='" + locationCity + '\'' +
                ", locationState='" + locationState + '\'' +
                ", locationZipCode='" + locationZipCode + '\'' +
                ", locationLong='" + locationLong + '\'' +
                ", locationLat='" + locationLat + '\'' +
                '}';
    }
}
