package temp;

import java.io.File;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Temp {
    
    private static Scanner x;
    public static void main(String[] args) {
        String filePath = "data.txt";
        String searchTerm = "1234";
        
        readRecord(filePath, searchTerm);
    }
    
    public static void readRecord(String searchTerm, String filePath)
    {
        boolean found = false;
        String id = "", name = "", age = "";
        
        try
        {
            x = new Scanner(new File("data.txt"));
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
                JOptionPane.showMessageDialog(null, "ID: " + id + " Name: " + name + " Age: " + age);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Not found!");
            }
        }
        catch(Exception e)
        {
            System.out.println("File not found!");
        }
    }
    
}
