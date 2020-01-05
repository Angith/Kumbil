package com.kumbil.neha;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.kumbil.neha.shared.SharedData;


public class CreateAlert extends DialogFragment {

    String message;
    CreateAlert context = this;
    public static CreateAlert newInstance(String message) {
        CreateAlert alert = new CreateAlert();

        Bundle args = new Bundle();
        args.putString("message", message);
        alert.setArguments(args);

        return alert;
    }

    public static interface OnCompleteListener {
        public abstract void onComplete(boolean ok);
    }

    private OnCompleteListener mListener;

    // make sure the Activity implemented it
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Activity a;

        if (context instanceof Activity){
            a=(Activity) context;
        } else {
            a=getActivity();
        }

        try {
            this.mListener = (OnCompleteListener)a;
        }
        catch (final ClassCastException e) {
            throw new ClassCastException(a.toString() + " must implement OnCompleteListener");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        message = getArguments().getString("message", "message is not available");
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(message)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dismiss();
                        context.mListener.onComplete(true);
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
