package com.wenchao.superirregularview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * @author wenchao
 * @date 2019/7/14.
 * @time 21:09
 * description：
 */
public class IrregularView extends View {

    private int width = -1;
    private int height = -1;

    private Bitmap bitmap;

    public IrregularView(Context context) {
        super(context);
    }

    public IrregularView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public IrregularView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        if (action != MotionEvent.ACTION_DOWN && action != MotionEvent.ACTION_UP) {
            return super.onTouchEvent(event);
        }

        int x = (int) event.getX();
        int y = (int) event.getY();

        if (width == -1 || height == -1) {
            width = getWidth();
            height = getHeight();
            Drawable drawable = getBackground();
            bitmap = ((BitmapDrawable) drawable).getBitmap();
        }

        if (bitmap == null || x < 0 || y < 0 || x >= width || y >= height) {
            return false;
        }

        final int pixel = bitmap.getPixel((int) (x * bitmap.getWidth() / (float) width),
                (int) (y * bitmap.getHeight() / (float) height));
        if (Color.TRANSPARENT == pixel) {
            return false;
        }

        return super.onTouchEvent(event);
    }
}
