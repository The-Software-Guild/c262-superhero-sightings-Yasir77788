package com.sg.superheroSightings.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;


public class Super {


    private int superId;

    @NotBlank(message = "Super name must not be empty.")
    @Size(max = 50, message="Super name must be less than 50 characters.")
    private String superName;

    @NotBlank(message = "Super description must not be empty.")
    @Size(max = 200, message="Super description must be less than 200 characters.")
    private String superDescription;

    @NotBlank(message = "Super power must not be empty.")
    @Size(max = 200, message="Super power must be less than 200 characters.")
    private String superPower;

    @NotBlank(message = "IsHero must not be empty.")
    @Size(max = 10, message="IsHero description must be less than 10 characters.")
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
