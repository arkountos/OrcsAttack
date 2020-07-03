package com.arkountos.orcsattack;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Set;

public class LoadEncounterAdapter extends RecyclerView.Adapter<LoadEncounterAdapter.MyViewHolder> {
//    private ArrayList<Character> mCharacters;
    private Set<Set<String>> mEncounters;
    private ArrayList<Set<String>> mArrayEncounters = new ArrayList<>();

    private Context mContext;
    private FragmentManager mFragmentManager;

    private Gson gson = new Gson();


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

    public LoadEncounterAdapter(Context context, Set<Set<String>> inputEncounters, FragmentManager fragmentManager){
//        mCharacters = inputCharacters;
        mContext = context;
        mFragmentManager = fragmentManager;
        mEncounters = inputEncounters;
        mArrayEncounters.addAll(mEncounters);
        Log.d("mEncounters:", mEncounters.toString());
        Log.d("mArrayEncounters:", mArrayEncounters.toString());
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
        Set<String> current_encounter = mArrayEncounters.get(position);

        ArrayList<Character> characters = null;
        for (String character : current_encounter){
            Character dejson_char = gson.fromJson(character, Character.class);
//            characters.add(gson.fromJson(current_encounter[i], Character.class));
        }

//        holder.mEncounter_members.setText(gson.fromJson(current_encounter,));
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
