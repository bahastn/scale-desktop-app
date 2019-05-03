

package guicontrollers;

import controller.DBConnection;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DBControl {
    public Button deleteButton;
    public TextField command;
    public TextField password;

    public DBControl() {
    }

    public void deleteAll(ActionEvent event) throws IllegalAccessException, InstantiationException, ClassNotFoundException, SQLException {
        Statement statement = DBConnection.getConnection().createStatement();
        String query = this.command.getText().trim();
        if (this.password.getText().trim().equals("QMWA-ASDE-HG72-SX23")) {
            statement.execute(query);
            Stage stage = (Stage)this.deleteButton.getScene().getWindow();
            stage.close();
        }

    }
}

