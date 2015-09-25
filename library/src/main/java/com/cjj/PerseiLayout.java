package com.cjj;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.view.ViewPropertyAnimatorUpdateListener;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.AbsListView;
import android.widget.FrameLayout;

/**
 * Created by cjj on 2015/9/22.
 */
public class PerseiLayout extends FrameLayout {

    public static final String Tag = "cjj_log";

    protected FrameLayout mHeadLayout;

    protected int DEFAULT_HEIGHT = 100;

    protected float mHeadHeight;

    protected boolean isOpenMenu;

    private View mChildView;

    private float mTouchY;

    private float mCurrentY;

    private DecelerateInterpolator decelerateInterpolator = new DecelerateInterpolator(10);

    public PerseiLayout(Context context) {
        this(context, null, 0);
    }

    public PerseiLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PerseiLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        if (isInEditMode()) {
            return;
        }

        if (getChildCount() > 1) {
            throw new RuntimeException("can only have one child widget");
        }
        TypedArray t = context.obtainStyledAttributes(attrs,R.styleable.PerseiLayout,defStyleAttr,0);
        mHeadHeight = t.getInteger(R.styleable.PerseiLayout_menu_height, Util.dip2px(getContext(),DEFAULT_HEIGHT));
        t.recycle();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        Context context = getContext();

        FrameLayout headViewLayout = new FrameLayout(context);
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
        layoutParams.gravity = Gravity.TOP;
        headViewLayout.setLayoutParams(layoutParams);
        mHeadLayout = headViewLayout;
        this.addView(mHeadLayout);

        mChildView = getChildAt(0);
    }
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        if (isOpenMenu) return true;
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mTouchY = ev.getY();
                mCurrentY = mTouchY;
                break;
            case MotionEvent.ACTION_MOVE:
                float currentY = ev.getY();
                float dy = currentY - mTouchY;
                if (dy > 0 && !canChildScrollUp()) {
                    return true;
                }
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        if (isOpenMenu) {
            return super.onTouchEvent(e);
        }

        switch (e.getAction()) {
            case MotionEvent.ACTION_MOVE:
                mCurrentY = e.getY();
                float dy = mCurrentY - mTouchY;
                dy = Math.min(mHeadHeight*2, dy);
                dy = Math.max(0, dy);
                if (mChildView != null) {
                    float offsetY = decelerateInterpolator.getInterpolation(dy / mHeadHeight/2 ) * dy/2 ;
                    float fraction = offsetY / mHeadHeight;
                    mHeadLayout.getLayoutParams().height = (int) offsetY;
                    mHeadLayout.requestLayout();
                    ViewCompat.setRotationX(mHeadLayout,90*(1.0f-fraction));
                    ViewCompat.setTranslationY(mChildView, offsetY);
                    mHeadLayout.setPivotX(mHeadLayout.getWidth()/2);
                    mHeadLayout.setPivotY(offsetY);

                }
                return true;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                if (mChildView != null) {
                    if (ViewCompat.getTranslationY(mChildView) >= mHeadHeight) {
                        createAnimatorTranslationY(mChildView, mHeadHeight, mHeadLayout);
                        isOpenMenu = true;
                    } else {
                        createAnimatorToHeadView(mHeadLayout,ViewCompat.getRotationX(mHeadLayout));
                        this.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                createAnimatorTranslationY(mChildView, 0, mHeadLayout);
                            }
                        },50);
                    }
                }
                return true;
        }
        return super.onTouchEvent(e);
    }



    public void createAnimatorToHeadView(final View v, final float angle)
    {
        ViewPropertyAnimatorCompat viewPropertyAnimatorCompat = ViewCompat.animate(v);
        viewPropertyAnimatorCompat.setDuration(200);
        viewPropertyAnimatorCompat.setInterpolator(new DecelerateInterpolator());
        viewPropertyAnimatorCompat.rotationX(90);
        viewPropertyAnimatorCompat.start();
        viewPropertyAnimatorCompat.setUpdateListener(new ViewPropertyAnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(View view) {
                float height = ViewCompat.getTranslationY(mChildView);
                mHeadLayout.setPivotX(mHeadLayout.getWidth() / 2);
                mHeadLayout.setPivotY(height);
            }
        });
    }
    public void createAnimatorTranslationY(final View v, final float h, final FrameLayout fl) {
        ViewPropertyAnimatorCompat viewPropertyAnimatorCompat = ViewCompat.animate(v);
        viewPropertyAnimatorCompat.setDuration(200);
        viewPropertyAnimatorCompat.setInterpolator(new DecelerateInterpolator());
        viewPropertyAnimatorCompat.translationY(h);
        viewPropertyAnimatorCompat.start();
        viewPropertyAnimatorCompat.setUpdateListener(new ViewPropertyAnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(View view) {
                float height = ViewCompat.getTranslationY(v);
                fl.getLayoutParams().height = (int) height;
                fl.requestLayout();
            }
        });
    }

    public void closeMenu()
    {
        createAnimatorToHeadView(mHeadLayout,ViewCompat.getRotationX(mHeadLayout));
       this.postDelayed(new Runnable() {
           @Override
           public void run() {
               closeMenuing();
           }
       },200);
    }

    private void closeMenuing() {
        if (mChildView != null) {
            ViewPropertyAnimatorCompat viewPropertyAnimatorCompat = ViewCompat.animate(mChildView);
            viewPropertyAnimatorCompat.setDuration(200);
            viewPropertyAnimatorCompat.y(ViewCompat.getTranslationY(mChildView));
            viewPropertyAnimatorCompat.translationY(0);
            viewPropertyAnimatorCompat.setInterpolator(new DecelerateInterpolator());
            viewPropertyAnimatorCompat.start();
        }
        isOpenMenu = false;
    }

    public void setHeaderView(final View headerView) {
        post(new Runnable() {
            @Override
            public void run() {
                mHeadLayout.addView(headerView);
            }
        });
    }

    public boolean canChildScrollUp() {
        if (mChildView == null) {
            return false;
        }
        if (Build.VERSION.SDK_INT < 14) {
            if (mChildView instanceof AbsListView) {
                final AbsListView absListView = (AbsListView) mChildView;
                return absListView.getChildCount() > 0
                        && (absListView.getFirstVisiblePosition() > 0 || absListView.getChildAt(0)
                        .getTop() < absListView.getPaddingTop());
            } else {
                return ViewCompat.canScrollVertically(mChildView, -1) || mChildView.getScrollY() > 0;
            }
        } else {
            return ViewCompat.canScrollVertically(mChildView, -1);
        }
    }


}
