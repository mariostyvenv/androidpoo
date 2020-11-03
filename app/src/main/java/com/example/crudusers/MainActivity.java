package com.example.crudusers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.crudusers.helpers.AdminSQLiteOpenHelper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText et_codigo, et_descripcion, et_precio;
    private Button btn_registrar, btn_buscar, btn_modificar, btn_eliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_codigo = (EditText)findViewById(R.id.txt_codigo);
        et_descripcion = (EditText)findViewById(R.id.txt_descripcion);
        et_precio = (EditText)findViewById(R.id.txt_precio);

        btn_registrar = (Button)findViewById(R.id.BotonRegistrar);
        btn_buscar = (Button)findViewById(R.id.BotonBuscar);
        btn_modificar = (Button)findViewById(R.id.BotonModificar);
        btn_eliminar = (Button)findViewById(R.id.BotonEliminar);

        btn_registrar.setOnClickListener(this);
        btn_buscar.setOnClickListener(this);
        btn_modificar.setOnClickListener(this);
        btn_eliminar.setOnClickListener(this);
    }

    public void registrar(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "Administracion", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String codigo = et_codigo.getText().toString();
        String descripcion = et_descripcion.getText().toString();
        String precio = et_precio.getText().toString();

        if(!codigo.isEmpty() && !descripcion.isEmpty() && !precio.isEmpty()) {
            ContentValues registro = new ContentValues();
            registro.put("codigo", codigo);
            registro.put("descripcion", descripcion);
            registro.put("precio", precio);

            BaseDeDatos.insert("articulos", null, registro);


            et_codigo.setText("");
            et_descripcion.setText("");
            et_precio.setText("");

            Toast.makeText(this, "Registro insertado con exito", Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(this, "Debe de llenar todos los campos", Toast.LENGTH_SHORT).show();
        }
        BaseDeDatos.close();
    }

    public void buscar(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "Administracion", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        final int DESCRIPCION = 0;
        final int PRECIO = 1;

        String codigo = et_codigo.getText().toString();

        if(!codigo.isEmpty()){
            Cursor fila = BaseDeDatos.rawQuery("SELECT descripcion, precio FROM articulos WHERE codigo ="+codigo, null);

            if(fila.moveToFirst()){
                et_descripcion.setText(fila.getString(DESCRIPCION));
                et_precio.setText(fila.getString(PRECIO));
            }else{
                Toast.makeText(this, "No existe el articulo", Toast.LENGTH_SHORT).show();
            }
            BaseDeDatos.close();

        }else{
            Toast.makeText(this, "Debe de llenar el codigo", Toast.LENGTH_SHORT).show();
        }
    }

    public void eliminar(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "Administracion", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String codigo = et_codigo.getText().toString();

        if(!codigo.isEmpty()){
            int cantidad =  BaseDeDatos.delete("articulos", "codigo=" +codigo, null);

            et_codigo.setText("");
            et_descripcion.setText("");
            et_precio.setText("");

            if(cantidad == 1){
                Toast.makeText(this, "Articulo eliminado exitosamente", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "El articulo no existe", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "Debe introducir el codigo del articulo", Toast.LENGTH_SHORT).show();
        }
        BaseDeDatos.close();
    }

    public void modificar(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "Administracion", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String codigo = et_codigo.getText().toString();
        String descripcion = et_descripcion.getText().toString();
        String precio = et_precio.getText().toString();

        if(!codigo.isEmpty() && !descripcion.isEmpty() && !precio.isEmpty()){
            ContentValues registro = new ContentValues();

            registro.put("codigo", codigo);
            registro.put("descripcion", descripcion);
            registro.put("precio", precio);

            int cantidad = BaseDeDatos.update("articulos", registro, "codigo="+ codigo, null);

            if(cantidad == 1){
                Toast.makeText(this, "Articulo modificado exitosamente", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Articulo no encontrado", Toast.LENGTH_SHORT).show();
            }

        }else{
            Toast.makeText(this, "Debe de llenar todos los campos", Toast.LENGTH_SHORT).show();
        }

        BaseDeDatos.close();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.BotonRegistrar:
                this.registrar(v);
                break;
            case R.id.BotonBuscar:
                this.buscar(v);
                break;
            case R.id.BotonModificar:
                this.modificar(v);
                break;
            case R.id.BotonEliminar:
                this.eliminar(v);
                break;
        }
    }
}