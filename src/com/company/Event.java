package com.company;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Event implements Serializable {
    private String title;
    private String location;
    private LocalDateTime date;
    private String note = "Empty";

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
}
