import controller.ConfigurationClass;
import controller.DBConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    public static void main(String[] args) throws ClassNotFoundException, IOException, InstantiationException, IllegalAccessException {
        ConfigurationClass.configProperties();

        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("gui/Login.fxml"));
        primaryStage.setTitle("Erbil Feed ");
        primaryStage.setScene(new Scene(root, 650, 300));
        primaryStage.show();
    }
}


