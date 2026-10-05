package com.velez300.appao1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapt extends RecyclerView.Adapter<Adapt.viewHolder>{

    ArrayList<Contact> agenda;

    public Adapt(ArrayList<Contact> agenda) {
        this.agenda = agenda;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View elem = LayoutInflater.from(parent.getContext()).inflate(R.layout.elementocontacto, parent,false);
        return new viewHolder(elem);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
    holder.name.setText(agenda.get(position).getName());
    holder.surname.setText(agenda.get(position).getSurname());
    holder.phone.setText(agenda.get(position).getPhone());
    holder.address.setText(agenda.get(position).getAddress());
    holder.imgPath.setImageResource(agenda.get(position).getIconPath());
    }

    @Override
    public int getItemCount() {
        return agenda.size();
    }


    public class viewHolder extends RecyclerView.ViewHolder{

        TextView name, surname, phone, address;
        ImageView imgPath;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.txtN);
            surname = itemView.findViewById(R.id.txtA);
            phone = itemView.findViewById(R.id.txtTel);
            address = itemView.findViewById(R.id.txtDir);
            imgPath = itemView.findViewById(R.id.imgAvatar);
        }
    }

    public void updateList(ArrayList<Contact> newList) {
        this.agenda = newList;
        notifyDataSetChanged();
    }
}
