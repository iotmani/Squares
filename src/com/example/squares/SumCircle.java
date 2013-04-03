package com.example.squares;

import java.util.List;

public class SumCircle {
	
	private int sum;
	private List<Shape> boxes;
	public SumCircle(int sum, List<Shape> boxes){
		this.sum = sum;
		this.boxes = boxes;
	}
	
	public boolean complete(){
		int boxsum = 0;
		for(Shape box : boxes){
			boxsum += box.getNumber();
		}
		return sum == boxsum;
	}
	
	public int getNumber(){
		return sum;
	}

}
