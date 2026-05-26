package com.example.proyecto_grupo_2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RegistroFinActivity extends AppCompatActivity {

    protected LinearLayout layoutFinRegistro;
    protected ImageView imgFinRegistro;
    protected TextView registroCompletado;
    protected Button btnVolverInicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registro_fin);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_MiPerfilActivity), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        layoutFinRegistro = (LinearLayout) findViewById(R.id.linearLayout_finRegistro);
        imgFinRegistro = (ImageView) findViewById(R.id.imageView_finRegistro);
        registroCompletado = (TextView) findViewById(R.id.textView_finRegistro);
        btnVolverInicio = (Button) findViewById(R.id.btnVolver_finRegistro);

        btnVolverInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent pasarPantalla = new Intent(RegistroFinActivity.this, LoginActivity.class);
                finish();
                startActivity(pasarPantalla);

            }
        });

    }
}