package com.example.inittrack2;

import android.content.Context;
import android.content.DialogInterface;
//import android.support.v7.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class HitpointsDialog extends AppCompatDialogFragment {

    private int hitpoints;
    private HitpointsDialogListener listener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        Log.d("onAttach", "context is " + context.toString());

        try {
            Log.d("onAttach", "getActivity() is " + getActivity() + " getParentFragment is " + getParentFragment() +
                    "getActivity().getSupportFragmentManager() is " + getActivity().getSupportFragmentManager());
            listener = (HitpointsDialogListener) context /* NO! */;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    "must implement HitpointsDialogListener!");
        }
    }



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.hitpoints_dialog, null);
        view.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        Button minus_one_btn = view.findViewById(R.id.minus_one);
        minus_one_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hitpoints -= 1;
            }
        });


        builder.setView(view)
                .setTitle("Test")
                .setNegativeButton(R.string.negative_button, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton(R.string.positive_button, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int result = hitpoints;
                        listener.applyData(hitpoints);
                    }
                });


        return builder.create();
    }

    interface HitpointsDialogListener{
        void applyData(int hitpoints);
    }
}
