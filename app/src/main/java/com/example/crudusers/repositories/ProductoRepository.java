package com.example.crudusers.repositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.crudusers.classes.Producto;
import com.example.crudusers.singletons.Database;

public class ProductoRepository {

    Database db;

    public ProductoRepository(Context context){
        db = new Database(context);
    }

    public boolean registrar(Producto producto){

        ContentValues registro = new ContentValues();
        registro.put("codigo", producto.getCodigo());
        registro.put("descripcion", producto.getDescripcion());
        registro.put("precio", producto.getPrecio());

        db.open();
        db.sqLiteDB.insert("articulos", null, registro);
        db.close();

        return true;
    }

    public Producto buscar(String codigo){

        final int DESCRIPCION = 0;
        final int PRECIO = 1;

        Producto producto = new Producto();

        db.open();
        Cursor fila = db.sqLiteDB.rawQuery("SELECT descripcion, precio FROM articulos WHERE codigo =" + codigo, null);

        if(fila.moveToFirst())
        {
            producto.setDescripcion(fila.getString(DESCRIPCION));
            producto.setPrecio(fila.getString(PRECIO));
            db.close();
            return producto;
        }
        else{
            db.close();
            return null;
        }
    }

    public boolean eliminar(String codigo){

        db.open();
        int cantidad =  db.sqLiteDB.delete("articulos", "codigo=" +codigo, null);
        db.close();

        if(cantidad == 1){
            return true;
        }else{
            return false;
        }

    }

    public boolean modificar(Producto producto){

        ContentValues registro = new ContentValues();

        registro.put("codigo", producto.getCodigo());
        registro.put("descripcion", producto.getDescripcion());
        registro.put("precio", producto.getPrecio());

        db.open();
        int cantidad = db.sqLiteDB.update("articulos", registro, "codigo="+ producto.getCodigo(), null);
        db.close();

        if(cantidad == 1){
            return true;
        }else{
            return false;
        }

    }
}