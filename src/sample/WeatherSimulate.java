package sample;

import javafx.animation.AnimationTimer;

import java.nio.file.WatchEvent;
import java.util.ArrayList;
import java.util.List;

public class WeatherSimulate {
    //finds the average value of an array
    public double findAverage(ArrayList<String> array, String category) {
        double average = 0.0;
        double count = 0.0;
        for (int i = 0; i < array.size(); i ++) {
            if (!array.get(i).equals("N/A")) {//only takes into account the value if it exists
                String [] information = array.get(i).split(" " , -1);
                String name = information[1];
                double value;
                if (information[0].charAt(information[0].length() - 1) == '*') {//ensures the string being turned into a double is valid
                    value =  Double.parseDouble(information[0].substring(0, information[0].length() - 1));
                }
                else {
                    value =  Double.parseDouble(information[0]);
                }
                if (category.equals("Y")) {//Yellowstone will affect all locations in America, so all values are taken into account
                    average += value;
                    count++;
                }
                else if (category.equals("HS")) {//a Gulf Coast hurricane will only affect states on the Gulf Coast, so only those states are taken into account
                    if (name.equals("Florida") || name.equals("Alabama") || name.equals("Mississippi") || name.equals("Louisiana") || name.equals("Texas")) {
                        average += value;
                        count++;
                    }
                }
                else {//an East Coast hurricane will only affect states on the East Coast, so only those states are taken into account
                    if (name.equals("Florida") || name.equals("Georgia") || name.equals("South/Carolina") || name.equals("North/Carolina") || name.equals("Virginia")|| name.equals("Maryland") || name.equals("New/Jersey")
                            || name.equals("New/Hampshire") || name.equals("Massachusetts") || name.equals("Maine")) {
                        average += value;
                        count++;
                    }
                }
            }
        }
        return average / count;//returns the total value divided by how many values were added
    }

    //simulates the event by changing the values in the array
    public ArrayList<String> doSimulation(ArrayList<String> data, String type, double t1, double t2, double t3, double t4, double t5, double t6, double t7) {
        for (int i = 0; i < data.size(); i ++) {
            if (!data.get(i).equals("N/A")) {//only takes into account the value if it exists
                String [] information = data.get(i).split(" " , -1);
                String name = information[1];
                double value;
                if (information[0].charAt(information[0].length() - 1) == '*') {//ensures the string being turned into a double is valid
                    value =  Double.parseDouble(information[0].substring(0, information[0].length() - 1));
                }
                else {
                    value =  Double.parseDouble(information[0]);
                }
                if (type.equals("Y")) {//for the Yellowstone supervolcano
                    if (name.equals("Wyoming")) {//these are grouped into regions that will undergo the same changes. States nearest Yellowstone will experience the most change, and that change will decrease as distance from Yellowstone increases
                        data.set(i, setValue(name, value, t1));
                    }
                    else if (name.equals("Montana") || name.equals("Idaho")) {
                        data.set(i, setValue(name, value, t2));
                    }
                    else if (name.equals("Utah") || name.equals("Colorado") || name.equals("Nebraska")) {
                        data.set(i, setValue(name, value, t3));
                    }
                    else if (name.equals("Kansas") || name.equals("Nevada") || name.equals("South/Dakota") || name.equals("North/Dakota")) {
                        data.set(i, setValue(name, value, t4));
                    }
                    else if (name.equals("Washington") || name.equals("Oregon") || name.equals("Southern/California") || name.equals("Northern/California") ||
                            name.equals("Arizona") || name.equals("Oklahoma") || name.equals("New/Mexico") || name.equals("Mississippi") ||
                            name.equals("Illinois") || name.equals("Iowa") || name.equals("Minnesota") || name.equals("Wisconsin")) {
                        data.set(i, setValue(name, value, t5));
                    }
                    else if (name.equals("Texas") || name.equals("Arkansas") || name.equals("Tennessee") || name.equals("Kentucky") ||
                            name.equals("Indiana") || name.equals("West/Virginia") | name.equals("Ohio") || name.equals("Michigan")) {
                        data.set(i, setValue(name, value, t6));
                    }
                    else {
                        data.set(i, setValue(name, value, t7));
                    }
                }
                else if (type.equals("HS")) {//for the Gulf Coast hurricane
                    if (name.equals("Florida")) {//these are grouped into regions that will undergo the same changes. As the hurricane moves from east to west, the affected states will undergo changes at different times
                        data.set(i, setValue(name, value, t1));
                    }
                    else if (name.equals("Alabama") || name.equals("Mississippi")) {
                        data.set(i, setValue(name, value, t2));
                    }
                    else if (name.equals("Louisiana")) {
                        data.set(i, setValue(name, value, t3));
                    }
                    else if (name.equals("Texas")) {
                        data.set(i, setValue(name, value, t4));
                    }
                }
                else {
                    if (name.equals("Florida")) {//for the East Coast hurricane. //these are grouped into regions that will undergo the same changes. As the hurricane moves from south to north, the affected states will undergo changes at different times
                        data.set(i, setValue(name, value, t1));
                    }
                    else if (name.equals("Georgia") || name.equals("South/Carolina") || name.equals("North/Carolina")) {
                        data.set(i, setValue(name, value, t2));
                    }
                    else if (name.equals("Virginia") || name.equals("Maryland") || name.equals("New/Jersey")) {
                        data.set(i, setValue(name, value, t3));
                    }
                    else if (name.equals("New/Hampshire") || name.equals("Massachusetts") || name.equals("Maine")) {
                        data.set(i, setValue(name, value, t4));
                    }
                }
            }
        }
        return data;//returns modified array
    }

    //sets the new value of the array
    public String setValue(String z, double x, double y) {
        return (x + y) + " " + z;
    }
}

