package com.example.practicatema7pmdm;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.MenuBuilder;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.practicatema7pmdm.Logic.LogicLugar;

import java.util.List;

public class NuevoEdicion extends AppCompatActivity {
    EditText edit1, edit2, edit3, edit4;
    RatingBar rb;
    Spinner spinner;
    ImageView imagen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_edicion);
        edit1 = findViewById(R.id.editText);
        edit2 = findViewById(R.id.editText2);
        edit3 = findViewById(R.id.editText3);
        edit4 = findViewById(R.id.editText4);
        spinner =findViewById(R.id.spinner2);
        rb = findViewById(R.id.ratingBar2);
        imagen = findViewById(R.id.imageView2);
        List<String> list = App.getListCategorias(this);
        final int listsize = list.size();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, list) {
            @Override
            public int getCount() {
                return(listsize); // Truncate the list
            }
        };
        imagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        //Viene de información
        if(App.SALIDAINFORMACION==1)
        {
            edit1.setText(App.lugarActivo.getNombre());
            spinner.setSelection(App.lugarActivo.getCategoria()-1);
            edit2.setText(App.lugarActivo.getLongitud().toString());
            edit3.setText(App.lugarActivo.getLatitud().toString());
            rb.setRating(App.lugarActivo.getValoracion());
            edit4.setText(App.lugarActivo.getComentarios());
        }
        else if(App.SALIDAINFORMACION==2)
        {

        }
    }
    @SuppressLint("RestrictedApi")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuBuilder m = (MenuBuilder) menu;
        m.setOptionalIconsVisible(true);
        return true;
    }
    @SuppressLint("NewApi")
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(!edit1.getText().toString().isEmpty() && !edit2.getText().toString().isEmpty() && !edit3.toString().isEmpty() && !edit4.toString().isEmpty())
        {
            if(App.SALIDAINFORMACION==1)
            {
                //Hacer aquí update
                App.lugarActivo.setNombre(edit1.getText().toString());
                App.lugarActivo.setLatitud(Float.parseFloat(edit3.getText().toString()));
                App.lugarActivo.setLongitud(Float.parseFloat(edit2.getText().toString()));
                App.lugarActivo.setComentarios(edit4.getText().toString());
                App.lugarActivo.setValoracion(rb.getRating());
                App.lugarActivo.setCategoria(spinner.getSelectedItemPosition()+1);
                LogicLugar.editarLugar(this, App.lugarActivo );
                Toast.makeText(this, getResources().getString(R.string.toast2), Toast.LENGTH_SHORT).show();
                finish();
            }
            else
            {
                App.lugarActivo.setNombre(edit1.getText().toString());
                App.lugarActivo.setLatitud(Float.parseFloat(edit3.getText().toString()));
                App.lugarActivo.setLongitud(Float.parseFloat(edit2.getText().toString()));
                App.lugarActivo.setComentarios(edit4.getText().toString());
                App.lugarActivo.setValoracion(rb.getRating());
                App.lugarActivo.setCategoria(spinner.getSelectedItemPosition()+1);
                LogicLugar.insertarLugar(this, App.lugarActivo );
                Toast.makeText(this, getResources().getString(R.string.toast1), Toast.LENGTH_SHORT).show();
                finish();
            }
        }
        else
        {

        }
        return false;
    }
}
