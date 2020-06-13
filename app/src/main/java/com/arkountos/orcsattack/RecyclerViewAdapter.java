package com.arkountos.orcsattack;

import com.arkountos.orcsattack.R;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.text.Layout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatSeekBar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.slider.*;


import java.util.ArrayList;

//import static androidx.core.graphics.drawable.IconCompat.getResources;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    private static final String TAG = "RecyclerViewAdapter";
    private int new_hitpoints;

    private ArrayList<Character> mcharacters = new ArrayList<>();
    private Context mContext;
    private FragmentManager mFragmentManager;

    // Layout inflater for use inside the holder
    LayoutInflater inflater;

    // An interface so I can call functions from MainActivity inside this adapter
    // https://stackoverflow.com/questions/36347429/how-to-call-method-in-fragment-from-adapter-android
    public interface OnInitiativeSetListener{
        void onInitiativeSet();
    }

    // Default Constructor!
    public RecyclerViewAdapter(Context context, ArrayList<Character> characters, FragmentManager fragmentManager) {
        mcharacters = characters;
        mContext = context;
        mFragmentManager = fragmentManager;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_on_recyclerlist, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        holder.image_name.setText(mcharacters.get(position).getName());
        Log.d(TAG, "" + mcharacters.get(position).getInitiative_modifier());
        holder.initiative_rolled.setText(("Initiative: " + mcharacters.get(position).getInitiative()));

        String res = "ic_" + mcharacters.get(position).getMyclass().toLowerCase();
        int id = mContext.getResources().getIdentifier(res, "drawable", mContext.getPackageName());
        holder.image.setImageResource(id);
        Log.d(TAG, "class is" + mcharacters.get(position).getMyclass());
        if(mcharacters.get(position).getMyclass().equals("Monster")){
            Log.d(TAG, "INSIDE COLOUR CHANGE");
            holder.parent_layout.setBackgroundColor(ContextCompat.getColor(mContext, R.color.colorEnemy));
        }

        holder.hitpoints.setText("" + mcharacters.get(position).getHitpoints());

        holder.options_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(mContext, holder.options_image); //?
                popup.inflate(R.menu.item_menu);
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.menu1:
                                Log.d(TAG, "Clicked Remove!!!");
                                int newPosition = holder.getAdapterPosition();
                                mcharacters.remove(newPosition);
                                notifyItemRemoved(newPosition);
                                notifyItemRangeChanged(newPosition, mcharacters.size());
                                //handle menu1 click
                                break;
                            case R.id.menu2:
                                Log.d(TAG, "Clicked Set Initiative!!!");
                                openInitiativeDialog(holder, position);
                                break;
                        }

                        return false;
                    }
                });
                //displaying the popup
                popup.show();
            }
        });

        holder.hitpoints_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHitpointsDialog(holder, position);
            }
        });
    }

    public void openInitiativeDialog(@NonNull final ViewHolder holder, final int position){
        final Dialog alertDialog = new Dialog(mContext);
        inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(com.arkountos.orcsattack.R.layout.hitpoints_dialog, null);

        final NumberPicker finalNumberPicker = view.findViewById(R.id.numberPicker);;
        finalNumberPicker.setMinValue(0);
        finalNumberPicker.setMaxValue(30);

        Button okButton = view.findViewById(R.id.okButton);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Btn", "Clicked!" +
                        "");
                mcharacters.get(position).setInitiative(finalNumberPicker.getValue());
                holder.initiative_rolled.setText("Initiative: " + mcharacters.get(position).getInitiative());
                alertDialog.dismiss();
            }
        });
        Button cancelButton = view.findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Btn", "Clicked Cancel!");
                alertDialog.dismiss();
            }
        });


        alertDialog.setContentView(view);
        alertDialog.show();
    }

    @SuppressLint("ResourceType")
    public void openHitpointsDialog(@NonNull final ViewHolder holder, final int position){

//        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(mContext);
//        AlertDialog alertDialog = dialogBuilder.create();
//        inflater = LayoutInflater.from(mContext);
        final Dialog alertDialog = new Dialog(mContext);
        inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(com.arkountos.orcsattack.R.layout.hitpoints_dialog, null);

        final NumberPicker finalNumberPicker = view.findViewById(R.id.numberPicker);;
        final int maxVal = 100;
        final int minVal = -99;
        int i;
        String[] nums = new String[199];
        for (i = minVal-1; i < maxVal-1; i++){
            nums[i+maxVal] = Integer.toString(i);
            Log.d("Array", Integer.toString(i));
        }
        finalNumberPicker.setMinValue(0);
        finalNumberPicker.setMaxValue(198);
        finalNumberPicker.setDisplayedValues(nums);
        finalNumberPicker.setValue(99);


        Button okButton = view.findViewById(R.id.okButton);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Btn", "Clicked!" +
                        "");
                mcharacters.get(position).setHitpoints(mcharacters.get(position).getHitpoints() + finalNumberPicker.getValue() - maxVal);
                holder.hitpoints.setText("" + mcharacters.get(position).getHitpoints());
                alertDialog.dismiss();
            }
        });
        Button cancelButton = view.findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Btn", "Clicked Cancel!");
                alertDialog.dismiss();
            }
        });


        alertDialog.setContentView(view);
        alertDialog.show();
