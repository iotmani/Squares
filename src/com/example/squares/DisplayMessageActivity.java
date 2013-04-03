package com.example.squares;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.widget.TextView;

public class DisplayMessageActivity extends FragmentActivity implements NumberClick, NumberPicker.NoticeDialogListener, NextLevelDialog.NoticeDialogListener {

	private PlaySurface drawView;
	private Shape shape;
	private NumberPicker dialog;
	private NextLevelDialog nextLevel;
	private CharSequence builder[];
	
	   @SuppressLint("NewApi")
	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);

	        // Create the text view
	        drawView = new PlaySurface(this);
	        drawView.addNumberListener(this);
	        builder = populateCharSequence();
	        dialog = new NumberPicker(builder);
	        nextLevel = new NextLevelDialog();
	        drawView.setBackgroundColor(Color.WHITE);
	        setContentView(drawView);
	    }

	    private CharSequence[] populateCharSequence() {
	    	List<String> listItems = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9");
	    	return listItems.toArray(new CharSequence[listItems.size()]);
	     }

		@Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	        switch (item.getItemId()) {
	        case android.R.id.home:
	            NavUtils.navigateUpFromSameTask(this);
	            return true;
	        }
	        return super.onOptionsItemSelected(item);
	    }

		public void numberClicked(Shape shape) {
			this.shape = shape;
			if(dialog.getSequence().length != 0)
				dialog.show(getFragmentManager(), ACCOUNT_SERVICE);
			else
				updateDialog(shape.addText(""), -1, dialog);
			
		}

		public void onDialogPositiveClick(int index) {
			if(shape != null){
				String oldValue = shape.addText(dialog.getSequence()[index].toString());
				drawView.invalidate();
				if(dialog.getSequence().length == 0)
					oldValue = "";
				updateDialog(oldValue, index, dialog);
				if(drawView.complete()){
					nextLevel.show(getFragmentManager(), CONNECTIVITY_SERVICE);
				}
			}
		}

		private void updateDialog(String oldValue, int index, NumberPicker dialog) {
			List<CharSequence> items = createOcean(dialog.getSequence(), index);
			if(oldValue != null && !oldValue.isEmpty())
				items.add(oldValue);
			Collections.sort(items, new Com());
			dialog.setCharaters(items.toArray(new CharSequence[items.size()]));
		}
		
		private List<CharSequence> createOcean(CharSequence builder[], int index){
			List<CharSequence> list = new ArrayList<CharSequence>();
			for(int i = 0; i<builder.length; i++){
				if(i != index)
					list.add(builder[i]);
			}
			return list;
		}
		
		public class Com implements Comparator<CharSequence>{
			
			public int compare(CharSequence arg0, CharSequence arg1) {
				try{
				return Integer.parseInt(arg0.toString()) - Integer.parseInt(arg1.toString());
				}catch(Exception e){
					return 0;
				}
			}
		}

		public void onDialogNegativeClick(int dialog) {
			
		}

		public void onPositiveClick(int dialog1) {
			drawView.reset();
			drawView.invalidate();
			dialog.setCharaters(populateCharSequence());
		}
		

}
