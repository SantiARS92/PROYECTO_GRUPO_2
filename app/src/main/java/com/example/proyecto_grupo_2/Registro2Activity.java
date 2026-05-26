package com.example.proyecto_grupo_2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
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

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Registro2Activity extends AppCompatActivity {

    protected TextView cabeceraRegistro2;
    protected LinearLayout layoutRegistro2;
    protected EditText emailRegistro2;
    protected EditText tlfRegistro2;
    protected EditText passwordRegistro2;
    protected EditText confirmarPWRegistro2;
    protected Button btnRegistrarseRegistro2;

    protected Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registro2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_MiPerfilActivity), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        cabeceraRegistro2 = (TextView) findViewById(R.id.textViewCabecera_registro2);
        layoutRegistro2 = (LinearLayout) findViewById(R.id.linearLayout_registro2);
        emailRegistro2 = (EditText) findViewById(R.id.editTextMail_registro2);
        tlfRegistro2 = (EditText) findViewById(R.id.editTextTelefono_registro2);
        passwordRegistro2 = (EditText) findViewById(R.id.editTextPassword_registro2);
        confirmarPWRegistro2 = (EditText) findViewById(R.id.editTextConfirmarPassword_registro2);
        btnRegistrarseRegistro2 = (Button) findViewById(R.id.btnRegistrarse_registro2);
        extras = getIntent().getExtras();

        btnRegistrarseRegistro2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Validación antes de registrar
                if (!validateFields()) {
                    return;
                }

                //AL PULSAR EL BOTON SE DEBE DE HACER LA INSERCIÓN EN LA BBDD Y SI ESTA HA SIDO CORRECTA PASAR A LA SIGUIENTE PANTALLA

                final String nombre=extras.getString("nombre");
                final String apellidos=extras.getString("apellidos");
                final String ciudad=extras.getString("ciudad");
                final String hospital=extras.getString("hospital");
                final String enfermedad=extras.getString("enfermedad");
                final String descripcion=extras.getString("descripcion");
                final String email=emailRegistro2.getText().toString();
                final String telefono=tlfRegistro2.getText().toString();
                final String pw=confirmarPWRegistro2.getText().toString();

                Response.Listener<String> respListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Registro2Activity", "Response: " + response);
                        try {
                            String jsonString = response;
                            if (response.contains("{")) {
                                jsonString = response.substring(response.indexOf("{"));
                            }
                            JSONObject jsonResp = new JSONObject(jsonString);
                            //RESPUESTA QUE DA AL HACER LA INSERCION EN LA BBDD
                            boolean success = jsonResp.getBoolean("success");
                            if(success){    //SI LA INSERCIÓN HA SIDO EXISTOSA SE PASARÁ A LA PANTALLA FINAL DEL REGISTRO

                                Intent pasarPantalla = new Intent(Registro2Activity.this, RegistroFinActivity.class);
                                finish();
                                startActivity(pasarPantalla);

                            }else {

                                Toast.makeText(Registro2Activity.this, getString(R.string.toastErrorCrearUsuario), Toast.LENGTH_SHORT).show();

                            }

                        } catch (JSONException e) {
                            Log.e("Registro2Activity", "Error parsing JSON: " + e.getMessage());
                            Toast.makeText(Registro2Activity.this, "Error de servidor: " + response, Toast.LENGTH_LONG).show();
                        }

                    }
                };

                RegisterRequest rr = new RegisterRequest(nombre,apellidos,ciudad,hospital,enfermedad,descripcion,email,telefono,pw,respListener);
                RequestQueue queue = Volley.newRequestQueue(Registro2Activity.this);
                queue.add(rr);

            }
        });
    }

    //Métodos de validación
    //Validamos que el email tenga un formato correcto
    private boolean isValidEmail (String email) {
        return email != null && !email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }


    //Validamos todos los campos del formulario de registro
    private boolean validateFields(){
        String email = emailRegistro2.getText().toString().trim();
        String telefono = tlfRegistro2.getText().toString().trim();
        String pw = passwordRegistro2.getText().toString().trim();
        String confirmarPW = confirmarPWRegistro2.getText().toString().trim();

        if (email.isEmpty()) {
            Toast.makeText(this, getString(R.string.introduce_email), Toast.LENGTH_SHORT).show();
            emailRegistro2.requestFocus();
            return false;
        }
        if (!isValidEmail(email)) {
            Toast.makeText(this, getString(R.string.formato_emailErroneo), Toast.LENGTH_SHORT).show();
            emailRegistro2.requestFocus();
            return false;
        }
        if (telefono.isEmpty()) {
            Toast.makeText(this, getString(R.string.introduce_telefono), Toast.LENGTH_SHORT).show();
            tlfRegistro2.requestFocus();
            return false;
        }

        if (pw.isEmpty()) {
            Toast.makeText(this, getString(R.string.introduce_contrasena), Toast.LENGTH_SHORT).show();
            passwordRegistro2.requestFocus();
            return false;
        }


        if (confirmarPW.isEmpty()) {
            Toast.makeText(this, getString(R.string.confirma_contrasena), Toast.LENGTH_SHORT).show();
            confirmarPWRegistro2.requestFocus();
            return false;
        }

        if (!pw.equals(confirmarPW)) {
            Toast.makeText(this, getString(R.string.formato_contrasenaErroneo), Toast.LENGTH_SHORT).show();
            confirmarPWRegistro2.requestFocus();
            return false;
        }

        return true;
    }
    }
