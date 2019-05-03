
package guicontrollers;

import entities.Login;
import controller.DBConnection;
import guicontrollers.AlertBox;
import java.net.URL;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EmployeeControlPanel implements Initializable {
    public TextField userName;
    public PasswordField password;
    public PasswordField conformPassword;
    public TextField firstName;
    public TextField lastName;
    public TextField jobTitle;
    public Button add;
    public CheckBox check;
    public Button exit;
    public ListView<String> employeeList;
    Login user = new Login();
    DBConnection dbcOnnection = new DBConnection();

    public EmployeeControlPanel() {
    }

    public void addUser(ActionEvent event) throws Exception {
        this.dbcOnnection.findAllEmployee();
        int adminPrivilage = 2;
        if (this.check.isSelected()) {
            adminPrivilage = 1;
        }

        if (!this.userName.getText().isEmpty() && !this.password.getText().isEmpty() && !this.firstName.getText().isEmpty() && !this.lastName.getText().isEmpty() && !this.jobTitle.getText().isEmpty()) {
            if (this.password.getText().equals(this.conformPassword.getText())) {
                String sql1 = "INSERT INTO USER (username , password , full_name, role, accupation) VALUES ( '" + this.userName.getText().trim() + "','" + this.password.getText().trim() + "','" + this.firstName.getText().trim() + " " + this.lastName.getText().trim() + "', " + adminPrivilage + ",'" + this.jobTitle.getText().trim() + "')";
                System.out.println(sql1);

                try {
                    Statement statement = DBConnection.getConnection().createStatement();
                    statement.execute(sql1);
                    this.userName.clear();
                    this.password.clear();
                    this.firstName.clear();
                    this.lastName.clear();
                    this.jobTitle.clear();
                    this.conformPassword.clear();
                } catch (Exception var8) {
                } finally {
                    DBConnection.getConnection().close();
                    this.setListView();
                }
            } else {
                AlertBox.massage("Password Error", "Passwords don't match! \n Try again");
            }
        } else {
            AlertBox.massage("Employee form", "Some fields are required . \n Please don't leave them empty ");
        }

    }

    public void exit(ActionEvent actionEvent) {
        Stage stage = (Stage)this.exit.getScene().getWindow();
        stage.close();
    }

    public void isChecked(ActionEvent actionEvent) {
    }

    public void setListView() {
        try {
            List<String> list = this.dbcOnnection.findAllEmployee();
            ObservableList<String> listItems = FXCollections.observableArrayList(list);
            this.employeeList.setItems(listItems);
        } catch (Exception var3) {
        }

    }

    public void initialize(URL location, ResourceBundle resources) {
        this.setListView();
    }
}

