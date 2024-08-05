package csvutil;

import model.Customer;
import model.Role;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class CustomerCSVUtil {
    public static Map<String, Customer> readCustomerFromCSV(String filePath) {
        Map<String, Customer> customerData = new HashMap<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length == 6) {
                    String username = values[0];
                    String password = values[1];
                    String fullName = values[2];
                    String email = values[3];
                    String phoneNumber = values[4];
                    Set<Role> roles = Set.of(values[5].split(";")).stream()
                            .map(Role::valueOf)
                            .collect(Collectors.toSet());
                    Customer customer = new Customer(username, password, fullName, email, phoneNumber);
                    customer.getRoles().clear();
                    customer.getRoles().addAll(roles);
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
                String roles = customer.getRoles().stream()
                        .map(Role::name)
                        .collect(Collectors.joining(";"));
                bufferedWriter.write(String.join(",",
                        customer.getUsername(),
                        customer.getPassword(),
                        customer.getFullName(),
                        customer.getEmail(),
                        customer.getPhoneNumber(),
                        roles));
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
