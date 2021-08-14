package sample;

import java.io.*;
import java.util.ArrayList;


public class WeatherHistorical {//reads the data from CSV files
    String name;
    double latitude;
    double longitude;
    public WeatherHistorical(String n) {
        name = n;
        getHistorical();
    }
    ArrayList<String> awsHistorical = new ArrayList<>();
    ArrayList<String> prcpHistorical = new ArrayList<>();
    ArrayList<String> snowHistorical = new ArrayList<>();
    ArrayList<String> sndpHistorical = new ArrayList<>();
    ArrayList<String> tavgHistorical = new ArrayList<>();
    ArrayList<String> tmaxHistorical = new ArrayList<>();
    ArrayList<String> tminHistorical = new ArrayList<>();
    ArrayList<String> wesgHistorical = new ArrayList<>();
    int awsIndex = -1;
    int prcpIndex = -1;
    int snowIndex = -1;
    int sndpIndex = -1;
    int tavgIndex = -1;
    int tmaxIndex = -1;
    int tminIndex = -1;
    int wesgIndex = -1;
    public ArrayList<String> getAwsHistorical() {
        return awsHistorical;
    }

    public void setAwsHistorical(ArrayList<String> awsHistorical) {
        this.awsHistorical = awsHistorical;
    }
    public void addAwsHistorical(String date, double value) {
        awsHistorical.add(date + "," + value);
    }
    public ArrayList<String> getPrcpHistorical() {
        return prcpHistorical;
    }

    public void setPrcpHistorical(ArrayList<String> prcpHistorical) {
        this.prcpHistorical = prcpHistorical;
    }
    public void addPrcpHistorical(String date, double value) {
        prcpHistorical.add(date + "," + value);
    }

    public ArrayList<String> getSnowHistorical() {
        return snowHistorical;
    }

    public void setSnowHistorical(ArrayList<String> snowHistorical) {
        this.snowHistorical = snowHistorical;
    }
    public void addSnowHistorical(String date, double value) {
        snowHistorical.add(date + "," + value);
    }

    public ArrayList<String> getSndpHistorical() {
        return sndpHistorical;
    }

    public void setSndpHistorical(ArrayList<String> sndpHistorical) {
        this.sndpHistorical = sndpHistorical;
    }
    public void addSndpHistorical(String date, double value) {
        sndpHistorical.add(date + "," + value);
    }

    public ArrayList<String> getTavgHistorical() {
        return tavgHistorical;
    }

    public void setTavgHistorical(ArrayList<String> tavgHistorical) {
        this.tavgHistorical = tavgHistorical;
    }
    public void addTavgHistorical(String date, double value) {
        tavgHistorical.add(date + "," + value);
    }

    public ArrayList<String> getTmaxHistorical() {
        return tmaxHistorical;
    }

    public void setTmaxHistorical(ArrayList<String> tmaxHistorical) {
        this.tmaxHistorical = tmaxHistorical;
    }
    public void addTmaxHistorical(String date, double value) {
        tmaxHistorical.add(date + "," + value);
    }

    public ArrayList<String> getTminHistorical() {
        return tminHistorical;
    }

    public void setTminHistorical(ArrayList<String> tminHistorical) {
        this.tminHistorical = tminHistorical;
    }
    public void addTminHistorical(String date, double value) {
        tminHistorical.add(date + "," + value);
    }

    public ArrayList<String> getWesgHistorical() {
        return wesgHistorical;
    }

