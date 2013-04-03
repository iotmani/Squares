package com.example.squares;

import com.example.squares.NumberPicker.NoticeDialogListener;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

public class NextLevelDialog extends DialogFragment {

	
	 NoticeDialogListener mListener;

	 public interface NoticeDialogListener {
	        public void onPositiveClick(int dialog);
	        public void onDialogNegativeClick(int dialog);
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
	    
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.dialog_fire_missiles)
               .setPositiveButton(R.string.fire, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                	   mListener.onPositiveClick(id);
                   }
               })
               .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                	   mListener.onDialogNegativeClick(id);
                   }
               });
        return builder.create();
    }

}
