package com.dariopenic.events.mvp.interactors.impl;

import com.dariopenic.events.R;
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
        return new Event(R.drawable.slint_featured, "Slint",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
    }

}
