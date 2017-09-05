import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class Settings_tabletController implements Initializable {
    @FXML
    private ToggleButton alertsButton;
    @FXML
    private ToggleButton tipsButton;
    @FXML
    private AnchorPane backgroundPane;
    @FXML
    private TextField s_locationTextField;
    
    private String location;

    @FXML
    private Label toggleTips;
    @FXML
    private Label toggleAlerts;
    @FXML
    private Label locationSettings;
    @FXML
    private Button cancelButton;
    @FXML
    private Button saveButton;
    
    private boolean valid=true;
    private boolean alerton_temp;
    private boolean tipson_temp;
    private String location_temp;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (Start.settings.isAlertOn()){ 
            alerton_temp = true;
            alertsButton.setSelected(true);
        }
        else
        {
            alertsButton.setSelected(false);
            alerton_temp = false;
        }
        if (Start.settings.isTipOn()){ 
            tipsButton.setSelected(true);
            tipson_temp = true;
        }
        else
        {
            tipsButton.setSelected(false);
            tipson_temp = false;
        }
        location_temp = Start.settings.getLocationName();
        s_locationTextField.setPromptText(location_temp);
    } 
    
    @FXML
    private void changeAlertsVisibility(ActionEvent event) {
    if (alerton_temp){ 
       alerton_temp = false;
        alertsButton.setSelected(false);
    }
    else {
        alerton_temp = true;
        alertsButton.setSelected(true);
    }
    }
    
    @FXML
    private void changeTipsVisibility(ActionEvent event) {
    if (tipson_temp){ 
        tipson_temp = false;
        tipsButton.setSelected(false);
    }
    else {
        tipson_temp = true;
        tipsButton.setSelected(true);
    }
    }

    @FXML
    private void cancel(ActionEvent event) throws IOException {
    Stage stage; 
    Parent root;        
    stage=(Stage) saveButton.getScene().getWindow();
        
    root = FXMLLoader.load(getClass().getResource("main_tablet.fxml"));
      
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
    }

    
    
    @FXML
    private void save(ActionEvent event) throws IOException{
        
    if (!s_locationTextField.getText().isEmpty()){
        
            String newLocation = model.Location.locationResolver(s_locationTextField.getText());
            if (newLocation.equalsIgnoreCase("Bad request")) {
                s_locationTextField.clear();
                s_locationTextField.setPromptText("Try different place");
                valid=false;
                
            }
            else {
            location_temp = (s_locationTextField.getText()).replace(" ", "-");
            Start.settings.setLocationName(location_temp); }
        
    }
    
    if (valid){
    Start.settings.setAlertOn(alerton_temp);
    Start.settings.setTipOn(tipson_temp);
       
    Start.settings.saveSettings();
    Start.reloadSettings();
    
    Stage stage; 
    Parent root;        
    stage=(Stage) saveButton.getScene().getWindow();
        
    root = FXMLLoader.load(getClass().getResource("main_tablet.fxml"));
      
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
    }
    }

    @FXML
    private void changeLocation(ActionEvent event) {
    if (!s_locationTextField.getText().isEmpty()){
    location_temp = (s_locationTextField.getText()).replace(" ", "");
    }
        else {
            s_locationTextField.clear();
            s_locationTextField.setPromptText("Try different place");
        }    
    }
    
}
