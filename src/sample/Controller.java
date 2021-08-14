package sample;

import javafx.animation.AnimationTimer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Controller {
    @FXML
    private AnchorPane anchor1;
    @FXML
    private Label lblT1, lblT2, lblT3, lblT4, lblT5, lblT6, lblU1, lblU2, lblSim, lbl1, lbl2, lbl3, lblUnits, lblName, lblGraph, lblDate;
    @FXML
    private CheckBox checkC, checkF, check1, check2, check3;
    @FXML
    private ImageView imgMap, imgSim;
    @FXML
    private ChoiceBox choice1;
    @FXML
    private LineChart lineChart;
    @FXML
    private ListView lst1;
    @FXML
    private TextField txtYear, txtMonth, txtDay;
    @FXML
    private Button btnLeft, btnRight, btnSimulateY, btnSimulateHS, btnSimulateHE, btnDate, btnToday;
    @FXML
    private Slider slider1;
    @FXML
    private Circle circle1, circle2, circle3, circle4, circle5, circle6, circle7, circle8, circle9,
            circle10, circle11, circle12, circle13, circle14, circle15, circle16, circle17, circle18, circle19,
            circle20, circle21, circle22, circle23, circle24, circle25, circle26, circle27, circle28, circle29,
            circle30, circle31, circle32, circle33, circle34, circle35, circle36, circle37, circle38, circle39,
            circle40, circle41, circle42, circle43, circle44, circle45;
    boolean isSimulation = false;
    char tempUnit = 'c';
    String prcpUnit = "pm";
    String unit = " °C ";
    boolean convert = false;
    boolean tempSelected = true;
    private long timer = System.nanoTime();
    private int count = 0;
    String date = "2020-04-04";
    String placeName = "Illinois";
    String dataName = "tavg";
    ArrayList<Circle> circles = new ArrayList<>();
    WeatherRegion[] weatherRegionArray = new WeatherRegion[45];
    WeatherHistorical[] weatherHistoricalArray = new WeatherHistorical[45];
    String[] stationID = new String[] {"GHCND:USW00024233Washington", "GHCND:USW00024229Oregon", "GHCND:USW00023234Northern/California", "GHCND:USW00093134Southern/California",
    "GHCND:USW00024131Idaho", "GHCND:USW00023169Nevada", "GHCND:USW00024033Montana", "GHCND:USW00024127Utah", "GHCND:USW00023183Arizona",
    "GHCND:USW00024018Wyoming", "GHCND:USW00003017Colorado", "GHCND:USW00023050New/Mexico", "GHCND:USW00014914North/Dakota", "GHCND:USW00014944South/Dakota", "GHCND:USW00014942Nebraska",
    "GHCND:USW00003928Kansas", "GHCND:USC00346659Oklahoma", "GHCND:USW00012918Texas", "GHCND:USW00014922Minnesota", "GHCND:USW00014933Iowa", "GHCND:USW00003947Missouri",
    "GHCND:USW00013963Arkansas", "GHCND:USW00012916Louisiana", "GHCND:USW00014839Wisconsin", "GHCND:USW00094846Illinois", "GHCND:USW00003940Mississippi", "GHCND:USW00094847Michigan",
    "GHCND:USW00093819Indiana", "GHCND:USW00093821Kentucky", "GHCND:USW00013897Tennessee", "GHCND:USW00013876Alabama", "GHCND:USW00014821Ohio", "GHCND:USW00013866West/Virginia",
    "GHCND:US1VAVBC023Virginia", "GHCND:USW00013881North/Carolina", "GHCND:USW00013880South/Carolina", "GHCND:USW00013874Georgia", "GHCND:USW00013889Florida", "GHCND:USW00013739Pennsylvania",
    "GHCND:USW00093721Maryland", "GHCND:USW00094728New/Jersey", "GHCND:USW00014733New/York", "GHCND:USW00014739Massachusetts", "GHCND:USW00014710New/Hampshire", "GHCND:USW00014764Maine"};

    //when the program first starts, the data is retrieved from the API, added to historical data for further use, and the screen is displayed
    @FXML
    private void initialize() throws IOException, InterruptedException {
        circles.add(circle1);
        circles.add(circle2);
        circles.add(circle3);
        circles.add(circle4);
        circles.add(circle5);
        circles.add(circle6);
        circles.add(circle7);
        circles.add(circle8);
        circles.add(circle9);
        circles.add(circle10);
        circles.add(circle11);
        circles.add(circle12);
        circles.add(circle13);
        circles.add(circle14);
        circles.add(circle15);
        circles.add(circle16);
        circles.add(circle17);
        circles.add(circle18);
        circles.add(circle19);
        circles.add(circle20);
        circles.add(circle21);
        circles.add(circle22);
        circles.add(circle23);
        circles.add(circle24);
        circles.add(circle25);
        circles.add(circle26);
        circles.add(circle27);
        circles.add(circle28);
        circles.add(circle29);
        circles.add(circle30);
        circles.add(circle31);
        circles.add(circle32);
        circles.add(circle33);
        circles.add(circle34);
        circles.add(circle35);
        circles.add(circle36);
        circles.add(circle37);
        circles.add(circle38);
        circles.add(circle39);
        circles.add(circle40);
        circles.add(circle41);
        circles.add(circle42);
        circles.add(circle43);
        circles.add(circle44);
        circles.add(circle45);
        imgMap.setImage(new Image("resources/map.jpg"));
        checkC.setSelected(true);
        //creates a new weatherHistorical object by retrieving data from the CSV files
        for (int i = 0; i < stationID.length; i ++) {
            weatherHistoricalArray[i] = new WeatherHistorical(stationID[i].substring(17).replace("/", " "));
        }
        //creates a new weatherRegion object by retrieving data from the API. Adds the data from each location to the respective weatherHistorical object so I do not need to call the API
        //every time I need the data from this day
        WeatherData data = new WeatherData();
        for (int i = 0; i < stationID.length; i ++) {
            TimeUnit.MILLISECONDS.sleep(300L);//addds delay because the API only allows 5 calls per second
            weatherRegionArray[i] = data.getWeatherData(stationID[i], "2020-04-04");
            weatherHistoricalArray[i].addAwsHistorical("2020-04-04", weatherRegionArray[i].getAws());
            weatherHistoricalArray[i].addPrcpHistorical("2020-04-04", weatherRegionArray[i].getPrcp());
            weatherHistoricalArray[i].addSnowHistorical("2020-04-04", weatherRegionArray[i].getSnow());
            weatherHistoricalArray[i].addSndpHistorical("2020-04-04", weatherRegionArray[i].getSndp());
            weatherHistoricalArray[i].addTavgHistorical("2020-04-04", weatherRegionArray[i].getTavg());
            weatherHistoricalArray[i].addTmaxHistorical("2020-04-04", weatherRegionArray[i].getTmax());
            weatherHistoricalArray[i].addTminHistorical("2020-04-04", weatherRegionArray[i].getTmin());
            weatherHistoricalArray[i].addWesgHistorical("2020-04-04", weatherRegionArray[i].getWesg());
            System.out.println(weatherRegionArray[i].getName());
        }
        drawScreen();//draws the screen
        convert = true;
        ObservableList<String> charOption = FXCollections.observableArrayList("TEMPERATURE", "OTHER");
        choice1.setStyle("-fx-font: 15px \"Andale Mono\";");
        choice1.setItems(charOption); //puts options into the choice box
        choice1.setValue("TEMPERATURE");
        choice1.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {//notifies when the choice box selection has changed
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                handleChoice1((String) choice1.getItems().get((Integer) number2));//calls method that handles the choice box selection
            }
        });
        graph(arrayGraph());//graphs data
        txtYear.setText("2020");
        txtMonth.setText("04");
        txtDay.setText("03");//the date is set to the date of the data gathered from the API, as the historical data only goes to 2020-04-02
        lblName.setText("ILLINOIS");//defautl location is Illinois
    }

    //automatically sets the date to today and displays data for today
    @FXML
    public void handlebtnToday() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");//formats the data
        Date getDate = new Date();
        txtYear.setText(reformatDate(dateFormat.format(getDate)).substring(0, 4));//sets respective elements of today's into the text boxes
        txtMonth.setText(reformatDate(dateFormat.format(getDate)).substring(5, 7));
        txtDay.setText(reformatDate(dateFormat.format(getDate)).substring(8));
        date = txtYear.getText() + "-" + txtMonth.getText() + "-" + txtDay.getText();//new date is now today's date
        drawScreen();
    }

    //reformats the date to include dashes, as CSV files have dashes instead of slashes
    public String reformatDate(String day) {
        StringBuilder newDate = new StringBuilder(day);
        for (int i = 0; i < day.length(); i ++) {
            if (day.charAt(i) == '/') {
                newDate.setCharAt(i, '-');
            }
        }
        return newDate.toString();
    }

    //rounds the data so there are no extremely long decimal values. For display purposes
    public String roundData(String data, boolean list) {
        DecimalFormat df = new DecimalFormat("#.##");//sets format to have two decimal places
        df.setRoundingMode(RoundingMode.CEILING);
        if (data.equals("N/A")) {
            return "N/A";
        }
        else {
            if (list) {//the data from the listView does not have units at the end, so the substring to get the actual data is different
                if (checkPredict() || data.charAt(data.length()- 1) == '*') {
                    return df.format(Double.parseDouble(data.substring(0, data.length() - 1))) + "*";
                }
                else {
                    return df.format(Double.parseDouble(data));
                }
            }
            else {//the data from drawScreen does include units at the end, so I need the substring to cut out the units
                if (checkPredict() || data.charAt(data.length() - 5) == '*') {
                    return df.format(Double.parseDouble(data.substring(0, data.length() - 5))) + "*" + unit;
                }
                else {
                    return df.format(Double.parseDouble(data.substring(0, data.length() - 4))) + unit;
                }
            }
        }
    }

    //determines the color for each circle based on the data and the comparing numbers
    public Paint getColor(String regionTempString, double a, double b, double c, double d, double e) {
        double regionTemp;
        if (regionTempString.equals("N/A")) {
            return Color.WHITE;
        }
        else {
            String[] regionData = regionTempString.split(" ", -1);
            if (regionData[0].charAt(regionData[0].length() - 1) == '*') {
                regionTemp = Double.parseDouble(regionData[0].substring(0, regionData[0].length() - 1));//retrieves the numerical data
            }
            else {
                regionTemp = Double.parseDouble(regionData[0]);
            }
            if (regionTemp > a) {
                return Color.RED;
            }
            else if (regionTemp > b && regionTemp <= a) {
                return Color.ORANGE;
            }
            else if (regionTemp > c && regionTemp <= b) {
                return Color.YELLOW;
            }
            else if (regionTemp > d && regionTemp <= c) {
                return Color.GREEN;
            }
            else if (regionTemp > e && regionTemp <= d) {
                return Color.BLUE;
            }
            else {
                return Color.PURPLE;
            }
        }
    }

    //draws the entire screen
    public void drawScreen() {
        String value = "N/A";
        lblGraph.setText("PAST 50 DAYS");
        for (int i = 0; i < circles.size(); i ++) {//runs through every location
            if (tempSelected) {//draws data for temperature
                if (check1.isSelected()) {//this is for average temperature
                    if (checkC.isSelected()) {//units are in Celsius, so 00 is set at the string that determines the converting method
                        if (!calculateAverage2(findData2(weatherHistoricalArray[i].getTavgHistorical(), "00"), findData2(weatherHistoricalArray[i].getTmaxHistorical(), "00"), findData2(weatherHistoricalArray[i].getTminHistorical(), "00")).equals("N/A")) {//if there is an actual value for the predicted average
                            value = roundData(calculateAverage2(findData2(weatherHistoricalArray[i].getTavgHistorical(), "00"), findData2(weatherHistoricalArray[i].getTmaxHistorical(), "00"), findData2(weatherHistoricalArray[i].getTminHistorical(), "00")), true) + unit;//then the value is used
                        }
                    }
                    else {//units are in Fahrenheit, so 10 is set at the string that determines the converting method
                        if (!calculateAverage2(findData2(weatherHistoricalArray[i].getTavgHistorical(), "10"), findData2(weatherHistoricalArray[i].getTmaxHistorical(), "10"), findData2(weatherHistoricalArray[i].getTminHistorical(), "10")).equals("N/A")) {
                            value = roundData(calculateAverage2(findData2(weatherHistoricalArray[i].getTavgHistorical(), "10"), findData2(weatherHistoricalArray[i].getTmaxHistorical(), "10"), findData2(weatherHistoricalArray[i].getTminHistorical(), "10")), true) + unit;
                        }
                    }
                }
                else if (check2.isSelected()) {//this is for maximum temperatures
                    if (checkPredict()) {//determines if the data needs to be predicted
                        value = roundData(predictArray(weatherHistoricalArray[i].getTmaxHistorical(), false), false);//predicts the data
                    }
                    else {
                        value = roundData(findData(weatherHistoricalArray[i].getTmaxHistorical()), false);//data already exists so finds the data to use
                    }
                }
                else if (check3.isSelected()){//this is for minimum temperatures
                    if (checkPredict()) {//determines if the data needs to be predicted
                        value = roundData(predictArray(weatherHistoricalArray[i].getTminHistorical(), false), false);//predicts the data
                    }
                    else {
                        value = roundData(findData(weatherHistoricalArray[i].getTminHistorical()), false);//data already exists so finds the data to use
                    }
                }
                if (checkC.isSelected()) {//sets the labels when the units are in Celsius
                    lblT1.setText("> 35");
                    lblT2.setText("25 - 35");
                    lblT3.setText("15 - 25");
                    lblT4.setText("5 - 15");
                    lblT5.setText("-5 - 5");
                    lblT6.setText("< -5");
                    circles.get(i).setFill(getColor(value, 35.0, 25.0, 15.0, 5.0, -5.0));//determines the color for each circle based on each location's value
                    lblUnits.setText("CELSIUS");
                }
                else if (checkF.isSelected()){//sets the labels when the units are in Fahrenheit
                    lblT1.setText("> 95");
                    lblT2.setText("77 -95");
                    lblT3.setText("59 - 77");
                    lblT4.setText("41 - 59");
                    lblT5.setText("23 - 41");
                    lblT6.setText("< 23");
                    circles.get(i).setFill(getColor(value, 95.0, 77.0, 59.0, 41.0, 23.0));//determines the color for each circle based on each location's value
                    lblUnits.setText("FAHRENHEIT");
                }
                check1.setText("AVERAGE TEMPERATURE");
                check2.setText("MAXIMUM TEMPERATURE");
                check3.setText("MINIMUM TEMPERATURE");//names each checkbox the values they will display
            }
            else {
                if (checkC.isSelected() && check1.isSelected()) {//this is for precipitation in millimetres
                    if (checkPredict()) {//determines if the data needs to be predicted
                        value = roundData(predictArray(weatherHistoricalArray[i].getPrcpHistorical(), false), false);//predicts the data
                    }
                    else {
                        value = roundData(findData(weatherHistoricalArray[i].getPrcpHistorical()), false);//data already exists so finds the data to use
                    }
                    lblT1.setText("> 50");//sets the labels for displaying precipitation in millimetres
                    lblT2.setText("40 - 50");
                    lblT3.setText("30 - 40");
                    lblT4.setText("20 - 30");
                    lblT5.setText("10 - 20");
                    lblT6.setText("< 10");
                    circles.get(i).setFill(getColor(value, 50.0, 40.0, 30.0, 20.0, 10.0));//determines the color for each circle based on each location's value
                    lblUnits.setText("MILLIMETRES");
                }
                else if (checkF.isSelected() && check1.isSelected()){//this is for precipitation in inches
                    if (checkPredict()) {//determines if the data needs to be predicted
                        value = roundData(predictArray(weatherHistoricalArray[i].getPrcpHistorical(), false), false);//predicts the data
                    }
                    else {
                        value = roundData(findData(weatherHistoricalArray[i].getPrcpHistorical()), false);//data already exists so finds the data to use
                    }
                    lblT1.setText("> 2.0");//sets the labels for displaying precipitation in inches
                    lblT2.setText("1.6 - 2.0");
                    lblT3.setText("1.2 - 1.6");
                    lblT4.setText("0.8 - 1.2");
                    lblT5.setText("0.4 - 0.8");
                    lblT6.setText("< 0.4");
                    circles.get(i).setFill(getColor(value, 2.0, 1.6, 1.2, 0.8, 0.4));//determines the color for each circle based on each location's value
                    lblUnits.setText("INCHES");
                }
                else if (checkC.isSelected() && check2.isSelected()){//this is for snowfall in millimetres
                    if (checkPredict()) {//determines if the data needs to be predicted
                        value = roundData(predictArray(weatherHistoricalArray[i].getSnowHistorical(), false), false);//predicts the data
                    }
                    else {
                        value = roundData(findData(weatherHistoricalArray[i].getSnowHistorical()), false);//data already exists so finds the data to use
                    }
                    lblT1.setText("> 500");//sets the labels for displaying snowfall in millimetres
                    lblT2.setText("400 - 500");
                    lblT3.setText("300 - 400");
                    lblT4.setText("200 - 300");
                    lblT5.setText("100 - 200");
                    lblT6.setText("< 100");
                    circles.get(i).setFill(getColor(value, 500.0, 400.0, 300.0, 200.0, 100.0));//determines the color for each circle based on each location's value
                    lblUnits.setText("MILLIMETRES");
                }
                else if (checkF.isSelected() && check2.isSelected()){//this is for snowfall in inches
                    if (checkPredict()) {//determines if the data needs to be predicted
                        value = roundData(predictArray(weatherHistoricalArray[i].getSnowHistorical(), false), false);//predicts the data
                    }
                    else {
                        value = roundData(findData(weatherHistoricalArray[i].getSnowHistorical()), false);//data already exists so finds the data to use
                    }
                    lblT1.setText("> 20");//sets the labels for displaying snowfall in inches
                    lblT2.setText("16 - 20");
                    lblT3.setText("12 - 16");
                    lblT4.setText("8 - 12");
                    lblT5.setText("4 - 8");
                    lblT6.setText("< 4");
                    circles.get(i).setFill(getColor(value, 20.0, 16.0, 12.0, 8.0, 4.0));//determines the color for each circle based on each location's value
                    lblUnits.setText("INCHES");
                }
                else if (checkC.isSelected() && check3.isSelected()){//this is for average wind speech in meters per second
                    if (checkPredict()) {//determines if the data needs to be predicted
                        value = roundData(predictArray(weatherHistoricalArray[i].getAwsHistorical(), false), false);//predicts the data
                    }
                    else {
                        value = roundData(findData(weatherHistoricalArray[i].getAwsHistorical()), false);//data already exists so finds the data to use
                    }
                    lblT1.setText("> 20");//sets the labels for displaying average wind speed in meters per second
                    lblT2.setText("13 - 20");
                    lblT3.setText("8 - 13");
                    lblT4.setText("3 - 8");
                    lblT5.setText("1 - 3");
                    lblT6.setText("< 1");
                    circles.get(i).setFill(getColor(value, 20.0, 13.0, 8.0, 3.0, 1.0));//determines the color for each circle based on each location's value
                    lblUnits.setText("METERS PER SECOND");
                }
                else if (checkF.isSelected() && check3.isSelected()){//this is for average wind speech in miles per hour
                    if (checkPredict()) {//determines if the data needs to be predicted
                        value = roundData(predictArray(weatherHistoricalArray[i].getAwsHistorical(), false), false);//predicts the data
                    }
                    else {
                        value = roundData(findData(weatherHistoricalArray[i].getAwsHistorical()), false);//data already exists so finds the data to use
                    }
                    lblT1.setText("> 44.7");//sets the labels for displaying average wind speed in miles per hour
                    lblT2.setText("29.0 - 44.7");
                    lblT3.setText("18.0 - 29.0");
                    lblT4.setText("6.7 - 18.0");
                    lblT5.setText("2.2 - 6.7");
                    lblT6.setText("< 2.2");
                    circles.get(i).setFill(getColor(value, 44.7, 29.0, 18.0, 6.7, 2.2));//determines the color for each circle based on each location's value
                    lblUnits.setText("MILES PER HOUR");
                }
                check1.setText("PRECIPITATION");
                check2.setText("SNOW");
                check3.setText("WIND SPEED");//names each checkbox the values they will display
            }
            Tooltip tooltip = new Tooltip("PLACE: " + weatherHistoricalArray[i].getName().replace("/", " ").toUpperCase() + "\n" + "DATA: " + value);//installs a tooltip on each circle so the user can hover over each circle to get the location's name and data
            tooltip.setPrefWidth(200);
            tooltip.setWrapText(true);
            tooltip.setStyle("-fx-font: 13px \"Andale Mono\";");
            Tooltip.install(circles.get(i), tooltip);
        }
        displayData();//calls displays data, which populates the listView and graph
    }

    //determines if the date has existing data or not, and if it doesn't then determines that the program must predict values
    public boolean checkPredict() {
        if (txtYear.getText().equals("2020") && Double.parseDouble(txtMonth.getText()) > 4) {//there is no data in 2020 for May or the months after
            return true;
        }
        else if (txtYear.getText().equals("2020") && Double.parseDouble(txtMonth.getText()) == 4 && Double.parseDouble(txtDay.getText()) > 5) {//there is only data in April up to the 4th
            return true;
        }
        return false;//there is existing data
    }

    ////predicts the value based on historical data
    public String predictArray(ArrayList<String> data, boolean predictCheck) {
        if (data.size() < 12) {
            return "N/A";
        }
        else {
            WeatherPredict prediction = new WeatherPredict();
            ArrayList<Double> thisYear = new ArrayList<>();//creates an arraylist of the most recent data to be compared to
            for (int k = data.size() - 1; k > data.size() - 14; k--) {
                thisYear.add(Double.parseDouble(data.get(k).substring(11)));
            }
            ArrayList<ArrayList<Double>> yearsArray = new ArrayList<>();//creates an arraylist of arraylists of each year's data to be used to predict
            for (int i = 1; i < 11; i ++) {
                String newYear = (Integer.parseInt(date.substring(0,4)) - i) + "-" + date.substring(5);
                ArrayList<Double> predict = new ArrayList<>();
                int index = 0;
                for (int j = 0; j < data.size(); j ++) {
                    String[] x = data.get(j).split(",", -1);
                    if (x[0].equals(newYear)) {
                        index = j;//finds the location of the specific day to predict for each year
                    }
                }
                for (int j = index - 14; j < index + 15; j ++) {//adds data from two weeks before the date and two weeks after to create an arraylist of data centered around the date to predict
                    if (j >= 0) {
                        String[] y = data.get(j).split(",", -1);
                        predict.add(Double.parseDouble(y[1]));
                    }

                }
                yearsArray.add(predict);//adds each year to the arraylist of years
            }
            int emptyArray = 0;
            for (int q = 0; q < yearsArray.size(); q++) {//determines if each year has data
                if (yearsArray.get(q).size() == 0) {
                    emptyArray++;
                }
            }
            if (emptyArray == yearsArray.size()) {//if there are no years with data needed to predict, the prediction cannot be made
                return "N/A";
            }
            else {
                if (predictCheck) {//determines whether data returned shpuld have units or not based on where it is being displayed
                    return prediction.predictValue(yearsArray, thisYear) + "";//predicts the data
                }
                else {
                    return convertUnits(prediction.predictValue(yearsArray, thisYear)) + "*" + unit;//predicts the data
                }
            }
        }
    }

    //finds the data in the arraylist for the date needed for drawScreen
    public String findData(ArrayList<String> dataArray) {
        for (int i = 0; i < dataArray.size(); i ++) {
            String[] info = dataArray.get(i).split(",", -1);
            if (info[0].equals(date)) {//determines if the data is for the specific date needed
                if (Double.parseDouble(info[1]) == 1000){
                    return "N/A";//if the value is 1000, it means there is no data for that date
                }
                else {
                    return convertUnits(Double.parseDouble(info[1])) + unit;//returns the data
                }
            }
        }
        return "N/A";
    }

    //finds the data in the arraylist for the date needed for the listView
    public String findData2(ArrayList<String> dataArray, String detConvert) {
        if (checkPredict()) {//determines if the data needs to be predicted
            if (predictArray(dataArray, true).equals("N/A")) {
                return "N/A";//returns N/A if no prediction can be made due to lack of data
            }
            else {
                return listConvert(predictArray(dataArray, true), detConvert) + "*";//returns the predicted data after it has been converted
            }
        }
        else {
            for (int i = 0; i < dataArray.size(); i++) {
                String[] info = dataArray.get(i).split(",", -1);
                if (info[0].equals(date)) {//finds the data of the specific date needed
                    if (Double.parseDouble(info[1]) == 1000){
                        return "N/A";//if the value is 1000, it means there is no data for that date
                    }
                    else {
                        return listConvert(info[1], detConvert);//returns the data after it has been converted
                    }
                }
            }
            return "N/A";
        }
    }

    //conversion tool for data to be displayed in the listView
    public String listConvert(String input, String determine) {
        double newInput = Double.parseDouble(input);//turns the data into double so it can be manipulated
        if (determine.substring(0,1).equals("0")) {
            return newInput + "";//means there is no need to convert
        }
        else {
            if (determine.substring(1).equals("0")) {//means the conversion is from Celsius to Fahrenheit
                return (newInput * (9.0 / 5.0)) + 32 + "";
            }
            else if (determine.substring(1).equals("1")) {//means the conversion is from millimetres to inches
                return newInput / 25.4 + "";
            }
            else {
                return newInput * 2.237 + "";//means the conversion is from meters per second to miles per hour
            }
        }
    }

    //conversion tool for drawScreen data
    public double convertUnits(double t) {
        if (convert) {//convert is only true when the user has selected imperial units
            if (checkF.isSelected() && tempSelected) {//means the conversion is from Celsius to Fahrenheit
                return (t * (9.0 / 5.0)) + 32;
            }
            else if (checkF.isSelected() && !tempSelected && !check3.isSelected()) {//means the conversion is from millimetres to inches
                return t / 25.4;
            }
            else if (checkF.isSelected() && !tempSelected && check3.isSelected()){//means the conversion is from meters per second to miles per hour
                return t * 2.237;
            }
            else {
                return t;//if imperial units are not selected, there is no need to convert as all units are originally in metric
            }
        }
        else {
            return  t;
        }

    }

    //called when user clicks on a circle
    @FXML
    public void handleMouseClick(MouseEvent event) {//https://stackoverflow.com/questions/24302636/better-way-for-getting-id-of-the-clicked-object-in-javafx-controller
        String source = event.getPickResult().getIntersectedNode().getId();//determines the ID of the circle clicked
        placeName = weatherHistoricalArray[Integer.parseInt(source.substring(6)) - 1].getName();//finds the location based on the circle clicked
        drawScreen();//draws screen to display data for that location
    }

    //finds the average temperature, as many locations do not have data for average temperature
    public String calculateAverage2(String avg, String max, String min) {
        if (avg.equals("N/A")) {
            if (max.equals("N/A") || min.equals("N/A")) {
                return "N/A";//average temperature cannot be predicted if there is no data for maximum or minimum temperature
            }
            else {
                if (checkPredict()) {//if date has data that does not exist, then the data will have * next to it so * must bre removed in order to turn value into a double
                    return (Double.parseDouble(max.substring(0, max.length() - 1)) + Double.parseDouble(min.substring(0, min.length() - 1))) / 2 + "*";
                }
                else {//if there is no predicted data being used to predict the average temperature, there is no * so there is no need to exclude it
                    return (Double.parseDouble(max) + Double.parseDouble(min)) / 2 + "*";
                }
            }

        }
        else {//average temperature data does exist so it is used
            return avg;
        }
    }

    //populates the listView and graph
    public void displayData(){
        lblName.setText(placeName.replace("/", " ").toUpperCase());//displays the name of the location
        lst1.getItems().clear();
        lst1.setStyle("-fx-font: 13px \"Andale Mono\";");
        String tavg = "N/A";
        String tmax = "N/A";
        String tmin = "N/A";
        String prcp = "N/A";
        String snow = "N/A";
        String aws = "N/A";
        for (int i = 0; i < weatherHistoricalArray.length; i++) {
            if (weatherHistoricalArray[i].getName().equals(placeName)) {//finds the location of the place's data
                if (checkC.isSelected()) {//data will all be metric
                    if (!calculateAverage2(findData2(weatherHistoricalArray[i].getTavgHistorical(), "00"), findData2(weatherHistoricalArray[i].getTmaxHistorical(), "00"), findData2(weatherHistoricalArray[i].getTminHistorical(), "00")).equals("N/A")) {//finds the average temperature
                        tavg = roundData(calculateAverage2(findData2(weatherHistoricalArray[i].getTavgHistorical(), "00"), findData2(weatherHistoricalArray[i].getTmaxHistorical(), "00"), findData2(weatherHistoricalArray[i].getTminHistorical(), "00")), true) + " °C";
                    }
                    if (!findData2(weatherHistoricalArray[i].getTmaxHistorical(), "00").equals("N/A")) {//finds the maximum temperature
                        tmax = roundData(findData2(weatherHistoricalArray[i].getTmaxHistorical(), "00"), true) + " °C";
                    }
                    if (!findData2(weatherHistoricalArray[i].getTminHistorical(), "00").equals("N/A")) {//finds the minimum temperature
                        tmin = roundData(findData2(weatherHistoricalArray[i].getTminHistorical(), "00"), true) + " °C";
                    }
                    if (!findData2(weatherHistoricalArray[i].getPrcpHistorical(), "00").equals("N/A")) {//finds the precipitation
                        prcp = roundData(findData2(weatherHistoricalArray[i].getPrcpHistorical(), "00"), true) + " mm";
                    }
                    if (!findData2(weatherHistoricalArray[i].getSnowHistorical(), "00").equals("N/A")) {//finds the snowfall
                        snow = roundData(findData2(weatherHistoricalArray[i].getSnowHistorical(), "00"), true) + " in";
                    }
                    if (!findData2(weatherHistoricalArray[i].getAwsHistorical(), "00").equals("N/A")) {//finds the average wind speed
                        aws = roundData(findData2(weatherHistoricalArray[i].getAwsHistorical(), "00"), true) + " m/s";
                    }
                }
                else {//data will all be imperial
                    if (!calculateAverage2(findData2(weatherHistoricalArray[i].getTavgHistorical(), "10"), findData2(weatherHistoricalArray[i].getTmaxHistorical(), "10"), findData2(weatherHistoricalArray[i].getTminHistorical(), "10")).equals("N/A")) {//finds the average temperature
                        tavg = roundData(calculateAverage2(findData2(weatherHistoricalArray[i].getTavgHistorical(), "10"), findData2(weatherHistoricalArray[i].getTmaxHistorical(), "10"), findData2(weatherHistoricalArray[i].getTminHistorical(), "10")), true) + " °F";
                    }
                    if (!findData2(weatherHistoricalArray[i].getTmaxHistorical(), "10").equals("N/A")) {//finds the maximum temperature
                        tmax = roundData(findData2(weatherHistoricalArray[i].getTmaxHistorical(), "10"), true) + " °F";
                    }
                    if (!findData2(weatherHistoricalArray[i].getTminHistorical(), "10").equals("N/A")) {//finds the minimum temperature
                        tmin = roundData(findData2(weatherHistoricalArray[i].getTminHistorical(), "10"), true) + " °F";
                    }
                    if (!findData2(weatherHistoricalArray[i].getPrcpHistorical(), "11").equals("N/A")) {//finds the precipitation
                        prcp = roundData(findData2(weatherHistoricalArray[i].getPrcpHistorical(), "11"), true) + " in";
                    }
                    if (!findData2(weatherHistoricalArray[i].getSnowHistorical(), "11").equals("N/A")) {//finds the snowfall
                        snow = roundData(findData2(weatherHistoricalArray[i].getSnowHistorical(), "11"), true) + " in";
                    }
                    if (!findData2(weatherHistoricalArray[i].getAwsHistorical(), "12").equals("N/A")) {//finds the average wind speed
                        aws = roundData(findData2(weatherHistoricalArray[i].getAwsHistorical(), "12"), true) + " mph";
                    }
                }
                lst1.getItems().add("AVERAGE TEMPERATURE: " + tavg);//populates the listView will appropriate values
                lst1.getItems().add("MAXIMUM TEMPERATURE: " + tmax);
                lst1.getItems().add("MINIMUM TEMPERATURE: " + tmin);
                lst1.getItems().add("PRECIPITATION: " + prcp);
                lst1.getItems().add("SNOW: " + snow);
                lst1.getItems().add("WIND SPEED: " + aws);
            }
        }
        if (isSimulation) {//during simulation, the buttons for simulations will be disabled
            btnSimulateHE.setDisable(true);
            btnSimulateHS.setDisable(true);
            btnSimulateY.setDisable(true);
        }
        else {
            if (!checkPredict()) {
                graph(arrayGraph());//if there is no prediction of data, the graph will be populated and buttons for simulations will be allowed
                btnSimulateHE.setDisable(false);
                btnSimulateHS.setDisable(false);
                btnSimulateY.setDisable(false);
            }
            else {//if there is prediction of data, the graph will be unused and the buttons for simulations will be disabled
                btnSimulateHE.setDisable(true);
                btnSimulateHS.setDisable(true);
                btnSimulateY.setDisable(true);
            }
        }

    }

    //graphs the data from an arraylist
    @FXML
    private void graph(ArrayList<Double> array){
        lineChart.getData().clear();
        if (array.size() != 0) {//will graph the data if the arraylist has items in it
            XYChart.Series series = new XYChart.Series();
            series.getData().clear();
            for (int i = 0; i < array.size(); i++) {
                series.getData().add(new XYChart.Data("" + i, array.get(i)));//adds all the data into the series
            }
            lineChart.getData().add(series);//displays the graph with all the data
        }

    }

    //determines the arraylist of data to be graphed
    public ArrayList<Double> arrayGraph() {
        ArrayList<Double> graphArray = new ArrayList<>();
        for (int i = 0; i < weatherHistoricalArray.length; i ++) {
            if (weatherHistoricalArray[i].getName().equals(placeName) && tempSelected && check1.isSelected()) {//creates an array of data for average temperature
                graphArray = dataGraph(weatherHistoricalArray[i].getTavgHistorical());
            }
            else if (weatherHistoricalArray[i].getName().equals(placeName) && tempSelected && check2.isSelected()) {//creates an array of data for maximum temperature
                graphArray = dataGraph(weatherHistoricalArray[i].getTmaxHistorical());
            }
            else if (weatherHistoricalArray[i].getName().equals(placeName) && tempSelected && check3.isSelected()) {//creates an array of data for minimum temperature
                graphArray = dataGraph(weatherHistoricalArray[i].getTminHistorical());
            }
            else if (weatherHistoricalArray[i].getName().equals(placeName) && !tempSelected && check1.isSelected()) {//creates an array of data for precipitation
                graphArray = dataGraph(weatherHistoricalArray[i].getPrcpHistorical());
            }
            else if (weatherHistoricalArray[i].getName().equals(placeName) && !tempSelected && check2.isSelected()) {//creates an array of data for snowfall
                graphArray = dataGraph(weatherHistoricalArray[i].getSnowHistorical());
            }
            else if (weatherHistoricalArray[i].getName().equals(placeName) && !tempSelected && check3.isSelected()) {//creates an array of data for average wind speed
                graphArray = dataGraph(weatherHistoricalArray[i].getAwsHistorical());
            }
        }
        return graphArray;//returns the arraylist of data
    }

    //populates the arraylist of data to be graphed
    public ArrayList<Double> dataGraph(ArrayList<String> a) {
        ArrayList<Double> b = new ArrayList<>();
        for (int j = 0; j < a.size() - 1; j ++) {
            if (a.get(j).substring(0, 10).equals(date)) {//finds the data for the specific date
                if (j - 50 >= 0) {
                    for (int k = j; k > j - 50; k--) {//will add data from the past 50 days from the specific date into the array
                        b.add(Double.parseDouble(a.get(k).substring(11)));
                    }
                }
                else {
                    for (int k = j; k >= 0; k--) {//if there is not 50 days before, then adds data as many days as possible from the specific date into the array
                        b.add(Double.parseDouble(a.get(k).substring(11)));
                    }
                }
            }
        }
        return b;//returns the array
    }

    //determines what happens when user checks for metric units
    @FXML
    public void handleCheckC() {
        if (checkF.isSelected()) {//checks this box if it isnt checked
            checkF.setSelected(false);
            checkC.setSelected(true);
            convert = false;//cant convert as units are originally in metric
            if (tempSelected) {//temperature is in Celsius
                unit = " °C ";
            }
            else {
               if (check3.isSelected()) {//average wind speed is in meters per second
                   unit = " m/s";
               }
               else {//precipitation and snowfall are in millimetres
                   unit = " mm ";
               }
            }
            drawScreen();//draws the screen to reflect changes
        }
        else {
            checkC.setSelected(false);//unchecks this box if it is already checked
            checkF.setSelected(true);
            convert = true;//can convert as now imperial units are selected
            if (tempSelected) {//temperature is in Fahrenheit
                unit = " °F ";
            }
            else {
                if (check3.isSelected()) {//average wind speed is in miles per hour
                    unit = " mph";
                }
                else {//precipitation and snowfall are in inches
                    unit = " in ";
                }
            }
            drawScreen();//draws the screen to reflect changes
        }
    }

    //determines what happens when user checks for imperial units
    @FXML
    public void handleCheckF() {
        if (checkC.isSelected()) {//checks this box if it isnt checked
            checkC.setSelected(false);
            checkF.setSelected(true);
            convert = true;//can convert as now imperial units are selected
            if (tempSelected) {//temperature is in Fahrenheit
                unit = " °F ";
            }
            else {
                if (check3.isSelected()) {//average wind speed is in miles per hour
                    unit = " mph";
                }
                else {
                    unit = " in ";//precipitation and snowfall are in inches
                }
            }
            drawScreen();//draws the screen to reflect changes
        }
        else {
            checkF.setSelected(false);//unchecks this box if it is already checked
            checkC.setSelected(true);
            convert = false;//cant convert as units are originally in metric
            if (tempSelected) {//temperature is in Celsius
                unit = " °C ";
            }
            else {
                if (check3.isSelected()) {//average wind speed is in meters per second
                    unit = " m/s";
                }
                else {//precipitation and snowfall are in millimetres
                    unit = " mm ";
                }
            }
            drawScreen();//draws the screen to reflect changes
        }
    }

    //determines what happens when first checkbox is selected
    @FXML
    public void handleCheck1() {
        check1.setSelected(true);
        check2.setSelected(false);
        check3.setSelected(false);
        if (tempSelected) {//if data is for temperature, first checkbox is for average temperature
            if (checkC.isSelected()) {//metric units for temperature is Celsius
                unit = " °C ";
            }
            else {//imperial units for temperature is Fahrenheit
                unit = " °F ";
            }
        }
        else {//if data is for other, first checkbox is for precipitation
            if (checkC.isSelected()) {//metric units for precipitation is millimetres
                unit = " mm ";
            }
            else {//imperial units for precipitation is inches
                unit = " in ";
            }
        }
        drawScreen();//draws the screen to reflect changes
    }

    //determines what happens when second checkbox is selected
    @FXML
    public void handleCheck2() {
        check2.setSelected(true);
        check3.setSelected(false);
        check1.setSelected(false);
        if (tempSelected) {//if data is for temperature, second checkbox is for maximum temperature
            if (checkC.isSelected()) {//metric units for temperature is Celsius
                unit = " °C ";
            }
            else {//imperial units for temperature is Fahrenheit
                unit = " °F ";
            }
        }
        else {//if data is for other, second checkbox is for snowfall
            if (checkC.isSelected()) {//metric units for snowfall is millimetres
                unit = " mm ";
            }
            else {//imperial units for snowfall is inches
                unit = " in ";
            }
        }
        drawScreen();//draws the screen to reflect changes
    }

    ///determines what happens when third checkbox is selected
    @FXML
    public void handleCheck3() {
        check3.setSelected(true);
        check2.setSelected(false);
        check1.setSelected(false);
        if (tempSelected) {//if data is for temperature, third checkbox is for minimum temperature
            if (checkC.isSelected()) {//metric units for temperature is Celsius
                unit = " °C ";
            }
            else {//imperial units for temperature is Fahrenheit
                unit = " °F ";
            }
        }
        else {//if data is for other, second checkbox is for average wind speed
            if (checkC.isSelected()) {//metric units for average wind speed is meters per second
                unit = " m/s";
            }
            else {//imperial units for average wind speed is miles per hour
                unit = " mph";
            }
        }
        drawScreen();//draws the screen to reflect changes
    }

    //determines what happens when user makes a choicebox selection
    public void handleChoice1(String choice) {//parameter is the value the user selected
        if (choice.equals("TEMPERATURE")) {//user selected temperature
            tempSelected = true;
            check1.setText("AVERAGE TEMPERATURE");//labels checkboxes appropriately for temperature
            check2.setText("MAXIMUM TEMPERATURE");
            check3.setText("MINIMUM TEMPERATURE");
            drawScreen();//draws the screen to reflect changes

        }
        else {//user selected other
            tempSelected = false;
            check1.setText("PRECIPITATION");//labels checkboxes appropriately for other
            check2.setText("SNOW");
            check3.setText("WIND SPEED");
            drawScreen();//draws the screen to reflect changes
        }
    }

    //determines what happens when user wants to go back a day
    @FXML
    public void handlebtnLeft() {
        if (Integer.parseInt(txtMonth.getText()) < 10) {//redisplays values without a 0 in front that need it
            txtMonth.setText("0" + Integer.parseInt(txtMonth.getText()));
        }
        if (Integer.parseInt(txtDay.getText()) < 10) {//redisplays values without a 0 in front that need it
            txtDay.setText("0" + Integer.parseInt(txtDay.getText()));
        }
        date = changeDate(txtYear.getText() + "-" + txtMonth.getText() + "-" + txtDay.getText(), 1);//gets the date of the previous day
        txtYear.setText(date.substring(0, 4));//sets each textbox to the appropriate value
        txtMonth.setText(date.substring(5,7));
        txtDay.setText(date.substring(8));
        if (checkDate()) {//determines if date is valid
            drawScreen();
        }
        else {
           lblDate.setText("NOT A VALID DATE");
        }

    }

    //determines what happens when user wants to go forward a day
    @FXML
    public void handlebtnRight() {
        if (Integer.parseInt(txtMonth.getText()) < 10) {//redisplays values without a 0 in front that need it
            txtMonth.setText("0" + Integer.parseInt(txtMonth.getText()));
        }
        if (Integer.parseInt(txtDay.getText()) < 10) {//redisplays values without a 0 in front that need it
            txtDay.setText("0" + Integer.parseInt(txtDay.getText()));
        }
        date = changeDate(txtYear.getText() + "-" + txtMonth.getText() + "-" + txtDay.getText(), 0);//gets the date of the next day
        txtYear.setText(date.substring(0, 4));//sets each textbox to the appropriate value
        txtMonth.setText(date.substring(5,7));
        txtDay.setText(date.substring(8));
        if (checkDate()) {//determines if date is valid
            drawScreen();
        }
        else {
            lblDate.setText("NOT A VALID DATE");
        }
    }

    //determines if date is valid or not
    @FXML
    private boolean checkDate() {
        int year = 0;
        int month = 0;
        int day = 0;
        try {
            year = Integer.parseInt(txtYear.getText());//checks to see if user entered an integer
        }
        catch(NumberFormatException ex) {
            lblDate.setText("NOT A VALID DATE");
            return false;
        }
        try {
            month = Integer.parseInt(txtMonth.getText());//checks to see if user entered an integer
        }
        catch(NumberFormatException ex) {
            lblDate.setText("NOT A VALID DATE");
            return false;
        }
        try {
            day = Integer.parseInt(txtDay.getText());//checks to see if user entered an integer
        }
        catch(NumberFormatException ex) {
            lblDate.setText("NOT A VALID DATE");
            return false;
        }
        if (year > 2021 || year < 2010 || month > 13 || month < 0 || day < 0 || day > 31) {//checks if year is later than 2020, before 2010, and if the month and day dont exist
            lblDate.setText("NOT A VALID DATE");
            return false;
        }
        if ((month == 9 || month == 4 || month == 6 || month == 11) && day > 30) {//these months do not have 31 days
            lblDate.setText("NOT A VALID DATE");
            return false;
        }
        if (month == 2) {
            if ((year % 4 == 0)) {//determines if year is leap year
                if (day > 29) {//leap years only have 29 days
                    lblDate.setText("NOT A VALID DATE");
                    return false;
                }
            }
            else  {
                if (day > 28) {//non leap years only have 28 days
                    lblDate.setText("NOT A VALID DATE");
                    return false;
                }
            }
        }
        return true;
    }

    //sets the date the user selected if it is valid
    @FXML
    private void handlebtnDate() {
        if (checkDate()) {//checks if date is valid
            if (Integer.parseInt(txtMonth.getText()) < 10) {//redisplays values without a 0 in front that need it
                txtMonth.setText("0" + Integer.parseInt(txtMonth.getText()));
            }
            if (Integer.parseInt(txtDay.getText()) < 10) {//redisplays values without a 0 in front that need it
                txtDay.setText("0" + Integer.parseInt(txtDay.getText()));
            }
            date = txtYear.getText() + "-" + txtMonth.getText() + "-" + txtDay.getText();//reformats date
            drawScreen();//draws screen to reflect changes
        }

    }//changes the date either one dat forward or backwards
    public String changeDate(String d, int z) {
        int year = Integer.parseInt(d.substring(0, 4));//sets year, day and month as ints for manipulation
        int month = Integer.parseInt(d.substring(5, 7));
        int day =  Integer.parseInt(d.substring(8));
        String monthReform;
        String dayReform;
        if (z == 0) {//user wants to go forward a day
            if ((day == 31 && (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12)) ||
                    (day == 30 && (month == 4 || month == 6 || month == 9 || month == 11)) || (day == 28 && month == 2 && year % 4 != 0)) {//uses the months that have 31 and 30 days to determine if a new month should start
                day = 1;
                if (month == 12) {//going past December 31 is January of a new year
                    month = 1;
                    year++;
                } else {//start of new month
                    month++;
                }
            }
            else {//day advances by one
                day++;
            }
        }
        else {//user wants to go back a day
            if (day == 1 && (month == 1 || month == 2 || month == 4 || month == 6 || month == 8 || month == 9 || month == 11)) {//the months before these months have 31 days
                day = 31;
                if (month == 1) {//a day back from January 1st is the last day of the year of last year
                    month = 12;
                    year --;
                }
                else {//end of last month
                    month--;
                }
            }
            else if (day == 1 && (month == 5 || month == 7 || month == 10 || month == 12)) {//the months before these months have 30 days
                day = 30;
                month--;//end of last month
            }
            else if (day == 1 && month == 3) {//the month before is February
                if (year % 4 == 0) {//on leap years, February has 29 days
                    day = 29;
                    month-- ;
                }
                else {//on non leap years, February has 28 days
                    day = 28;
                    month-- ;
                }
            }
            else {//day goes back by one
                day--;
            }
        }
        if (month < 10) {//redisplays values without a 0 in front that need it
            monthReform = "0" + month;
        }
        else {
            monthReform = "" + month;
        }
        if (day < 10) {//redisplays values without a 0 in front that need it
            dayReform = "0" + day;
        }
        else {
            dayReform = "" + day;
        }
        return year + "-" + monthReform + "-" + dayReform;//returns new reformatted date
    }

    //simulates Yellowstone supervolcano eruption
    @FXML
    public void handleBtnSimulateY() {//Yellowstone will most significantly impact temperature and precipitation
        ArrayList<String> firstArray = new ArrayList<>();//arraylist of average temperatures
        ArrayList<String> secondArray = new ArrayList<>();//arraylist of precipitation
        for (int i = 0; i < 45; i ++) {//adds data from each location
            if (checkC.isSelected()) {//adds data in metric units
                if (!findData(weatherHistoricalArray[i].getTavgHistorical()).equals("N/A")) {//adds average temperature data
                    firstArray.add(findData(weatherHistoricalArray[i].getTavgHistorical()).substring(0, findData(weatherHistoricalArray[i].getTavgHistorical()).length() - 4) + " " + weatherHistoricalArray[i].getName());
                }
                else {//if data doesnt exist, add N/A to maintain size of arraylist
                    firstArray.add("N/A");
                }
                if (!findData(weatherHistoricalArray[i].getPrcpHistorical()).equals("N/A")) {//adds precipitation data
                    secondArray.add(findData(weatherHistoricalArray[i].getPrcpHistorical()).substring(0, findData(weatherHistoricalArray[i].getPrcpHistorical()).length() - 4)+ " " + weatherHistoricalArray[i].getName());
                }
                else {//if data doesnt exist, add N/A to maintain size of arraylist
                    secondArray.add("N/A");
                }
            }
            else {//adds data in imperical units
                if (!calculateAverage2(findData2(weatherHistoricalArray[i].getTavgHistorical(), "10"), findData2(weatherHistoricalArray[i].getTmaxHistorical(), "10"), findData2(weatherHistoricalArray[i].getTminHistorical(), "10")).equals("N/A")) {//adds average temperature data
                    firstArray.add(calculateAverage2(findData2(weatherHistoricalArray[i].getTavgHistorical(), "10"), findData2(weatherHistoricalArray[i].getTmaxHistorical(), "10"), findData2(weatherHistoricalArray[i].getTminHistorical(), "10")) + " " + weatherHistoricalArray[i].getName());
                }
                else {//if data doesnt exist, add N/A to maintain size of arraylist
                    firstArray.add("N/A");
                }
                if (!findData2(weatherHistoricalArray[i].getPrcpHistorical(), "11").equals("N/A")) {//adds precipitation data
                    secondArray.add(findData2(weatherHistoricalArray[i].getPrcpHistorical(), "11") + " " + weatherHistoricalArray[i].getName());
                }
                else {//if data doesnt exist, add N/A to maintain size of arraylist
                    secondArray.add("N/A");
                }
            }
        }
        String unit1;
        String unit2;
        if (checkC.isSelected()) {//determines units for conversion
            unit1 = "N";
            unit2 = "N";
        }
        else {
            unit1 = "YT";
            unit2 = "YP";
        }
        handlebtnSimulate(firstArray, secondArray, "Y", unit1, unit2);//calls simulate function to simulate data for the 2 arraylists
    }

    //simulates hurricane on the Gulf Coast
    @FXML
    public void handleBtnSimulateHS() {//hurricanes will most significantly impact precipitation and average wind speed
        ArrayList<String> firstArray = new ArrayList<>();//arraylist of average wind speed
        ArrayList<String> secondArray = new ArrayList<>();//arraylist of precipitation
        for (int i = 0; i < 45; i ++) {//adds data from each location
            if (checkC.isSelected()) {//adds data in metric units
                if (!findData(weatherHistoricalArray[i].getAwsHistorical()).equals("N/A")) {//adds average wind speed data
                    firstArray.add(findData(weatherHistoricalArray[i].getAwsHistorical()).substring(0, findData(weatherHistoricalArray[i].getAwsHistorical()).length() - 4) + " " + weatherHistoricalArray[i].getName());
                }
                else {//if data doesnt exist, add N/A to maintain size of arraylist
                    firstArray.add("N/A");
                }
                if (!findData(weatherHistoricalArray[i].getPrcpHistorical()).equals("N/A")) {//adds precipitation data
                    secondArray.add(findData(weatherHistoricalArray[i].getPrcpHistorical()).substring(0, findData(weatherHistoricalArray[i].getPrcpHistorical()).length() - 4)+ " " + weatherHistoricalArray[i].getName());
                }
                else {//if data doesnt exist, add N/A to maintain size of arraylist
                    secondArray.add("N/A");
                }
            }
            else {//adds data in imperical units
                if (!findData2(weatherHistoricalArray[i].getAwsHistorical(), "12").equals("N/A")) {//adds average wind speed data
                    firstArray.add(findData2(weatherHistoricalArray[i].getAwsHistorical(), "12") + " " + weatherHistoricalArray[i].getName());
                }
                else {//if data doesnt exist, add N/A to maintain size of arraylist
                    firstArray.add("N/A");
                }
                if (!findData2(weatherHistoricalArray[i].getPrcpHistorical(), "11").equals("N/A")) {//adds precipitation data
                    secondArray.add(findData2(weatherHistoricalArray[i].getPrcpHistorical(), "11") + " " + weatherHistoricalArray[i].getName());
                }
                else {//if data doesnt exist, add N/A to maintain size of arraylist
                    secondArray.add("N/A");
                }
            }
        }
        String unit1;
        String unit2;
        if (checkC.isSelected()) {//determines units for conversion
            unit1 = "N";
            unit2 = "N";
        }
        else {
            unit1 = "YA";
            unit2 = "YP";
        }
        handlebtnSimulate(firstArray, secondArray, "HS", unit1, unit2);//calls simulate function to simulate data for the 2 arraylists
    }

    //simulates hurricane on the East Coast
    @FXML
    public void handleBtnSimulateHE() {//hurricanes will most significantly impact precipitation and average wind speed
        ArrayList<String> firstArray = new ArrayList<>();//arraylist of average wind speed
        ArrayList<String> secondArray = new ArrayList<>();//arraylist of precipitation
        for (int i = 0; i < 45; i ++) {//adds data from each location
            if (checkC.isSelected()) {//adds data in metric units
                if (!findData(weatherHistoricalArray[i].getAwsHistorical()).equals("N/A")) {//adds average wind speed data
                    firstArray.add(findData(weatherHistoricalArray[i].getAwsHistorical()).substring(0, findData(weatherHistoricalArray[i].getAwsHistorical()).length() - 4) + " " + weatherHistoricalArray[i].getName());
                }
                else {//if data doesnt exist, add N/A to maintain size of arraylist
                    firstArray.add("N/A");
                }
                if (!findData(weatherHistoricalArray[i].getPrcpHistorical()).equals("N/A")) {//adds precipitation data
                    secondArray.add(findData(weatherHistoricalArray[i].getPrcpHistorical()).substring(0, findData(weatherHistoricalArray[i].getPrcpHistorical()).length() - 4)+ " " + weatherHistoricalArray[i].getName());
                }
                else {//if data doesnt exist, add N/A to maintain size of arraylist
                    secondArray.add("N/A");
                }
            }
            else {//adds data in imperical units
                if (!findData2(weatherHistoricalArray[i].getAwsHistorical(), "12").equals("N/A")) {//adds average wind speed data
                    firstArray.add(findData2(weatherHistoricalArray[i].getAwsHistorical(), "12") + " " + weatherHistoricalArray[i].getName());
                }
                else {//if data doesnt exist, add N/A to maintain size of arraylist
                    firstArray.add("N/A");
                }
                if (!findData2(weatherHistoricalArray[i].getPrcpHistorical(), "11").equals("N/A")) {//adds precipitation data
                    secondArray.add(findData2(weatherHistoricalArray[i].getPrcpHistorical(), "11") + " " + weatherHistoricalArray[i].getName());
                }
                else {//if data doesnt exist, add N/A to maintain size of arraylist
                    secondArray.add("N/A");
                }
            }
        }
        String unit1;
        String unit2;
        if (checkC.isSelected()) {//determines units for conversion
            unit1 = "N";
            unit2 = "N";
        }
        else {
            unit1 = "YA";
            unit2 = "YP";
        }
        handlebtnSimulate(firstArray, secondArray, "HE", unit1, unit2);//calls simulate function to simulate data for the 2 arraylists
    }

    //converts units based on a specific method
    public Double convertSimUnits(double value, String u) {
        if (u.equals("N")) {//means there is no conversion needed
            return value;
        }
        else if (u.equals("YT")) {//means conversion is from Celsius to Fahrenheit
            return (value * (9.0 / 5.0)) + 32;
        }
        else if (u.equals("YP")) {//means conversion is from millimetres to inches
            return value / 25.4;
        }
        else {//means conversion is from meters per second to miles per hour
            return value * 2.237;
        }
    }

    //simulates changes to the 2 arraylists and displays the map, listView, and graph
    @FXML
    public void handlebtnSimulate(ArrayList<String> simulateArray1, ArrayList<String> simulateArray2, String disaster, String convert1, String convert2) {//https://www.nhc.noaa.gov/outreach/history/
        isSimulation = true;
        startSimulation(true);//disables appropriate iems
        WeatherSimulate weatherSimulate = new WeatherSimulate();
        ArrayList<Double> simGraph1 = new ArrayList<>();//arraylist of new data to be graphed
        ArrayList<Double> simGraph2 = new ArrayList<>();//arraylist of new data to be graphed
        count = 0;
        timer = System.nanoTime();
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (now - timer> 8000000000.0 && count == 0) {//every 8 seconds something will happen
                    if (disaster.equals("Y")) {//specifies Yellowstone
                        imgSim.setImage(new Image("resources/volcano.jpg"));//draws picture of volcano
                        imgSim.setLayoutX(150);//sets location of image
                        imgSim.setLayoutY(90);
                        lblSim.setText("THE ERUPTION OF THE SUPERVOLCANO WILL SEND OUT LAVA IN A 40 MILE RADIUS AND VOLCANIC ASH MILES INTO THE AIR");
                        simGraph2.add(weatherSimulate.findAverage(simulateArray2, disaster));//finds the average value before the simulation begins
                        simGraph1.add(weatherSimulate.findAverage(simulateArray1, disaster));
                        weatherSimulate.doSimulation(simulateArray1, disaster, convertSimUnits(0, convert1), convertSimUnits(0, convert1), convertSimUnits(0, convert1), convertSimUnits(0, convert1), convertSimUnits(0, convert1), convertSimUnits(0, convert1), convertSimUnits(0, convert1));//returns arraylist of modified values
                        weatherSimulate.doSimulation(simulateArray2, disaster, convertSimUnits(500, convert2), convertSimUnits(400, convert2), convertSimUnits(50, convert2), convertSimUnits(20, convert2), convertSimUnits(5, convert2), convertSimUnits(1, convert2), convertSimUnits(0, convert2));
                        drawScreen2(simulateArray2, "PRECIPITATION", checkC.isSelected(), disaster, simGraph2, 0);//draws screen to reflect changes in precipitation
                    }
                    else if (disaster.equals("HS")) {//specifies Gulf Coast hurricane
                        imgSim.setImage(new Image("resources/hurricane.jpg"));//draws picture of hurricane
                        imgSim.setLayoutX(430);//sets location of image
                        imgSim.setLayoutY(330);
                        lblSim.setText("THE HURRICANE WILL FORM FROM A COMBINATION OF TROPICAL WAVES, FRONTS AND TROUGHS, AND WILL MAKE LANDFALL NEAR FLORIDA");
                        simGraph2.add(weatherSimulate.findAverage(simulateArray2, disaster));//finds the average value before the simulation begins
                        simGraph1.add(weatherSimulate.findAverage(simulateArray1, disaster));
                        weatherSimulate.doSimulation(simulateArray1, disaster, convertSimUnits(47, convert1), convertSimUnits(0, convert1), convertSimUnits(0, convert1), convertSimUnits(0, convert1), convertSimUnits(0, convert1), convertSimUnits(0, convert1), convertSimUnits(0, convert1));//returns arraylist of modified values
                        weatherSimulate.doSimulation(simulateArray2, disaster, convertSimUnits(300, convert2), convertSimUnits(0, convert2), convertSimUnits(0, convert2), convertSimUnits(0, convert2), convertSimUnits(0, convert2), convertSimUnits(0, convert2), convertSimUnits(0, convert2));
                        drawScreen2(simulateArray1, "WIND SPEED", checkC.isSelected(), disaster, simGraph1, 0);//draws screen to reflect changes in wind speed
                    }
                    else {//specifies East Coast hurricane
                        imgSim.setImage(new Image("resources/hurricane.jpg"));//draws picture of hurricane
                        imgSim.setLayoutX(496);//sets location of image
                        imgSim.setLayoutY(300);
                        lblSim.setText("STARTING FIRST AS A TROPICAL WAVE AND THEN EVOLVING INTO A TROPICAL STORM, THE HURRICANE WILL MAKE LANDFALL NEAR FLORIDA");
                        simGraph2.add(weatherSimulate.findAverage(simulateArray2, disaster));//finds the average value before the simulation begins
                        simGraph1.add(weatherSimulate.findAverage(simulateArray1, disaster));
                        weatherSimulate.doSimulation(simulateArray1, disaster, convertSimUnits(55, convert1), convertSimUnits(0, convert1), convertSimUnits(0, convert1), convertSimUnits(0, convert1), convertSimUnits(0, convert1), convertSimUnits(0, convert1), convertSimUnits(0, convert1));//returns arraylist of modified values
                        weatherSimulate.doSimulation(simulateArray2, disaster, convertSimUnits(230, convert2), convertSimUnits(0, convert2), convertSimUnits(0, convert2), convertSimUnits(0, convert2), convertSimUnits(0, convert2), convertSimUnits(0, convert2), convertSimUnits(0, convert2));
                        drawScreen2(simulateArray1, "WIND SPEED", checkC.isSelected(), disaster, simGraph1, 0);//draws screen to reflect changes in wind speed
                    }
                    count++;//determines next action
                    timer = System.nanoTime();//resets time
                }
                else if (now - timer> 8000000000.0 && count == 1) {
                    if (disaster.equals("Y")) {
                        drawScreen2(simulateArray1, "TEMPERATURE", checkC.isSelected(), disaster, simGraph1, 0);//draws screen to reflect changes in temperature
                    }
                    else {
                        drawScreen2(simulateArray2, "PRECIPITATION", checkC.isSelected(), disaster, simGraph2, 0);//draws screen to reflect changes in precipitation
                    }
                    count++;//determines next action
                    timer = System.nanoTime();//resets time
                }
                else if (now - timer > 8000000000.0 && count == 2) {
                    if (disaster.equals("Y")) {
                        lblSim.setText("THE UMBRELLA CLOUD OF ASH WOULD BURY THE NORTHERN ROCKIE MOUNTAINS AND NEARBY STATES, SPREADING TO THE MIDWEST AND BOTH COASTS ");
                        weatherSimulate.doSimulation(simulateArray1, disaster, convertSimUnits(0, convert1), convertSimUnits(0, convert1), convertSimUnits(0, convert1), convertSimUnits(0, convert1), convertSimUnits(0, convert1), convertSimUnits(0, convert1), convertSimUnits(0, convert1));//returns arraylist of modified values
                        weatherSimulate.doSimulation(simulateArray2, disaster, convertSimUnits(500, convert2), convertSimUnits(300, convert2), convertSimUnits(40, convert2), convertSimUnits(10, convert2), convertSimUnits(8, convert2), convertSimUnits(3, convert2), convertSimUnits(1, convert2));
                        drawScreen2(simulateArray2, "PRECIPITATION", checkC.isSelected(), disaster, simGraph2, 1);//draws screen to reflect changes in precipitation
                    }
                    else if (disaster.equals("HS")) {
                        imgSim.setLayoutX(396);//moves image
                        imgSim.setLayoutY(305);
                        lblSim.setText("MOVING SOUTHWEST, THE HURRICANE WILL HEAD INTO THE EASTERN GULF OF MEXICO");
                        weatherSimulate.doSimulation(simulateArray1, disaster, convertSimUnits(-20, convert1), convertSimUnits(60, convert1), convertSimUnits(0, convert1), convertSimUnits(0, convert1), convertSimUnits(0, convert1), convertSimUnits(0, convert1), convertSimUnits(0, convert1));//returns arraylist of modified values
                        weatherSimulate.doSimulation(simulateArray2, disaster, convertSimUnits(-250, convert2), convertSimUnits(230, convert2), convertSimUnits(0, convert2), convertSimUnits(0, convert2), convertSimUnits(0, convert2), convertSimUnits(0, convert2), convertSimUnits(0, convert2));
                        drawScreen2(simulateArray1, "WIND SPEED", checkC.isSelected(), disaster, simGraph1, 1);//draws screen to reflect changes in wind speed
                    }
                    else {
                        imgSim.setLayoutX(500);//moves image
                        imgSim.setLayoutY(250);
                        lblSim.setText("CURVING NORTHEAST, THE HURRICANE WILL CROSS THE FLORIDA PENINSULA");
                        weatherSimulate.doSimulation(simulateArray1, disaster, convertSimUnits(-35, convert1), convertSimUnits(37, convert1), convertSimUnits(0, convert1), convertSimUnits(0, convert1), convertSimUnits(0, convert1), convertSimUnits(0, convert1), convertSimUnits(0, convert1));//returns arraylist of modified values
                        weatherSimulate.doSimulation(simulateArray2, disaster, convertSimUnits(-200, convert2), convertSimUnits(150, convert2), convertSimUnits(0, convert2), convertSimUnits(0, convert2), convertSimUnits(0, convert2), convertSimUnits(0, convert2), convertSimUnits(0, convert2));//returns arraylist of modified values
                        drawScreen2(simulateArray1, "WIND SPEED", checkC.isSelected(), disaster, simGraph1, 1);//draws screen to reflect changes in wind speed
                    }
                    count++;//determines next action
                    timer = System.nanoTime();//resets time
                }
                else if (now - timer> 8000000000.0 && count == 3) {
                    if (disaster.equals("Y")) {
                        drawScreen2(simulateArray1, "TEMPERATURE", checkC.isSelected(), disaster, simGraph1, 1);//draws screen to reflect changes in temperature
                    }
                    else {
                        drawScreen2(simulateArray2, "PRECIPITATION", checkC.isSelected(), disaster, simGraph2, 1);//draws screen to reflect changes in precipitation
                    }
                    count++;//determines next action
                    timer = System.nanoTime();//resets time
                }
                else if (now - timer> 8000000000.0 && count == 4) {
                    if (disaster.equals("Y")) {
                        lblSim.setText("SULFUR AEROSOLS EMITTED FROM THE ERUPTION WOULD REFLECT SUNLIGHT BACK INTO THE ATMOSPHERE, COOLING REGIONAL CLIMATES");
                        weatherSimulate.doSimulation(simulateArray1, disaster, convertSimUnits(-5, convert1), convertSimUnits(-5, convert1), convertSimUnits(-5, convert1), convertSimUnits(-5, convert1), convertSimUnits(-4, convert1), convertSimUnits(-4, convert1), convertSimUnits(-2, convert1));//returns arraylist of modified values
                        weatherSimulate.doSimulation(simulateArray2, disaster, convertSimUnits(100, convert2), convertSimUnits(100, convert2), convertSimUnits(50, convert2), convertSimUnits(10, convert2), convertSimUnits(5, convert2), convertSimUnits(2, convert2), convertSimUnits(0, convert2));
                        drawScreen2(simulateArray2, "PRECIPITATION", checkC.isSelected(), disaster, simGraph2, 2);//draws screen to reflect changes in precipitation
                    }
                    else if (disaster.equals("HS")) {
                        imgSim.setLayoutX(351);//moves image
                        imgSim.setLayoutY(297);
                        lblSim.setText("THE HURRICANE WILL PASS BY THE MISSISSIPPI RIVER, HITTING THE LOUISIANA/MISSISSIPPI BORDER");
                        weatherSimulate.doSimulation(simulateArray1, disaster, convertSimUnits(-27, convert1), convertSimUnits(-40, convert1), convertSimUnits(50, convert1), convertSimUnits(0, convert1), convertSimUnits(0, convert1), convertSimUnits(0, convert1), convertSimUnits(0, convert1));//returns arraylist of modified values
                        weatherSimulate.doSimulation(simulateArray2, disaster, convertSimUnits(-50, convert2), convertSimUnits(-190, convert2), convertSimUnits(180, convert2), convertSimUnits(0, convert2), convertSimUnits(0, convert2), convertSimUnits(0, convert2), convertSimUnits(0, convert2));
                        drawScreen2(simulateArray1, "WIND SPEED", checkC.isSelected(), disaster, simGraph1, 2);//draws screen to reflect changes in wind speed
                    }
                    else {
                        imgSim.setLayoutX(536);//moves image
                        imgSim.setLayoutY(170);
                        lblSim.setText("THE HURRICANE WILL HIT THE MID-ATLANTIC STATES AND HEAD TOWARDS NEW ENGLAND");
                        weatherSimulate.doSimulation(simulateArray1, disaster, convertSimUnits(-20, convert1), convertSimUnits(-10, convert1), convertSimUnits(42, convert1), convertSimUnits(0, convert1), convertSimUnits(0, convert1), convertSimUnits(0, convert1), convertSimUnits(0, convert1));//returns arraylist of modified values
                        weatherSimulate.doSimulation(simulateArray2, disaster, convertSimUnits(-30, convert2), convertSimUnits(-80, convert2), convertSimUnits(155, convert2), convertSimUnits(0, convert2), convertSimUnits(0, convert2), convertSimUnits(0, convert2), convertSimUnits(0, convert2));
                        drawScreen2(simulateArray1, "WIND SPEED", checkC.isSelected(), disaster, simGraph1, 2);//draws screen to reflect changes in wind speed
                    }
                    count++;//determines next action
                    timer = System.nanoTime();//resets time
                }
                else if (now - timer> 8000000000.0 && count == 5) {
                    if (disaster.equals("Y")) {
                        drawScreen2(simulateArray1, "TEMPERATURE", checkC.isSelected(), disaster, simGraph1, 2);//draws screen to reflect changes in temperature
                    }
                    else {
                        drawScreen2(simulateArray2, "PRECIPITATION", checkC.isSelected(), disaster, simGraph2, 2);//draws screen to reflect changes in precipitation
                    }
                    count++;//determines next action
                    timer = System.nanoTime();//resets time
                }
                else if (now - timer> 8000000000.0 && count == 6) {
                    if (disaster.equals("Y")) {
                        lblSim.setText("AN ASH-FILLED STRATOSPHERE WOULD DARKEN THE SKY AND DECREASE GLOBAL TEMPERATURES FOR YEARS");
                        weatherSimulate.doSimulation(simulateArray1, disaster, convertSimUnits(-7, convert1), convertSimUnits(-7, convert1), convertSimUnits(-6, convert1), convertSimUnits(-6, convert1), convertSimUnits(-6, convert1), convertSimUnits(-5, convert1), convertSimUnits(-4, convert1));//returns arraylist of modified values
                        weatherSimulate.doSimulation(simulateArray2, disaster, convertSimUnits(0, convert2), convertSimUnits(0, convert2), convertSimUnits(0, convert2), convertSimUnits(0, convert2), convertSimUnits(0, convert2), convertSimUnits(0, convert2), convertSimUnits(0, convert2));
                        drawScreen2(simulateArray2, "PRECIPITATION", checkC.isSelected(), disaster, simGraph2, 3);//draws screen to reflect changes in precipitation
                    }
                    else if (disaster.equals("HS")) {
                        imgSim.setLayoutX(308);//moves image
                        imgSim.setLayoutY(290);
                        lblSim.setText("AFTER PASSING BY THE TEXAS/LOUISIANA BORDER, THE HURRICANE WILL SUBSIDE AND EVENTUALLY BE ABSORBED BY A FRONTAL ZONE");
                        weatherSimulate.doSimulation(simulateArray1, disaster, convertSimUnits(0, convert1), convertSimUnits(-20, convert1), convertSimUnits(25, convert1), convertSimUnits(40, convert1), convertSimUnits(0, convert1), convertSimUnits(0, convert1), convertSimUnits(0, convert1));//returns arraylist of modified values
                        weatherSimulate.doSimulation(simulateArray2, disaster, convertSimUnits(0, convert2), convertSimUnits(-40, convert2), convertSimUnits(-120, convert2), convertSimUnits(160, convert2), convertSimUnits(0, convert2), convertSimUnits(0, convert2), convertSimUnits(0, convert2));
                        drawScreen2(simulateArray1, "WIND SPEED", checkC.isSelected(), disaster, simGraph1, 3);//draws screen to reflect changes in wind speed
                    }
                    else {
                        imgSim.setLayoutX(568);//moves image
                        imgSim.setLayoutY(92);
                        lblSim.setText("TRAVELING PAST NEW ENGLAND, THE HURRICANE WILL BECOME EXTRATROPICAL OVER EASTERN CANADA");
                        weatherSimulate.doSimulation(simulateArray1, disaster, convertSimUnits(0, convert1), convertSimUnits(-27, convert1), convertSimUnits(-20, convert1), convertSimUnits(30, convert1), convertSimUnits(0, convert1), convertSimUnits(0, convert1), convertSimUnits(0, convert1));//returns arraylist of modified values
                        weatherSimulate.doSimulation(simulateArray2, disaster, convertSimUnits(0, convert2), convertSimUnits(-70, convert2), convertSimUnits(90, convert2), convertSimUnits(120, convert2), convertSimUnits(0, convert2), convertSimUnits(0, convert2), convertSimUnits(0, convert2));
                        drawScreen2(simulateArray1, "WIND SPEED", checkC.isSelected(), disaster, simGraph1, 3);//draws screen to reflect changes in wind speed
                    }
                    count++;//determines next action
                    timer = System.nanoTime();//resets time
                }
                else if (now - timer> 8000000000.0 && count == 7) {
                    if (disaster.equals("Y")) {
                        drawScreen2(simulateArray1, "TEMPERATURE", checkC.isSelected(), disaster, simGraph1, 3);//draws screen to reflect changes in temperature
                    }
                    else {
                        drawScreen2(simulateArray2, "PRECIPITATION", checkC.isSelected(), disaster, simGraph2, 3);//draws screen to reflect changes in precipitation
                    }
                    count++;//determines next action
                    timer = System.nanoTime();//resets time
                }
                else if (now - timer > 8000000000.0 && count == 8) {//simulation ends
                    isSimulation = false;
                    imgSim.setImage(null);
                    drawScreen();//draws original screen
                    startSimulation(false);//enables appropriate items
                    stop();//stops timer
                }
            }
        }.start();
    }

    //draws screen for simulation
    public void drawScreen2(ArrayList<String> arrayData, String category, boolean metric, String type, ArrayList<Double> graphSim, int index) {
        String units = "";
        double avgValue = 0.0;
        double size = 0.0;
        for (int i = 0; i < arrayData.size(); i ++) {
            if (!arrayData.get(i).equals("N/A")) {//uses data if it exists
                if (metric) {//units are metric
                    checkF.setSelected(false);
                    checkC.setSelected(true);
                    convert = false;
                    if (category.equals("TEMPERATURE")) {//data is temperature
                        units = " °C";
                        choice1.getSelectionModel().select(0);
                        check1.setSelected(true);
                        check2.setSelected(false);
                        check3.setSelected(false);
                        String[] info = arrayData.get(i).split(" ", -1);
                        double newValue = Double.parseDouble(info[0]);
                        avgValue += newValue;//adds up all the values
                        size ++;//counts how many values are added
                        lblT1.setText("> 35");
                        lblT2.setText("25 - 35");
                        lblT3.setText("15 - 25");
                        lblT4.setText("5 - 15");
                        lblT5.setText("-5 - 5");
                        lblT6.setText("< -5");
                        circles.get(i).setFill(getColor(arrayData.get(i), 35.0, 25.0, 15.0, 5.0, -5.0));
                        lblUnits.setText("CELSIUS");
                    }
                    else if (category.equals("PRECIPITATION")) {//data is precipitation
                        choice1.getSelectionModel().select(1);
                        check1.setSelected(true);
                        check2.setSelected(false);
                        check3.setSelected(false);
                        units = " mm";
                        String[] info = arrayData.get(i).split(" ", -1);
                        double newValue = Double.parseDouble(info[0]);
                        String name = info[1];
                        if (type.equals("HS")) {//Gulf Coast hurricane
                            if (name.equals("Florida") || name.equals("Alabama") || name.equals("Mississippi") || name.equals("Louisiana") || name.equals("Texas")) {
                                avgValue += newValue;//adds up all the values only for affected locations
                                size++;//counts how many values are added
                            }
                        }
                        else if (type.equals("HE")){//East Coast hurricane
                            if (name.equals("Florida") || name.equals("Georgia") || name.equals("South/Carolina") || name.equals("North/Carolina") || name.equals("Virginia")|| name.equals("Maryland") || name.equals("New/Jersey")
                                    || name.equals("New/Hampshire") || name.equals("Massachusetts") || name.equals("Maine")) {
                                avgValue += newValue;//adds up all the values only for affected locations
                                size++;//counts how many values are added
                            }
                        }
                        else {//Yellowstone affects all locations
                            avgValue += newValue;//adds up all the values
                            size++;//counts how many values are added
                        }
                        lblT1.setText("> 50");
                        lblT2.setText("40 - 50");
                        lblT3.setText("30 - 40");
                        lblT4.setText("20 - 30");
                        lblT5.setText("10 - 20");
                        lblT6.setText("< 10");
                        circles.get(i).setFill(getColor(arrayData.get(i), 50.0, 40.0, 30.0, 20.0, 10.0));
                        lblUnits.setText("MILLIMETRES");
                    }
                    else {//data is wind speed
                        choice1.getSelectionModel().select(1);
                        check1.setSelected(false);
                        check2.setSelected(false);
                        check3.setSelected(true);
                        units = " m/s";
                        String[] info = arrayData.get(i).split(" ", -1);
                        double newValue = Double.parseDouble(info[0]);
                        String name = info[1];
                        if (type.equals("HS")) {//Gulf Coast hurricane
                            if (name.equals("Florida") || name.equals("Alabama") || name.equals("Mississippi") || name.equals("Louisiana") || name.equals("Texas")) {
                                avgValue += newValue;//adds up all the values only for affected locations
                                size++;//counts how many values are added
                            }
                        }
                        else {//East Coast hurricane
                            if (name.equals("Florida") || name.equals("Georgia") || name.equals("South/Carolina") || name.equals("North/Carolina") || name.equals("Virginia")|| name.equals("Maryland") || name.equals("New/Jersey")
                                    || name.equals("New/Hampshire") || name.equals("Massachusetts") || name.equals("Maine")) {
                                avgValue += newValue;//adds up all the values only for affected locations
                                size++;//counts how many values are added
                            }
                        }
                        lblT2.setText("13 - 20");
                        lblT3.setText("8 - 13");
                        lblT4.setText("3 - 8");
                        lblT5.setText("1 - 3");
                        lblT6.setText("< 1");
                        circles.get(i).setFill(getColor(arrayData.get(i), 20.0, 13.0, 8.0, 3.0, 1.0));
                        lblUnits.setText("METERS PER SECOND");
                    }
                }
                else {//imperial units
                    checkF.setSelected(true);
                    checkC.setSelected(false);
                    convert = true;
                    if (category.equals("TEMPERATURE")) {//data is temperature
                        choice1.getSelectionModel().select(0);
                        check1.setSelected(true);
                        check2.setSelected(false);
                        check3.setSelected(false);
                        units = " °F";
                        String[] info = arrayData.get(i).split(" ", -1);
                        double newValue = Double.parseDouble(info[0]);//adds up all the values
                        avgValue += newValue;//counts how many values are added
                        lblT1.setText("> 95");
                        lblT2.setText("77 -95");
                        lblT3.setText("59 - 77");
                        lblT4.setText("41 - 59");
                        lblT5.setText("23 - 41");
                        lblT6.setText("< 23");
                        circles.get(i).setFill(getColor(arrayData.get(i), 95.0, 77.0, 59.0, 41.0, 23.0));
                        lblUnits.setText("FAHRENHEIT");
                    }
                    else if (category.equals("PRECIPITATION")) {//data is precipitation
                        choice1.getSelectionModel().select(1);
                        check1.setSelected(true);
                        check2.setSelected(false);
                        check3.setSelected(false);
                        units = " in";
                        String[] info = arrayData.get(i).split(" ", -1);
                        double newValue = Double.parseDouble(info[0]);
                        String name = info[1];
                        if (type.equals("HS")) {//Gulf Coast hurricane
                            if (name.equals("Florida") || name.equals("Alabama") || name.equals("Mississippi") || name.equals("Louisiana") || name.equals("Texas")) {
                                avgValue += newValue;//adds up all the values only for affected locations
                                size++;//counts how many values are added
                            }
                        }
                        else if (type.equals("HE")){//East Coast hurricane
                            if (name.equals("Florida") || name.equals("Georgia") || name.equals("South/Carolina") || name.equals("North/Carolina") || name.equals("Virginia")|| name.equals("Maryland") || name.equals("New/Jersey")
                                    || name.equals("New/Hampshire") || name.equals("Massachusetts") || name.equals("Maine")) {
                                avgValue += newValue;//adds up all the values only for affected locations
                                size++;//counts how many values are added
                            }
                        }
                        else {//Yellowstone affects all locations
                            avgValue += newValue;//adds up all the values
                            size++;//counts how many values are added
                        }
                        lblT1.setText("> 2.0");
                        lblT2.setText("1.6 - 2.0");
                        lblT3.setText("1.2 - 1.6");
                        lblT4.setText("0.8 - 1.2");
                        lblT5.setText("0.4 - 0.8");
                        lblT6.setText("< 0.4");
                        circles.get(i).setFill(getColor(arrayData.get(i), 2.0, 1.6, 1.2, 0.8, 0.4));
                        lblUnits.setText("INCHES");
                    }
                    else {//data is wind speed
                        choice1.getSelectionModel().select(1);
                        check1.setSelected(false);
                        check2.setSelected(false);
                        check3.setSelected(true);
                        units = " mph";
                        String[] info = arrayData.get(i).split(" ", -1);
                        double newValue = Double.parseDouble(info[0]);
                        String name = info[1];
                        if (type.equals("HS")) {//Gulf Coast hurricane
                            if (name.equals("Florida") || name.equals("Alabama") || name.equals("Mississippi") || name.equals("Louisiana") || name.equals("Texas")) {
                                avgValue += newValue;//adds up all the values only for affected locations
                                size++;//counts how many values are added
                            }
                        }
                        else {//East Coast hurricane
                            if (name.equals("Florida") || name.equals("Georgia") || name.equals("South/Carolina") || name.equals("North/Carolina") || name.equals("Virginia")|| name.equals("Maryland") || name.equals("New/Jersey")
                                    || name.equals("New/Hampshire") || name.equals("Massachusetts") || name.equals("Maine")) {
                                avgValue += newValue;//adds up all the values only for affected locations
                                size++;//counts how many values are added
                            }
                        }
                        lblT1.setText("> 44.7");
                        lblT2.setText("29.0 - 44.7");
                        lblT3.setText("18.0 - 29.0");
                        lblT4.setText("6.7 - 18.0");
                        lblT5.setText("2.2 - 6.7");
                        lblT6.setText("< 2.2");
                        circles.get(i).setFill(getColor(arrayData.get(i), 44.7, 29.0, 18.0, 6.7, 2.2));
                        lblUnits.setText("MILES PER HOUR");
                    }
                }
            }
        }
        avgValue = avgValue / size;//finds average value by dividing total value over number of values
        lst1.getItems().clear();
        lst1.getItems().add("AVERAGE " + category + ": " + roundData(avgValue + "", true) + "*" + units);//displays average value
        lst1.getItems().add("CHANGE IN " + category + ": " + roundData(avgValue - graphSim.get(index) + "", true) + "*" + units);//displays change in value by finding difference between this value and the value preceding it
        lblGraph.setText(category);
        lblName.setText("AFFECTED AREAS");
        graphSim.add(avgValue);//adds this value to array of all values
        graph(graphSim);//graphs this array
    }

    //disables or enables specific items
    @FXML
    public void startSimulation(boolean x) {
        btnLeft.setDisable(x);
        btnRight.setDisable(x);
        btnDate.setDisable(x);
        btnToday.setDisable(x);
        txtYear.setDisable(x);
        txtMonth.setDisable(x);
        txtDay.setDisable(x);
        choice1.setDisable(x);
        check1.setDisable(x);
        check2.setDisable(x);
        check3.setDisable(x);
        checkC.setDisable(x);
        checkF.setDisable(x);
        btnSimulateY.setDisable(x);
        btnSimulateHE.setDisable(x);
        btnSimulateHS.setDisable(x);
    }
}
