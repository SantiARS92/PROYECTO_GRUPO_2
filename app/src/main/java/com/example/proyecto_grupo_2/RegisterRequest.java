package com.example.proyecto_grupo_2;

import androidx.annotation.Nullable;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {

    //URL QUE CONECTA CON EL LOCALHOST DE APACHE
    private static final String RegisterRequestUrl = "http://10.0.2.2/acompaname_bbdd/registrar";
    private Map<String,String> params;

    public RegisterRequest(String nombre, String apellidos, String ciudad, String hospital, String enfermedad, String descripcion, String email, String telefono, String pw, Response.Listener<String> listener) {

        super(Method.POST, RegisterRequestUrl, listener, null);
        params = new HashMap<>();
        params.put("nombre", nombre);
        params.put("apellidos", apellidos);
        params.put("ciudad", ciudad);
        params.put("hospital", hospital);
        params.put("enfermedad", enfermedad);
        params.put("descripcion", descripcion);
        params.put("email", email);
        params.put("telefono", telefono);
        params.put("pw", pw);
    }

    @Nullable
    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
