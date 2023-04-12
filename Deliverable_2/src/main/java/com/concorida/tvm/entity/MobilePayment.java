package com.concorida.tvm.entity;

public class MobilePayment extends Payment{
    String qrCode;

    public MobilePayment(String qrCode) {
        this.qrCode = qrCode;
    }

    public MobilePayment() {
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }
}
