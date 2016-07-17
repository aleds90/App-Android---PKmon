package com.aleds90.android.pokemonhelper.view;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.aleds90.android.pokemonhelper.R;
import com.aleds90.android.pokemonhelper.model.Pokemon;
import com.aleds90.android.pokemonhelper.model.PokemonDAO;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;

public class PokemonAdapter extends ArrayAdapter<Pokemon>{
    private ArrayList<Pokemon> userArrayList;// = new ArrayList<Wallet>();
    private Context context;


    public PokemonAdapter(ArrayList<Pokemon> userArrayList, Context context){
        super(context, R.layout.adapter_pokemons, userArrayList);
        this.userArrayList = userArrayList;
        this.context = context;
    }

    @Override
    public View getView(int position, final View convertView, final ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.adapter_pokemons, parent, false);

        final Pokemon pokemon = userArrayList.get(position);
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
        //TextView tv_gym = (TextView)view.findViewById(R.id.text_gym_address);
        ImageView image = (ImageView)view.findViewById(R.id.pokemon_image);
        ImageView imageDelete = (ImageView) view.findViewById(R.id.pokemon_delete);

        Picasso.with(view.getContext()).load("https://img.pokemondb.net/artwork/"+name+".jpg").into(image);
        tv_name.setText(name+"("+cp+")");
        //tv_gym.setText(address+"("+longitude+","+latitude+")");

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, DettailsActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("Pokemon", (Serializable) pokemon);
                context.startActivity(i);
            }
        });

        imageDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(parent.getContext())
                        .setTitle("Delete entry")
                        .setMessage("Are you sure you want to delete this?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                PokemonDAO pokemonDAO = new PokemonDAO(context);
                                pokemonDAO.deletePokemon(pokemon);
                                dialog.dismiss();
                                remove(pokemon);
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();

                ;
            }
        });


        return view;
    }


}
