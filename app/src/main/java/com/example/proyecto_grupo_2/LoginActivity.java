package com.example.proyecto_grupo_2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

public class LoginActivity extends AppCompatActivity {

    protected LinearLayout layoutLogin;
    protected ImageView imgLogin;
    protected EditText editTextUsuario;
    protected EditText editTextPassword;
    protected Button btnIniciarSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_MiPerfilActivity), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        layoutLogin = (LinearLayout) findViewById(R.id.layout_login);
        imgLogin = (ImageView) findViewById(R.id.img_login);
        editTextUsuario = (EditText) findViewById(R.id.editTextUsuario_login);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword_login);
        btnIniciarSesion = (Button) findViewById(R.id.btnIniciarSesion_login);

        //BTN INICIAR SESION


        btnIniciarSesion.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //VALIDACIONES ANTES DE CONECTAR CON EL SERVIDOR
                if (!validateFields()){
                    return;
                }

                //CONEXIÓN A LA BBDD PARA COMPROBAR SI EL USUARIO Y LA CONTRASEÑA SON CORRECTOS

                final String correoUsuario = editTextUsuario.getText().toString();
                final String passwordUsuario = editTextPassword.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("RESPUESTA_SERVIDOR", response);

                        try {

                            String jsonString = response;
                            if (response.contains("{")) {
                                jsonString = response.substring(response.indexOf("{"));
                            }
                            JSONObject jsonResponse = new JSONObject(jsonString);
                            boolean success = jsonResponse.getBoolean("success");

                            if(success){
                                int id_usuario = jsonResponse.optInt("id_usuario");
                                String nombre = jsonResponse.optString("nombre", "");
                                String apellidos = jsonResponse.optString("apellidos", "");
                                String ciudad = jsonResponse.optString("ciudad", "");
                                String hospital = jsonResponse.optString("hospital", "");
                                String enfermedad = jsonResponse.optString("enfermedad", "");
                                String descripcion = jsonResponse.optString("descripcion", "");
                                String email = jsonResponse.optString("email", "");
                                String telefono = jsonResponse.optString("telefono", "");
                                //String pw = jsonResponse.optString("pw", "");

                                Intent pasarPantalla = new Intent(LoginActivity.this, UserMainActivity.class);
                                pasarPantalla.putExtra("id_usuario",id_usuario);
                                pasarPantalla.putExtra("nombre",nombre);
                                pasarPantalla.putExtra("apellidos",apellidos);
                                pasarPantalla.putExtra("ciudad",ciudad);
                                pasarPantalla.putExtra("hospital",hospital);
                                pasarPantalla.putExtra("enfermedad",enfermedad);
                                pasarPantalla.putExtra("descripcion",descripcion);
                                pasarPantalla.putExtra("email",email);
                                pasarPantalla.putExtra("telefono",telefono);
                                //pasarPantalla.putExtra("pw",pw);
                                finish();
                                startActivity(pasarPantalla);

                            }else{

                                Toast.makeText(LoginActivity.this, getString(R.string.toastErrorLoginUsuario), Toast.LENGTH_SHORT).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };

                LoginRequest loginRequest = new LoginRequest(correoUsuario,passwordUsuario,responseListener);

                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                queue.add(loginRequest);

            }
        });

    }

    //Valida que el email tenga un formato correcto
    private boolean isValidEmail(String email){
        return email !=null && !email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    //Valida todos los campos del formulario
    private boolean validateFields(){
        String correo = editTextUsuario.getText().toString().trim();
        String pw = editTextPassword.getText().toString().trim();

        if(correo.isEmpty()){
            Toast.makeText(this, getString(R.string.introduce_email), Toast.LENGTH_SHORT).show();
            editTextUsuario.requestFocus();
            return false;
        }
        if (!isValidEmail(correo)){
            Toast.makeText(this, getString(R.string.formato_emailErroneo), Toast.LENGTH_SHORT).show();
            editTextUsuario.requestFocus();
            return false;
        }
        if (pw.isEmpty()){
            Toast.makeText(this, getString(R.string.introduce_contrasena), Toast.LENGTH_SHORT).show();
            editTextPassword.requestFocus();
            return false;
        }
        return true;

    }
}