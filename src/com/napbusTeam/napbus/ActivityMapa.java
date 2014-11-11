package com.napbusTeam.napbus;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;

import android.support.v4.app.FragmentActivity;

public class ActivityMapa extends FragmentActivity {

GoogleMap mMap = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);
		setUpMapIfNeeded(); //Llama a cargar al mapa
		addClickListener();
    }

    private void addClickListener(){
    	   mMap.setOnMapClickListener(new ClickListener(mMap));
    	   }

	private void setUpMapIfNeeded() {
	      if (mMap == null) {
	      mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
	      
	      if (mMap != null) {

		//Seteamos el tipo de mapa 
		mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
		
	
		//Auto ubicacion gps
		mMap.setMyLocationEnabled(true);
		
		LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();

        Location location = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, true));
        if (location != null)
        {
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                    new LatLng(location.getLatitude(), location.getLongitude()), 13));

            CameraPosition cameraPosition = new CameraPosition.Builder()
            .target(new LatLng(location.getLatitude(), location.getLongitude()))      // Sets the center of the map to location user
            .zoom(15)                   // nivel de zoom
            .bearing(0)                 // Orientacion de la camara
            .tilt(0)                    // Establece la inclinación de la cámara en grados
            .build();                   // Constructor
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        }

	      }
	      }
	   }
	}


