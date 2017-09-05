/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Tips;
import model.WeatherAPI;

/**
 * FXML Controller class
 *
 * @author nicks
 */
public class Tips_phoneController implements Initializable {

    @FXML
    private ImageView backgroundPane;
    @FXML
    private Button s_HomeButton;
    @FXML
    private Label tips;
    @FXML
    private ImageView tips2;
    @FXML
    private ImageView tips1;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    model.WeatherAPI weather = new WeatherAPI(model.Location.locationResolver(Start.settings.getLocationName()));
    model.Tips weatherTips = new Tips();
    String condition = model.WeatherAPI.weatherInfoList.get(0).condition;     
    tips.setText(weatherTips.getTip(condition));
              if (condition.contains("rain"))
            {
                tips2.setVisible(true);
                tips1.setVisible(false);
            }
              else
              {
                tips2.setVisible(false);
                tips1.setVisible(true);
              }
    }

    @FXML
    private void gotoHome(ActionEvent event) {
    Stage stage; 
    Parent root;        
    stage=(Stage) tips.getScene().getWindow();
    try{
    root = FXMLLoader.load(getClass().getResource("main_phone.fxml"));
        Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
    }
    catch(IOException e){
        System.out.println("Could not load main phone screen");
    }

    }
    
}
