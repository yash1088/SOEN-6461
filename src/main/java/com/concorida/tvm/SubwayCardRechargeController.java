package com.concorida.tvm;

import com.concorida.tvm.backend.QRCodeGenerator;
import com.concorida.tvm.entity.*;
import com.concorida.tvm.service.PaymentService;
import com.concorida.tvm.service.impl.PaymentServiceImpl;
import com.google.zxing.WriterException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import org.dom4j.DocumentException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

public class SubwayCardRechargeController {
    PaymentService paymentService = new PaymentServiceImpl();
    @FXML private Label label0,label1,label2,label3,label4;
    @FXML private RadioButton rd1,rd2,rd3,rd4,rd5,rd6,rd7;
    @FXML private Button rechargeButton;
    ResourceBundle bundle = ResourceBundle.getBundle("messages_fr",Locale.FRANCE);
    AlertBox alertBox = new AlertBox();
    String msg = "";
    boolean is_french = false;
    public SubwayCardRechargeController(){

    }

    public SubwayCardRechargeController(String msg){
        this.msg = msg;
    }

    public void setMsg(boolean is_french){
        this.is_french = is_french;
        if(is_french){
            label0.setText(bundle.getString("label_a"));
            label1.setText(bundle.getString("label_b"));
            label2.setText(bundle.getString("label_c"));
            label3.setText(bundle.getString("label_d"));
            label4.setText(bundle.getString("label_e"));

            rd1.setText(bundle.getString("rd1"));
            rd2.setText(bundle.getString("rd2"));
            rd3.setText(bundle.getString("rd3"));
            rd4.setText(bundle.getString("rd4"));
            rd5.setText(bundle.getString("rd5"));
            rd6.setText(bundle.getString("rd6"));
            rd7.setText(bundle.getString("rd7"));



            rechargeButton.setText(bundle.getString("rechargebutton"));
        }
    }

    @FXML
    private ToggleGroup rechargeAmountToggleGroup;

    @FXML
    private ToggleGroup paymentMethodToggleGroup;
    @FXML
    private ToggleGroup invoiceToggleGroup;
    @FXML
    private TextField emailAddressTextField;
    @FXML
    private HBox emailAddressHBox;
    @FXML
    private Label Invoice_Msg;

    @FXML
    private Label QRCode;
    @FXML
    private Label Message;

