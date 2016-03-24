package com.example.tm.materialtransitionanim;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Transition;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class Main2Activity extends AppCompatActivity {

    private ImageView ivBack;
    private ImageView header;
    private RelativeLayout rl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        initView();
    }

    private void initView() {
        ivBack = (ImageView) findViewById(R.id.iv_ab_back);
        rl = (RelativeLayout) findViewById(R.id.rl_act2);
        header = (ImageView) findViewById(R.id.iv_header);
        Intent intent = getIntent();
        final int image = intent.getIntExtra("image", R.drawable.fox);
        header.setImageResource(image);
        header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                performCircleReveal();
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Transition transition = getWindow().getSharedElementEnterTransition();
            transition.addListener(new Transition.TransitionListener() {
                @Override
                public void onTransitionStart(Transition transition) {

                }

                @Override
                public void onTransitionEnd(Transition transition) {
                    performCircleReveal();
                }

                @Override
                public void onTransitionCancel(Transition transition) {

                }

                @Override
                public void onTransitionPause(Transition transition) {

                }

                @Override
                public void onTransitionResume(Transition transition) {

                }
            });
        }
    }

    private void performCircleReveal(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            final float finalRadius = (float) Math.hypot(rl.getWidth(), rl.getHeight());
            Animator anim = ViewAnimationUtils.createCircularReveal(
                    rl,
                    (header.getLeft() + header.getRight()) / 2,
                    (header.getTop() + header.getBottom()) / 2,
                    (float) header.getWidth() / 2,
                    finalRadius);
            anim.setDuration(1000);
            anim.setInterpolator(new AccelerateDecelerateInterpolator());
            anim.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationStart(Animator animation) {
                    super.onAnimationStart(animation);
                    Log.e("---", "start anim");
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    Log.e("---", "end anim");
                    //show views
                }
            });
            anim.start();
        }
    }
}
