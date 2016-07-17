package com.aleds90.android.pokemonhelper.view;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.aleds90.android.pokemonhelper.R;
import com.aleds90.android.pokemonhelper.model.GymDAO;
import com.aleds90.android.pokemonhelper.model.Pokemon;
import com.aleds90.android.pokemonhelper.model.PokemonDAO;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;

public class PokemonAdapter extends ArrayAdapter<Pokemon>{
    private ArrayList<Pokemon> userArrayList;// = new ArrayList<Wallet>();
    private Context context;
    private String name;
    private int cp;
    private int level;
    private String notes;
    private Activity activity;


    public PokemonAdapter(ArrayList<Pokemon> userArrayList, Context context, Activity activity){
        super(context, R.layout.adapter_pokemons, userArrayList);
        this.userArrayList = userArrayList;
        this.context = context;
        this.activity = activity;
    }

    @Override
    public View getView(int position, final View convertView, final ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.adapter_pokemons, parent, false);

        final Pokemon pokemon = userArrayList.get(position);
        final int pokemon_id = pokemon.getId();
        name = pokemon.getName();
        cp = pokemon.getCp();

        int gym_id = pokemon.getGym().getId();
        String address = pokemon.getGym().getAddress();
        double latitude = pokemon.getGym().getLatitude();
        double longitude = pokemon.getGym().getLongitude();
        level = pokemon.getGym().getLevel();
        notes = pokemon.getGym().getNotes();

        TextView tv_name = (TextView)view.findViewById(R.id.text_name);
        //TextView tv_gym = (TextView)view.findViewById(R.id.text_gym_address);
        ImageView image = (ImageView)view.findViewById(R.id.pokemon_image);
        ImageView imageDelete = (ImageView) view.findViewById(R.id.pokemon_delete);
        ImageView imageEdit = (ImageView)view.findViewById(R.id.pokemon_edit);

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

        imageEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(activity);
                dialog.setContentView(R.layout.dialog_edit_gym);
                dialog.setTitle("CAMBIA POKEMON");
                dialog.setCanceledOnTouchOutside(false);

                final AutoCompleteTextView et_Pokemon = (AutoCompleteTextView) dialog.findViewById(R.id.et_Pokemon);
                String[] pokemonList = v.getResources().getStringArray(R.array.pokemons);
                ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.select_dialog_item, pokemonList);
                et_Pokemon.setThreshold(1);
                et_Pokemon.setAdapter(adapter);
                et_Pokemon.setTextColor(Color.BLACK);

                final EditText et_CP = (EditText) dialog.findViewById(R.id.et_CP);
                final EditText et_Level = (EditText) dialog.findViewById(R.id.et_Level);
                //final EditText et_Address = (EditText) dialog.findViewById(R.id.et_Address);
                final EditText et_Notes = (EditText) dialog.findViewById(R.id.et_Notes);

                Button btn_OK = (Button) dialog.findViewById(R.id.btn_OK);
                Button btn_Cancell = (Button) dialog.findViewById(R.id.btn_Cancell);

                et_Pokemon.setText(name);
                et_CP.setText(Integer.toString(cp));
                et_Level.setText(Integer.toString(level));
                et_Notes.setText(notes);

                btn_OK.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pokemon.setName(et_Pokemon.getText().toString());
                        pokemon.setCp(Integer.parseInt(et_CP.getText().toString()));
                        pokemon.getGym().setLevel(Integer.parseInt(et_Level.getText().toString()));
                        pokemon.getGym().setNotes(et_Notes.getText().toString());

                        GymDAO gymDAO = new GymDAO(getContext());
                        gymDAO.update(pokemon.getGym());

                        PokemonDAO pokemonDAO = new PokemonDAO(getContext());
                        pokemonDAO.update(pokemon);
                        remove(pokemon);
                        add(pokemon);
                        dialog.dismiss();
                    }
                });


                btn_Cancell.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });

        return view;
    }


}
