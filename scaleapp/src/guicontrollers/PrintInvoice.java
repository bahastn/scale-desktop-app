
package guicontrollers;

import entities.Login;
import controller.DBConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.print.Printer.MarginType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import jssc.SerialPortException;

public class PrintInvoice implements Initializable {
    @FXML
    public Button exit;
    public Label invoiceNumber;
    public Label grossDateAndTime;
    public Label phoneNumber;
    public Label net;
    public Label product;
    public Label company;
    public Label carNumber;
    public Label agent;
    public Label driverFullName;
    public Label provider;
    public Label note;
    public Label gross;
    public Label tar;
    public Label tarDateAndTime;
    public Label opertor;
    public AnchorPane printingArea;
    public Button print;
    public Label cityIssue;
    public Integer invoiceID;
    ObservableList<Integer> list = FXCollections.observableArrayList();
    public ChoiceBox<Integer> numberOfCopies;
    Login user = new Login();
    DBConnection db = new DBConnection();

    public PrintInvoice() {
    }

    public void doPrinter() throws IllegalAccessException, InstantiationException, ClassNotFoundException, SQLException, InterruptedException, IOException, SerialPortException {
        PrinterJob printerJob = PrinterJob.createPrinterJob();
        printerJob.getJobSettings().setCopies(this.numberCopies());
        System.out.println(this.numberOfCopies.getValue());
        PageLayout pageLayout = Printer.getDefaultPrinter().createPageLayout(Paper.A5, PageOrientation.PORTRAIT, MarginType.HARDWARE_MINIMUM);
        printerJob.getJobSettings().setPageLayout(pageLayout);
        printerJob.getJobSettings().setJobName("ErbilFeed_ScaleInvoice");
        printerJob.getJobStatus();
        printerJob.printPage(this.printingArea);
        printerJob.endJob();
    }

    public void closePanel() {
        Stage stage = (Stage)this.exit.getScene().getWindow();
        stage.close();
    }

    public void initialize(URL location, ResourceBundle resources) {
        this.setNumberOfCopies();
    }

    public void initData(Integer integer) throws IllegalAccessException, InstantiationException, ClassNotFoundException, SQLException, IOException {
        this.invoiceID = integer;
        Statement statement = DBConnection.getConnection().createStatement();
        String query = "SELECT * FROM WEIGHT WHERE INVOICE_NUMBER =  " + integer + ";";
        ResultSet resultSet = statement.executeQuery(query);
        resultSet.next();
        this.invoiceNumber.setText(String.valueOf(integer));
        this.gross.setText(resultSet.getString(2));
        this.grossDateAndTime.setText(String.valueOf(resultSet.getTimestamp(3)));
        this.tar.setText(String.valueOf(resultSet.getString(4)));
        this.tarDateAndTime.setText(String.valueOf(resultSet.getTimestamp(5)));
        this.net.setText(String.valueOf(resultSet.getInt(6)));
        this.driverFullName.setText(resultSet.getString(7));
        this.carNumber.setText(resultSet.getString(8));
        this.note.setText(resultSet.getString(9));
        this.cityIssue.setText(resultSet.getString(10));
        this.phoneNumber.setText(resultSet.getString(11));
        this.company.setText(resultSet.getString(12));
        this.product.setText(resultSet.getString(18));
        this.provider.setText(resultSet.getString(17));
        this.agent.setText(resultSet.getString(16));
        this.getOperter();
    }

    public void getOperter() throws IllegalAccessException, InstantiationException, ClassNotFoundException, SQLException {
        Statement statement = DBConnection.getConnection().createStatement();
        String operiterQuery = "SELECT  USER.FULL_NAME FROM  USER JOIN  WEIGHT ON USER.USERID =  WEIGHT.USERID WHERE  INVOICE_NUMBER = " + this.invoiceID + ";";
        ResultSet resultSet = statement.executeQuery(operiterQuery);
        resultSet.next();
        String fullName = resultSet.getString(1);
        this.opertor.setText(fullName);
    }

    public void setNumberOfCopies() {
        this.list.removeAll();
        this.list.addAll(1, 2, 3, 4, 5);
        this.numberOfCopies.setItems(this.list);
    }

    public Integer numberCopies() {
        return this.numberOfCopies.getValue() != null ? this.numberOfCopies.getValue() : 2;
    }

    public void previuseRecord(ActionEvent event) throws Exception {
        System.out.println("priveous record will be");
        this.findRecord(-1);
    }

    public void nextRecord(ActionEvent event) throws Exception {
        System.out.println(" next record will be ");
        this.findRecord(1);
    }

    public void findRecord(int recordNymber) throws Exception {
        Integer invoice = Integer.valueOf(this.invoiceNumber.getText());
        System.out.println(invoice);
        Integer result = invoice + recordNymber;
        System.out.println(recordNymber);

        try {
            if (result >= DBConnection.createID("WEIGHT", 13)) {
                AlertBox.massage("Alert", "you reach Last Page");
            } else if (result < 1) {
                AlertBox.massage("Alert", "You Reach lowest invoice number");
            } else {
                this.initData(result);
            }
        } catch (Exception var5) {
        }

    }

}

