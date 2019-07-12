package com.cq.bottomnavigationdemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

/**
 * Created by ${YangJunJin}
 * on 2019/7/12
 */
public class ChangeColorIconWithTextView extends View {

    private Bitmap mBitmap;
    private Canvas mCanvas;
    private Paint mPaint;
    /**
     * 颜色
     */
    private int mColor = 0xCECECECE;
    /**
     * 透明度 0.0-1.0
     */
    private float mAlpha = 0f;
    /**
     * 图标
     */
    private Bitmap mIconBitmap;
    /**
     * 限制绘制icon的范围,包括图标文字
     */
    private Rect mIconRect;
    /**
     * icon底部文本
     */
    private String mText = "微信";
    private int mTextSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 10, getResources().getDisplayMetrics());
    private Paint mTextPaint;
    private Rect mTextBound = new Rect();

    public ChangeColorIconWithTextView(Context context) {
        super(context);
    }

    /**
     * 初始化自定义属性值
     *
     * @param context
     * @param attrs
     */
    public ChangeColorIconWithTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        // 获取设置的图标
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.ChangeColorIconView);

        int n = a.getIndexCount();
        for (int i = 0; i < n; i++) {

            int attr = a.getIndex(i);
            switch (attr) {
                case R.styleable.ChangeColorIconView_icon:
                    BitmapDrawable drawable = (BitmapDrawable) a.getDrawable(attr);
                    mIconBitmap = drawable.getBitmap();
                    break;
                case R.styleable.ChangeColorIconView_color:
                    mColor = a.getColor(attr, 0x45C01A);
                    break;
                case R.styleable.ChangeColorIconView_text:
                    mText = a.getString(attr);
                    break;
                case R.styleable.ChangeColorIconView_text_size:
                    mTextSize = (int) a.getDimension(attr, TypedValue
                            .applyDimension(TypedValue.COMPLEX_UNIT_SP, 10,
                                    getResources().getDisplayMetrics()));
                    break;

            }
        }

        a.recycle();

        mTextPaint = new Paint();
        mTextPaint.setTextSize(mTextSize);
        mTextPaint.setColor(0xff555555);
        // 得到text绘制范围
        mTextPaint.getTextBounds(mText, 0, mText.length(), mTextBound);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        // 得到绘制icon的宽
        int bitmapWidth = Math.min(getMeasuredWidth() - getPaddingLeft()
                - getPaddingRight(), getMeasuredHeight() - getPaddingTop()
                - getPaddingBottom() - mTextBound.height());

        int left = getMeasuredWidth() / 2 - bitmapWidth / 2;
        int top = (getMeasuredHeight() - mTextBound.height()) / 2 - bitmapWidth
                / 2;
        // 设置icon的绘制范围
        mIconRect = new Rect(left, top, left + bitmapWidth, top + bitmapWidth);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        //1、计算alpha（默认为0）
        int alpha = (int) Math.ceil((255 * mAlpha));
        //2、第一次描绘，绘制原图
        canvas.drawBitmap(mIconBitmap, null, mIconRect, null);
        setupTargetBitmap(alpha);
//        6、绘制原文本
        drawSourceText(canvas, alpha);
//        7、绘制设置alpha和颜色后的文本
        drawTargetText(canvas, alpha);
//        8、将内存中的bitmap绘制出来
        canvas.drawBitmap(mBitmap, 0, 0, null);
    }

    /**
     * 第二次描绘
     * 3、在绘图区域，绘制一个纯色块（设置了alpha），此步绘制在内存的bitmap上
     *
     * @param alpha
     */
    private void setupTargetBitmap(int alpha) {
        mBitmap = Bitmap.createBitmap(getMeasuredWidth(), getMeasuredHeight(),
                Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
        mPaint = new Paint();
        mPaint.setColor(mColor);
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setAlpha(alpha);
        mCanvas.drawRect(mIconRect, mPaint);
//        4、设置mode，针对内存中的bitmap上的paint
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        mPaint.setAlpha(255);
//        5、绘制我们的图标，此步绘制在内存的bitmap上
        mCanvas.drawBitmap(mIconBitmap, null, mIconRect, mPaint);
    }

    /**
     * 没有被选中的颜色
     *
     * @param canvas
     * @param alpha
     */
    private void drawSourceText(Canvas canvas, int alpha) {
        mTextPaint.setTextSize(mTextSize);
//        mTextPaint.setColor(0xff990033);
        mTextPaint.setColor(0xff333333);
        mTextPaint.setAlpha(255 - alpha);
        canvas.drawText(mText, mIconRect.left + mIconRect.width() / 2
                        - mTextBound.width() / 2,
                mIconRect.bottom + mTextBound.height(), mTextPaint);
    }

    /**
     * 选中的icon
     *
     * @param canvas
     * @param alpha
     */
    private void drawTargetText(Canvas canvas, int alpha) {
//        mTextPaint.setColor(mColor);
        mTextPaint.setColor(0xff990033);
        mTextPaint.setAlpha(alpha);
        canvas.drawText(mText, mIconRect.left + mIconRect.width() / 2
                        - mTextBound.width() / 2,
                mIconRect.bottom + mTextBound.height(), mTextPaint);
    }

    public void setIconAlpha(float alpha) {
        this.mAlpha = alpha;
        invalidateView();
    }

    private void invalidateView() {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            invalidate();
        } else {
            postInvalidate();
        }
    }
}
