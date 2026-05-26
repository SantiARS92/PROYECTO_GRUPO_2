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

public class MiPerfilActivity extends AppCompatActivity {

    //SE INSTANCIAN LOS 22 ELEMENTOS QUE COMPONEN LA ACTIVITY DE MI PERFIL PARA SER REFERENCIADOS
    protected TextView espacioEnBlanco1;
    protected TextView cabecera;
    protected TextView espacioEnBlanco2;
    protected ImageView imgUser;
    protected TextView espacioEnBlanco3;
    protected TextView nombreUser;
    protected TextView nombre;
    protected String nom="";
    protected TextView apellidosUser;
    protected TextView apellidos;
    protected String ape="";
    protected TextView ciudadUser;
    protected TextView ciudad;
    protected String ciu="";
    protected TextView hospitalUser;
    protected TextView hospital;
    protected String hos="";
    protected TextView enfermedadUser;
    protected TextView enfermedad;
    protected String enf="";
    protected TextView infoUser;
    protected TextView info;
    protected String in="";
    protected TextView emailUser;
    protected TextView email;
    protected String em="";
    protected TextView tlfUser;
    protected TextView tlf;
    protected String tl="";
    protected TextView espacioEnBlanco4;
    protected Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mi_perfil);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_MiPerfilActivity), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //ELEMENTOS REFERENCIADOS

        espacioEnBlanco1 = (TextView) findViewById(R.id.espacioEnBlanco1_miPerfilActivity);
        cabecera = (TextView) findViewById(R.id.cabecera_miPerfilActivity);
        espacioEnBlanco2 = (TextView) findViewById(R.id.espacioEnBlanco2_miPerfilActivity);
        imgUser = (ImageView) findViewById(R.id.imagenUsuario_miPerfilActivity);
        imgUser.setImageResource(R.drawable.default_user);
        espacioEnBlanco3 = (TextView) findViewById(R.id.espacioEnBlanco3_miPerfilActivity);
        nombre = (TextView) findViewById(R.id.nombre_miPerfilActivity);
        nombreUser = (TextView) findViewById(R.id.nombreUser_miPerfilActivity);
        apellidos = (TextView) findViewById(R.id.apellidos_miPerfilActivity);
        apellidosUser = (TextView) findViewById(R.id.apellidosUser_miPerfilActivity);
        ciudad = (TextView) findViewById(R.id.ciudad_miPerfilActivity);
        ciudadUser = (TextView) findViewById(R.id.ciudadUser_miPerfilActivity);
        hospital = (TextView) findViewById(R.id.hospital_miPerfilActivity);
        hospitalUser = (TextView) findViewById(R.id.hospitalUser_miPerfilActivity);
        enfermedad = (TextView) findViewById(R.id.enfermedad_miPerfilActivity);
        enfermedadUser = (TextView) findViewById(R.id.enfermedadUser_miPerfilActivity);
        infoUser = (TextView) findViewById(R.id.sobreMiUser_miPerfilActivity);
        info = (TextView) findViewById(R.id.sobreMi_miPerfilActivity);
        email = (TextView) findViewById(R.id.email_miPerfilActivity);
        emailUser = (TextView) findViewById(R.id.emailUser_miPerfilActivity);
        tlf = (TextView) findViewById(R.id.tlf_miPerfilActivity);
        tlfUser = (TextView) findViewById(R.id.telefonoUser_miPerfilActivity);
        espacioEnBlanco4 = (TextView) findViewById(R.id.espacioEnBlanco4_miPerfilActivity);

        extras = getIntent().getExtras();

        if (extras != null) {

            nom = extras.getString("nombre", "");
            ape = extras.getString("apellidos", "");
            ciu = extras.getString("ciudad", "");
            hos = extras.getString("hospital", "");
            enf = extras.getString("enfermedad", "");
            in = extras.getString("descripcion", "");
            em = extras.getString("email", "");
            tl = extras.getString("telefono", "");

            nombreUser.setText(nom);
            apellidosUser.setText(ape);
            ciudadUser.setText(ciu);
            hospitalUser.setText(hos);
            enfermedadUser.setText(enf);
            infoUser.setText(in);
            emailUser.setText(em);
            tlfUser.setText(tl);
        }
    }
}