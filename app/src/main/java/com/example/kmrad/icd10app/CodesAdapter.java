package com.example.kmrad.icd10app;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kmrad on 17.04.2018.
 */

public class CodesAdapter extends RecyclerView.Adapter<CodesAdapter.ViewHolder>{

    ArrayList<Codes> list = new ArrayList<>();

    CodesAdapter(ArrayList<Codes> kody) {
        this.list = kody;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.icd_item, null);
        return new ViewHolder(itemLayoutView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setName(list.get(position).getName());
        holder.setCode(list.get(position).getCode());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txt_view_nazwa)
        TextView diagnosis;

        @BindView(R.id.txt_view_icd)
        TextView code;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        private void setName(String name) {
            diagnosis.setText(name);
        }
        private void setCode(String codeNumber) {code.setText(codeNumber);
        }
    }

}
