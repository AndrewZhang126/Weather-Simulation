package sample;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class WeatherPredict {
    //based on all previous years of data and this years data, return a predicted value for this year
    //calculates a line of best fit for the data of each year, and uses that line of best fit to model this years data
    //the line of best fit with the least error for this year is the line that best models this year and will be used to predict this years data
    public Double predictValue(ArrayList<ArrayList<Double>> allData, ArrayList<Double> oneYear) { //equation from https://www.statisticshowto.com/probability-and-statistics/regression-analysis/find-a-linear-regression-equation/
        double error = 1000000.0;
        double finalA = 0.0;
        double finalB = 0.0;
        for (ArrayList<Double> y : allData) {//runs through every year
            ArrayList<Double> x = createArray(y.size());//creates the x variables, which are just numbers representing the days since the first date
            double xy = sumArray(multiplyValues(x, y));//finds the summation of all x and y values multiplies
            double xSquare = sumArray(multiplyValues(x, x));//finds the summation of all x squared
            double ySquare = sumArray(multiplyValues(y, y));//finds the summation of all y squared
            if (findResiduals(oneYear, calculateA(sumArray(x), sumArray(y), xy, xSquare, ySquare, y.size()), (calculateB(sumArray(x), sumArray(y), xy, xSquare, ySquare, y.size()))) < error) {//calculates the slope and intercept of a line of best fit and finds the total error when that line is applied to the current years data
                error = findResiduals(oneYear, calculateB(sumArray(x), sumArray(y), xy, xSquare, ySquare, y.size()), (calculateA(sumArray(x), sumArray(y), xy, xSquare, ySquare, y.size())));//if the error is less than the previous error, this is now the new line of best fit
                finalA = calculateA(sumArray(x), sumArray(y), xy, xSquare, ySquare, y.size());//saves the new intercept
                finalB = calculateB(sumArray(x), sumArray(y), xy, xSquare, ySquare, y.size());//saves the new slope
            }
        }
        return finalA + (finalB * oneYear.size());//returns the new value by multiplying the slope of the line of best fit by the predicted day's x variable and adds the intercept from the line of best fit

    }

    //calculates total error from the line of best fit
    public Double findResiduals(ArrayList<Double> compare, double slope, double intercept) {
        double residual = 0.0;
        for (int i = 0; i < compare.size(); i ++) {
            residual += Math.abs(compare.get(i) - ((slope * i) + intercept));//subtracts predicted value from oberved value
        }
        return residual;//returns sum of all residuals as total error
    }

    //this is the formula for calculating the intercept
    public Double calculateA(double sumX, double sumY, double sumXY, double sumXSquare, double sumYSquare, double n) {
        return ((sumY * sumXSquare) - (sumX * sumXY)) / ((n * sumXSquare) - Math.pow(sumX, 2));
    }

    //this is the formula for calculating the slope
    public Double calculateB(double sumX, double sumY, double sumXY, double sumXSquare, double sumYSquare, double n) {
        return ((n * sumXY) - (sumX * sumY)) / ((n * sumXSquare) - Math.pow(sumX, 2));
    }

    //creates a new array of multiplied values from two arrays
    public ArrayList<Double> multiplyValues(ArrayList<Double> a1, ArrayList<Double> a2) {
        ArrayList<Double> multiplyArray = new ArrayList<>();
        for (int i = 0; i < a1.size(); i ++) {
            multiplyArray.add(a1.get(i) * a2.get(i));
        }
        return multiplyArray;
    }

    //finds the sum of all values in an array
    public Double sumArray(ArrayList<Double> array) {
        double count = 0;
        for (int i = 0; i < array.size(); i ++) {
            count += array.get(i);
        }
        return count;
    }

    //creates an array
    public ArrayList<Double> createArray(int size) {
        ArrayList<Double> newArray = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            newArray.add((double) i);//represents the days past since the first day
        }
        return newArray;
    }
}
