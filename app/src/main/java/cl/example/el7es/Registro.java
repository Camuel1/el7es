package cl.example.el7es;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import android.app.Application;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import android.webkit.WebView;

import java.util.HashMap;
import java.util.Map;


public class Registro extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseFirestore Firestore;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button btnRegister;

    private Button btmenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        // Inicializar FirebaseAuth
        mAuth = FirebaseAuth.getInstance();

        btmenu = findViewById(R.id.btback2);
        editTextEmail = findViewById(R.id.id_email);
        editTextPassword = findViewById(R.id.id_pass);
        btnRegister = findViewById(R.id.bt_registro);

        btmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navegar a la actividad "Registro"
                Intent intent = new Intent(Registro.this, MenuPrincipal.class);
                startActivity(intent);
            }
        });

        // Agregar un Listener al botón de registro
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Aquí se inicia el proceso de reCAPTCHA antes de registrar al usuario

                Firestore = FirebaseFirestore.getInstance();
                String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();

                if (email.isEmpty() || password.isEmpty()) {
                    // Verificar si el email o la contraseña están vacíos
                    Toast.makeText(Registro.this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
                } else {
                    // Registro del usuario
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(Registro.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // El registro fue exitoso
                                        registrouser(email, password);
                                        Toast.makeText(Registro.this, "Registro de usuario exitoso", Toast.LENGTH_SHORT).show();

                                        Intent intent = new Intent(Registro.this, MenuPrincipal.class);
                                        startActivity(intent);
                                        // Puedes redirigir al usuario a la pantalla principal u otra actividad aquí
                                    } else {
                                        // El registro falló
                                        Toast.makeText(Registro.this, "Error al registrar el usuario", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }

            private void registrouser(String email, String password) {
                String id = mAuth.getCurrentUser().getUid();
                Map<String, Object> map = new HashMap<>();
                map.put("id", id);
                map.put("email", email);
                map.put("password", password);

                Firestore.collection("user").document(id).set(map)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                finish();
                                startActivity(new Intent(Registro.this, LoginActivity.class));
                                Toast.makeText(Registro.this, "Usuario registrado", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Registro.this, "Error al guardar", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }
}



