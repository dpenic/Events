package com.dariopenic.events.mvp.interactors.impl;

import com.dariopenic.events.R;
import com.dariopenic.events.custom.EventsApplication;
import com.dariopenic.events.models.Event;
import com.dariopenic.events.mvp.interactors.EventInteractor;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;

/**
 * Created by dario on 14/02/15.
 */
public class EventInteractorImpl implements EventInteractor {

    @Override
    public void loadEvents(Callback<List<Event>> eventsCallback) {
        List<Event> fakeEvents = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            fakeEvents.add(newEvent());
        }
        eventsCallback.success(fakeEvents, null);
    }

    private Event newEvent() {
        return new Event(R.drawable.slint_featured, EventsApplication.getInstance().getString(R.string.slint),
                EventsApplication.getInstance().getString(R.string.lorem_ipsum));
    }

}
