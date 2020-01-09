package com.example.pickmeup;

import android.content.Intent;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;

import java.util.Arrays;
import java.util.List;

public class TestActivity extends AppCompatActivity {
    private static final int AUTOCOMPLETE_REQUEST_CODE = 106;
    LocationListener locationListener;
    LocationManager locationManager;
    PlacesClient placesClient;
    AutoCompleteAdapter adapter;
    AutoCompleteTextView autoCompleteTextView;

    @Nullable
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_layout);
        String apiKey = "AIzaSyArFg0Rrt3sKxxilI7xsy6N5h85TYJ16TM";
        if (apiKey.isEmpty()) {
//            responseView.setText(getString(R.string.error));
        }

        // Setup Places Client
        if (!Places.isInitialized()) {
            Places.initialize(getApplicationContext(), apiKey);
        }

        placesClient = Places.createClient(this);
//        initAutoCompleteTextView();
        // Initialize the AutocompleteSupportFragment.
        AutocompleteSupportFragment autocompleteFragment = (AutocompleteSupportFragment)
                getSupportFragmentManager().findFragmentById(R.id.autocomplete_fragment);

        autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME));

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                Toast.makeText(TestActivity.this, (int) place.getLatLng().latitude, Toast.LENGTH_SHORT).show();
                Toast.makeText(TestActivity.this, (int) place.getLatLng().longitude, Toast.LENGTH_SHORT).show();
                // TODO: Get info about the selected place.
                Log.i("fg", "Place: " + place.getName() + ", " + place.getId());
            }

            @Override
            public void onError(Status status) {
                Toast.makeText(TestActivity.this, "error", Toast.LENGTH_SHORT).show();
                // TODO: Handle the error.
                Log.i("gh", "An error occurred: " + status);
            }
        });
    }


//    private void initAutoCompleteTextView() {
//
//        autoCompleteTextView = findViewById(R.id.auto);
//        autoCompleteTextView.setThreshold(1);
//        autoCompleteTextView.setOnItemClickListener(autocompleteClickListener);
//        adapter = new AutoCompleteAdapter(this, placesClient);
//        autoCompleteTextView.setAdapter(adapter);
//    }
//    private AdapterView.OnItemClickListener autocompleteClickListener = new AdapterView.OnItemClickListener() {
//        @Override
//        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//            try {
//                final AutocompletePrediction item = adapter.getItem(i);
//                String placeID = null;
//                if (item != null) {
//                    placeID = item.getPlaceId();
//                }
//
////                To specify which data types to return, pass an array of Place.Fields in your FetchPlaceRequest
////                Use only those fields which are required.
//
//                List<Place.Field> placeFields = Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.ADDRESS
//                        , Place.Field.LAT_LNG);
//
//                FetchPlaceRequest request = null;
//                if (placeID != null) {
//                    request = FetchPlaceRequest.builder(placeID, placeFields)
//                            .build();
//                }
//
//                if (request != null) {
//                    placesClient.fetchPlace(request).addOnSuccessListener(new OnSuccessListener<FetchPlaceResponse>() {
//                        @SuppressLint("SetTextI18n")
//                        @Override
//                        public void onSuccess(FetchPlaceResponse task) {
////                            responseView.setText(task.getPlace().getName() + "\n" + task.getPlace().getAddress());
//                        }
//                    }).addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception e) {
//                            e.printStackTrace();
////                            responseView.setText(e.getMessage());
//                        }
//                    });
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//        }
//    };
public void onSearchCalled() {
    // Set the fields to specify which types of place data to return.
    List<Place.Field> fields = Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.ADDRESS, Place.Field.LAT_LNG);
    // Start the autocomplete intent.
    Intent intent = new Autocomplete.IntentBuilder(
            AutocompleteActivityMode.FULLSCREEN, fields).setCountry("NG") //NIGERIA
            .build(this);
    startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE);
}
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == AUTOCOMPLETE_REQUEST_CODE) {
//            if (resultCode == RESULT_OK) {
//                Place place = Autocomplete.getPlaceFromIntent(data);
//                Log.i("fg", "Place: " + place.getName() + ", " + place.getId() + ", " + place.getAddress());
//                Toast.makeText(AutocompleteFromIntentActivity.this, "ID: " + place.getId() + "address:" + place.getAddress() + "Name:" + place.getName() + " latlong: " + place.getLatLng(), Toast.LENGTH_LONG).show();
//                String address = place.getAddress();
//                // do query with address
//
//            } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
//                // TODO: Handle the error.
//                Status status = Autocomplete.getStatusFromIntent(data);
//                Toast.makeText(AutocompleteFromIntentActivity.this, "Error: " + status.getStatusMessage(), Toast.LENGTH_LONG).show();
//                Log.i("fg", status.getStatusMessage());
//            } else if (resultCode == RESULT_CANCELED) {
//                // The user canceled the operation.
//            }
//        }
//    }
}
