
package guicontrollers;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox {
    public AlertBox() {
    }

    public static void massage(String title, String massage) throws Exception {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(title);
        Label label = new Label();
        label.setText(massage);
        Button close = new Button("Close");
        close.setOnAction((e) -> {
            stage.close();
        });
        VBox panal = new VBox(20.0D);
        panal.getChildren().addAll(label, close);
        panal.setMinWidth(300.0D);
        panal.setMinHeight(200.0D);
        panal.setAlignment(Pos.CENTER);
        Scene scene = new Scene(panal);
        stage.setScene(scene);
        stage.show();
    }

}

