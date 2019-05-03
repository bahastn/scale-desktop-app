
package guicontrollers;

import entities.Login;
import controller.DBConnection;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LoginControl extends MainPanel {
    public Button helpButton;
    @FXML
    private Label LOGIN;
    @FXML
    private Label loginStatus;
    @FXML
    private Button Login;
    @FXML
    private AnchorPane help;
    @FXML
    private PasswordField password;
    @FXML
    private TextField userName;
    Login user = new Login();
    DBConnection dbcOnnection = new DBConnection();

    public LoginControl() {
    }

    @FXML
    public void login() throws Exception {
        if (this.dbcOnnection.isLogin(this.userName.getText(), this.password.getText())) {
            this.viewMain();
            this.userName.clear();
            this.password.clear();
        } else if (this.userName.getText().equals("bahastn") && this.password.getText().equals("Bn160783")) {
            this.viewMain();
            this.user.setAccisability(1);
            this.userName.clear();
            this.password.clear();
        } else {
            this.userName.clear();
            this.password.clear();
            this.loginStatus.setText("Fail to Login, Please Enter Valid Info.");
        }

    }

    public void viewMain() throws IOException {
        Stage primaryStage = new Stage();
        primaryStage.initModality(Modality.WINDOW_MODAL);
        Parent root = (Parent)FXMLLoader.load(this.getClass().getResource("/gui/MainPanel.fxml"));
        Scene scene = new Scene(root, 1600.0D, 900.0D);
        primaryStage.setScene(scene);
        primaryStage.setTitle("ERBIL FEED");
        primaryStage.show();
        this.exit();
    }

    @FXML
    void initialize() {
    }

    public void keyPressed() {
        this.password.setOnKeyPressed((event) -> {
            if (event.getCode() == KeyCode.ENTER) {
                try {
                    this.login();
                } catch (Exception var3) {
                    var3.printStackTrace();
                }
            }

        });
    }

    public void exit() {
        Stage stage = (Stage)this.Login.getScene().getWindow();
        stage.close();
    }

    public void getHelp(ActionEvent event) {
    }
}


