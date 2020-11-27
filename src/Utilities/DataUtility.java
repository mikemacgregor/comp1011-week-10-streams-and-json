package Utilities;

import Models.Customer;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

public class DataUtility {

    private static final String host = "localhost";
    private static final String port = "3306";
    private static final String dbname = "F20COMP1011Test2";
    private static final String user = "student";
    private static final String password = "student";

    // retrieve customers from database
    public static ArrayList<Customer> getCustomers() {

        ArrayList<Customer> customers = new ArrayList<>();

        // try with resources ... will automatically close what is in the () next to the "try"
        try(
                // connect to database
                Connection connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + dbname + "?serverTimezone=UTC", user, password);
                // sql query
                PreparedStatement getCustomers = connection.prepareStatement("SELECT * FROM customers");
                // execute query, returns results set
                ResultSet resultSet = getCustomers.executeQuery();
                ) {

            // loop over results set
            while (resultSet.next()) {

                // get birthday from string in the database
                String[] birthdayArray = resultSet.getString("birthday").split("/");
                int year = Integer.parseInt(birthdayArray[2]);
                int month = Integer.parseInt(birthdayArray[0]);
                int day = Integer.parseInt(birthdayArray[1]);
                LocalDate birthday = LocalDate.of(year, month, day);

                Customer newCustomer = new Customer(
                        resultSet.getInt("number"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getString("cctype"),
                        resultSet.getString("bloodtype"),
                        resultSet.getString("telephonenumber"),
                        Double.parseDouble(resultSet.getString("kilograms")),
                        Double.parseDouble(resultSet.getString("centimeters")),
                        birthday
                );

                customers.add(newCustomer);

            }

            return customers;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;

        }
    }

    public static ArrayList<Customer> getTallest(ArrayList<Customer> customers) {

        // get all customers
        // ArrayList<Customer> customers = getCustomers();

        // sort our customers from tallest (highest) to shortest (lowest)
        Collections.sort(customers, (customer1, customer2) -> {
            return Double.compare(customer2.getHeight(), customer1.getHeight());
        });

        return customers;
    }

    public static ArrayList<Customer> getCustomersInAreaCode(ArrayList<Customer> allCustomers, String areaCode) {

        // create empty filtered list
        ArrayList<Customer> filteredList = new ArrayList<>();

        // loop over customers and check if they match the supplied area code
        for (Customer customer : allCustomers) {
            if (customer.getPhoneNumber().getAreaCode().matches(areaCode))
                filteredList.add(customer);
        }

        return filteredList;
    }

    public static ArrayList<Customer> getCustomersWithString(ArrayList<Customer> allCustomers, String searchString) {

        // create empty filtered list
        ArrayList<Customer> filteredList = new ArrayList<>();

        // loop over customers and check if they match the search string
        for (Customer customer : allCustomers) {
            if (customer.contains(searchString))
                filteredList.add(customer);
        }

        return filteredList;
    }

    public static Double getAverageBMI(ArrayList<Customer> allCustomers) {

        // variable for average bmi calculation
        Double bmiSum = 0.0;

        // loop over customers to sum bmi
        for (Customer customer : allCustomers) {
            bmiSum += customer.getBMI();
        }

        return bmiSum / allCustomers.size();
    }

    public static TreeMap<String, Integer> getBloodTypeCounts(ArrayList<Customer> allCustomers) {

        // create initial tree map with all blood types and count of zero for each
        TreeMap<String, Integer> bloodTypeCounts = new TreeMap<>();

        List<String> bloodTypes = Customer.getBloodTypes();

        for (String bloodType: bloodTypes) {
            bloodTypeCounts.put(bloodType, 0);
        }

        // loop over customers to add count of blood type
        for (Customer customer : allCustomers) {
            bloodTypeCounts.put( customer.getBloodType(), bloodTypeCounts.get(customer.getBloodType())+1 );
        }

        return bloodTypeCounts;
    }


}
