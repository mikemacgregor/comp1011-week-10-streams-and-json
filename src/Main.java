import Models.Customer;
import Utilities.DataUtility;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main { // extends Application {
    public static void main(String[] args) {

        ArrayList<Customer> customers = DataUtility.getCustomers();
        System.out.println("customers size: "+customers.size());

        // display all customers in console
        for (Customer customer : customers) {
            System.out.println(customer);
        }


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
