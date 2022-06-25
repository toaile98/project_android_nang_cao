package com.example.hctpfpoly.adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hctpfpoly.R;
import com.example.hctpfpoly.dao.ph13373_LopDAO;
import com.example.hctpfpoly.dao.ph13373_SinhVienDAO;
import com.example.hctpfpoly.model.ph13373_SinhVien;

import java.util.List;

public class ph13373_Adapter_sv extends RecyclerView.Adapter<ph13373_Adapter_sv.SinhVien_ViewHolder> {

    private Context context;
    List<ph13373_SinhVien> _lst;

    public ph13373_Adapter_sv(Context context, List<ph13373_SinhVien> _lst) {
        this.context = context;
        this._lst = _lst;
    }

    public SinhVien_ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sv,parent,false);

        return new SinhVien_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder( ph13373_Adapter_sv.SinhVien_ViewHolder holder, int position) {

        ph13373_SinhVien ph13373_sinhVien = _lst.get(position);
        holder.txtItemSVMasv.setText("Mã sinh viên:"+ ph13373_sinhVien.getMaSV());
        holder.txtItemSVTenSV.setText("Tên sinh viên:"+ph13373_sinhVien.getTenSV());
        holder.txtItemSVMalop.setText("Mã lớp:"+ph13373_sinhVien.getMaLop());
        holder.txtItemSVNagySinh.setText("Năm sinh:"+ph13373_sinhVien.getNgaySinh());
        holder.txtItemSVDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog= new Dialog(v.getContext());
                dialog.setContentView(R.layout.dialog_delete_lop);
                Button btn_huy,btn_ok;
                btn_huy=dialog.findViewById(R.id.btn_dialog_xoalop_huy);
                btn_ok=dialog.findViewById(R.id.btn_dialog_xoalop_dongy);
                btn_huy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                btn_ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ph13373_SinhVienDAO sinhVienDAO = new ph13373_SinhVienDAO(dialog.getContext());
                        if (sinhVienDAO.delete(ph13373_sinhVien.getMaSV())<0){
                            Toast.makeText(context,"Xóa thất bại!",Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(context,"Xóa thành công!",Toast.LENGTH_SHORT).show();
                            notifyDataSetChanged();
                        }
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        if (_lst.size()!=0){
            return _lst.size();
        }
        return 0;
    }

    public class SinhVien_ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtItemSVMasv;
        public TextView txtItemSVTenSV;
        public TextView txtItemSVMalop;
        public TextView txtItemSVNagySinh;
        public ImageView txtItemSVDelete;
        public SinhVien_ViewHolder(@NonNull View itemView) {
            super(itemView);


            txtItemSVMasv = (TextView) itemView.findViewById(R.id.txt_itemSV_masv);
            txtItemSVTenSV = (TextView) itemView.findViewById(R.id.txt_itemSV_tenSV);
            txtItemSVMalop = (TextView) itemView.findViewById(R.id.txt_itemSV_malop);
            txtItemSVNagySinh = (TextView) itemView.findViewById(R.id.txt_itemSV_nagySinh);
            txtItemSVDelete = (ImageView) itemView.findViewById(R.id.txt_itemSV_delete);

        }
    }
}
