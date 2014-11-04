package com.napbusTeam.napbus;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class ActivityMapa extends FragmentActivity {

public static final LatLng DESTINO = new LatLng(0.0, 0.0);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	GoogleMap mMap = null;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);
	setUpMapIfNeeded(); //Llama a cargar al mapa
	setMarker(DESTINO,"Destino",""); //Seteamos el marcador de destino
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

	private void setMarker(LatLng position, String titulo, String info) {
	  // Agregamos marcadores para indicar sitios de interéses.
	  Marker myMaker = mMap.addMarker(new MarkerOptions()
	       .position(position)
	       .title(titulo)  //Agrega un titulo al marcador
	       .snippet(info)   //Agrega información detalle relacionada con el marcador 
	       .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))); //Color del marcador
	}

}
