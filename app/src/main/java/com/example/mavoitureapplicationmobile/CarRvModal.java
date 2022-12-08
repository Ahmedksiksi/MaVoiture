package com.example.mavoitureapplicationmobile;

import android.os.Parcel;
import android.os.Parcelable;


    public class CarRvModal {
        private String carModel;
        private String carDescription;
        private String carPrice;
        private String bestSuitedFor;
        private String carImg;
        public CarRvModal() {

        }
        public CarRvModal(String carModel, String carDescription, String carPrice, String bestSuitedFor, String carImg) {
            this.carModel = carModel;
            this.carDescription = carDescription;
            this.carPrice = carPrice;
            this.bestSuitedFor = bestSuitedFor;
            this.carImg = carImg;
        }

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
    }