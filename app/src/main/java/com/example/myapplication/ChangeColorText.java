
package com.example.myapplication;
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
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

public class ChangeColorText extends View {

	int mColor = 0xFF45C01A;
	String mText = "微信";
	int mTextSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
			12, getResources().getDisplayMetrics());

	Canvas mCanvas;
	Bitmap mBitmap;
	Paint mPaint;
	Bitmap mIconBitma;

	float mAlpha;     //图标和文字的颜色设置
	Rect mIconRect;   //图标的边框
	Rect mTextBound;  //文字的边框

	Paint mTextPaint; //文本的画笔

	public ChangeColorText(Context context) {
		super(context);
	}

	/**
	 * 获取自定义属性的值
	 */
	public ChangeColorText(Context context, AttributeSet attrs) {
		super(context, attrs);

		//TypedArray实例是个属性的容器，通过context.obtainStyledAttributes()方法返回得到。
		TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.ChangeColorText);

		// 获取各个自定义属性的值
		int n = a.getIndexCount();
		for (int i = 0; i < n; i++) {
			int attr = a.getIndex(i);
			switch (attr) {
				case R.styleable.ChangeColorText_icon1:
					BitmapDrawable drawable = (BitmapDrawable)a.getDrawable(attr);
					mIconBitma = drawable.getBitmap();
					break;
				case R.styleable.ChangeColorText_color1:
					mColor = a.getColor(attr, 0xFF45C01A);
					break;
				case R.styleable.ChangeColorText_text:
					mText = a.getString(attr);
					break;

				case R.styleable.ChangeColorText_textSize:
					mTextSize = (int) a.getDimension(attr, TypedValue
							.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12,
									getResources().getDisplayMetrics()));
					break;
				default:
					break;
			}
		}

		a.recycle(); // 这里一定要回收掉

		// 初始化一些变量
		mTextBound = new Rect();  //文字边框
		mTextPaint = new Paint(); //文字画笔
		mTextPaint.setTextSize(mTextSize); //文字大小
		mTextPaint.setColor(0xff555555);   //文字颜色
		mTextPaint.getTextBounds(mText, 0, mText.length(), mTextBound);  //
	}

	@SuppressLint("DrawAllocation")
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// 测量图标的宽高 
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		int iconWidth = Math.min(getMeasuredWidth() - getPaddingLeft()
				- getPaddingRight(), getMeasuredHeight() - getPaddingTop()
				- getPaddingBottom() - mTextBound.height());

		int left = getMeasuredWidth() / 2 - iconWidth / 2;
		int top = (getMeasuredHeight() - mTextBound.height()) / 2 - iconWidth
				/ 2;
		mIconRect = new Rect(left, top, left + iconWidth, top + iconWidth);
	}

	@Override
	protected void onDraw(Canvas canvas) {

		// 绘制控件
		canvas.drawBitmap(mIconBitma, null, mIconRect,null);

		int alpha = (int) Math.ceil(255 * mAlpha);

		// 内存去准备mBitmap , setAlpha , 纯色 ，xfermode ， 图标
		setUpTargetBitmap(alpha);

		// 1、绘制原文本
		drawSourceText(canvas,alpha);
		// 2、绘制变色的文本
		drawTargetText(canvas,alpha);

		canvas.drawBitmap(mBitmap, 0, 0,null);
	}

	/**  绘制变色的文本*/
	private void drawTargetText(Canvas canvas, int alpha) {
		mTextPaint.setColor(mColor);
		mTextPaint.setAlpha(alpha);

		int x = getMeasuredWidth()/2 - mTextBound.width()/2;
		int y = mIconRect.bottom + mTextBound.height();
		canvas.drawText(mText,x , y, mTextPaint);
	}

	/**绘制原文本*/
	private void drawSourceText(Canvas canvas, int alpha) {
		mTextPaint.setColor(0xff333333);
		mTextPaint.setAlpha(255);
		int x = getMeasuredWidth()/2 - mTextBound.width()/2;
		int y = mIconRect.bottom + mTextBound.height();
		canvas.drawText(mText,x , y, mTextPaint);
	}
	/** 在内存中绘制可变色的Icon */
	private void setUpTargetBitmap(int alpha) {
		mBitmap = Bitmap.createBitmap(getMeasuredWidth(),getMeasuredHeight(),Config.ARGB_8888);

		mCanvas = new Canvas(mBitmap);
		mPaint = new Paint();
		mPaint.setColor(mColor);
		mPaint.setAntiAlias(true);
		mPaint.setDither(true);
		mPaint.setAlpha(alpha);
		mCanvas.drawRect(mIconRect, mPaint);

		mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
		mPaint.setAlpha(255);
		mCanvas.drawBitmap(mIconBitma, null, mIconRect,mPaint);
	}

	private static final String INSTANCE_STATUS = "instance_status";
	private static final String STATUS_ALPHA = "status_alpha";

	@Override
	protected Parcelable onSaveInstanceState() {
		Bundle bundle = new Bundle();
		bundle.putParcelable(INSTANCE_STATUS, super.onSaveInstanceState());
		bundle.putFloat(STATUS_ALPHA, mAlpha);
		return bundle;
	}
	@Override
	protected void onRestoreInstanceState(Parcelable state) {
		if (state instanceof Bundle) {
			Bundle bundle = (Bundle) state;
			mAlpha = bundle.getFloat(STATUS_ALPHA);
			super.onRestoreInstanceState(bundle.getParcelable(INSTANCE_STATUS));
			return;
		}
		super.onRestoreInstanceState(state);
	}

	/**
	 * 设置透明度
	 * @param alpha
	 */
	public void setIconAlpha(float alpha){
		this.mAlpha = alpha;
		invalidateView();
	}

	/** 重绘 */
	private void invalidateView() {
		if (Looper.getMainLooper() == Looper.myLooper()) {
			invalidate();
		}else {
			postInvalidate();
		}
	}
}