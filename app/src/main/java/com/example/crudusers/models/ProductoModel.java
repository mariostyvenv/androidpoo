package com.example.crudusers.models;

import android.content.Context;
import android.widget.EditText;
import android.widget.Toast;

import com.example.crudusers.classes.Producto;
import com.example.crudusers.repositories.ProductoRepository;

public class ProductoModel {

    ProductoRepository productoRepositorio;
    Producto p;
    Context context;

    public ProductoModel(Context context){

        this.context = context;
        this.productoRepositorio = new ProductoRepository(context);
        this.p = new Producto();

    }

    public void registrar(EditText et_codigo, EditText et_descripcion, EditText et_precio){

        String codigo = et_codigo.getText().toString();
        String descripcion = et_descripcion.getText().toString();
        String precio = et_precio.getText().toString();

        if(!codigo.isEmpty() && !descripcion.isEmpty() && !codigo.isEmpty()) {

            this.p.setCodigo(codigo);
            this.p.setDescripcion(descripcion);
            this.p.setPrecio(precio);

            if(this.productoRepositorio.registrar(p)){
                Toast.makeText(this.context, "Registrado correctamente", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this.context, "Error al registrar", Toast.LENGTH_SHORT).show();
            }

        }else {
            Toast.makeText(this.context, "Debe llenar todos los campos", Toast.LENGTH_SHORT).show();
        }

    }

    public void buscar(EditText et_codigo, EditText et_descripcion, EditText et_precio){

        String codigo = et_codigo.getText().toString();

        if(!codigo.isEmpty()){
            this.p = productoRepositorio.buscar(codigo);
            if(this.p != null){
                et_codigo.setText(this.p.getCodigo());
                et_descripcion.setText(this.p.getDescripcion());
                et_precio.setText(this.p.getPrecio());
            }else{
                Toast.makeText(this.context, "No se encontró", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this.context, "Debe llenar todos los campos", Toast.LENGTH_SHORT).show();
        }
    }

    public void modificar(EditText et_codigo, EditText et_descripcion, EditText et_precio){

        String codigo = et_codigo.getText().toString();
        String descripcion = et_descripcion.getText().toString();
        String precio = et_precio.getText().toString();

        if(!codigo.isEmpty() && !descripcion.isEmpty() && !codigo.isEmpty()) {

            this.p.setCodigo(codigo);
            this.p.setDescripcion(descripcion);
            this.p.setPrecio(precio);

            if(this.productoRepositorio.modificar(p)){
                Toast.makeText(this.context, "Actualizado correctamente", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this.context, "Error al actualizar", Toast.LENGTH_SHORT).show();
            }

        }else {
            Toast.makeText(this.context, "Debe llenar todos los campos", Toast.LENGTH_SHORT).show();
        }
    }

    public void eliminar(EditText et_codigo){

        String codigo = et_codigo.getText().toString();

        if(!codigo.isEmpty()){
            if(this.productoRepositorio.eliminar(codigo)){
                Toast.makeText(this.context, "Eliminado correctamente", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this.context, "Error al eliminar", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this.context, "Debe llenar todos los campos", Toast.LENGTH_SHORT).show();
        }

    }
}
