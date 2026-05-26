package com.example.proyecto_grupo_2;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Timer;
import java.util.TimerTask;

public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_intro);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.logo_main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //PERMITE OCULTAR EL MENÚ EN LA ACTIVIDAD
        //getSupportActionBar().hide();

        TimerTask myTimerTask = new TimerTask() {
            @Override
            public void run() {

                // Codigo de pasar pantalla
                // Codigo que se ejecuta tras la espera
                // Por ejemplo, Codigo para pasar a la siguiente actividad

                Intent pasarPantalla = new Intent(IntroActivity.this, BienvenidaActivity.class);
                finish();
                startActivity(pasarPantalla);
            }
        };

        //new timer
        Timer timer = new Timer();
        // schedule timer
        timer.schedule(myTimerTask, 3000); // 3 segundos de espera


    }
}