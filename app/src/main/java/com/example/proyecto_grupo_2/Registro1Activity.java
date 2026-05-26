package com.example.proyecto_grupo_2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Registro1Activity extends AppCompatActivity {

    protected TextView cabeceraRegistro1;
    protected LinearLayout layoutRegistro1;
    protected EditText nombreRegistro1;
    protected EditText apellidosRegistro1;
    protected EditText ciudadRegistro1;
    protected EditText hospitalRegistro1;
    protected EditText enfermedadRegistro1;
    protected EditText breveDescripcionRegistro1;
    protected Button btnSiguiente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registro1);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_MiPerfilActivity), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        cabeceraRegistro1 = (TextView) findViewById(R.id.textViewCabecera_Registro1);
        layoutRegistro1 = (LinearLayout) findViewById(R.id.linearLayout_registro2);
        nombreRegistro1 = (EditText) findViewById(R.id.editTextNombre_registro1);
        apellidosRegistro1 = (EditText) findViewById(R.id.editTextApellidos_registro1);
        ciudadRegistro1 = (EditText) findViewById(R.id.editTextCiudad_registro1);
        hospitalRegistro1 = (EditText) findViewById(R.id.editTextHospital_registro1);
        enfermedadRegistro1 = (EditText) findViewById(R.id.editTextEnfermedad_registro1);
        breveDescripcionRegistro1 = findViewById(R.id.editTextBreveDescripcion_registro1);
        btnSiguiente = (Button) findViewById(R.id.btnSiguiente_registro1);


        //PASAR A LA SIGUIENTE PANTALLA DEL REGISTRO
        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Validar antes de continuar
                if (!validateFields()) {
                    return;
                }

                Intent pasarPantalla = new Intent(Registro1Activity.this, Registro2Activity.class);
                pasarPantalla.putExtra("nombre", nombreRegistro1.getText().toString());
                pasarPantalla.putExtra("apellidos", apellidosRegistro1.getText().toString());
                pasarPantalla.putExtra("ciudad", ciudadRegistro1.getText().toString());
                pasarPantalla.putExtra("enfermedad", enfermedadRegistro1.getText().toString());
                pasarPantalla.putExtra("hospital", hospitalRegistro1.getText().toString());
                pasarPantalla.putExtra("descripcion", breveDescripcionRegistro1.getText().toString());
                finish();
                startActivity(pasarPantalla);

            }
        });

    }

    //Métodos de validación
    //Comprobamos que el campo no esté vacío
    private boolean isFieldNotEmpty(EditText editText) {
        return !editText.getText().toString().trim().isEmpty();
    }

    //Valida todos los campos del formulario de registro
    private boolean validateFields() {
        if (!isFieldNotEmpty(nombreRegistro1)) {
            Toast.makeText(this, getString(R.string.introduce_nombre), Toast.LENGTH_SHORT).show();
            nombreRegistro1.requestFocus();
            return false;
        }
        if (!isFieldNotEmpty(apellidosRegistro1)) {
            Toast.makeText(this, getString(R.string.introduce_apellidos), Toast.LENGTH_SHORT).show();
            apellidosRegistro1.requestFocus();
            return false;
        }
        if (!isFieldNotEmpty(ciudadRegistro1)) {
            Toast.makeText(this, getString(R.string.introduce_ciudad), Toast.LENGTH_SHORT).show();
            ciudadRegistro1.requestFocus();
            return false;
        }
        if (!isFieldNotEmpty(hospitalRegistro1)) {
            Toast.makeText(this, getString(R.string.introduce_hospital), Toast.LENGTH_SHORT).show();
            hospitalRegistro1.requestFocus();
            return false;
        }
        if (!isFieldNotEmpty(enfermedadRegistro1)) {
            Toast.makeText(this, getString(R.string.introduce_enfermedad), Toast.LENGTH_SHORT).show();
            enfermedadRegistro1.requestFocus();
            return false;
        }
        if (!isFieldNotEmpty(breveDescripcionRegistro1)) {
            Toast.makeText(this, getString(R.string.introduce_descripcion), Toast.LENGTH_SHORT).show();
            breveDescripcionRegistro1.requestFocus();
            return false;
        }
    return true;
}
}