package com.napbusTeam.napbus;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public abstract class Posicion {
	public static MarkerOptions opciones = new MarkerOptions();	
	public static void setOpciones(LatLng position, String titulo, String info){
		opciones.position(position);
		opciones.title(titulo);
		opciones.snippet(info);
		opciones.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
	}

}
