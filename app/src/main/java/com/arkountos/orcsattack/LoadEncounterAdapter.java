package com.arkountos.orcsattack;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

public class LoadEncounterAdapter extends RecyclerView.Adapter<LoadEncounterAdapter.MyViewHolder> {
//    private ArrayList<Character> mCharacters;
    private Map<String, Set<String>> mEncounters;
    private Object[] mEncounterArray;


    private Context mContext;
    private FragmentManager mFragmentManager;

    private Gson gson = new Gson();


    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView mEncounter_name;
        public TextView mEncounter_members;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mEncounter_name = itemView.findViewById(R.id.Encounter_Name);
            mEncounter_members = itemView.findViewById(R.id.Encounter_Members);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent resultIntent = new Intent();
                }
            });
        }
    }

    public LoadEncounterAdapter(Context context, Map<String, Set<String>> inputEncounters, FragmentManager fragmentManager){
//        mCharacters = inputCharacters;
        mContext = context;
        mFragmentManager = fragmentManager;
        mEncounters = inputEncounters;

        mEncounterArray = mEncounters.entrySet().toArray();
        Log.d("print objectarray", Arrays.toString(mEncounterArray));
//        for (Map.Entry<String, Set<String>> mapping : mEncounters.entrySet()){
//            Log.d("Print mapping adapter", mapping.toString());
//            Log.d("Trying to get the key", mapping.keySet().toString());
//            Log.d("Trying to get the value", mapping.values().toString());
//            mArrayEncounters.put(mapping.keySet().toString(), mapping.values());
//        }
//        mArrayEncounters.addAll(mEncounters);
//        Log.d("mEncounters:", mEncounters.toString());
//        Log.d("mArrayEncounters:", mArrayEncounters.toString());
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
        String current_encounter = mEncounterArray[position].toString();
        Log.d("current_encounter", current_encounter);

        String[] current_encounter_parts_1 = current_encounter.split("\\[");
        Log.d("currenc_parts_1", Arrays.toString(current_encounter_parts_1));
        String current_encounter_name = current_encounter_parts_1[0].substring(0, current_encounter_parts_1[0].length() - 1);
        Log.d("current_encounter_name", current_encounter_name);
        String current_encounter_characters = current_encounter_parts_1[1].substring(0, current_encounter_parts_1[1].length() - 1);
        Log.d("current_encounter_chars", current_encounter_characters);
        String[] current_encounter_characters_array = current_encounter_characters.split(" ");
        Log.d("currenc_chars_array", Arrays.toString(current_encounter_characters_array));


        ArrayList<Character> characters = new ArrayList<>();
        for (String character : current_encounter_characters_array){
            String temp_character = "";
            if (!character.substring(character.length() - 1).equals("}")) {
                temp_character = character.substring(0, character.length() - 1);
            }
            else{
                temp_character = character;
            }
            Log.d("temp_character", temp_character);
            Character dejson_char = gson.fromJson(temp_character, Character.class);
            characters.add(dejson_char);
        }

        String character_info = "";
        for (Character character : characters){
            character_info += character.getName().toString() + "," + character.getMyclass().toString() + ", HP:" + character.getHitpoints() + ", AC:" + character.getArmor_class() + ", Modifier:" + character.getInitiative_modifier() + "\n";
        }

        holder.mEncounter_members.setText(character_info);
        holder.mEncounter_name.setText(current_encounter_name);

        final String finalCharacter_info = character_info;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("EXTRA_ENCOUNTER_STRING", finalCharacter_info);
                Log.d("AdapteronClick", "OK!");
                // Casting to Activity to finish inside adapter as seen here:
                // https://stackoverflow.com/questions/7951936/how-to-finish-an-activity-from-an-adapter
                ((Activity)mContext).setResult(Activity.RESULT_OK, resultIntent);
                ((Activity)mContext).finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mEncounters.size();
    }
}
