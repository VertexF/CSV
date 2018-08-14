package temp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Temp extends Application{
    
    @Override
    public void start(Stage stage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLTemp.fxml"));
        
        try
        {   
            Image icon = new Image("file:./res/today.png");
            stage.getIcons().add(icon);
        }
        catch(Exception e)
        {
            System.out.println("Image failed to load!");
        }
        
        stage.setTitle("Job Centre - Universal Credit");
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("file:./src/temp/TempTheme.css");
        stage.setScene(scene);
        stage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
