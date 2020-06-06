package com.arkountos.orcsattack;

import com.arkountos.orcsattack.R;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.PopupMenu;
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

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> implements HitpointsDialog.HitpointsDialogListener {
    private static final String TAG = "RecyclerViewAdapter";
    private int new_hitpoints;

    private ArrayList<Character> mcharacters = new ArrayList<>();
    private Context mContext;
    private FragmentManager mFragmentManager;

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
                                Log.d(TAG, "Clicked Edit!!!");
                                int newPosition2 = holder.getAdapterPosition();
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
                Log.d(TAG, "Hey!");
                openDialog();

                Log.d(TAG, "Done with openDialog, new_hitpoints are:" +  new_hitpoints +
                        "and char hitpoints are: " + mcharacters.get(position).getHitpoints());
            }
        });
    }

    public void openDialog(){
//        HitpointsDialog hpDialog = new HitpointsDialog();
//        hpDialog.show(mFragmentManager, "test");
        AlertDialog.Builder alert = new AlertDialog.Builder(mContext);
//        alert.setTitle( "Change the Hitpoints?");
        TextView titleView = new TextView(mContext);
        titleView.setText("Change Hitpoints");
        titleView.setTextColor(Color.BLACK);
        titleView.setTextSize(30);
        titleView.setGravity(Gravity.CENTER);
        titleView.setPadding(0, 8, 0, 0);
        alert.setCustomTitle(titleView);

        final LinearLayout layout = new LinearLayout(mContext);
        layout.setOrientation(LinearLayout.VERTICAL);

//        TextView titleTextView = new TextView(mContext);
//        titleTextView.setText("Set new Hitpoints");
//        titleTextView.setTextColor(Color.BLACK);
//        titleTextView.setGravity(Gravity.CENTER);
//        layout.addView(titleTextView);

//        AppCompatSeekBar seekBar = new AppCompatSeekBar(mContext);
//        layout.addView(seekBar);

        NumberPicker numberPicker = new NumberPicker(mContext);
        numberPicker.setScaleX(Float.parseFloat("0.75"));
        numberPicker.setScaleY(Float.parseFloat("0.75"));
        numberPicker.setGravity(Gravity.CENTER);
        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(100);



        layout.addView(numberPicker);

        alert.setView(layout);
        alert.show();
    }


    @Override
    public int getItemCount() {
        return mcharacters.size();
    }

    @Override
    public void applyData(int hitpoints) {
        Log.d(TAG, "################ In applyData ##############");
        new_hitpoints = hitpoints;
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
