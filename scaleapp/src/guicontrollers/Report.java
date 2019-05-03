
package guicontrollers;

import controller.DBConnection;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Report implements Initializable {
    public DatePicker toDate;
    public DatePicker fromDate;
    public TextField driverName;
    public TextField agentName;
    public Button exit;
    public Button exportToPDFFile;
    DBConnection db = new DBConnection();

    public Report() {
    }

    @FXML
    public void exportToExcel(ActionEvent actionEvent) throws IOException, IllegalAccessException, InstantiationException, ClassNotFoundException, SQLException {
        this.exportToExcel(this.query());
    }

    public void exit(ActionEvent actionEvent) {
        Stage stage = (Stage)this.exit.getScene().getWindow();
        stage.close();
    }

    public void exportToPDF(ActionEvent actionEvent) {
    }

    public void exportToExcel(String query) throws SQLException, IllegalAccessException, InstantiationException, ClassNotFoundException, IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("ErbilFeed");
        Font font = sheet.getWorkbook().createFont();
        font.setFontName("Arial");
        font.setFontHeightInPoints((short)14);
        CellStyle style = sheet.getWorkbook().createCellStyle();
        style.setFont(font);
        style.setWrapText(true);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        XSSFRow header1 = sheet.createRow(0);
        header1.setRowStyle(style);
        header1.createCell(0).setCellValue("رقم الفاتورة");
        header1.createCell(1).setCellValue("تاريخ الخروح");
        header1.createCell(2).setCellValue("الشركة");
        header1.createCell(3).setCellValue("رقم الشاحنة");
        header1.createCell(4).setCellValue("المحافظة");
        header1.createCell(5).setCellValue("المنتج");
        header1.createCell(6).setCellValue("الوكيل/المشتري");
        header1.createCell(7).setCellValue("المجهز");
        header1.createCell(8).setCellValue("الاسم السائق");
        header1.createCell(9).setCellValue("رقم الهاتف ");
        header1.createCell(10).setCellValue("الوزن الصافى");
        System.out.println(query);
        Statement statement = DBConnection.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        for(Integer index = 1; resultSet.next(); index = index + 1) {
            XSSFRow row = sheet.createRow(index);
            LocalDate date = resultSet.getDate(5).toLocalDate();
            row.createCell(0).setCellValue((double)resultSet.getInt(13));
            row.createCell(1).setCellValue(String.valueOf(date));
            row.createCell(2).setCellValue(resultSet.getString(12));
            row.createCell(3).setCellValue(resultSet.getString(8));
            row.createCell(4).setCellValue(resultSet.getString(10));
            row.createCell(5).setCellValue(resultSet.getString(18));
            row.createCell(6).setCellValue(resultSet.getString(16));
            row.createCell(7).setCellValue(resultSet.getString(17));
            row.createCell(8).setCellValue(resultSet.getString(7));
            row.createCell(9).setCellValue(resultSet.getString(11));
            row.createCell(10).setCellValue((double)resultSet.getInt(6));
        }

        String fileName = "Excel_report_" + LocalDate.now().toString() + ".xlsx";
        FileOutputStream fileOutputStream = new FileOutputStream(new File("C:/data", fileName));
        workbook.write(fileOutputStream);
        fileOutputStream.flush();
        fileOutputStream.close();
    }

    public String query() {
        try {
            if (this.stringValidation(this.agentName.getText()) && this.stringValidation(this.driverName.getText())) {
                return "SELECT * FROM WEIGHT WHERE DATE_TIME_SECOND_READING BETWEEN  '" + this.fromDate.getValue().toString() + "' and  '" + this.toDate.getValue().toString() + "' AND VERSION = 2 AND CUST_NAME = '" + this.driverName.getText() + "' AND AGENT = '" + this.agentName.getText() + "';";
            } else if (this.stringValidation(this.agentName.getText())) {
                return "SELECT * FROM WEIGHT WHERE DATE_TIME_SECOND_READING BETWEEN  '" + this.fromDate.getValue().toString() + "' and  '" + this.toDate.getValue().toString() + "' AND VERSION = 2 AND  AGENT = '" + this.agentName.getText() + "';";
            } else {
                return this.stringValidation(this.driverName.getText()) ? "SELECT * FROM WEIGHT WHERE DATE_TIME_SECOND_READING BETWEEN  '" + this.fromDate.getValue().toString() + "' and  '" + this.toDate.getValue().toString() + "' AND VERSION = 2 AND CUST_NAME = '" + this.driverName.getText() + "' ;" : "SELECT * FROM WEIGHT WHERE DATE_TIME_SECOND_READING BETWEEN  '" + this.fromDate.getValue().toString() + "' and  '" + this.toDate.getValue().toString() + "' AND VERSION = 2 ;";
            }
        } catch (Exception var2) {
            return "SELECT * FROM WEIGHT WHERE VERSION = 2";
        }
    }

    public boolean stringValidation(String string) {
        try {
            if (!string.isEmpty()) {
                return true;
            }
        } catch (NullPointerException var3) {
        }

        return false;
    }

    public void initialize(URL location, ResourceBundle resources) {
        Integer year = LocalDate.now().getYear();
        this.fromDate.setValue(LocalDate.parse(year.toString() + "-01-01"));
        this.toDate.setValue(LocalDate.parse(year.toString() + "-12-31"));
    }
}


