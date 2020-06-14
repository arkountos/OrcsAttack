package com.arkountos.orcsattack;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LoadEncounterAdapter extends RecyclerView.Adapter<LoadEncounterAdapter.MyViewHolder> {
    private ArrayList<Character> mCharacters;

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView mEncounter_name;
        public TextView mEncounter_members;
        public TextView mEncounter_amount;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mEncounter_name = itemView.findViewById(R.id.Encounter_Name);
            mEncounter_members = itemView.findViewById(R.id.Encounter_Members);
            mEncounter_amount = itemView.findViewById(R.id.Encounter_amount);
        }
    }

    public LoadEncounterAdapter(ArrayList<Character> inputCharacters){
        mCharacters = inputCharacters;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_on_encounter_recyclerview, parent, false);
        MyViewHolder mvh = new MyViewHolder(v);
        return mvh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
