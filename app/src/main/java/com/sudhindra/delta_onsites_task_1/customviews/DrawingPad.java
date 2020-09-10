package com.sudhindra.delta_onsites_task_1.customviews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.util.Pair;

import com.sudhindra.delta_onsites_task_1.R;

import java.util.ArrayList;

public class DrawingPad extends View {

    private ArrayList<Pair<Path, Paint>> allPairs;
    private boolean eraserOn;
    private String[] colors;
    private int colorIndex = 0;

    private DrawingPadListener listener;

    public interface DrawingPadListener {
        void onDrawingChanged(ArrayList<Pair<Path, Paint>> newList);
    }

    public void setAllPairs(ArrayList<Pair<Path, Paint>> allPairs) {
        this.allPairs = allPairs;
        invalidate();
    }

    public void setEraserOn(boolean eraserOn) {
        this.eraserOn = eraserOn;
    }

    public void setColorIndex(int colorIndex) {
        this.colorIndex = colorIndex;
    }

    public void setListener(DrawingPadListener listener) {
        this.listener = listener;
    }

    public DrawingPad(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        allPairs = new ArrayList<>();
        colors = context.getResources().getStringArray(R.array.scribbleColors);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (Pair<Path, Paint> pair : allPairs) {
            assert pair.first != null;
            assert pair.second != null;
            canvas.drawPath(pair.first, pair.second);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (!eraserOn)
                    allPairs.add(getDrawingPair(x, y));
                else
                    allPairs.add(getEraserPair(x, y));
                break;
            case MotionEvent.ACTION_MOVE:
                allPairs.get(allPairs.size() - 1).first.lineTo(x, y);
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                return false;
        }
        listener.onDrawingChanged(allPairs);
        return true;
    }

    private Pair<Path, Paint> getDrawingPair(float x, float y) {
        Path path = new Path();
        path.moveTo(x, y);
        Paint paint = new Paint();
        paint.setColor(Color.parseColor(colors[colorIndex]));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(10f);
        return new Pair<>(path, paint);
    }

    private Pair<Path, Paint> getEraserPair(float x, float y) {
        Path eraserPath;
        Paint eraserPaint;
        eraserPath = new Path();
        eraserPath.moveTo(x, y);
        eraserPaint = new Paint();
        eraserPaint.setColor(getContext().getResources().getColor(R.color.scribblePadColor));
        eraserPaint.setStyle(Paint.Style.STROKE);
        eraserPaint.setStrokeCap(Paint.Cap.ROUND);
        eraserPaint.setStrokeJoin(Paint.Join.ROUND);
        eraserPaint.setAntiAlias(true);
        eraserPaint.setStrokeWidth(30f);
        return new Pair<>(eraserPath, eraserPaint);
    }
}
