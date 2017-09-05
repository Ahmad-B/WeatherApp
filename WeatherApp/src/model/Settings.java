/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author laszlojmoricz
 */
//HOLDING THE SETTINGS
public class Settings {

private String locationName;
private boolean tipOn;
private boolean alertOn;  
    
    public Settings(){
    try{ loadSettings();} catch (Exception e) {System.out.println("No success");}
    }
    
    //Method will save the current settings
    public void saveSettings() {
        String filename = "Settings";
        try {
            PrintWriter outputStream = null;
              File jarPath=new File(Settings.class.getProtectionDomain().getCodeSource().getLocation().getPath());
        String propertiesPath=jarPath.getParentFile().getAbsolutePath();
            outputStream = new PrintWriter(new FileWriter(propertiesPath + "/" + filename + ".txt"));
            
            outputStream.println(getLocationName()); //location
            outputStream.println(isTipOn()); //tips
            outputStream.println(isAlertOn()); //alert
            
            outputStream.flush();
            outputStream.close();
        } catch (IOException ex) {
            System.out.println("Error - The settings could not be written to the file.");
            ex.printStackTrace();
        }
        
    }
  
   //Method will read the previous settings
    public void loadSettings() throws IOException {
        ArrayList<String> getinfo = new ArrayList<String>();
        String filename = "Settings";
        String line = null;
        File jarPath=new File(Settings.class.getProtectionDomain().getCodeSource().getLocation().getPath());
        String propertiesPath=jarPath.getParentFile().getAbsolutePath();
        try {
                
            BufferedReader inStream = new BufferedReader(new FileReader(propertiesPath + "/" + filename + ".txt"));
            while ((line = inStream.readLine()) != null) {
                String[] info= line.split(" ");
                getinfo.add(info[0]);
            }
            
            setLocationName(getinfo.get(0));
            setTipOn(Boolean.parseBoolean(getinfo.get(1)));
            setAlertOn(Boolean.parseBoolean(getinfo.get(2)));
            
            inStream.close();
        } catch (IOException ex) {
            System.out.println("Error - The results could not be read from the file.");
        }
    }

    /**
     * @return the locationName
     */
    public String getLocationName() {
        return locationName;
    }

    /**
     * @param locationName the locationName to set
     */
    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    /**
     * @return the tipOn
     */
    public boolean isTipOn() {
        return tipOn;
    }

    /**
     * @param tipOn the tipOn to set
     */
    public void setTipOn(boolean tipOn) {
        this.tipOn = tipOn;
    }

    /**
     * @return the alertOn
     */
    public boolean isAlertOn() {
        return alertOn;
    }

    /**
     * @param alertOn the alertOn to set
     */
    public void setAlertOn(boolean alertOn) {
        this.alertOn = alertOn;
    }
    
    
}
