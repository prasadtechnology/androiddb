package com.example.prasad.dbapplication.models;

public class Calender {
    private long eventId;

    public long getEventId() {
        return eventId;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
    }

    private String eventName;
    private String eventDesc;
    private long eventTime;

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDesc() {
        return eventDesc;
    }

    public void setEventDesc(String eventDesc) {
        this.eventDesc = eventDesc;
    }

    public long getEventTime() {
        return eventTime;
    }

    public void setEventTime(long eventTime) {
        this.eventTime = eventTime;
    }

    @Override
    public String toString() {
        return "Calender{" +
                "eventName='" + eventName + '\'' +
                ", eventDesc='" + eventDesc + '\'' +
                ", eventTime=" + eventTime +
                '}';
    }
}
