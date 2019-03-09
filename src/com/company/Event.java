package com.company;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

public class Event implements Serializable {
    private String eventName;
    private String location;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean longerThanOneDay = false;
    private String note;

    public Event(String eventName, String location, LocalDate startDate, LocalDate endDate, boolean longerThanOneDay, String note) {
        this.eventName = eventName;
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
        this.longerThanOneDay = longerThanOneDay;
        this.note = note;
    }

    public Event(String eventName, String location, LocalDate startDate, boolean longerThanOneDay, String note) {
        this.eventName = eventName;
        this.location = location;
        this.startDate = startDate;
        this.longerThanOneDay = longerThanOneDay;
        this.note = note;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public boolean isLongerThanOneDay() {
        return longerThanOneDay;
    }

    public void setAllDay(boolean longerThanOneDay) {
        this.longerThanOneDay = longerThanOneDay;
        if(this.longerThanOneDay == true){
            setEndDate(null);
        }
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
