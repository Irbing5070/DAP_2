package com.desarrollodeaplicaciones.sqlitedbhelper;


import android.support.v7.app.AppCompatActivity;
import android.view.View.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Irbing on 13/03/2017.
 */

public class AgregarMiembro extends AppCompatActivity implements OnClickListener{

    EditText et;
    Button btnAgregar;
    SQLControlador dbconeccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_miembro);

        et = (EditText) findViewById(R.id.et_miembro_id);
        btnAgregar=(Button)findViewById(R.id.btnAgregarId);

        dbconeccion = new SQLControlador(this);
        dbconeccion.abrirBaseDeDatos();
        btnAgregar.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnAgregarId:
                String name=et.getText().toString();
                dbconeccion.insertarDatos(name);
                Intent main = new Intent(AgregarMiembro.this, MainActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(main);
                break;
            default:
                break;
        }
    }
}
