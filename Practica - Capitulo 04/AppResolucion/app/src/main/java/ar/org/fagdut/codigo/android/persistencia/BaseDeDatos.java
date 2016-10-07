package ar.org.fagdut.codigo.android.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fanky on 6/25/16.
 */
public class BaseDeDatos extends SQLiteOpenHelper {

    public static final String TAG = "DatabaseDAL";

    public static final String DATABASE_NAME = "main.db";
    public static final int CURRENT_DATABASE_VERSION = 1; // current DB Version
    public static final String SQL_CREATE_TABLE = "CREATE TABLE valores (valor TEXT)";

    // instancia de la db
    protected SQLiteDatabase mDatabase;

    public BaseDeDatos(Context context) {
        super(context, DATABASE_NAME, null, CURRENT_DATABASE_VERSION);
    }

    public void open() throws SQLException {
        mDatabase = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(TAG, "Upgrading the database from version " + oldVersion + " to " + newVersion);

        // clear all data by dropping tables.
        db.execSQL("DROP TABLE IF EXISTS valores");

        // recreate tables.
        db.execSQL(SQL_CREATE_TABLE);
    }

    public void guardar(String valor) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("valor", valor);
        // nombre de tabla, columnHack, values
        mDatabase.insert("valores", null,contentValues);
    }

    public List<String> getValores() {
        Cursor cursor = mDatabase.rawQuery("SELECT * FROM valores", null);
        List<String> found = new ArrayList<String>();

        while (cursor.moveToNext()) {
            String valor = cursor.getString(cursor.getColumnIndex("valor"));
            found.add(valor);
        }

        return found;
    }
}