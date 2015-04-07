package com.dariopenic.events.transitions;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.transition.TransitionValues;
import android.transition.Visibility;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by dario on 06/04/15.
 */
public class Scale extends Visibility {

    public Scale(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public Animator onAppear(ViewGroup sceneRoot, View view, TransitionValues startValues, TransitionValues endValues) {
        return createScaleAnimator(view, 0f, 1f);
    }

    @Override
    public Animator onDisappear(ViewGroup sceneRoot, View view, TransitionValues startValues, TransitionValues endValues) {
        return createScaleAnimator(view, 1f, 0f);
    }

    public Animator createScaleAnimator(View view, float startScale, float endScale) {
//        PropertyValuesHolder holderX = PropertyValuesHolder.ofFloat("scaleX", startScale, endScale); // removed scaling by X, only scaling by Y
        PropertyValuesHolder holderY = PropertyValuesHolder.ofFloat("scaleY", startScale, endScale);
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(view, holderY);
        animator.setDuration(300);
        return animator;
    }
}

