package com.example.hctpfpoly.fragment;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.hctpfpoly.R;
import com.example.hctpfpoly.adapter.ph13373_Adapter_sv;
import com.example.hctpfpoly.dao.ph13373_LopDAO;
import com.example.hctpfpoly.dao.ph13373_SinhVienDAO;
import com.example.hctpfpoly.model.ph13373_SinhVien;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ph13373_Fragment_sv extends Fragment {


    private RecyclerView rycDsSv;
    private FloatingActionButton btnFrgDsSVAddSV;

    ph13373_LopDAO lopDAO ;
    ph13373_SinhVienDAO sinhVienDAO;
    ph13373_SinhVien sinhVien;

    List<ph13373_SinhVien> _lst;
    private ph13373_Adapter_sv adapter_sv;


    public ph13373_Fragment_sv() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ph13373__sv, container, false);
        rycDsSv = (RecyclerView) view.findViewById(R.id.ryc_ds_sv);
        btnFrgDsSVAddSV = (FloatingActionButton) view.findViewById(R.id.btn_frg_dsSV_addSV);
        lopDAO = new ph13373_LopDAO(getContext());
        sinhVienDAO = new ph13373_SinhVienDAO(getContext());
         _lst=new ArrayList<>();

        RecyclerView.LayoutManager layoutManager =new LinearLayoutManager(getContext());
        rycDsSv.setLayoutManager(layoutManager);

        _lst=sinhVienDAO.get_lstSV();
        setData(_lst);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {


        btnFrgDsSVAddSV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Add_SV();
            }
        });
    }

    private void Add_SV() {
        Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_add_sv);




        EditText tvDialogAddSVMasv1;
        EditText tvDialogAddSVTenSV1;
        Spinner tvDialogAddSVMalop;
        Spinner tvDialogAddSVNamsinh;
        Button btnDialogAddlopHuy;
        Button btnDialogAddlopOk;

        tvDialogAddSVMasv1 = (EditText) dialog.findViewById(R.id.tv_dialog_addSV_masv1);
        tvDialogAddSVTenSV1 = (EditText) dialog.findViewById(R.id.tv_dialog_addSV_tenSV1);
        tvDialogAddSVMalop = (Spinner) dialog.findViewById(R.id.tv_dialog_addSV_malop);
        tvDialogAddSVNamsinh = (Spinner) dialog.findViewById(R.id.tv_dialog_addSV_namsinh);
        btnDialogAddlopHuy = (Button) dialog.findViewById(R.id.btn_dialog_addlop_huy);
        btnDialogAddlopOk = (Button) dialog.findViewById(R.id.btn_dialog_addlop_ok);


        // set _lst LOP
        List<String> _lst_dslop=lopDAO.getMaLop();
        if (_lst_dslop.size()==0){
            List<String> _lstLop1 = new ArrayList<>();
            _lstLop1.add("Chưa có lớp !");
            ArrayAdapter adapter1 = new ArrayAdapter(dialog.getContext(),android.R.layout.simple_spinner_dropdown_item,_lstLop1);
            tvDialogAddSVMalop.setAdapter(adapter1);
            btnDialogAddlopOk.setVisibility(View.GONE);
        }else {
            ArrayAdapter adapter1 = new ArrayAdapter(dialog.getContext(),android.R.layout.simple_spinner_dropdown_item,_lst_dslop);
            tvDialogAddSVMalop.setAdapter(adapter1);
        }

        //set _lst nam sinh
        List<String> _lst_dsNamSinh=new ArrayList<>();
        for (int i = 2021; i >1899 ; i--) {
            _lst_dsNamSinh.add(String.valueOf(i));
        }
        ArrayAdapter adapter2 = new ArrayAdapter(dialog.getContext(), android.R.layout.simple_spinner_dropdown_item,_lst_dsNamSinh);
        tvDialogAddSVNamsinh.setAdapter(adapter2);


        // btn su kien
        btnDialogAddlopHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnDialogAddlopOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tvDialogAddSVMasv1.getText().toString().isEmpty()||
                        tvDialogAddSVTenSV1.getText().toString().isEmpty()){
                    Toast.makeText(getContext(),"Nhập thông tin sinh viên!",Toast.LENGTH_SHORT).show();
                    return;
                }
                sinhVien = new ph13373_SinhVien(tvDialogAddSVMasv1.getText().toString(),tvDialogAddSVMalop.getSelectedItem().toString(),
                                                tvDialogAddSVTenSV1.getText().toString(),tvDialogAddSVNamsinh.getSelectedItem().toString());
                if (sinhVienDAO.insert(sinhVien)<0){
                    Toast.makeText(getContext(),"Thêm thất bại!!",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getContext(),"Thêm thành công!",Toast.LENGTH_SHORT).show();


                    setData(sinhVienDAO.get_lstSV());
                    dialog.dismiss();
                }
            }
        });

        dialog.show();
    }
    private void setData(List<ph13373_SinhVien> _lst){
        this.adapter_sv = new ph13373_Adapter_sv(getContext(),_lst);
        this.rycDsSv.setAdapter(adapter_sv);

    }


}