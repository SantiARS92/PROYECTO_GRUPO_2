package com.example.proyecto_grupo_2;

import androidx.annotation.Nullable;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest {

    private static final String LoginRequestUrl = "http://10.0.2.2/acompaname_bbdd/login";
    private Map<String,String> params;

    public LoginRequest(String email, String pw, Response.Listener<String> listener) {

        super(Request.Method.POST, LoginRequestUrl, listener, null);
        params = new HashMap<>();
        params.put("email", email);
        params.put("pw", pw);
    }

    @Nullable
    @Override
    public Map<String, String> getParams() {
        return params;
    }

}
