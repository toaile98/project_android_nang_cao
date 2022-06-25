package com.example.hctpfpoly.fragment;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hctpfpoly.R;
import com.example.hctpfpoly.adapter.ph13373_Adapter_lop;
import com.example.hctpfpoly.dao.ph13373_LopDAO;
import com.example.hctpfpoly.model.ph13373_Lop;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class ph13373_Fragment_lop extends Fragment {


    List<ph13373_Lop> _lst;

    ph13373_LopDAO ph13373_lopDAO;
    ph13373_Adapter_lop ph13373_adapter_lop;
    RecyclerView recyclerView;
    FloatingActionButton btn;

    public ph13373_Fragment_lop() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_ph13373__lop, container, false);
        recyclerView=view.findViewById(R.id.ryc_ds_lop);
        btn=view.findViewById(R.id.btn_frg_dsLop_addLop);
        _lst =new ArrayList<>();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        ph13373_lopDAO = new ph13373_LopDAO(getContext());
        _lst=ph13373_lopDAO.get_lstLop();
        ph13373_adapter_lop = new ph13373_Adapter_lop(getContext(),_lst);


        recyclerView.setAdapter(ph13373_adapter_lop);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull  View view, @Nullable Bundle savedInstanceState) {

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddLop();
            }
        });
    }
    private void AddLop(){

        Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_add_lop);
        Button btn_huy,btn_ok;
        EditText txt_ma,txt_ten;
        txt_ma=dialog.findViewById(R.id.txt_dialog_addLop_malop);
        txt_ten=dialog.findViewById(R.id.txt_dialog_addLop_tenlop);
        btn_huy=dialog.findViewById(R.id.btn_dialog_addlop_huy);
        btn_ok=dialog.findViewById(R.id.btn_dialog_addlop_ok);
        btn_huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txt_ma.getText().toString().isEmpty()||txt_ten.getText().toString().isEmpty()){
                    Toast.makeText(getContext(),"Hãy nhập đầy đủ thông tin!",Toast.LENGTH_SHORT).show();
                    return;
                }
                ph13373_Lop lop = new ph13373_Lop();
                lop.setMaLop(txt_ma.getText().toString());
                lop.setTenLop(txt_ten.getText().toString());

                if (ph13373_lopDAO.insert(lop)<0){
                    Toast.makeText(getContext(),"Thêm thất bại!",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getContext(),"Thêm thành công!",Toast.LENGTH_SHORT).show();
                    ph13373_adapter_lop = new ph13373_Adapter_lop(getContext(),ph13373_lopDAO.get_lstLop());
                    recyclerView.setAdapter(ph13373_adapter_lop);
                    dialog.dismiss();

                }
            }
        });

        dialog.show();
    }
}