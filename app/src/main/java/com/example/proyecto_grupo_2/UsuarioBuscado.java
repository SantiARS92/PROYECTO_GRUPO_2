package com.example.proyecto_grupo_2;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class UsuarioBuscado extends AppCompatActivity {

    protected TextView nombreUser;
    protected TextView ciudadUser;
    protected TextView enfermedadUser;
    protected TextView emailUser;

    protected String nom = "";
    protected String ciu = "";
    protected String enf = "";
    protected String em = "";

    protected Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_usuario_buscado);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_UsuarioBuscadoActivity), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        nombreUser = findViewById(R.id.nombreUser_usuarioBuscado);
        ciudadUser = findViewById(R.id.ciudadUser_usuarioBuscado);
        enfermedadUser = findViewById(R.id.enfermedadUser_usuarioBuscado);
        emailUser = findViewById(R.id.emailUser_usuarioBuscado);

        extras = getIntent().getExtras();

        if (extras != null) {

            nom = extras.getString("nombre", "");
            ciu = extras.getString("ciudad", "");
            enf = extras.getString("enfermedad", "");
            em = extras.getString("email", "");

            nombreUser.setText(nom);
            ciudadUser.setText(ciu);
            enfermedadUser.setText(enf);
            emailUser.setText(em);
        }
    }
}
