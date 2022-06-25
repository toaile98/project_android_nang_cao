package com.example.hctpfpoly.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.hctpfpoly.database.ph13373_SQLiteHelper_HocTap;
import com.example.hctpfpoly.model.ph13373_SinhVien;

import java.util.ArrayList;
import java.util.List;

public class ph13373_SinhVienDAO {
    private SQLiteDatabase db;

    public ph13373_SinhVienDAO(Context context){
        ph13373_SQLiteHelper_HocTap sqLiteHelper_hocTap = new ph13373_SQLiteHelper_HocTap(context);
        db = sqLiteHelper_hocTap.getWritableDatabase();
    }



    public long insert(ph13373_SinhVien obj){
        ContentValues values = new ContentValues();
        values.put("maSV",obj.getMaSV());
        values.put("maLop",obj.getMaLop());
        values.put("tenSV",obj.getTenSV());
        values.put("ngaySinh",obj.getNgaySinh());

        return db.insert("sinhVien",null,values);
    }
    public long delete(String maSV){
        return db.delete("sinhVien","maSV=?",new String[]{maSV});
    }
    public List<ph13373_SinhVien> get_lstSV(){
        List<ph13373_SinhVien> _lst = new ArrayList<>();
        Cursor cursor = db.query("sinhVien",null,null,null,null,null,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            ph13373_SinhVien sinhVien = new ph13373_SinhVien();
            sinhVien.setMaSV(cursor.getString(0));
            sinhVien.setMaLop(cursor.getString(1));
            sinhVien.setTenSV(cursor.getString(2));
            sinhVien.setNgaySinh(cursor.getString(3));
            _lst.add(sinhVien);
            cursor.moveToNext();
        }
        cursor.close();
        return _lst;
    }

}
