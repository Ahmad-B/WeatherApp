import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.Tips;
import model.WeatherAPI;

public class MainTabletController implements Initializable {
    
            
    @FXML
    private Pane tipsPane;
    boolean tipsVisible = true;
   
    @FXML
    private Button openTipsButton;
    boolean tipsOpen = false;
    @FXML
    private Label alertLabel; 
    @FXML
    private Label cityLabel;
    @FXML
    private Label descLabel;
    @FXML
    private Label iconLabel;
    @FXML
    private Label currentTempLabel;
    @FXML
    private Label chanceOFRainLabel;
    @FXML
    private Label windspeedLabel;
    @FXML
    private Label visibilityLabel;
    
    @FXML
    private Label day1name;
    @FXML
    private Label day2name;
    @FXML
    private Label day3name;
    @FXML
    private Label day4name;
    @FXML
    private Label day5name;
    
    @FXML
    private Label day1icon;
    @FXML
    private Label day2icon;
    @FXML
    private Label day3icon;
    @FXML
    private Label day4icon;
    @FXML
    private Label day5icon;
   
    @FXML
    private Label day1temp;
    @FXML
    private Label day2temp;
    @FXML
    private Label day3temp;
    @FXML
    private Label day4temp;
     @FXML
    private Label day5temp;
    @FXML
    private ImageView tips;
    @FXML
    private ImageView tips1;
    @FXML
    private AnchorPane backgroundPane;
    @FXML
    private Label actual_tips;

    @FXML
    private Label sunriseLabel;
    @FXML
    private Label sunsetLabel;
  
    @FXML
    private VBox day1box;
    @FXML
    private VBox day2box;
    @FXML
    private VBox day3box;
    @FXML
    private VBox day4box;
    @FXML
    private VBox day5box;
    @FXML
    private ImageView openSettingsButton;
    @FXML
    private Button changeDevice;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    loadBackground();
    loadData();
    alertLabel.setVisible(Start.settings.isAlertOn());
    tipsPane.setVisible(Start.settings.isTipOn());
    openTips();
    openTipsButton.setId("toggle_open");
    
        
//SETTING UP WEATHER ICON FONTS-----------------------------------------------//        
    Font iconFont = Font.loadFont(getClass().getResourceAsStream("/assets/meteocons-webfont.ttf"), 140);
    iconLabel.setFont(iconFont);
    iconLabel.setTextFill(Color.web("#FFFFFF"));
    
    Font iconFont2 = Font.loadFont(getClass().getResourceAsStream("/assets/meteocons-webfont.ttf"), 90);
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
    }
 
//TIPS TOGGLE OPEN-CLOSE------------------------------------------------------//
    @FXML
    private void changeTips(ActionEvent event) {
        if (tipsOpen==false){
        openTips();
        openTipsButton.setId("toggle_open");
        }
    else {
        closeTips();
        openTipsButton.setId("toggle");
        }
    }
    
    private void openTips(){
    Timeline timeline = new Timeline();
    KeyValue kv = new KeyValue(tipsPane.layoutXProperty(), 0, Interpolator.EASE_IN);
    KeyFrame kf = new KeyFrame(Duration.millis(200), kv);
    timeline.getKeyFrames().add(kf);
    timeline.play();
    tipsOpen=true;
    } 
    private void closeTips(){
    Timeline timeline = new Timeline();
    KeyValue kv = new KeyValue(tipsPane.layoutXProperty(), 393, Interpolator.EASE_IN);
    KeyFrame kf = new KeyFrame(Duration.millis(200), kv);
    timeline.getKeyFrames().add(kf);
    timeline.play();
    tipsOpen=false;
    } 
//NAVIGATION------------------------------------------------------------------//
    @FXML
    private void openSettings (MouseEvent event) throws IOException {Stage stage; 
    Parent root;        
    stage=(Stage) openSettingsButton.getScene().getWindow();    
    root = FXMLLoader.load(getClass().getResource("settings_tablet.fxml"));
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
    }
