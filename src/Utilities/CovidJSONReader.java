package Utilities;

import JSON.CovidJSONResponse;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileReader;

public class CovidJSONReader {

    // return an object

    public static CovidJSONResponse getCovidJSON() {

        CovidJSONResponse covidResponse = null;

        // try with resources
        try (
                // access the json file
                FileReader fileReader = new FileReader("./src/JSON/covid.json");
                JsonReader jsonReader = new JsonReader(fileReader);

                ){

            Gson gson = new Gson();
            covidResponse = gson.fromJson(jsonReader, CovidJSONResponse.class);
            System.out.println();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return covidResponse;
    }
}
