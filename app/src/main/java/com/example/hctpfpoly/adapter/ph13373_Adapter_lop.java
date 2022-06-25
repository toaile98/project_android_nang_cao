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

import androidx.recyclerview.widget.RecyclerView;

import com.example.hctpfpoly.R;
import com.example.hctpfpoly.dao.ph13373_LopDAO;
import com.example.hctpfpoly.model.ph13373_Lop;

import java.util.List;

public class ph13373_Adapter_lop extends RecyclerView.Adapter<ph13373_Adapter_lop.Lop_ViewHolder> {
    private Context context;
    List<ph13373_Lop> _lstLops;

//    private Listener listener;
//    public interface Listener{
//        void onClickSave(View view);
//    }
//
//    public void setListener(Listener listener){
//        this.listener = listener;
//    }

    public ph13373_Adapter_lop(Context context, List<ph13373_Lop> _lstLops) {
        this.context = context;
        this._lstLops = _lstLops;
    }


//    public ph13373_Adapter_lop(Context context) {
//        this.context = context;
//    }
//    public void setData(List<ph13373_Lop> _lst){
//        this._lstLops=_lst;
//        notifyDataSetChanged();
//    }

    @Override
    public Lop_ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lop,parent,false);

        return new Lop_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ph13373_Adapter_lop.Lop_ViewHolder holder, int position) {
        ph13373_Lop lop = _lstLops.get(position);
        if (lop==null){
            return;
        }
        holder.txtItemLopMalop.setText("Mã lớp:"+lop.getMaLop());
        holder.txtItemLopTenlop.setText("Tên lớp:"+lop.getTenLop());
//        holder.txtItemLopDelete.setOnClickListener(view -> {
//            this.listener.onClickSave(view);
//        });
        holder.txtItemLopDelete.setOnClickListener(new View.OnClickListener() {
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
                        try {
                            ph13373_LopDAO lopDAO = new ph13373_LopDAO(dialog.getContext());
                            if (lopDAO.delete(lop.getMaLop())<0){
                                Toast.makeText(context,"Xóa thất bại!",Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(context,"Xóa thành công!",Toast.LENGTH_SHORT).show();
                                notifyDataSetChanged();
                            }
                            dialog.dismiss();
                        }catch (Exception e){
                            Toast.makeText(v.getContext(), "Không thể xóa!",Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    }
                });
                dialog.show();
            }
        });


    }

    @Override
    public int getItemCount() {
        if (_lstLops!=null){
            return _lstLops.size();
        }
        return 0;
    }

    public class Lop_ViewHolder extends RecyclerView.ViewHolder{

        private TextView txtItemLopMalop;
        private TextView txtItemLopTenlop;
        private ImageView txtItemLopDelete;
        public Lop_ViewHolder(View itemView) {
            super(itemView);


            txtItemLopMalop = (TextView)itemView.findViewById(R.id.txt_itemLop_malop);
            txtItemLopTenlop = (TextView) itemView.findViewById(R.id.txt_itemLop_tenlop);
            txtItemLopDelete = (ImageView) itemView.findViewById(R.id.txt_itemLop_delete);

        }
    }

}
