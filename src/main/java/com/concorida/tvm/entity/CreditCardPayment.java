package com.concorida.tvm.entity;

public class CreditCardPayment extends Payment{
    String lastFourNumberOfCard;

    public CreditCardPayment(String lastFourNumberOfCard) {
        this.lastFourNumberOfCard = lastFourNumberOfCard;
    }

    public CreditCardPayment() {
    }

    public String getLastFourNumberOfCard() {
        return lastFourNumberOfCard;
    }

    public void setLastFourNumberOfCard(String lastFourNumberOfCard) {
        this.lastFourNumberOfCard = lastFourNumberOfCard;
    }
}
