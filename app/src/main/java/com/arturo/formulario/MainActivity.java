package com.arturo.formulario;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText nombre,telefono;
    RadioButton rb;
    RadioGroup rg;
    Button guardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nombre=findViewById(R.id.nombre);
        telefono=findViewById(R.id.telefono);
        rg=findViewById(R.id.rg);
        guardar=findViewById(R.id.guardar);

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if(rg.getCheckedRadioButtonId()==-1 || nombre.length()<1 || telefono.length()<1) {
                    Toast.makeText(getApplicationContext(),"Complete todos los campos",Toast.LENGTH_LONG).show();

                }else {

                    Intent intent=new Intent(MainActivity.this,ListadoActivity.class);
                    intent.putExtra("usuario",nombre.getText().toString());
                    startActivity(intent);
                }
            }
        });

    }
}
