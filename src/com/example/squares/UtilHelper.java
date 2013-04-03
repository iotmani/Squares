package com.example.squares;

import android.content.Context;

public class UtilHelper {

	
	public static int getTextSize(Context context){
	    float GESTURE_THRESHOLD_DIP = 16.0f;
		final float scale = context.getResources().getDisplayMetrics().density;
		return  (int) (GESTURE_THRESHOLD_DIP * scale + 0.5f);
	}
}
