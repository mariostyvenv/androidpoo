package com.example.crudusers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.crudusers.models.ProductoModel;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText et_codigo, et_descripcion, et_precio;
    private Button btn_registrar, btn_buscar, btn_modificar, btn_eliminar;

    ProductoModel producto = new ProductoModel(this);

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

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.BotonRegistrar:
                producto.registrar(et_codigo, et_descripcion, et_precio);
                break;
            case R.id.BotonBuscar:
                producto.buscar(et_codigo, et_descripcion, et_precio);
                break;
            case R.id.BotonModificar:
                producto.modificar(et_codigo, et_descripcion, et_precio);
                break;
            case R.id.BotonEliminar:
                producto.eliminar(et_codigo);
                break;
        }
    }
}