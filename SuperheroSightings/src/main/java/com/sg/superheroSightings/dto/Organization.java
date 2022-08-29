package com.sg.superheroSightings.dto;

import java.util.List;
import java.util.Objects;

public class Organization {
    //Organizations have names, descriptions, and address/contact information.
    //Organizations have members.
    private int orgId;
    private String orgName;
    private String orgDescription;
    private boolean isHeroOrganization;
    private String orgPhone;
    private String orgEmail;
    //private int locationId;
    private Location location;
    private List<Hero> members;


    public Organization() {
    }

    public Organization(String orgName, String orgDescription, boolean isHeroOrganization, String orgPhone, String orgEmail, Location location) {
        this.orgName = orgName;
        this.orgDescription = orgDescription;
        this.isHeroOrganization = isHeroOrganization;
        this.orgPhone = orgPhone;
        this.orgEmail = orgEmail;
        this.location = location;
    }

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgDescription() {
        return orgDescription;
    }

    public void setOrgDescription(String orgDescription) {
        this.orgDescription = orgDescription;
    }

    public boolean isHeroOrganization() {
        return isHeroOrganization;
    }

    public void setHeroOrganization(boolean heroOrganization) {
        isHeroOrganization = heroOrganization;
    }

    public String getOrgPhone() {
        return orgPhone;
    }

    public void setOrgPhone(String orgPhone) {
        this.orgPhone = orgPhone;
    }

    public String getOrgEmail() {
        return orgEmail;
    }

    public void setOrgEmail(String orgEmail) {
        this.orgEmail = orgEmail;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<Hero> getMembers() {
        return members;
    }

    public void setMembers(List<Hero> members) {
        this.members = members;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return orgId == that.orgId && isHeroOrganization == that.isHeroOrganization && Objects.equals(orgName, that.orgName) && Objects.equals(orgDescription, that.orgDescription) && Objects.equals(orgPhone, that.orgPhone) && Objects.equals(orgEmail, that.orgEmail) && Objects.equals(location, that.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orgId, orgName, orgDescription, isHeroOrganization, orgPhone, orgEmail, location);
    }

    @Override
    public String toString() {
        return "Organization{" +
                "orgId=" + orgId +
                ", orgName='" + orgName + '\'' +
                ", orgDescription='" + orgDescription + '\'' +
                ", isHeroOrganization=" + isHeroOrganization +
                ", orgPhone='" + orgPhone + '\'' +
                ", orgEmail='" + orgEmail + '\'' +
                ", location=" + location +
                '}';
    }
}
