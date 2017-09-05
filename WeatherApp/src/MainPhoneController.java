/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.WeatherAPI;

/**
 * FXML Controller class
 *
 * @author laszlojmoricz
 */
public class MainPhoneController implements Initializable {
    
    @FXML
    private Pane settingsPane;
    @FXML
    private Button s_alertsButton;
    @FXML
    private Button s_tipsButton;
    @FXML
    private Button s_backButton;
    @FXML
    private Button s_locationButton;
    @FXML
    private Label s_settingsLabel;
    @FXML
    private TextField s_locationTextField;
    @FXML
    private ImageView backgroundPane;
    
    //Labels for the main area of the GUI
    @FXML
    private Label iconLabel;
    @FXML
    private Label chanceOFRainLabel;
    @FXML
    private Label windspeedLabel;
    @FXML
    private Label visibilityLabel;
    @FXML
    private Label sunriseLabel;
    @FXML
    private Label currentTempLabel;
    @FXML
    private Label sunsetLabel;
    @FXML
    private Label descLabel;
    @FXML
    private Label alertLabel;
    @FXML
    private Label cityLabel;

    @FXML
    private Button settingsButton;
    @FXML
    private Button tipsButton;
    
    //Day 1 labels
    @FXML
    private Label day1name;
    @FXML
    private Label day1icon;
    @FXML
    private Label day1temp;
    
    //Day 2 labels
    @FXML
    private Label day2name;
    @FXML
    private Label day2icon;
    @FXML
    private Label day2temp;
    
    //Day 3 labels
    @FXML
    private Label day3name;
    @FXML
    private Label day3icon;
    @FXML
    private Label day3temp;
    
    //Day 4 labels
    @FXML
    private Label day4name;
    @FXML
    private Label day4icon;
    @FXML
    private Label day4temp;
    
    //Day 5 labels
    @FXML
    private Label day5name;
    @FXML
    private Label day5icon;
    @FXML
    private Label day5temp;
    
    //Daily weather Panes
    @FXML AnchorPane day1box;
    @FXML AnchorPane day2box;
    @FXML AnchorPane day3box;
    @FXML AnchorPane day4box;
    @FXML AnchorPane day5box;
    @FXML
    private Button changeDevice;

    @FXML
    void goToTips (ActionEvent event) {
    Parent root;
    Stage stage;
    stage=(Stage) day5temp.getScene().getWindow();    
    try{
    root = FXMLLoader.load(getClass().getResource("tips_phone.fxml"));
    
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
    } catch (Exception e) {System.out.println("No success");}
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadBackground();
        loadData();
        alertLabel.setVisible(Start.settings.isAlertOn());
        tipsButton.setVisible(Start.settings.isTipOn());
        s_locationTextField.setText(Start.settings.getLocationName());
        
        Font iconFont = Font.loadFont(getClass().getResourceAsStream("/assets/meteocons-webfont.ttf"), 45);
        iconLabel.setFont(iconFont);
        iconLabel.setTextFill(Color.web("#FFFFFF"));
        
        Font iconFont2 = Font.loadFont(getClass().getResourceAsStream("/assets/meteocons-webfont.ttf"), 25);
        day1icon.setFont(iconFont2);
        day1icon.setTextFill(Color.web("#FFFFFF"));
        day2icon.setFont(iconFont2);
        day2icon.setTextFill(Color.web("#FFFFFF"));
        day3icon.setFont(iconFont2);
        day3icon.setTextFill(Color.web("#FFFFFF"));
        day4icon.setFont(iconFont2);
        day4icon.setTextFill(Color.web("#FFFFFF"));
        day5icon.setFont(iconFont2);
        day5icon.setTextFill(Color.web("#FFFFFF"));
        
        //INITIALIZING SETTINGS PAGE NODE VALUES---------------------------------------//
        settingsPane.setVisible(false);
        
        if (Start.settings.isAlertOn()) s_alertsButton.setStyle("-fx-graphic: url(assets/toggleonphone_1.png)");
        else s_alertsButton.setStyle("-fx-graphic: url(assets/toggleoffphone_1.png)");
    
        if (Start.settings.isTipOn()) s_tipsButton.setStyle("-fx-graphic: url(assets/toggleonphone_1.png)");
        else s_tipsButton.setStyle("-fx-graphic: url(assets/toggleoffphone_1.png)");
        
        s_locationTextField.clear();
        s_locationTextField.setPromptText(Start.settings.getLocationName());
    }
    
