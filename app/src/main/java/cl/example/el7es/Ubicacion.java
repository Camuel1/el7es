package cl.example.el7es;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Ubicacion extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener, GoogleMap.OnMapLongClickListener {

    private Button btnVolverMenuPrincipal;

    EditText txtLatitud, txtLongitud;
    GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubicacion);

        btnVolverMenuPrincipal = findViewById(R.id.btmenu);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        btnVolverMenuPrincipal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navegar de regreso a MenuPrincipalActivity
                Intent intent = new Intent(Ubicacion.this, MenuPrincipal.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap= googleMap;




        this.mMap.setOnMapClickListener(this);
        this.mMap.setOnMapLongClickListener(this);

        LatLng Chillan = new LatLng(-36.6236501,-72.139589);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Chillan));
        LatLng ChillanSoccer = new LatLng(-36.6211772,-72.1107827);
        mMap.addMarker(new MarkerOptions().position(ChillanSoccer).title("Chillan Soccer"));
        LatLng LaCurvaSoccer = new LatLng(-36.6038949,-72.1118428);
        mMap.addMarker(new MarkerOptions().position(LaCurvaSoccer).title("LaCurvaSoccer"));
        LatLng PolideportivoLaLiga = new LatLng(-36.591593,-72.1164714);
        mMap.addMarker(new MarkerOptions().position(PolideportivoLaLiga).title("Polideportido La Liga"));





    }

    @Override
    public void onMapClick(@NonNull LatLng latLng) {
        txtLatitud.setText(""+latLng.latitude);
        txtLongitud.setText(""+latLng.longitude);

    }

    @Override
    public void onMapLongClick(@NonNull LatLng latLng) {
        txtLatitud.setText(""+latLng.latitude);
        txtLongitud.setText(""+latLng.longitude);
    }
}