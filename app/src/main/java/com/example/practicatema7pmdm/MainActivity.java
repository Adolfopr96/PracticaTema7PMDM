    package com.example.practicatema7pmdm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

    public class MainActivity extends AppCompatActivity {
        public ListView listView;
        private static List <Lugar> lstProd;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            Spinner spinner = (Spinner) findViewById(R.id.spinner);
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.spinner, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
            listView = findViewById(R.id.card_listView);
            listView.addHeaderView(new View(this)); // añade espacio arriba de la primera card
            listView.addFooterView(new View(this)); // añade espacio debajo de la última card

            listView.setOnItemClickListener(
                    new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView parent, View view, int position, long id) {
                            App.lugarActivo = lstProd.get(position - 1);
                            App.accion = App.INFORMACION;
                            startActivity(new Intent(getApplicationContext(), InformacionActivity.class));
                        }
                    }
            );
        }

        @Override
        protected void onResume() {
            super.onResume();
            CardAdapter listadoDeCards = new CardAdapter(getApplicationContext(), R.layout.list_item_card);
            lstProd = LogicLugar.listaProductos(this);
            if (lstProd == null) {
                Toast.makeText(this, "La base de datos está vacía.", Toast.LENGTH_LONG).show();
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
            startActivity(new Intent(this, nuevoLugar.class));
        }
}
