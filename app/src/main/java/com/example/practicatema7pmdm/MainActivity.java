    package com.example.practicatema7pmdm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.practicatema7pmdm.Logic.LogicLugar;
import com.example.practicatema7pmdm.Model.Lugar;

import java.util.List;

    public class MainActivity extends AppCompatActivity {
        public ListView listView;
        ImageView imagen1;
        Intent i;
        Intent i1;
        Spinner spinner;
        private static List<Lugar> lstProd;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            imagen1 = findViewById(R.id.imageView);
            i = new Intent(this, Mapa.class);
            i1 = new Intent(this, NuevoEdicion.class);
            spinner = findViewById(R.id.spinner);
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.spinner, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
            imagen1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   startActivity(i);
                }
            });
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
                {
                    if(spinner.getSelectedItem().toString() == getResources().getString(R.string.global))
                    {
                        mostrarTodo();
                    }
                    else
                    {
                        mostrarDatosEspecifico();

                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            listView = findViewById(R.id.card_listView);
            listView.addHeaderView(new View(this)); // añade espacio arriba de la primera card
            listView.addFooterView(new View(this)); // añade espacio debajo de la última card
            listView.setOnItemClickListener(
                    new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView parent, View view, int position, long id) {
                            App.lugarActivo = lstProd.get(position - 1);
                            App.accion = App.INFORMACION;
                            App.categoriaSeleccionada = spinner.getSelectedItem().toString();
                            startActivity(new Intent(getApplicationContext(), Informacion.class));
                        }
                    }
            );
        }
        @Override
        protected void onResume()
        {
            super.onResume();
            if(spinner.getSelectedItem().toString() == getResources().getString(R.string.global))
            {

            }
            else
            {
                mostrarDatosEspecifico();
            }
        }

        public void mostrarDatosEspecifico() {
            CardAdapter listadoDeCards = new CardAdapter(getApplicationContext(), R.layout.list_item_card);
            lstProd = LogicLugar.listaLugar1(this, spinner);
            if (lstProd == null) {
                listView.setAdapter(null);
            } else {
                for (Lugar p : lstProd) {
                    listadoDeCards.add(p);
                }
                listView.setAdapter(listadoDeCards);
            }
        }
        public void mostrarTodo()
        {
            CardAdapter listadoDeCards = new CardAdapter(getApplicationContext(), R.layout.list_item_card);
            lstProd = LogicLugar.listaLugar(this);
            if (lstProd == null) {
                listView.setAdapter(null);
            } else {
                for (Lugar p : lstProd) {
                    listadoDeCards.add(p);
                }
                listView.setAdapter(listadoDeCards);
            }
        }
        //Nueva activity
        public void clicNuevo(View view) {
            App.lugarActivo = new Lugar();
            App.accion = App.INSERTAR;
            //Usamos esto para separar ir al edicion/nuevo a través de información
            //App.SALIDAINFORMACION=2;
            startActivity(i1);
        }
}