//BACKGROUND CHANGER----------------------------------------------------------//    
    private void loadBackground()
    {   
        WeatherAPI weather = new WeatherAPI(model.Location.locationResolver(Start.settings.getLocationName()));
        String condition = model.WeatherAPI.weatherInfoList.get(0).condition;
        if (condition.contains("Rain")){
            backgroundPane.setStyle("-fx-background-image: url('assets/raining.png')");   
         } else if (condition.contains("Cloudy")){
            backgroundPane.setStyle("-fx-background-image: url('assets/cloudy.png')");    
         } else if (condition.contains("Fog")){
            backgroundPane.setStyle("-fx-background-image: url('assets/foggy.png')");   
         } else if (condition.contains("Snow")){
            backgroundPane.setStyle("-fx-background-image: url('assets/snowing.png')");  
         } else if (condition.contains("Sunny")) {
            backgroundPane.setStyle("-fx-background-image: url('assets/sunny.png')");
         } else {
            backgroundPane.setStyle("-fx-background-image: url('assets/foggy.png')");
        }
    }
    
    //API---------------------------------------------------------------------//
    private void loadData(){
    model.WeatherAPI weather = new WeatherAPI(model.Location.locationResolver(Start.settings.getLocationName()));
    Tips weatherTips = new Tips();
    String temp = model.WeatherAPI.weatherInfoList.get(0).currentTemp;
    String condition = model.WeatherAPI.weatherInfoList.get(0).condition;
    String windspeed = model.WeatherAPI.weatherInfoList.get(0).windSpeed;
    String visibility = model.WeatherAPI.weatherInfoList.get(0).visibility;
    String sunrise = model.WeatherAPI.weatherInfoList.get(0).sunRise;
    String sunset = model.WeatherAPI.weatherInfoList.get(0).sunSet;
    int humidity = Integer.parseInt(model.WeatherAPI.weatherInfoList.get(0).humidity);
    actual_tips.setText(weatherTips.getTip(condition));
    if (condition.contains("rain"))
    {
        tips1.setVisible(false);
        tips.setVisible(true);
    }
    else
    {
        tips.setVisible(false);
        tips1.setVisible(true);
    }
       currentTempLabel.setText(temp +"°");
       cityLabel.setText(Start.settings.getLocationName());
       descLabel.setText(condition);
       windspeedLabel.setText("Wind Speed: " + windspeed + " km/h");
       visibilityLabel.setText("Visibility: " + visibility +" mi");
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
           if(!(condition.contains("rain"))) {
               actual_tips.setText(weatherTips.getTip("chance"));
               tips1.setVisible(false);
               tips.setVisible(true);
           }
       }
       
       setIcon(iconLabel, condition);
       
              
       day1name.setText(WeatherAPI.weatherForecastList.get(0).day);
       day1temp.setText("Low: " + WeatherAPI.weatherForecastList.get(0).lowTemp + 
               " High: " + WeatherAPI.weatherForecastList.get(0).highTemp);
       setIcon(day1icon, WeatherAPI.weatherForecastList.get(0).condition);
       setColour(day1box, WeatherAPI.weatherForecastList.get(0).condition);
       
       day2name.setText(WeatherAPI.weatherForecastList.get(1).day);
       day2temp.setText("Low: " + WeatherAPI.weatherForecastList.get(1).lowTemp + 
               " High: " + WeatherAPI.weatherForecastList.get(1).highTemp);
       setIcon(day2icon, WeatherAPI.weatherForecastList.get(1).condition);
       setColour(day2box, WeatherAPI.weatherForecastList.get(1).condition);
       
       day3name.setText(WeatherAPI.weatherForecastList.get(2).day);
       day3temp.setText("Low: " + WeatherAPI.weatherForecastList.get(2).lowTemp + 
               " High: " + WeatherAPI.weatherForecastList.get(2).highTemp);
       setIcon(day3icon, WeatherAPI.weatherForecastList.get(2).condition);
       setColour(day3box, WeatherAPI.weatherForecastList.get(2).condition);
       
       day4name.setText(WeatherAPI.weatherForecastList.get(3).day);
       day4temp.setText("Low: " + WeatherAPI.weatherForecastList.get(3).lowTemp + 
               " High: " + WeatherAPI.weatherForecastList.get(3).highTemp);
       setIcon(day4icon, WeatherAPI.weatherForecastList.get(3).condition);
       setColour(day4box, WeatherAPI.weatherForecastList.get(3).condition);
       
       day5name.setText(WeatherAPI.weatherForecastList.get(4).day);
       day5temp.setText("Low: " + WeatherAPI.weatherForecastList.get(4).lowTemp + 
               " High: " + WeatherAPI.weatherForecastList.get(4).highTemp);
       setIcon(day5icon, WeatherAPI.weatherForecastList.get(4).condition);
       setColour(day5box, WeatherAPI.weatherForecastList.get(4).condition);   
    }
    
    //HELPER METHODS FOR API - SET ICON, SET BG COLOUR FOR NEXT DAYS FORECASTS//
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
    
    private static void setColour(VBox vbox, String condition){
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
    private void changeDeviceScene(ActionEvent event) throws IOException {
    Stage stage; 
    Parent root;        
    stage=(Stage) day1icon.getScene().getWindow();
        
    root = FXMLLoader.load(getClass().getResource("main_phone.fxml"));
      
    Scene scene = new Scene(root);
    stage.setTitle("Cycling Sun - Smartphone");
    stage.setScene(scene);
    stage.show();
    }

}
