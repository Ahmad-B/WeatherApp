/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.net.*;
import java.util.regex.*;
import java.util.ArrayList;
import java.io.*;

public class WeatherAPI
{
	static String theWeatherRSS;
	public static String theCity;
	public static ArrayList<Forecast> weatherForecastList;
        public static ArrayList<Info> weatherInfoList;
	
	public class Forecast
	{
		public String day;
                public String lowTemp;
		public String highTemp;
                public String condition;

	}
        
        public class Info
        {
                public String currentTemp;
                public String humidity;
                public String visibility;
                public String windSpeed;
                public String sunRise;
                public String sunSet;
                public String condition;
                
        }

	public WeatherAPI(String city)
	{
		theCity = city;
		theWeatherRSS = getWeatherAsRSS(city);
		parseWeather(theWeatherRSS);
                parseInfo(theWeatherRSS);
	}

	void parseWeather(String weatherHTML)
	{
		weatherForecastList = new ArrayList<Forecast>();
		int startIndex = 0;
		while(startIndex != -1)
		{
			startIndex = weatherHTML.indexOf("<yweather:forecast", startIndex);
                        
			if(startIndex != -1)
			{ // found a weather forecast
				int endIndex = weatherHTML.indexOf(">", startIndex);
				String weatherForecast = weatherHTML.substring(startIndex, endIndex+1);
                                

				// get temp forecast
                                Forecast fore = new Forecast();
                                fore.day = getValueForKey(weatherForecast, "day");
                                fore.lowTemp = getValueForKey(weatherForecast, "low");
				fore.highTemp = getValueForKey(weatherForecast, "high");
				fore.condition = getValueForKey(weatherForecast, "text");

                                weatherForecastList.add(fore);
				
				// move to end of this forecast
                                startIndex = endIndex;
				
			}
		}     
                
	}
        
        void parseInfo(String weatherHTML) {
            
            weatherInfoList = new ArrayList<Info>();
            int startIndex = 0;
		while(startIndex != -1)
		{
			startIndex = weatherHTML.indexOf("<yweather:wind", startIndex);
                        
			if(startIndex != -1)
			{ // found a weather forecast
				int endIndex = weatherHTML.indexOf("<description>", startIndex);
				String weatherForecast = weatherHTML.substring(startIndex, endIndex+1);
                                

				// get temp forecast
                                Info inf = new Info();
                                
                                inf.currentTemp = getValueForKey(weatherForecast, "temp");
                                inf.humidity = getValueForKey(weatherForecast, "humidity");
                                inf.visibility = getValueForKey(weatherForecast, "visibility");
                                inf.windSpeed = getValueForKey(weatherForecast, "speed");
                                inf.sunRise = getValueForKey(weatherForecast, "sunrise");
                                inf.sunSet = getValueForKey(weatherForecast, "sunset");
                                inf.condition = getValueForKey(weatherForecast, "text");
                                
				
                                weatherInfoList.add(inf);
				
				// move to end of this forecast
                                startIndex = endIndex;
				
			}
		}
        
        }

	String getValueForKey(String theString, String keyString)
	{
		int startIndex = theString.indexOf(keyString);
		startIndex = theString.indexOf("\"", startIndex);
		int endIndex = theString.indexOf("\"", startIndex+1);
		String resultString = theString.substring(startIndex+1, endIndex);
		return resultString;
	}

	String getWeatherAsRSS(String city)
	{
		try{
			/*
			Adapted from: http://stackoverflow.com/questions/1381617/simplest-way-to-correctly-load-html-from-web-page-into-a-string-in-java
			Answer provided by: erickson
			*/
			URL url = new URL("http://weather.yahooapis.com/forecastrss?w="+city+"&u=c");
			URLConnection con = url.openConnection();
			Pattern p = Pattern.compile("text/html;\\s+charset=([^\\s]+)\\s*");
			Matcher m = p.matcher(con.getContentType());
			/* If Content-Type doesn't match this pre-conception, choose default and 
			 * hope for the best. */
			String charset = m.matches() ? m.group(1) : "ISO-8859-1";
			Reader r = new InputStreamReader(con.getInputStream(), charset);
			StringBuilder buf = new StringBuilder();
			while (true) {
			  int ch = r.read();
			  if (ch < 0)
				break;
			  buf.append((char) ch);
			}
			String str = buf.toString();
			return(str);
		}
		catch(Exception e) {System.err.println("Weather API Exception: "+e);}
		return null;
	}

}
