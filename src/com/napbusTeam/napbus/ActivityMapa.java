package com.napbusTeam.napbus;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.graphics.Color;
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
	// Configuramos el objeto GoogleMaps con valores iniciales.
	      if (mMap == null) {
	      //Instanciamos el objeto mMap a partir del MapFragment definido bajo el Id "map"
	      mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
	      // Chequeamos si se ha obtenido correctamente una referencia al objeto GoogleMap
	      if (mMap != null) {
		// El objeto GoogleMap ha sido referenciado correctamente 
		//ahora podemos manipular sus propiedades

		//Seteamos el tipo de mapa 
		mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
	
		//Activamos la capa o layer MyLocation
		mMap.setMyLocationEnabled(true);
	      }
	   }
	}

}
