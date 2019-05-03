
package entities;

import controller.DBConnection;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Scale {
    DBConnection dbConnection = new DBConnection();
    private Integer scaleId;
    private Integer firstReading;
    private Date dateTimeFirstReading;
    private Integer secondReading;
    private Date dateTimeSecondReading;
    private Integer netWeight;
    private String custName;
    private String custCarNember;
    private String note;
    private String custCarCity;
    private String custPhone;
    private String company;
    private Integer invoiceNumber;
    public Integer version;
    private String agent;
    private String foodType;
    private int userid;
    private String provider;

    public Scale() {
    }
    //

    public Scale(Integer scaleId, Integer firstReading, Date dateTimeFirstReading, Integer secondReading, Date dateTimeSecondReading, Integer netWeight, String custName, String custCarNember, String note, String custCarCity, String custPhone, String company, Integer invoiceNumber, Integer version, String agent, String foodType, int userid, String provider) {
        this.scaleId = scaleId;
        this.firstReading = firstReading;
        this.dateTimeFirstReading = dateTimeFirstReading;
        this.secondReading = secondReading;
        this.dateTimeSecondReading = dateTimeSecondReading;
        this.netWeight = netWeight;
        this.custName = custName;
        this.custCarNember = custCarNember;
        this.note = note;
        this.custCarCity = custCarCity;
        this.custPhone = custPhone;
        this.company = company;
        this.invoiceNumber = invoiceNumber;
        this.version = version;
        this.agent = agent;
        this.foodType = foodType;
        this.userid = userid;
        this.provider = provider;
    }

    public String getProvider() {
        return this.provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public Integer getScaleId() {
        return this.scaleId;
    }

    public void setScaleId(Integer scaleId) {
        this.scaleId = scaleId;
    }

    public Integer getFirstReading() {
        return this.firstReading;
    }

    public void setFirstReading(Integer firstReading) {
        this.firstReading = firstReading;
    }

    public Date getDateTimeFirstReading() {
        return this.dateTimeFirstReading;
    }

    public void setDateTimeFirstReading(Date dateTimeFirstReading) {
        this.dateTimeFirstReading = dateTimeFirstReading;
    }

    public Integer getSecondReading() {
        return this.secondReading;
    }

    public void setSecondReading(Integer secondReading) {
        this.secondReading = secondReading;
    }

    public Date getDateTimeSecondReading() {
        return this.dateTimeSecondReading;
    }

    public void setDateTimeSecondReading(Date dateTimeSecondReading) {
        this.dateTimeSecondReading = dateTimeSecondReading;
    }

    public Integer getNetWeight() {
        return Math.abs(this.firstReading - this.secondReading);
    }

    public void setNetWeight(Integer netWeight) {
        this.netWeight = netWeight;
    }

    public String getCustName() {
        return this.custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustCarNember() {
        return this.custCarNember;
    }

    public void setCustCarNember(String custCarNember) {
        this.custCarNember = custCarNember;
    }

    public String getNote() {
        return this.note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCustCarCity() {
        return this.custCarCity;
    }

    public void setCustCarCity(String custCarCity) {
        this.custCarCity = custCarCity;
    }

    public String getCustPhone() {
        return this.custPhone;
    }

    public void setCustPhone(String custPhone) {
        this.custPhone = custPhone;
    }

    public String getCompany() {
        return this.company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Integer getInvoiceNumber() {
        return this.invoiceNumber;
    }

    public void setInvoiceNumber(Integer invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public Integer getVersion() {
        return this.version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getAgent() {
        return this.agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public String getFoodType() {
        return this.foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    public int getUserid() {
        return this.userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public ObservableList<Scale> getListItems() throws SQLException, IllegalAccessException, InstantiationException, ClassNotFoundException, IOException {
        ObservableList<Scale> list = FXCollections.observableArrayList();
        Statement statement = DBConnection.getConnection().createStatement();
        String sql = "SELECT * FROM weight";
        ResultSet resultSet = statement.executeQuery(sql);

        while(resultSet.next()) {
            if (resultSet.getInt(14) == 1) {
                Integer scaleID = resultSet.getInt(1);
                Integer grossWeight = resultSet.getInt(2);
                Date grossDateAndTime = resultSet.getDate(3);
                Integer tarWeight = resultSet.getInt(4);
                Date tarDateAndTime = resultSet.getDate(5);
                Integer netWeights = resultSet.getInt(6);
                String customerName = resultSet.getString(7);
                String customercCarNumber = resultSet.getString(8);
                String notes = resultSet.getString(9);
                String city = resultSet.getString(10);
                String cellphone = resultSet.getString(11);
                String companyName = resultSet.getString(12);
                int customerId = 0;
                int versionWeight = resultSet.getInt(14);
                int userId = resultSet.getInt(15);
                String agent = resultSet.getString(16);
                String products = resultSet.getString(18);
                String provider = resultSet.getString(17);
                Scale scale = new Scale(scaleID, grossWeight, grossDateAndTime, tarWeight, tarDateAndTime, netWeights, customerName, customercCarNumber, notes, city, cellphone, companyName, Integer.valueOf(customerId), versionWeight, agent, products, userId, provider);
                list.add(scale);
            }
        }

        return list;
    }
}

