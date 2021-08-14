package sample;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

public class WeatherData {//gets data from the API
    String date;//the values from the API
    String datatype;
    String station;
    String attributes;
    double value;

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getAttributes() {
        return attributes;
    }

    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getDatatype() {
        return datatype;
    }

    public void setDatatype(String datatype) {
        this.datatype = datatype;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    //reads the data from the API
    public WeatherRegion getWeatherData(String location, String time) throws IOException { //code from https://www.mkyong.com/webservices/jax-rs/restfull-java-client-with-java-net-url/
        //URL url = new URL("https://www.ncdc.noaa.gov/cdo-web/api/v2/data?datasetid=GHCND&stationid=GHCND:USW00094846&startdate=2010-05-01&enddate=2010-05-01&units=metric"); //selects the URL of the API based on station ID and date
        URL url = new URL("https://www.ncdc.noaa.gov/cdo-web/api/v2/data?datasetid=GHCND&stationid=" + location.substring(0, 17) + "&startdate=" + time + "&enddate=" + time + "&units=metric");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();//allows a connection for access
        conn.setRequestMethod("GET");//only gets data from the API
        conn.setRequestProperty("Accept", "application/json");//json format
        conn.setRequestProperty("token", "qqfgOpZRhUyqHOrRWSZLkvoYgflyDXMN");
        if (conn.getResponseCode() != 200)
        {
            throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());//prevents runtime errors
        }
        BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));//reads the lines of the API
        StringBuilder apiOutput = new StringBuilder();//string that will contain all data from the API
        String input;
        while ((input = (br.readLine())) != null) {//while there is a line to be read, the line will be appended to a string
            apiOutput.append(input);
        }
        conn.disconnect();//stops the reading
        return passJson(apiOutput.toString(), location);
    }

    //organizes the data from the API and returns it as a weatherRegion object
    public WeatherRegion passJson(String response, String placeName) {
        Gson gson = new Gson();
        WeatherRegion weatherRegion = new WeatherRegion();
        weatherRegion.setName(placeName.substring(17));
        WeatherResult weatherResult = gson.fromJson(response, WeatherResult.class); //converts the json to gson with response as the key and Map.class as the object
        List<WeatherData> weatherData = weatherResult.getResults();
        for (WeatherData data: weatherData){
            if (data.getDatatype() != null) {
                if (data.getDatatype().equalsIgnoreCase("AWND")) {
                    weatherRegion.setAws(data.getValue());//sets data as average wind speed
                }
                else if (data.getDatatype().equalsIgnoreCase("PRCP")) {
                    weatherRegion.setPrcp(data.getValue());//sets data as precipitation
                }
                else if (data.getDatatype().equalsIgnoreCase("SNOW")) {
                    weatherRegion.setSnow(data.getValue());//sets data as snowfall
                }
                else if (data.getDatatype().equalsIgnoreCase("SNWD")) {
                    weatherRegion.setSndp(data.getValue());//sets data as snow depth
                }
                else if (data.getDatatype().equalsIgnoreCase("TAVG")) {
                    weatherRegion.setTavg(data.getValue());//sets data as average temperature
                }
                else if (data.getDatatype().equalsIgnoreCase("TMAX")) {
                    weatherRegion.setTmax(data.getValue());//sets data as maximum temperature
                }
                else if (data.getDatatype().equalsIgnoreCase("TMIN")) {
                    weatherRegion.setTmin(data.getValue());//sets data as minimum temeprature
                }
                else if (data.getDatatype().equalsIgnoreCase("WESD")) {
                    weatherRegion.setWesg(data.getValue());//sets data as water equivalent of snow on ground
                }
            }
        }
        return weatherRegion;
    }
}
