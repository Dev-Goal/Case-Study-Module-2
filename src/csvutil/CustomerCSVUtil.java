package csvutil;

import model.Customer;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class CustomerCSVUtil {
    public static Map<String, Customer> readCustomerFromCSV(String filePath) {
        Map<String, Customer> customerData = new HashMap<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length == 5) {
                    String username = values[0];
                    String password = values[1];
                    String fullName = values[2];
                    String email = values[3];
                    String phoneNumber = values[4];
                    Customer customer = new Customer(username, password, fullName, email, phoneNumber);
                    customerData.put(username, customer);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return customerData;
    }

    public static void writeCustomerToCSV(Map<String, Customer> customerData, String filePath) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath))) {
            for (Customer customer : customerData.values()) {
                bufferedWriter.write(String.join(",",
                        customer.getUsername(),
                        customer.getPassword(),
                        customer.getFullName(),
                        customer.getEmail(),
                        customer.getPhoneNumber()));
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
