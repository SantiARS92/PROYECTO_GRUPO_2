package com.example.proyecto_grupo_2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class BienvenidaActivity extends AppCompatActivity {

    protected LinearLayout layoutLogin;
    protected ImageView imageLogin;
    protected Button btnLogin;
    protected Button btnRegistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bienvenida);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_MiPerfilActivity), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //ELEMENTOS REFERENCIADOS
        layoutLogin = (LinearLayout) findViewById(R.id.layout_bienvenida);
        imageLogin = (ImageView) findViewById(R.id.logo_bienvenida);
        btnLogin = (Button) findViewById(R.id.btnIniciarSesion_bienvenida);
        btnRegistro = (Button) findViewById(R.id.btnCrearCuenta_bienvenida);

        //BTN INICIAR SESION

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               Intent pasarPantalla = new Intent(BienvenidaActivity.this, LoginActivity.class);
               finish();
               startActivity(pasarPantalla);

            }
        });

        //BTN REGISTRARSE

        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent pasarPantalla = new Intent(BienvenidaActivity.this, Registro1Activity.class);
                finish();
                startActivity(pasarPantalla);

            }
        });



    }
}