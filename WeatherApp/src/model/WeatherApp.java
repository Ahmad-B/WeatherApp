
package model;

public class WeatherApp {


    public static void main(String[] args)
	{
		WeatherAPI weather = new WeatherAPI(model.Location.locationResolver("London"));
            
                System.out.println(weather.theWeatherRSS);
		for(int i = 0; i < weather.weatherForecastList.size(); i++)
		{
			System.out.println(weather.weatherForecastList.get(i).day);
                        System.out.println(weather.weatherForecastList.get(i).lowTemp + " " +
			weather.weatherForecastList.get(i).highTemp);
                        
                        System.out.println(weather.weatherForecastList.get(i).condition);
                       
                              
		}
                
                for(int i = 0; i < weather.weatherInfoList.size(); i++)
		{
                        System.out.println("Wind Speed: " + weather.weatherInfoList.get(i).windSpeed);
                        System.out.println("Sunrise: " + weather.weatherInfoList.get(i).sunRise);
                        System.out.println("Sunset: " +weather.weatherInfoList.get(i).sunSet);
                        System.out.println("Current temperature: " + weather.weatherInfoList.get(i).currentTemp);
                        System.out.println("Visibility: " +weather.weatherInfoList.get(i).visibility);
                        System.out.println("Humidity: " + weather.weatherInfoList.get(i).humidity);
		}
                        
	}
    
}
