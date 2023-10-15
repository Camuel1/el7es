package cl.example.el7es;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private EditText etNombre, etEmail, etHora;
    private Button btnGuardar, btnMenuPrincipal;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNombre = findViewById(R.id.id_name);
        etEmail = findViewById(R.id.id_correo);
        etHora = findViewById(R.id.id_hora);

        btnGuardar = findViewById(R.id.id_agendar);
        btnMenuPrincipal = findViewById(R.id.bt_idmenu);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("horarios");

        // Agregar un Listener para el botón de MenuPrincipal
        btnMenuPrincipal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MenuPrincipal.class);
                startActivity(intent);
            }
        });

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String nombre = etNombre.getText().toString();
                final String email = etEmail.getText().toString();
                final String hora = etHora.getText().toString();

                if (nombre.isEmpty() || email.isEmpty() || hora.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Completa todos los campos", Toast.LENGTH_SHORT).show();
                } else {
                    // Comprobar si la hora ya está registrada
                    databaseReference.orderByChild("hora").equalTo(hora).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                Toast.makeText(MainActivity.this, "La hora ya está reservada", Toast.LENGTH_SHORT).show();
                            } else {
                                DatabaseReference nuevoUsuarioRef = databaseReference.push();
                                nuevoUsuarioRef.child("nombre").setValue(nombre);
                                nuevoUsuarioRef.child("email").setValue(email);
                                nuevoUsuarioRef.child("hora").setValue(hora);

                                Toast.makeText(MainActivity.this, "Hora Reservada", Toast.LENGTH_SHORT).show();

                                etNombre.setText("");
                                etEmail.setText("");
                                etHora.setText("");
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            // Maneja los errores de Firebase Database aquí
                        }
                    });
                }
            }
        });
    }
}