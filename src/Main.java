import Models.Customer;
import Utilities.CovidJSONReader;
import Utilities.DataUtility;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main { // extends Application {
    public static void main(String[] args) {

        ArrayList<Customer> customers = DataUtility.getCustomers();
        System.out.println("customers size: "+customers.size());

        // display all customers in console
        for (Customer customer : customers) {
            System.out.println(customer);
        }

        // same thing using streams
        System.out.println("\n\nprinted from a stream\n\n");
        customers.stream().forEach(System.out::println);

        // using a stream, to find all customers in 705 area code
        System.out.println("\n\nprinted from a stream\n\n" +
        customers.stream()
                .map(Customer::getPhoneNumber) // stream converts from a stream of Customer objects to a stream of Phone Number objects
                .filter(phoneNumber -> phoneNumber.getAreaCode().equals("705")) // must return true or false
                // .filter(phoneNumber -> phoneNumber.getCityCode().equals("717")) // can stack multiple filters
                .count()); // return count of objects that meet the filter criteria
                // .forEach(System.out::println);

        // using a stream, display all customers over 55
        System.out.println("\n\nprinted from a stream ... average age of all customers\n\n");
        List<Customer> customersOver55 = customers.stream()
                        .filter(customer -> customer.getAge() >= 55)
                        .collect(Collectors.toList());
        System.out.println("number of customers over 55: " + customersOver55.size());


        // using a stream, get average age of our customers
        System.out.println("\n\nprinted from a stream ... average age of all customers\n\n");

        OptionalDouble averageAge = customers.stream()
                    .mapToDouble(Customer::getAge)
                    .average();

        if(averageAge.isPresent())
            System.out.println("average age is " + averageAge.getAsDouble());

        // what if we want key-value pairs?
        // use a stream to get blood types and number of customers for each blood type
        Map<String, Long> bloodTypeCount = customers.stream()
                                                        .map(Customer::getBloodType)
                                                        .collect(Collectors.groupingBy(Function.identity(),
                                                                Collectors.counting()));

        System.out.println(bloodTypeCount);


        // json
        CovidJSONReader.getCovidJSON();

        // if using a gui scene
        try {
            // launch(args);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /* @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("customersView.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Customers");
        // stage.getIcons().add(new Image("icon.png"));
        stage.show();
    } */
}
