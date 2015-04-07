package com.dariopenic.events.adapters;

import com.dariopenic.events.R;
import com.dariopenic.events.models.Event;
import com.dariopenic.events.utils.DisplayUtils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by dario on 14/02/15.
 */
public class EventsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public interface OnItemClickListener {

        public void onItemClick(View view, ImageView imageView, int position);
    }

    private static final int ANIMATED_ITEMS_COUNT = 3;

    private Context context;

    private int lastAnimatedPosition = -1;

    private int itemsCount = 0;

    private List<Event> events;

    private OnItemClickListener listener;

    public EventsAdapter(Context context, List<Event> events, OnItemClickListener listener) {
        this.context = context;
        this.events = events;
        this.listener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        final View view = LayoutInflater.from(context).inflate(R.layout.card_event, viewGroup, false);
        return new CellEventViewHolder(view, listener);
    }

    private void runEnterAnimation(View view, int position) {
        if (position >= ANIMATED_ITEMS_COUNT - 1) {
            return;
        }
        if (position > lastAnimatedPosition) {
            lastAnimatedPosition = position;
            view.setTranslationY(DisplayUtils.getScreenHeight(context));
            view.animate().translationY(0)
                    .setInterpolator(new DecelerateInterpolator(3.f))
                    .setDuration(700)
                    .start();
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        runEnterAnimation(viewHolder.itemView, i);
        CellEventViewHolder holder = (CellEventViewHolder) viewHolder;
        // TODO: set correct values to each cell
    }

    @Override
    public int getItemCount() {
        return itemsCount;
    }

    public static class CellEventViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private OnItemClickListener listener;

        public CellEventViewHolder(View itemView, OnItemClickListener listener) {
            super(itemView);
            this.listener = listener;
            ButterKnife.inject(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onItemClick(v, (ImageView) v.findViewById(R.id.ivEventPromo), getPosition());
        }
    }

    public void updateItems() {
        itemsCount = events.size();
        notifyDataSetChanged();
    }

}
