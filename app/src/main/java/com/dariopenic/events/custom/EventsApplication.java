package com.dariopenic.events.custom;

import com.dariopenic.events.mvp.modules.ApplicationModule;

import android.app.Application;

import dagger.ObjectGraph;

/**
 * Created by dario on 14/02/15.
 */
public class EventsApplication extends Application {

    private ObjectGraph objectGraph;

    private static EventsApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static EventsApplication getInstance() {
        return instance;
    }

    public ObjectGraph getObjectGraph() {
        if (objectGraph == null) {
            objectGraph = ObjectGraph.create(new ApplicationModule());
        }
        return objectGraph;
    }

}