    @FXML
    private void backToMain(ActionEvent event) {
        settingsPane.setVisible(false);   
    }
    
    @FXML
    void goToSettings(ActionEvent event) {
        settingsPane.setVisible(true);
    }
    
    @FXML
    private void changeAlertsVisibility(ActionEvent event) {
        if (Start.settings.isAlertOn()){ 
            Start.settings.setAlertOn(false);
            s_alertsButton.setStyle("-fx-graphic: url(assets/toggleoffphone_1.png)");
        }
        else {
            Start.settings.setAlertOn(true);
            s_alertsButton.setStyle("-fx-graphic: url(assets/toggleonphone_1.png)");
        }
            alertLabel.setVisible(Start.settings.isAlertOn());
            Start.settings.saveSettings();
    }
    
        @FXML
    private void changeTipsVisibility(ActionEvent event) {
    if (Start.settings.isTipOn()){ 
        Start.settings.setTipOn(false);
        s_tipsButton.setStyle("-fx-graphic: url(assets/toggleoffphone_1.png)");
    }
    else {
        Start.settings.setTipOn(true);
        s_tipsButton.setStyle("-fx-graphic: url(assets/toggleonphone_1.png)");
    }
        tipsButton.setVisible(Start.settings.isTipOn());
        Start.settings.saveSettings();
    }
    
    @FXML
    private void changeLocation(ActionEvent event) {
        if (!s_locationTextField.getText().isEmpty()){
        Start.settings.setLocationName(s_locationTextField.getText());
        Start.settings.saveSettings();
        s_locationTextField.clear();
        settingsPane.requestFocus();
        s_locationTextField.setPromptText(Start.settings.getLocationName());
        loadData();}
        else {
            s_locationTextField.clear();
            s_locationTextField.setPromptText("Type a valid location");
        }
    }
    
        private void loadBackground()
    {   
        WeatherAPI weather = new WeatherAPI(model.Location.locationResolver(Start.settings.getLocationName()));
        String condition = model.WeatherAPI.weatherInfoList.get(0).condition;            
        if (condition.contains("Rain")){
            backgroundPane.setImage(new Image ("assets/raining_phone.png"));   
         } else if (condition.contains("Cloudy")){
            backgroundPane.setImage(new Image ("assets/cloudy_phone.png"));  
         } else if (condition.contains("Foggy")){
            backgroundPane.setImage(new Image ("assets/foggy_phone.png"));    
         } else if (condition.contains("Snow")){
            backgroundPane.setImage(new Image ("assets/snowing_phone.png"));  
         } else if (condition.contains("Sunny")) {
            backgroundPane.setImage(new Image ("assets/sunny_phone.png")); 
         } else {
           backgroundPane.setImage(new Image ("assets/Cycling-New-Forest_phone.png"));
         }
    }

