package com.company;

import java.util.Date;

public class Event {
    private String eventName;
    private String location;
    private Date startDate;
    private Date endDate;
    private boolean allDay = true;
    private String note;

    public Event(String eventName, String location, Date startDate, Date endDate, boolean allDay, String note) {
        this.eventName = eventName;
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
        this.allDay = allDay;
        this.note = note;
    }

    public Event(String eventName, String location, Date startDate, boolean allDay, String note) {
        this.eventName = eventName;
        this.location = location;
        this.startDate = startDate;
        this.allDay = allDay;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public boolean isAllDay() {
        return allDay;
    }

    public void setAllDay(boolean allDay) {
        this.allDay = allDay;
        if(this.allDay == true){
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
