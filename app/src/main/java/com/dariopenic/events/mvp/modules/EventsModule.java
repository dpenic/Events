package com.dariopenic.events.mvp.modules;

import com.dariopenic.events.activities.EventsActivity;
import com.dariopenic.events.mvp.interactors.EventInteractor;
import com.dariopenic.events.mvp.interactors.impl.EventInteractorImpl;
import com.dariopenic.events.mvp.presenters.EventPresenter;
import com.dariopenic.events.mvp.presenters.impl.EventPresenterImpl;
import com.dariopenic.events.mvp.views.EventView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by dario on 14/02/15.
 */
@Module(
        injects = {
                EventsActivity.class,
                EventPresenterImpl.class
        }
)
public class EventsModule {

    private EventView view;

    public EventsModule(EventView view) {
        this.view = view;
    }

    @Provides
    public EventPresenter provideEventPresenter() {
        return new EventPresenterImpl(view);
    }

    @Provides
    public EventInteractor provideEventInteractor() {
        return new EventInteractorImpl();
    }
    
}
