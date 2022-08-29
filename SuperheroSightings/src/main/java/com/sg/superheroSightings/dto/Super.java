package com.sg.superheroSightings.dto;

import java.util.Objects;

public class Super {
    //Heroes have names, descriptions, and a superpower.
    //Heroes are affiliated with one or more superhero/supervillain organizations.

    private int superId;
    private String superName;
    private String superDescription;
    private String superPower;
    private boolean isHero;

    public Super() {
    }

    public Super(String superName, String superDescription, String superPower, boolean isHero) {
        this.superName = superName;
        this.superDescription = superDescription;
        this.superPower = superPower;
        this.isHero = isHero;
    }

    public int getSuperId() {
        return superId;
    }

    public void setSuperId(int superId) {
        this.superId = superId;
    }

    public String getSuperName() {
        return superName;
    }

    public void setSuperName(String superName) {
        this.superName = superName;
    }

    public String getSuperDescription() {
        return superDescription;
    }

    public void setSuperDescription(String superDescription) {
        this.superDescription = superDescription;
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
        Super aSuper = (Super) o;
        return superId == aSuper.superId && isHero == aSuper.isHero && Objects.equals(superName, aSuper.superName) && Objects.equals(superDescription, aSuper.superDescription) && Objects.equals(superPower, aSuper.superPower);
    }

    @Override
    public int hashCode() {
        return Objects.hash(superId, superName, superDescription, superPower, isHero);
    }

    @Override
    public String toString() {
        return "Super{" +
                "superId=" + superId +
                ", superName='" + superName + '\'' +
                ", superDescription='" + superDescription + '\'' +
                ", superPower='" + superPower + '\'' +
                ", isHero=" + isHero +
                '}';
    }
}
