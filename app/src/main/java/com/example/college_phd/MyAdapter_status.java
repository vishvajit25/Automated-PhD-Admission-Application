package com.example.college_phd;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter_status extends RecyclerView.Adapter
        <MyAdapter_status.MyViewHolder_status> {

    Context context;

    ArrayList<User_status>  list_status;


    public MyAdapter_status(Context context, ArrayList<User_status> list_status) {
        this.context = context;
        this.list_status = list_status;
    }

    @NonNull
    @Override
    public MyViewHolder_status onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_result,parent,false);
        return  new MyViewHolder_status(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder_status holder, int position) {

        User_status user_status = list_status.get(position);
        holder.Name.setText(user_status.getName());
        holder.applicantID.setText(user_status.getAppID());
        holder.status.setText(user_status.getStatus());
        holder.time.setText(user_status.getTime());
        holder.date.setText(user_status.getDate());


    }

    @Override
    public int getItemCount() {
        return list_status.size();
    }

    public static class MyViewHolder_status extends RecyclerView.ViewHolder{

        TextView Name, status,time,date, applicantID;

        public MyViewHolder_status(@NonNull View itemView) {
            super(itemView);

            Name = itemView.findViewById(R.id.name_status);
            applicantID = itemView.findViewById(R.id.appid_status);
            status = itemView.findViewById(R.id.status_result);
            date = itemView.findViewById(R.id.date_result);
            time = itemView.findViewById(R.id.time_result);

        }
    }

}