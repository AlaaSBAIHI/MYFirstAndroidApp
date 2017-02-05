package com.example.redi.MyFirstAndroidApp.models.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.redi.MyFirstAndroidApp.R;
import com.example.redi.MyFirstAndroidApp.models.entities.Venue;
import com.example.redi.MyFirstAndroidApp.models.functional.Consumer;
import com.example.redi.MyFirstAndroidApp.models.ui.fragments.RetainFragment;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
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
public class MyMapsActivity extends AppCompatActivity implements
        ConnectionCallbacks, OnConnectionFailedListener, LocationListener {

    public static final String NETWORK_FRAGMENT_TAG = "NETWORK_FRAGMENT";

    public float markerColor;

    private Map<Marker, Venue> markerVenueMap = new HashMap<>();

    private LatLng venuesLocation;

    private Location mLastLocation;

    private MarkerOptions markerOptions;

    private Marker venuesMarker;

    private Marker myLocationMarker;

    private FloatingActionButton fab_add_venue;

    private FloatingActionButton fab_edit_venue;

    private FloatingActionButton fab_delete_venue;

    private FloatingActionButton fab_refresh_venue;

    private GoogleApiClient mGoogleApiClient;

    private GoogleMap myCurrentGoogleMap;

    private LocationRequest mLocationRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_maps);

        connectToGPSAPIClient();

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


    public RetainFragment getRetainFragment() {
        RetainFragment retainFragment = (RetainFragment) getSupportFragmentManager().findFragmentByTag(NETWORK_FRAGMENT_TAG);
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
                //googleMap.clear();

                MyMapsActivityPermissionsDispatcher.enableMyLocationWithCheck(MyMapsActivity.this, googleMap);


                myCurrentGoogleMap = googleMap;

                drawMyCurrentLocation(googleMap);


                for (int i = 0; i < venues.size(); i++) {

                    String snippet = "Description: " + venues.get(i).getDescription() + ".\n" + "Category: " + venues.get(i).getCategory() + ".\n" +
                            "Address: " + venues.get(i).getAddress() + ".\n" + "Latitude: " + venues.get(i).getLatitude() + ".\n" + "Longitude: " + venues.get(i).getLongitude();

                    venuesLocation = new LatLng(venues.get(i).getLatitude(), venues.get(i).getLongitude());


                    switch (venues.get(i).getCategory().toString().toLowerCase()) {
                        case "bar":
                            markerColor = BitmapDescriptorFactory.HUE_ORANGE;
                            break;

                        case "restaurant":
                            markerColor = BitmapDescriptorFactory.HUE_BLUE;
                            break;

                        case "coworking_space":
                            markerColor = BitmapDescriptorFactory.HUE_GREEN;
                            break;
                    }

                    markerOptions = new MarkerOptions().position(venuesLocation).title(venues.get(i).getName()).
                            snippet(snippet).icon(BitmapDescriptorFactory.defaultMarker(markerColor));


                    venuesMarker = googleMap.addMarker(markerOptions);

                    markerVenueMap.put(venuesMarker, venues.get(i));

                    if (mLastLocation == null) {
                        googleMap.moveCamera(CameraUpdateFactory.newLatLng(venuesLocation));
                        googleMap.moveCamera(CameraUpdateFactory.zoomTo(10));

                    }
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
                        fab_add_venue.setVisibility(View.INVISIBLE);
//                        fab_refresh_venue.setVisibility(View.INVISIBLE);
                        fab_delete_venue.setVisibility(View.VISIBLE);
                        fab_edit_venue.setVisibility(View.VISIBLE);

                        fab_edit_venue.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                startActivity(new Intent(MyMapsActivity.this, AddNewVenue.class).putExtra("updateVenueDetails", markerVenueMap.get(marker)).putExtra("actionType", "update"));

                            }
                        });


                        fab_delete_venue.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                getRetainFragment().deletePlace(new Consumer<Void>() {
                                    @Override
                                    public void apply(Void aVoid) {
                                        setupNetworkFragment();
                                    }
                                }, markerVenueMap.get(marker));

                                fab_delete_venue.setVisibility(View.INVISIBLE);
                                fab_edit_venue.setVisibility(View.INVISIBLE);

                            }
                        });
                        return false;
                    }
                });


                fab_add_venue.setVisibility(View.INVISIBLE);
//                fab_refresh_venue.setVisibility(View.INVISIBLE);

                googleMap.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener() {
                    @Override
                    public boolean onMyLocationButtonClick() {
                        if (mLastLocation != null) {

                            if (myLocationMarker != null)
                                myLocationMarker.remove();

                            myLocationMarker = googleMap.addMarker(new MarkerOptions().position(new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude())).draggable(true));


                            fab_add_venue.setVisibility(View.VISIBLE);
//                            fab_refresh_venue.setVisibility(View.VISIBLE);

                            fab_delete_venue.setVisibility(View.INVISIBLE);
                            fab_edit_venue.setVisibility(View.INVISIBLE);

                        }
                        return false;
                    }
                });


                fab_add_venue.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        double[] position = {mLastLocation.getLatitude(), mLastLocation.getLongitude()};

                        startActivity(new Intent(MyMapsActivity.this, AddNewVenue.class).putExtra("position", position));

                    }
                });

                googleMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
                    @Override
                    public void onMarkerDragStart(Marker marker) {
                    }

                    @Override
                    public void onMarkerDrag(Marker marker) {
                    }

                    @Override
                    public void onMarkerDragEnd(Marker marker) {
                        fab_add_venue.setVisibility(View.INVISIBLE);
//                        fab_refresh_venue.setVisibility(View.INVISIBLE);
                    }
                });
/*
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
                });*/
            }


        });


    }


    @SuppressWarnings("MissingPermission")
    @NeedsPermission(Manifest.permission.ACCESS_FINE_LOCATION)
    public void enableMyLocation(GoogleMap googleMap) {
        googleMap.setMyLocationEnabled(true);
    }


    @OnPermissionDenied(Manifest.permission.ACCESS_FINE_LOCATION)
    public void showPermissionDeniedInfo() {
        Toast.makeText(this, "Please enable Location Permission for  proper app usage", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // NOTE: delegate the permission handling to generated method
        MyMapsActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }


    public void connectToGPSAPIClient() {
        // Create an instance of GoogleAPIClient.
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }
    }


    @Override
    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    @Override
    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();

    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {


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

        createLocationRequest();

        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);

        LocationServices.FusedLocationApi.requestLocationUpdates(
                mGoogleApiClient, mLocationRequest, this);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


    protected void createLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(5000);
        mLocationRequest.setFastestInterval(2000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

    @Override
    public void onLocationChanged(Location location) {
        mLastLocation = location;
        if (myCurrentGoogleMap != null)
            drawMyCurrentLocation(myCurrentGoogleMap);

        else
            Toast.makeText(this, "Failed to find the current Location!", Toast.LENGTH_SHORT);

    }


    private void drawMyCurrentLocation(GoogleMap googleMap) {
        if (mLastLocation != null) {
            if (myLocationMarker != null) {
                myLocationMarker.remove();
            }

            LatLng latLng = new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude());
            markerOptions = new MarkerOptions().position(latLng).title("Current Location").draggable(true);

            myLocationMarker = googleMap.addMarker(markerOptions);
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude())));
            googleMap.moveCamera(CameraUpdateFactory.zoomTo(15));

        }
    }
}
