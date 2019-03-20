package com.company.objects;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Event implements Serializable {
    private String title;
    private String location;
    private LocalDateTime date;
    private String note;

    public Event(String title, String location, LocalDateTime date, String note) {
        this.title = title;
        this.location = location;
        this.date = date;
        this.note = note;
    }

    public String getTitle() {
        return title;
    }

    public String getLocation() {
        return location;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getNote() {
        return note;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
