package com.example.imegescale;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

import androidx.annotation.NonNull;

public class MyImageView extends View {

    Drawable image;
    ScaleGestureDetector gestureDetector;
    float scale = 1f;

    public MyImageView(Context context) {
        super(context);
        image = context.getResources().getDrawable(R.drawable.starry_night);
        image.setBounds(0,0,image.getIntrinsicWidth(),image.getIntrinsicHeight());
        //이미지의 처음 크기 갋ㅅ을 분석한다.
        gestureDetector = new ScaleGestureDetector(context, new ScaleListerner());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        canvas.scale(scale, scale);
        image.draw(canvas);
        canvas.restore();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        invalidate();
        return true;

    }



    class ScaleListerner extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(@NonNull ScaleGestureDetector detector) {
            scale = scale * detector.getScaleFactor();
            if (scale > 10f )
                scale = 10f;
            if (scale <0.1f)
                scale = 1f;

            return true;
        }
        // 메소드를 오버라이딩 해야한다.

    }

}