    @FXML
    public void initialize() {
        updateEmailAddressVisibility();

        invoiceToggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                updateEmailAddressVisibility();
            }
        });
        rechargeButton.setOnAction(event -> {

            try {
                if("EMAIL_INVOICE".equalsIgnoreCase(invoiceToggleGroup.getSelectedToggle().getUserData().toString()) &&
                        "".equalsIgnoreCase(emailAddressTextField.getText().trim())){
                    if (is_french){
                        alertBox.showAlert("Veuillez fournir votre e-mail!!",is_french);
                    }
                    else{
                        alertBox.showAlert("Please provide your email!!",is_french);
                    }
                }
                else{
                    handleRecharge();
                }

            } catch (IOException | DocumentException e) {
                throw new RuntimeException(e);
            } catch (WriterException e) {
                throw new RuntimeException(e);
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @FXML
    private void handleRecharge() throws IOException, WriterException, URISyntaxException, DocumentException {

        Invoice_Msg.setText("");
        RadioButton selectedRechargeAmount = (RadioButton) rechargeAmountToggleGroup.getSelectedToggle();
        String rechargeAmount = (String) selectedRechargeAmount.getUserData();

        RadioButton selectedPaymentMethod = (RadioButton) paymentMethodToggleGroup.getSelectedToggle();
        String paymentMethod = (String) selectedPaymentMethod.getUserData();

        RadioButton selectedInvoiceMethod = (RadioButton) invoiceToggleGroup.getSelectedToggle();
        String invoiceMethod = (String) selectedInvoiceMethod.getUserData();

        if ("CASH".equalsIgnoreCase(paymentMethod)){
            CashPayment cashPayment = new CashPayment();
            MetroCard metroCard = new MetroCard();
            cashPayment.setAmount(Double.parseDouble(rechargeAmount));
            cashPayment.setPaymentMethod("Cash");
            Date dNow = new Date( );
            SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
            cashPayment.setPaidDate(ft.format(dNow));
            metroCard.setBindingPhoneNumber("4388888888");
            metroCard.setMetroCardId("123456");
            cashPayment.setMetroCard(metroCard);
            paymentService.addCashPayment(cashPayment);
        }else if ("CREDIT_CARD".equalsIgnoreCase(paymentMethod)){
            CreditCardPayment creditCardPayment = new CreditCardPayment();
            MetroCard metroCard = new MetroCard();
            creditCardPayment.setAmount(Double.parseDouble(rechargeAmount));
            creditCardPayment.setPaymentMethod("Credit");
            Date dNow = new Date( );
            SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
            creditCardPayment.setPaidDate(ft.format(dNow));
            metroCard.setBindingPhoneNumber("4388888888");
            metroCard.setMetroCardId("123456");
            creditCardPayment.setMetroCard(metroCard);
            paymentService.addCreditCardPayment(creditCardPayment);
        }else if ("DEBIT_CARD".equalsIgnoreCase(paymentMethod)){
            DebitCardPayment debitCardPayment = new DebitCardPayment();
            MetroCard metroCard = new MetroCard();
            debitCardPayment.setAmount(Double.parseDouble(rechargeAmount));
            debitCardPayment.setPaymentMethod("Credit");
            Date dNow = new Date( );
            SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
            debitCardPayment.setPaidDate(ft.format(dNow));
            metroCard.setBindingPhoneNumber("4388888888");
            metroCard.setMetroCardId("123456");
            debitCardPayment.setMetroCard(metroCard);
            paymentService.addDebitCardPayment(debitCardPayment);
        }else if ("MOBILE_PAYMENT".equalsIgnoreCase(paymentMethod)){
            MobilePayment mobilePayment = new MobilePayment();
            MetroCard metroCard = new MetroCard();
            mobilePayment.setAmount(Double.parseDouble(rechargeAmount));
            mobilePayment.setPaymentMethod("Credit");
            Date dNow = new Date( );
            SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
            mobilePayment.setPaidDate(ft.format(dNow));
            metroCard.setBindingPhoneNumber("4388888888");
            metroCard.setMetroCardId("123456");
            mobilePayment.setMetroCard(metroCard);
            paymentService.addMobilePayment(mobilePayment);
        }

        rechargeSubwayCard(rechargeAmount, paymentMethod);
        printInvoice(invoiceMethod);
    }



    private void printInvoice(String Invoice_Message){
        if ("PRINT_INVOICE".equalsIgnoreCase(Invoice_Message)){
            if(is_french){
                System.out.println("iii");
                Invoice_Msg.setText(bundle.getString("invoice_msg"));
            }
            else{
                Invoice_Msg.setText("The invoice printed");
            }

        }if ("EMAIL_INVOICE".equalsIgnoreCase(Invoice_Message)){
            if(is_french){
                Invoice_Msg.setText(bundle.getString("invoice_msg_email"));
            }
            else{
                Invoice_Msg.setText("The invoice printed");
            }
        }
    }

    private void rechargeSubwayCard(String rechargeAmount, String paymentMethod) throws IOException, WriterException, URISyntaxException {
        if ("CREDIT_CARD".equalsIgnoreCase(paymentMethod)){
            if(is_french){
                Message.setText(bundle.getString("recharge_success"));
            }
            else{
                Message.setText("Recharge successfully!");
            }
        }else if("DEBIT_CARD".equalsIgnoreCase(paymentMethod)){
            if(is_french){
                Message.setText(bundle.getString("recharge_success"));
            }
            else{
                Message.setText("Recharge successfully!");
            }
        }else if("CASH".equalsIgnoreCase(paymentMethod)){
            if(is_french){
                Message.setText(bundle.getString("recharge_success"));
            }
            else{
                Message.setText("Recharge successfully!");
            }
        }else {
            Message.setText("Please scan QR code to pay.");
            QRCodeGenerator.getInstance().generateQRCode();
            Image image;
            image = new Image(getClass().getResourceAsStream("/QR/payment_code.png"),250,250,false,false);
            QRCode.setGraphic(new ImageView(image));
        }


    }
    private void updateEmailAddressVisibility() {
        RadioButton selectedInvoiceOption = (RadioButton) invoiceToggleGroup.getSelectedToggle();
        String userData = selectedInvoiceOption.getUserData().toString();
        emailAddressHBox.setVisible(userData.equals("EMAIL_INVOICE"));
    }


}
