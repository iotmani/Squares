package com.example.squares;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;


public class NumberPicker extends DialogFragment {
	
	 private CharSequence sequence[];
	 NoticeDialogListener mListener;
	 
	 public NumberPicker(CharSequence sequence[]){
		 this.sequence = sequence;
	 }
	 public interface NoticeDialogListener {
	        public void onDialogPositiveClick(int dialog);
	 }
	 
	    @Override
	  public void onAttach(Activity activity) {
	        super.onAttach(activity);
	        try {
	            mListener = (NoticeDialogListener) activity;
	        } catch (ClassCastException e) {
	            throw new ClassCastException(activity.toString()
	                    + " must implement NoticeDialogListener");
	        }
	 }
	    
	 
	 
	 public void setCharaters(CharSequence sequence[]){
		 this.sequence = sequence;
	 }
	 
	 public CharSequence[] getSequence(){
		 return sequence;
	 }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.pick_number);
        builder.setItems(sequence, new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface dialog, int which) {
	        	mListener.onDialogPositiveClick(which);
	        }
        });

        // Create the AlertDialog object and return it
        return builder.create();
    }
}