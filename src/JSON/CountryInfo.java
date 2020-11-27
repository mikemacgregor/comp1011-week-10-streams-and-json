package JSON;

import com.google.gson.annotations.SerializedName;


public class CountryInfo {
    @SerializedName("Country")
    private String countryName;

    @SerializedName("TotalConfirmed")
    private int totalConfirmed;

    public CountryInfo(String countryName, int totalConfirmed) {
        this.countryName = countryName;
        this.totalConfirmed = totalConfirmed;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public int getTotalConfirmed() {
        return totalConfirmed;
    }

    public void setTotalConfirmed(int totalConfirmed) {
        this.totalConfirmed = totalConfirmed;
    }
}