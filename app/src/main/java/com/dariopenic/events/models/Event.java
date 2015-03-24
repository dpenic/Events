package com.dariopenic.events.models;

/**
 * Created by dario on 14/02/15.
 */
public class Event {

    private int imageResource;

    private String title;

    private String description;

    public Event(int imageResource, String title, String description) {
        this.imageResource = imageResource;
        this.title = title;
        this.description = description;
    }

    public int getImageResource() {
        return imageResource;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
