package com.example.pkl.animation;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    AnimationDrawable mAnimation;
    ImageView img;
    int x=0,y=0;
    int w=0,h=0;
    int num_img = 23;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Drawable d = getResources().getDrawable(R.drawable.b1);
        h = d.getIntrinsicHeight();
        w = d.getIntrinsicWidth();

        img = (ImageView) findViewById(R.id.img);
        BitmapDrawable[] frame = new BitmapDrawable[num_img];
        int i=0;

        for(i=1; i<=num_img-1; i++){
            frame[i] = (BitmapDrawable)getResources().getDrawable(
                    getResources().getIdentifier((String)"b"+i, "drawable", this.getPackageName()) );
        }

        int reasonableDuration = 200;
        mAnimation = new AnimationDrawable();
        for(i=1; i<=num_img-1; i++){
            mAnimation.addFrame(frame[i], reasonableDuration);
        }

        img.setImageDrawable(mAnimation);
/*
        TranslateAnimation tAnimation = new TranslateAnimation(280, 0, 0, 0);
        tAnimation.setDuration(5000);
        tAnimation.setFillAfter(true);
        tAnimation.setRepeatCount(Animation.INFINITE);
        tAnimation.setRepeatMode(Animation.REVERSE);
        img.setAnimation(tAnimation);
*/
/*
        RotateAnimation rAnim = new RotateAnimation(0f, 180f,
        		RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        rAnim.setStartOffset(0);
        rAnim.setDuration(10000);
        rAnim.setFillAfter(true);
        rAnim.setRepeatCount(Animation.INFINITE);
        rAnim.setRepeatMode(Animation.RESTART);
        img.setAnimation(rAnim);
*/

        ScaleAnimation sAnimation = new ScaleAnimation(1f, 2f, 1f, 2f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        sAnimation.setDuration(10000);
        sAnimation.setFillAfter(true);
        sAnimation.setRepeatCount(Animation.INFINITE);
        sAnimation.setRepeatMode(Animation.RESTART);
        img.setAnimation(sAnimation);


        final Button strtbtn = (Button) findViewById(R.id.strtbtn);
        strtbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mAnimation.start();
                mAnimation.setOneShot(false);
            }
        });

        final Button stpbtn = (Button) findViewById(R.id.stpbtn);
        stpbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mAnimation.stop();
            }
        });
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            TranslateAnimation tAnimation = new TranslateAnimation(x, ((int)event.getX()-(w/2)), y, ((int)event.getY()-h));
            x = (int)event.getX()-(w/2);
            y = (int)event.getY()-h;
            tAnimation.setDuration(1000);
            tAnimation.setFillAfter(true);
            tAnimation.setRepeatCount(0);
            tAnimation.setRepeatMode(Animation.RESTART);
            img.startAnimation(tAnimation);
            return true;
        }
        return super.onTouchEvent(event);
    }
}
