package com.example.proyecto_grupo_2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BuscarActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<Usuario> listaUsuarios;
    UsuarioAdapter adapter;
    private static final String URL = "http://10.0.2.2/acompaname_bbdd/buscarUsuarios";
    private int idUsuario;
    private String ciudadUsuario;
    private String enfermedadUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar);

        idUsuario = getIntent().getIntExtra("id_usuario", 0);
        ciudadUsuario = getIntent().getStringExtra("ciudad");
        enfermedadUsuario = getIntent().getStringExtra("enfermedad");
        Log.d("BUSCAR", "id = " + idUsuario + " ciudad = " + ciudadUsuario + " enfermedad = " +enfermedadUsuario);
        listView = findViewById(R.id.listViewUsuarios);

        listaUsuarios = new ArrayList<>();

        adapter = new UsuarioAdapter(this, listaUsuarios);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {

            Usuario usuario = listaUsuarios.get(position);

            Intent intent = new Intent(BuscarActivity.this, UsuarioBuscado.class);


            intent.putExtra("nombre", usuario.getNombre());
            intent.putExtra("ciudad", usuario.getCiudad());
            intent.putExtra("enfermedad", usuario.getEnfermedad());
            intent.putExtra("email", usuario.getEmail());

            startActivity(intent);
        });
        Log.d("BUSCAR_PARAMS", "id_usuario=" + idUsuario + " | ciudad=" + ciudadUsuario + " | enfermedad=" + enfermedadUsuario);
        Log.d("BUSCAR_PARAMS", "id_usuario=" + idUsuario + " | ciudad=" + ciudadUsuario + " | enfermedad=" + enfermedadUsuario);
        cargarUsuarios();
    }

    private void cargarUsuarios() {

        Log.d("BUSCAR", "Entrando en cargarUsuarios");

        StringRequest request =
                new StringRequest(Request.Method.POST,
                        URL,
                        response -> {

                            Log.d("USUARIOS_JSON", response);

                            try {

                                JSONObject json = new JSONObject(response);

                                boolean success = json.getBoolean("success");

                                Log.d("USUARIOS_SUCCESS", String.valueOf(success));

                                if (success) {

                                    JSONArray usuarios = json.getJSONArray("usuarios");

                                    Log.d("USUARIOS_LENGTH", String.valueOf(usuarios.length()));

                                    listaUsuarios.clear();

                                    for (int i = 0; i < usuarios.length(); i++) {

                                        JSONObject obj = usuarios.getJSONObject(i);

                                        Usuario usuario = new Usuario(

                                                obj.getInt("id_usuario"),
                                                obj.getString("nombre"),
                                                obj.getString("apellidos"),
                                                obj.getString("ciudad"),
                                                obj.getString("enfermedad"),
                                                obj.optString("descripcion", ""),
                                                obj.getString("email"),
                                                obj.optString("telefono", "")
                                        );

                                        listaUsuarios.add(usuario);
                                    }

                                    adapter.notifyDataSetChanged();
                                }

                            } catch (Exception e) {
                                Log.e("BUSCAR_ERROR", "Error parseando JSON", e);
                            }

                        },
                        error -> {
                            Log.e("BUSCAR_ERROR", "Error Volley: " + error.toString());
                            if (error.networkResponse != null) {
                                Log.e("BUSCAR_ERROR", "Status Code: " + error.networkResponse.statusCode);
                            }
                        }) {

                    @Override
                    protected Map<String, String> getParams() {

                        Map<String, String> params = new HashMap<>();
                        params.put("id_usuario", String.valueOf(idUsuario));
                        params.put("ciudad", ciudadUsuario);
                        params.put("enfermedad", enfermedadUsuario);
                        return params;
                    }
                };

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }
}