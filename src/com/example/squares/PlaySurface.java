package com.example.squares;

import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

public class PlaySurface extends View {

	private List<Shape> boxes;
	private NumberClick listener;
	private boolean shouldListener = true;
	private ShapeCreator shapeCreator;
	private PuzzleCreator creator;
	
	public PlaySurface(Context context) {
		super(context);
		boxes = new ArrayList<Shape>();
		creator = new PuzzleCreator();
		shapeCreator = new NumberCreator(boxes, creator, UtilHelper.getTextSize(context));
	}
	
	
	public void onDraw(Canvas c){
		if(!shapeCreator.isDrawn())
			shapeCreator.createShape(getWidth(), getHeight());
		
		for(Shape box : boxes){
				box.drawShape(c);
		}

	}
	
	public boolean onTouchEvent(MotionEvent event){
		if(shouldListener){
			for(int i = 0; i<boxes.size(); i++){
				if(boxes.get(i).clicked(event.getX(), event.getY())){
					transformClick(boxes.get(i));
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean complete(){
		for(Shape box : boxes){
			if(!box.isComplete())
				return false;
		}
		return true;
	}
	
	public void reset(){
		boxes.clear();
		creator = new PuzzleCreator();
		shapeCreator = new NumberCreator(boxes, creator, UtilHelper.getTextSize(getContext()));
		shouldListener = true;
	}
	
	public void transformClick(Shape box){
		shouldListener = false;
		listener.numberClicked(box);
		this.invalidate();
		shouldListener = true;
	}

	public void addNumberListener(NumberClick listener) {
		this.listener = listener;
	}
}
