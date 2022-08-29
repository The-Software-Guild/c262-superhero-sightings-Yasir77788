package com.sg.superheroSightings.dto;

public class HeroOrgBridge {

    private int heroId;
    private int organizationId;

    public HeroOrgBridge() {
    }

    public HeroOrgBridge(int heroId, int organizationId) {
        this.heroId = heroId;
        this.organizationId = organizationId;
    }

    public int getHeroId() {
        return heroId;
    }

    public void setHeroId(int heroId) {
        this.heroId = heroId;
    }

    public int getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
    }




}
