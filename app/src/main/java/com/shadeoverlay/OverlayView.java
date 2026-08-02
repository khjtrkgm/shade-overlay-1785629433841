package com.shadeoverlay;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.os.SystemClock;
import android.view.View;

public class OverlayView extends View {
    private final Paint paint = new Paint();
    private String effect = "dim";
    private int color = 0xFF000000;
    private int alpha = 128;

    public OverlayView(Context c) { super(c); }

    public void setEffect(String e) { this.effect = e; invalidate(); }
    public void setBaseColor(int c) { this.color = c; invalidate(); }
    public void setAlphaValue(int a) { this.alpha = a; invalidate(); }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int w = getWidth();
        int h = getHeight();
        int a = alpha & 0xFF;
        paint.setStyle(Paint.Style.FILL);
        if (effect == null) effect = "dim";
        switch (effect) {
            case "none":
                canvas.drawColor(Color.argb(a, Color.red(color), Color.green(color), Color.blue(color)));
                break;
            case "dim":
                canvas.drawColor(Color.argb(a, 0, 0, 0));
                break;
            case "warm":
                canvas.drawColor(Color.argb(a, 255, 140, 0));
                break;
            case "cool":
                canvas.drawColor(Color.argb(a, 0, 120, 255));
                break;
            case "gradient":
                paint.setShader(new LinearGradient(0, 0, w, h,
                    Color.argb(a, Color.red(color), Color.green(color), Color.blue(color)),
                    Color.argb(a / 2, 0, 0, 0), Shader.TileMode.CLAMP));
                canvas.drawRect(0, 0, w, h, paint);
                paint.setShader(null);
                break;
            case "pulse":
                int t = (int) (SystemClock.uptimeMillis() / 30) % 510;
                int pa = a * (t < 255 ? t : 510 - t) / 255;
                canvas.drawColor(Color.argb(pa, Color.red(color), Color.green(color), Color.blue(color)));
                postInvalidateDelayed(30);
                break;
            case "scan":
                canvas.drawColor(Color.argb(a / 2, 0, 0, 0));
                paint.setColor(Color.argb(a, 0, 0, 0));
                for (int y = 0; y < h; y += 4) {
                    canvas.drawRect(0, y, w, y + 2, paint);
                }
                break;
            case "gray":
                canvas.drawColor(Color.argb(a, 90, 90, 90));
                break;
        }
    }
}
