package com.concorida.tvm.entity.paymentfactory;

import com.concorida.tvm.entity.CreditCardPayment;
import com.concorida.tvm.entity.Payment;

public class CreditCardPaymentFactory implements PaymentFactory{
    @Override
    public Payment createPayment() {
        return new CreditCardPayment();
    }
}
