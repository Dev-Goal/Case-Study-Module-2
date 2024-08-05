package controller;

import csvutil.CustomerCSVUtil;
import csvutil.EmployeeCSVUtil;
import model.Customer;
import model.Employee;
import model.Role;
import view.HomeView;

import java.util.Map;

public class AdminController {
    private final HomeView homeView = new HomeView();
    private Map<String, Employee> employeeData;
    private static final String EMPLOYEE_FILE_PATH = "src/data/employee.csv";
    private Map<String, Customer> customerData;
    private static final String CUSTOMER_FILE_PATH = "src/data/customer.csv";

    private void loadData() {
        this.customerData = CustomerCSVUtil.readCustomerFromCSV(CUSTOMER_FILE_PATH);
        this.employeeData = EmployeeCSVUtil.readEmployeeFromCSV(EMPLOYEE_FILE_PATH);
    }

    public void showCustomerList() {
        loadData();
        homeView.showMessage("Danh sách khách hàng");
        for (Customer customer : customerData.values()) {
            if (customer.getRoles().contains(Role.ROLE_USER) || customer.getRoles().contains(Role.ROLE_CUSTOMER)) {
                homeView.showDetailRoleOfCustomer(customer);
            }
        }
    }

    public void showEmployeeList() {
        loadData();
        homeView.showMessage("Danh sách nhân viên");
        for (Employee employee : employeeData.values()) {
            if (employee.getRoles().contains(Role.ROLE_EMPLOYEE) || employee.getRoles().contains(Role.ROLE_MANAGER)) {
                homeView.showDetailRoleOfEmployee(employee);
            }
        }
    }

    public void promoteEmployee() {
        loadData();
        homeView.showMessage("Thăng cấp nhân viên");
        String username = homeView.getInput("Tên đăng nhập nhân viên bạn muốn thăng cấp: ");
        Employee employee = employeeData.get(username);
        if (employee == null || !employee.getRoles().contains(Role.ROLE_EMPLOYEE)) {
            homeView.showMessage("Đây không phải là nhân viên");
            return;
        }
        employee.removeRole(Role.ROLE_EMPLOYEE);
        employee.addRole(Role.ROLE_MANAGER);
        employeeData.put(username, employee);
        EmployeeCSVUtil.writeEmployeeToCSV(employeeData, EMPLOYEE_FILE_PATH);
        homeView.showMessage("Thăng cấp nhân viên thành công");
    }

    public void demoteEmployee() {
        loadData();
        homeView.showMessage("Giáng chức quản lý");
        String username = homeView.getInput("Tên đăng nhập quản lý bạn muốn giáng chức: ");
        Employee employee = employeeData.get(username);
        if (employee == null || !employee.getRoles().contains(Role.ROLE_MANAGER)) {
            homeView.showMessage("Đây không phải là quản lý");
            return;
        }
        employee.removeRole(Role.ROLE_MANAGER);
        employee.addRole(Role.ROLE_EMPLOYEE);
        employeeData.put(username, employee);
        EmployeeCSVUtil.writeEmployeeToCSV(employeeData, EMPLOYEE_FILE_PATH);
        homeView.showMessage("Giáng chức quản lý thành công");
    }

    public void deleteEmployee() {
        loadData();
        homeView.showMessage("Xóa nhân viên");
        String username = homeView.getInput("Tên đăng nhập nhân viên/quản lý bạn muốn xóa: ");
        Employee employee = employeeData.get(username);
        if (employee == null) {
            homeView.showMessage("Không có nhân viên này");
            return;
        } else if (!employee.getRoles().contains(Role.ROLE_EMPLOYEE) || !employee.getRoles().contains(Role.ROLE_MANAGER)) {
            homeView.showMessage("Đây không phải là nhân viên hoặc quản lý");
            return;
        } else if (employee.getRoles().contains(Role.ROLE_ADMIN)) {
            homeView.showMessage("Đây là tài khoản Admin, không thể xóa");
            return;
        }
        String message = homeView.getInput("Có chắc chắn xóa nhân viên này: ");
        if (message.equalsIgnoreCase("Có")) {
            employeeData.remove(username);
            EmployeeCSVUtil.writeEmployeeToCSV(employeeData, EMPLOYEE_FILE_PATH);
            homeView.showMessage("Xóa nhân viên/quản lý thành công");
        } else {
            homeView.showMessage("Xóa nhân viên/quản lý không thành công");
        }
    }


}
