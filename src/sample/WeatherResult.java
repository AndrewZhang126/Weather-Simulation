package sample;

import java.util.List;

public class WeatherResult {//organizes weatherResult from API
    public List<WeatherData> getResults() {
        return results;
    }

    public void setResults(List<WeatherData> results) {
        this.results = results;
    }

    List<WeatherData> results;
    MetaData metadata;

    public MetaData getMetadata() {
        return metadata;
    }

    public void setMetadata(MetaData metadata) {
        this.metadata = metadata;
    }
}
