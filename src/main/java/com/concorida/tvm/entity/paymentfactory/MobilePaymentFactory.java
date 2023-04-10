package com.concorida.tvm.entity.paymentfactory;

import com.concorida.tvm.entity.MobilePayment;
import com.concorida.tvm.entity.Payment;

public class MobilePaymentFactory implements PaymentFactory{
    @Override
    public Payment createPayment() {
        return new MobilePayment();
    }
}
