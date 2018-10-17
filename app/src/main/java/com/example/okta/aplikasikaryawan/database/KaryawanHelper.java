package com.example.okta.aplikasikaryawan.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class KaryawanHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "KaryawanManager";
    private static final String TABLE_NAME = "forecast";

    private static final String KEY_ID = "id";
    private static final String KEY_NAMA = "nama";
    private static final String KEY_NIK = "nik";
    private static final String KEY_TTL = "ttl";
    private static final String KEY_JENISKELAMIN = "jeniskelamin";
    private static final String KEY_ALAMAT = "alamat";
    private static final String KEY_HOBI = "hobi";

    public KaryawanHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_FORECAST_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_NAMA + " TEXT,"
                + KEY_NIK + " INTEGER,"
                + KEY_TTL + " TEXT,"
                + KEY_JENISKELAMIN + " TEXT,"
                + KEY_ALAMAT + " TEXT,"
                + KEY_HOBI + " TEXT" + ")";
        db.execSQL(CREATE_FORECAST_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        //create table again
        onCreate(db);
    }

    public void addKaryawan(KaryawanDB karyawanDB){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAMA,karyawanDB.getNama());
        values.put(KEY_NIK,karyawanDB.getNik());
        values.put(KEY_TTL,karyawanDB.getTtl());
        values.put(KEY_JENISKELAMIN,karyawanDB.getJeniskelamin());
        values.put(KEY_ALAMAT,karyawanDB.getAlamat());
        values.put(KEY_HOBI,karyawanDB.getHobi());

        db.insert(TABLE_NAME,null,values);
        db.close();

    }

    public List<KaryawanDB> getAllKaryawan(){
        List<KaryawanDB> karyawanDBList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                KaryawanDB karyawanDB = new KaryawanDB();
                karyawanDB.setId(Integer.parseInt(cursor.getString(0)));
                karyawanDB.setNama(cursor.getString(1));
                karyawanDB.setNik(cursor.getInt(2));
                karyawanDB.setTtl(cursor.getString(3));
                karyawanDB.setJeniskelamin(cursor.getString(4));
                karyawanDB.setAlamat(cursor.getString(5));
                karyawanDB.setHobi(cursor.getString(6));
                karyawanDBList.add(karyawanDB);
            } while (cursor.moveToNext());
        }
        return karyawanDBList;
    }
}
