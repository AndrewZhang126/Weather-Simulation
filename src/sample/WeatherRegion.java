package sample;

public class WeatherRegion {//weatherRegion object
    String name;
    double aws = 1000;//average wind speed
    double prcp = 1000;//precipitation
    double snow = 1000;//snow
    double sndp = 1000;//snow depth
    double tavg = 1000;//temperature average
    double tmax = 1000;//temperature maximum
    double tmin = 1000;//temperature minimum
    double wesg = 1000;//water equivalent of snow on ground
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getAws() {
        return aws;
    }

    public void setAws(double aws) {
        this.aws = aws;
    }

    public double getPrcp() {
        return prcp;
    }

    public void setPrcp(double prcp) {
        this.prcp = prcp;
    }

    public double getSnow() {
        return snow;
    }

    public void setSnow(double snow) {
        this.snow = snow;
    }

    public double getSndp() {
        return sndp;
    }

    public void setSndp(double sndp) {
        this.sndp = sndp;
    }

    public double getTavg() {
        return tavg;
    }

    public void setTavg(double tavg) {
        this.tavg = tavg;
    }

    public double getTmax() {
        return tmax;
    }

    public void setTmax(double tmax) {
        this.tmax = tmax;
    }

    public double getTmin() {
        return tmin;
    }

    public void setTmin(double tmin) {
        this.tmin = tmin;
    }

    public double getWesg() {
        return wesg;
    }

    public void setWesg(double wesg) {
        this.wesg = wesg;
    }
}
