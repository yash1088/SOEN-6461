package com.concorida.tvm.service;

import com.concorida.tvm.entity.CashPayment;
import com.concorida.tvm.entity.CreditCardPayment;
import com.concorida.tvm.entity.DebitCardPayment;
import com.concorida.tvm.entity.MobilePayment;
import org.dom4j.DocumentException;

import java.io.IOException;
import java.util.List;

public interface PaymentService {
    void addCreditCardPayment(CreditCardPayment creditCardPayment) throws DocumentException, IOException;
    void addDebitCardPayment(DebitCardPayment debitCardPayment) throws DocumentException, IOException;
    void addMobilePayment(MobilePayment mobilePayment) throws DocumentException, IOException;
    void addCashPayment(CashPayment cashPayment) throws DocumentException, IOException;
    List<Object> getAllPaymentListByMetroCardId(String metroCardId) throws DocumentException, IOException;
}

