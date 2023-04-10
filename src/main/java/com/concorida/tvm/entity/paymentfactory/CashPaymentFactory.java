package com.concorida.tvm.entity.paymentfactory;

import com.concorida.tvm.entity.CashPayment;
import com.concorida.tvm.entity.CreditCardPayment;
import com.concorida.tvm.entity.Payment;

public class CashPaymentFactory implements PaymentFactory{
    @Override
    public Payment createPayment() {
        return new CashPayment();
    }
}
