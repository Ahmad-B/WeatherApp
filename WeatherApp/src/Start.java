import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.*;
import model.Settings;

public class Start extends Application{
    
    public static Settings settings;
    private static boolean tablet = true;   //Tells which device has to be shown
    
    private static Stage stage;             //Primary stage - so it can be changed after start run
    private static Scene tabletMainScene;         //Scenes - so they can be changed after start run
    private static Scene phoneMainScene;
    private static Scene tabletSettingsScene;
    private static Scene phoneSettingsScene;
    private static Scene phoneTipsScene;
       
    
    public void start(Stage primaryStage) throws Exception{ 
        
        stage = primaryStage;
        
    
        
        Parent tabletMain = FXMLLoader.load(getClass().getResource("main_tablet.fxml"));
        tabletMainScene = new Scene(tabletMain);
        
        Parent tabletSettings = FXMLLoader.load(getClass().getResource("settings_tablet.fxml"));
        tabletSettingsScene = new Scene(tabletSettings);
        
        Parent phoneMain = FXMLLoader.load(getClass().getResource("main_phone.fxml"));
        phoneMainScene = new Scene(phoneMain);
        
        Parent phoneTips = FXMLLoader.load(getClass().getResource("tips_phone.fxml"));
        phoneTipsScene = new Scene(phoneTips);
        
       //PRIMARY STAGE
        stage.setTitle("Cycling Sun - Tablet");
        stage.setResizable(false);
        stage.setScene(tabletMainScene);
        stage.show();   
        
       
       
        
    }
    
    public static void reloadSettings(){
    settings = new Settings();
    }
    
    public static void main(String[] args) throws IOException{
       settings = new Settings();      
       launch(args);  
    } 
}
