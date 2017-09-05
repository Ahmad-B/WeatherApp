/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author laszlojmoricz
 */
public class SettingsPhoneController implements Initializable {
    
    @FXML
    private Button settingsBackButton;
    @FXML
    private Button togglePhoneTips;
    @FXML
    private Button togglePhoneAlerts;
    @FXML
    private Button settingsToTipsButton;
    
    @FXML
    private TextField newLocationText;

    @FXML
    void settingsGoBack(ActionEvent event) {
        //Start.openMain();
    }
    
    @FXML
    void settingsToTips(ActionEvent event) {
        Start.openTips();
    }
    
    @FXML
    void togglePhoneAlrts(ActionEvent event) {
        
    }

    @FXML
    void togglePhoneTps(ActionEvent event) {
        
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
