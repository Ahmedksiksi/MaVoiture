package com.example.mavoitureapplicationmobile;

import android.os.Parcel;
import android.os.Parcelable;


    public class CarRvModal implements Parcelable {
        // creating variables for our different fields.
        private String carModel;
        private String carDescription;
        private String carPrice;
        private String bestSuitedFor;
        private String carImg;
        private String carId;


        public String getCarId() {
            return carId;
        }

        public void setCarId(String carId) {
            this.carId = carId;
        }


        // creating an empty constructor.
        public CarRvModal() {

        }

        protected CarRvModal(Parcel in) {
            carModel = in.readString();
            carId = in.readString();
            carDescription = in.readString();
            carPrice = in.readString();
            bestSuitedFor = in.readString();
            carImg = in.readString();
        }

        public static final Creator<CarRvModal> CREATOR = new Creator<CarRvModal>() {
            @Override
            public CarRvModal createFromParcel(Parcel in) {
                return new CarRvModal(in);
            }

            @Override
            public CarRvModal[] newArray(int size) {
                return new CarRvModal[size];
            }
        };

        // creating getter and setter methods.
        public String getCarModel() {
            return carModel;
        }

        public void setCarModel(String carModel) {
            this.carModel = carModel;
        }

        public String getCarDescription() {
            return carDescription;
        }

        public void setCarDescription(String carDescription) {
            this.carDescription = carDescription;
        }

        public String getCarPrice() {
            return carPrice;
        }

        public void setCarPrice(String carPrice) {
            this.carPrice = carPrice;
        }

        public String getBestSuitedFor() {
            return bestSuitedFor;
        }

        public void setBestSuitedFor(String bestSuitedFor) {
            this.bestSuitedFor = bestSuitedFor;
        }

        public String getCarImg() {
            return carImg;
        }

        public void setCarImg(String carImg) {
            this.carImg = carImg;
        }




        public CarRvModal(String carId, String carModel, String carDescription, String carPrice, String bestSuitedFor, String carImg) {
            this.carModel = carModel;
            this.carId = carId;
            this.carDescription = carDescription;
            this.carPrice = carPrice;
            this.bestSuitedFor = bestSuitedFor;
            this.carImg = carImg;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(carModel);
            dest.writeString(carId);
            dest.writeString(carDescription);
            dest.writeString(carPrice);
            dest.writeString(bestSuitedFor);
            dest.writeString(carImg);
        }
    }