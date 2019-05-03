
package guicontrollers;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class About {
    public Button exit;

    public About() {
    }

    public void closePanel(ActionEvent event) {
        Stage stage = (Stage)this.exit.getScene().getWindow();
        stage.close();
    }

}

