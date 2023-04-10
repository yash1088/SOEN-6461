package com.concorida.tvm.service.impl;

import com.concorida.tvm.dao.PaymentDao;
import com.concorida.tvm.dao.impl.PaymentDaoImpl;
import com.concorida.tvm.entity.CashPayment;
import com.concorida.tvm.entity.CreditCardPayment;
import com.concorida.tvm.entity.DebitCardPayment;
import com.concorida.tvm.entity.MobilePayment;
import com.concorida.tvm.service.PaymentService;
import org.dom4j.DocumentException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PaymentServiceImpl implements PaymentService {
    PaymentDao paymentDao = new PaymentDaoImpl();

    @Override
    public void addCreditCardPayment(CreditCardPayment creditCardPayment) throws DocumentException, IOException {
        paymentDao.addCreditPayment(creditCardPayment);
    }

    @Override
    public void addDebitCardPayment(DebitCardPayment debitCardPayment) throws DocumentException, IOException {
        paymentDao.addDebitPayment(debitCardPayment);
    }

    @Override
    public void addMobilePayment(MobilePayment mobilePayment) throws DocumentException, IOException {
        paymentDao.addMobilePayment(mobilePayment);
    }

    @Override
    public void addCashPayment(CashPayment cashPayment) throws DocumentException, IOException {
        paymentDao.addCashPayment(cashPayment);
    }

    @Override
    public List<Object> getAllPaymentListByMetroCardId(String metroCardId) throws DocumentException, IOException {
        List<Object> allPaymentList = new ArrayList<>();
        allPaymentList.addAll(paymentDao.getAllCreditPaymentByMetroCardNumber(metroCardId));
        allPaymentList.addAll(paymentDao.getAllDebitPaymentByMetroCardNumber(metroCardId));
        allPaymentList.addAll(paymentDao.getAllMobilePaymentByMetroCardNumber(metroCardId));
        allPaymentList.addAll(paymentDao.getAllCashPaymentByMetroCardNumber(metroCardId));
        return allPaymentList;
    }
}
