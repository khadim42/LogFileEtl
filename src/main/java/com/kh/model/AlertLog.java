package com.kh.model;

public class AlertLog {
    String eventId;
    long eventDuration;
    String type;
    String host;
    boolean alert;

    public AlertLog(String eventId, long eventDuration, String type, String host, boolean alert) {
        this.eventId = eventId;
        this.eventDuration = eventDuration;
        this.type = type;
        this.host = host;
        this.alert = alert;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public long getEventDuration() {
        return eventDuration;
    }

    public void setEventDuration(long eventDuration) {
        this.eventDuration = eventDuration;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public boolean isAlert() {
        return alert;
    }

    public void setAlert(boolean alert) {
        this.alert = alert;
    }
}
