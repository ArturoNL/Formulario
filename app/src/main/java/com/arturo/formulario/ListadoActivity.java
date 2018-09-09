package com.arturo.formulario;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListadoActivity extends AppCompatActivity {

    public static final int REQUEST_PHONE_CALL=1;
    String usuario;
    ListView lista;
    ArrayList usuarios,telefono;
    ArrayAdapter arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);

        lista=findViewById(R.id.lista);

        Bundle bundle=getIntent().getExtras();
        setTitle("Listado de usuarios - "+bundle.get("nombre"));
        usuarios =new ArrayList<String>();
        usuarios.add("Pedro");
        usuarios.add("Juan");
        usuarios.add("María");

        telefono=new ArrayList<String>();
        telefono.add("123456789");
        telefono.add("987654321");
        telefono.add("123131231");

        arrayAdapter=new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,usuarios);
        lista.setAdapter(arrayAdapter);
        registerForContextMenu(lista);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String numero=telefono.get(position).toString();
                Intent intent=new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+numero));
                if(ActivityCompat.checkSelfPermission(ListadoActivity.this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(ListadoActivity.this,new String[]{Manifest.permission.CALL_PHONE},REQUEST_PHONE_CALL);
                 }else{
                    startActivity(intent);
                }

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.agregar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.agregar:
                Toast.makeText(
                        ListadoActivity.this,
                        "Agregando nuevo item",
                        Toast.LENGTH_SHORT).show();
                usuarios.add("Usuario Nuevo");
                arrayAdapter.notifyDataSetChanged();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater=getMenuInflater();
        AdapterView.AdapterContextMenuInfo info=(AdapterView.AdapterContextMenuInfo) menuInfo;
        menu.setHeaderTitle((String) this.usuarios.get(info.position));
        menuInflater.inflate(R.menu.eliminar,menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info=(AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()){
            case R.id.eliminar:
                Toast.makeText(ListadoActivity.this,"Eliminando usuario", Toast.LENGTH_LONG).show();
                usuarios.remove(info.position);
                arrayAdapter.notifyDataSetChanged();
                return true;
            default:
                return super.onContextItemSelected(item);
        }


    }
}
