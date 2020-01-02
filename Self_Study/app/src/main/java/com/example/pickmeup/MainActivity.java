package com.example.pickmeup;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.microsoft.maps.MapElementLayer;
import com.microsoft.maps.MapIcon;
import com.microsoft.maps.MapRenderMode;
import com.microsoft.maps.MapView;

public class MainActivity extends AppCompatActivity {
    private MapView mMapView;
    //private static final Geopoint LAKE_WASHINGTON = new Geopoint(47.609466, -122.265185);
    private MapElementLayer mPinLayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMapView = new MapView(this, MapRenderMode.VECTOR);  // or use MapRenderMode.RASTER for 2D map
        mMapView.setCredentialsKey("Avf48jA4uiUjRouQOzb6Gusq3gCbDfTty71uLJgkoj9ZUmumXFTVyfW8icGbabb0");
        mPinLayer = new MapElementLayer();
        mMapView.getLayers().add(mPinLayer);
        //Geopoint location = LAKE_WASHINGTON  ;// your pin lat-long coordinates
        String title ="Your current Loction" ;     // title to be shown next to the pin
        // your pin graphic (optional)

        MapIcon pushpin = new MapIcon();
        //pushpin.setLocation(location);
        pushpin.setTitle(title);
        //pushpin.setImage(new MapImage(pinBitmap));

        mPinLayer.getElements().add(pushpin);
        ((FrameLayout)findViewById(R.id.map_view)).addView(mMapView);
    }
    @Override
    protected void onStart() {
        super.onStart();
      //  mMapView.setScene(
             //   MapScene.createFromLocationAndZoomLevel(LAKE_WASHINGTON, 10),
             //   MapAnimationKind.NONE);
    }
    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        mMapView.onPause();
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMapView.onSaveInstanceState(outState);
    }
    @Override
    protected void onStop() {
        super.onStop();
        mMapView.onStop();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }
    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }
}
