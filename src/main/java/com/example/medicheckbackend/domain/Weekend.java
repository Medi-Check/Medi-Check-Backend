package com.example.medicheckbackend.domain;

public enum Weekend {
    MONDAY("MONDAY"),
    TUESDAY("TUESDAY"),
    WEDNESDAY("WEDNESDAY"),
    THURSDAY("THURSDAY"),
    FRIDAY("FRIDAY"),
    SATURDAY("SATURDAY"),
    SUNDAY("SUNDAY"),
    EVERYDAY("EVERYDAY");

    private String weekend;

    Weekend(String weekend) {
        this.weekend = weekend;
    }
}
