package com.example.proyecto_grupo_2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.example.proyecto_grupo_2.databinding.ActivityUserMainBinding;
import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

public class UserMainActivity extends AppCompatActivity {

    private ActivityUserMainBinding bind;
    protected TextView nombreUser;
    //protected TextView apellidosUser;
    protected Bundle extras;
    protected String nombre="";
    protected String apellidos="";
    protected String ciudad="";
    protected String hospital="";
    protected String enfermedad="";
    protected String descripcion="";
    protected String email="";
    protected String telefono="";
    protected int idUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        bind = ActivityUserMainBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_content_layout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        init();

        extras = getIntent().getExtras();

        if (extras != null) {
            idUsuario = extras.getInt("id_usuario", 0);
            nombre = extras.getString("nombre", "");
            apellidos = extras.getString("apellidos", "");
            ciudad = extras.getString("ciudad", "");
            hospital = extras.getString("hospital", "");
            enfermedad = extras.getString("enfermedad", "");
            descripcion = extras.getString("descripcion", "");
            email = extras.getString("email", "");
            telefono = extras.getString("telefono", "");
        }

        bind.navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                boolean fragmentTransaction = false;
                Fragment fragment = null;

                int itemID = menuItem.getItemId();

                if (itemID == R.id.nav_home){

                    Intent intent = new Intent(UserMainActivity.this, MiPerfilActivity.class);



                    intent.putExtra("nombre", nombre);
                    intent.putExtra("apellidos", apellidos);
                    intent.putExtra("ciudad", ciudad);
                    intent.putExtra("hospital", hospital);
                    intent.putExtra("enfermedad", enfermedad);
                    intent.putExtra("descripcion", descripcion);
                    intent.putExtra("email", email);
                    intent.putExtra("telefono", telefono);

                    startActivity(intent);

                } else if (itemID == R.id.nav_fetch){
                    Intent intent = new Intent(UserMainActivity.this, BuscarActivity.class);
                    intent.putExtra("id_usuario", idUsuario);
                    intent.putExtra("ciudad", ciudad);
                    intent.putExtra("enfermedad", enfermedad);
                    startActivity(intent);

                } else if (itemID == R.id.nav_contacts){ //Próximamente se ejecutará.
                    //Intent intent = new Intent(UserMainActivity.this, ContactosActivity.class);
                    //startActivity(intent);

                } else if (itemID == R.id.nav_share){
                    Intent intent = new Intent(UserMainActivity.this, SobreNosotrosActivity.class);
                    startActivity(intent);

                } else if (itemID == R.id.nav_web) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("https://acompaname.gitbook.io/acompaname-manual-de-usuario"));
                    startActivity(intent);

                } else if (itemID == R.id.nav_close) {
                    Intent intent = new Intent(UserMainActivity.this, LoginActivity.class);
                    startActivity(intent);
                }

                if (fragmentTransaction){

                    for (int i=0;i<getSupportFragmentManager().getBackStackEntryCount();i++){
                        getSupportFragmentManager().popBackStack();
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.content_fragment,fragment).commit();
                    menuItem.setCheckable(true);
                    Objects.requireNonNull(getSupportActionBar()).setTitle(menuItem.getTitle());
                    bind.main.closeDrawers();
                }

                return true;
            }
        });

        bind.navView.setCheckedItem(R.id.nav_home);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Inicio");

        OnBackPressedCallback onBackPressedCallback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {

                int entries = getSupportFragmentManager().getBackStackEntryCount();

                if (entries > 0) {
                    getSupportFragmentManager().popBackStack();
                } else if (entries == 0 && bind.main.isDrawerOpen(GravityCompat.START)) {
                    bind.main.closeDrawers();
                } else {
                    bind.main.openDrawer(GravityCompat.START);
                }
            }
        };

        nombreUser = (TextView) findViewById(R.id.textViewNombre_userMain);



        nombreUser.setText(getString(R.string.bienvenida, nombre));

        getOnBackPressedDispatcher().addCallback(onBackPressedCallback);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind = null;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            bind.main.openDrawer(GravityCompat.START);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void init(){
        setToolbar();
    }

    private void setToolbar(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }
}