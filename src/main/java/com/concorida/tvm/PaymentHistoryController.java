package com.concorida.tvm;

import com.concorida.tvm.entity.CashPayment;
import com.concorida.tvm.entity.CreditCardPayment;
import com.concorida.tvm.entity.DebitCardPayment;
import com.concorida.tvm.entity.MobilePayment;
import com.concorida.tvm.service.PaymentService;
import com.concorida.tvm.service.impl.PaymentServiceImpl;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;
import org.dom4j.DocumentException;

import java.io.IOException;

public class PaymentHistoryController {
    PaymentService paymentService = new PaymentServiceImpl();
    @FXML
    private TableView<Object> propertyTableView;
    @FXML
    private TableColumn<Object, String> idColumn;
    @FXML
    private TableColumn<Object, Double> amountColumn;
    @FXML
    private TableColumn<Object, String> metroCardColumn;
    @FXML
    private TableColumn<Object, String> phoneNumberColumn;
    @FXML
    private TableColumn<Object, String> paidDateColumn;
    @FXML
    private TableColumn<Object, String> methodColumn;

    private ObservableList<Object> paymentList;

    public void initialize() {
        fetchDataInBackground();

    }
    private void fetchDataInBackground() {
        Thread fetchDataThread = new Thread(() -> {
            paymentList = FXCollections.observableArrayList();
            try {
                paymentList.addAll(paymentService.getAllPaymentListByMetroCardId("123456"));
            } catch (DocumentException | IOException e) {
                throw new RuntimeException(e);
            }

            Platform.runLater(() -> {
                setupTableColumns();
                propertyTableView.setItems(paymentList);
            });
        });

        fetchDataThread.start();
    }

    private void setupTableColumns() {
        idColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Object, String>,
                ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Object, String> cellData) {
                Object payment = cellData.getValue();
                if (payment instanceof CreditCardPayment) {
                    return new ReadOnlyObjectWrapper<String>(((CreditCardPayment) payment).getPaymentId());
                } else if (payment instanceof DebitCardPayment) {
                    return new ReadOnlyObjectWrapper<String>(((DebitCardPayment) payment).getPaymentId());
                } else if (payment instanceof MobilePayment) {
                    return new ReadOnlyObjectWrapper<String>(((MobilePayment) payment).getPaymentId());
                }else if (payment instanceof CashPayment) {
                    return new ReadOnlyObjectWrapper<String>(((CashPayment) payment).getPaymentId());
                }
                return null;
            }
        });
        methodColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Object, String>,
                ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Object, String> cellData) {
                Object payment = cellData.getValue();
                if (payment instanceof CreditCardPayment) {
                    return new ReadOnlyObjectWrapper<String>(((CreditCardPayment) payment).getPaymentMethod());
                } else if (payment instanceof DebitCardPayment) {
                    return new ReadOnlyObjectWrapper<String>(((DebitCardPayment) payment).getPaymentMethod());
                } else if (payment instanceof MobilePayment) {
                    return new ReadOnlyObjectWrapper<String>(((MobilePayment) payment).getPaymentMethod());
                }else if (payment instanceof CashPayment) {
                    return new ReadOnlyObjectWrapper<String>(((CashPayment) payment).getPaymentMethod());
                }
                return null;
            }
        });
        amountColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Object, Double>,
                ObservableValue<Double>>() {
            public ObservableValue<Double> call(TableColumn.CellDataFeatures<Object, Double> cellData) {
                Object payment = cellData.getValue();
                if (payment instanceof CreditCardPayment) {
                    return new ReadOnlyObjectWrapper<Double>(((CreditCardPayment) payment).getAmount());
                } else if (payment instanceof DebitCardPayment) {
                    return new ReadOnlyObjectWrapper<Double>(((DebitCardPayment) payment).getAmount());
                } else if (payment instanceof MobilePayment) {
                    return new ReadOnlyObjectWrapper<Double>(((MobilePayment) payment).getAmount());
                }else if (payment instanceof CashPayment) {
                    return new ReadOnlyObjectWrapper<Double>(((CashPayment) payment).getAmount());
                }
                return null;
            }
        });
        metroCardColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Object, String>,
                ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Object, String> cellData) {
                Object payment = cellData.getValue();
                if (payment instanceof CreditCardPayment) {
                    return new ReadOnlyObjectWrapper<String>(((CreditCardPayment) payment).getMetroCard().getMetroCardId());
                } else if (payment instanceof DebitCardPayment) {
                    return new ReadOnlyObjectWrapper<String>(((DebitCardPayment) payment).getMetroCard().getMetroCardId());
                } else if (payment instanceof MobilePayment) {
                    return new ReadOnlyObjectWrapper<String>(((MobilePayment) payment).getMetroCard().getMetroCardId());
                } else if (payment instanceof CashPayment) {
                    return new ReadOnlyObjectWrapper<String>(((CashPayment) payment).getMetroCard().getMetroCardId());
                }
                return null;
            }
        });
        phoneNumberColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Object, String>,
                ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Object, String> cellData) {
                Object payment = cellData.getValue();
                if (payment instanceof CreditCardPayment) {
                    return new ReadOnlyObjectWrapper<String>(((CreditCardPayment) payment).getMetroCard().getBindingPhoneNumber());
                } else if (payment instanceof DebitCardPayment) {
                    return new ReadOnlyObjectWrapper<String>(((DebitCardPayment) payment).getMetroCard().getBindingPhoneNumber());
                } else if (payment instanceof MobilePayment) {
                    return new ReadOnlyObjectWrapper<String>(((MobilePayment) payment).getMetroCard().getBindingPhoneNumber());
                }else if (payment instanceof CashPayment) {
                    return new ReadOnlyObjectWrapper<String>(((CashPayment) payment).getMetroCard().getBindingPhoneNumber());
                }
                return null;
            }
        });
        paidDateColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Object, String>,
                ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Object, String> cellData) {
                Object payment = cellData.getValue();
                if (payment instanceof CreditCardPayment) {
                    return new ReadOnlyObjectWrapper<String>(((CreditCardPayment) payment).getPaidDate());
                } else if (payment instanceof DebitCardPayment) {
                    return new ReadOnlyObjectWrapper<String>(((DebitCardPayment) payment).getPaidDate());
                } else if (payment instanceof MobilePayment) {
                    return new ReadOnlyObjectWrapper<String>(((MobilePayment) payment).getPaidDate());
                }else if (payment instanceof CashPayment) {
                    return new ReadOnlyObjectWrapper<String>(((CashPayment) payment).getPaidDate());
                }
                return null;
            }
        });
    }
}
