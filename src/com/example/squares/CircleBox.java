package com.example.squares;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;

public class CircleBox implements Shape {

	private float cx;
	private float cy;
	private float numberCenterX;
	private float numberCenterY;
	private float radius;
	private Paint paint;
	private Paint numberPaint;
	private SumCircle number;
	private int color;
	private float textSize;
	
	public CircleBox(float cx, float cy, float radius, SumCircle circle, float textSize){
		this(cx, cy, radius, circle, Color.WHITE, textSize);
	}
	
	public CircleBox(float cx, float cy, float radius, SumCircle circle, int color, float textSize){
		this.cx = cx;
		this.cy = cy;
		this.numberCenterX = this.cx;
		this.numberCenterY = this.cy + 3;
		this.radius = radius;
		this.number = circle;
		this.color = color;
		this.textSize = textSize;
		createPaint();
	}
	
	private void createPaint(){
		paint = new Paint();
		paint.setColor(color);
		paint.setAntiAlias(true);
		numberPaint = new Paint();
		numberPaint.setAntiAlias(true);
		numberPaint.setColor(Color.BLACK);
		numberPaint.setStyle(Style.FILL);
		numberPaint.setStrokeWidth(5);
		numberPaint.setTextAlign(Align.CENTER);
		numberPaint.setTextSize(textSize);
	}

	public boolean clicked(float x, float y) {
		return false;
	}
	
	private boolean checkPoints(float x, float y){
		double d =  Math.pow(Math.abs(cx - x), 2) + Math.pow(Math.abs(cy - y), 2);
		d = Math.pow(d, 2);
		double radius = Math.pow(this.radius, 2);
		if(d < radius || d == radius)
			return true;
		else if (d > radius){
			return false;
		}
		return false;
	}


	public void drawShape(Canvas s) {
		if(number.complete()){
			paint.setColor(Color.GREEN);
		}else{
			paint.setColor(Color.BLACK);
		}
		s.drawCircle(cx, cy, radius, paint);
		paint.setColor(color);
		s.drawCircle(cx, cy, radius-2, paint);
		s.drawText(String.valueOf(number.getNumber()), numberCenterX, numberCenterY, numberPaint);
	}

	public String addText(String text) {
		return String.valueOf(number.getNumber());
	}

	public int getNumber() {
		return number.getNumber();
	}

	public boolean isComplete() {
		return number.complete();
	}
}
