package com.example.project731;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private Button back;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        back = findViewById(R.id.back);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MapsActivity.this, FirebaseMenuActivity.class));
                finish();
            }
        });
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Yorkdale Footlocker and move the camera
        LatLng yorkdale = new LatLng(43.724729, -79.450025);
        mMap.addMarker(new MarkerOptions().position(yorkdale).title("Yorkdale Footlocker"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(yorkdale));

        // Add a marker in Eaton Centre Footlocker and move the camera
        LatLng eaton = new LatLng(43.653539, -79.380371);
        mMap.addMarker(new MarkerOptions().position(eaton).title("Eaton Centre Footlocker"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(eaton));

        // Add a marker in Dufferin Mall Footlocker
        LatLng dufferin = new LatLng(43.655729, -79.435603);
        mMap.addMarker(new MarkerOptions().position(dufferin).title("Dufferin Mall Footlocker"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(dufferin));

        //Add a marker in Montreal Footlocker
        LatLng montreal = new LatLng(45.501667, -73.571119);
        mMap.addMarker(new MarkerOptions().position(montreal).title("Montreal Footlocker"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(montreal));

        //Add a marker in Calgary Footlocker
        LatLng calgary = new LatLng (50.998860, -114.073393);
        mMap.addMarker(new MarkerOptions().position(calgary).title("Calgary Footlocker"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(calgary));

        //Add a marker in Edmonton Footlocker
        LatLng edmonton = new LatLng (53.561273, -113.504651);
        mMap.addMarker(new MarkerOptions().position(edmonton).title("Edmonton Footlocker"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(edmonton));

        //Add a marker in Vancouver Footlocker
        LatLng vancouver = new LatLng (49.282877, -123.121790);
        mMap.addMarker(new MarkerOptions().position(vancouver).title("Vancouver Footlocker"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(vancouver));
    }
}