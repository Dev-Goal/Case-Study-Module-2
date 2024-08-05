package controller;

import model.Employee;
import model.Role;
import view.HomeView;

import java.util.HashMap;
import java.util.Map;

public class EmployeeController {
    private final HomeView homeView = new HomeView();
    private final Map<String, Employee> employeeData = new HashMap<>();

    public void signUpEmployee() {
        String username = homeView.getInput("Tên đăng nhập: ");
        if (employeeData.containsKey(username) && employeeData.containsValue(Role.ROLE_EMPLOYEE)) {
            homeView.showMessage("Tên đăng nhập đã được sử dụng");
            return;
        }
        String password = homeView.getInput("Mật khẩu: ");
        String fullName = homeView.getInput("Họ và tên: ");
        String email = homeView.getInput("Email: ");
        String phoneNumber = homeView.getInput("Số điện thoại: ");
        String citizen = homeView.getInput("CCCD: ");
        Employee employee = new Employee(username, password, fullName, email, phoneNumber, citizen);
        employeeData.put(username, employee);
        homeView.showMessage("Đăng ký nhân viên thành công");
    }

    public void signInEmployee() {
        String username = homeView.getInput("Tên đăng nhập: ");
        String password = homeView.getInput("Mật khẩu: ");
        Employee employee = employeeData.get(username);
        if (employee == null || !employee.getPassword().equals(password)) {
            homeView.showMessage("Tên đăng nhập hoặc mật khẩu không đúng");
            return;
        }
        homeView.showMessage("Đăng nhập thành công");
        showMenuEmployee();
    }

    private void showMenuEmployee() {
        homeView.showMessage("Hello Employee");
    }

    public void showEmployeeList() {
        homeView.showMessage("Danh sách nhân viên");
        for (Employee employee : employeeData.values()) {
            if (employee.getRoles().contains(Role.ROLE_EMPLOYEE) || employee.getRoles().contains(Role.ROLE_MANAGER)) {
                homeView.showDetailRoleOfEmployee(employee);
            }
        }
    }

    public void promoteEmployee() {
        homeView.showMessage("Thăng cấp nhân viên");
        String username = homeView.getInput("Tên đăng nhập nhân viên bạn muốn thăng cấp: ");
        Employee employee = employeeData.get(username);
        if (employee == null || !employee.getRoles().contains(Role.ROLE_EMPLOYEE)) {
            homeView.showMessage("Đây không phải là nhân viên");
            return;
        }
        employee.removeRole(Role.ROLE_EMPLOYEE);
        employee.addRole(Role.ROLE_MANAGER);
        homeView.showMessage("Thăng cấp nhân viên thành công");
    }

    public void demoteEmployee() {
        homeView.showMessage("Giáng chức quản lý");
        String username = homeView.getInput("Tên đăng nhập quản lý bạn muốn giáng chức: ");
        Employee employee = employeeData.get(username);
        if (employee == null || !employee.getRoles().contains(Role.ROLE_MANAGER)) {
            homeView.showMessage("Đây không phải là quản lý");
            return;
        }
        employee.removeRole(Role.ROLE_MANAGER);
        employee.addRole(Role.ROLE_EMPLOYEE);
        homeView.showMessage("Giáng chức quản lý thành công");
    }

    public void deleteEmployee() {
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
            homeView.showMessage("Xóa nhân viên/quản lý thành công");
        } else {
            homeView.showMessage("Xóa nhân viên/quản lý không thành công");
        }
    }
}