    public void setWesgHistorical(ArrayList<String> wesgHistorical) {
        this.wesgHistorical = wesgHistorical;
    }
    public void addWesgHistorical(String date, double value) {
        wesgHistorical.add(date + "," + value);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void getAll() {

    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

   //reads and sets data from the CSV files
    public void getHistorical() {
        String csvFile = "src/resources/" + name + ".csv";//reads the csv files I have for each locations
        name = name.replace(" ", "/");
        BufferedReader br = null;
        String cvsSplitBy = ",";
        try {
            br = new BufferedReader(new FileReader(new File(csvFile)));
            String[] firstLine = br.readLine().replace("\"", "").split(cvsSplitBy, -1);//splits first line into the data categories
            for (int i = 0; i < firstLine.length; i ++) {
                if (firstLine[i].equals("AWND")) {//finds the index of where average wind speed can be found
                    awsIndex = i + 1;
                }
                else if (firstLine[i].equalsIgnoreCase("PRCP")) {//finds the index of where precipitation can be found
                    prcpIndex = i + 1;
                }
                else if (firstLine[i].equalsIgnoreCase("SNOW")) {//finds the index of where snowfall can be found
                    snowIndex = i + 1;
                }
                else if (firstLine[i].equalsIgnoreCase("SNWD")) {//finds the index of where snow depth can be found
                    sndpIndex = i + 1;
                }
                else if (firstLine[i].equalsIgnoreCase("TAVG")) {//finds the index of where average temperature can be found
                    tavgIndex = i + 1;
                }
                else if (firstLine[i].equalsIgnoreCase("TMAX")) {//finds the index of where maximum temperature can be found
                    tmaxIndex = i + 1;
                }
                else if (firstLine[i].equalsIgnoreCase("TMIN")) {//finds the index of where minimum temperature can be found
                    tminIndex = i + 1;
                }
                else if (firstLine[i].equalsIgnoreCase("WESD")) {//finds the index of where water equivalent of snow on ground can be found
                    wesgIndex = i + 1;
                }
            }
            String line = "";
            while ((line = br.readLine()) != null) {
                line = line.replace("\"", "");
                String[] country = line.split(cvsSplitBy, -1);
                latitude = Double.parseDouble(country[3]);
                longitude = Double.parseDouble(country[4]);
                for (int i = 0; i < country.length; i++) {
                    if (!country[i].equalsIgnoreCase("")) {//ensures there is data in the spot being checked
                        if (i == awsIndex) {//if index matches average wind speed index, then add that data to the arraylist of avergae wind speed along with the date
                            awsHistorical.add(country[6] + "," + Double.parseDouble(country[i]));
                        }
                        else if (i == prcpIndex) {//if index matches precipitation index, then add that data to the arraylist of precipitation along with the date
                            prcpHistorical.add(country[6] + "," + Double.parseDouble(country[i]));
                        }
                        else if (i == snowIndex) {//if index matches snowfall index, then add that data to the arraylist of snowfall along with the date
                            snowHistorical.add(country[6] + "," + Double.parseDouble(country[i]));
                        }
                        else if (i == sndpIndex) {//if index matches snow depth index, then add that data to the arraylist of snow depth along with the date
                            sndpHistorical.add(country[6] + "," + Double.parseDouble(country[i]));
                        }
                        else if (i == tavgIndex) {//if index matches average temperature index, then add that data to the arraylist of average temperature along with the date
                            tavgHistorical.add(country[6] + "," + Double.parseDouble(country[i]));
                        }
                        else if (i == tmaxIndex) {//if index matches maximum temperature index, then add that data to the arraylist of average temperature along with the date
                            tmaxHistorical.add(country[6] + "," + Double.parseDouble(country[i]));
                        }
                        else if (i == tminIndex) {//if index matches minimum temperature index, then add that data to the arraylist of minimum temperature along with the date
                            tminHistorical.add(country[6] + "," + Double.parseDouble(country[i]));
                        }
                        else if (i == wesgIndex) {//if index matches water equivalent of snow on ground index, then add that data to the arraylist of water equivalent of snow on ground along with the date
                            wesgHistorical.add(country[6] + "," + Double.parseDouble(country[i]));
                        }
                    }
                }
            }
        }
        catch (FileNotFoundException e) {//catch exceptions
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (br != null) {//stops reading
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
