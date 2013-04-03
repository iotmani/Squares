package com.example.squares;

import java.util.List;


public class NumberCreator implements ShapeCreator {

	private final int rows = 3;
	private final int columns = 3;
	private final int circle_nums = 4;
	private final int space = 1;
	private final int index_start = 10;
	private int squareWidth = 0;
	private int squareHeight = 0;
	private boolean isDrawn = false;
	private List<Shape> boxes;
	private PuzzleCreator puzzle;
	private int defaultTextSize;
	
	public NumberCreator(List<Shape> boxes, PuzzleCreator puzzle, int textSize){
		this.boxes = boxes;
		this.puzzle = puzzle;
		this.defaultTextSize = textSize;
	}
	
	public void createShape(int width, int height) {
		isDrawn = true;
		float x = index_start; 
		float y = index_start;
		squareWidth = (width / rows) - index_start;
		squareHeight = ((height - (squareWidth / 2)) / columns) - index_start ;
		
		int squarecounter  = 0;
		for(int i = 0; i<rows; i++){
			for(int j = 0; j<columns; j++){
				boxes.add(new NumberBox(x, y, x+squareWidth, y+squareHeight, getBoxGroupID(squarecounter), defaultTextSize));
				squarecounter++;
				x +=  squareWidth + space;
			}
			x = index_start;
			y += squareHeight + space;
		}
		
		float radius = squareWidth / 4;
		  x = index_start + squareWidth; 
		  y = index_start + squareHeight;
		int circlecounter = 1;
		for(int i = 0; i < circle_nums/ 2; i++){
			for(int j = 0; j < circle_nums / 2; j++){
				boxes.add(new CircleBox(x, y, radius, getSumCircle(circlecounter, false), defaultTextSize));
				circlecounter++;
				x += squareWidth;
			}
			x = index_start + squareWidth;
			y += squareHeight;
		}
		
		x = index_start + radius + index_start; 
		y = index_start + (squareHeight * columns) + radius + index_start;
		for(int i = 0; i < 3; i++){
			boxes.add(new CircleBox(x, y, radius, getSumCircle(i, true), getGroupID(i), defaultTextSize));
			x += squareWidth; 
		}
	}
	
	private int getGroupID(int squarecounter) {
		return Groups.getGroupColor(squarecounter);
	}
	
	private int getBoxGroupID(int squarecounter) {
		return Groups.getGroupColor(puzzle.findMyGroup(squarecounter));
	}

	private SumCircle getSumCircle(int i, boolean groupbox) {
		return puzzle.getSumCircle(i, boxes, groupbox);
	}

	public boolean isDrawn() {
		return isDrawn;
	}

}
