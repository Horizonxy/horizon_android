package com.horizon.android.widget;

import com.horizon.android.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

public class ChangeColorIcon extends View {

	private Context mCxt;
	/**
	 * 图标
	 */
	private Bitmap mIconBitmap;
	/**
	 * 颜色
	 */
	private int mColor = 0xFF45C01A;
	/**
	 * 透明度 0.0-1.0
	 */
	private float mAlpha = 0f;
	/**
	 * icon底部文本
	 */
	private String mText;
	/**
	 * 底部文本大小
	 */
	private int mTextSize;

	private Paint mTextPaint;
	private Rect mTextBound = new Rect();

	/**
	 * 限制绘制icon的范围
	 */
	private Rect mIconRect;

	
	private Bitmap mBitmap;
	private Canvas mCanvas;
	private Paint mPaint;
	
	public ChangeColorIcon(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context, attrs);
	}

	public ChangeColorIcon(Context context) {
		this(context, null);
	}

	private void init(Context context, AttributeSet attrs) {
		mCxt = context;

		initAttrs(attrs);
		initTextPaint();
	}

	private void initTextPaint() {
		mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mTextPaint.setTextSize(mTextSize);
		mTextPaint.setColor(mColor);
		// 得到text绘制范围
		mTextPaint.getTextBounds(mText, 0, mText.length(), mTextBound);
	}

	private void initAttrs(AttributeSet attrs) {
		TypedArray a = mCxt.obtainStyledAttributes(attrs, R.styleable.ChangeColorIcon);

		int n = a.getIndexCount();
		for (int i = 0; i < n; i++) {
			int attr = a.getIndex(i);
			if (attr == R.styleable.ChangeColorIcon_ico_drawable) {
				BitmapDrawable drawable = (BitmapDrawable) a.getDrawable(attr);
				mIconBitmap = drawable.getBitmap();
			} else if (attr == R.styleable.ChangeColorIcon_ico_color) {
				mColor = a.getColor(attr, 0x45C01A);
			} else if (attr == R.styleable.ChangeColorIcon_ico_text) {
				mText = a.getString(attr);
			} else if (attr == R.styleable.ChangeColorIcon_ico_text_size) {
				mTextSize = (int) a.getDimensionPixelOffset(attr, 12);
			}
		}
		a.recycle();
	}

	@SuppressLint("DrawAllocation")
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

		// 得到绘制icon的宽
		int bitmapWidth = Math.min(getMeasuredWidth() - getPaddingLeft() - getPaddingRight(),
				getMeasuredHeight() - getPaddingTop() - getPaddingBottom() - mTextBound.height());

		int left = getMeasuredWidth() / 2 - bitmapWidth / 2;
		int top = (getMeasuredHeight() - mTextBound.height()) / 2 - bitmapWidth / 2;
		// 设置icon的绘制范围
		mIconRect = new Rect(left, top, left + bitmapWidth, top + bitmapWidth);
	}

	@SuppressLint("DrawAllocation")
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);

		int alpha = (int) Math.ceil((255 * mAlpha));
		canvas.drawBitmap(mIconBitmap, null, mIconRect, null);

		mBitmap = Bitmap.createBitmap(getMeasuredWidth(), getMeasuredHeight(), Config.ARGB_8888);
		mCanvas = new Canvas(mBitmap);
		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mPaint.setAntiAlias(true);
		mPaint.setDither(true);
		mPaint.setColor(mColor);
		mPaint.setAlpha(alpha);
		mCanvas.drawRect(mIconRect, mPaint);
		mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
		mPaint.setAlpha(255);
		mCanvas.drawBitmap(mIconBitmap, null, mIconRect, mPaint);

		mTextPaint.setColor(0x1196db);
		mTextPaint.setAlpha(255 - alpha);
		canvas.drawText(mText, mIconRect.left + mIconRect.width() / 2 - mTextBound.width() / 2,
				mIconRect.bottom + mTextBound.height(), mTextPaint);

		mTextPaint.setColor(mColor);
		mTextPaint.setAlpha(alpha);
		canvas.drawText(mText, mIconRect.left + mIconRect.width() / 2 - mTextBound.width() / 2,
				mIconRect.bottom + mTextBound.height(), mTextPaint);

		canvas.drawBitmap(mBitmap, 0, 0, null);
	}

	public void setIconAlpha(float alpha) {
		this.mAlpha = alpha;
		postInvalidate();
	}
}
