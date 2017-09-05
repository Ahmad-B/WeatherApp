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
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;


public class Tips {
private HashMap weather;  

    //Read the tips from the text file
    public Tips(){
        this.weather = new HashMap();
        ArrayList<String> getinfo = new ArrayList<String>();
        String line = null;
        
        try {
                
            BufferedReader inStream = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/assets/tips.txt")));
            while ((line = inStream.readLine()) != null) {
                getinfo.add(line);
            }
            
            inStream.close();
            for(int i = 0; i<getinfo.size()-2;i++)
            {
                if(getinfo.get(i+1).equals("-----------------"))
                {
                    weather.put(getinfo.get(i),getinfo.get(i+2));
                }
                
            }
        }
        catch (IOException ex) {
            System.out.println("Error - The results could not be read from the file.");
        }    
    
}
    //Get the tip corresponding to the weather condition
    public String getTip(String weatherQuery)
    {
        if (weatherQuery.contains("rain") || weatherQuery.contains("Rain"))
        {
            weatherQuery = "Rain";
        }
       String weatherTip = (String) weather.get(weatherQuery);
       if (weatherTip == null)
       {
           return (String) weather.get("Fair");
       }
       else
       {
           return weatherTip;
       }
    }
}
     