package com.example.redi.MyFirstAndroidApp;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.redi.MyFirstAndroidApp.models.entities.Venue;
import com.example.redi.MyFirstAndroidApp.models.functional.Consumer;
import com.example.redi.MyFirstAndroidApp.models.ui.fragments.RetainFragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class MyMapsActivity extends AppCompatActivity {

    private Map<MarkerOptions, Venue> markerVenueMap = new HashMap<>();

    protected static final String NETWORK_FRAGMENT_TAG = "NETWORK_FRAGMENT";

    private RetainFragment retainFragment;

    private FloatingActionButton fab_add_venue;
    private FloatingActionButton fab_edit_venue;
    private FloatingActionButton fab_delete_venue;
    private FloatingActionButton fab_refresh_venue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_maps);

        fab_add_venue = (FloatingActionButton) findViewById(R.id.add_venue);
        fab_edit_venue = (FloatingActionButton) findViewById(R.id.edit_venue);
        fab_delete_venue = (FloatingActionButton) findViewById(R.id.delete_venue);
        fab_refresh_venue = (FloatingActionButton) findViewById(R.id.refresh_venue);

        fab_add_venue.setVisibility(View.INVISIBLE);
        fab_edit_venue.setVisibility(View.INVISIBLE);
        fab_delete_venue.setVisibility(View.INVISIBLE);
        fab_refresh_venue.setVisibility(View.INVISIBLE);

        setupNetworkFragment();

    }

    private RetainFragment getRetainFragment() {
        retainFragment = (RetainFragment) getSupportFragmentManager().findFragmentByTag(NETWORK_FRAGMENT_TAG);
        if (retainFragment == null) {
            retainFragment = new RetainFragment();
            getSupportFragmentManager().beginTransaction().add(retainFragment, NETWORK_FRAGMENT_TAG).commit();
        }

        return retainFragment;
    }

    private void setupNetworkFragment() {
        getRetainFragment().getPlaces(new Consumer<List<Venue>>() {
            @Override
            public void apply(List<Venue> venues) {
                setupMapView(venues);
            }
        });
    }


    private void setupMapView(final List<Venue> venues) {


        SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapFragment);

        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(final GoogleMap googleMap) {

                googleMap.clear();


                MyMapsActivityPermissionsDispatcher.enableMyLocationWithCheck(MyMapsActivity.this, googleMap);

                for (int i = 0; i < venues.size(); i++) {

                    String snippet = "Description: " + venues.get(i).getDescription() + ".\n" + "Category: " + venues.get(i).getCategory() + ".\n" +
                            "Address: " + venues.get(i).getAddress() + ".\n" + "Latitude: " + venues.get(i).getLatitude() + ".\n" + "Longitude: " + venues.get(i).getLongitude();

                    LatLng myLocation = new LatLng(venues.get(i).getLatitude(), venues.get(i).getLongitude());
                    MarkerOptions marker = new MarkerOptions().position(myLocation).title(venues.get(i).getName()).
                            snippet(snippet);

                    googleMap.addMarker(marker);

                    markerVenueMap.put(marker, venues.get(i));

                    googleMap.moveCamera(CameraUpdateFactory.newLatLng(myLocation));
                    googleMap.moveCamera(CameraUpdateFactory.zoomTo(10));

                    googleMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {

                        @Override
                        public View getInfoWindow(Marker arg0) {
                            return null;
                        }

                        @Override
                        public View getInfoContents(Marker marker) {

                            LinearLayout info = new LinearLayout(MyMapsActivity.this);
                            info.setOrientation(LinearLayout.VERTICAL);


                            TextView title = new TextView(MyMapsActivity.this);
                            title.setTextColor(Color.BLACK);
                            title.setGravity(Gravity.CENTER);
                            title.setTypeface(null, Typeface.BOLD);
                            title.setText(marker.getTitle());

                            TextView snippet = new TextView(MyMapsActivity.this);
                            snippet.setWidth(300);
                            snippet.setTextColor(Color.GRAY);
                            snippet.setText(marker.getSnippet());

                            info.addView(title);
                            info.addView(snippet);

                            return info;
                        }
                    });
                }

                googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(final Marker marker) {
                        fab_delete_venue.setVisibility(View.VISIBLE);
                        fab_edit_venue.setVisibility(View.VISIBLE);

                        fab_delete_venue.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                getRetainFragment().deletePlace(markerVenueMap.get(marker).getId());
                            }
                        });
                        return false;
                    }
                });

                fab_add_venue.setVisibility(View.INVISIBLE);
                fab_refresh_venue.setVisibility(View.INVISIBLE);

                googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(final LatLng latLng) {
                        googleMap.clear();

                        googleMap.addMarker(new MarkerOptions().position(latLng));

                        fab_add_venue.setVisibility(View.VISIBLE);
                        fab_refresh_venue.setVisibility(View.VISIBLE);

                        fab_delete_venue.setVisibility(View.INVISIBLE);
                        fab_edit_venue.setVisibility(View.INVISIBLE);

                        fab_add_venue.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                double[] position = {latLng.latitude, latLng.longitude};

                                startActivity(new Intent(MyMapsActivity.this, AddNewVenue.class).putExtra("position", position));

                            }
                        });

                        fab_refresh_venue.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                setupNetworkFragment();
                                fab_add_venue.setVisibility(View.INVISIBLE);
                                fab_refresh_venue.setVisibility(View.INVISIBLE);
                            }
                        });

                    }
                });
            }
        });


    }


    @SuppressWarnings("MissingPermission")
    @NeedsPermission(Manifest.permission.ACCESS_FINE_LOCATION)
    void enableMyLocation(GoogleMap googleMap) {
        googleMap.setMyLocationEnabled(true);
    }


    @OnPermissionDenied(Manifest.permission.ACCESS_FINE_LOCATION)
    void showPermissionDeniedInfo() {
        Toast.makeText(this, "Please enable Location Permission for  proper app usage", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // NOTE: delegate the permission handling to generated method
        MyMapsActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }
}
