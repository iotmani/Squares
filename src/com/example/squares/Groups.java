package com.example.squares;

import android.graphics.Color;

public enum Groups {
	GroupOne, GroupTwo, GroupThree;
	
	
	public static int getGroupColor(int group){
		if(group == 0){
			return Color.argb(255, 102, 204, 0);
		}else if(group == 1){
			return Color.argb(255, 255, 153, 51);
		}else if(group == 2){
			return Color.argb(255, 255, 51, 51);
		}
		return Color.BLACK;
	}

}
