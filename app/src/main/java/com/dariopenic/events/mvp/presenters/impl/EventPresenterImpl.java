package com.dariopenic.events.mvp.presenters.impl;

import com.dariopenic.events.models.Event;
import com.dariopenic.events.mvp.interactors.EventInteractor;
import com.dariopenic.events.mvp.presenters.EventPresenter;
import com.dariopenic.events.mvp.views.EventView;

import java.util.List;

import javax.inject.Inject;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by dario on 14/02/15.
 */
public class EventPresenterImpl implements EventPresenter {

    private EventView view;

    @Inject
    protected EventInteractor interactor;

    public EventPresenterImpl(EventView view) {
        this.view = view;
    }

    @Override
    public void loadEvents() {
        interactor.loadEvents(new Callback<List<Event>>() {
            @Override
            public void success(List<Event> events, Response response) {
                view.showEvents(events);
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

}
