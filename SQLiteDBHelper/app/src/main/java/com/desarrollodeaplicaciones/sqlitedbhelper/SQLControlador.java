package com.desarrollodeaplicaciones.sqlitedbhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Irbing on 13/03/2017.
 */

public class SQLControlador {

    private DBHelper dbHelper;
    private Context ourcontext;
    private SQLiteDatabase database;

    public SQLControlador (Context ourcontext){
        this.ourcontext = ourcontext;
    }

    public SQLControlador abrirBaseDeDatos()throws SQLException {
        dbHelper = new DBHelper(ourcontext);
        database = dbHelper.getWritableDatabase();
        return this;
    }
    public void cerrar(){
        dbHelper.close();
    }

    public void insertarDatos(String name){
        ContentValues cv = new ContentValues();
        cv.put(DBHelper.MIEMBRO_NOMBRE, name);
        database.insert(DBHelper.TABLE_MEMBER, null, cv);
    }
    public void deleteData (long memberID){
        database.delete(DBHelper.TABLE_MEMBER, DBHelper.MIEMBRO_ID+"="+memberID,null);
    }

    public int actualizarDatos(long memberID, String memberName){
        ContentValues cvActualizar = new ContentValues();
        cvActualizar.put(DBHelper.MIEMBRO_NOMBRE, memberName);
        int i = database.update(DBHelper.TABLE_MEMBER, cvActualizar,DBHelper.MIEMBRO_ID + " = " + memberID, null);
        return i;
    }
    public Cursor leerDatos(){
        String[] todasLasColumnas = new String[]{
                DBHelper.MIEMBRO_ID,
                DBHelper.MIEMBRO_NOMBRE
        };
        Cursor c = database.query(DBHelper.TABLE_MEMBER, todasLasColumnas,null,null,null,null,null);
        if (c!=null){
            c.moveToFirst();
        }
        return c;
    }
}
