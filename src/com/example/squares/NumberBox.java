package com.example.squares;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;

public class NumberBox implements Shape{

	private float left;
	private float top;

	private float right;
	private float bottom;
	private RectF rectangle;
	private Paint numberPaint;
	private Paint paint;
	private String number = "";
	private float numberleft;
	private float numbertop;
	
	public NumberBox(float left, float top, float right, float bottom, int color, float textSize){
		this(left, top, right, bottom, color, "", textSize);
	}
	
	public NumberBox(float left, float top, float right, float bottom, int color, String number, float textSize){
		this.left = left;
		this.top = top;
		this.right = right;
		this.bottom = bottom;
		this.number = number;
		paint = new Paint();
		paint.setColor(color);
		paint.setStrokeWidth(3);
		numberPaint = new Paint();
		numberPaint.setAntiAlias(true);
		numberPaint.setColor(Color.BLACK);
		numberPaint.setStyle(Style.FILL);
		numberPaint.setStrokeWidth(10);
		numberPaint.setTextSize(textSize);
		numberPaint.setTextAlign(Align.CENTER);
		numberleft = (right - left) / 2;
		numbertop = ( bottom - top) / 2;
	}
	
	private RectF getRectangle(){
		if(rectangle == null)
			rectangle = new RectF(left, top, right, bottom);
		return rectangle;
	}
	
	public Paint getPaint(){
		return paint;
	}
	
	public boolean clicked(float x, float y){
		if( checkCoordinates(x, y) ){
			return true;
		}
		return false;
	}
	
	private boolean checkCoordinates(float x, float y){
		return (x >= left && x <= right) && (y >= top && y <= bottom);
	}

	public void drawShape(Canvas s) {
		s.drawRect(getRectangle(), paint);
		s.drawText(number, left+numberleft, top+numbertop, numberPaint);
	}

	public String addText(String text) {
		String oldValue = number;
		number = text;
		return oldValue;
	}

	public int getNumber() {
		if(number != null && !number.isEmpty())
			return Integer.parseInt(number);
		return 0;
	}

	public boolean isComplete() {
		return true;
	}
}
