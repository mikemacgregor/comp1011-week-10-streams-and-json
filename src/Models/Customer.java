package Models;

import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Customer {
    private int id;
    private String firstName;
    private String lastName;
    private String creditCardType;
    private String bloodType;
    private PhoneNumber phoneNumber;
    private Double weight;
    private Double height;
    private LocalDate birthday;

    /*
     * Constructor to retrieve a customer from the database (includes the id)
     * @param id
     * @param firstName
     * @param lastName
     * @param creditCardType
     * @param bloodType
     * @param phoneNumber
     * @param weight
     * @param height
     */
    public Customer(int id, String firstName, String lastName, String creditCardType, String bloodType,
                    String phoneNumber, Double weight, Double height, LocalDate birthday) {
        setId(id);
        setFirstName(firstName);
        setLastName(lastName);
        setCCType(creditCardType);
        setBloodType(bloodType);
        setPhoneNumber(phoneNumber);
        setWeight(weight);
        setHeight(height);
        setBirthday(birthday);
    }

    /*
     * Constructor to insert a new customer into the database (does not include the id)
     */
    /* public Customer(String firstName, String lastName, String creditCardType, String bloodType,
                    String phoneNumber, Double weight, Double height) {
        setFirstName(firstName);
        setLastName(lastName);
        setCCType(creditCardType);
        setBloodType(bloodType);
        setPhoneNumber(phoneNumber);
        setWeight(weight);
        setHeight(height);
    } */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id > 0)
            this.id = id;
        else
            throw new IllegalArgumentException("The customer's id must be a positive number");
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        // if (firstName.matches("[A-Z][a-zA-Z]*[-]?[A-z]*?") && firstName.length() <= 30)
        if (firstName.length() <= 30)
            this.firstName = firstName;
        else
            throw new IllegalArgumentException("Customers's first name must start with a capital letter and have between 1 and 30 letters");
    }

    public String getLastName() {
        return lastName;
    }

    // last name can start with lower case, i.e. Dutch names van Derjagt
    public void setLastName(String lastName) {
        // if (lastName.matches("[a-zA-Z]*[-]?[A-z]*?") && lastName.length() <= 30)
        if (lastName.length() <= 30)
            this.lastName = lastName;
        else
            throw new IllegalArgumentException("Customers's last name must and have between 1 and 30 letters");
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        // split the string phone number into three parts
        String[] row = phoneNumber.split("-");
        String areaCode = row[0].trim();
        String cityCode = row[1].trim();
        String lineNumber = row[2].trim();

        this.phoneNumber = new PhoneNumber(areaCode, cityCode, lineNumber);

        // if any of the parts of the phone number fails validation an exception thrown from PhoneNumber class
    }

    public String getCCType() {
        return creditCardType;
    }

    public void setCCType(String creditCardType) {
        if (creditCardType.toLowerCase().matches("visa") || creditCardType.toLowerCase().matches("mastercard"))
            this.creditCardType = creditCardType;
        else
            throw new IllegalArgumentException("The customer's credit card is not a valid type, Visa or Mastercard");
    }

    public String getBloodType() {
        return bloodType;
    }

    public static List<String> getBloodTypes()
    {
        List<String> bloodTypes = Arrays.asList("B+","O+","AB+","O-","A+","B-","A-","AB-");
        Collections.sort(bloodTypes);
        return bloodTypes;
    }

    public void setBloodType(String bloodType) {
        if (getBloodTypes().contains(bloodType))
            this.bloodType = bloodType;
        else
            throw new IllegalArgumentException("Blood type must be one of the following types: " + getBloodTypes());
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        if (weight >= 3 && weight <= 500)
            this.weight = weight;
        else
            throw new IllegalArgumentException("The customer's weight is outside the valid range of 3 to 500 kg");
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        if (height >= 30 && height <= 300)
            this.height = height;
        else
            throw new IllegalArgumentException("The customer's height is outside the valid range of 30 to 300 cm");
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public int getAge() {
        return Period.between(birthday, LocalDate.now()).getYears();
    }

    // weight/height/height*10000 ... weight in kg, height in cm
    public Double getBMI() {
        return (weight/height/height * 10000);
    }

    public String getBMIResult() {
        Double bmi = getBMI();
        String bmiResult = "";
        if(bmi < 19) {
            bmiResult = "underweight";
        } else if(bmi < 25) {
            bmiResult = "healthy";
        } else if(bmi < 30) {
            bmiResult = "overweight";
        } else if(bmi < 40) {
            bmiResult = "obese";
        } else {
            bmiResult = "extremely obese";
        }

        return bmiResult;
    }

    public Boolean contains(String searchString) {
        searchString = searchString.toLowerCase();
        if(firstName.toLowerCase().contains(searchString)
                || lastName.toLowerCase().contains(searchString)
                || creditCardType.toLowerCase().contains(searchString)
                || bloodType.toLowerCase().contains(searchString)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString()
    {
        return String.format("%d %s %s %d %.1f %.1f %.1f", id, firstName, lastName, getAge(), height, weight, getBMI());
    }
}
