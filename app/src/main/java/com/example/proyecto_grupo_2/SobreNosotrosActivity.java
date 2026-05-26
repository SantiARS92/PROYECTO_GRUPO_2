package com.example.proyecto_grupo_2;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

public class SobreNosotrosActivity extends AppCompatActivity {

    //SE INSTANCIAN LOS ELEMENTOS QUE COMPONEN LA ACTIVITY DE MI PERFIL PARA SER REFERENCIADOS
    protected TextView espacioEnBlanco1;
    protected TextView cabeceraSobreNosotros;
    protected TextView espacioEnBlanco2;
    protected ImageView logoAcompaname;
    protected TextView queEsAcompaname;
    protected TextView espacioEnBlanco3;
    protected TextView descripcion;
    protected TextView espacioEnBlanco4;
    protected TextView autoresDelProyecto;
    protected TextView autor1;
    protected TextView autor2;
    protected TextView autor3;
    protected TextView autor4;
    protected ImageView logoIFP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sobre_nosotros);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_SobreNosotrosActivity), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //ELEMENTOS REFERENCIADOS

        espacioEnBlanco1 = (TextView) findViewById(R.id.espacioEnBlanco1_SobreNosotrosActivity);
        cabeceraSobreNosotros = (TextView) findViewById(R.id.cabecera_SobreNosotrosActivity);
        espacioEnBlanco2 = (TextView) findViewById(R.id.espacioEnBlanco2_SobreNosotrosActivity);
        logoAcompaname = (ImageView) findViewById(R.id.logo_SobreNosotrosActivity);
        logoAcompaname.setImageResource(R.drawable.acompanamelogo);
        queEsAcompaname = (TextView) findViewById(R.id.QueEs_SobreNosotrosActivity);
        espacioEnBlanco3 = (TextView) findViewById(R.id.espacioEnBlanco3_SobreNosotrosActivity);
        descripcion = (TextView) findViewById(R.id.descripcion_SobreNosotrosActivity);
        espacioEnBlanco4 = (TextView) findViewById(R.id.espacioEnBlanco4_SobreNosotrosActivity);
        autoresDelProyecto = (TextView) findViewById(R.id.autoreDelProyecto_SobreNosotrosActivity);
        autor1 = (TextView) findViewById(R.id.nombre1_SobreNosotrosActivity);
        autor2 = (TextView) findViewById(R.id.nombre2_SobreNosotrosActivity);
        autor3 = (TextView) findViewById(R.id.nombre3_SobreNosotrosActivity);
        autor4 = (TextView) findViewById(R.id.nombre4_SobreNosotrosActivity);
        logoIFP = (ImageView) findViewById(R.id.logoIFP_SobreNosotrosActivity);




    }
}