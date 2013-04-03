package com.example.squares;

import android.graphics.Canvas;

public interface Shape {

	public boolean clicked(float x, float y);
	
	public void drawShape(Canvas s);
	
	public String addText(String text);
	
	public int getNumber();
	
	public boolean isComplete();
}
