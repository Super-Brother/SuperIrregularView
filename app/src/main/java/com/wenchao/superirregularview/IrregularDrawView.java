package com.wenchao.superirregularview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * @author wenchao
 * @date 2019/7/14.
 * @time 21:56
 * description：
 */
public class IrregularDrawView extends View {

    private int cx;
    private int cy;
    private Paint paint;
    private int[] colors = {0xFFD21D22, 0xFFFBD109, 0xFF4BB748, 0xFF2F7ABB};
    private int width = -1;
    private int height = -1;
    private Bitmap bitmap;

    public IrregularDrawView(Context context) {
        this(context, null);
    }

    public IrregularDrawView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IrregularDrawView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(1);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        width = getMeasuredWidth();
        height = getMeasuredHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        Canvas canvasTemp = new Canvas(bitmap);

        cx = width / 2;
        cy = height / 2;

        int innerCr = 40;
        int outerCr = 50;
        int wholeCr = 100;

        RectF innerRectF = new RectF(cx - outerCr, cy - outerCr, cx + outerCr, cy + outerCr);
        RectF outerRectF = new RectF(cx - wholeCr, cy - wholeCr, cx + wholeCr, cy + wholeCr);

        Path path = new Path();
        //绘制红色区域
        path.addArc(innerRectF, 150, 120);
        path.lineTo((float) (cx + Math.sqrt(3) * outerCr), cy - outerCr);
        path.addArc(outerRectF, -30, -120);
        path.lineTo((float) (cx - Math.sqrt(3) * outerCr / 2), cy + outerCr / 2);
        paint.setColor(colors[0]);
        canvas.drawPath(path, paint);

        //黄色区域
        Matrix matrix = new Matrix();
        matrix.setRotate(120, cx, cy);
        path.transform(matrix);
        paint.setColor(colors[1]);
        canvas.drawPath(path, paint);

        //绿色区域
        path.transform(matrix);
        paint.setColor(colors[2]);
        canvas.drawPath(path, paint);

        paint.setColor(Color.WHITE);
        canvasTemp.drawCircle(cx, cy, outerCr, paint);

        paint.setColor(colors[3]);
        canvasTemp.drawCircle(cx, cy, innerCr, paint);

        canvas.drawBitmap(bitmap, 0, 0, paint);
        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        if (action != MotionEvent.ACTION_DOWN && action != MotionEvent.ACTION_UP) {
            return super.onTouchEvent(event);
        }

        int x = (int) event.getX();
        int y = (int) event.getY();

        if (bitmap == null || x < 0 || y < 0 || x >= width || y >= height) {
            return false;
        }

        final int pixel = bitmap.getPixel((int) (x * bitmap.getWidth() / (float) width),
                (int) (y * bitmap.getHeight() / (float) height));
        if (Color.TRANSPARENT == pixel) {
            return false;
        } else {
            this.setTag(this.getId(), 3);
            for (int i = 0; i < colors.length; i++) {
                if (colors[i] == pixel) {
                    this.setTag(this.getId(), i);
                    break;
                }
            }
        }

        return super.onTouchEvent(event);
    }
}
