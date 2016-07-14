package com.aleds90.android.pokemonhelper.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.aleds90.android.pokemonhelper.R;
import com.aleds90.android.pokemonhelper.model.Gym;
import com.aleds90.android.pokemonhelper.model.GymDAO;
import com.aleds90.android.pokemonhelper.model.Pokemon;
import com.aleds90.android.pokemonhelper.model.PokemonDAO;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fakeList();

        PokemonDAO pokemonDAO = new PokemonDAO(getApplicationContext());
        ArrayList<Pokemon> pokemonArrayList = pokemonDAO.getPokemons();
        System.out.println("ARRAY SIZE: "+ pokemonArrayList.size());

        ListView pokemons = (ListView)findViewById(R.id.pokemons);
        pokemons.setAdapter(new PokemonAdapter( fakeList(),getApplicationContext()));

    }

    private ArrayList<Pokemon> fakeList(){
        PokemonDAO pokemonDAO = new PokemonDAO(getApplicationContext());
        GymDAO gymDAO = new GymDAO(getApplicationContext());

        Gym gym1 = new Gym();
        gym1.setAddress("indirizzo1");
        gym1.setLevel(1);
        gym1.setNotes("note1");
        gym1.setLatitude(1.0);
        gym1.setLongitude(1.0);
        gymDAO.save(gym1);

        Gym gym2 = new Gym();
        gym2.setAddress("indirizzo2");
        gym2.setLevel(2);
        gym2.setNotes("note2");
        gym2.setLatitude(2.0);
        gym2.setLongitude(2.0);
        gymDAO.save(gym2);

        Gym gym3 = new Gym();
        gym3.setAddress("indirizzo3");
        gym3.setLevel(3);
        gym3.setNotes("note3");
        gym3.setLatitude(3.0);
        gym3.setLongitude(3.0);
        gymDAO.save(gym3);

        Pokemon pokemon1 = new Pokemon();
        pokemon1.setName("name1");
        pokemon1.setCp(100);
        pokemon1.setGym(gym1);
        pokemonDAO.save(pokemon1);

        Pokemon pokemon2 = new Pokemon();
        pokemon2.setName("name2");
        pokemon2.setCp(200);
        pokemon2.setGym(gym2);
        pokemonDAO.save(pokemon2);

        Pokemon pokemon3 = new Pokemon();
        pokemon3.setName("name3");
        pokemon3.setCp(300);
        pokemon3.setGym(gym3);
        pokemonDAO.save(pokemon3);

        ArrayList<Pokemon> pokemons = new ArrayList<>();
        pokemons.add(pokemon1);
        pokemons.add(pokemon2);
        pokemons.add(pokemon3);

        return pokemons;
    }
}
