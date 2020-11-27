package JSON;

import com.google.gson.annotations.SerializedName;

public class CovidJSONResponse {

    @SerializedName("Message") private String message;

    @SerializedName("Date") private String dateSent;

    @SerializedName("Countries")
    private CountryInfo[] countries;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDateSent() {
        return dateSent;
    }

    public void setDateSent(String dateSent) {
        this.dateSent = dateSent;
    }

    public CountryInfo[] getCountries() {
        return countries;
    }

    public void setCountries(CountryInfo[] countries) {
        this.countries = countries;
    }


}
