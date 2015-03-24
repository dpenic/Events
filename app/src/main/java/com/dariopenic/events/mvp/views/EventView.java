package com.dariopenic.events.mvp.views;

import com.dariopenic.events.models.Event;

import java.util.List;

/**
 * Created by dario on 14/02/15.
 */
public interface EventView {

    public void showEvents(List<Event> events);

}
