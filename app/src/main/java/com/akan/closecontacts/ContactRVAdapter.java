package com.akan.closecontacts;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ContactRVAdapter extends RecyclerView.Adapter<ContactRVAdapter.ViewHolder> {
    private ArrayList<LoginModal> contactModalArrayList;
    private Context context;

    public ContactRVAdapter(ArrayList<LoginModal>contactModalArrayList,Context context){
        this.contactModalArrayList = contactModalArrayList;
        this.context = context;
    }
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_rv_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LoginModal modal = contactModalArrayList.get(position);
        holder.contactNameTV.setText(modal.getUserName());
        holder.PhoneNumberTV.setText(modal.getPhoneNumber());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // on below line we are calling an intent.
                Intent i = new Intent(context, Update_contact.class);

                // below we are passing all our values.
                i.putExtra("name", modal.getUserName());
                i.putExtra("phoneNo", modal.getPhoneNumber());

                // starting our activity.
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return contactModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView contactNameTV, PhoneNumberTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            contactNameTV = itemView.findViewById(R.id.textView6);
            PhoneNumberTV = itemView.findViewById(R.id.textView7);
        }
    }

}
