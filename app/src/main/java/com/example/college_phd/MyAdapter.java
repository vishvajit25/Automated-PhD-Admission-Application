package com.example.college_phd;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;

    ArrayList<User> list;


    public MyAdapter(Context context, ArrayList<User> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return  new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        User user = list.get(position);
        holder.Name.setText(user.getName());
        holder.ApplicantID.setText(user.getApplicantID());
        holder.tenthpercentage.setText(user.gettenthpercentage());
        holder.twelfthpercentage.setText(user.gettwelfthpercentage());
        holder.ugpercentage.setText(user.getugpercentage());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView Name, tenthpercentage, twelfthpercentage, ugpercentage, ApplicantID;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            Name = itemView.findViewById(R.id.tvfirstName);
            ApplicantID = itemView.findViewById(R.id.tvappli);
            tenthpercentage = itemView.findViewById(R.id.tvtenth);
            twelfthpercentage = itemView.findViewById(R.id.tvtwelfth);
            ugpercentage = itemView.findViewById(R.id.tvug);

        }
    }

}