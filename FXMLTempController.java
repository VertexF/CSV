package temp;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Dan
 */
public class FXMLTempController implements Initializable {

    @FXML
    private MenuItem open;
    
    
    private FileHandling handle;
    private static Scanner x;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        handle = new FileHandling();
        
        open.setOnAction( e -> 
        {
            readRecord(handle.loadFile(), "1234");
        });
    }
    
    public static void readRecord(File file, String searchTerm)
    {
        boolean found = false;
        String id = "", name = "", age = "";
        
        try
        {
            x = new Scanner(file);
            x.useDelimiter("[,\n]");
            
            while(x.hasNext() && !found)
            {
                id = x.next();
                name = x.next();
                age = x.next();
                
                if(id.equals(searchTerm))
                {
                    found = true;
                }
            }
            
            if(found)
            {
                System.out.println("ID: " + id + " Name: " + name + " Age: " + age);
            }
            else
            {
                System.out.println("Not found!");
            }
        }
        catch(Exception e)
        {
            System.out.println("File not found!");
        }
    }
    
}
