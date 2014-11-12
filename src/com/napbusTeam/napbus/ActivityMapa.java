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

    private static final long MINIMUM_DISTANCECHANGE_FOR_UPDATE = 1; // in Meters
    private static final long MINIMUM_TIME_BETWEEN_UPDATE = 1000; // in Milliseconds
	     
    private static final long POINT_RADIUS = 1000; // in Meters
    private static final long PROX_ALERT_EXPIRATION = -1;
	 
    private static final String POINT_LATITUDE_KEY = "POINT_LATITUDE_KEY";
    private static final String POINT_LONGITUDE_KEY = "POINT_LONGITUDE_KEY";
	     
    private static final String PROX_ALERT_INTENT =
         "com.javacodegeeks.android.lbs.ProximityAlert";
     
    private LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);
		setUpMapIfNeeded(); //Llama a cargar al mapa
		addClickListener();
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    MINIMUM_TIME_BETWEEN_UPDATE,
                    MINIMUM_DISTANCECHANGE_FOR_UPDATE,
                    new MyLocationListener()
    );
    }

    private void addClickListener(){
    	   mMap.setOnMapClickListener(new ClickListener(mMap){
   		public void onClick (View v){
	    		saveProxAlertPoint();
    		}
    	   });
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
	   private void saveProxAlertPoint() {
		Toast.makeText(this, "Dentro del saveProxPoint!!!!!!", Toast.LENGTH_LONG);
		Location location =
        locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		if (location==null) {
		    Toast.makeText(this, "No last known location. Aborting...",
		        Toast.LENGTH_LONG).show();
		    return;
		}
		saveCoordinatesInPreferences((float)location.getLatitude(),
               (float)location.getLongitude());

		addProximityAlert(location.getLatitude(), location.getLongitude());
	}
				 
    private void saveCoordinatesInPreferences(float latitude, float longitude) {
		// TODO Auto-generated method stub
    	SharedPreferences prefs =
	       this.getSharedPreferences(getClass().getSimpleName(),
	                       Context.MODE_PRIVATE);
	    SharedPreferences.Editor prefsEditor = prefs.edit();
	    prefsEditor.putFloat(POINT_LATITUDE_KEY, latitude);
	    prefsEditor.putFloat(POINT_LONGITUDE_KEY, longitude);
	    prefsEditor.commit();
	}

	private void addProximityAlert(double latitude, double longitude) {
				         
        Intent intent = new Intent(PROX_ALERT_INTENT);
        PendingIntent proximityIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
				
	    locationManager.addProximityAlert(
	        latitude, // the latitude of the central point of the alert region
	        longitude, // the longitude of the central point of the alert region
	        POINT_RADIUS, // the radius of the central point of the alert region, in meters
	        PROX_ALERT_EXPIRATION, // time for this proximity alert, in milliseconds, or -1 to indicate no expiration
	        proximityIntent // will be used to generate an Intent to fire when entry to or exit from the alert region is detected
	    		);
			         
       IntentFilter filter = new IntentFilter(PROX_ALERT_INTENT); 
       registerReceiver(new ProximityIntentReceiver(), filter);	
	}

	private Location retrievelocationFromPreferences() {
		        SharedPreferences prefs =
		           this.getSharedPreferences(getClass().getSimpleName(),
		                           Context.MODE_PRIVATE);
		        Location location = new Location("POINT_LOCATION");
		        location.setLatitude(prefs.getFloat(POINT_LATITUDE_KEY, 0));
		        location.setLongitude(prefs.getFloat(POINT_LONGITUDE_KEY, 0));
		        return location;
		}
	
	public class MyLocationListener implements LocationListener {
	    public void onLocationChanged(Location location) {
	        Location pointLocation = retrievelocationFromPreferences();
	        float distance = location.distanceTo(pointLocation);
	        Toast.makeText(Map.this,
	                "Distance from Point:"+distance, Toast.LENGTH_LONG).show();
	    }
	    public void onStatusChanged(String s, int i, Bundle b) {           
	    }
	    public void onProviderDisabled(String s) {
	    }
	    public void onProviderEnabled(String s) {           
	    }
	}
	}


