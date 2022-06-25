package com.example.hctpfpoly.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.hctpfpoly.database.ph13373_SQLiteHelper_HocTap;
import com.example.hctpfpoly.model.ph13373_Lop;

import java.util.ArrayList;
import java.util.List;

public class ph13373_LopDAO {
    ph13373_SQLiteHelper_HocTap sqLiteHelper_hocTap;
    SQLiteDatabase db;



    public ph13373_LopDAO(Context context) {
        sqLiteHelper_hocTap = new ph13373_SQLiteHelper_HocTap(context);
        db=sqLiteHelper_hocTap.getWritableDatabase();
        db.setForeignKeyConstraintsEnabled(true);
    }

    public long insert(ph13373_Lop obj){
        ContentValues values = new ContentValues();
        values.put("maLop",obj.getMaLop());
        values.put("tenlop",obj.getTenLop());

        return db.insert("lop",null,values);
    }
    public long delete(String maLop){
        return db.delete("lop","maLop=?",new String[]{maLop});
    }
    public List<ph13373_Lop> get_lstLop(){
        List<ph13373_Lop> _lst = new ArrayList<>();
        Cursor cursor = db.query("lop",null,null,null,null,null,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            ph13373_Lop lop = new ph13373_Lop();
            lop.setMaLop(cursor.getString(0));
            lop.setTenLop(cursor.getString(1));
            _lst.add(lop);
            cursor.moveToNext();
        }
        cursor.close();
        return _lst;
    }
    public List<String> getMaLop(){
        List<String> _lst = new ArrayList<>();
        Cursor cursor = db.query("lop",null,null,null,null,null,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){

            String st =(cursor.getString(1));
            _lst.add(st);

            cursor.moveToNext();
        }
        cursor.close();
        return _lst;
    }
}
