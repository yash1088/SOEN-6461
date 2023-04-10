package com.concorida.tvm.dao.impl;

import com.concorida.tvm.dao.PaymentDao;
import com.concorida.tvm.entity.*;
import com.concorida.tvm.entity.paymentfactory.*;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PaymentDaoImpl implements PaymentDao {
    public static Document paymentDocument;
    PaymentFactory creditCardPaymentFactory = new CreditCardPaymentFactory();
    PaymentFactory debittCardPaymentFactory = new DebittCardPaymentFactory();
    PaymentFactory mobilePaymentFactory = new MobilePaymentFactory();
    PaymentFactory cashPaymentFactory = new CashPaymentFactory();
    public void loadMockData(String fileName) throws DocumentException {
        SAXReader saxReader = new SAXReader();
        paymentDocument = saxReader.read(fileName);
    }


    @Override
    public void addCreditPayment(CreditCardPayment creditCardPayment) throws IOException, DocumentException {
        String dataPath = "mockdata/payment.xml";
        loadMockData(dataPath);
        int currentNodeCount = this.getNodeCount(dataPath);
        Element rootElement = paymentDocument.getRootElement();
        Element primaryElement = rootElement.addElement("Payment");
        String paymentId = String.valueOf(currentNodeCount+1);
        primaryElement.addAttribute("id", paymentId);
        primaryElement.addElement("PaymentMethod").addText(creditCardPayment.getPaymentMethod());
        primaryElement.addElement("Amount").addText(String.valueOf(creditCardPayment.getAmount()));
        primaryElement.addElement("MetroCardNumber").addText(creditCardPayment.getMetroCard().getMetroCardId());
        primaryElement.addElement("PhoneNumber").addText(creditCardPayment.getMetroCard().getBindingPhoneNumber());
        primaryElement.addElement("PaidDate").addText(creditCardPayment.getPaidDate());
        saveInfo(dataPath);
    }

    @Override
    public void addDebitPayment(DebitCardPayment debitCardPayment) throws IOException, DocumentException {
        String dataPath = "mockdata/payment.xml";
        loadMockData(dataPath);
        int currentNodeCount = this.getNodeCount(dataPath);
        Element rootElement = paymentDocument.getRootElement();
        Element primaryElement = rootElement.addElement("Payment");
        String paymentId = String.valueOf(currentNodeCount+1);
        primaryElement.addAttribute("id", paymentId);
        primaryElement.addElement("PaymentMethod").addText(debitCardPayment.getPaymentMethod());
        primaryElement.addElement("Amount").addText(String.valueOf(debitCardPayment.getAmount()));
        primaryElement.addElement("MetroCardNumber").addText(debitCardPayment.getMetroCard().getMetroCardId());
        primaryElement.addElement("PhoneNumber").addText(debitCardPayment.getMetroCard().getBindingPhoneNumber());
        primaryElement.addElement("PaidDate").addText(debitCardPayment.getPaidDate());
        saveInfo(dataPath);
    }

    @Override
    public void addMobilePayment(MobilePayment mobilePayment) throws IOException, DocumentException {
        String dataPath = "mockdata/payment.xml";
        loadMockData(dataPath);
        int currentNodeCount = this.getNodeCount(dataPath);
        Element rootElement = paymentDocument.getRootElement();
        Element primaryElement = rootElement.addElement("Payment");
        String paymentId = String.valueOf(currentNodeCount+1);
        primaryElement.addAttribute("id", paymentId);
        primaryElement.addElement("PaymentMethod").addText(mobilePayment.getPaymentMethod());
        primaryElement.addElement("Amount").addText(String.valueOf(mobilePayment.getAmount()));
        primaryElement.addElement("MetroCardNumber").addText(mobilePayment.getMetroCard().getMetroCardId());
        primaryElement.addElement("PhoneNumber").addText(mobilePayment.getMetroCard().getBindingPhoneNumber());
        primaryElement.addElement("PaidDate").addText(mobilePayment.getPaidDate());
        saveInfo(dataPath);
    }

    @Override
    public void addCashPayment(CashPayment cashPayment) throws IOException, DocumentException {
        String dataPath = "mockdata/payment.xml";
        loadMockData(dataPath);
        int currentNodeCount = this.getNodeCount(dataPath);
        Element rootElement = paymentDocument.getRootElement();
        Element primaryElement = rootElement.addElement("Payment");
        String paymentId = String.valueOf(currentNodeCount+1);
        primaryElement.addAttribute("id", paymentId);
        primaryElement.addElement("PaymentMethod").addText(cashPayment.getPaymentMethod());
        primaryElement.addElement("Amount").addText(String.valueOf(cashPayment.getAmount()));
        primaryElement.addElement("MetroCardNumber").addText(cashPayment.getMetroCard().getMetroCardId());
        primaryElement.addElement("PhoneNumber").addText(cashPayment.getMetroCard().getBindingPhoneNumber());
        primaryElement.addElement("PaidDate").addText(cashPayment.getPaidDate());
        saveInfo(dataPath);
    }

    @Override
    public List<CreditCardPayment> getAllCreditPaymentByMetroCardNumber(String metroCardId) throws IOException, DocumentException {
        String dataPath = "mockdata/payment.xml";
        loadMockData(dataPath);
        List<CreditCardPayment> creditCardPaymentList = new ArrayList<>();
        Element rootElement = paymentDocument.getRootElement();
        Iterator iteratorPrimary = rootElement.elementIterator();
        while (iteratorPrimary.hasNext()){
            CreditCardPayment creditCardPayment = (CreditCardPayment) creditCardPaymentFactory.createPayment();
            MetroCard metroCard = new MetroCard();
            Element elementChild = (Element) iteratorPrimary.next();
            String elementAttribute = elementChild.attributeValue("id");
            creditCardPayment.setPaymentId(elementAttribute);
            Iterator iteratorChild = elementChild.elementIterator();
            while (iteratorChild.hasNext()){
                Element element = (Element) iteratorChild.next();
                if (element.getName().equalsIgnoreCase("PaymentMethod")){
                    creditCardPayment.setPaymentMethod(element.getTextTrim());
                }else if (element.getName().equalsIgnoreCase("Amount")){
                    creditCardPayment.setAmount(Double.parseDouble(element.getTextTrim()));
                }else if (element.getName().equalsIgnoreCase("MetroCardNumber")){
                    metroCard.setMetroCardId(element.getTextTrim());
                }else if (element.getName().equalsIgnoreCase("PhoneNumber")){
                    metroCard.setBindingPhoneNumber(element.getTextTrim());
                }else if (element.getName().equalsIgnoreCase("PaidDate")){
                    creditCardPayment.setPaidDate(element.getTextTrim());
                }
            }
            if ("123456".equalsIgnoreCase(metroCard.getMetroCardId())
                    && "Credit".equalsIgnoreCase(creditCardPayment.getPaymentMethod())){
               creditCardPayment.setMetroCard(metroCard);
                creditCardPaymentList.add(creditCardPayment);
            }
        }
        return creditCardPaymentList;
    }

    @Override
    public List<DebitCardPayment> getAllDebitPaymentByMetroCardNumber(String metroCardId) throws IOException, DocumentException {
        String dataPath = "mockdata/payment.xml";
        loadMockData(dataPath);
        List<DebitCardPayment> debitCardPaymentList = new ArrayList<>();
        Element rootElement = paymentDocument.getRootElement();
        Iterator iteratorPrimary = rootElement.elementIterator();
        while (iteratorPrimary.hasNext()){
            DebitCardPayment debitCardPayment = (DebitCardPayment) debittCardPaymentFactory.createPayment();
            MetroCard metroCard = new MetroCard();
            Element elementChild = (Element) iteratorPrimary.next();
            String elementAttribute = elementChild.attributeValue("id");
            debitCardPayment.setPaymentId(elementAttribute);
            Iterator iteratorChild = elementChild.elementIterator();
            while (iteratorChild.hasNext()){
                Element element = (Element) iteratorChild.next();
                if (element.getName().equalsIgnoreCase("PaymentMethod")){
                    debitCardPayment.setPaymentMethod(element.getTextTrim());
                }else if (element.getName().equalsIgnoreCase("Amount")){
                    debitCardPayment.setAmount(Double.parseDouble(element.getTextTrim()));
                }else if (element.getName().equalsIgnoreCase("MetroCardNumber")){
                    metroCard.setMetroCardId(element.getTextTrim());
                }else if (element.getName().equalsIgnoreCase("PhoneNumber")){
                    metroCard.setBindingPhoneNumber(element.getTextTrim());
                }else if (element.getName().equalsIgnoreCase("PaidDate")){
                    debitCardPayment.setPaidDate(element.getTextTrim());
                }
            }
            if ("123456".equalsIgnoreCase(metroCard.getMetroCardId())
                    && "Debit".equalsIgnoreCase(debitCardPayment.getPaymentMethod())){
                debitCardPayment.setMetroCard(metroCard);
                debitCardPaymentList.add(debitCardPayment);
            }
        }
        return debitCardPaymentList;
    }

    @Override
    public List<MobilePayment> getAllMobilePaymentByMetroCardNumber(String metroCardId) throws IOException, DocumentException {
        String dataPath = "mockdata/payment.xml";
        loadMockData(dataPath);
        List<MobilePayment> mobilePaymentList = new ArrayList<>();
        Element rootElement = paymentDocument.getRootElement();
        Iterator iteratorPrimary = rootElement.elementIterator();
        while (iteratorPrimary.hasNext()){
            MobilePayment mobilePayment = (MobilePayment) mobilePaymentFactory.createPayment();
            MetroCard metroCard = new MetroCard();
            Element elementChild = (Element) iteratorPrimary.next();
            String elementAttribute = elementChild.attributeValue("id");
            mobilePayment.setPaymentId(elementAttribute);
            Iterator iteratorChild = elementChild.elementIterator();
            while (iteratorChild.hasNext()){
                Element element = (Element) iteratorChild.next();
                if (element.getName().equalsIgnoreCase("PaymentMethod")){
                    mobilePayment.setPaymentMethod(element.getTextTrim());
                }else if (element.getName().equalsIgnoreCase("Amount")){
                    mobilePayment.setAmount(Double.parseDouble(element.getTextTrim()));
                }else if (element.getName().equalsIgnoreCase("MetroCardNumber")){
                    metroCard.setMetroCardId(element.getTextTrim());
                }else if (element.getName().equalsIgnoreCase("PhoneNumber")){
                    metroCard.setBindingPhoneNumber(element.getTextTrim());
                }else if (element.getName().equalsIgnoreCase("PaidDate")){
                    mobilePayment.setPaidDate(element.getTextTrim());
                }
            }
            if ("123456".equalsIgnoreCase(metroCard.getMetroCardId())
                    && "Debit".equalsIgnoreCase(mobilePayment.getPaymentMethod())){
                mobilePayment.setMetroCard(metroCard);
                mobilePaymentList.add(mobilePayment);
            }
        }
        return mobilePaymentList;
    }

    @Override
    public List<CashPayment> getAllCashPaymentByMetroCardNumber(String metroCardId) throws IOException, DocumentException {
        String dataPath = "mockdata/payment.xml";
        loadMockData(dataPath);
        List<CashPayment> cashPaymentList = new ArrayList<>();
        Element rootElement = paymentDocument.getRootElement();
        Iterator iteratorPrimary = rootElement.elementIterator();
        while (iteratorPrimary.hasNext()){
            CashPayment cashPayment = (CashPayment) cashPaymentFactory.createPayment();
            MetroCard metroCard = new MetroCard();
            Element elementChild = (Element) iteratorPrimary.next();
            String elementAttribute = elementChild.attributeValue("id");
            cashPayment.setPaymentId(elementAttribute);
            Iterator iteratorChild = elementChild.elementIterator();
            while (iteratorChild.hasNext()){
                Element element = (Element) iteratorChild.next();
                if (element.getName().equalsIgnoreCase("PaymentMethod")){
                    cashPayment.setPaymentMethod(element.getTextTrim());
                }else if (element.getName().equalsIgnoreCase("Amount")){
                    cashPayment.setAmount(Double.parseDouble(element.getTextTrim()));
                }else if (element.getName().equalsIgnoreCase("MetroCardNumber")){
                    metroCard.setMetroCardId(element.getTextTrim());
                }else if (element.getName().equalsIgnoreCase("PhoneNumber")){
                    metroCard.setBindingPhoneNumber(element.getTextTrim());
                }else if (element.getName().equalsIgnoreCase("PaidDate")){
                    cashPayment.setPaidDate(element.getTextTrim());
                }
            }
            if ("123456".equalsIgnoreCase(metroCard.getMetroCardId())
                    && "Cash".equalsIgnoreCase(cashPayment.getPaymentMethod())){
                cashPayment.setMetroCard(metroCard);
                cashPaymentList.add(cashPayment);
            }
        }
        return cashPaymentList;
    }


    public void saveInfo(String fileName) throws IOException {
        XMLWriter xmlWriter = new XMLWriter(new FileOutputStream(fileName));
        xmlWriter.write(paymentDocument);
        xmlWriter.flush();
        xmlWriter.close();
    }
    public int getNodeCount(String fileName) throws DocumentException {
        SAXReader saxReader1 = new SAXReader();
        Document doc = saxReader1.read(fileName);
        Element rootElement = doc.getRootElement();
        Iterator iteratorChild = rootElement.elementIterator();
        int nodeCount = 0;
        while (iteratorChild.hasNext()){
            iteratorChild.next();
            nodeCount ++;
        }
        return nodeCount;
    }
}
