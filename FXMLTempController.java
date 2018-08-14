package temp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Dan
 */
public class FXMLTempController implements Initializable {

    @FXML
    private MenuItem open;
    @FXML
    private MenuItem save;
    @FXML
    private TableColumn<ObservableList<String>, String> IDCol;
    @FXML
    private TableColumn<ObservableList<String>, String> names;
    @FXML
    private TableColumn<ObservableList<String>, String> ageCol;
    
    @FXML
    private TableView tableView;
    
    @FXML
    private WebView webView;
    
    private final ObservableList<ObservableList<String>> items = FXCollections.observableArrayList();
    private FileHandling handle;
    private Scanner x;
    private static String filePath;
    
    private WebEngine engine;
    private String hyperTextLink = "http://";
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        handle = new FileHandling();
        
        engine = webView.getEngine();
        File f = new File("test.html");
        engine.load(f.toURI().toString());
        
        save.setOnAction( e ->
        {
            try
            {
                FileWriter fw = new FileWriter(filePath, false);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter pw = new PrintWriter(bw);
                
                pw.println();
                for(int i = 0; i < items.size(); i++)
                {
                    for(int j = 0; j < items.get(i).size(); j++)
                    {
                        pw.print(items.get(i).get(j));
                        
                        if(j == items.get(i).size() - 1)
                        {
                            
                        }
                        else
                        {
                            pw.print(",");
                        }
                    }
                    
                    pw.println();
                }

                pw.flush();
                pw.close();
            }
            catch(Exception excep)
            {
                System.out.println("File could not save.");
            }

        });
        
        IDCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList<String>, String>, ObservableValue<String>>()
        {         
            @Override
            public ObservableValue<String> call(CellDataFeatures<ObservableList<String>, String> cdf)
            {
                if(cdf.getValue().size() == 0)
                {
                    return null;
                }
                return new SimpleStringProperty(cdf.getValue().get(0));
            }
        });
        
        names.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList<String>, String>, ObservableValue<String>>()
        {         
            @Override
            public ObservableValue<String> call(CellDataFeatures<ObservableList<String>, String> cdf)
            {     
                if(cdf.getValue().size() == 0)
                {
                    return null;
                }
                return new SimpleStringProperty(cdf.getValue().get(1));
            }
        });
        
        ageCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList<String>, String>, ObservableValue<String>>()
        {         
            @Override
            public ObservableValue<String> call(CellDataFeatures<ObservableList<String>, String> cdf)
            {     
                if(cdf.getValue().size() == 0)
                {
                    return null;
                }
                return new SimpleStringProperty(cdf.getValue().get(2));
            }
        });
        
        open.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event)
        {
            FileHandling ooo = new FileHandling();
            File fil = ooo.loadFile();
            try
            {
                filePath = fil.getCanonicalPath();
            }
            catch(Exception e)
            {
                System.out.println("Could not load file");
            }
            
            final Service<ObservableList<ObservableList<String>>> service = new Service<ObservableList<ObservableList<String>>>()
            {                 
                @Override
                protected Task<ObservableList<ObservableList<String>>> createTask()
                {
                    return new Task<ObservableList<ObservableList<String>>>()
                    {
                        @Override
                        protected ObservableList<ObservableList<String>> call() throws Exception
                        {
                            
                            FileReader input = new FileReader(fil.getCanonicalFile());
                            BufferedReader lineReader = new BufferedReader(input);
                            
                            String line;

                            while((line = lineReader.readLine()) != null)
                            {
                                ObservableList<String> item = FXCollections.observableArrayList();
                                Scanner scanner = new Scanner(line);
                                scanner.useDelimiter(",");
                                while(scanner.hasNext())
                                {
                                    String temp = scanner.next();
                                    item.add(temp);
                                }
                                
                                items.add(item);
                                
                            }
                            
                            lineReader.close();
                            input.close();     
                            
                            return items;
                        }
                    };
                }
            };

            tableView.itemsProperty().bind(service.valueProperty());

            service.start();
        }
    });
    }
    
}
