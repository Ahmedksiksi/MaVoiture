package com.example.mavoitureapplicationmobile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



public class CarRVAdapter extends RecyclerView.Adapter<CarRVAdapter.ViewHolder> {
    // creating variables for our list, context, interface and position.
    private ArrayList<CarRvModal> carRVModalArrayList;
    private Context context;
    private CarClickInterface carClickInterface;
    int lastPos = -1;

    // creating a constructor.
    public CarRVAdapter(ArrayList<CarRvModal> carRVModalArrayList, Context context, CarClickInterface carClickInterface) {
        this.carRVModalArrayList = carRVModalArrayList;
        this.context = context;
        this.carClickInterface = carClickInterface;
    }

    @NonNull
    @Override
    public CarRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // inflating our layout file on below line.
        View view = LayoutInflater.from(context).inflate(R.layout.car_rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarRVAdapter.ViewHolder holder, int position) {
        // setting data to our recycler view item on below line.
        CarRvModal caRVModal = carRVModalArrayList.get(holder.getAdapterPosition());
        holder.carTV.setText(caRVModal.getCarModel());
        holder.carPriceTV.setText("Rs. " + caRVModal.getCarPrice());
        Picasso.get().load(caRVModal.getCarImg()).into(holder.car1);
        // adding animation to recycler view item on below line.
        setAnimation(holder.itemView, position);
        holder.car1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                carClickInterface.onCarClick(holder.getAdapterPosition());
            }
        });
    }

    private void setAnimation(View itemView, int position) {
        if (position > lastPos) {
            // on below line we are setting animation.
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            itemView.setAnimation(animation);
            lastPos = position;
        }
    }

    @Override
    public int getItemCount() {
        return carRVModalArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // creating variable for our image view and text view on below line.
        private ImageView car1;
        private TextView carTV, carPriceTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing all our variables on below line.
            car1 = itemView.findViewById(R.id.idIVCar);
            car1 = itemView.findViewById(R.id.idTVCArName);
            carPriceTV = itemView.findViewById(R.id.idTVCarPrice);
        }
    }

    public interface CarClickInterface {
         void onCarClick(int position);
    }
}
