package com.aleds90.android.pokemonhelper.view;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.aleds90.android.pokemonhelper.R;
import com.aleds90.android.pokemonhelper.model.Pokemon;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.DecimalFormat;

public class DettailsActivity extends AppCompatActivity implements OnMapReadyCallback {

    SupportMapFragment sMapFragment;
    private GoogleMap map;
    private Pokemon p;
    private TextView tv_Distance;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sMapFragment = SupportMapFragment.newInstance();
        setContentView(R.layout.activity_dettails);

        Intent i = getIntent();
        p = (Pokemon) i.getSerializableExtra("Pokemon");

        ImageButton back = (ImageButton)findViewById(R.id.btn_backGym);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        TextView tv_title = (TextView)findViewById(R.id.titleTB);
        tv_title.setText(p.getName());
        TextView tv_GymLvl = (TextView) findViewById(R.id.tv_GymLvl);
        TextView tv_GymNotes = (TextView) findViewById(R.id.tv_GymNotes);
        tv_Distance = (TextView) findViewById(R.id.tv_Distance);

        tv_GymLvl.setText("Livello palestra: " + p.getGym().getLevel());
        tv_GymNotes.setText("Note: " + p.getGym().getNotes());


        sMapFragment.getMapAsync(this);
        createMap();
    }

    private void createMap() {
        android.support.v4.app.FragmentManager sfm = getSupportFragmentManager();
        // Aggiunge la mappa al nostro Fragment
        sfm.beginTransaction().add(R.id.map_frame, sMapFragment).commit();
        sfm.beginTransaction().show(sMapFragment).commit();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

        setup_Map();
    }

    private void setup_Map() {
        //map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
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
        map.setMyLocationEnabled(true);

        double lat = p.getGym().getLatitude();
        double log = p.getGym().getLongitude();
        LatLng gym_location = new LatLng(lat, log);
        map.addMarker(new MarkerOptions().position(gym_location));
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, log), 12));

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        LatLng my_location = new LatLng(locationManager.getLastKnownLocation(
                LocationManager.GPS_PROVIDER).getLatitude(), locationManager.getLastKnownLocation(
                LocationManager.GPS_PROVIDER).getLongitude());
        double distance = CalculationByDistance(my_location, gym_location);
        tv_Distance.setText("Distanza dalla palestra: " + new DecimalFormat("##.##").format(distance) + "km");


    }

    public double CalculationByDistance(LatLng StartP, LatLng EndP) {
        int Radius = 6371;// radius of earth in Km
        double lat1 = StartP.latitude;
        double lat2 = EndP.latitude;
        double lon1 = StartP.longitude;
        double lon2 = EndP.longitude;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1))
                * Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2)
                * Math.sin(dLon / 2);
        double c = 2 * Math.asin(Math.sqrt(a));
        double valueResult = Radius * c;
        double km = valueResult / 1;
        DecimalFormat newFormat = new DecimalFormat("####");
        int kmInDec = Integer.valueOf(newFormat.format(km));
        double meter = valueResult % 1000;
        int meterInDec = Integer.valueOf(newFormat.format(meter));
        Log.i("Radius Value", "" + valueResult + "   KM  " + kmInDec
                + " Meter   " + meterInDec);

        return Radius * c;
    }
}
