package com.aleds90.android.pokemonhelper.view;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.aleds90.android.pokemonhelper.R;
import com.aleds90.android.pokemonhelper.model.GPS;
import com.aleds90.android.pokemonhelper.model.Gym;
import com.aleds90.android.pokemonhelper.model.GymDAO;
import com.aleds90.android.pokemonhelper.model.Pokemon;
import com.aleds90.android.pokemonhelper.model.PokemonDAO;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LocationListener {

    protected LocationManager locationManager;
    GPS gps = new GPS();

    final Context context = this;
    private Button btn_AddGym;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_AddGym = (Button) findViewById(R.id.btn_AddGym);
        btn_AddGym.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog_insert_gym);
                dialog.show();
            }
        });



        PokemonDAO pokemonDAO = new PokemonDAO(getApplicationContext());
        ArrayList<Pokemon> pokemonArrayList = pokemonDAO.getPokemons();


        ListView pokemons = (ListView)findViewById(R.id.pokemons);

        pokemons.setAdapter(new PokemonAdapter( pokemonArrayList,getApplicationContext()));

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);

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
        gym1.setId((int)gymDAO.save(gym1));

        Gym gym2 = new Gym();
        gym2.setAddress("indirizzo2");
        gym2.setLevel(2);
        gym2.setNotes("note2");
        gym2.setLatitude(2.0);
        gym2.setLongitude(2.0);
        gym2.setId((int)gymDAO.save(gym2));


        Gym gym3 = new Gym();

        gym3.setAddress("indirizzo3");
        gym3.setLevel(3);
        gym3.setNotes("note3");
        gym3.setLatitude(3.0);
        gym3.setLongitude(3.0);
        gym3.setId((int) gymDAO.save(gym3));

        Pokemon pokemon1 = new Pokemon();
        pokemon1.setId(1);
        pokemon1.setName("name1");
        pokemon1.setCp(100);
        pokemon1.setGym(gym1);
        pokemonDAO.save(pokemon1);

        Pokemon pokemon2 = new Pokemon();
        pokemon1.setId(2);
        pokemon2.setName("name2");
        pokemon2.setCp(200);
        pokemon2.setGym(gym2);
        pokemonDAO.save(pokemon2);

        Pokemon pokemon3 = new Pokemon();
        pokemon3.setId(3);
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

    @Override
    public void onLocationChanged(Location location) {
        gps.setAltitude(location.getAltitude());
        gps.setLatitude(location.getLatitude());
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
