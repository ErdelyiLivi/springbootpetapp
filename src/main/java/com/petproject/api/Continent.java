package com.petproject.api;

/**
 * Created by ler on 2017-03-19.
 */
public enum Continent {
    EUROPE("Europe"), AFRICA("Africa"), ASIA("Asia"), AMERICA("America"), AUSTRALIA("Australia");

    private final String displayName;

    Continent(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
