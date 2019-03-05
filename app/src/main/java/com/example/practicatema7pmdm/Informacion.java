package com.example.practicatema7pmdm;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuBuilder;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.practicatema7pmdm.Logic.LogicLugar;

public class Informacion extends AppCompatActivity {

    TextView txt1, txt2, txt3, txt4, txt5;
    RatingBar rb;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion);
        txt1 = findViewById(R.id.textView11);
        txt2 = findViewById(R.id.textView12);
        txt3 = findViewById(R.id.textView13);
        txt4 = findViewById(R.id.textView14);
        txt5 = findViewById(R.id.textView17);
        rb = findViewById(R.id.ratingBar3);
        //Pondremos SALIDAINFORMACION=1 PARA IDENTIFICAR EN QUE MOMENTO HEMOS ENTRADO POR INFORMACIÓN EN VEZ DE NUEVO.
        //App.SALIDAINFORMACION=1;
        txt1.setText(App.lugarActivo.getNombre());
        txt2.setText(App.categoriaSeleccionada);
        txt3.setText(App.lugarActivo.getLongitud().toString());
        txt4.setText(App.lugarActivo.getLatitud().toString());
        txt5.setText(App.lugarActivo.getComentarios());
        rb.setRating(App.lugarActivo.getValoracion());
    }
    @SuppressLint("RestrictedApi")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuinformacion, menu);
        MenuBuilder m = (MenuBuilder) menu;
        m.setOptionalIconsVisible(true);
        return true;
    }
    @SuppressLint("NewApi")
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        return false;
    }
}
