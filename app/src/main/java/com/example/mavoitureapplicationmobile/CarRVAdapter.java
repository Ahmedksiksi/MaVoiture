package com.example.mavoitureapplicationmobile;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class CarRVAdapter extends FirebaseRecyclerAdapter<
        CarRvModal, CarRVAdapter.personsViewholder> {
    MainActivity main=new MainActivity();
    public CarRVAdapter(
            @NonNull FirebaseRecyclerOptions<CarRvModal> options)
    {
        super(options);
    }

    @Override
    protected void
    onBindViewHolder(@NonNull personsViewholder holder,
                     int position, @NonNull CarRvModal model)
    {

        holder.carname.setText(model.getCarModel());

        holder.price.setText(model.getCarPrice());

        Glide.with(holder.img.getContext()).load(model.getCarImg()).into(holder.img);


    }

    @NonNull
    @Override
    public personsViewholder
    onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view
                = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.car_rv_item, parent, false);
        return new CarRVAdapter.personsViewholder(view);
    }







    class personsViewholder
            extends RecyclerView.ViewHolder {
        ImageView img;
        TextView  carname, price;
        public personsViewholder(@NonNull View itemView)
        {
            super(itemView);

            img= itemView.findViewById(R.id.idIVCar);
            carname = itemView.findViewById(R.id.idTVCArName);
            price = itemView.findViewById(R.id.idTVCarPrice);

        }
    }
}
