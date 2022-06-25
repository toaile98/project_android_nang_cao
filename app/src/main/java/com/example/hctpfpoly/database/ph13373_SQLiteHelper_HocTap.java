package com.example.hctpfpoly.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ph13373_SQLiteHelper_HocTap extends SQLiteOpenHelper {
    public static final String DB_NAME="hoctap.db";
    public static final int VERSION =1;

    public ph13373_SQLiteHelper_HocTap(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String createLop="CREATE TABLE lop (\n" +
                "    maLop  TEXT PRIMARY KEY\n" +
                "                NOT NULL,\n" +
                "    tenLop TEXT NOT NULL\n" +
                ");\n";


        final String createSinhVien = "CREATE TABLE sinhVien (\n" +
                "    maSV     TEXT PRIMARY KEY ,\n" +
                "    maLop    TEXT    REFERENCES lop (mLop) \n" +
                "                     NOT NULL,\n" +
                "    tenSV    TEXT    NOT NULL,\n" +
                "    ngaySinh TEXT    NOT NULL\n" +
                ");\n";

        db.execSQL(createLop);
        db.execSQL(createSinhVien);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS lop");
        db.execSQL("DROP TABLE IF EXISTS sinhVien");

        onCreate(db);
    }
}
