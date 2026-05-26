package com.example.proyecto_grupo_2;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class UsuarioAdapter extends ArrayAdapter<Usuario> {

    private Context context;
    private ArrayList<Usuario> lista;

    public UsuarioAdapter(Context context,
                          ArrayList<Usuario> lista) {

        super(context, 0, lista);

        this.context = context;
        this.lista = lista;
    }

    @NonNull
    @Override
    public View getView(int position,
                        View convertView,
                        @NonNull ViewGroup parent) {

        Log.d("ADAPTER", "Position: " + position);
        Log.d("ADAPTER", "Tamaño lista: " + lista.size());

        if (convertView == null) {

            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.item_usuario,
                            parent,
                            false);
        }

        Usuario usuario = lista.get(position);

        TextView nombre =
                convertView.findViewById(R.id.txtNombre);

        TextView enfermedad =
                convertView.findViewById(R.id.txtEnfermedad);

        TextView descripcion =
                convertView.findViewById(R.id.txtDescripcion);

        nombre.setText(
                usuario.getNombre() + " "
                        + usuario.getApellidos());

        enfermedad.setText(
                usuario.getEnfermedad());

        descripcion.setText(
                usuario.getDescripcion());

        return convertView;
    }
}