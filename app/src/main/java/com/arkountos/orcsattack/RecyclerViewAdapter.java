package com.arkountos.orcsattack;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;


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

        Button okButton = view.findViewById(R.id.okButtonEncounter);
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
        Button cancelButton = view.findViewById(R.id.cancelButtonEncounter);
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


        Button okButton = view.findViewById(R.id.okButtonEncounter);
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
        Button cancelButton = view.findViewById(R.id.cancelButtonEncounter);
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
