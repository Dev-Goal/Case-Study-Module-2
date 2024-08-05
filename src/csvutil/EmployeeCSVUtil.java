package csvutil;

import model.Employee;
import model.Role;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class EmployeeCSVUtil {
    public static Map<String, Employee> readEmployeeFromCSV(String filePath) {
        Map<String, Employee> employeeData = new HashMap<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))){
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length == 7) {
                    String username = values[0];
                    String password = values[1];
                    String fullName = values[2];
                    String email = values[3];
                    String phoneNumber = values[4];
                    String citizen = values[5];
                    Set<Role> roles = Set.of(values[6].split(";")).stream()
                            .map(Role::valueOf)
                            .collect(Collectors.toSet());
                    Employee employee = new Employee(username, password, fullName, email, phoneNumber, citizen);
                    employee.getRoles().clear();
                    employee.getRoles().addAll(roles);
                    employeeData.put(username, employee);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return employeeData;
    }

    public static void writeEmployeeToCSV(Map<String, Employee> employeeData, String filePath) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath))) {
            for (Employee employee : employeeData.values()) {
                String roles = employee.getRoles().stream()
                        .map(Role::name)
                        .collect(Collectors.joining(";"));
                bufferedWriter.write(String.join(",",
                        employee.getUsername(),
                        employee.getPassword(),
                        employee.getFullName(),
                        employee.getEmail(),
                        employee.getPhoneNumber(),
                        employee.getCitizen(),
                        roles));
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
