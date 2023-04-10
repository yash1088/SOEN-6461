package com.concorida.tvm.entity;

public class DebitCardPayment extends Payment{
    String lastFourNumberOfCard;

    public DebitCardPayment(String lastFourNumberOfCard) {
        this.lastFourNumberOfCard = lastFourNumberOfCard;
    }

    public DebitCardPayment() {
    }

    public String getLastFourNumberOfCard() {
        return lastFourNumberOfCard;
    }

    public void setLastFourNumberOfCard(String lastFourNumberOfCard) {
        this.lastFourNumberOfCard = lastFourNumberOfCard;
    }
}
