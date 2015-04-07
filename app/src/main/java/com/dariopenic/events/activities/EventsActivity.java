package com.dariopenic.events.activities;

import com.dariopenic.events.R;
import com.dariopenic.events.adapters.EventsAdapter;
import com.dariopenic.events.custom.EventsApplication;
import com.dariopenic.events.models.Event;
import com.dariopenic.events.mvp.modules.EventsModule;
import com.dariopenic.events.mvp.presenters.EventPresenter;
import com.dariopenic.events.mvp.views.EventView;
import com.dariopenic.events.utils.DisplayUtils;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import dagger.ObjectGraph;


public class EventsActivity extends ActionBarActivity implements EventView, EventsAdapter.OnItemClickListener {

    private static final int ANIM_DURATION_TOOLBAR = 450;

    @InjectView(R.id.toolbar)
    protected Toolbar toolbar;

    @InjectView(R.id.rvEvents)
    protected RecyclerView rvEvents;

    @Inject
    protected EventPresenter presenter;

    private EventsAdapter eventsAdapter;

    private boolean pendingAnimationIntro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
//        getWindow().setExitTransition(new Explode());
//        getWindow().setAllowEnterTransitionOverlap(true);
//        getWindow().setAllowReturnTransitionOverlap(true);

        setContentView(R.layout.activity_events);
        ButterKnife.inject(this);
        injectLocalGraph();
        setupToolbar();

        if (savedInstanceState == null) {
            pendingAnimationIntro = true;
        }
        presenter.loadEvents();

    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.app_name);
    }

    private void injectLocalGraph() {
        ObjectGraph localGraph = EventsApplication.getInstance().getObjectGraph().plus(new EventsModule(this));
        localGraph.inject(this);
        localGraph.inject(presenter);
    }


    @Override
    public void showEvents(List<Event> events) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvEvents.setLayoutManager(linearLayoutManager);
        eventsAdapter = new EventsAdapter(this, events, this);
        rvEvents.setAdapter(eventsAdapter);
        if (pendingAnimationIntro) {
            pendingAnimationIntro = false;
            startIntroAnimation();
        }
    }


    private void startIntroAnimation() {
        int actionbarSize = DisplayUtils.dpToPx(this, 56);
        toolbar.setTranslationY(-actionbarSize);
        toolbar.animate().translationY(0).setDuration(ANIM_DURATION_TOOLBAR).setStartDelay(500);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startContentAnimation();
            }
        }, 650);
    }

    private void startContentAnimation() {
        eventsAdapter.updateItems();
    }

    @Override
    public void onItemClick(View view, ImageView imageView, int position) {
        Intent intent = new Intent(EventsActivity.this, EventDetailsActivity.class);
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this, imageView, "image").toBundle());
    }
}
