package cl.example.el7es;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class Sensores extends AppCompatActivity {

    SensorManager mgr;
    TextView textLista;
    Button btnVolverMenu;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensores);

        btnVolverMenu = findViewById(R.id.btnVolverMenu);

        mgr=(SensorManager) getSystemService(Context.SENSOR_SERVICE);
        textLista =(TextView) findViewById(R.id.listaSensor);
        List<Sensor> sensorList=mgr.getSensorList(Sensor.TYPE_ALL);
        StringBuilder stringBuilder= new StringBuilder();
        for (Sensor s: sensorList){
            stringBuilder.append(s.getName());
        }

        textLista.setVisibility(View.VISIBLE);
        textLista.setText(stringBuilder);

        btnVolverMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navegar de regreso a MenuPrincipalActivity
                Intent intent = new Intent(Sensores.this, MenuPrincipal.class);
                startActivity(intent);
            }
        });


    }
}