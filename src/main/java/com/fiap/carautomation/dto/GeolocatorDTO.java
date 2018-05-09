package com.fiap.carautomation.dto;

public class GeolocatorDTO {
    private String userId;
    private String userAddressDestin;


    public String getUserAddressDestin() {
        return userAddressDestin;
    }

    public void setUserAddressDestin(String userAddressDestin) {
        this.userAddressDestin = userAddressDestin;
    }

    private String carId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }
}
