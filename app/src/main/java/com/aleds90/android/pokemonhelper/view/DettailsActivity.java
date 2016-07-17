package com.aleds90.android.pokemonhelper.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.aleds90.android.pokemonhelper.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class DettailsActivity extends AppCompatActivity implements OnMapReadyCallback {

    SupportMapFragment sMapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sMapFragment = SupportMapFragment.newInstance();
        setContentView(R.layout.activity_dettails);

        sMapFragment.getMapAsync(this);
        createMap();
    }

    private void createMap() {
        android.support.v4.app.FragmentManager sfm = getSupportFragmentManager();
        // Aggiunge la mappa al nostro Fragment
        Intent i = getIntent();

        //Todo: dall intent farsi passare l id della gym, prendersi i dati per le coordinate con una richiesta al DB
        //GoogleMap map = sMapFragment.getMap();
        //map.addMarker(new MarkerOptions().position(new LatLng()))
        sfm.beginTransaction().add(R.id.map_frame, sMapFragment).commit();
        sfm.beginTransaction().show(sMapFragment).commit();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }
}
