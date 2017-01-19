package com.example.redi.MyFirstAndroidApp.models.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.example.redi.MyFirstAndroidApp.models.entities.Venue;
import com.example.redi.MyFirstAndroidApp.models.functional.Consumer;
import com.example.redi.MyFirstAndroidApp.models.http.RestClient;
import com.example.redi.MyFirstAndroidApp.models.http.VenueService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ReDI on 1/10/2017.
 */

public class RetainFragment extends Fragment {
    private VenueService service = RestClient.getInstance().createService(VenueService.class);
    private List<Venue> venues;
    private Consumer getConsumer;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }


    public void createPlace(final Venue venue) {
        Call<Void> call = service.createVenue(venue);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful())
                    showMessageSuccessful("Add New", venue.getName().toString());
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                showNetError();
            }
        });

    }


    public void getPlaces(final Consumer<List<Venue>> consumer) {
        getConsumer = consumer;
        if (venues == null) {
            Call<List<Venue>> call = service.getVenues();
            call.enqueue(new Callback<List<Venue>>() {
                @Override
                public void onResponse(Call<List<Venue>> call, Response<List<Venue>> response) {
                    if (response.isSuccessful()) {
                        venues = response.body();
                        getConsumer.apply(venues);
                    } else {
                        showNetError();
                    }
                }

                @Override
                public void onFailure(Call<List<Venue>> call, Throwable t) {
                    showNetError();
                }
            });
        } else {
            getConsumer.apply(venues);
        }
    }


    public void updatePlace(final Venue venue) {
        Call<Void> call = service.updateVenue(venue);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful())
                    showMessageSuccessful("Update", venue.getName().toLowerCase());
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                showNetError();
            }
        });
    }

    public void deletePlace(final long id) {
        Call<Void> call = service.deleteVenue(id);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful())
                    showMessageSuccessful("Delete", "");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                showNetError();
            }
        });
    }

    private void showNetError() {
        Toast.makeText(getActivity(), "Operation Failed!", Toast.LENGTH_SHORT).show();
    }

    private void showMessageSuccessful(String msg, String venue_name) {
        Toast.makeText(getActivity(), msg + " Venue: " + venue_name + " successful!", Toast.LENGTH_SHORT).show();
    }
}
