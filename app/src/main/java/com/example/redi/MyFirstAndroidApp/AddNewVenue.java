package com.example.redi.MyFirstAndroidApp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.example.redi.MyFirstAndroidApp.models.entities.Venue;
import com.example.redi.MyFirstAndroidApp.models.ui.fragments.RetainFragment;

import java.util.ArrayList;
import java.util.List;

public class AddNewVenue extends AppCompatActivity {

    private Venue venue;

    private RetainFragment retainFragment;

    private EditText name;
    private EditText description;
    private EditText address;
    private EditText latitude;
    private EditText longitude;


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
        categories.add("Coworking Place");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, categories);
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        category.setAdapter(arrayAdapter);

        latitude = (EditText) findViewById(R.id.new_venue_txt_latitude);
        longitude = (EditText) findViewById(R.id.new_venue_txt_longitude);


        Intent intent = getIntent();

        double[] position = intent.getDoubleArrayExtra("position");

        latitude.setText(String.valueOf(position[0]));
        longitude.setText(String.valueOf(position[1]));

        latitude.setEnabled(false);
        longitude.setEnabled(false);


        ImageButton save = (ImageButton) findViewById(R.id.new_venue_btn_save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validate()) {
                    venue = new Venue(name.getText().toString(), description.getText().toString(),
                            category.getSelectedItem().toString().toLowerCase(), address.getText().toString(),
                            Double.parseDouble(latitude.getText().toString()), Double.parseDouble(longitude.getText().toString()));

                    getRetainFragment().createPlace(venue);
                    startActivity(new Intent(AddNewVenue.this, MyMapsActivity.class));
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


    private RetainFragment getRetainFragment() {
        retainFragment = (RetainFragment) getSupportFragmentManager().findFragmentByTag(MyMapsActivity.NETWORK_FRAGMENT_TAG);
        if (retainFragment == null) {
            retainFragment = new RetainFragment();
            getSupportFragmentManager().beginTransaction().add(retainFragment, MyMapsActivity.NETWORK_FRAGMENT_TAG).commit();
        }

        return retainFragment;
    }


    public boolean validate() {
        if (checkText(name) && checkText(description) && checkText(address) && checkText(latitude) && checkText(longitude))
            return true;

        return false;
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
