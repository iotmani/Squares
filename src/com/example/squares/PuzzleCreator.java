package com.example.squares;

import java.util.ArrayList;
import java.util.List;

public class PuzzleCreator {
	
	private List<String> number = new ArrayList<String>(); 
	private Integer[] positions;
	private int groupSizeOne = 0, groupSizeTwo = 0, groupSizeThree = 0, minGroup = 2, maxGroup = 4, groupSize = 9;
	
	public PuzzleCreator(){
		number.add("1");
		number.add("2");
		number.add("3");
		number.add("4");
		number.add("5");
		number.add("6");
		number.add("7");
		number.add("8");
		number.add("9");
		positions = new Integer[9];
		publishGroups();
		publishNumber();
	}
	
	private void publishGroups() {
		groupSizeOne = RandomGen.generateFromRange(minGroup, maxGroup);
		groupSizeTwo = RandomGen.generateFromRange(minGroup, maxGroup);

		int remainingSize = (groupSize - (groupSizeOne + groupSizeTwo));
		groupSizeThree = remainingSize;
		
	}


	public void publishNumber(){
		for(int i = 0; i<9 && !number.isEmpty(); i++){
			String pos = RandomGen.generateFromStringCollection(number);
			number.remove(pos);
			positions[i] = Integer.parseInt(pos);
			if(number.size() == 1){
				positions[i+1] = Integer.parseInt(number.get(0));
				number.remove(0);
			}
		}
	}
	
	public SumCircle getSumCircle(int index, List<Shape> shapes, boolean groupbox){
		if(groupbox){
			return findMyNumber(groupSizeOne, index, shapes);
		}else{
			List<Shape> results = new ArrayList<Shape>();
			results.add(shapes.get(tranNumber(index)));
			results.add(shapes.get(index));
			results.add(shapes.get(index+3));
			results.add(shapes.get(index+4));
			return new SumCircle(sum(index),  results);
		}
	}
	
	private SumCircle findMyNumber(int groupSize, int index, List<Shape> shapes) {
		int startindex, endindex = 0;
		if(index == 0){
			startindex = 0;
			endindex = groupSizeOne;
		}else if(index == 1){
			startindex = groupSizeOne;
			endindex = startindex + groupSizeTwo;
		}else{
			startindex = groupSizeTwo + groupSizeOne;
			endindex = startindex + groupSizeThree;
		}
		List<Shape> results = new ArrayList<Shape>();
		int sum = 0;
		for(int i = startindex; i<endindex; i++ ){
			results.add(shapes.get(i));
			sum += positions[i];
		}
		return new SumCircle(sum, results);
	}
	
	public int findMyGroup(int index){
		int startindex, endindex = 0;
		startindex = 0;
		endindex = groupSizeOne-1;
		if(index >= startindex && index <= endindex){
			return 0;
		}
		
		startindex = groupSizeOne;
		endindex = (startindex + groupSizeTwo) -1;
		if(index >= startindex && index <= endindex){
			return 1;
		}
		
		startindex = groupSizeTwo + groupSizeOne;
		endindex = (startindex + groupSizeThree) - 1;
		if(index >= startindex && index <= endindex) {
			return 2;
		}
		
		return 1;
	}
	private int tranNumber(int number){
		if(number == 1 || number == 2){
			return number -1;
		}else{
			return number + 1;
		}
	}

	private int sum(int index){
		return positions[tranNumber(index)] + positions[index] + positions[index+3] + positions[index+4];
	}

}
