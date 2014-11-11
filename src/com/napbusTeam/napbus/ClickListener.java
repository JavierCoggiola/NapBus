package com.napbusTeam.napbus;

import android.graphics.Color;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;

public class ClickListener implements OnMapClickListener {
	
	private GoogleMap map;
	public ClickListener(GoogleMap map){
		this.map = map;
	}
	
	@Override
	public void onMapClick(LatLng latLong) {
		Log.v("ClickListener latitude: ", String.valueOf(latLong.latitude));
		Log.v("ClickListener longitude: ", String.valueOf(latLong.longitude));
		setMarker(latLong,"Su Destino","Alarma sonara al llegar aquí"); 
		//Seteamos el marcador de destino
	}
	
	private void setMarker(LatLng position, String titulo, String info) {
		  this.map.clear();
		  Posicion.setOpciones(position, titulo, info);
		  this.map.addMarker(Posicion.opciones);
		  
		  Circle circle = this.map.addCircle(new CircleOptions()
		    .center(position)
		    .radius(440)
		    .strokeColor(Color.BLUE)
		    .fillColor(Color.TRANSPARENT)
		  	.strokeWidth(4));
		}
}