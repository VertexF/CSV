package temp;

import java.io.File;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Temp extends Application{
    
    @Override
    public void start(Stage stage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLTemp.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("file:./src/temp/TempTheme.css");
        stage.setScene(scene);
        stage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
