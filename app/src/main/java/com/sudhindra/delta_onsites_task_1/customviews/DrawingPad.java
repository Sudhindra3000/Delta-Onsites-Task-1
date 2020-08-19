package com.sudhindra.delta_onsites_task_1.customviews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.view.MotionEventCompat;

import java.util.ArrayList;

public class DrawingPad extends View {

    private Path path;
    private Paint paint;

    private DrawingPadListener listener;

    public interface DrawingPadListener {
        void onPathChanged(Path path);
    }

    public void setPath(Path path) {
        this.path = path;
        invalidate();
    }

    public void setListener(DrawingPadListener listener) {
        this.listener = listener;
    }

    public DrawingPad(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        path = new Path();

        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(5f);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(path, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                path.moveTo(x, y);
                break;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(x, y);
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                return false;
        }
        listener.onPathChanged(path);
        return true;
    }
}
