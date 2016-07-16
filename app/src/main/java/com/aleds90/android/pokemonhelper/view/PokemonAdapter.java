package com.aleds90.android.pokemonhelper.view;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.aleds90.android.pokemonhelper.R;
import com.aleds90.android.pokemonhelper.model.Pokemon;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class PokemonAdapter extends ArrayAdapter<Pokemon>{
    private ArrayList<Pokemon> userArrayList;// = new ArrayList<Wallet>();
    private Context context;


    public PokemonAdapter(ArrayList<Pokemon> userArrayList, Context context){
        super(context, R.layout.adapter_pokemons, userArrayList);
        this.userArrayList = userArrayList;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.adapter_pokemons, parent, false);

        Pokemon pokemon = userArrayList.get(position);
        int pokemon_id = pokemon.getId();
        String name = pokemon.getName();
        int cp = pokemon.getCp();

        int gym_id = pokemon.getGym().getId();
        String address = pokemon.getGym().getAddress();
        double latitude = pokemon.getGym().getLatitude();
        double longitude = pokemon.getGym().getLongitude();
        int level = pokemon.getGym().getLevel();
        String notes = pokemon.getGym().getNotes();

        TextView tv_name = (TextView)view.findViewById(R.id.text_name);
        TextView tv_gym = (TextView)view.findViewById(R.id.text_gym_address);
        ImageView image = (ImageView)view.findViewById(R.id.pokemon_image);

        Picasso.with(view.getContext()).load("https://img.pokemondb.net/artwork/"+name+".jpg").into(image);
        tv_name.setText(name+"("+cp+")");
        tv_gym.setText(address+"("+longitude+","+latitude+")");


        return view;
    }


}
