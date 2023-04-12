package com.concorida.tvm.dao;

import com.concorida.tvm.entity.*;
import org.dom4j.DocumentException;

import java.io.IOException;
import java.util.List;

public interface PaymentDao {
    void addCreditPayment(CreditCardPayment creditCardPayment) throws IOException, DocumentException;
    void addDebitPayment(DebitCardPayment debitCardPayment) throws IOException, DocumentException;
    void addMobilePayment(MobilePayment mobilePayment) throws IOException, DocumentException;
    void addCashPayment(CashPayment cashPayment) throws IOException, DocumentException;

    List<CreditCardPayment> getAllCreditPaymentByMetroCardNumber(String metroCardId) throws IOException, DocumentException;
    List<DebitCardPayment> getAllDebitPaymentByMetroCardNumber(String metroCardId) throws IOException, DocumentException;
    List<MobilePayment> getAllMobilePaymentByMetroCardNumber(String metroCardId) throws IOException, DocumentException;
    List<CashPayment> getAllCashPaymentByMetroCardNumber(String metroCardId) throws IOException, DocumentException;
}
