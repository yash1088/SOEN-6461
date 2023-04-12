package com.concorida.tvm.entity;

public class MetroCard {
    String metroCardId;
    String bindingPhoneNumber;
    Double balance;

    public MetroCard(String metroCardId, String bindingPhoneNumber, Double balance) {
        this.metroCardId = metroCardId;
        this.bindingPhoneNumber = bindingPhoneNumber;
        this.balance = balance;
    }

    public MetroCard() {
    }

    public String getMetroCardId() {
        return metroCardId;
    }

    public void setMetroCardId(String metroCardId) {
        this.metroCardId = metroCardId;
    }

    public String getBindingPhoneNumber() {
        return bindingPhoneNumber;
    }

    public void setBindingPhoneNumber(String bindingPhoneNumber) {
        this.bindingPhoneNumber = bindingPhoneNumber;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
