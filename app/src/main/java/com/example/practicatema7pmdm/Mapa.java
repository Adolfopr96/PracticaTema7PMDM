package com.example.practicatema7pmdm;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.practicatema7pmdm.DataBaseManager.DB_SQLite;
import com.example.practicatema7pmdm.Logic.LogicLugar;
import com.example.practicatema7pmdm.Model.Lugar;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Mapa extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;
    LatLng nuevaPosicion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    public void mostrarTodo() {
        List<Lugar> lstLugar = LogicLugar.listaLugar(this);
        if (lstLugar == null) {
        } else {

            for (Lugar p : lstLugar) {
                nuevaPosicion = new LatLng(p.getLatitud(), p.getLongitud());
                if (p.getCategoria() == 1) {

                    mMap.addMarker(new MarkerOptions().position(nuevaPosicion).title(p.getNombre()).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
                } else if (p.getCategoria() == 2) {
                    mMap.addMarker(new MarkerOptions().position(nuevaPosicion).title(p.getNombre()).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
                } else if (p.getCategoria() == 3) {
                    mMap.addMarker(new MarkerOptions().position(nuevaPosicion).title(p.getNombre()).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
                }
                else if (p.getCategoria() == 4) {
                    mMap.addMarker(new MarkerOptions().position(nuevaPosicion).title(p.getNombre()).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)));
                }
                else if (p.getCategoria() == 5) {
                    mMap.addMarker(new MarkerOptions().position(nuevaPosicion).title(p.getNombre()).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
                }
            }
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mostrarTodo();
        //mMap.addMarker(new MarkerOptions().position(Sevilla).title("Ciudad de Sevilla").icon(BitmapDescriptorFactory.fromResource(R.drawable.mi_marcador)));
        //mMap.addMarker(new MarkerOptions().position(Sevilla).title("Ciudad de Sevilla").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));
        //mMap.addMarker(new MarkerOptions().position(Huelva).title("Ciudad de Huelva"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(Sevilla));
        mMap.animateCamera(CameraUpdateFactory.zoomIn());
        mMap.animateCamera(CameraUpdateFactory.zoomTo(5), 2000, null);
        mMap.setOnMarkerClickListener(this);
    }

    @Override
    public boolean onMarkerClick(final Marker marker) {

        Integer clickCount = (Integer) marker.getTag();

        if (clickCount != null) {
            clickCount = clickCount + 1;
            marker.setTag(clickCount);
            Toast.makeText(this, marker.getTitle() + " ha sido pulsado " + clickCount + " veces.", Toast.LENGTH_SHORT).show();
        }

        return false;
    }
}
