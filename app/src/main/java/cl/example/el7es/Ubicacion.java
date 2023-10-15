package cl.example.el7es;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class Ubicacion extends AppCompatActivity {

    private Button btnVolverMenuPrincipal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubicacion);

        btnVolverMenuPrincipal = findViewById(R.id.btmenu);

        btnVolverMenuPrincipal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navegar de regreso a MenuPrincipalActivity
                Intent intent = new Intent(Ubicacion.this, MenuPrincipal.class);
                startActivity(intent);
            }
        });
    }
}