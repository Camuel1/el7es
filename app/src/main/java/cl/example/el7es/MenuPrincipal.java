package cl.example.el7es;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MenuPrincipal extends AppCompatActivity {


    Button btnRegistro;
    Button btnLogin;
    Button btnUbicacion;
    Button btnActivityMain;
    Button btnSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

         btnRegistro = findViewById(R.id.btnregister);
         btnLogin = findViewById(R.id.btnlogin);
         btnUbicacion = findViewById(R.id.btnmap);
         btnActivityMain = findViewById(R.id.btn_reservar);
         btnSensor = findViewById(R.id.btnSensor);


        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuPrincipal.this, Registro.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuPrincipal.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        btnUbicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuPrincipal.this, Ubicacion.class);
                startActivity(intent);
            }
        });

        btnActivityMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuPrincipal.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnSensor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuPrincipal.this, Sensores.class);
                startActivity(intent);
            }
        });

    }


}