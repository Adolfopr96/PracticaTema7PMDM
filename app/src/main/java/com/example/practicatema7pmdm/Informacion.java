package com.example.practicatema7pmdm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Informacion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion);
        //Pondremos SALIDAINFORMACION=1 PARA IDENTIFICAR EN QUE MOMENTO HEMOS ENTRADO POR INFORMACIÓN EN VEZ DE NUEVO.
        //App.SALIDAINFORMACION=1;
    }
}
