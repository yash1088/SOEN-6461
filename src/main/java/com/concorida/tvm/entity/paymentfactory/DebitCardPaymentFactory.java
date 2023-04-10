package com.concorida.tvm.entity.paymentfactory;

import com.concorida.tvm.entity.DebitCardPayment;
import com.concorida.tvm.entity.Payment;

public class DebitCardPaymentFactory implements PaymentFactory{
    @Override
    public Payment createPayment() {
        return new DebitCardPayment();
    }
}
