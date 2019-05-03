
package controller;

import entities.Login;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBConnection {
    Login user = new Login();

    public DBConnection() {
    }

    public static Connection getConnection() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class.forName("org.h2.Driver");

        try {
            Connection connection = DriverManager.getConnection("jdbc:h2:~/data/erbilfeed");
            return connection;
        } catch (SQLException var1) {
            return null;
        }
    }


    public boolean isLogin(String username, String password) throws IllegalAccessException, InstantiationException, ClassNotFoundException, SQLException, IOException {
        boolean var4;
        try {
            Statement statement = getConnection().createStatement();
            String sql = "SELECT * FROM USER";
            ResultSet resultSet = statement.executeQuery(sql);

            do {
                if (!resultSet.next()) {
                    return false;
                }
            } while(!resultSet.getString(2).equals(username) || !resultSet.getString(3).equals(password));

            int access = resultSet.getInt(5);
            String fullName = resultSet.getString(4);
            String accupation = resultSet.getString(6);
            Integer uID = resultSet.getInt(1);
            String pass = resultSet.getString(3);
            this.user.setPassword(pass);
            this.user.setAccisability(access);
            Login.setFullName(fullName);
            Login.setAccupation(accupation);
            this.user.setUserid(uID);
            boolean var11 = true;
            return var11;
        } catch (SQLException var15) {
            var15.printStackTrace();
            var4 = false;
        } finally {
            getConnection().close();
        }

        return var4;
    }

    public static void setResult(ResultSet resultSet) throws SQLException {
        StringBuffer stringBuffer = new StringBuffer();

        while(resultSet.next()) {
            stringBuffer.append(resultSet.getInt(1));
            stringBuffer.append(resultSet.getInt(7));
            stringBuffer.toString();
            System.out.println(stringBuffer);
        }

    }

    public static Integer createID(String tableName, int column) throws IllegalAccessException, InstantiationException, ClassNotFoundException, SQLException, IOException {
        int max = 0;

        try {
            Statement statement = getConnection().createStatement();
            String sql = "SELECT * FROM " + tableName;
            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next()) {
                if (resultSet.getInt(column) > max) {
                    max = resultSet.getInt(column);
                }
            }
        } catch (SQLException var9) {
            var9.printStackTrace();
        } finally {
            getConnection().close();
        }

        return max + 1;
    }

    public List<String> findAllEmployee() throws IllegalAccessException, InstantiationException, ClassNotFoundException, SQLException, IOException {
        List<String> users = new ArrayList();
        Statement statement = getConnection().createStatement();
        String sql = "SELECT * FROM USER";
        ResultSet resultSet = statement.executeQuery(sql);

        while(resultSet.next()) {
            String userId = resultSet.getString(2);
            String fullName = resultSet.getString(4);
            String role = resultSet.getString(6);
            users.add(fullName + " | " + userId + " | " + role);
        }

        return users;
    }

    public Integer findByInfo(Integer value) throws IllegalAccessException, InstantiationException, ClassNotFoundException, SQLException, IOException {
        String query = "SELECT SCALE_ID FROM WEIGHT WHERE INVOICE_NUMBER =" + value + " ;";
        Statement statement = getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        resultSet.next();
        Integer id = resultSet.getInt(1);
        return id;
    }

    public Integer checkInvoiceValidation(Integer integer) throws IllegalAccessException, InstantiationException, ClassNotFoundException, SQLException {
        String q = "SELECT SCALE_ID FROM WEIGHT WHERE INVOICE_NUMBER =" + integer + " ;";
        Statement statement = getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(q);
        resultSet.next();
        Integer invoice = resultSet.getInt("INVOICE_NUMBER");
        if (integer.toString().isEmpty()) {
            return -1;
        } else {
            return integer == 0 ? 0 : invoice;
        }
    }
}

