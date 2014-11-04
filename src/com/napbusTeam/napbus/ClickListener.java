package com.napbusTeam.napbus;

import android.util.Log;

import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.model.LatLng;

public class ClickListener implements OnMapClickListener {
	@Override
	public void onMapClick(LatLng latLong) {
		Log.v("ClickListener latitude: ", String.valueOf(latLong.latitude));
		Log.v("ClickListener longitude: ", String.valueOf(latLong.longitude));
	}
}