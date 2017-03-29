/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpleloginfx;

import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.logging.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.*;

/**
 * FXML Controller class
 *
 * @author blj0011
 */
public class LoginScreenController implements Initializable
{
    @FXML TextField tfUsername, tfPassword;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    
    private final HashMap<String, String> usersInfo = new HashMap();
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        
    }    
    
    @FXML void handleBTNLogin(ActionEvent event)
    {
        if(tfUsername.getText().length() > 0 && tfPassword.getText().length() > 0)
        {
            Set set = usersInfo.entrySet();
            Iterator iterator = set.iterator();
            while(iterator.hasNext())
            {
                Map.Entry mentry = (Map.Entry)iterator.next();
                System.out.println("key is: "+ mentry.getKey() + " & Value is: " + mentry.getValue());
                if(tfUsername.getText().equals(mentry.getKey()))
                {
                    System.out.println("Username found!");
                    if(tfPassword.getText().equals(mentry.getValue()))
                    {
                        try 
                        {
                            System.out.println("Password correct!");
                            System.out.println("Load next screen!");
                            
                            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainScreen.fxml"));
                            Parent root = (Parent)fxmlLoader.load();
                            Scene scene = new Scene(root);
                            Stage stage = new Stage();
                            stage.setTitle("Main Screen");
                            stage.setScene(scene);
                            stage.show();
                            
                            ((Node)(event.getSource())).getScene().getWindow().hide();
                        }
                        catch (IOException ex) 
                        {
                            Logger.getLogger(LoginScreenController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    else
                    {
                        System.out.println("Password incorrect!");
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Sign In Dialog");
                        alert.setHeaderText("Login Unsuccessful");
                        alert.setContentText("Your username and/or password is incorrect!");
                        alert.showAndWait(); 
                    }
                }               
            }
        }
    }
    
   public void setHashMap(HashMap<String, String> usersInfo)
   {
        this.usersInfo.putAll(usersInfo);         
   }
}
