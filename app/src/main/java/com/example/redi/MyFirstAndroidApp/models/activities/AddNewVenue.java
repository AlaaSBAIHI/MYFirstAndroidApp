package com.example.redi.MyFirstAndroidApp.models.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.example.redi.MyFirstAndroidApp.R;
import com.example.redi.MyFirstAndroidApp.models.entities.Venue;
import com.example.redi.MyFirstAndroidApp.models.ui.fragments.RetainFragment;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

public class AddNewVenue extends AppCompatActivity {

    private Venue venue;

    private RetainFragment retainFragment;

    private String id;
    private EditText name;
    private EditText description;
    private EditText address;
    private EditText latitude;
    private EditText longitude;
    private String actionType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_venue);


        name = (EditText) findViewById(R.id.new_venue_txt_name);
        description = (EditText) findViewById(R.id.new_venue_txt_description);
        address = (EditText) findViewById(R.id.new_venue_txt_address);
        final Spinner category = (Spinner) findViewById(R.id.new_venue_spr_category);

        List<String> categories = new ArrayList<>();
        categories.add("Bar");
        categories.add("Restaurant");
        categories.add("Coworking Space");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, categories);
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        category.setAdapter(arrayAdapter);

        latitude = (EditText) findViewById(R.id.new_venue_txt_latitude);
        longitude = (EditText) findViewById(R.id.new_venue_txt_longitude);

        Intent intent = getIntent();

        double[] position = intent.getDoubleArrayExtra("position");

        if (position != null) {
            latitude.setText(String.valueOf(position[0]));
            longitude.setText(String.valueOf(position[1]));
        }

        actionType = intent.getStringExtra("actionType");

        venue = intent.getParcelableExtra("updateVenueDetails");

        if (venue != null && actionType != null) {

            name.setText(venue.getName());
            description.setText(venue.getDescription());

            String normalisedCategory = Normalizer.normalize(venue.getCategory(), Normalizer.Form.NFD).toLowerCase().replaceAll("_", " ");

            for (int i = 0; i < categories.size(); i++) {
                if (normalisedCategory.equalsIgnoreCase(categories.get(i).toString())) {
                    category.setSelection(i);
                    break;
                }

            }

            address.setText(venue.getAddress());
            latitude.setText(String.valueOf(venue.getLatitude()));
            longitude.setText(String.valueOf(venue.getLongitude()));
        }


        latitude.setEnabled(false);
        longitude.setEnabled(false);


        ImageButton save = (ImageButton) findViewById(R.id.new_venue_btn_save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validate()) {
                    if (actionType != null && actionType.equalsIgnoreCase("update")) {
                        venue.setName(name.getText().toString());
                        venue.setDescription(description.getText().toString());
                        venue.setCategory(category.getSelectedItem().toString().toLowerCase());
                        venue.setAddress(address.getText().toString());
                        venue.setLatitude(Double.parseDouble(latitude.getText().toString()));
                        venue.setLongitude(Double.parseDouble(longitude.getText().toString()));

                        actionType = "u";
                        getRetainFragment().updatePlace(venue);
                        startActivity(new Intent(AddNewVenue.this, MyMapsActivity.class));

                    } else {
                        venue = new Venue(name.getText().toString(), description.getText().toString(),
                                category.getSelectedItem().toString().toLowerCase(), address.getText().toString(),
                                Double.parseDouble(latitude.getText().toString()), Double.parseDouble(longitude.getText().toString()));

                        actionType = " ";
                        getRetainFragment().createPlace(venue);
                        startActivity(new Intent(AddNewVenue.this, MyMapsActivity.class));
                    }
                }
            }
        });

        ImageButton cancel = (ImageButton) findViewById(R.id.new_venue_btn_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddNewVenue.this, MyMapsActivity.class));
            }
        });
    }


    public RetainFragment getRetainFragment() {
        retainFragment = (RetainFragment) getSupportFragmentManager().findFragmentByTag(MyMapsActivity.NETWORK_FRAGMENT_TAG);
        if (retainFragment == null) {
            retainFragment = new RetainFragment();
            getSupportFragmentManager().beginTransaction().add(retainFragment, MyMapsActivity.ACCESSIBILITY_SERVICE).commit();
        }

        return retainFragment;
    }

    public boolean validate() {
        return checkText(name) && checkText(description) && checkText(address) && checkText(latitude) && checkText(longitude);

    }

    public boolean checkText(EditText editText) {
        if (editText.getText().toString().isEmpty() || editText.getText() == null || editText.getText().toString().trim().isEmpty() ||
                editText.getText().toString().length() < 3) {
            editText.setError("You should add a valid " + editText.getHint().toString() + "!");
            return false;
        }
        return true;
    }
}