    private void loadData(){
    WeatherAPI weather = new WeatherAPI(model.Location.locationResolver(Start.settings.getLocationName()));
    String temp = model.WeatherAPI.weatherInfoList.get(0).currentTemp;
    String condition = model.WeatherAPI.weatherInfoList.get(0).condition;
    String windspeed = model.WeatherAPI.weatherInfoList.get(0).windSpeed;
    String visibility = model.WeatherAPI.weatherInfoList.get(0).visibility;
    String sunrise = model.WeatherAPI.weatherInfoList.get(0).sunRise;
    String sunset = model.WeatherAPI.weatherInfoList.get(0).sunSet;
    int humidity = Integer.parseInt(model.WeatherAPI.weatherInfoList.get(0).humidity);
       
       currentTempLabel.setText(temp + "°");
       cityLabel.setText(Start.settings.getLocationName());
       descLabel.setText(condition);
       windspeedLabel.setText("Wind Speed: " + windspeed + " km/h");
       visibilityLabel.setText("Visibility: " + visibility + " mi");
       sunriseLabel.setText(sunrise);
       sunsetLabel.setText(sunset);
       if(humidity <= 30) {
           chanceOFRainLabel.setText("Chance of Rain: Low");
           alertLabel.setText("DRY RIDE");
           
       }
       else if(humidity >= 31 & humidity <= 70) {
           chanceOFRainLabel.setText("Chance of Rain: Medium");
           alertLabel.setText("MIGHT GET WET");
           alertLabel.setStyle("-fx-background-color: linear-gradient(orange, gold);");
       }
       else if(humidity >= 71) {
           chanceOFRainLabel.setText("Chance of Rain: High");
           alertLabel.setText("ONLY FOR THE BRAVE");
           alertLabel.setStyle("-fx-background-color: linear-gradient(red, firebrick);");
       }
       
       setIcon(iconLabel, condition);
              
       day1name.setText(WeatherAPI.weatherForecastList.get(0).day);
       day2name.setText(WeatherAPI.weatherForecastList.get(1).day);
       day3name.setText(WeatherAPI.weatherForecastList.get(2).day);
       day4name.setText(WeatherAPI.weatherForecastList.get(3).day);
       day5name.setText(WeatherAPI.weatherForecastList.get(4).day);
       
       day1temp.setText("Low: " + WeatherAPI.weatherForecastList.get(0).lowTemp + 
               "\nHigh: " + WeatherAPI.weatherForecastList.get(0).highTemp);
       setIcon(day1icon, WeatherAPI.weatherForecastList.get(0).condition);
       setColour(day1box, WeatherAPI.weatherForecastList.get(0).condition);
       
       day2temp.setText("Low: " + WeatherAPI.weatherForecastList.get(1).lowTemp + 
               "\nHigh: " + WeatherAPI.weatherForecastList.get(1).highTemp);
       setIcon(day2icon, WeatherAPI.weatherForecastList.get(1).condition);
       setColour(day2box, WeatherAPI.weatherForecastList.get(1).condition);
       
       day3temp.setText("Low: " + WeatherAPI.weatherForecastList.get(2).lowTemp + 
               "\nHigh: " + WeatherAPI.weatherForecastList.get(2).highTemp);
       setIcon(day3icon, WeatherAPI.weatherForecastList.get(2).condition);
       setColour(day3box, WeatherAPI.weatherForecastList.get(2).condition);
       
       day4temp.setText("Low: " + WeatherAPI.weatherForecastList.get(3).lowTemp + 
               "\nHigh: " + WeatherAPI.weatherForecastList.get(3).highTemp);
       setIcon(day4icon, WeatherAPI.weatherForecastList.get(3).condition);
       setColour(day4box, WeatherAPI.weatherForecastList.get(3).condition);       

       day5temp.setText("Low: " + WeatherAPI.weatherForecastList.get(4).lowTemp + 
               "\nHigh: " + WeatherAPI.weatherForecastList.get(4).highTemp);
       setIcon(day5icon, WeatherAPI.weatherForecastList.get(4).condition);
       setColour(day5box, WeatherAPI.weatherForecastList.get(4).condition);    
      
    }
    
        private static void setIcon(Label dayicon, String condition){
        
        if(condition.toLowerCase().contains("rain") || 
           condition.toLowerCase().contains("shower")) {
           dayicon.setText("R");
           }
        else if(condition.toLowerCase().contains("sun")) {
            dayicon.setText("B");
            }
        else if(condition.toLowerCase().contains("cloud")) {
            dayicon.setText("N");
            }
        else if(condition.toLowerCase().contains("thunder") || 
            condition.toLowerCase().contains("storm")) {
            dayicon.setText("P");
            }
        else if(condition.toLowerCase().contains("snow")) {
            dayicon.setText("W");
            }
       }
    
    private static void setColour(AnchorPane vbox, String condition){
           if(condition.toLowerCase().contains("rain") || 
           condition.toLowerCase().contains("shower")) {
           vbox.setStyle("-fx-background-color: linear-gradient(orange, gold);");
           }
        else if(condition.toLowerCase().contains("sun")) {
            vbox.setStyle("-fx-background-color: linear-gradient(darkolivegreen, green);");
            }
        else if(condition.toLowerCase().contains("cloud")) {
            vbox.setStyle("-fx-background-color: linear-gradient(darkolivegreen, green);");
            }
        else if(condition.toLowerCase().contains("thunder") || 
            condition.toLowerCase().contains("storm")) {
            vbox.setStyle("-fx-background-color: linear-gradient(red, firebrick);");
            }
        else if(condition.toLowerCase().contains("snow")) {
            vbox.setStyle("-fx-background-color: linear-gradient(red, firebrick);");
            }
       }

    @FXML
    private void changeDeviceScene(ActionEvent event) {
        try{
            Stage stage; 
    Parent root;        
    stage=(Stage) iconLabel.getScene().getWindow();
        
    root = FXMLLoader.load(getClass().getResource("main_tablet.fxml"));
      
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.setTitle("Cycling Sun - Tablet");
    stage.show();
        }
        catch(Exception e)
        {
            System.out.println("Error changing device");
        }
    }
    
}
