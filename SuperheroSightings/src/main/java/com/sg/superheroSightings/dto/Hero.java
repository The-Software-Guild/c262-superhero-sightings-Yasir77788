package com.sg.superheroSightings.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Hero {
    //Heroes have names, descriptions, and a superpower.
    //Heroes are affiliated with one or more superhero/supervillain organizations.

    private int heroId;
    private String heroName;
    private String heroDescription;
    private String superPower;
    private boolean isHero;

    public Hero() {
    }

    public Hero(String heroName, String heroDescription, String superPowr, boolean isHero) {
        this.heroName = heroName;
        this.heroDescription = heroDescription;
        this.superPower = superPowr;
        this.isHero = isHero;
    }

    public int getHeroId() {
        return heroId;
    }

    public void setHeroId(int heroId) {
        this.heroId = heroId;
    }

    public String getHeroName() {
        return heroName;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }

    public String getHeroDescription() {
        return heroDescription;
    }

    public void setHeroDescription(String heroDescription) {
        this.heroDescription = heroDescription;
    }

    public String getSuperPower() {
        return superPower;
    }

    public void setSuperPower(String superPower) {
        this.superPower = superPower;
    }

    public boolean isHero() {
        return isHero;
    }

    public void setHero(boolean hero) {
        isHero = hero;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hero hero = (Hero) o;
        return heroId == hero.heroId && isHero == hero.isHero && Objects.equals(heroName, hero.heroName) && Objects.equals(heroDescription, hero.heroDescription) && Objects.equals(superPower, hero.superPower);
    }

    @Override
    public int hashCode() {
        return Objects.hash(heroId, heroName, heroDescription, superPower, isHero);
    }

    @Override
    public String toString() {
        return "Hero{" +
                "heroId=" + heroId +
                ", heroName='" + heroName + '\'' +
                ", heroDescription='" + heroDescription + '\'' +
                ", superPower='" + superPower + '\'' +
                ", isHero=" + isHero +
                '}';
    }
}
