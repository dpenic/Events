package com.dariopenic.events.mvp.interactors;

import com.dariopenic.events.models.Event;

import java.util.List;

import retrofit.Callback;

/**
 * Created by dario on 14/02/15.
 */
public interface EventInteractor {

    public void loadEvents(Callback<List<Event>> eventsCallback);

}