////        HitpointsDialog hpDialog = new HitpointsDialog();
////        hpDialog.show(mFragmentManager, "test");
//        AlertDialog.Builder alert = new AlertDialog.Builder(mContext);
//
////        alert.setTitle( "Change the Hitpoints?");
//        TextView titleView = new TextView(mContext);
//        titleView.setText("Change Hitpoints");
////        titleView.setTextColor(Color.BLACK);
//        titleView.setTextSize(30);
//        titleView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.colorPrimary));
//        titleView.setGravity(Gravity.CENTER);
//        titleView.setPadding(0, 8, 0, 0);
//        alert.setCustomTitle(titleView);
//
//        final LinearLayout layout = new LinearLayout(mContext);
//        layout.setOrientation(LinearLayout.VERTICAL);
//
//
//        layout.setBackgroundColor(ContextCompat.getColor(mContext, R.color.colorPrimary));
//
////        TextView titleTextView = new TextView(mContext);
////        titleTextView.setText("Set new Hitpoints");
////        titleTextView.setTextColor(Color.BLACK);
////        titleTextView.setGravity(Gravity.CENTER);
////        layout.addView(titleTextView);
//
////        AppCompatSeekBar seekBar = new AppCompatSeekBar(mContext);
////        layout.addView(seekBar);
//
//        final NumberPicker numberPicker = new NumberPicker(mContext);
//        numberPicker.setScaleX(Float.parseFloat("0.75"));
//        numberPicker.setScaleY(Float.parseFloat("0.75"));
//        numberPicker.setGravity(Gravity.CENTER);
//        numberPicker.setMinValue(0);
//        numberPicker.setMaxValue(100);
//        layout.addView(numberPicker);
//
//        Button okButton = new Button(mContext);
//        okButton.setTextColor(Color.BLACK);
//        okButton.setText("Ok");
//        okButton.setGravity(Gravity.RIGHT);
//        okButton.setTextColor(Color.WHITE);
//        okButton.setPadding(0,0, 64, 64);
//        okButton.setTextSize(20);
//        okButton.setId(1);
//        okButton.setOnClickListener(new View.OnClickListener() {
//            @SuppressLint("SetTextI18n")
//            @Override
//            public void onClick(View v) {
//                mcharacters.get(position).setHitpoints(mcharacters.get(position).getHitpoints() + numberPicker.getValue());
//                holder.hitpoints.setText("" + mcharacters.get(position).getHitpoints());
//
//            }
//        });
//        layout.addView(okButton);
//
//
//        RelativeLayout.LayoutParams relativeParams = new RelativeLayout.LayoutParams(
//                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
//        relativeParams.addRule(RelativeLayout.LEFT_OF, okButton.getId());
//        relativeParams.addRule(RelativeLayout.ALIGN_LEFT, okButton.getId());
//
//        Button cancelButton = new Button(mContext);
//        cancelButton.setTextColor(Color.BLACK);
//        cancelButton.setText("Cancel");
////        cancelButton.setGravity(Gravity.RIGHT);
//        cancelButton.setTextColor(Color.WHITE);
//        cancelButton.setPadding(0,0, 128, 64);
//        cancelButton.setTextSize(20);
//        cancelButton.setId(2);
//        cancelButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//        layout.addView(cancelButton, relativeParams);
//
////        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
////            @Override
////            public void onClick(DialogInterface dialog, int which) {
////                mcharacters.get(position).setHitpoints(mcharacters.get(position).getHitpoints() + numberPicker.getValue());
////                holder.hitpoints.setText("" + mcharacters.get(position).getHitpoints());
////            }
////        });
//
//        alert.setView(layout);
//        alert.show();
    }


    @Override
    public int getItemCount() {
        return mcharacters.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        // Parameters go here
        TextView image_name;
        TextView initiative_rolled;
        ImageView image;
        ConstraintLayout parent_layout;
        ImageView options_image;
        TextView hitpoints;
        ImageView hitpoints_image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parent_layout = itemView.findViewById(R.id.parent_layout);
            image = itemView.findViewById(R.id.image);
            image_name = itemView.findViewById(R.id.image_name);
            initiative_rolled = itemView.findViewById(R.id.inititative_rolled);
            options_image = itemView.findViewById(R.id.options);
            hitpoints = itemView.findViewById(R.id.hitpoints);
            hitpoints_image = itemView.findViewById(R.id.hitpoints_image);
        }
    }
}
